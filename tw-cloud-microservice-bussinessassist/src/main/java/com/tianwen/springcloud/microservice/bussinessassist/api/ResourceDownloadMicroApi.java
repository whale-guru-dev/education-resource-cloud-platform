package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceDownload;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "bussinessassist-service", url = "http://localhost:2225/bussinessassist-service/download")
public interface ResourceDownloadMicroApi extends ICRUDMicroApi<ResourceDownload> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<ResourceDownload> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getByExample", method = RequestMethod.POST)
    Response<ResourceDownload> getByExample(@RequestBody ResourceDownload example);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<ResourceDownload> insert(@RequestBody ResourceDownload entity);

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Response<Integer> getCount(@RequestBody Map<String, Object> param);
}
