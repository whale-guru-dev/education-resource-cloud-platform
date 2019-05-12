package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface FeedBackMapper extends MyMapper<FeedBack> {
    int getCountByUser(Map<String, Object> param);
    int getCountByUserAndResource(Map<String, Object> param);

    int countFeedBack(Map<String, Object> map);

    List<FeedBack> queryFeedBackForList(Map<String, Object> param);
    List<FeedBack> getFeedBackInfoByAuditor(String userId);
    List<FeedBack> getFeedBackInfoByCreator(String userId);

    int getCountByAuditor(Map<String, Object> mapList);
    int getCountByAuditorAndResource(Map<String, Object> mapList);
}
