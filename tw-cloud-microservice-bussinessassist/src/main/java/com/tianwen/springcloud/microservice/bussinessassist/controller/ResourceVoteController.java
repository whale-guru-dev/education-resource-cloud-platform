package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.ResourceVoteMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceFlags;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceVote;
import com.tianwen.springcloud.microservice.bussinessassist.service.ResourceVoteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/vote")
public class ResourceVoteController extends AbstractCRUDController<ResourceVote> implements ResourceVoteMicroApi {
    @Autowired
    private ResourceVoteService resourceVoteService;

    @Autowired
    private HttpSession httpSession;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceVote> getList(@RequestBody QueryTree queryTree)
    {
        Response<ResourceVote> resp = resourceVoteService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceVote> insert(@RequestBody ResourceVote entity)
    {
        int ret = resourceVoteService.save(entity);
        Response<ResourceVote> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Integer> getCount(@RequestBody Map<String, Object> param)
    {
        return new Response<Integer>(resourceVoteService.countVote(param));
    }

    @Override
    public Response<Integer> getHotValue(@PathVariable(value = "contentid") String contentid) {
        return new Response<>(resourceVoteService.getHotValue(contentid));
    }

    @Override
    public Response<ResourceFlags> getResourceFlags(@RequestBody Map<String, String> param) {
        return new Response<>(resourceVoteService.getResourceFlags(param));
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {

    }
}
