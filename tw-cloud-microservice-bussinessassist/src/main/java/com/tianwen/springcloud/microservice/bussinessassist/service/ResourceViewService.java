package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ResourceViewMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceViewService extends MyBaseService<ResourceView> {
    @Autowired
    private ResourceViewMapper resourceViewMapper;

    @SystemServiceLog(description = "")
    public Response<ResourceView> search(QueryTree queryTree)
    {
        // PageHelper
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixTimestampParameter(map);
        int count = resourceViewMapper.countView(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<ResourceView> queryList = resourceViewMapper.queryViewForList(map);
        Page<ResourceView> result = new Page<ResourceView>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<>(result);
    }

    @SystemServiceLog(description = "")
    public int getCountByUserAndResource(Map<String, Object> mapList)
    {
        fixTimestampParameter(mapList);
        int count = resourceViewMapper.getCountByUserAndResource(mapList);
        return count;
    }

    @SystemServiceLog(description = "")
    public Map<String, Object> viewResource(Map<String, Object> mapList)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        if (getCountByUserAndResource(mapList) != 0)
            result.put("result", -1);
        else
        {
            resourceViewMapper.addResourceView(mapList);
            result.put("result", 0);
        }

        return result;
    }

    public int countView(Map<String, Object> param) {
        fixTimestampParameter(param);
        return resourceViewMapper.countView(param);
    }
}
