package com.tianwen.springcloud.microservice.operation.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.dao.OrderMapper;
import com.tianwen.springcloud.microservice.operation.entity.Order;
import com.tianwen.springcloud.microservice.operation.entity.OrderStatInfo;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService extends BaseService<Order> {

    @Autowired
    private OrderMapper orderMapper;

    public Map<String, Object> fixTimestampFormat(Map<String, Object> map)
    {
        {
            Object begin = map.get("begin_time");
            if (begin != null)
            {
                map.remove("begin_time");
                if (!begin.toString().isEmpty())
                {
                    Timestamp beginDate = Timestamp.valueOf(begin.toString() + " 00:00:00");
                    map.remove("begin_time");
                    map.put("begin_time", beginDate);
                }
            }
        }

        {
            Object end = map.get("end_time");
            if (end != null)
            {
                map.remove("end_time");
                if (!end.toString().isEmpty()){
                    Timestamp endDate = Timestamp.valueOf(end.toString() + " 23:59:59");
                    map.remove("end_time");
                    map.put("end_time", endDate);
                }
            }
        }

        return map;
    }

    public Response<Order> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        //        Author GOD, 2019-2-15
        Integer count = orderMapper.getListCount(map);
        //        Author GOD, 2019-2-15
        Integer start, pageSize, pageNo;

        try{
            start = pagination.getStart();
        }
        catch (NullPointerException e) {
            start = 0;
        }
        map.put("start", start);

        try{
            pageSize = pagination.getNumPerPage();
        }
        catch (NullPointerException e) {
            pageSize = 10;
        }
        map.put("numPerPage", pageSize);

        List<Order> queryList = orderMapper.getList(map);

        try{
            pageNo = pagination.getPageNo();
        }
        catch (NullPointerException e){
            pageNo = 1;
        }
        Page<Order> result = new Page<>(pageNo, pageSize);
        result.addAll(queryList);
        result.setTotal(count);

        return new Response<>(result);
    }

    public void connectOrderid2Contentid(Map<String, String> map) {
        orderMapper.connectOrderid2Contentid(map);
    }

    public int getOrderNum() {
        return orderMapper.getOrderNum();
    }

    public int getOrderPoint(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixTimestampFormat(map);

        return orderMapper.countOrderForUser(map);
    }


    public List<Order> getAlldata() {
        Map<String, Object>map = new HashMap<>();
        map.put("ispaid", "1");
        return orderMapper.getList(map);
    }

    public Response<String> getContentidsByOrder(String orderid) {
        Map<String, String> map = new HashMap<>();
        map.put("orderid", orderid);
        return new Response<String>(orderMapper.getContentidsByOrder(map));
    }

    public Response<Integer> countOrderForUser(String userid) {
        Map<String, Object>map = new HashMap<>();
        map.put("userid", userid);
        map.put("ispaid","1");
        return new Response<>(orderMapper.countOrderForUser(map));
    }

    public Response<String> getAllCreators() {
        return new Response<String>(orderMapper.getAllCreators());
    }

    public Response<Order> getAllOrders() {
        Map<String, Object>map = new HashMap<>();
        map.put("ispaid", "1");
        return new Response<>(orderMapper.getAllOrders(map));
    }

    public Integer getCount4User(Map<String, String> userinfo) {
        Integer count = orderMapper.selectCountForUser(userinfo);
        int countValue;

        if (count == null)
            countValue = 0;
        else
            countValue = count;

        return countValue;
    }

    public Response<String> getContentids4User(Map<String, Object>map) {

        return new Response<>(orderMapper.getContentids4User(map));
    }

    public Response<Order> getStatistics(QueryTree queryTree) {
        Map<String, Object> param = QueryUtils.queryTree2Map(queryTree);
        fixTimestampFormat(param);

        List<Order> result = orderMapper.getStatistics(param);
        return new Response<>(result);
    }

    public List<OrderStatInfo> getOrderStatistics(List<AreaInfo> areaList) {
        List<OrderStatInfo> result = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("areaInfos", areaList);
        List<Map<String,Object>> statInfos = orderMapper.getOrderStatistics(map);
        if (CollectionUtils.isNotEmpty(areaList)) {
            for (AreaInfo area : areaList) {
                OrderStatInfo orderStatInfo = new OrderStatInfo();
                Map<String, Object> statInfo = getStatInfoByAreaid(area.getAreaid(), statInfos);
                orderStatInfo.setInfo(statInfo);
                result.add(orderStatInfo);
            }
        }
        return result;
    }

    private Map<String, Object> getStatInfoByAreaid(String areaid, List<Map<String, Object>> statInfos) {
        if (CollectionUtils.isNotEmpty(statInfos)) {
            for (Map<String, Object> statInfo : statInfos) {
                String selectedAreaid = (String) statInfo.get("areaid");
                if (StringUtils.equals(areaid, selectedAreaid))
                    return statInfo;
            }
        }
        return null;
    }
}
