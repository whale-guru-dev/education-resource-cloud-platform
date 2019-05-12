package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceCollection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ResourceCollectionMapper extends MyMapper<ResourceCollection> {

    /**
     * get a user's collected item count
     *
     * @param map : user information
     * @return int : count of collected items
     * @see
     */
    int getCountByUser(Map<String, Object> mapList);

    /**
     * relation of a user and a resource (that user collected that resource)
     *
     * @param map : userid, contentid
     * @return int : not 0 : collected, 0 : not collected
     * @see
     */
    int getCountByUserAndResource(Map<String, Object> mapList);

    /**
     * add a resource to collection
     *
     * @param map : userid, content information
     * @return void
     * @see
     */
    void addResourceCollect(Map<String, Object> mapList);

    /**
     * delete a resource from collection
     *
     * @param map
     * @return void : userid, contentid
     * @see
     */
    void removeResourceCollect(Map<String, Object> mapList);

    List<ResourceCollection> getCollectInfoByUser(String userId);

    int countCollection(Map<String, Object> map);

    List<ResourceCollection> queryCollectionForList(Map<String, Object> map);

    /**
     *
     * @param map
     * @return
     */
    List<String> getContentids(Map<String, Object> map);
}
