package com.tianwen.springcloud.microservice.resource.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.resource.dao.ThemeResMapper;
import com.tianwen.springcloud.microservice.resource.entity.ThemeRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by gems on 2018.12.21.
 */
@Service
public class ThemeResService  extends BaseService<ThemeRes>{
    @Autowired
    private ThemeResMapper themeResMapper;

    public Response<ThemeRes> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();

        Map<String, Object> param = QueryUtils.queryTree2Map(queryTree);
        Long count = themeResMapper.getCount(param);
        param.put("start", pagination.getStart());
        param.put("numPerPage", pagination.getNumPerPage());
        List<ThemeRes> queryList = themeResMapper.getList(param);

        Page<ThemeRes> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.addAll(queryList);
        page.setTotal(count);

        return new Response<>(page);
    }

    public List<ThemeRes> getListById(QueryTree queryTree) {
        Map<String, Object> param = QueryUtils.queryTree2Map(queryTree);
        return  themeResMapper.getList(param);
    }
}
