package com.tianwen.springcloud.microservice.question.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.question.api.AnswerMicroApi;
import com.tianwen.springcloud.microservice.question.entity.Answer;
import com.tianwen.springcloud.microservice.question.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController extends AbstractCRUDController<Answer> implements AnswerMicroApi {
    @Autowired
    private AnswerService answerService;

    @Override
    public Response<Answer> getList(@RequestBody QueryTree queryTree) {
        return answerService.getList(queryTree);
    }

    @Override
    public Response<Answer> insert(@RequestBody Answer entity) {
        return null;
    }

    @Override
    public void validate(MethodType methodType, Object p) {    }
}
