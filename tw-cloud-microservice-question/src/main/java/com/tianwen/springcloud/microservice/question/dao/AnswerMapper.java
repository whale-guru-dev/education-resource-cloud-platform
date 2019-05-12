package com.tianwen.springcloud.microservice.question.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.question.entity.Answer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AnswerMapper extends MyMapper<Answer> {

    List<Answer> getList(Map<String, Object> map);
}
