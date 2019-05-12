package com.tianwen.springcloud.microservice.resource.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianwen.springcloud.commonapi.base.IService;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.constant.IStateCode;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.resource.constant.IResourceMicroConstants;
import com.tianwen.springcloud.microservice.resource.dao.ResourceMapper;
import com.tianwen.springcloud.microservice.resource.entity.Resource;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ESearchService{
    @Value("${ESEARCH_SERVER}")
    protected String esearchServer;

    @Autowired
    protected ResourceMapper resourceMapper;

    public JSONObject getKeyword()
    {
        JSONObject keyword, fields;

        keyword = new JSONObject();
        keyword.put("type", "keyword");
        keyword.put("ignore_above", 256);
        fields = new JSONObject();
        fields.put("keyword", keyword);

        return fields;
    }

    public String getSqlQueryResult(String query) throws Exception{
        JSONObject jsonQuery = new JSONObject();

        jsonQuery.put("query", query);
        HttpPost httpPost = new HttpPost("http://" + esearchServer + "/_xpack/sql?format=json");

        return doRequestOperation(httpPost, jsonQuery);
    }

    public List<String> getObjectIdsByQuery(String query) throws Exception{
        String retVal = getSqlQueryResult(query);

        JSONObject resData = JSONObject.parseObject(retVal);
        JSONArray rows = resData.getJSONArray("rows");
        if (rows == null)
            rows = new JSONArray();
        List<String> objectids = new ArrayList<>();

        int i, len = rows.size();
        objectids.add("");
        objectids.add("");
        for (i = 0; i < len; i++) {
            JSONArray array = rows.getJSONArray(i);
            objectids.add(array.getString(0));
        }

        objectids.add("");
        objectids.add("");

        return objectids;
    }

    public JSONObject getExtraQueryString(String field, String value){
        JSONObject query = new JSONObject();
        /*
        --------------- begin -----------------------------
        @author: jong
        @date: 2019-01-30
        Enable partial-match in search. For Chinese words, "*" is not working, so compose query string as "AAA AAA*"
        to support search for both Chinese and English text
         */
        String searchValue = value.trim() + " " + value.trim() + "*";
        return  getQueryString(field, searchValue);
        /*
        @author: jong
        ---------------- end -------------------------------
         */
    }

    public JSONObject getQueryString(String field, String value){
        JSONObject query = new JSONObject();
        String searchValue = value;
        query.put("query", searchValue);
        List<String> fields = new ArrayList<>();
        fields.add(field);
        query.put("fields", fields);

        JSONObject query_string = new JSONObject();
        query_string.put("query_string", query);

        return query_string;
    }

    public JSONObject getBoolConditions(String operator){
        JSONArray conditions = new JSONArray();
        JSONObject oprObj = new JSONObject();
        JSONObject boolObj = new JSONObject();

        oprObj.put(operator, conditions);
        boolObj.put("bool", oprObj);

        return boolObj;
    }

    public JSONObject getMatchConditions(String key, Object value){
        JSONObject item = new JSONObject();
        item.put(key, value);

        JSONObject match = new JSONObject();
        match.put("match", item);

        return match;
    }

    public String getConditionValue(QueryCondition condition, int index){
        String value = "";
        if (condition != null){
            Object[] values = condition.getFieldValues();
            if (values != null && values.length > index && values[index] != null)
                value = values[index].toString();
        }
        return value;
    }

    public void fixQueryTree(QueryTree queryTree) throws Exception{
        int i, len;

        if (queryTree.getQueryCondition("activityids") != null || queryTree.getQueryCondition("activityid") != null || queryTree.getQueryCondition("activitytype") != null){
            QueryCondition idsCond = queryTree.getQueryCondition("activityids");
            QueryCondition idCond = queryTree.getQueryCondition("activityid");
            QueryCondition typeCond = queryTree.getQueryCondition("activitytype");

            QueryTree activityQuery = new QueryTree();
            if (idsCond != null) {
                activityQuery.addCondition(idsCond);
                queryTree.getConditions().remove(idsCond);
            }
            if (idCond != null) {
                activityQuery.addCondition(idCond);
                queryTree.getConditions().remove(idCond);
            }
            if (typeCond != null) {
                activityQuery.addCondition(typeCond);
                queryTree.getConditions().remove(typeCond);
            }
            Map<String, Object> map = QueryUtils.queryTree2Map(activityQuery);
            List<String> contentids = resourceMapper.getActivityContentIds(map);
            //contentids.add("");
            //contentids.add("");
            queryTree.addCondition(new QueryCondition("contentid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, contentids));
        }

        if (queryTree.getQueryCondition("orgid") != null) {
            QueryCondition orgCond = queryTree.getQueryCondition("orgid");
            String value = "";

            try {
                Object[] values = orgCond.getFieldValues();
                value = StringUtil.join(values, " ");
            } catch (Exception e) {
            }

            queryTree.getConditions().remove(orgCond);

            if (!value.isEmpty()) {
                String query = "select userid from users where orgid like '" + value + "'";
                List<String> userids = getObjectIdsByQuery(query);
                queryTree.addCondition(new QueryCondition("creatorids", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, userids));
            }
        }

        if (queryTree.getQueryCondition("onelabel") != null) {
            QueryCondition labelCond = queryTree.getQueryCondition("onelabel");
            List<String> onelabelids = new ArrayList<>();

            queryTree.getConditions().remove(labelCond);

            len = labelCond.getFieldValues() == null ? 0 : labelCond.getFieldValues().length;
            Boolean noCondFlag = true;
            for (i = 0; i < len; i++) {
                String value = getConditionValue(labelCond, i);

                if (!value.isEmpty()) {
                    noCondFlag = false;
                    String query = "select labelid from labels where labelname = '" + value + "'";
                    try {
                        onelabelids.addAll(getObjectIdsByQuery(query));
                    }
                    catch (Exception e){}
                }
            }

            if (noCondFlag == false)
                queryTree.addCondition(new QueryCondition("onelabelid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, onelabelids));
        }

        if (queryTree.getQueryCondition("twolabel") != null) {
            QueryCondition labelCond = queryTree.getQueryCondition("twolabel");
            String value = getConditionValue(labelCond, 0);

            Boolean noCondFlag = true;
            queryTree.getConditions().remove(labelCond);
            List<String> twolabelids = new ArrayList<>();
            if (!value.isEmpty()) {
                noCondFlag = false;
                String query = "select labelid from labels where labelname = '" + value + "'";
                twolabelids = getObjectIdsByQuery(query);
            }
            if (twolabelids != null && twolabelids.size() < 2){
                twolabelids.add("");
                twolabelids.add("");
            }

            if (noCondFlag == false)
                queryTree.addCondition(new QueryCondition("twolabelid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, twolabelids));
        }

        if (queryTree.getQueryCondition("threelabel") != null) {
            QueryCondition labelCond = queryTree.getQueryCondition("threelabel");
            String value = getConditionValue(labelCond, 0);

            queryTree.getConditions().remove(labelCond);
            List<String> threelabelids = new ArrayList<>();
            Boolean noCondFlag = true;
            if (!value.isEmpty()) {
                noCondFlag = false;
                String query = "select labelid from labels where labelname = '" + value + "'";
                threelabelids = getObjectIdsByQuery(query);
            }
            if (threelabelids != null && threelabelids.size() < 2){
                threelabelids.add("");
                threelabelids.add("");
            }

            if (noCondFlag == false)
                queryTree.addCondition(new QueryCondition("threelabelid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, threelabelids));
        }

        if (queryTree.getQueryCondition("creatorname") != null) {
            QueryCondition creatorCond = queryTree.getQueryCondition("creatorname");
            String value = getConditionValue(creatorCond, 0);

            queryTree.getConditions().remove(creatorCond);

            if (!value.isEmpty()) {
                String query = "select userid from users where realname like '" + value + "'";
                List<String> userids = getObjectIdsByQuery(query);
                queryTree.addCondition(new QueryCondition("creatorids", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, userids));
            }
        }

        if (queryTree.getQueryCondition("catalogids") != null) {
            QueryCondition chapterCond = queryTree.getQueryCondition("catalogids");
            String value = "";

            try {
                Object[] values = chapterCond.getFieldValues();
                value = StringUtil.join(values, " ");
            } catch (Exception e) {
            }

            queryTree.getConditions().remove(chapterCond);

            if (!value.isEmpty()) {
                String query = "select resourceno from res_con_catalog where chapterid like '" + value + "'";
                List<String> contentids = getObjectIdsByQuery(query);
                queryTree.addCondition(new QueryCondition("contentid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, contentids));
            }
        }

        /////////////////////////////// special theme begin
        if (queryTree.getQueryCondition("themeids") != null)
        {
            QueryCondition chapterCond = queryTree.getQueryCondition("themeids");
            String value = "";

            try {
                Object[] values = chapterCond.getFieldValues();
                value = StringUtil.join(values, " ");
            } catch (Exception e) {}

            queryTree.getConditions().remove(chapterCond);

            if (!value.isEmpty()) {
                String query = "select resourceno from res_con_theme where themeid like '" + value + "'";
                String retVal = getSqlQueryResult(query);

                JSONObject resData = JSONObject.parseObject(retVal);
                JSONArray rows = resData.getJSONArray("rows");
                if (rows == null)
                    rows = new JSONArray();
                List<String> resources = new ArrayList<>();
                len = rows.size();

                for (i = 0; i < len; i++)
                {
                    JSONArray array = rows.getJSONArray(i);
                    resources.add(array.getString(0));
                }

                resources.add("");
                resources.add("");
                queryTree.addCondition(new QueryCondition("contentid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, resources));
            }
        }
    }

    public JSONObject getSortScript(String script, String type, String order){
        JSONObject jsonScript = new JSONObject();
        jsonScript.put("script", script);
        jsonScript.put("type", type);
        jsonScript.put("order", order);

        JSONObject sort = new JSONObject();
        sort.put("_script", jsonScript);

        return sort;
    }

    public JSONObject getNameSortScript(String key, String value){
        return getSortScript("(doc['" + key + ".keyword'].value != null && doc['" + key + ".keyword'].value.equalsIgnoreCase(\"" + value + "\") == true) ? 1 : 0",
                "number", "desc");
    }

    public JSONObject getSpecialIdSortScript(String key, String value){
        return getSortScript("String str = \"" + value + "\"; return str.indexOf(doc['" + key + ".keyword'].value);",
                "number", "desc");
    }

    public JSONObject getImportRemark() {
        JSONObject remarksObject = getBoolConditions("must");
        JSONArray remarksArray = remarksObject.getJSONObject("bool").getJSONArray("must");

        JSONObject existField = new JSONObject();
        existField.put("field", "remarks");
        existField.put("boost", 1);

        JSONObject exist = new JSONObject();
        exist.put("exists", existField);

        JSONObject valueObject = getBoolConditions("must_not");
        JSONArray valueArray = valueObject.getJSONObject("bool").getJSONArray("must_not");
        JSONObject keyword = new JSONObject();
        keyword.put("value", "");
        keyword.put("boost", 1);
        JSONObject term = new JSONObject();
        term.put("remarks.keyword", keyword);
        JSONObject valueItem = new JSONObject();
        valueItem.put("term", term);
        valueArray.add(valueItem);

        remarksArray.add(exist);
        remarksArray.add(valueObject);

        return remarksObject;
    }

    public String doRequest(HttpUriRequest request) throws IOException{
        HttpClient httpClient = new DefaultHttpClient();

        request.setHeader("Content-Type", "application/json");
        request.setHeader("Accept", "application/json");

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        String retVal = "";
        if (entity != null)
            retVal = EntityUtils.toString(entity, HTTP.UTF_8);

        return retVal;
    }

    public String doRequestOperation(HttpEntityEnclosingRequestBase request, JSONObject json) throws Exception{
        StringEntity paramEntity = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);
        request.setEntity(paramEntity);
        return doRequest(request);
    }

    public JSONObject addSourceData(List<String> includeFields, List<String> excludeFields){
        JSONObject source = new JSONObject();
        source.put("includes", includeFields);
        source.put("excludes", excludeFields);

        return source;
    }

    public JSONObject addIncludeFields(List<String> includeFields){
        return addSourceData(includeFields, new ArrayList<>());
    }

    public JSONObject addIncludeField(String fieldName){
        List<String> includes = new ArrayList<>();
        includes.add(fieldName);
        return addIncludeFields(includes);
    }

    public JSONObject makeMapping(String esType, String[] keys, String[] types){
        int i, len = keys.length;

        JSONObject fields = new JSONObject();
        JSONObject properties = new JSONObject();
        JSONObject type = new JSONObject();
        JSONObject mapping = new JSONObject();

        for (i = 0; i < len; i++)
        {
            JSONObject item = new JSONObject();

            item.put("type", types[i]);
            if (types[i].equalsIgnoreCase("text")) {
                item.put("fields", getKeyword());
                item.put("search_analyzer", "ik_smart");
                item.put("analyzer", "ik_smart");
            }
            else if (types[i].equalsIgnoreCase("date"))
            {
                item.put("fields", getKeyword());
                item.put("format", "yyyy-MM-dd HH:mm:ss");
            }

            fields.put(keys[i], item);
        }

        properties.put("properties", fields);
        type.put(esType, properties);
        mapping.put("mappings", type);

        return mapping;
    }

    public Response notFoundServerError() {
        return new Response(IStateCode.HTTP_404, IResourceMicroConstants.ES_SERVER_ERROR);
    }
}
