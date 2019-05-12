package com.tianwen.springcloud.microservice.question.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.question.api.PaperParameterMicroApi;
import com.tianwen.springcloud.microservice.question.entity.PaperParam;
import com.tianwen.springcloud.microservice.question.service.PaperParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/paperparameter")
public class PaperParameterController extends AbstractCRUDController<PaperParam> implements PaperParameterMicroApi {
    @Autowired
    private PaperParameterService paperParameterService;

    @Override
    public Response<PaperParam> getList(@RequestBody QueryTree queryTree) {
        return paperParameterService.getList(queryTree);
    }

    @Override
    public Response<Integer> getCount(@RequestBody Map<String, Object> map) {
        return paperParameterService.getCount(map);
    }

    @Override
    public void validate(MethodType methodType, Object p) {

    }
}
