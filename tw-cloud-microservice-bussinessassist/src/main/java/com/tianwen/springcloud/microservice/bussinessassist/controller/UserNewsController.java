package com.tianwen.springcloud.microservice.bussinessassist.controller;

import java.sql.Timestamp;
import java.util.List;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.UserNewsMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import com.tianwen.springcloud.microservice.bussinessassist.entity.UserNews;
import com.tianwen.springcloud.microservice.bussinessassist.service.UserNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/news")
public class UserNewsController extends AbstractCRUDController<UserNews> implements UserNewsMicroApi {
    @Autowired
    private UserNewsService userNewsService;

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }

    @Override
    public Response<UserNews> getList(@RequestBody QueryTree queryTree) {
        return userNewsService.search(queryTree);
    }

    @Override
    public Response<Integer> getCount(@RequestBody QueryTree queryTree) {
        int count = userNewsService.count(queryTree);
        return new Response<>(count);
    }

    @Override
    public Response<UserNews> getData(@RequestBody QueryTree queryTree) {
        List<UserNews> data = userNewsService.getAllData(queryTree);
        return new Response<>(data);
    }

    @Override
    public Response<UserNews> insert(@RequestBody UserNews entity) {
        userNewsService.save(entity);
        return new Response<>(entity);
    }

    @Override
    public Response<UserNews> view(@RequestBody UserNews entity) {
        entity.setStatus("0");
        userNewsService.updateNotNull(entity);
        return new Response<>(entity);
    }
}
