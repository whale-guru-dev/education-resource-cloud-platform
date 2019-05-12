package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceFlags;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceVote;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/vote")
public interface ResourceVoteMicroApi extends ICRUDMicroApi<ResourceVote> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<ResourceVote> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<ResourceVote> insert(@RequestBody ResourceVote entity);

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Response<Integer> getCount(@RequestBody Map<String, Object> param);

    @RequestMapping(value = "/getHotValue/{contentid}", method = RequestMethod.GET)
    public Response<Integer> getHotValue(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getResourceFlags", method = RequestMethod.POST)
    public Response<ResourceFlags> getResourceFlags (@RequestBody Map<String, String> param);
}
