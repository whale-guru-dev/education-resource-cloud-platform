package com.tianwen.springcloud.microservice.operation.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.entity.Integral;
import com.tianwen.springcloud.microservice.operation.entity.IntegralInfo;
import com.tianwen.springcloud.microservice.operation.entity.ScoreExportMan;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;


@FeignClient(value = "integral-service", url = "http://localhost:2223/operation-service/integral")
public interface IntegralMicroApi extends ICRUDMicroApi<Integral>
{
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Integral> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getExportManList", method = RequestMethod.GET)
    public Response<ScoreExportMan> getExportManList();

    @RequestMapping(value = "/getUserContribution/{userid}", method = RequestMethod.GET)
    public Response<Integer> getUserContribution(@PathVariable(value = "userid") String userid);

    @RequestMapping(value = "/getTodayIntegralInfo", method = RequestMethod.POST)
    Response<IntegralInfo> getTodayIntegralInfo(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getTodayBonusScore", method = RequestMethod.POST)
    Response<Integer> getTodayBonusScore(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/getTodayLoginTimes/{userid}", method = RequestMethod.GET)
    Response<Integer> getTodayLoginTimes(@PathVariable(value = "userid") String userid);

    @RequestMapping(value = "/getScoreStatistics", method = RequestMethod.POST)
    Response<IntegralInfo> getScoreStatistics(@RequestBody List<AreaInfo> areaList);
}
