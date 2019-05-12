package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Advert;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/advert")
public interface AdvertMicroApi extends ICRUDMicroApi<Advert> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Advert> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public Response<Integer> getCount(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Advert> insert(@RequestBody Advert entity);

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public Response<Advert> modify(@RequestBody Advert entity);

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public Response<Advert> remove(@PathVariable(value = "id") String id);
}
