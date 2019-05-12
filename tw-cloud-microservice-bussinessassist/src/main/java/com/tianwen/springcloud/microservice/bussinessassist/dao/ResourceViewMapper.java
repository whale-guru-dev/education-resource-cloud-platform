package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceView;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceViewMapper extends MyMapper<ResourceView> {

    /**
     * relation of a user and a resource (that user viewed that resource)
     *
     * @param map : userid, contentid
     * @return int : 0 : not viewed, not 0 : viewed
     * @see
     */
    int getCountByUserAndResource(Map<String, Object> mapList);

    /**
     *
     * view a resource
     * @param map : userid, content information
     * @return void
     * @see
     */
    void addResourceView(Map<String, Object> mapList);

    int countView(Map<String, Object> map);

    List<ResourceView> queryViewForList(Map<String, Object> map);
}
