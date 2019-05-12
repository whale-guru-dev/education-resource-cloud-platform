package com.tianwen.springcloud.microservice.question.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.question.entity.Child;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ChildMapper extends MyMapper<Child> {

    List<Child> getList(Map<String, Object> map);
}
