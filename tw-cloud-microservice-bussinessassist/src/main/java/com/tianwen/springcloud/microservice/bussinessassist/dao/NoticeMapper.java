package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NoticeMapper extends MyMapper<Notice> {

    /**
     * relation of a user and a resource (that user noticed that resource)
     *
     * @param map : userid, contentid
     * @return int : 0 : not noticed, not 0 : noticed
     * @see
     */
    int getCountByUserAndResource(Map<String, Object> mapList);


    List<Notice> queryNoticeForList(Map<String, Object> map);
}
