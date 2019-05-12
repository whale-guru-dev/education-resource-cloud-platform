package com.tianwen.springcloud.microservice.resource.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.OrderMethod;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.resource.entity.Audit;
import com.tianwen.springcloud.microservice.resource.dao.ResourceMapper;
import com.tianwen.springcloud.microservice.resource.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ResourceService extends BaseService<Resource>{
    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private FileService fileService;

    public void fixParam(Map<String, Object> map)
    {
        {
            Object begin = map.get("begin_time");
            if (begin != null)
            {
                map.remove("begin_time");
                if (!begin.toString().isEmpty())
                {
                    Timestamp beginDate = Timestamp.valueOf(begin.toString() + " 00:00:00");
                    map.remove("begin_time");
                    map.put("begin_time", beginDate);
                }
            }
        }

        {
            Object end = map.get("end_time");
            if (end != null)
            {
                map.remove("end_time");
                if (!end.toString().isEmpty()){
                    Date endDate = Timestamp.valueOf(end.toString() + " 23:59:59");
                    map.remove("end_time");
                    map.put("end_time", endDate);
                }
            }
        }

        // search label
        {
            Object obj = map.get("searchlabel");
            if (obj != null)
            {
                map.remove("searchlabel");
                String search = obj.toString();
                if (!search.isEmpty()) {
                    search = '%' + search + '%';
                    map.put("searchlabel", search);
                }
            }
        }
        // multiple catalog ids
        {
            Object obj = map.get("catalogids");
            List<String> catData = new ArrayList<String>();
            String catids = null;

            if (obj != null)
                catids = obj.toString();

            if (catids == null || catids.isEmpty() || catids.equals("[]"))
                map.remove("catalogids");
            else {
                String ids = catids;
                if (catids.indexOf(',') != -1)
                    ids = catids.substring(1, catids.length() - 1);
                String[] catid = ids.split(", ");

                for (String oneid : catid) {
                    if (oneid.isEmpty()) continue;
                    catData.add(oneid);
                }

                map.remove("catalogids");
                catData.add("");
                catData.add("");
                map.put("catalogids", catData);
            }
        }

        {
            Object contypes = map.get("contenttype");
            if (contypes != null)
            {
                List<String> typeList;
                try
                {
                    typeList = (List<String>)contypes;
                    map.remove("contenttype");
                    map.put("contenttypes", contypes);
                }
                catch (Exception e)
                {
                }
            }
        }

        if (map.get("schoolsectionid") != null)
            map.put("schoolsectionid", map.get("schoolsectionid").toString());
        if (map.get("subjectid") != null)
            map.put("subjectid", map.get("subjectid").toString());
        if (map.get("gradeid") != null)
            map.put("gradeid", map.get("gradeid").toString());
        if (map.get("editiontypeid") != null)
            map.put("editiontypeid", map.get("editiontypeid").toString());
        if (map.get("bookmodelid") != null)
            map.put("bookmodelid", map.get("bookmodelid").toString());
    }

    @SystemServiceLog(description = "")
    public Response<Resource> search(QueryTree queryTree)
    {
        Pagination pagination = queryTree.getPagination();
        List<OrderMethod> ordermethods = queryTree.getOrderMethods();
        String orderBy;

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixParam(map);

        if (ordermethods != null) {
            orderBy = ordermethods.get(0).getField();
            if (orderBy.equals("推荐排序"))
                map.put("orderByVotes", "");
            else if (orderBy.equals("最新上传"))
                map.put("orderByLastUpdate", "");
            else if (orderBy.equals("最受欢迎"))
                map.put("orderByRating", "");
            else if (orderBy.equals("名校精品"))
                map.put("orderBySchool", "");
            else if (orderBy.equals("最热资源"))
                map.put("orderByHot", "");
            else if (orderBy.equals("orderBycreatetime"))
                map.put("orderBycreatetime", "");
            else if (orderBy.equals("orderbyanswer"))
                map.put("orderbyanswer", "");
        }

        Long count = resourceMapper.countResource(map);

        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());

        List<Resource> queryList = resourceMapper.queryForList(map);

        if (!CollectionUtils.isEmpty(queryList)) {
            for (Resource resource : queryList) {
                FileInfo fileInfo = fileService.getByContentid(resource.getContentid());
                resource.setFileInfo(fileInfo);
            }
        }

        Page<Resource> result = new Page<Resource>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);

        return new Response<>(result);
    }

    /**
     * @author han
     * retrieve resources by IDs
     */
    public List<Resource> getResourcesByIds(List<String> ids)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("contentids", ids);
        return resourceMapper.getByIds(map);
    }

