package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.OptionMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OptionService extends MyBaseService<Option> {

    @Autowired
    OptionMapper optionMapper;

    public Response<Option> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(pagination);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        List<Option> queryList = optionMapper.getList(map), pageList;

        int count = queryList.size(), start = -1, pageSize = 200, pageNo=1 ;

        if (map.get("start") != null && pagination.getNumPerPage() != null && pagination.getPageNo() != null) {
            start = Integer.parseInt(map.get("start").toString());
            pageSize = pagination.getNumPerPage().intValue();
            pageNo = pagination.getPageNo();
        }
        if (start > queryList.size())
            pageList = new ArrayList<>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Option> result = new Page<Option>(pageNo, pageSize);
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<Option>(result);
    }

    public Response<Integer> getOptionList() {
        return new Response<Integer>(optionMapper.getOptionCount());
    }
}
