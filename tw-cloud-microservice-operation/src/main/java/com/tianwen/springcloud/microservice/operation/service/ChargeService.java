package com.tianwen.springcloud.microservice.operation.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.dao.ChargeMapper;
import com.tianwen.springcloud.microservice.operation.entity.Charge;
import com.tianwen.springcloud.microservice.operation.entity.ChargeStatInfo;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ChargeService extends BaseService<Charge> {

    @Autowired
    private ChargeMapper chargeMapper;

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
                    Date endDate = Timestamp.valueOf(end.toString() + " 23:59:59");
                    map.remove("end_time");
                    map.put("end_time", endDate);
                }
            }
        }

        return map;
    }

    public Response<Charge> search(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixTimestampFormat(map);
        Long count = chargeMapper.countChargeList(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<Charge> queryList = chargeMapper.queryChargeForList(map);
        Page<Charge> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.setTotal(count);
        page.addAll(queryList);

        return new Response<>(page);
    }

    public double getChargePoint(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixTimestampFormat(map);

        List<Charge> queryList = chargeMapper.queryChargeForList(map);

        double chargeSum = 0;
        for(Charge charge : queryList)
            chargeSum += charge.getCost();/* * charge.getExchange();*/
        return chargeSum;
    }

    public List<Charge> getAllData(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixTimestampFormat(map);

        List<Charge> queryList = chargeMapper.queryChargeForList(map);

        return queryList;
    }

    public List<String> getChargeUsers() {
        return chargeMapper.getChargeUsers();
    }

    public Response<Charge> getStatistics(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixTimestampFormat(map);
        return chargeMapper.getStatistics(map);
    }

    public List<ChargeStatInfo> getAreaStatistics(List<AreaInfo> areaList) {
        List<ChargeStatInfo> result = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("areaInfos", areaList);
        List<Map<String, Object>> statInfos = chargeMapper.getAreaStatistics(map);

        if (CollectionUtils.isNotEmpty(areaList)) {
            for (AreaInfo area : areaList) {
                ChargeStatInfo chargeStatInfo = new ChargeStatInfo();
                Map<String, Object> statInfo = getStatInfoByAreaid(area.getAreaid(), statInfos);
                chargeStatInfo.setInfo(statInfo);

                int avg = 0;
                if (chargeStatInfo.getTotal() != 0 && CollectionUtils.isNotEmpty(area.getUserids()))
                    avg = chargeStatInfo.getTotal()/area.getUserids().size();
                chargeStatInfo.setAverage(avg);
                result.add(chargeStatInfo);
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
