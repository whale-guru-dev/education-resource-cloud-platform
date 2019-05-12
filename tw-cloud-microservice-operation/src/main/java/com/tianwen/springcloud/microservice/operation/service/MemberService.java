package com.tianwen.springcloud.microservice.operation.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.operation.dao.MemberMapper;
import com.tianwen.springcloud.microservice.operation.entity.Member;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberService extends BaseService<Member> {

    @Autowired
    private MemberMapper memberMapper;

    public Response<Member> search(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        List<Member> queryList = memberMapper.queryMemberForList(map), pageList;
        int count = queryList.size();
        int start = Integer.parseInt(map.get("start").toString()), pageSize = pagination.getNumPerPage().intValue();
        if (start > queryList.size())
            pageList = new ArrayList<>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Member> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.setTotal(count);
        page.addAll(pageList);

        return new Response<>(page);
    }

    public Response<Integer> getMyScore(String userid) {
        String score = memberMapper.getMyScore(userid);
        return new Response<>(StringUtils.isEmpty(score)?Integer.valueOf(0):Integer.parseInt(score));
    }

    public Member getByUserId(String userid) {
        return memberMapper.getByUserId(userid);
    }

    public List<String> getUserIds(Integer count) {
        return memberMapper.getUserIdsByCount(count);
    }

    public Long getUseableTotalScore(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        return memberMapper.getTotalScore(map);
    }
}
