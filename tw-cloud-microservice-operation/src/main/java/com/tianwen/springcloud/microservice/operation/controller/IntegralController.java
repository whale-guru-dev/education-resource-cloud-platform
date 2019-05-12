package com.tianwen.springcloud.microservice.operation.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.api.IntegralMicroApi;
import com.tianwen.springcloud.microservice.operation.entity.Integral;
import com.tianwen.springcloud.microservice.operation.entity.IntegralInfo;
import com.tianwen.springcloud.microservice.operation.entity.ScoreExportMan;
import com.tianwen.springcloud.microservice.operation.service.IntegralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/integral")
public class IntegralController extends AbstractCRUDController<Integral> implements IntegralMicroApi  {

    @Autowired
    private IntegralService integralService;

    @Override
    public Response<Integral> getList(@RequestBody QueryTree queryTree) {
        return integralService.search(queryTree);
    }

    @Override
    public Response<ScoreExportMan> getExportManList() {
        return integralService.getExportManList();
    }

    @Override
    public Response<Integer> getUserContribution(@PathVariable(value = "userid") String userid) {
        return integralService.getUserContribution(userid);
    }

    @Override
    public Response<IntegralInfo> getTodayIntegralInfo(@RequestBody QueryTree queryTree) {
        return integralService.getTodayIntegralInfo(queryTree);
    }

    @Override
    public void validate(MethodType methodType, Object p) {
        if (methodType == MethodType.ADD)
            validateAdd((Integral) p);
    }

    @Override
    public Response<Integer> getTodayBonusScore(@RequestBody Map<String, Object> map) {
        return integralService.getTodayBonusScore(map);
    }

    @Override
    public Response<Integer> getTodayLoginTimes(@PathVariable(value = "userid") String userid) {
        return integralService.getTodayLoginTimes(userid);
    }

    private void validateAdd(Integral integral) {
        integral.setCreatetime(new Timestamp(System.currentTimeMillis()));
        integral.setLastmodifytime(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public Response<IntegralInfo> getScoreStatistics(@RequestBody List<AreaInfo> areaList) {
        return new Response<>(integralService.getScoreStatistics(areaList));
    }
}
