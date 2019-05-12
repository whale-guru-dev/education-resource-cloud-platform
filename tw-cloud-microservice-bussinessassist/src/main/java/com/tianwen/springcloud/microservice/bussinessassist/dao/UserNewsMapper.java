package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import com.tianwen.springcloud.microservice.bussinessassist.entity.UserNews;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserNewsMapper extends MyMapper<UserNews> {

    List<UserNews> queryNewsForList(Map<String, Object> map);

    Integer countNews(Map<String, Object> map);
}
