package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/feedback")
public interface FeedBackMicroApi extends ICRUDMicroApi<FeedBack> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<FeedBack> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<FeedBack> insert(@RequestBody FeedBack entity);
}
