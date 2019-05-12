package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/audit")
public interface AuditMicroApi extends ICRUDMicroApi<Audit> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Audit> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getAllData", method = RequestMethod.POST)
    public Response<Audit> getAllData(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getByContentid/{contentid}", method = RequestMethod.GET)
    public Response<Audit> getByContentid(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Audit> insert(@RequestBody Audit entity);

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public Response<Integer > getCount(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getAuditorList", method = RequestMethod.GET)
    public Response<String> getAuditorList();
}
