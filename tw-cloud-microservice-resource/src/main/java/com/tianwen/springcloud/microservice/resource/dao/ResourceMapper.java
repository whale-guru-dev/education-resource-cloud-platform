package com.tianwen.springcloud.microservice.resource.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.resource.entity.Audit;
import com.tianwen.springcloud.microservice.resource.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceMapper extends MyMapper<Resource> {

    /*
    * get connection between resource and catalog, especially for elasticsearch
    *
    * */
    List<ResConCatalogInfo> getBookChapterInfo(Map<String, Object> map);

    /**
     * get length of resource list, especially for pagination
     *
     * @param map : condition
     * @return Long : length of resource list
     * @see
     */
    Long countResource(Map<String, Object> map);

    /**
     * get resource information list for elastic search
     *
     * @param map : condition, order, pagination
     * @return List<Resource> : resource information list
     * @see
     */
//    List<ElasticResource> queryForESearchList(Map<String, Object> map);

    /**
     * get resource information list
     *
     * @param map : condition, order, pagination
     * @return List<Resource> : resource information list
     * @see
     */
    List<Resource> queryForList(Map<String, Object> map);

    //  Author: Han
    // description: get resources by IDs
    List<Resource> getByIds(Map<String, Object> map);

    /**
     * update resource's school information
     *
     * @param mapList oneschool, twoschool, contentid
     * @return void
     * @see
     */
    void setSchool(Map<String, String> mapList);

    /**
     * increase vote count of a resource
     *
     * @param resource to increase a vote count
     * @return void
     * @see
     */
    void updateVoteTimes(Resource resource);

    /**
     * increase collection count of a resource
     *
     * @param resource to increase a collection count
     * @return void
     * @see
     */
    void updateCollectionTimes(Resource resource);

    /**
     * increase view count of a resource
     *
     * @param resource to increase view count
     * @return void
     * @see
     */
    void updateViewTimes(Resource resource);

    /**
     * increase hot value of a resource
     *
     * @param resource to update hot value
     * @return void
     * @see
     */
    void updateHotValue(Resource resource);

    /**
     * increase view count of a resource
     *
     * @param resource to increase download count
     * @return void
     * @see
     */
    void updateDownloadTimes(Resource resource);

    /**
     * get count of resource which was created by a user
     *
     * @param userid
     * @return int : count of resource
     * @see
     */
    int getCountByCreator(String userid);

    /**
     *
     * @param connectInfo
     * @return
     */
    Integer getChapterCount(Map<String, String> connectInfo);

    /**
     *
     * @param connectInfo
     */
    void connectBookChapter(Map<String, String> connectInfo);

    /**
     * remove relation between all catalogs and a specific content
     *
     * @param contentid
     * @return void
     * @see
     */

    void disconnectBookChapter(String contentid);

    /**
     * add a relation between content and file
     *
     * @param connectInfo : resource and file information
     * @return void
     * @see
     */
    void connectFile(Map<String, String> connectInfo);

    /**
     * delete relation between all contents
     *
     * @param contentid
     * @return void
     * @see
     */
    void disconnectFile(String contentid);

    /**
     * delete relation between all contents and a specific file
     *
     * @param connectInfo
     * @return void
     * @see
     */
    void connectActivity(Map<String, String> connectInfo);

    /**
     * delete relation between all contents and a specific file
     *
     * @param contentid
     * @return void
     * @see
     */
    void disconnectActivity(String contentid);

    /**
     *
     * @param contentid
     * @return
     */
    List<String> getCatalogids(String contentid);

    /**
     *
     * @param contentid
     * @return
     */
    String getFileid(String contentid);

    /**
     *
     * @param contentid
     * @return
     */
    String getActivityId(String contentid);

    /**
     *
     * @param bookid
     */
    void removeBook(String bookid);

    /**
     *
     * @return
     */
    List<String> getCollectedCountInfo(Map<String, String> map);

    /**
     *
     * @param map
     * @return
     */
    List<ExportMan> getExportManListInfo(Map<String, Object> map);

    /**
     *
     * @param catalogid
     * @return
     */
    List<String> getByCatalogId(String catalogid);

    /**
     *
     * @param param
     * @return
     */
    List<String> getActivitIds(Map<String, String> param);

    /**
     *
     * @param activityid
     * @return
     */
    Long getActivityViewtimes(String activityid);

    /**
     *
     * @param activityid
     * @return
     */
    Long getActivityDowntimes(String activityid);

    /**
     * get joiner count of activity
     * @param activityid
     * @return
     */
    Long getJoinerCountOfActivity(String activityid);

    /**
     * get user good answered count
     * @param map
     * @return
     */
    Long getGoodAnswerCount(Map<String, String> map);

    /**
     *
     * @param map
     * @return
     */
    List<Resource> activityResourceList(Map<String, Object> map);

    /**
     *
     * @param resource
     */
    void updateRateSum(Resource resource);

    /**
     * get count of resource list which in an activity
     * @param map
     * @return
     */
    Long getCountByActivityId(Map<String, Object> map);

    /**
     * get resource list in an activity
     * @param map
     * @return
     */
    List<Resource> getListByActivityid(Map<String, Object> map);

    /**
     * get resource count of activity
     * @param activityid
     * @return
     */
    Integer getResourceCountOfActivity(String activityid);


    /**
     * get joiner ids
     * @param activityid
     * @return
     */
    List<String> getJoinerIds(Map<String, String> activityid);

    /**
     *
     * @param userid
     * @return
     */
    UserCountInfo getCountInfoByUserId(String userid);

    /**
     * get Reward Activity Status
     * @param map
     * @return
     */
    String getRewardActivityStatus(Map<String, String> map);

    /**
     *
     * @param map
     * @return
     */
    Long getActivityCountByCreator(Map<String, String> map);

    /**
     *
     * @param userid
     * @return
     */
    Integer getCountResourceByUserid(Map<String,String>userid);

    /**
     *
     * @param map
     * @return
     */
    List<String> getCreators(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    Long getJoinInfoForUser(Map<String, String> map);

    /**
     *
     * @param map
     * @return String
     * */
    String getMaximumContentNo(Map<String, Object> map);

    String getPrefix();

    List<String> getIdsByQueryTree(Map<String, Object> map);

    UserCountInfo getResourceCountInfo(Map<String, Object> map);

    /**
     * get creator id for good answered
     * @param activityid
     * @return
     */
    String getGoodAnswerCreator(String activityid);

    /***
     *
     * @Param contentid
     * @return activity ids
     */
    List<String> getActivityByResource(String contentid);

    List<ResConTypeInfo> getResourceCountByCatalog(Map<String, Object> param);

    List<ResAuditInfo> getAuditStatistics(Map<String, Object> param);

    Long countAuditInfoList(Map<String, Object> map);

    List<Audit> queryAuditInfoList(Map<String, Object> map);

    ActivityCountInfo getActivityCountInfo(String activityid);

    List<String> getActivityContentIds(Map<String, Object> map);

    Long countImportedList(Map<String, Object> param);

    List<Resource> getImportedList(Map<String, Object> param);

    Long getThemeResCnt(Map<String, Object> param);

    List<Resource> getThemeResList(Map<String, Object> param);
    //    Author : GOD 2019-2-18 Bug ID: #781
    List<Long> getAuditStatisticsCountBySharerange(Map<String, Object> param);
    //    Author : GOD 2019-2-18 Bug ID: #781
}
