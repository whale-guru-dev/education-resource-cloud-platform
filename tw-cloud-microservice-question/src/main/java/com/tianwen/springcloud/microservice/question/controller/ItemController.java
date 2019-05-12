package com.tianwen.springcloud.microservice.question.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.question.api.ItemMicroApi;
import com.tianwen.springcloud.microservice.question.entity.Item;
import com.tianwen.springcloud.microservice.question.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/item")
public class ItemController extends AbstractCRUDController<Item> implements ItemMicroApi {
    @Autowired
    private ItemService itemService;

    @Override
    public Response<Item> getList(@RequestBody QueryTree queryTree) {
        return itemService.getList(queryTree);
    }


    @Override
    public void validate(MethodType methodType, Object p) {    }
}
