package com.tianwen.springcloud.microservice.question.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.question.api.QuestionMicroApi;
import com.tianwen.springcloud.microservice.question.entity.Question;
import com.tianwen.springcloud.microservice.question.service.QuestionService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/question")
public class QuestionController extends AbstractCRUDController<Question> implements QuestionMicroApi {
    @Autowired
    private QuestionService questionService;

    @Override
    public Response<Question> getList(@RequestBody QueryTree queryTree) {
        return questionService.getList(queryTree);
    }

    @Override
    public Response<Map<String, Object>> getQuestionStatistics() {
        Map<String, Object> result = new HashMap<>();
        Date begin_time = new Date(System.currentTimeMillis()), end_time = new Date(System.currentTimeMillis());

        //total
        {
            result.put("total", questionService.getTotalCount());
        }
        //totay
        {
            QueryTree today = new QueryTree();
            today.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            today.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("today", questionService.getPaperStatistics(today));
        }
        return new Response<>(result);
    }


    @Override
    public void validate(MethodType methodType, Object p) {    }
}
