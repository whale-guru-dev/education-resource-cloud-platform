package com.tianwen.springcloud.microservice.operation.api;

import com.tianwen.springcloud.commonapi.base.ICRUDMicroApi;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.microservice.operation.entity.Member;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "operation-service", url = "http://localhost:2223/operation-service/member")
public interface MemberMicroApi extends ICRUDMicroApi<Member>
{

    @RequestMapping(value = "/getMyScore/{userid}", method = RequestMethod.GET)
    public Response<Integer> getMyScore(@PathVariable(value = "userid") String userid);

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Response<Member> edit(@RequestBody Member collectionMember);

    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Response<Member> getList(@RequestBody QueryTree queryTree);

    @RequestMapping(value = "/getByUserId/{userid}", method = RequestMethod.GET)
    public Response<Member> getByUserId(@PathVariable(value = "userid") String userid);

    @RequestMapping(value = "/getUserIds", method = RequestMethod.POST)
    public Response<String> getUserIds(@RequestBody Integer count);

    @RequestMapping(value = "/getUseableTotalScore", method = RequestMethod.POST)
    public Response<Long> getUseableTotalScore(@RequestBody QueryTree queryTree);
}
