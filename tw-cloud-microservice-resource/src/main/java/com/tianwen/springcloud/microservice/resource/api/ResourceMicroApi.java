package com.tianwen.springcloud.microservice.resource.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.resource.entity.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(value = "resource-service", url = "http://localhost:2224/resource-service/resource")
public interface ResourceMicroApi extends ICRUDMicroApi<Resource> {
    @RequestMapping(value = "/getResConInfo", method = RequestMethod.POST)
    public Response<ResConCatalogInfo> getResConInfo(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/makeESDb", method = RequestMethod.GET)
    public Response<Resource> makeESDb();

    @RequestMapping(value = "/batchSaveToES", method = RequestMethod.POST)
    public Response<Resource> batchSaveToES(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/saveToES", method = RequestMethod.POST)
    public Response<Resource> saveToES(@RequestBody Resource entity);

    @RequestMapping(value = "/deleteFromES", method = RequestMethod.POST)
    public Response<Resource> removeFromES(@RequestBody Resource entity);

    @RequestMapping(value = "/deleteFromES/{id}", method = RequestMethod.GET)
    public Response<Resource> removeFromES(@PathVariable(value = "id") String id);

    @RequestMapping(value = "/makeConESDb", method = RequestMethod.GET)
    public Response<ResConCatalogInfo> makeConESDb();

    @RequestMapping(value = "/batchSaveConToES", method = RequestMethod.POST)
    public Response<ResConCatalogInfo> batchSaveConToES(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/saveConToES", method = RequestMethod.POST)
    public Response<ResConCatalogInfo> saveConToES(@RequestBody ResConCatalogInfo conInfo);

    @RequestMapping(value = "/deleteConFromES", method = RequestMethod.POST)
    public Response<ResConCatalogInfo> removeConFromES(@RequestBody ResConCatalogInfo conInfo);

    @RequestMapping(value = "/get/{contentid}", method = RequestMethod.GET)
    Response<Resource> get(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/updateHot", method = RequestMethod.POST)
    public void updateHot(@RequestBody Resource resource);

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public void view(@RequestBody Resource resource);

    @RequestMapping(value = "/click", method = RequestMethod.POST)
    public void click(@RequestBody Resource resource);

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void download(@RequestBody Resource resource);

    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public void collection(@RequestBody Resource resource);

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public void rate(@RequestBody Resource resource);

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Resource> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getImportedList", method = RequestMethod.POST)
    public Response<Resource> getImportedList(@RequestBody QueryTree queryTree);

//    @RequestMapping(value = "/getESearchList", method = RequestMethod.POST)
//    public Response<ElasticResource> getESearchList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getAllData", method = RequestMethod.POST)
    public Response<Resource> getAllData(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public Response<Long> getCount(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public Response<Resource> batchDelete(@RequestBody List<String>ids);

    @RequestMapping(value = "/connectFile", method = RequestMethod.POST)
    public void connectFile(@RequestBody Map<String, String> connectInfo);

    @RequestMapping(value = "/disconnectFile/{contentid}", method = RequestMethod.GET)
    public void disconnectFile(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/removeBook/{bookid}", method = RequestMethod.GET)
    public void removeBook(@PathVariable(value = "bookid") String bookid);

    @RequestMapping(value = "/connectBookChapter", method = RequestMethod.POST)
    public void connectBookChapter(@RequestBody Map<String, String> connectInfo);

    @RequestMapping(value = "/disconnectBookChapter/{contentid}", method = RequestMethod.GET)
    public void disconnectBookChapter(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/connectActivity", method = RequestMethod.POST)
    public void connectActivity(@RequestBody Map<String, String> connectInfo);

    @RequestMapping(value = "/disconnectActivity/{contentid}", method = RequestMethod.GET)
    public void disconnectActivity(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/batchStateUpdate", method = RequestMethod.POST)
    Response<Resource> batchStateUpdate(@RequestBody Map<String, Object> mapList);

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    Response<Resource> update(@RequestBody Resource entity);

    @RequestMapping(value = "/getCatalogids/{contentid}", method = RequestMethod.GET)
    Response<String> getCatalogids(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getFileId/{contentid}", method = RequestMethod.GET)
    Response<String> getFileId(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getActivityId/{contentid}", method = RequestMethod.GET)
    Response<String> getActivityId(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getByQueryTree", method = RequestMethod.POST)
    Response<Resource> getByQueryTree(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getResourceStatus/{mode}", method = RequestMethod.GET)
    Response<Map<String, Object>> getResourceStatus(@PathVariable(value = "mode") int mode);

    @RequestMapping(value = "/getResourceStatistics", method = RequestMethod.POST)
    Response<Map<String, Object>> getResourceStatistics(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getExportManList", method = RequestMethod.POST)
    Response<ExportMan> getExportManList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getActivityCountInfo/{sourceid}", method = RequestMethod.GET)
    Response<String> getActivityCountInfo(@PathVariable(value = "sourceid") String sourceid);

    @RequestMapping(value = "/getByCatalogId/{catalogid}", method = RequestMethod.GET)
    Response<String> getByCatalogId(@PathVariable(value = "catalogid") String catalogid);

    @RequestMapping(value = "/getActivitIds", method = RequestMethod.POST)
    Response<String> getActivitIds(@RequestBody Map<String, String> param);

    @RequestMapping(value = "/getActivityInfo/{activityid}", method = RequestMethod.GET)
    Response<ActivityCountInfo> getActivityInfo(@PathVariable(value = "activityid") String activityid);

    @RequestMapping(value = "/validateActivityList", method = RequestMethod.POST)
    Response<ActivityCountInfo> validateActivityList(@RequestBody List<String> activityids);

    @RequestMapping(value = "/getListByActivityId", method = RequestMethod.POST)
    Response<Resource> getListByActivityId(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getResourcesInThemes", method = RequestMethod.POST)
    Response<Resource> getResourcesInThemes(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getResourceCount/{activityid}", method = RequestMethod.GET)
    Response<Integer> getResourceCount(@PathVariable(value = "activityid") String activityid);

    @RequestMapping(value = "/activityResourceList", method = RequestMethod.POST)
    Response<Resource> activityResourceList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getTotalClickTimes", method = RequestMethod.POST)
    Response<Integer> getTotalClickTimes(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getTotalViewTimes", method = RequestMethod.POST)
    Response<Integer> getTotalViewTimes(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getTotalCollectionTimes", method = RequestMethod.POST)
    Response<Integer> getTotalCollectionTimes(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getTotalDownTimes", method = RequestMethod.POST)
    Response<Integer> getTotalDownTimes(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getJoinerIds/{activityid}", method = RequestMethod.GET)
    Response<String> getJoinerIds(@PathVariable(value = "activityid") String activityid);

    @RequestMapping(value = "/getResourceCountInfo", method = RequestMethod.POST)
    Response<UserCountInfo> getResourceCountInfo(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getCountInfoByUserId/{userid}", method = RequestMethod.GET)
    Response<UserCountInfo> getCountInfoByUserId(@PathVariable(value = "userid") String userid);

    @RequestMapping(value = "/setStopStatusByActivityId/{activityid}", method = RequestMethod.GET)
    Response setStopStatusByActivityId(@PathVariable(value = "activityid") String activityid);

    @RequestMapping(value = "/getRewardActivityStatus/{contentid}", method = RequestMethod.POST)
    Response<String> getRewardActivityStatus(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getCountByCreator/{userid}", method = RequestMethod.GET)
    Response<Long> getCountByCreator(@PathVariable(value = "userid") String userid);

    @RequestMapping(value = "/getCountResourceByUserid/{userid}", method = RequestMethod.GET)
    Response<Integer> getCountResourceByUserid(@PathVariable(value = "userid")String userid);

    @RequestMapping(value = "/getCreators", method = RequestMethod.POST)
    Response<String> getCreators(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getJoinCountInfoByUser/{userid}", method = RequestMethod.GET)
    Response<Map<String, Long>> getJoinCountInfoByUser(@PathVariable(value = "userid") String userid);

    @RequestMapping(value = "/getMaximumContentNo", method = RequestMethod.POST)
    Response<String>  getMaximumContentNo(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getPrefix", method = RequestMethod.GET)
    Response<String>  getPrefix();

    @RequestMapping(value = "/getResIdsByActivity", method = RequestMethod.POST)
    Response<String> getResIdsByActivity(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getIdsByQueryTree", method = RequestMethod.POST)
    Response<String> getIdsByQueryTree(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getActivityByResource/{contentid}", method = RequestMethod.GET)
    Response<String> getActivityByResource(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getResourceCountByCatalog", method = RequestMethod.POST)
    Response<ResConTypeInfo> getResourceCountByCatalog(@RequestBody Map<String, Object> param);

    @RequestMapping(value = "/getResourceCountByAudit", method = RequestMethod.POST)
    Response<ResAuditInfo> getResourceCountByAudit(@RequestBody QueryTree queryTree);

    //    Author : GOD 2019-2-18 Bug ID: #781
    @RequestMapping(value = "/getResourceCountByAuditSharerange", method = RequestMethod.POST)
    Response<Long> getResourceCountByAuditSharerange(@RequestBody QueryTree queryTree);
    //    Author : GOD 2019-2-18 Bug ID: #781

    @RequestMapping(value = "/getAuditList", method = RequestMethod.POST)
    Response<Audit> getAuditList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    Response<Resource> add(@RequestBody Resource entity);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    Response<Resource> delete(@PathVariable(value = "id") String id);
}
