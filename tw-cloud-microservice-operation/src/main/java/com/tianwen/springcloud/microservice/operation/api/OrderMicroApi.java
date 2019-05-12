package com.tianwen.springcloud.microservice.operation.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.entity.Order;
import com.tianwen.springcloud.microservice.operation.entity.OrderStatInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;


@FeignClient(value = "operation-service", url = "http://localhost:2223/operation-service/order")
public interface OrderMicroApi extends ICRUDMicroApi<Order> {
    @RequestMapping (value = "/getStatistics", method = RequestMethod.POST)
    public Response<Order> getStatistics(@RequestBody QueryTree queryTree);

    @RequestMapping (value = "/getList", method = RequestMethod.POST)
    public Response<Order> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Order> insert(@RequestBody Order order);

    @RequestMapping(value = "/getOrderStatics", method = RequestMethod.GET)
    public Response<Map<String, Object>> getOrderStatics();

    @RequestMapping(value = "/getCount4User", method = RequestMethod.POST)
    Response<Integer> getCount4User(@RequestBody Map<String, String> userinfo);

    @RequestMapping(value = "/getTotalOrderScore", method = RequestMethod.POST)
    public Response<Integer> getTotalOrderScore(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getAllData", method = RequestMethod.GET)
    public Response<Order> getAllData();

    @RequestMapping(value = "/getContentidsByOrder/{orderid}", method = RequestMethod.GET)
    public Response<String> getContentidsByOrder(@PathVariable(value = "orderid")String orderid);

    @RequestMapping(value = "/countOrderForUser/{userid}", method = RequestMethod.GET)
    public Response<Integer> countOrderForUser(@PathVariable(value = "userid")String userid);

    @RequestMapping(value = "/getAllCreators", method = RequestMethod.GET)
    public Response<String> getAllCreators();

    @RequestMapping(value = "/getAllOrders", method = RequestMethod.GET)
    public Response<Order> getAllOrders();

    @RequestMapping(value = "/getContentids4User", method = RequestMethod.POST)
    public Response<String> getContentids4User(@RequestBody Map<String, Object>map);

    @RequestMapping(value = "/getOrderStatistics", method = RequestMethod.POST)
    public Response<OrderStatInfo> getOrderStatistics(@RequestBody List<AreaInfo> areaList);
}
