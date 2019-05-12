package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Option;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/option")
public interface OptionMicroApi extends ICRUDMicroApi<Option> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Option> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getOptionCount", method = RequestMethod.GET)
    public Response<Integer> getOptionCount();

    @RequestMapping(value = "/deleteSomeOption", method = RequestMethod.GET)
    public Response<Option> deleteSomeOption();
}
