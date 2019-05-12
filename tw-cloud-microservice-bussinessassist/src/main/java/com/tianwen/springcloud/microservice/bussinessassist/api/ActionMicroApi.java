package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Action;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/action")
public interface ActionMicroApi extends ICRUDMicroApi<Action> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Action> getList(QueryTree queryTree);

    @RequestMapping(value = "/getActionListByUser/{userid}", method = RequestMethod.GET)
    public Response<Action> getActionListByUser(@PathVariable(value = "userid")String userid);
}
