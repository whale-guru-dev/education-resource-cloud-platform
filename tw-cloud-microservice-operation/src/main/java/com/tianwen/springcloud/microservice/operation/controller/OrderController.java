package com.tianwen.springcloud.microservice.operation.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.api.OrderMicroApi;
import com.tianwen.springcloud.microservice.operation.entity.Order;
import com.tianwen.springcloud.microservice.operation.entity.OrderStatInfo;
import com.tianwen.springcloud.microservice.operation.service.OrderService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController extends AbstractCRUDController<Order> implements OrderMicroApi {

    @Autowired
    private OrderService orderService;

    @Override
    public Response<Order> getStatistics(@RequestBody QueryTree queryTree) {
        return orderService.getStatistics(queryTree);
    }

    @Override
    public Response<Order> getList(@RequestBody QueryTree queryTree) {
            return orderService.getList(queryTree);
    }

    @Override
    public Response<Order> insert(@RequestBody Order order) {
        add(order);
        for(String contentid: order.getContentids()) {
            Map<String, String> map = new HashMap<>();
            map.put("orderid", order.getOrderid());
            map.put("contentid", contentid);
            orderService.connectOrderid2Contentid(map);
        }
        return new Response<>();
    }

    @Override
    public Response<Map<String, Object>> getOrderStatics() {
        Map<String, Object> result = new HashMap<>();
        Calendar calendar;
        Date begin_time = new Date(System.currentTimeMillis()), end_time = new Date(System.currentTimeMillis());

        // total
        {
            int ordercount = orderService.getOrderNum();
            result.put("total",ordercount);
        }

        // today
        {
            QueryTree today = new QueryTree();
            today.addCondition(new QueryCondition("ispaid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
            today.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            today.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

            result.put("today", orderService.getOrderPoint(today));
        }

        // last week
        {
            QueryTree lastweek = new QueryTree();
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            begin_time = calendar.getTime();
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            end_time = calendar.getTime();
            lastweek.addCondition(new QueryCondition("ispaid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
            lastweek.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            lastweek.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("lastweek", orderService.getOrderPoint(lastweek));
        }

        // last month
        {
            QueryTree lastmonth = new QueryTree();
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            end_time = calendar.getTime();
            calendar.set(Calendar.DATE, 1);
            begin_time = calendar.getTime();
            lastmonth.addCondition(new QueryCondition("ispaid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
            lastmonth.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            lastmonth.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("lastmonth", orderService.getOrderPoint(lastmonth));
        }

        return new Response<>(result);
    }

    @Override
    public Response<Integer> getCount4User(@RequestBody Map<String, String> userinfo) {
        return new Response<>(orderService.getCount4User(userinfo));
    }

    @Override
    public Response<Integer> getTotalOrderScore(@RequestBody QueryTree queryTree) {

        return new Response<>(orderService.getOrderPoint(queryTree));
    }

    @Override
    public Response<Order> getAllData() {
        List<Order> orderList = (List<Order>) orderService.getAlldata();

        int i=0;
        for (Order order: orderList){
            if(i==0){
                i++;
                continue;
            }
            for(int j=0;j<i;j++){
                if(orderList.get(j).getUserid().compareTo(order.getUserid()) == 0){
                    orderList.get(j).setIntegralprice(orderList.get(j).getIntegralprice()+order.getIntegralprice());
                }
                break;
            }
            i++;
        }
        i=0;
        for (Order order: orderList){
            if (i==0){
                i++;
                continue;
            }
            for (int j=0;j<i;j++){
                if (orderList.get(j).getUserid().compareTo(order.getUserid()) == 0)
                    orderList.get(i).setIntegralprice(0);
            }
        }
        i=0;
        for (Order order: orderList){
            if(i==0){
                i++;
                continue;
            }
            if (orderList.get(i).getIntegralprice() == 0){
                orderList.remove(i);
                continue;
            }
            i++;
        }

        i=0;
        for(Order order: orderList){
            if(i==0){
                i++;
                continue;
            }
            for(int j=0;j<i;j++){
                if (order.getIntegralprice() > orderList.get(j).getIntegralprice()){
                    Order replace = orderList.get(j);
                    orderList.remove(j);
                    orderList.add(j,order);
                    orderList.remove(i);
                    orderList.add(i,replace);
                }
                i++;
            }
        }
        return new Response<>(orderList);
    }

    @Override
    public Response<String> getContentidsByOrder(@PathVariable(value = "orderid") String orderid) {
        return orderService.getContentidsByOrder(orderid);
    }

    @Override
    public Response<Integer> countOrderForUser(@PathVariable(value = "userid") String userid) {
        return orderService.countOrderForUser(userid);
    }

    @Override
    public Response<String> getAllCreators() {
        return orderService.getAllCreators();
    }

    @Override
    public Response<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public Response<String> getContentids4User(@RequestBody Map<String, Object>map) {
        return orderService.getContentids4User(map);
    }

    @Override
    public Response<OrderStatInfo> getOrderStatistics(@RequestBody List<AreaInfo> areaList) {
        return new Response<>(orderService.getOrderStatistics(areaList));
    }

    @Override
    public void validate(MethodType methodType, Object p) {}
}
