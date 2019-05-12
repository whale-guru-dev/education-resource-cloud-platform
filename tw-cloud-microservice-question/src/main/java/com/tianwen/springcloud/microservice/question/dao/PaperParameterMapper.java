package com.tianwen.springcloud.microservice.question.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.question.entity.PaperParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface PaperParameterMapper extends MyMapper<PaperParam> {

    List<PaperParam> getList(Map<String, Object> map);

    int getCount(Map<String, Object> map);
}
