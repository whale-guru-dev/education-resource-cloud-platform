package com.tianwen.springcloud.microservice.operation.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.entity.Charge;
import com.tianwen.springcloud.microservice.operation.entity.ChargeStatInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(value = "operation-service", url = "http://localhost:2223/operation-service/charge")
public interface ChargeMicroApi extends ICRUDMicroApi<Charge>
{
    @RequestMapping (value = "/getStatistics", method = RequestMethod.POST)
    public Response<Charge> getStatistics(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/chargeToAccount", method = RequestMethod.POST)
    public Response<Charge> chargeToAccount(@RequestBody Map<String, Object> param);

    @RequestMapping(value = "/placeCharge", method = RequestMethod.POST)
    public Response<Charge> placeCharge(@RequestBody Map<String, Object> param);

    @RequestMapping(value = "/getAllData", method = RequestMethod.POST)
    public Response<Charge> getAllData(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getChargeItemList", method = RequestMethod.POST)
    public Response<Charge> getChargeItemList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getTotalChargeScore", method = RequestMethod.POST)
    public Response<Double> getTotalChargeScore(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getChargeStatus", method = RequestMethod.POST)
    public Response<Map<String, Object>> getChargeStatus(@RequestBody  QueryTree queryTree);

    @RequestMapping(value = "/getChargeStatistics/{mode}", method = RequestMethod.GET)
    public Response<Map<String, Object>> getChargeStatistics(@PathVariable(value = "mode") int mode);

    @RequestMapping(value = "/getChargeStatByArea", method = RequestMethod.POST)
    public Response<ChargeStatInfo> getChargeStatByArea(@RequestBody List<AreaInfo> areaList);
}
