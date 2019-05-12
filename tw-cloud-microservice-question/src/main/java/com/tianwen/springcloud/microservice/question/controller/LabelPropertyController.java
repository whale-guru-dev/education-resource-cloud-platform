package com.tianwen.springcloud.microservice.question.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.question.api.LabelPropertyMicroApi;
import com.tianwen.springcloud.microservice.question.entity.LabelProperty;
import com.tianwen.springcloud.microservice.question.service.LabelPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/labelproperty")
public class LabelPropertyController extends AbstractCRUDController<LabelProperty> implements LabelPropertyMicroApi {
    @Autowired
    private LabelPropertyService labelPropertyService;

    @Override
    public Response<LabelProperty> getList(@RequestBody QueryTree queryTree) {
        return labelPropertyService.getList(queryTree);
    }

    @Override
    public Response<Integer> getCount(@RequestBody Map<String, Object> map) {
        return labelPropertyService.getCount(map);
    }

    @Override
    public void validate(MethodType methodType, Object p) {

    }
}
