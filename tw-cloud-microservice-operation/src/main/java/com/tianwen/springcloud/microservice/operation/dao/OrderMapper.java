package com.tianwen.springcloud.microservice.operation.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.operation.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper extends MyMapper<Order> {

    List<Order> getList(Map<String, Object> map);

    void connectOrderid2Contentid(Map<String, String> map);

    int getOrderNum();

    List<Order> queryOrderForList(Map<String, Object> map);

    Integer getListCount(Map<String, Object> map);

    String isGoodinOrder(Map<String, Object>map);

    List<String> getContentidsByOrder(Map<String, String> map);

    Integer countOrderForUser(Map<String, Object> map);

    List<String> getAllCreators();

    List<Order> getAllOrders(Map<String, Object>map);

    /**
     * order count
     * @param userinfo
     * @return
     */
    Integer selectCountForUser(Map<String, String> userinfo);

    List<String> getContentids4User(Map<String, Object>map);

    List<Order> getStatistics(Map<String, Object> param);

    /**
     *
     * @param areaList
     * @return
     */
    List<Map<String,Object>> getOrderStatistics(Map<String, Object> areaList);
}
