package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.github.pagehelper.PageInfo;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.ResourceBasketMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.api.ResourceVoteMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceBasket;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceVote;
import com.tianwen.springcloud.microservice.bussinessassist.service.ResourceBasketService;
import com.tianwen.springcloud.microservice.bussinessassist.service.ResourceVoteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.postgresql.core.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/basket")
public class ResourceBasketController extends AbstractCRUDController<ResourceBasket> implements ResourceBasketMicroApi {
    @Autowired
    private ResourceBasketService resourceBasketService;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceBasket> getList(@RequestBody QueryTree queryTree)
    {
        Response<ResourceBasket> resp = resourceBasketService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceBasket> insert(@RequestBody ResourceBasket entity)
    {
        int ret = resourceBasketService.save(entity);
        Response<ResourceBasket> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<ResourceBasket> remove(@RequestBody Map<String, Object> param)
    {
        QueryTree queryTree = new QueryTree();
        queryTree.addCondition(new QueryCondition("userid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, param.get("userid").toString()));
        queryTree.addCondition(new QueryCondition("contentid", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, param.get("contentid").toString()));
        PageInfo<ResourceBasket> basketPage = resourceBasketService.search(queryTree).getPageInfo();
        if (basketPage != null)
        {
            List<ResourceBasket> basketList = basketPage.getList();
            if (!CollectionUtils.isEmpty(basketList)) {
                ResourceBasket entity = basketList.get(0);
                int ret = resourceBasketService.delete(basketList.get(0));
                Response<ResourceBasket> resp = new Response<>(entity);
                resp.getServerResult().setResultCode(Integer.toString(ret));
                return resp;
            }
        }
        return new Response<>();
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