//    @SystemServiceLog(description = "")
//    public List<ElasticResource> getESearchResult(QueryTree queryTree)
//    {
//        Pagination pagination = queryTree.getPagination();
//
//        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
//        map.put("start", pagination.getStart());
//        map.put("numPerPage", pagination.getNumPerPage());
//
//        return resourceMapper.queryForESearchList(map);
//    }

    public Response<Resource> activityResourceList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        List<Resource> queryList = resourceMapper.activityResourceList(map), pageList;
        int count = queryList.size();
        int start = Integer.parseInt(map.get("start").toString()), pageSize = pagination.getNumPerPage().intValue();
        if (start > queryList.size())
            pageList = new ArrayList<Resource>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Resource> result = new Page<Resource>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(pageList);
        result.setTotal(count);

        return new Response<Resource>(result);
    }

    public  Response<ExportMan>  getExportManListInfo(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        List<ExportMan> queryList = resourceMapper.getExportManListInfo(map), pageList;
        int count = queryList.size();
        int start = Integer.parseInt(map.get("start").toString()), pageSize = pagination.getNumPerPage().intValue();
        if (start > queryList.size())
            pageList = new ArrayList<>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<ExportMan> result = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(pageList);
        result.setTotal(count);

        return new Response<>(result);
    }


    public Response<Resource> getListByActivityid(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixParam(map);
        Long count = resourceMapper.getCountByActivityId(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<Resource> queryList = resourceMapper.getListByActivityid(map);
        Page<Resource> result = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<>(resourceMapper.getListByActivityid(map));
    }

    public List<String> getCollectedCountInfo(String sourceid) {
        Map<String, String> map = new HashMap<>();
        map.put("sourceid", sourceid);
        return resourceMapper.getCollectedCountInfo(map);
    }

    public void disconnectFile(String contentid) {
        resourceMapper.disconnectFile(contentid);
    }

    public void disconnectBookChapter(String contentid) {
        resourceMapper.disconnectBookChapter(contentid);
    }

    public void setResourceSchool(Map<String,String> mapList) {
        resourceMapper.setSchool(mapList);
    }

    public void connectFile(Map<String, String> connectInfo) {
        resourceMapper.connectFile(connectInfo);
    }

    public void connectBookChapter(Map<String, String> connectInfo) {
        Integer count = resourceMapper.getChapterCount(connectInfo);
        if (count == 0)
            resourceMapper.connectBookChapter(connectInfo);
    }

    public void connectActivity(Map<String, String> connectInfo) {
        resourceMapper.connectActivity(connectInfo);
    }

    public List<String> getCatalogids(String contentid) {
        return resourceMapper.getCatalogids(contentid);
    }

    public String getFileid(String contentid) {
        return resourceMapper.getFileid(contentid);
    }

    public String getActivityId(String contentid) {
        return resourceMapper.getActivityId(contentid);
    }

    public void removeBook(String bookid) {
        resourceMapper.removeBook(bookid);
    }

    public void updateViewTimes(Resource resource) {
        resourceMapper.updateViewTimes(resource);
    }

    public void updateHotValue(Resource resource) {
        resourceMapper.updateHotValue(resource);
    }

    public void updateCollectionTimes(Resource resource) {
        resourceMapper.updateCollectionTimes(resource);
    }

    public void updateDownloadTimes(Resource resource) {
        resourceMapper.updateDownloadTimes(resource);
    }

    public void updateClickTimes(Resource resource) {
        resourceMapper.updateVoteTimes(resource);
    }

    public void disconnectActivity(String contentid) {
        resourceMapper.disconnectActivity(contentid);
    }

    public int getCount(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixParam(map);
        long count = resourceMapper.countResource(map);
        return (int)count;
    }

    public List<Resource> getAllData(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixParam(map);

        List<Resource> queryList = resourceMapper.queryForList(map);

        return queryList;
    }

    public List<String> getByCatalogId(String catalogid) {
        return resourceMapper.getByCatalogId(catalogid);
    }

    public List<String> getActivityIds(Map<String, String> param) {
        List<String> result = resourceMapper.getActivitIds(param);
        if (result == null)
            result = new ArrayList<>();
        result.add("");
        result.add("");
        return result;
    }

    public Long getActivityViewtimes(String activityid) {
        return resourceMapper.getActivityViewtimes(activityid);
    }

    public Long getActivityDowntimes(String activityid) {
        return resourceMapper.getActivityDowntimes(activityid);
    }

    public Long getJoinerCount(String activityid) {
        return resourceMapper.getJoinerCountOfActivity(activityid);
    }

    public void updateRateSum(Resource resource) {
        resourceMapper.updateRateSum(resource);
    }

    public Response<Integer> getResourceCountOfActivity(String activityid) {
        return new Response<>(resourceMapper.getResourceCountOfActivity(activityid));
    }

    public Response<String> getJoinerIds(String activityid) {
        Map<String, String> map = new HashMap<>();
        map.put("activityid", activityid);

        List<String> joinerids = resourceMapper.getJoinerIds(map);
        if (joinerids != null) {
            joinerids.add("");
            joinerids.add("");
        }
        return new Response<>(joinerids);
    }

    public Response<UserCountInfo> getCountInfoByUserId(String userid) {
        return new Response<>(resourceMapper.getCountInfoByUserId(userid));
    }

    public Response<String> getRewardActivityStatus(String contentid) {
        Map<String, String> map = new HashMap<>();
        map.put("contentid", contentid);
        return new Response(resourceMapper.getRewardActivityStatus(map));
    }

    public Response<Long> getCountByCreator(String userid) {
        Map<String, String> map = new HashMap<>();
        map.put("creator", userid);
        return new Response<>(resourceMapper.getActivityCountByCreator(map));
    }

    public Response<Integer> getCountResourceByUserid(String userid) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        return new Response<Integer>(resourceMapper.getCountResourceByUserid(map));
    }

    public Response<String> getCreators(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        List<String> creators = resourceMapper.getCreators(map);
        if (creators != null) {
            creators.add("");
            creators.add("");
        }
        return new Response<>(creators);
    }

    public Response<Map<String, Long>> getJoinCountInfoByUser(String userid) {
        Map<String, Long> result = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        map.put("activitytype", "1");
        Long collectioncount = resourceMapper.getJoinInfoForUser(map) ;
        if (collectioncount == null)
            collectioncount = Long.valueOf(0);
        result.put("collectionjoincount", collectioncount);
        map.remove("activitytype");
        map.put("activitytype", "2");
        Long rewardjoincount = resourceMapper.getJoinInfoForUser(map);
        result.put("rewardjoincount", rewardjoincount==null?Long.valueOf(0):rewardjoincount);
        map.remove("activitytype");
        map.put("activitytype", "3");
        Long estimatejoincount = resourceMapper.getJoinInfoForUser(map);
        result.put("estimatejoincount", estimatejoincount==null?Long.valueOf(0):estimatejoincount);
        result.put("goodansweredcount", resourceMapper.getGoodAnswerCount(map));
        return new Response<>(result);
    }

    public String getMaximumContentNo(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        return resourceMapper.getMaximumContentNo(map);
    }

    public String getPrefix() {
        return resourceMapper.getPrefix();
    }

    public Long getAnswerCount(String activityid) {
        Map<String, Object> map = new HashMap<>();
        map.put("activityid", activityid);
        return resourceMapper.getCountByActivityId(map);
    }

    public Response<String> getIdsByQueryTree(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        Pagination pagination = queryTree.getPagination();
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());

        List<String> queryList = resourceMapper.getIdsByQueryTree(map);
        if (queryList == null)
            return new Response();

        queryList.add("");
        //        Author GOD, 2019-2-15
        queryList.add("");
        //        Author GOD, 2019-2-15
        return new Response<String>(queryList);
    }
    public UserCountInfo getResourceCountInfo(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixParam(map);
        return resourceMapper.getResourceCountInfo(map);
    }

    public String getGoodAnswerCreator(String activityid) {
        return resourceMapper.getGoodAnswerCreator(activityid);
    }

    public List<String> getActivityByResource(String contentid) {
        return resourceMapper.getActivityByResource(contentid);
    }

    public List<ResConTypeInfo> getResourceCountByCatalog(Map<String, Object> param)
    {
        return resourceMapper.getResourceCountByCatalog(param);
    }

    public List<ResConCatalogInfo> getResConInfo(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        return resourceMapper.getBookChapterInfo(map);
    }

    public List<ResAuditInfo> getAuditInfo(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
//        <!--Author : GOD-->
//    <!--Date Start: 2019-2-4 2:00 PM-->
//    <!--Reason : Bug ID 559-->
        fixParam(map);
//            <!--Author : GOD-->
//    <!--Date End: 2019-2-4 4:00 PM-->
//    <!--Reason : Bug ID 559-->
        if (map.get("businesstype") != null)
        {
            List<String> sourceids = new ArrayList<>();
            try {
                List<String> ids = (List<String>)map.get("businesstype");
                sourceids.addAll(ids);
            }
            catch (Exception e){
                sourceids.add(map.get("businesstype").toString());
            }
            if (sourceids.size() == 1 && sourceids.get(0).isEmpty())
                map.remove("businesstype");
            else
            {
                sourceids.add("");
                map.put("businesstype", sourceids);
            }
        }

        return resourceMapper.getAuditStatistics(map);
    }
    //    Author : GOD 2019-2-18 Bug ID: #781
    public List<Long> getAuditInfoCountBySharerange(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixParam(map);
        if (map.get("businesstype") != null)
        {
            List<String> sourceids = new ArrayList<>();
            try {
                List<String> ids = (List<String>)map.get("businesstype");
                sourceids.addAll(ids);
            }
            catch (Exception e){
                sourceids.add(map.get("businesstype").toString());
            }
            if (sourceids.size() == 1 && sourceids.get(0).isEmpty())
                map.remove("businesstype");
            else
            {
                sourceids.add("");
                map.put("businesstype", sourceids);
            }
        }

        String[] shareranges = {"1", "2", "3", "4", "5"};
        List<Long> countBySharerange = new ArrayList<>();
        for (String sharerange : shareranges) {
            map.put("sharerange", sharerange);
            List<Long> counts = resourceMapper.getAuditStatisticsCountBySharerange(map);
            Long sum = 0L;
            for(Long count : counts) {
                sum = sum + count;
            }
            countBySharerange.add(sum);
        }

        return countBySharerange;
    }
    //    Author : GOD 2019-2-18 Bug ID: #781
    public Response<Audit> getAuditList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixParam(map);

        Long count = resourceMapper.countAuditInfoList(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());

        List<OrderMethod> ordermethods = queryTree.getOrderMethods();
        if (ordermethods != null && !org.apache.commons.collections.CollectionUtils.isEmpty(ordermethods)) {
            OrderMethod orderMethod = ordermethods.get(0);
            String field = orderMethod.getField();
            String value = orderMethod.getMethod().getValue();
            map.put(field+value, "");
        }
        map.remove("orderMethods");

        List<Audit> auditList = resourceMapper.queryAuditInfoList(map);

        Page<Audit> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.addAll(auditList);
        page.setTotal(count);

        return new Response<>(page);
    }

    public ActivityCountInfo getActivityCounts(String activityid) {
        return resourceMapper.getActivityCountInfo(activityid);
    }

    public Response<Resource> getImportedList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> param = QueryUtils.queryTree2Map(queryTree);
        fixParam(param);
        long count = resourceMapper.countImportedList(param);

        int numPerPage = pagination.getNumPerPage(), start = pagination.getStart();
        param.put("numPerPage", numPerPage);
        param.put("start", start);

        List<Resource> resources = resourceMapper.getImportedList(param);
        if (resources != null){
            for (Resource resource : resources)
            {
                resource.setFileInfo(fileService.getByContentid(resource.getContentid()));
                //Author : GOD 2019-2-22 Reason: BatchFileUpload
                resource.setCatalogids(getCatalogids(resource.getContentid()));
                //Author : GOD 2019-2-22 Reason: BatchFileUpload
            }
        }
        Page<Resource> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.addAll(resources);
        page.setTotal(count);

        return new Response<>(page);
    }

    public List<String> getResIdsByActivity(QueryTree queryTree) {
        Map<String, Object> param = QueryUtils.queryTree2Map(queryTree);
        Pagination pagination = queryTree.getPagination();
        param.put("start", pagination.getStart());
        param.put("numPerPage", pagination.getNumPerPage());
        List<String> idList = resourceMapper.getActivityContentIds(param);
        idList.add("");
        idList.add("");
        return idList;
    }

    public Response<Resource> getThemeResources(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> param = QueryUtils.queryTree2Map(queryTree);
        param.put("start", pagination.getStart());
        param.put("numPerPage", pagination.getNumPerPage());
        Long count = resourceMapper.getThemeResCnt(param);
        List<Resource> resList = resourceMapper.getThemeResList(param);

        Page<Resource> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.addAll(resList);
        page.setTotal(count);
        return new Response<>(page);
    }
}