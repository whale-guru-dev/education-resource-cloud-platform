package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.AdvertMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.constant.IBussinessMicroConstants;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Advert;
import com.tianwen.springcloud.microservice.bussinessassist.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/advert")
public class AdvertController extends AbstractCRUDController<Advert> implements AdvertMicroApi {
    @Autowired
    private AdvertService advertService;

    @Override
    public void validate(MethodType methodType, Object p)
    {

    }

    @Override
    public Response<Advert> getList(@RequestBody QueryTree queryTree) {
        return advertService.search(queryTree);
    }

    @Override
    public Response<Integer> getCount(@RequestBody QueryTree queryTree) {
        int count = advertService.countAdvert(queryTree);
        return new Response<>(count);
    }

    @Override
    public Response<Advert> insert(@RequestBody Advert entity) {
        entity.setStatus(IBussinessMicroConstants.ADVERT_STATUS_VALID);
        advertService.save(entity);
        return new Response<>(entity);
    }

    @Override
    public Response<Advert> modify(@RequestBody Advert entity) {
        advertService.updateNotNull(entity);
        return new Response<>(entity);
    }

    @Override
    public Response<Advert> remove(@PathVariable(value = "id") String id) {
        Advert entity =advertService.selectByKey(id);
        advertService.delete(entity);
        return new Response<>(entity);
    }


}
