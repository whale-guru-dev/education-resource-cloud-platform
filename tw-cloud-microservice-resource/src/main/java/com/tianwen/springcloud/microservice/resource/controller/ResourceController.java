package com.tianwen.springcloud.microservice.resource.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.constant.IStateCode;
import com.tianwen.springcloud.commonapi.exception.ParameterException;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.OrderMethod;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.resource.api.ResourceMicroApi;
import com.tianwen.springcloud.microservice.resource.constant.IResourceMicroConstants;
import com.tianwen.springcloud.microservice.resource.entity.*;
import com.tianwen.springcloud.microservice.resource.service.ESearchService;
import com.tianwen.springcloud.microservice.resource.service.FileService;
import com.tianwen.springcloud.microservice.resource.service.ResourceService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController extends AbstractCRUDController<Resource> implements ResourceMicroApi{
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ESearchService eSearchService;

    @Autowired
    private FileService fileService;

    @Value("${ESEARCH_SERVER}")
    protected String esearchServer;

    @Override
    public Response<ResConCatalogInfo> getResConInfo(@RequestBody QueryTree queryTree) {
        List<ResConCatalogInfo> result = resourceService.getResConInfo(queryTree);

        Response<ResConCatalogInfo> resp = new Response<>(result);

        if (CollectionUtils.isEmpty(result))
            resp.getServerResult().setResultCode(IStateCode.PARAMETER_IS_INVALID);

        return resp;
    }

    @Override
    public Response<Resource> makeESDb() {
        String indexUrl = "http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CONTENT;

        try{
            HttpResponse response;
            HttpClient httpClient = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete(indexUrl);
            response = httpClient.execute(httpDelete);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String[] keys = {"contentid", "contentno", "subjectid", "editiontypeid", "gradeid", "bookmodelid", "chapter", "section", "knowledgepoint",
                "contenttype", "onelabelid", "twolabelid", "searchlabel", "oneschool", "twoschool", "fitobject", "sourceid", "sharerange", "creator",
                "createtime", "lastmodifier", "lastupdatetime", "endtime", "status", "score", "version", "viewtimes", "downtimes", "collectiontimes",
                "clicktimes", "usetime", "reslevelid", "contentkey", "epubtype", "isallowdownload", "threelabelid", "ratesum", "isgoods", "isanswer",
                "remarks", "importstatus", "filepath", "hotvalue", "isanonymity", "thumbnailpath", "sharerangekey", "schoolsectionid", "name",
                "keyword", "description", "encryptstatus"};
        String[] types = {"text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text", "text",
                "text", "text", "text", "text", "date", "text", "date", "date", "text", "long", "text", "long", "long", "long", "long", "date", "text", "text",
                "text", "text", "text", "double", "text", "text", "text", "text", "text", "long", "text", "text", "text", "text", "text", "text", "text", "text"};

        JSONObject mapping = eSearchService.makeMapping(IResourceMicroConstants.TYPE_CONTENT, keys, types);
        HttpPut httpPut = new HttpPut(indexUrl);

        try {
            eSearchService.doRequestOperation(httpPut, mapping);
        }
        catch (Exception e) {
            return eSearchService.notFoundServerError();
        }

        return new Response<>();
    }

    @Override
    public Response<Resource> batchSaveToES(@RequestBody QueryTree queryTree) {
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

        List<Resource> dataList = search(resQuery).getPageInfo().getList();

        if (!CollectionUtils.isEmpty(dataList)) {
            for (Resource item : dataList)
                saveToES(item);
        }
        else
            return new Response<>(IStateCode.PARAMETER_IS_INVALID, "");

        return new Response<>();
    }

    @Override
    public Response<Resource> saveToES(@RequestBody Resource entity) {
        JSONObject json = new JSONObject();

        json.put("contentid", entity.getContentid());
        json.put("contentno", entity.getContentno());
        json.put("schoolsectionid", entity.getSchoolsectionid());
        json.put("subjectid", entity.getSubjectid());
        json.put("gradeid", entity.getGradeid());
        json.put("editiontypeid", entity.getEditiontypeid());
        json.put("bookmodelid", entity.getBookmodelid());
        json.put("chapter", entity.getChapter());
        json.put("section", entity.getSection());
        json.put("knowledgepoint", entity.getKnowledgepoint());
        json.put("contenttype", entity.getContenttype());
        json.put("onelabelid", entity.getOnelabelid());
        json.put("twolabelid", entity.getTwolabelid());
        json.put("threelabelid", entity.getThreelabelid());
        json.put("searchlabel", entity.getSearchlabel());
        json.put("oneschool", entity.getOneschool());
        json.put("twoschool", entity.getTwoschool());
        json.put("fitobject", entity.getFitobject());
        json.put("sourceid", entity.getSourceid());
        json.put("sharerange", entity.getSharerange());
        json.put("creator", entity.getCreator());
        if (entity.getCreatetime() != null)
            json.put("createtime", DateUtils.formatDate(entity.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));

        json.put("lastmodifier", entity.getLastmodifier());
        if (entity.getLastupdatetime() != null)
            json.put("lastupdatetime", DateUtils.formatDate(entity.getLastupdatetime(), "yyyy-MM-dd HH:mm:ss"));

        if (entity.getEndtime() != null)
            json.put("endtime", DateUtils.formatDate(entity.getEndtime(), "yyyy-MM-dd HH:mm:ss"));

        json.put("status", entity.getStatus());
        json.put("score", entity.getScore());
        json.put("version", entity.getVersion());
        json.put("viewtimes", entity.getViewtimes());
        json.put("downtimes", entity.getDowntimes());
        json.put("collectiontimes", entity.getCollectiontimes());
        json.put("clicktimes", entity.getClicktimes());
        if (entity.getUsetime() != null)
            json.put("usetime", DateUtils.formatDate(entity.getUsetime(), "yyyy-MM-dd HH:mm:ss"));

        json.put("reslevelid", entity.getReslevelid());
        json.put("contentkey", entity.getContentkey());
        json.put("epubtype", entity.getEpubtype());
        json.put("isallowdownload", entity.getIsallowdownload());
        json.put("ratesum", entity.getRatesum());
        json.put("isgoods", entity.getIsgoods());
        json.put("isanswer", entity.getIsanswer());
        json.put("remarks", entity.getRemarks());
        json.put("importstatus", entity.getImportstatus());
        json.put("filepath", entity.getFilepath());
        json.put("hotvalue", entity.getHotvalue());
        json.put("isanonymity", entity.getIsanonymity());
        json.put("thumbnailpath", entity.getThumbnailpath());
        json.put("sharerangekey", entity.getSharerangekey());
        json.put("name", entity.getName());
        json.put("keyword", entity.getKeyword());
        json.put("description", entity.getDescription());
        json.put("encryptstatus", entity.getEncryptstatus());

        HttpPut httpPut = new HttpPut("http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CONTENT + "/" + IResourceMicroConstants.TYPE_CONTENT + "/" + entity.getContentid());
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            StringEntity paramEntity = new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON);
            httpPut.setEntity(paramEntity);
            httpPut.setHeader("Content-Type", "application/json");
            httpPut.setHeader("Accept", "application/json");
            HttpResponse res = httpClient.execute(httpPut);

            System.out.println(res.toString());
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        return new Response<>(entity);
    }

    @Override
    public Response<Resource> removeFromES(@RequestBody Resource entity){
        HttpDelete httpDelete = new HttpDelete("http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CONTENT + "/" + IResourceMicroConstants.TYPE_CONTENT + "/" + entity.getContentid());
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
    public Response<Resource> removeFromES(@PathVariable(value = "id") String id) {
        HttpDelete httpDelete = new HttpDelete("http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CONTENT + "/" + IResourceMicroConstants.TYPE_CONTENT + "/" + id);
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
    public Response<ResConCatalogInfo> makeConESDb() {
        String indexUrl = "http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CON_RES_CAT;
        HttpDelete httpDelete = new HttpDelete(indexUrl);
        try{
            eSearchService.doRequest(httpDelete);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        String[] keys = {"bookid", "chapterid", "resourceno", "resourcetype"};
        String[] types = {"text", "text", "text", "text"};

        JSONObject mapping = eSearchService.makeMapping(IResourceMicroConstants.TYPE_CON_RES_CAT, keys, types);
        HttpPut httpPut = new HttpPut(indexUrl);
        try {
            eSearchService.doRequestOperation(httpPut, mapping);
        }
        catch (Exception e) {
            return eSearchService.notFoundServerError();
        }

        return new Response<>();
    }

    @Override
    public Response<ResConCatalogInfo> batchSaveConToES(@RequestBody QueryTree queryTree) {
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

        List<ResConCatalogInfo> dataList = resourceService.getResConInfo(resQuery);

        if (!CollectionUtils.isEmpty(dataList)) {
            for (ResConCatalogInfo item : dataList)
                saveConToES(item);
        }
        else
            return new Response<>(IStateCode.PARAMETER_IS_INVALID, "");

        return new Response<>();
    }

    @Override
    public Response<ResConCatalogInfo> saveConToES(@RequestBody ResConCatalogInfo conInfo) {
        JSONObject json = new JSONObject();

        json.put("resourceno", conInfo.getResourceno());
        json.put("resourcetype", conInfo.getResourcetype());
        json.put("bookid", conInfo.getBookid());
        json.put("chapterid", conInfo.getChapterid());

        String chapterid = conInfo.getChapterid();
        if (chapterid != null && !chapterid.isEmpty()) {
            HttpPut httpPut = new HttpPut("http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CON_RES_CAT + "/" + IResourceMicroConstants.TYPE_CON_RES_CAT + "/" + conInfo.getResourceno() + "_" + conInfo.getChapterid());
            try {
                eSearchService.doRequestOperation(httpPut, json);
            } catch (Exception e) {
                return eSearchService.notFoundServerError();
            }
        }

        return new Response<>();
    }

    @Override
    public Response<ResConCatalogInfo> removeConFromES(@RequestBody ResConCatalogInfo conInfo) {
        HttpDelete httpDelete = new HttpDelete("http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CON_RES_CAT + "/" + IResourceMicroConstants.TYPE_CON_RES_CAT + "/" + conInfo.getResourceno() + "_" + conInfo.getChapterid());
        HttpClient httpClient = new DefaultHttpClient();

        try {
            httpClient.execute(httpDelete);
        } catch (Exception e) {}

        return new Response<>();
    }

    @Override
    public Response<Resource> get(@PathVariable(value = "contentid") String contentid) {
        Resource resource = resourceService.selectByKey(contentid);

        if (resource != null) {
            resource.setActivityid(resourceService.getActivityId(contentid));
            resource.setCatalogids(resourceService.getCatalogids(contentid));
            if (resource.getCatalogids() == null)
                resource.setCatalogids(new ArrayList<>());
            FileInfo fileInfo = fileService.getByContentid(contentid);
            if (fileInfo != null)
                resource.setFileInfo(fileInfo);

            resource.setCatalogids(resourceService.getCatalogids(contentid));
        }
        return new Response<>(resource);
    }

    @Override
    @ApiOperation(value = "根据ID删除资源信息", notes = "根据ID删除资源信息")
    @ApiImplicitParam(name = "id", value = "资源ID", required = true, dataType = "String", paramType = "path")
    @SystemControllerLog(description = "根据ID删除资源信息")
    public Response<Resource> delete(@PathVariable String id)
    {
        Response<Resource> resp = removeFromES(id);
        if (!resp.getServerResult().getResultCode().equalsIgnoreCase(IStateCode.HTTP_200))
            return resp;

        Resource entity = new Resource();
        entity.setContentid(id);
        entity.setStatus(IResourceMicroConstants.RES_STATUS_DELETE);
        super.update(entity);

        return removeFromES(id);
    }

    @Override
    public void updateHot(@RequestBody Resource resource) {
        resourceService.updateHotValue(resource);
//        super.update(resource);
        saveToES(resourceService.selectByKey(resource.getContentid()));
    }

    @Override
    public void view(@RequestBody Resource resource) {
        resourceService.updateViewTimes(resource);
//        super.update(resource);
        saveToES(resourceService.selectByKey(resource.getContentid()));
    }

    @Override
    public void click(@RequestBody Resource resource) {
        resourceService.updateClickTimes(resource);
//        super.update(resource);
        saveToES(resourceService.selectByKey(resource.getContentid()));
    }

    @Override
    public void download(@RequestBody Resource resource) {
        resourceService.updateDownloadTimes(resource);
        saveToES(resourceService.selectByKey(resource.getContentid()));
    }

    @Override
    public void collection(@RequestBody Resource resource) {
        saveToES(resourceService.selectByKey(resource.getContentid()));
        resourceService.updateCollectionTimes(resource);
//        super.update(resource);
    }

    @Override
    public void rate(@RequestBody Resource resource) {
        saveToES(resourceService.selectByKey(resource.getContentid()));
        //resourceService.updateRateSum(resource);
        super.update(resource);
    }

    @Override
    @ApiOperation(value = "获取资源列表", notes = "获取资源列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = true, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取资源列表")
    public Response<Resource> getList(@RequestBody QueryTree queryTree)
    {
        Pagination pagination = queryTree.getPagination();
        List<Resource> resourceList = new ArrayList<>();
        int totalSize = 0, pageSize = pagination.getNumPerPage(), i, len;
        queryTree.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1 2 3 4 5 6 7 9"));

        try {
            eSearchService.fixQueryTree(queryTree);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        while (resourceList.size() < pageSize) {
            JSONArray totalConditions = new JSONArray();
            JSONArray sortArray = new JSONArray();

            if (queryTree.getConditions() != null) {
                List<QueryCondition> condList = queryTree.getConditions();
                for (QueryCondition condition : condList) {
                    String key = condition.getFieldName();
                    String value;
                    String rawVal = StringUtil.join(condition.getFieldValues(), " ").trim(); // remove leading and trailing spaces

                    if (key.equalsIgnoreCase("schoolsectionid") || key.equalsIgnoreCase("subjectid") || key.equalsIgnoreCase("gradeid")
                            || key.equalsIgnoreCase("editiontypeid") || key.equalsIgnoreCase("bookmodelid")
                            || key.equalsIgnoreCase("sourceids") || key.equalsIgnoreCase("contenttypes"))
                        value = rawVal.replace(',', ' ');
                    else
                        value = rawVal;

                    if (key.equalsIgnoreCase("activityidkey") || key.equalsIgnoreCase("activitynamekey") || key.equalsIgnoreCase("activityids") || key.equalsIgnoreCase("creatorswithkey")) continue;
                    if (!key.equalsIgnoreCase("contentid") && StringUtils.isEmpty(value)) continue;

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
//                           Author : GOD
//                           Date Start : 2019-2-9 2 PM
//                           Reason : Web First page statistics error
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
//                           Author : GOD
//                           Date End : 2019-2-9 6 PM
//                           Reason : Web First page statistics error
                            totalConditions.add(item);
                        }
                    } else if (key.equalsIgnoreCase("castleid")) {
                        JSONObject boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "2"));
                    } else if (key.equalsIgnoreCase("cityid")) {
                        JSONObject boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "3"));
                    } else if (key.equalsIgnoreCase("partid")) {
                        JSONObject boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "4"));
                    } else if (key.equalsIgnoreCase("orgsharerangeid")) {
                        JSONObject boolObj = eSearchService.getBoolConditions("must");
                        JSONArray conditions = boolObj.getJSONObject("bool").getJSONArray("must");
                        conditions.add(eSearchService.getQueryString("sharerangekey", value));
                        conditions.add(eSearchService.getMatchConditions("sharerange", "5"));
                    } else if (key.equalsIgnoreCase("sortconids")){
                        sortArray.add(eSearchService.getSpecialIdSortScript("contentid", value));
                    } else if (key.equalsIgnoreCase("searchkey")) {
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

                        should.add(eSearchService.getExtraQueryString("name", value));
                        should.add(eSearchService.getQueryString("creator", queryTree.getQueryCondition("creatorswithkey").getFieldValues()[0].toString()));

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
            }

            if(queryTree.getQueryCondition("remarks") != null)
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
// Author : GOD, 资源管理 OrderBy : lastupdatetime
// Modify Reason : Xiao Said
// Data Start : 2019-1-28 11:30 AM

            String sortMethod = "lastupdatetime.keyword";
// Author : GOD, 资源管理 OrderBy : lastupdatetime
// Modify Reason : Xiao Said
// Data End : 2019-1-28 6:15 PM
            if (!CollectionUtils.isEmpty(queryTree.getOrderMethods())) {
                OrderMethod method = queryTree.getOrderMethods().get(0);
                String orderBy = "";
                if (method.getField() != null)
                    orderBy = method.getField();

                if (orderBy.equals("推荐排序"))
                    sortMethod = "clicktimes";
                else if (orderBy.equals("最新上传"))
                    sortMethod = "createtime.keyword";
                else if (orderBy.equals("最受欢迎")) {
                    /*
                     * @author: jong
                     * @date: 2019-02-08
                     */
                    sortArray.add(eSearchService.getSortScript("doc['viewtimes'].value+doc['collectiontimes'].value+doc['clicktimes'].value", "number", "desc"));
                    /*
                     * ---------------------------------
                     */
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
                searchQuery.put("sort", sortArray);
            }

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

                List<String> resourceIds = new ArrayList<>();
                for (i = 0; i < len; i++) {
                    String id = resData.getJSONObject(i).get("_id").toString();
                    resourceIds.add(id);
                }

                //  Author: Han
                // description: get resources by IDs
                List<Resource> te_tempList = resourceService.getResourcesByIds(resourceIds);
                List<Resource> tempList = new ArrayList<>();
                for(Resource temp: te_tempList){
                    FileInfo fileInfo = fileService.getByContentid(temp.getContentid());
                    temp.setFileInfo(fileInfo);
                    tempList.add(temp);
                }

//                QueryTree resQuery = new QueryTree();
// Author : GOD, 资源管理 Resource Service OrderBy : lastupdatetime
// Modify Reason : Xiao Said
// Data Start : 2019-1-28 11:30 AM
//                if (resourceIds.size() > 1)
//                    resQuery.addCondition(new QueryCondition("contentids", QueryCondition.Prepender.AND, QueryCondition.Operator.IN, resourceIds));
//                else
//                    resQuery.addCondition(new QueryCondition("contentid", QueryCondition.Prepender.AND, QueryCondition.Operator.IN, resourceIds));
//                resQuery.getPagination().setNumPerPage(resourceIds.size());
//                List<Resource> tempList = search(resQuery).getPageInfo().getList();  resourceService.search(resQuery).getPageInfo().getList();  // search(resQuery).getPageInfo().getList();
// Author : GOD, 资源管理 OrderBy : lastupdatetime
// Modify Reason : Xiao Said
// Data End : 2019-1-28 6:15 PM
                resourceList.addAll(tempList);

                if (totalSize < pageSize || queryTree.getPagination().getStart() + pageSize > totalSize)
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }

            queryTree.getPagination().setPageNo(queryTree.getPagination().getPageNo() + 1);
        }

        List<Resource> pageList = new ArrayList<>();
        if (resourceList.size() < pageSize)
            pageList.addAll(resourceList);
        else
            pageList = resourceList.subList(0, pageSize);

        Response<Resource> resp = new Response<>(pageList);
        resp.getPageInfo().setTotal(totalSize);
        return resp;
    }

    @Override
    public Response<Resource> getImportedList(@RequestBody QueryTree queryTree) {
        try{
            eSearchService.fixQueryTree(queryTree);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }
        return resourceService.getImportedList(queryTree);
    }

//    @Override
//    public Response<ElasticResource> getESearchList(@RequestBody QueryTree queryTree) {
//        List<ElasticResource> data = resourceService.getESearchResult(queryTree);
//        return new Response<>(data);
//    }

    @Override
    public Response<Resource> getAllData(@RequestBody QueryTree queryTree) {
        queryTree.addCondition(new QueryCondition("getAllData", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
        List<Resource> allData = resourceService.getAllData(queryTree);
        Response<Resource> resp = new Response<>(allData);
        return resp;
    }

    @Override
    public Response<Long> getCount(@RequestBody QueryTree queryTree) {
        queryTree.getPagination().setNumPerPage(1);
        Long count = getList(queryTree).getPageInfo().getTotal();

        return new Response<>(count);
    }

    @Override
    @ApiOperation(value = "根据ID批量资源删除", notes = "根据ID批量资源删除")
    @ApiImplicitParam(name = "contentids", value = "资源ID", required = true, dataType = "String", paramType = "body")
    @SystemControllerLog(description = "根据ID批量资源删除")
    public Response<Resource> batchDelete(@RequestBody List<String> ids) {
        List<Resource> removeList = new ArrayList<>();
        List<String> internalMessage = new ArrayList<>();
        for (String contentid:ids) {
            if (!StringUtils.isEmpty(contentid)) {
                Resource entity = resourceService.selectByKey(contentid);
                if (entity == null) {
                    internalMessage.add(contentid + "not exist!");
                    continue;
                }

                Response<Resource> resp = removeFromES(entity.getContentid());
                if (resp.getServerResult().getResultCode().equalsIgnoreCase(IStateCode.HTTP_200)) {
                    entity.setContentid(contentid);
                    entity.setStatus(IResourceMicroConstants.RES_STATUS_DELETE);
                    //resourceService.updateNotNull(entity);
                    super.update(entity);
                    removeList.add(entity);
                }

                resourceService.disconnectFile(contentid);
                resourceService.disconnectBookChapter(contentid);
            }
        }

        Response<Resource> resp = new Response<>(removeList);
        resp.getServerResult().setInternalMessage(StringUtils.join(internalMessage, "\n"));
        return resp;
    }

    @Override
    public void connectFile(@RequestBody Map<String, String> connectInfo) {
        resourceService.connectFile(connectInfo);
    }

    @Override
    public void disconnectFile(@PathVariable(value = "contentid") String contentid) {
        resourceService.disconnectFile(contentid);
    }

    @Override
    public void removeBook(@PathVariable(value = "bookid") String bookid) {
        QueryTree queryTree = new QueryTree();
        queryTree.addCondition(new QueryCondition("bookid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, bookid));
        queryTree.addCondition(new QueryCondition("getalldata", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
        List<ResConCatalogInfo> conInfos = resourceService.getResConInfo(queryTree);

        for(ResConCatalogInfo conInfo : conInfos)
            removeConFromES(conInfo);

        resourceService.removeBook(bookid);
    }

    @Override
    public void connectBookChapter(@RequestBody Map<String, String> connectInfo) {
        resourceService.connectBookChapter(connectInfo);

        ResConCatalogInfo conInfo = new ResConCatalogInfo();
        conInfo.setBookid(connectInfo.get("bookid"));
        conInfo.setChapterid(connectInfo.get("chapterid"));
        conInfo.setResourceno(connectInfo.get("contentid"));
        conInfo.setResourcetype(connectInfo.get("resourcetype"));
        saveConToES(conInfo);
    }

    @Override
    public void disconnectBookChapter(@PathVariable(value = "contentid") String contentid) {
        QueryTree queryTree = new QueryTree();
        queryTree.addCondition(new QueryCondition("resourceno", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, contentid));
        queryTree.addCondition(new QueryCondition("getalldata", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
        List<ResConCatalogInfo> conInfos = resourceService.getResConInfo(queryTree);

        for(ResConCatalogInfo conInfo : conInfos)
            removeConFromES(conInfo);

        resourceService.disconnectBookChapter(contentid);
    }

    @Override
    public void connectActivity(@RequestBody Map<String, String> connectInfo) {
        resourceService.connectActivity(connectInfo);
    }

    @Override
    public void disconnectActivity(@PathVariable(value = "contentid") String contentid) {
        resourceService.disconnectActivity(contentid);
    }

    @Override
    @ApiOperation(value = "资源批量状态修改", notes = "资源批量状态修改")
    @ApiImplicitParam(name = "mapList", value = "资源批量状态修改信息", required = true, dataType = "Map<String, Object>", paramType = "body")
    @SystemControllerLog(description = "资源批量状态修改")
    public Response<Resource> batchStateUpdate(@RequestBody Map<String, Object> mapList) {
        List<Resource> resp = new ArrayList<>();
        List<String> contentIds = (List<String>)mapList.get("contentids");
        String status = mapList.get("status").toString();
        for (String resourceid : contentIds) {
            Resource resource = new Resource();
            resource.setContentid(resourceid);
            resource.setStatus(status);
            resource.setLastupdatetime(new Timestamp(System.currentTimeMillis()));
            //resourceService.updateNotNull(resource);
            super.update(resource);
            resp.add(resource);
        }
        return new Response<>(resp);
    }

    @Override
    public Response<Resource> update(@RequestBody Resource entity) {
        entity.setCreatetime(null); // no need to update entity
        super.update(entity);
        Resource res = resourceService.getMapper().selectByPrimaryKey(entity.getContentid());

        Response<Resource> ret = null;
        if (res.getStatus().equalsIgnoreCase(IResourceMicroConstants.RES_STATUS_DELETE)) {
             ret = removeFromES(res.getContentid());
        }
        else
            ret = saveToES(res);

        return ret;
    }

    @Override
    public Response<String> getCatalogids(@PathVariable(value = "contentid") String contentid) {
        return new Response<>(resourceService.getCatalogids(contentid));
    }

    @Override
    public Response<String> getFileId(@PathVariable(value = "contentid") String contentid) {
        return new Response<>(resourceService.getFileid(contentid));
    }

    @Override
    public Response<String> getActivityId(@PathVariable(value = "contentid") String contentid) {
        return new Response<>(resourceService.getActivityId(contentid));
    }

    @Override
    public Response<Resource> getByQueryTree(@RequestBody QueryTree queryTree) {
        List<Resource> resources = getList(queryTree).getPageInfo().getList();
        Resource resource = null;
        if (!CollectionUtils.isEmpty(resources))
            resource = resources.get(0);
        return new Response<>(resource);
    }

    @ApiOperation(value = "名校资源设置", notes = "名校资源设置")
    @ApiImplicitParam(name = "mapList", value = "", required = true, dataType = "Map<String, String>", paramType = "body")
    @SystemControllerLog(description = "名校资源设置")
    public Response<Resource> setResourceSchool(@RequestBody Map<String, String> mapList)
    {
        resourceService.setResourceSchool(mapList);
        return new Response<>(new Resource());
    }

    private void validateUpdate(Resource resource) {
        Resource entity = resourceService.selectByKey(resource.getContentid());
        if (null == resource)
        {
            throw new ParameterException(IStateCode.PARAMETER_IS_EMPTY, "请求体为空");
        }

        resource.setCollectiontimes(null);
        resource.setClicktimes(null);
        resource.setDowntimes(null);
        resource.setViewtimes(null);
        resource.setCreatetime(entity.getCreatetime());
        resource.setLastupdatetime(new Timestamp(System.currentTimeMillis()));
    }

    private void validateAdd(Resource entity)
    {
        if (null == entity)
        {
            throw new ParameterException(IStateCode.PARAMETER_IS_EMPTY, "请求体为空");
        }

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        if (StringUtils.isEmpty(entity.getIsallowdownload()))
            entity.setIsallowdownload("1");
        if (StringUtils.isEmpty(entity.getSourceid()))
            entity.setSourceid(IResourceMicroConstants.RES_SOURCE_UPLOAD);
        entity.setIsanswer(IResourceMicroConstants.RES_SOURCE_NOT_ANSWER);
        String isanonymity = entity.getIsanonymity();
        if (isanonymity == null)
            entity.setIsanonymity(IResourceMicroConstants.RES_NOT_ANONYMITY);

        entity.setCreatetime(currentTime);
        entity.setLastupdatetime(currentTime);
    }


    @Override
    public Response<Map<String, Object>> getResourceStatus(@PathVariable(value = "mode") int mode) {
        Map<String, Object> result = new HashMap<>();
        List<Object> dataList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        Calendar calendar;
        Date begin_time = new Date(0), end_time = new Date(0);
        Date curDate = new Date(System.currentTimeMillis());
        if (mode == 1)  // last 10 days
        {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -9);
            for (int i = 0; i < 10; i++) {
                QueryTree days = new QueryTree();

                begin_time = calendar.getTime();
                end_time = calendar.getTime();

                days.getPagination().setNumPerPage(1);
                days.addCondition(new QueryCondition("isgoods", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                days.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                days.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(getList(days).getPageInfo().getTotal());
                titleList.add(DateUtils.formatDate(begin_time, "yyyy-MM-dd"));

                calendar.add(Calendar.DATE, 1);
            }
        }
        else if (mode == 2) // last 4 weeks
        {
            for (int i = 0; i < 4; i++)
            {
                QueryTree weeks = new QueryTree();

                calendar = Calendar.getInstance();
                calendar.add(calendar.DATE, (i - 3) * 7);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                begin_time = calendar.getTime();
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, (i - 3) * 7);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                end_time = calendar.getTime();

                weeks.getPagination().setNumPerPage(1);
                weeks.addCondition(new QueryCondition("isgoods", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                weeks.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                weeks.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(getList(weeks).getPageInfo().getTotal());
                titleList.add(DateUtils.formatDate(begin_time, "yyyy-MM-dd") + " ~ " + DateUtils.formatDate(end_time, "yyyy-MM-dd"));

                calendar.add(Calendar.DATE, 1);
            }
        }
        else if (mode == 3)  // last 12 months
        {
            for (int i = 0; i < 12; i++) {
                QueryTree months = new QueryTree();

                calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, 1);
                calendar.add(Calendar.MONTH, i - 10);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                end_time = calendar.getTime();
                calendar.set(calendar.DATE, 1);
                begin_time = calendar.getTime();

                months.getPagination().setNumPerPage(1);
                months.addCondition(new QueryCondition("isgoods", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                months.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                months.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(getList(months).getPageInfo().getTotal());
                titleList.add(DateUtils.formatDate(begin_time, "yyyy-MM"));
            }
        } else if (mode == 4)  // last 4 quarters
        {
            for (int i = 0; i < 4; i++) {
                QueryTree quarters = new QueryTree();

                calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, 1);
                calendar.add(Calendar.MONTH, i * 3 - 8);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                end_time = calendar.getTime();
                calendar = Calendar.getInstance();
                calendar.add(Calendar.MONTH, i * 3 - 11);
                calendar.set(calendar.DATE, 1);
                begin_time = calendar.getTime();

                quarters.addCondition(new QueryCondition("isgoods", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                quarters.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                quarters.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
                quarters.getPagination().setNumPerPage(1);
                dataList.add(getList(quarters).getPageInfo().getTotal());
                titleList.add(DateUtils.formatDate(begin_time, "yyyy-MM") + " ~ " + DateUtils.formatDate(end_time, "yyyy-MM"));

                calendar.add(Calendar.DATE, 1);
            }
        }
        else if (mode == 5) // last 5 years
        {
            for (int i = 4; i >= 0; i--)
            {
                QueryTree years = new QueryTree();

                begin_time.setYear(curDate.getYear() - i);
                begin_time.setMonth(0);
                begin_time.setDate(1);
                end_time.setYear(curDate.getYear() - i);
                end_time.setMonth(11);
                end_time.setDate(31);

                years.getPagination().setNumPerPage(1);
                years.addCondition(new QueryCondition("isgoods", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                years.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                years.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(getList(years).getPageInfo().getTotal());
                titleList.add(DateUtils.formatDate(begin_time, "yyyy"));
            }
        }

        result.put("title", titleList);
        result.put("data", dataList);

        return new Response<>(result);
    }

    @Override
    public Response<Map<String, Object>> getResourceStatistics(@RequestBody  QueryTree queryTree)
    {
        Map<String, Object> result = new HashMap<>();
        Calendar calendar;
        Date begin_time = new Date(System.currentTimeMillis()), end_time = new Date(System.currentTimeMillis());
        // total
        {
            QueryTree total = new QueryTree();
            total.getConditions().addAll(queryTree.getConditions());
            total.getPagination().setNumPerPage(1);
            result.put("total", getList(total).getPageInfo().getTotal());
        }

        // today
        {
            QueryTree today = new QueryTree();
            today.getConditions().addAll(queryTree.getConditions());
            today.getPagination().setNumPerPage(1);
            today.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            today.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

            result.put("today", getList(today).getPageInfo().getTotal());
        }

        // yesterday
        {
            QueryTree yesterday = new QueryTree();
            yesterday.getConditions().addAll(queryTree.getConditions());
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            begin_time = calendar.getTime();
            end_time = calendar.getTime();
            yesterday.getPagination().setNumPerPage(1);
            yesterday.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            yesterday.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("yesterday", getList(yesterday).getPageInfo().getTotal());
        }

        // last week
        {
            QueryTree lastweek = new QueryTree();
            lastweek.getConditions().addAll(queryTree.getConditions());
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
//            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.add(Calendar.DATE,1);
            begin_time = calendar.getTime();
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            calendar.add(Calendar.DATE,1);
            end_time = calendar.getTime();
            lastweek.getPagination().setNumPerPage(1);
            lastweek.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            lastweek.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("lastweek", getList(lastweek).getPageInfo().getTotal());
        }

        // last month
        {
            QueryTree lastmonth = new QueryTree();
            lastmonth.getConditions().addAll(queryTree.getConditions());
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            end_time = calendar.getTime();
            calendar.set(Calendar.DATE, 1);
            begin_time = calendar.getTime();
            lastmonth.getPagination().setNumPerPage(1);
            lastmonth.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            lastmonth.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("lastmonth", getList(lastmonth).getPageInfo().getTotal());
        }

        return new Response<>(result);
    }

    public Response<Integer> getRewardGoodAnswerCount(@RequestBody QueryTree queryTree) {
        return new Response<>(resourceService.getCount(queryTree));
    }

    @Override
    public Response<ExportMan> getExportManList(@RequestBody QueryTree queryTree) {
        return resourceService.getExportManListInfo(queryTree);
    }

    @Override
    public Response<String> getActivityCountInfo(@PathVariable(value = "sourceid") String sourceid) {
        return new Response<>(resourceService.getCollectedCountInfo(sourceid));
    }

    @Override
    public Response<String> getByCatalogId(@PathVariable(value = "catalogid") String catalogid) {
        return new Response<>(resourceService.getByCatalogId(catalogid));
    }

    @Override
    public Response<String> getActivitIds(@RequestBody Map<String, String> param) {
        return new Response<>(resourceService.getActivityIds(param));
    }

    @Override
    public Response<ActivityCountInfo> getActivityInfo(@PathVariable(value = "activityid") String activityid) {
        ActivityCountInfo activityCountInfo = resourceService.getActivityCounts(activityid);
        activityCountInfo.setCreator(resourceService.getGoodAnswerCreator(activityid));
        activityCountInfo.setActivityid(activityid);
        return new Response<>(activityCountInfo);
    }

    @Override
    public Response<ActivityCountInfo> validateActivityList(@RequestBody List<String> activityIds) {
        List<ActivityCountInfo> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(activityIds)) {
            for (String activityid : activityIds)
            {
                result.add(getActivityInfo(activityid).getResponseEntity());
            }
        }
        return new Response<>(result);
    }

    @Override
    public Response<Resource> getListByActivityId(@RequestBody QueryTree queryTree) {
        return resourceService.getListByActivityid(queryTree);
    }

    @Override
    public Response<Resource> getResourcesInThemes(@RequestBody QueryTree queryTree) {
        return resourceService.getThemeResources(queryTree);
    }

    @Override
    @ApiOperation(value = "获取征集资源总数", notes = "获取征集资源总数")
    @ApiImplicitParam(name = "activityid", value = "", required = true, dataType = "String", paramType = "pathvariable")
    @SystemControllerLog(description = "获取征集资源总数")
    public Response<Integer> getResourceCount(@PathVariable(value = "activityid") String activityid) {
        return resourceService.getResourceCountOfActivity(activityid);
    }

    @Override
    public Response<Resource> activityResourceList(@RequestBody QueryTree queryTree) {
        return resourceService.activityResourceList(queryTree);
    }

    @Override
    public Response<Integer> getTotalClickTimes(@RequestBody QueryTree queryTree) {
        List<Resource> allData = resourceService.getAllData(queryTree);
        int total = 0;
        for (Resource data : allData)
            total += data.getClicktimes();
        return new Response<>(total);
    }

    @Override
    public Response<Integer> getTotalViewTimes(@RequestBody QueryTree queryTree) {
        List<Resource> allData = resourceService.getAllData(queryTree);
        int total = 0;
        for (Resource data : allData)
            total += data.getViewtimes();
        return new Response<>(total);
    }

    @Override
    public Response<Integer> getTotalCollectionTimes(@RequestBody QueryTree queryTree) {
        List<Resource> allData = resourceService.getAllData(queryTree);
        int total = 0;
        for (Resource data : allData)
            total += data.getCollectiontimes();
        return new Response<>(total);
    }

    @Override
    public Response<Integer> getTotalDownTimes(@RequestBody QueryTree queryTree) {
        List<Resource> allData = resourceService.getAllData(queryTree);
        int total = 0;
        for (Resource data : allData)
            total += data.getDowntimes();
        return new Response<>(total);
    }

    @Override
    @ApiOperation(value = "获取活动参与人ID", notes = "获取活动参与人ID")
    @ApiImplicitParam(name = "activityid", value = "", required = true, dataType = "String", paramType = "pathvariable")
    @SystemControllerLog(description = "获取活动参与人ID")
    public Response<String> getJoinerIds(@PathVariable(value = "activityid") String activityid) {
        return resourceService.getJoinerIds(activityid);
    }

    @Override
    public Response<UserCountInfo> getResourceCountInfo(@RequestBody QueryTree queryTree) {
        UserCountInfo userCountInfo = resourceService.getResourceCountInfo(queryTree);
        return  new Response<>(userCountInfo);
    }

    @Override
    public Response<UserCountInfo> getCountInfoByUserId(@PathVariable(value = "userid") String userid) {
        return resourceService.getCountInfoByUserId(userid);
    }

    @Override
    public Response setStopStatusByActivityId(@PathVariable(value = "activityid") String activityid) {
        QueryTree queryTree = new QueryTree();
        queryTree.getConditions().add(new QueryCondition("activityid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, activityid));
        List<Resource> resources = resourceService.getListByActivityid(queryTree).getPageInfo().getList();
        if (!CollectionUtils.isEmpty(resources)) {
            for (Resource resource : resources) {
                Resource res = new Resource();
                res.setContentid(resource.getContentid());
                res.setStatus(IResourceMicroConstants.RES_STATUS_STOP);
                //resourceService.updateNotNull(res);
                super.update(res);
            }
        }
        return new Response();
    }

    @Override
    public Response<String> getRewardActivityStatus(@PathVariable(value = "contentid") String contentid) {
        return resourceService.getRewardActivityStatus(contentid);
    }

    @Override
    public Response<Long> getCountByCreator(@PathVariable(value = "userid") String userid) {
        return resourceService.getCountByCreator(userid);
    }

    @Override
    public Response<Integer> getCountResourceByUserid(@PathVariable(value = "userid") String userid) {
        return resourceService.getCountResourceByUserid(userid);
    }

    @Override
    public Response<String> getCreators(@RequestBody QueryTree queryTree) {
        return resourceService.getCreators(queryTree);
    }

    @Override
    public Response<Map<String, Long>> getJoinCountInfoByUser(@PathVariable(value = "userid") String userid) {
        return resourceService.getJoinCountInfoByUser(userid);
    }

    @Override
    public Response<String> getMaximumContentNo(@RequestBody QueryTree queryTree) {
        String maxNo = resourceService.getMaximumContentNo(queryTree);
        return new Response<>(maxNo);
    }

    @Override
    public Response<String> getPrefix() {
        String prefix = resourceService.getPrefix();
        return new Response<>(prefix);
    }

    @Override
    public Response<String> getResIdsByActivity(@RequestBody QueryTree queryTree) {
        List<String> idList = resourceService.getResIdsByActivity(queryTree);
        return new Response<>(idList);
    }

    @Override
    public Response<String> getIdsByQueryTree(@RequestBody QueryTree queryTree) {
        return resourceService.getIdsByQueryTree(queryTree);
    }

    @Override
    public Response<String> getActivityByResource(@PathVariable(value = "contentid") String contentid) {
        return new Response<>(resourceService.getActivityByResource(contentid));
    }

    @Override
    public Response<ResConTypeInfo> getResourceCountByCatalog(@RequestBody Map<String, Object> param) {
        return new Response<>(resourceService.getResourceCountByCatalog(param));
    }

    @Override
    public Response<ResAuditInfo> getResourceCountByAudit(@RequestBody QueryTree queryTree){
        return new Response<>(resourceService.getAuditInfo(queryTree));
    }
    //    Author : GOD 2019-2-18 Bug ID: #781
    @Override
    public Response<Long> getResourceCountByAuditSharerange(@RequestBody QueryTree queryTree){
        return new Response<>(resourceService.getAuditInfoCountBySharerange(queryTree));
    }
    //    Author : GOD 2019-2-18 Bug ID: #781


    @Override
    public Response<Audit> getAuditList(@RequestBody QueryTree queryTree) {
        try {
            eSearchService.fixQueryTree(queryTree);
        }
        catch (Exception e){
            return eSearchService.notFoundServerError();
        }

        return resourceService.getAuditList(queryTree);
    }

    @Override
    public Response<Resource> add(@RequestBody Resource entity) {
        super.add(entity);
        return saveToES(entity);
    }

    @Override
    public void validate(MethodType methodType, Object p) {
        switch (methodType)
        {
            case ADD:
                Resource entity = (Resource)p;
                validateAdd(entity);
                break;
            case DELETE:
                break;
            case GET:
                break;
            case SEARCH:
                break;
            case UPDATE:
                Resource updateEntity = (Resource)p;
                validateUpdate(updateEntity);
                break;
            case BATCHADD:
                break;
            case BATCHDELETEBYENTITY:
                break;
            case BATCHUPDATE:
                break;
            case DELETEBYENTITY:
                break;
            case GETBYENTITY:
                break;
            default:
                break;
        }
    }
}
