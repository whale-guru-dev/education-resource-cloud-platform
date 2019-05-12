package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceStar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ResourceStarMapper extends MyMapper<ResourceStar> {

    /**
     * relation of a user and a resource (that user rated that resource)
     *
     * @param map : userid, contentid
     * @return int : 0 : not rated, not 0 : rated
     * @see
     */
    int getCountByUserAndResource(Map<String, Object> mapList);

    /**
     * rate a resource
     *
     * @param map : userid, content information, rating(integer value)
     * @return void
     * @see
     */
    void addResourceStar(Map<String, Object> mapList);

    int countStar(Map<String, Object> map);

    List<ResourceStar> queryStarForList(Map<String, Object> map);
}
