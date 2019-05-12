package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.api.AuditMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.api.ResourceCollectionMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceCollection;
import com.tianwen.springcloud.microservice.bussinessassist.service.AuditService;
import com.tianwen.springcloud.microservice.bussinessassist.service.ResourceCollectionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.postgresql.core.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/collect")
public class ResourceCollectionController extends AbstractCRUDController<ResourceCollection> implements ResourceCollectionMicroApi {
    @Autowired
    private ResourceCollectionService resourceCollectionService;

    @Autowired
    private HttpSession httpSession;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceCollection> getList(@RequestBody QueryTree queryTree)
    {
        Response<ResourceCollection> resp = resourceCollectionService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceCollection> insert(@RequestBody ResourceCollection entity)
    {
        int ret = resourceCollectionService.save(entity);
        Response<ResourceCollection> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    public Response<ResourceCollection> remove(@RequestBody Map<String, Object> param) {
        List<ResourceCollection> entityList = resourceCollectionService.getCollectInfoByUser(param.get("userid").toString());
        Response resp = new Response<>();

        if (entityList != null) {
            for (ResourceCollection resCol : entityList)
                if (resCol.getObjectid().equals(param.get("contentid").toString()))
                    resp.setResponseEntity(resCol);
        }

        Map<String, Object> result = resourceCollectionService.throwResource(param);
        resp.getServerResult().setResultCode(result.get("result").toString());
        return resp;
    }

    @Override
    public Response<Integer> getCount(@RequestBody Map<String, Object> param) {
        return new Response<>(resourceCollectionService.countCollection(param));
    }

    @Override
    public Response<String> getContentidsByQueryTree(@RequestBody Map<String, Object> map) {
        return new Response<>(resourceCollectionService.getContentids(map));
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
