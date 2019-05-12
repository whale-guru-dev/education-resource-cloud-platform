package com.tianwen.springcloud.microservice.question.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.question.entity.Paper;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "question-service", url = "http://localhost:2228/question-service/paper")
public interface PaperMicroApi extends ICRUDMicroApi<Paper> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Paper> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getPaperStatistics", method = RequestMethod.GET)
    public Response<Map<String, Object>>getPaperStatistics();
}
