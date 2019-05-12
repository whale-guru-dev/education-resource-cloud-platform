package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.NoticeMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Notice;
import com.tianwen.springcloud.microservice.bussinessassist.service.NoticeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/notice")
public class NoticeController extends AbstractCRUDController<Notice> implements NoticeMicroApi {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private HttpSession httpSession;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Notice> getList(@RequestBody QueryTree queryTree)
    {
        Response<Notice> resp = noticeService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Notice> insert(@RequestBody Notice entity)
    {
        int ret = noticeService.save(entity);
        Response<Notice> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
