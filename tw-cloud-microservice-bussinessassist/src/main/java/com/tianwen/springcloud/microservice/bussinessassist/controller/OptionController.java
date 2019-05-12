package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.OptionMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Option;
import com.tianwen.springcloud.microservice.bussinessassist.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/option")
public class OptionController extends AbstractCRUDController<Option> implements OptionMicroApi {

    @Autowired
    OptionService optionService;

    @Override
    public Response<Option> getList(@RequestBody QueryTree queryTree) {
        return optionService.getList(queryTree);
    }

    @Override
    public Response<Integer> getOptionCount() {
        return optionService.getOptionList();
    }

    @Override
    public Response<Option> deleteSomeOption() {

        return new Response<>();
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }
}
