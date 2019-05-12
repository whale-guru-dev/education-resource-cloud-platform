package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceStar;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/star")
public interface ResourceStarMicroApi extends ICRUDMicroApi<ResourceStar> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<ResourceStar> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<ResourceStar> insert(@RequestBody ResourceStar entity);

    @RequestMapping(value = "/getRateSum/{contentid}", method = RequestMethod.GET)
    public Response<Integer> getRateSumByResource(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getRateCount/{contentid}", method = RequestMethod.GET)
    public Response<Integer> getRateCountByResource(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getRating", method = RequestMethod.POST)
    public Response<Integer> getRatingByUser(@RequestBody Map<String, Object> param);

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Response<Integer> getCount(@RequestBody Map<String, Object> param);
}
