package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.FeedBackMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Screenshot;
import com.tianwen.springcloud.microservice.bussinessassist.service.FeedBackService;
import com.tianwen.springcloud.microservice.bussinessassist.service.ScreenshotService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feedback")
public class FeedBackController extends AbstractCRUDController<FeedBack> implements FeedBackMicroApi {
    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private ScreenshotService screenshotService;

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<FeedBack> getList(@RequestBody QueryTree queryTree)
    {
        Response<FeedBack> resp = feedBackService.search(queryTree);
        return resp;
    }

    @Override
    @ApiOperation(value = "获取审核列表", notes = "获取审核列表")
    @ApiImplicitParam(name = "queryTree", value = "搜索条件树", required = false, dataType = "QueryTree", paramType = "body")
    @SystemControllerLog(description = "获取审核列表")
    public Response<FeedBack> insert(@RequestBody FeedBack entity)
    {
        entity.setStatus("0");
        entity.setIsscored("0");
        int ret = feedBackService.save(entity);
        if (ret == 1)
            ret = 200;
        Screenshot screenshot = entity.getScreenshot();
        if (screenshot != null) {
            screenshot.setCreatetime(entity.getCreatetime());
            screenshotService.save(screenshot);
            screenshotService.conFeedback2Screenshot(entity);
        }
        Response<FeedBack> resp = new Response<>(entity);
        resp.getServerResult().setResultCode(Integer.toString(ret));
        return resp;
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
