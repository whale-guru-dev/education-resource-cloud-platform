package com.tianwen.springcloud.microservice.question.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.question.api.ChildMicroApi;
import com.tianwen.springcloud.microservice.question.entity.Child;
import com.tianwen.springcloud.microservice.question.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/child")
public class ChildController extends AbstractCRUDController<Child> implements ChildMicroApi {
    @Autowired
    private ChildService childService;

    @Override
    public Response<Child> getList(@RequestBody QueryTree queryTree) {
        return childService.getList(queryTree);
    }


    @Override
    public void validate(MethodType methodType, Object p) {    }
}
