package com.tianwen.springcloud.microservice.operation.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.operation.api.MemberMicroApi;
import com.tianwen.springcloud.microservice.operation.entity.Member;
import com.tianwen.springcloud.microservice.operation.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping(value = "/member")
public class MemberController extends AbstractCRUDController<Member> implements MemberMicroApi {
    @Autowired
    private MemberService memberService;

    @Override
    public Response<Integer> getMyScore(@PathVariable(value = "userid") String userid) {
        return memberService.getMyScore(userid);
    }

    @Override
    public Response<Member> edit(@RequestBody Member collectionMember) {
        Timestamp curtime = new Timestamp(System.currentTimeMillis());
        collectionMember.setLastmodifytime(curtime);

        memberService.updateNotNull(collectionMember);
        return new Response<>(collectionMember);
    }

    @Override
    public Response<Member> getList(@RequestBody QueryTree queryTree) {
        return memberService.search(queryTree);
    }

    @Override
    public Response<Member> getByUserId(@PathVariable(value = "userid") String userid) {
        return new Response<>(memberService.getByUserId(userid));
    }

    @Override
    public Response<String> getUserIds(@RequestBody Integer count) {
        return new Response<>(memberService.getUserIds(count));
    }

    @Override
    public Response<Long> getUseableTotalScore(@RequestBody QueryTree queryTree) {
        return new Response(memberService.getUseableTotalScore(queryTree));
    }


    private void validateAdd(Member member){
        Timestamp curtime =new Timestamp(System.currentTimeMillis());
        member.setCreatetime(curtime);
        member.setLastmodifytime(curtime);
    }

    @Override
    public void validate(MethodType methodType, Object p)
    {

    }
}
