package com.tianwen.springcloud.microservice.bussinessassist.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Good;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@FeignClient(value = "reosurce-service", url = "http://localhost:2225/bussinessassist-service/good")
public interface GoodMicroApi extends ICRUDMicroApi<Good>
{
    @RequestMapping(value = "/makeESDb", method = RequestMethod.GET)
    public Response<Good> makeESDb();

    @RequestMapping(value = "/batchSaveToES", method = RequestMethod.POST)
    public Response<Good> batchSaveToES(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/saveToES", method = RequestMethod.POST)
    public Response<Good> saveToES(@RequestBody Good entity);

    @RequestMapping(value = "/deleteFromES", method = RequestMethod.POST)
    public Response<Good> removeFromES(@RequestBody Good entity);

    @RequestMapping(value = "/deleteFromES/{id}", method = RequestMethod.GET)
    public Response<Good> removeFromES(@PathVariable(value = "id") String id);

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Good> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getListByStatus", method = RequestMethod.POST)
    public Response<Good> getListByStatus(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getProductIds", method = RequestMethod.POST)
    public Response<String> getProductIds(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getByContentid/{contentid}", method = RequestMethod.GET)
    public Response<Good> getByContentid(@PathVariable(value = "contentid") String contentid);

    @RequestMapping(value = "/getByListAndQueryTree", method = RequestMethod.POST)
    public Response<Good> getByListAndQueryTree(@RequestBody Map<String, Object> map);

    @RequestMapping(value = "/getCountByCreator/{creator}", method = RequestMethod.GET)
    public Response<Long> getCountByCreator(@PathVariable(value = "creator") String creator);

    @RequestMapping(value = "/getCount", method = RequestMethod.POST)
    public Response<Long> getCount(@RequestBody QueryTree queryTree);
}
