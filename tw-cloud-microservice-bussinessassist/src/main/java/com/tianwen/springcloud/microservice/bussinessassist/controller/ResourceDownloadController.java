package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.AuditMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.api.ResourceDownloadMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceDownload;
import com.tianwen.springcloud.microservice.bussinessassist.service.AuditService;
import com.tianwen.springcloud.microservice.bussinessassist.service.ResourceDownloadService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/download")
public class ResourceDownloadController extends AbstractCRUDController<ResourceDownload> implements ResourceDownloadMicroApi {
    @Autowired
    private ResourceDownloadService resourceDownloadService;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceDownload> getList(@RequestBody QueryTree queryTree)
    {
        Response<ResourceDownload> resp = resourceDownloadService.search(queryTree);
        return resp;
    }

    @Override
    public Response<ResourceDownload> getByExample(@RequestBody ResourceDownload example) {
        return resourceDownloadService.getByExample(example);
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceDownload> insert(@RequestBody ResourceDownload entity)
    {
        int ret = resourceDownloadService.save(entity);
        Response<ResourceDownload> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    public Response<Integer> getCount(@RequestBody Map<String, Object> param) {
        return new Response<>(resourceDownloadService.countDown(param));
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
