package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Advert;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AdvertMapper extends MyMapper<Advert> {
    /**
     *
     * @param param
     * @return
     */
    List<Advert> queryAdvertForList(Map<String, Object> param);

    /**
     *
     * @param param
     * @return
     */
    int countAdvert(Map<String, Object> param);
}
