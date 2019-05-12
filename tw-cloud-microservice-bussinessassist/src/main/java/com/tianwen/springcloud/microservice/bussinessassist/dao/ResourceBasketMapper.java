package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceBasket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ResourceBasketMapper extends MyMapper<ResourceBasket> {

    /**
     * get a user's basket item count
     *
     * @param map : user information
     * @return int : count of basket items
     * @see
     */
    int count(Map<String, Object> mapList);

    /**
     * add a resource to basket
     *
     * @param map : userid, content information
     * @return void
     * @see
     */
    void addResourceBasket(Map<String, Object> mapList);

    void removeResourceBasket(Map<String, Object> mapList);

    List<ResourceBasket> queryBasketForList(Map<String, Object> param);
}
