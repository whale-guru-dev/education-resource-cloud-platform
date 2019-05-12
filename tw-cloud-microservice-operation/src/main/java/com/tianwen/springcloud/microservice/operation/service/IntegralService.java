package com.tianwen.springcloud.microservice.operation.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.dao.IntegralMapper;
import com.tianwen.springcloud.microservice.operation.dao.MemberMapper;
import com.tianwen.springcloud.microservice.operation.entity.Integral;
import com.tianwen.springcloud.microservice.operation.entity.IntegralInfo;
import com.tianwen.springcloud.microservice.operation.entity.ScoreExportMan;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class IntegralService extends BaseService<Integral> {

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private MemberMapper memberMapper;

    public Response<Integral> search(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
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

        int count = integralMapper.countIntegralList(map);
        List<Integral> queryList = integralMapper.queryIntegralForList(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        Page<Integral> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.setTotal(count);
        page.addAll(queryList);
        return new Response<>(page);
    }

    public Response<ScoreExportMan> getExportManList() {
        return new Response<>(integralMapper.getExportManList());
    }

    public Response<Integer> getUserContribution(String userid) {
        return new Response<>(integralMapper.getUserContribution(userid));
    }

    public Response<IntegralInfo> getTodayIntegralInfo(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        IntegralInfo integralInfo = new IntegralInfo();
        List<Integer> integralList = integralMapper.getTodayIntegralInfo(map);
        if (integralList != null) {
            integralInfo.setBonusIntegral((integralList.size()>0)?integralList.get(0):0);
            integralInfo.setUsedIntegral((integralList.size()>1)?integralList.get(1):0);
        } else {
            integralInfo.setBonusIntegral(0);
            integralInfo.setUsedIntegral(0);
        }
        return new Response(integralInfo);
    }

    public Response getTodayBonusScore(Map<String, Object> map) {
        Integer score = integralMapper.getTodayBonusScore(map);
        int scoreValue;
        if (score == null)
            scoreValue = 0;
        else
            scoreValue = score;
        return new Response(scoreValue);
    }

    public Response<Integer> getTodayLoginTimes(String userid) {
        Map<String, String> map = new HashMap<>();
        map.put("userid", userid);
        return new Response<>(integralMapper.getTodayLoginTimes(map));
    }

    public List<IntegralInfo> getScoreStatistics(List<AreaInfo> areaList) {
        List<IntegralInfo> integralInfoList = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        map.put("areaInfos", areaList);
        List<Map<String,Object>> statInfos = integralMapper.getStatistics(map);
        if (CollectionUtils.isNotEmpty(areaList)) {
            for (AreaInfo area : areaList) {
                IntegralInfo integralInfo = new IntegralInfo();
                Map<String, Object> statInfo = getStatInfoByAreaid(area.getAreaid(), statInfos);
                integralInfo.setInfo(statInfo);
                integralInfoList.add(integralInfo);
            }
        }
        return integralInfoList;
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
