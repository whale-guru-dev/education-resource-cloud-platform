package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.constant.IStateCode;
import com.tianwen.springcloud.commonapi.query.OrderMethod;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.GoodMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.constant.IBussinessMicroConstants;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Good;
import com.tianwen.springcloud.microservice.bussinessassist.service.ESearchService;
import com.tianwen.springcloud.microservice.bussinessassist.service.GoodService;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/good")
public class GoodController extends AbstractCRUDController<Good> implements GoodMicroApi {
    @Autowired
    private GoodService goodService;

    @Autowired
    private ESearchService eSearchService;

    @Value("${ESEARCH_SERVER}")
    protected String esearchServer;

    @Override
    public Response<Good> makeESDb() {
        String indexUrl = "http://" + esearchServer + "/" + IBussinessMicroConstants.INDEX_GOOD;
        HttpDelete httpDelete = new HttpDelete(indexUrl);

        try{
            eSearchService.doRequest(httpDelete);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        String[] keys = {"goodid", "goodtype", "productid", "allowpaymenttype", "onshelftime", "downshelftime",
                "goodprice", "merchantno", "userid", "createtime", "lastmodifytime", "status"};
        String[] types = {"text", "text", "text", "text", "date", "date", "double", "text", "text", "date", "date", "text"};

        JSONObject mapping = eSearchService.makeMapping(IBussinessMicroConstants.TYPE_GOOD, keys, types);
        HttpPut httpPut = new HttpPut(indexUrl);

        try {
            eSearchService.doRequestOperation(httpPut, mapping);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        return new Response<>();
    }

    @Override
    public Response<Good> add(@RequestBody Good entity) {
        Response<Good> dbResp = super.add(entity);
        if (dbResp.getServerResult().getResultCode().equalsIgnoreCase(IStateCode.HTTP_200)) {
            Response<Good> esResp = saveToES(entity);
            if (!esResp.getServerResult().getResultCode().equalsIgnoreCase(IStateCode.HTTP_200)) {
                super.delete(entity.getGoodid());
                return esResp;
            }
        }
        return dbResp;
    }

    @Override
    public Response<Good> batchSaveToES(@RequestBody QueryTree queryTree) {
        int start;
        try{
            start = (int)queryTree.getQueryCondition("start").getFieldValues()[0];
        }
        catch (Exception e) { start = 1; }

        int pageSize = 50;
        int pageNo = start / pageSize;

        if (pageNo == 0)
            pageNo = 1;

            QueryTree resQuery = new QueryTree();
            resQuery.getPagination().setNumPerPage(pageSize);
            resQuery.getPagination().setPageNo(pageNo);

            resQuery.orderBy("createtime", OrderMethod.Method.ASC);

            List<Good> dataList = search(resQuery).getPageInfo().getList();

            if (!CollectionUtils.isEmpty(dataList)) {
                for (Good item : dataList)
                    saveToES(item);
            }
            else
                return new Response<>(IStateCode.PARAMETER_IS_INVALID, "");

            return new Response<>();
    }

    @Override
    public Response<Good> saveToES(@RequestBody Good entity) {
        JSONObject json = new JSONObject();

        json.put("goodid", entity.getGoodid());
        json.put("goodtype", entity.getGoodtype());
        json.put("productid", entity.getProductid());
        json.put("allowpaymenttype", entity.getAllowpaymenttype());
        if (entity.getOnshelftime() != null)
            json.put("onshelftime", DateUtils.formatDate(entity.getOnshelftime(), "yyyy-MM-dd HH:mm:ss"));

        if (entity.getDownshelftime() != null)
            json.put("downshelftime", DateUtils.formatDate(entity.getDownshelftime(), "yyyy-MM-dd HH:mm:ss"));

        json.put("goodprice", entity.getGoodprice());
        json.put("merchantno", entity.getMerchantno());
        json.put("userid", entity.getUserid());
        if (entity.getCreatetime() != null)
            json.put("createtime", DateUtils.formatDate(entity.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
        if (entity.getLastmodifytime() != null)
            json.put("lastmodifytime", DateUtils.formatDate(entity.getLastmodifytime(), "yyyy-MM-dd HH:mm:ss"));

        json.put("status", entity.getStatus());

        HttpPut httpPut = new HttpPut("http://" + esearchServer + "/" + IBussinessMicroConstants.INDEX_GOOD + "/" + IBussinessMicroConstants.TYPE_GOOD + "/" + entity.getGoodid());

        try
        {
            eSearchService.doRequestOperation(httpPut, json);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        return new Response<>(goodService.selectByKey(entity.getGoodid()));
    }

    @Override
    public Response<Good> removeFromES(@RequestBody Good entity) {
        HttpDelete httpDelete = new HttpDelete("http://" + esearchServer + "/" + IBussinessMicroConstants.INDEX_GOOD + "/" + IBussinessMicroConstants.TYPE_GOOD + "/" + entity.getGoodid());
        HttpClient httpClient = new DefaultHttpClient();
        try {
            httpClient.execute(httpDelete);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        return new Response<>();
    }

    @Override
    public Response<Good> removeFromES(@PathVariable(value = "id") String id) {
        HttpDelete httpDelete = new HttpDelete("http://" + esearchServer + "/" + IBussinessMicroConstants.INDEX_GOOD + "/" + IBussinessMicroConstants.TYPE_GOOD + "/" + id);
        HttpClient httpClient = new DefaultHttpClient();
        try {
            httpClient.execute(httpDelete);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        return new Response<>();
    }

    @Override
    public Response<Good> update(@RequestBody Good entity)
    {
        if (StringUtils.isEmpty(entity.getGoodid())) {
            entity.setGoodid(null);
            if (entity.getStatus() == null)
                entity.setStatus("1");
            if (entity.getOnshelftime() == null)
                entity.setOnshelftime(new Timestamp(System.currentTimeMillis()));
            if (entity.getDownshelftime() == null)
                entity.setDownshelftime(new Timestamp(System.currentTimeMillis() + 1000 * 86400 * 30));
            if (entity.getGoodtype() == null)
                entity.setGoodtype(IBussinessMicroConstants.GOOD_TYPE_RESOURCE);

            entity.setCreatetime(new Timestamp(System.currentTimeMillis()));
            entity.setLastmodifytime(new Timestamp(System.currentTimeMillis()));

            super.add(entity);
        } else {
            super.update(entity);
        }

        saveToES(goodService.selectByKey(entity.getGoodid()));

        return new Response<>(entity);
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
        Good good = null;
        switch (methodType) {
            case ADD:
                good = (Good) p;
                validateAdd(good);
                break;
            case UPDATE:
                good = (Good) p;
                validateUpdate(good);
                break;
            default: break;
        }
    }

    private void validateAdd(Good good) {
        Timestamp curtime = new Timestamp(System.currentTimeMillis());
        good.setCreatetime(curtime);
        good.setLastmodifytime(curtime);
    }

    private void validateUpdate(Good good) {
        Timestamp curtime = new Timestamp(System.currentTimeMillis());
        good.setLastmodifytime(curtime);

    }

    @Override
    public Response<Good> getList(@RequestBody QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        int totalSize = 0, pageSize = pagination.getNumPerPage(), i, len;
        List<Good> goodList = new ArrayList<>();

        try {
            eSearchService.fixQueryTree(queryTree);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        queryTree.addCondition(new QueryCondition("isgoods", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
//        queryTree.addCondition(statusCondition);
/*        return goodService.search(queryTree);*/

        while (goodList.size() < pageSize)
        {
            JSONArray totalConditions = new JSONArray();
            JSONArray sortArray = new JSONArray();

            if (queryTree.getConditions() != null) {
                List<QueryCondition> condList = queryTree.getConditions();

                JSONArray shareShould = new JSONArray();
                JSONObject shareBool = new JSONObject();
                JSONObject shareSearchkey = new JSONObject();

                for (QueryCondition condition : condList) {
                    String key = condition.getFieldName();
                    String value;
                    String rawVal = StringUtil.join(condition.getFieldValues(), " ");

                    if (key.equalsIgnoreCase("status")) continue;

                    if (key.equalsIgnoreCase("schoolsectionid") || key.equalsIgnoreCase("subjectid") || key.equalsIgnoreCase("gradeid")
                            || key.equalsIgnoreCase("editiontypeid") || key.equalsIgnoreCase("bookmodelid")
                            || key.equalsIgnoreCase("sourceids") || key.equalsIgnoreCase("contenttypes"))
                        value = rawVal.replace(',', ' ');
                    else
                        value = rawVal;

                    if (StringUtils.isEmpty(value)) continue;

                    /*if (key.equalsIgnoreCase("status"))
                        key = "goodstatus";*/
                    if (key.equalsIgnoreCase("contenttypes"))
                        key = "contenttype";
                    if (key.equalsIgnoreCase("sourceids"))
                        key = "sourceid";
                    if (key.equalsIgnoreCase("contentids"))
                        key = "contentid";
                    if (key.equalsIgnoreCase("creatorids") || key.equalsIgnoreCase("activityjoiner"))
                        key = "creator";

                    if (key.equalsIgnoreCase("begin_time")) {
                        value += " 00:00:00";
                        Timestamp begin;
                        try {
                            begin = Timestamp.valueOf(value);

                            JSONObject item = new JSONObject();
                            JSONObject range = new JSONObject();
                            JSONObject gt = new JSONObject();

                            gt.put("gt", DateUtils.formatDate(begin, "yyyy-MM-dd HH:mm:ss"));
                            range.put("createtime.keyword", gt);
                            item.put("range", range);

                            totalConditions.add(item);
                        } catch (IllegalArgumentException e) {
                        }
                    } else if (key.equalsIgnoreCase("end_time")) {
                        value += " 23:59:59";
                        Timestamp end;
                        try {
                            end = Timestamp.valueOf(value);
                        } catch (Exception e) {
                            end = null;
                        }
                        if (end != null) {
                            JSONObject item = new JSONObject();
                            JSONObject range = new JSONObject();
                            JSONObject lt = new JSONObject();

                            lt.put("lt", DateUtils.formatDate(end, "yyyy-MM-dd HH:mm:ss"));
                            range.put("createtime.keyword", lt);
                            item.put("range", range);

                            totalConditions.add(item);
                        }
                    } else if (key.equalsIgnoreCase("castleid")) {
                        JSONObject boolObj;
                        boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "2"));
                        totalConditions.add(conditions);
                    } else if (key.equalsIgnoreCase("cityid")) {
                        JSONObject boolObj;
                        boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "3"));
                        totalConditions.add(conditions);
                    } else if (key.equalsIgnoreCase("partid")) {
                        JSONObject boolObj;
                        boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "4"));
                        totalConditions.add(conditions);
                    } else if (key.equalsIgnoreCase("orgsharerangeid")) {
                        JSONObject boolObj;
                        boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "5"));
                        totalConditions.add(conditions);
                    } else if (key.equalsIgnoreCase("sortconids")) {
                        sortArray.add(eSearchService.getSpecialIdSortScript("contentid", value));

                    }
//                    else if (key.equalsIgnoreCase("sharerangekey")) {

//                        shareShould.add(eSearchService.getQueryString("sharerangekey", value));

//                    }
                    else if (key.equalsIgnoreCase("usercastleid")) {
                        JSONArray must = new JSONArray();

                        JSONObject bool = new JSONObject();

                        JSONObject searchkey = new JSONObject();

                        must.add(eSearchService.getQueryString("sharerangekey", value));
                        must.add(eSearchService.getMatchConditions("sharerange", "2"));
                        bool.put("must", must);
                        searchkey.put("bool", bool);
                        shareShould.add(searchkey);


                    } else if (key.equalsIgnoreCase("usercityid")) {
                        JSONArray must = new JSONArray();

                        JSONObject bool = new JSONObject();

                        JSONObject searchkey = new JSONObject();

                        must.add(eSearchService.getQueryString("sharerangekey", value));
                        must.add(eSearchService.getMatchConditions("sharerange", "3"));
                        bool.put("must", must);
                        searchkey.put("bool", bool);
                        shareShould.add(searchkey);

                    } else if (key.equalsIgnoreCase("userpartid")) {
                        JSONArray must = new JSONArray();

                        JSONObject bool = new JSONObject();

                        JSONObject searchkey = new JSONObject();

                        must.add(eSearchService.getQueryString("sharerangekey", value));
                        must.add(eSearchService.getMatchConditions("sharerange", "4"));
                        bool.put("must", must);
                        searchkey.put("bool", bool);
                        shareShould.add(searchkey);

                    } else if (key.equalsIgnoreCase("userorgsharerangeid")) {
                        JSONArray must = new JSONArray();

                        JSONObject bool = new JSONObject();

                        JSONObject searchkey = new JSONObject();

                        must.add(eSearchService.getQueryString("sharerangekey", value));
                        must.add(eSearchService.getMatchConditions("sharerange", "5"));
                        bool.put("must", must);
                        searchkey.put("bool", bool);
                        shareShould.add(searchkey);
                    }
                    else if (key.equalsIgnoreCase("searchkey")) {
                        //author: kmh
                        char[] elasticReserved =  {'+','-','=','&','|','>','<','!','(',')','{','}','[',']','^','\\','"','~','*','?',':','/'};
                        char[] charvalue = value.toCharArray();
                        Boolean flag = false;
                        for(int ii = 0; ii < charvalue.length;ii++){
                            for(int jj = 0; jj < elasticReserved.length;jj++){
                                if(charvalue[ii] == elasticReserved[jj]){
                                    flag = true;
                                    charvalue[ii] = ' ';
                                    break;
                                }
                            }
                        }
                        value = String.valueOf(charvalue);
                        value.trim();
                        if(value.length() == 0 && flag){
                            value = " ";
                        }//author kmh

                        JSONArray should = new JSONArray();

                        JSONObject bool = new JSONObject();

                        JSONObject searchkey = new JSONObject();

//                        should.add(eSearchService.getExtraQueryString("name.keyword", value));
                        should.add(eSearchService.getExtraQueryString("name", value));
                        //should.add(eSearchService.getQueryString("description.keyword", value));

                        bool.put("should", should);

                        searchkey.put("bool", bool);

                        totalConditions.add(searchkey);

                        /**
                         * -----------------------------------------------------------------------
                         * @author: jong
                         * @date: 2019-01-31
                         * @description: Improve relevance in elastic search by adding _score sort
                         */
                        sortArray.add(eSearchService.getNameSortScript("name", value));
                        JSONObject sortObj = new JSONObject();
                        sortObj.put("_score", "desc");
                        sortArray.add(sortObj);
                        /**
                         * @date: 2019-01-31
                         * -----------------------------------------------------------------------
                         */
                    } else if (key.equalsIgnoreCase("labelkey")) {
                        JSONObject bool = new JSONObject();
                        JSONObject should = new JSONObject();
                        JSONArray shouldCond = new JSONArray();

                        JSONObject one = new JSONObject();
                        JSONObject onematch = new JSONObject();
                        onematch.put("onelabelid", value);
                        one.put("match", onematch);
                        shouldCond.add(one);
                        JSONObject two = new JSONObject();
                        JSONObject twomatch = new JSONObject();
                        twomatch.put("twolabelid", value);
                        two.put("match", twomatch);
                        shouldCond.add(two);
                        JSONObject three = new JSONObject();
                        JSONObject threematch = new JSONObject();
                        threematch.put("threelabelid", value);
                        three.put("match", threematch);
                        shouldCond.add(three);

                        should.put("should", shouldCond);
                        bool.put("bool", should);

                        totalConditions.add(bool);
                    } else {
                        JSONObject cond = new JSONObject();
                        JSONObject term = new JSONObject();

                        cond.put(key, value);
                        term.put("match", cond);

                        totalConditions.add(term);
                    }
                }
                if(shareShould.size() > 0){

                    JSONArray must = new JSONArray();
                    JSONObject bool = new JSONObject();
                    JSONObject searchkey = new JSONObject();
                    must.add(eSearchService.getMatchConditions("sharerange", "1"));
                    bool.put("must", must);
                    searchkey.put("bool", bool);
                    shareShould.add(searchkey);
                    shareBool.put("should", shareShould);
                    shareSearchkey.put("bool", shareBool);
                    totalConditions.add(shareSearchkey);

                }

            }

            if (queryTree.getQueryCondition("remarks") != null)
                totalConditions.add(eSearchService.getImportRemark());

            JSONObject totalBool = new JSONObject();
            totalBool.put("must", totalConditions);

            JSONObject totalQuery = new JSONObject();
            totalQuery.put("bool", totalBool);

            JSONObject searchQuery = new JSONObject();

            int size = pagination.getNumPerPage();
            if (size > 10000) size = 10000;
            searchQuery.put("from", pagination.getStart());

            searchQuery.put("size", size);
            searchQuery.put("query", totalQuery);
            searchQuery.put("_source", eSearchService.addIncludeField("contentid"));

            String sortMethod = "lastupdatetime.keyword";


            if (!CollectionUtils.isEmpty(queryTree.getOrderMethods())) {
                OrderMethod method = queryTree.getOrderMethods().get(0);
                String orderBy = "";
                if (method.getField() != null)
                    orderBy = method.getField();

                if (orderBy.equals("推荐排序"))
                    sortMethod = "clicktimes";
                else if (orderBy.equals("最新上传"))
                    //sortMethod = "lastupdatetime.keyword";
                    sortMethod = "createtime.keyword";
                else if (orderBy.equals("最受欢迎")) {
                    sortArray.add(eSearchService.getSortScript("doc['viewtimes'].value+doc['collectiontimes'].value+doc['clicktimes'].value", "number", "desc"));
                    sortMethod = "";
                }
                else if (orderBy.equals("最新最热"))
                    sortMethod = "hotvalue";
                else if (orderBy.equals("最热资源"))
                    sortMethod = "hotvalue";
                else if (orderBy.equals("orderBycreatetime"))
                    sortMethod = "createtime.keyword";
                else if (orderBy.equals("downtimes"))
                    sortMethod = "downtimes";
            }
            if (!sortMethod.isEmpty()) {
                JSONObject sort = new JSONObject();
                sort.put(sortMethod, "desc");
                sortArray.add(sort);
//                searchQuery.put("sort", sortArray);
            }
            searchQuery.put("sort", sortArray);

            HttpPost httpPost = new HttpPost("http://" + esearchServer + "/contents/contents/_search");

            String retVal = "";
            JSONObject searchRes;
            JSONArray resData;

            try {
                retVal = eSearchService.doRequestOperation(httpPost, searchQuery);
            } catch (Exception e) {
                return eSearchService.notFoundServerError();
            }

            try {
                searchRes = JSONObject.parseObject(retVal);
                JSONObject resultData = searchRes.getJSONObject("hits");
                resData = resultData.getJSONArray("hits");
                len = resData.size();
                totalSize = resultData.getInteger("total");

                for (i = 0; i < len; i++) {
                    String resid = resData.getJSONObject(i).get("_id").toString();
                    Good good = goodService.getByContentid(resid);
                    if (good != null)
                        goodList.add(good);
                }

                if (totalSize < pageSize || queryTree.getPagination().getStart() + pageSize > totalSize)
                    break;
            } catch (Exception e) {}

            queryTree.getPagination().setPageNo(queryTree.getPagination().getPageNo() + 1);
        }

        List<Good> pageList = new ArrayList<>();
        if (goodList.size() < pageSize)
            pageList.addAll(goodList);
        else
            pageList = goodList.subList(0, pageSize);

        Response<Good> resp = new Response<>(pageList);
        resp.getPageInfo().setTotal(totalSize);
        return resp;
    }

    /**
     * @author jong
     * @date 2019-02-14
     * @param queryTree
     * @return
     */
    @Override
    public Response<Good> getListByStatus(@RequestBody QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        int totalSize = 0, pageSize = pagination.getNumPerPage(), i, len;
        List<Good> goodList = new ArrayList<>();

        try {
            eSearchService.fixQueryTree(queryTree);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        while (goodList.size() < pageSize) {
            JSONArray totalConditions = new JSONArray();
            JSONArray sortArray = new JSONArray();

            QueryCondition condition = queryTree.getQueryCondition("status");
            if (condition != null) {
                JSONObject jsonCond = new JSONObject();
                JSONObject term = new JSONObject();

                jsonCond.put("status", StringUtil.join(condition.getFieldValues(), " ").trim());
                term.put("match", jsonCond);

                totalConditions.add(term);
            }
            else {
                JSONObject jsonCond = new JSONObject();
                JSONObject term = new JSONObject();

                jsonCond.put("status", "0 1");
                term.put("match", jsonCond);

                totalConditions.add(term);
            }

            JSONObject totalBool = new JSONObject();
            totalBool.put("must", totalConditions);

            JSONObject totalQuery = new JSONObject();
            totalQuery.put("bool", totalBool);

            JSONObject searchQuery = new JSONObject();

            int size = pagination.getNumPerPage();
            if (size > 10000) size = 10000;
            searchQuery.put("from", pagination.getStart());

            searchQuery.put("size", size);
            searchQuery.put("query", totalQuery);
            searchQuery.put("_source", eSearchService.addIncludeField("goodid"));

            String sortMethod = "lastmodifytime.keyword";
            JSONObject sort = new JSONObject();
            sort.put(sortMethod, "desc");
            sortArray.add(sort);

            searchQuery.put("sort", sortArray);

            HttpPost httpPost = new HttpPost("http://" + esearchServer + "/goods/goods/_search");

            String retVal = "";
            JSONObject searchRes;
            JSONArray resData;

            try {
                retVal = eSearchService.doRequestOperation(httpPost, searchQuery);
            } catch (Exception e) {
                return eSearchService.notFoundServerError();
            }

            try {
                searchRes = JSONObject.parseObject(retVal);
                JSONObject resultData = searchRes.getJSONObject("hits");
                resData = resultData.getJSONArray("hits");
                len = resData.size();
                totalSize = resultData.getInteger("total");

                for (i = 0; i < len; i++) {
                    String goodid = resData.getJSONObject(i).get("_id").toString();
                    Good good = goodService.getById(goodid);
                    if (good != null)
                        goodList.add(good);
                }

                if (totalSize < pageSize || queryTree.getPagination().getStart() + pageSize > totalSize)
                    break;
            } catch (Exception e) {}

            queryTree.getPagination().setPageNo(queryTree.getPagination().getPageNo() + 1);
        }

        List<Good> pageList = new ArrayList<>();
        if (goodList.size() <= pageSize)
            pageList.addAll(goodList);
        else
            pageList = goodList.subList(0, pageSize);

        Response<Good> resp = new Response<>(pageList);
        resp.getPageInfo().setTotal(totalSize);
        return resp;
    }

    @Override
    public Response<String> getProductIds(@RequestBody QueryTree queryTree) {
        List<String> proIds = goodService.getProductIds(queryTree);
        proIds.add("");
        proIds.add("");
        return new Response<>(proIds);
    }

    @Override
    public Response<Good> getByContentid(@PathVariable(value = "contentid") String contentid) {
        Good good = goodService.getByContentid(contentid);
        return new Response<>(good);
    }

    @Override
    public Response<Good> getByListAndQueryTree(@RequestBody Map<String, Object> map) {
        List<Good> list = (List<Good>) map.get("list");
        int start = 0, pageSize = (int) map.get("numperpage"), pageno = (int) map.get("pageno");
        int count = list.size();
        if (start > list.size())
            list = new ArrayList<>();
        else
            list = list.subList(start ,start + pageSize <= list.size() ? start + pageSize : list.size());
        Page<Good> result = new Page<>(pageno, pageSize);
        result.addAll(list);
        result.setTotal(count);
        return new Response<>(result);
    }

    @Override
    public Response<Long> getCountByCreator(@PathVariable(value = "creator") String creator) {
        QueryTree queryTree = new QueryTree();
        queryTree.addCondition(new QueryCondition("creator", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, creator));

        queryTree.getPagination().setNumPerPage(1);
        Long count = getList(queryTree).getPageInfo().getTotal();

        return new Response<>(count);
    }

    @Override
    public Response<Long> getCount(@RequestBody QueryTree queryTree) {
        queryTree.getPagination().setNumPerPage(1);
        Long count = getList(queryTree).getPageInfo().getTotal();

        return new Response<>(count);
    }
}
