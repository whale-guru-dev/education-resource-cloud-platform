package com.tianwen.springcloud.microservice.resource.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.resource.entity.ThemeRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by gems on 2018.12.21.
 */
@Mapper
public interface ThemeResMapper extends MyMapper<ThemeRes>{
    Long getCount(Map<String, Object> param);

    List<ThemeRes> getList(Map<String, Object> param);
}
