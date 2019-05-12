package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.ResourceViewMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceView;
import com.tianwen.springcloud.microservice.bussinessassist.service.ResourceViewService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/view")
public class ResourceViewController extends AbstractCRUDController<ResourceView> implements ResourceViewMicroApi {
    @Autowired
    private ResourceViewService resourceViewService;

    @Autowired
    private HttpSession httpSession;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceView> getList(@RequestBody QueryTree queryTree)
    {
        Response<ResourceView> resp = resourceViewService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceView> insert(@RequestBody ResourceView entity)
    {
        int ret = resourceViewService.save(entity);
        Response<ResourceView> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Integer> getCount(@RequestBody Map<String, Object> param)
    {
        return new Response<Integer>(resourceViewService.countView(param));
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
