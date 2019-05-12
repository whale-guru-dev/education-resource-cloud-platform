package com.tianwen.springcloud.microservice.question.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.question.dao.LabelPropertyMapper;
import com.tianwen.springcloud.microservice.question.entity.LabelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelPropertyService extends BaseService<LabelProperty> {

    @Autowired
    private LabelPropertyMapper labelPropertyMapper;

    public Response<LabelProperty> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(pagination);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        List<LabelProperty> queryList = labelPropertyMapper.getList(map), pageList ;

        int count = queryList.size();
        int start = Integer.parseInt(map.get("start").toString()), pageSize = pagination.getNumPerPage().intValue();
        if (start > queryList.size())
            pageList = new ArrayList<LabelProperty>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<LabelProperty> result = new Page<LabelProperty>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<LabelProperty>(result);
    }

    public Response<Integer> getCount(Map<String, Object>map) {
        return new Response<>(labelPropertyMapper.getCount(map));
    }
}
