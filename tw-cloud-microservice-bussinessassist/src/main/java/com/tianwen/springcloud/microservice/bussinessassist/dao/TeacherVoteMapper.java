package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.TeacherVote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TeacherVoteMapper extends MyMapper<TeacherVote> {

    /**
     * relation of a user and a resource (that user voted that resource)
     *
     * @param mapList : userid, contentid
     * @return int : 0 : not voted, not 0 : voted
     * @see
     */
    int getCountByUserAndTeacher(Map<String, Object> mapList);

    /**
     *
     * vote a resource
     * @param mapList : userid, content information
     * @return void
     * @see
     */
    void addTeacherVote(Map<String, Object> mapList);

    int countVote(Map<String, Object> map);
}
