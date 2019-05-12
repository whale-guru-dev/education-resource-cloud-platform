package com.tianwen.springcloud.microservice.question.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.question.api.PaperMicroApi;
import com.tianwen.springcloud.microservice.question.entity.Paper;
import com.tianwen.springcloud.microservice.question.service.PaperService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/paper")
public class PaperController extends AbstractCRUDController<Paper> implements PaperMicroApi {
    @Autowired
    private PaperService paperService;

    @Override
    public Response<Paper> getList(@RequestBody QueryTree queryTree) {
        return paperService.getList(queryTree);
    }

    @Override
    public Response<Map<String, Object>> getPaperStatistics() {
        Map<String, Object> result = new HashMap<>();
        Date begin_time = new Date(System.currentTimeMillis()), end_time = new Date(System.currentTimeMillis());

        //total
        {
            result.put("total", paperService.getTotalCount());
        }
        //totay
        {
            QueryTree today = new QueryTree();
            today.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            today.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("today", paperService.getPaperStatistics(today));
        }
        return new Response<>(result);
    }


    @Override
    public void validate(MethodType methodType, Object p) {    }
}
