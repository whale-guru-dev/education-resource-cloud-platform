package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ResourceBasketMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceBasketService extends MyBaseService<ResourceBasket> {
    @Autowired
    private ResourceBasketMapper resourceBasketMapper;

    @SystemServiceLog(description = "")
    public Map<String, Object> addToBasket(Map<String, Object> mapList)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        if (resourceBasketMapper.count(mapList) != 0)
            result.put("result", -1);
        else
        {
            resourceBasketMapper.addResourceBasket(mapList);
            result.put("result", 0);
        }

        return result;
    }

    public Map<String,Object> removeFromBasket(Map<String, Object> mapList) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (resourceBasketMapper.count(mapList) == 0)
            result.put("result", -1);
        else
        {
            resourceBasketMapper.removeResourceBasket(mapList);
            result.put("result", 0);
        }

        return result;
    }

    public Response<ResourceBasket> search(QueryTree queryTree) {
        // PageHelper
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        int count = resourceBasketMapper.count(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<ResourceBasket> queryList = resourceBasketMapper.queryBasketForList(map);
        Page<ResourceBasket> result = new Page<ResourceBasket>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<ResourceBasket>(result);
    }
}
