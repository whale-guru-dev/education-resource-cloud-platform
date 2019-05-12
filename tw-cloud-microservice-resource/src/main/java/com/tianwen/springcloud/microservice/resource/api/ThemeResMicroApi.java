package com.tianwen.springcloud.microservice.resource.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.resource.entity.ThemeRes;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by gems on 2018.12.20.
 */
@FeignClient(value = "resource-service", url = "http://localhost:2224/resource-service/themeres")
public interface ThemeResMicroApi extends ICRUDMicroApi<ThemeRes>{
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<ThemeRes> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Response<Integer> insert(@RequestBody List<ThemeRes> list);

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Response<Integer> remove(@RequestBody List<ThemeRes> list);

    @RequestMapping(value = "/makeConESDb", method = RequestMethod.GET)
    public Response<ThemeRes> makeConESDb();

    @RequestMapping(value = "/batchSaveConToES", method = RequestMethod.POST)
    public Response<ThemeRes> batchSaveConToES(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/saveConToES", method = RequestMethod.POST)
    public Response<ThemeRes> saveConToES(@RequestBody ThemeRes conInfo);

    @RequestMapping(value = "/deleteConFromES", method = RequestMethod.POST)
    public Response<ThemeRes> removeConFromES(@RequestBody ThemeRes conInfo);

    @RequestMapping(value = "/getThemeResListById", method = RequestMethod.POST)
    public List<ThemeRes> getThemeResListById(@RequestBody QueryTree queryTree );
}
