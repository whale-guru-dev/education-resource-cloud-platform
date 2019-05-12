package com.tianwen.springcloud.microservice.question.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.question.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface QuestionMapper extends MyMapper<Question> {

    List<Question> getList(Map<String, Object> map);
    int getTotalCount();
}
