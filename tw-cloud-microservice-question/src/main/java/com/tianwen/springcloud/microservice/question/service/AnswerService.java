package com.tianwen.springcloud.microservice.question.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.question.dao.AnswerMapper;
import com.tianwen.springcloud.microservice.question.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerService extends BaseService<Answer> {

    @Autowired
    private AnswerMapper answerMapper;

    public Response<Answer> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(pagination);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        List<Answer> queryList = answerMapper.getList(map), pageList ;

        int count = queryList.size();
        int start = Integer.parseInt(map.get("start").toString()), pageSize = pagination.getNumPerPage().intValue();
        if (start > queryList.size())
            pageList = new ArrayList<Answer>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Answer> result = new Page<Answer>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<Answer>(result);
    }
}
