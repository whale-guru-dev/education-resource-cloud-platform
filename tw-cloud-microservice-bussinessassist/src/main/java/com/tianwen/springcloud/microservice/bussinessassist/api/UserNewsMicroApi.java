package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import com.tianwen.springcloud.microservice.bussinessassist.entity.UserNews;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/news")
public interface UserNewsMicroApi extends ICRUDMicroApi<UserNews> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<UserNews> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public Response<Integer> getCount(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public Response<UserNews> getData(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<UserNews> insert(@RequestBody UserNews entity);

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Response<UserNews> view(@RequestBody UserNews entity);
}
