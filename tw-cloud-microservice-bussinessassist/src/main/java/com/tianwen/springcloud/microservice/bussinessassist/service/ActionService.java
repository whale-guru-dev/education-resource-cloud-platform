package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ActionMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActionService extends MyBaseService<Action> {

    @Autowired
    ActionMapper actionMapper;

    public Response<Action> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
            queryTree.setPagination(pagination);

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        List<Action> queryList = actionMapper.getList(map), pageList;

//        pagination.setNumPerPage(5);
        int count = queryList.size(), start = -1, pageSize = 200, pageNo = 1;

        if (map.get("start") != null && pagination.getNumPerPage() != null && pagination.getPageNo() != null) {
            start = Integer.parseInt(map.get("start").toString());
            pageSize = pagination.getNumPerPage().intValue();
            pageNo = pagination.getPageNo();
        }
        if (start > queryList.size())
            pageList = new ArrayList<Action>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Action> result = new Page<Action>(pageNo, pageSize);
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<Action>(result);
    }

    public Response<Action> getActionListByUser(String userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        return new Response(actionMapper.getList(map));
    }
}
