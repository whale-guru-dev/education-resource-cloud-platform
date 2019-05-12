package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceCollection;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/collect")
public interface ResourceCollectionMicroApi extends ICRUDMicroApi<ResourceCollection> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<ResourceCollection> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<ResourceCollection> insert(@RequestBody ResourceCollection entity);

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Response<ResourceCollection> remove(@RequestBody Map<String, Object> param);

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Response<Integer> getCount(@RequestBody Map<String, Object> param);

    @RequestMapping(value = "/getContentidsByQueryTree", method = RequestMethod.POST)
    public Response<String> getContentidsByQueryTree(@RequestBody Map<String, Object> map);
}
