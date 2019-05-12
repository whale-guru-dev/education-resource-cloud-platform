package com.tianwen.springcloud.microservice.question.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.question.dao.LevelMapper;
import com.tianwen.springcloud.microservice.question.entity.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LevelService extends BaseService<Level> {

    @Autowired
    private LevelMapper levelMapper;

    public Response<Level> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(pagination);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        List<Level> queryList = levelMapper.getList(map), pageList ;

        int count = queryList.size();
        int start = Integer.parseInt(map.get("start").toString()), pageSize = pagination.getNumPerPage().intValue();
        if (start > queryList.size())
            pageList = new ArrayList<Level>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Level> result = new Page<Level>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<Level>(result);
    }
}
