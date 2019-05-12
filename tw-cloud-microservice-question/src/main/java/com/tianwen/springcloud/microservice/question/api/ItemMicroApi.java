package com.tianwen.springcloud.microservice.question.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.question.entity.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "question-service", url = "http://localhost:2228/question-service/item")
public interface ItemMicroApi extends ICRUDMicroApi<Item> {
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Item> getList(@RequestBody QueryTree queryTree);
}
