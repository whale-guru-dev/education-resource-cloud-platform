package com.tianwen.springcloud.microservice.resource.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.resource.entity.FileInfo;
import com.tianwen.springcloud.microservice.resource.entity.Resource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@FeignClient(value = "resource-service", url = "http://localhost:2224/resource-service/file")
public interface FileMicroApi extends ICRUDMicroApi<FileInfo> {

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<FileInfo> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public Response<Integer> batchDelete(@RequestBody List<String> ids);

    @RequestMapping(value = "/getByContentid/{contentid}", method = RequestMethod.GET)
    public Response<FileInfo> getByContentid(@PathVariable(value = "contentid")  String contentid);

    @RequestMapping(value = "/getListByFields", method = RequestMethod.POST)
    public Response<FileInfo> getListByFields(@RequestBody Map<String,String> fields);

}
