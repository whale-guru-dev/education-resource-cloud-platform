package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.api.TeacherVoteMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.TeacherVote;
import com.tianwen.springcloud.microservice.bussinessassist.service.TeacherVoteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/teachervote")
public class TeacherVoteController extends AbstractCRUDController<TeacherVote> implements TeacherVoteMicroApi {
    @Autowired
    private TeacherVoteService teacherVoteService;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<TeacherVote> insert(@RequestBody TeacherVote entity)
    {
        int ret = teacherVoteService.save(entity);
        Response<TeacherVote> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Integer> getCount(@RequestBody QueryTree param)
    {
        Map<String, Object> map = QueryUtils.queryTree2Map(param);
        return new Response<>(teacherVoteService.countVote(map));
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
