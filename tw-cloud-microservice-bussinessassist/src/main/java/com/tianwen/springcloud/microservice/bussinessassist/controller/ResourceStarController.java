package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.AuditMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.api.ResourceStarMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceStar;
import com.tianwen.springcloud.microservice.bussinessassist.service.AuditService;
import com.tianwen.springcloud.microservice.bussinessassist.service.ResourceStarService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/star")
public class ResourceStarController extends AbstractCRUDController<ResourceStar> implements ResourceStarMicroApi {
    @Autowired
    private ResourceStarService resourceStarService;

    @Autowired
    private HttpSession httpSession;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceStar> getList(@RequestBody QueryTree queryTree)
    {
        Response<ResourceStar> resp = resourceStarService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceStar> insert(@RequestBody ResourceStar entity)
    {
        int ret = resourceStarService.save(entity);
        Response<ResourceStar> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Integer> getRateSumByResource(@PathVariable(value = "contentid") String contentid)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("contentid", contentid);

        List<ResourceStar> starList = resourceStarService.getRateInfo(map);
        int sum = 0;

        for(ResourceStar star : starList)
            sum += star.getResult();

        return new Response<>(sum);
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Integer> getRateCountByResource(@PathVariable(value = "contentid") String contentid)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("contentid", contentid);

        List<ResourceStar> starList = resourceStarService.getRateInfo(map);
        int count = starList.size();

        return new Response<>(count);
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<Integer> getRatingByUser(@RequestBody Map<String, Object> param)
    {
        List<ResourceStar> starList = resourceStarService.getRateInfo(param);
        int rating = 0;

        if (!CollectionUtils.isEmpty(starList))
            rating = starList.get(0).getResult();

        return new Response<>(rating);
    }

    @Override
    public Response<Integer> getCount(@RequestBody Map<String, Object> param) {
        return new Response<>(resourceStarService.countStar(param));
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
