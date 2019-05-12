package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceFlags;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceVote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceVoteMapper extends MyMapper<ResourceVote> {

    /**
     * relation of a user and a resource (that user voted that resource)
     *
     * @param map : userid, contentid
     * @return int : 0 : not voted, not 0 : voted
     * @see
     */
    int getCountByUserAndResource(Map<String, Object> mapList);

    /**
     *
     * vote a resource
     * @param map : userid, content information
     * @return void
     * @see
     */
    void addResourceVote(Map<String, Object> mapList);

    int countVote(Map<String, Object> map);

    List<ResourceVote> queryVoteForList(Map<String, Object> map);

    Integer getHotValue(Map<String, String> map);

    ResourceFlags getResourceFlags(Map<String, String> map);
}
