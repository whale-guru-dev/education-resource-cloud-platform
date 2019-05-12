package com.tianwen.springcloud.microservice.question.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.question.entity.PaperParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "question-service", url = "http://localhost:2228/question-service/paperparameter")
public interface PaperParameterMicroApi extends ICRUDMicroApi<PaperParam> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<PaperParam> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public Response<Integer> getCount(@RequestBody Map<String, Object>map);
}
