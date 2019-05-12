package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Action;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ActionMapper extends MyMapper<Action> {

    List<Action> getList(Map<String, Object> map);
}
