package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.AuditMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import com.tianwen.springcloud.microservice.bussinessassist.service.AuditService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value = "/audit")
public class AuditController extends AbstractCRUDController<Audit> implements AuditMicroApi {
    @Autowired
    private AuditService auditService;

    @Autowired
    private HttpSession httpSession;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Audit> getList(@RequestBody QueryTree queryTree)
    {
        Response<Audit> resp = auditService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "", notes = "")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "")
    public Response<Audit> getAllData(@RequestBody QueryTree queryTree)
    {
        List<Audit> resp = auditService.getAllData(queryTree);
        return new Response<>(resp);
    }

    @Override
    public Response<Audit> getByContentid(@PathVariable(value = "contentid") String contentid) {
        Audit entity = auditService.getByContentid(contentid);

        return new Response<>(entity);
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Integer> getCount(@RequestBody QueryTree queryTree)
    {
        int count = auditService.getCount(queryTree);
        Response<Integer> resp = new Response<>(count);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<String> getAuditorList()
    {
        List<String> userList = auditService.getAuditorList();
        return new Response<>(userList);
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Audit> insert(@RequestBody Audit entity)
    {
        int ret = auditService.save(entity);
        Response<Audit> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
