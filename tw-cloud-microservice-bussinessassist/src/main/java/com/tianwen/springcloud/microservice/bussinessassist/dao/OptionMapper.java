package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Option;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface OptionMapper extends MyMapper<Option> {
    int countOptionTotal(List<Option> optionList);

    List<Option> getList(Map<String, Object> map);

    Integer getOptionCount();
}
