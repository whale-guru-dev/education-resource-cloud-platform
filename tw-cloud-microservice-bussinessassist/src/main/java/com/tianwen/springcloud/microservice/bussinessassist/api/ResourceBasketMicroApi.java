package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceBasket;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceVote;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/basket")
public interface ResourceBasketMicroApi extends ICRUDMicroApi<ResourceBasket> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<ResourceBasket> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<ResourceBasket> insert(@RequestBody ResourceBasket entity);
    
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Response<ResourceBasket> remove(@RequestBody Map<String, Object> param);
}
