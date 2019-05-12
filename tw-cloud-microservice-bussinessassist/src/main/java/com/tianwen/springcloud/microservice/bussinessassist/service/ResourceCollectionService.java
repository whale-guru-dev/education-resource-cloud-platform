package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ResourceCollectionMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceCollectionService extends MyBaseService<ResourceCollection> {
    @Autowired
    private ResourceCollectionMapper resourceCollectionMapper;

    @SystemServiceLog(description = "")
    public Response<ResourceCollection> search(QueryTree queryTree)
    {
        // PageHelper
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixTimestampParameter(map);
        int count = resourceCollectionMapper.countCollection(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<ResourceCollection> queryList = resourceCollectionMapper.queryCollectionForList(map);
        Page<ResourceCollection> result = new Page<ResourceCollection>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<ResourceCollection>(result);
    }

    @SystemServiceLog(description = "")
    public int getCountByUser(Map<String, Object> mapList)
    {
        fixTimestampParameter(mapList);
        int count = resourceCollectionMapper.getCountByUser(mapList);
        return count;
    }

    @SystemServiceLog(description = "")
    public int getCountByUserAndResource(Map<String, Object> mapList)
    {
        fixTimestampParameter(mapList);
        int count = resourceCollectionMapper.getCountByUserAndResource(mapList);
        return count;
    }

    @SystemServiceLog(description = "")
    public Map<String, Object> collectResource(Map<String, Object> mapList)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        if (getCountByUserAndResource(mapList) != 0)
            result.put("result", -1);
        else if(getCountByUser(mapList) >= 200)
            result.put("result", -2);
        else
        {
            resourceCollectionMapper.addResourceCollect(mapList);
            result.put("result", 0);
        }

        return result;
    }

    public Map<String,Object> throwResource(Map<String, Object> mapList) {
        Map<String, Object> result = new HashMap<String, Object>();

        if (getCountByUserAndResource(mapList) == 0)
            result.put("result", -1);
        else
        {
            resourceCollectionMapper.removeResourceCollect(mapList);
            result.put("result", 0);
        }

        return result;
    }

    public List<ResourceCollection> getCollectInfoByUser(String userId) {
        return resourceCollectionMapper.getCollectInfoByUser(userId);
    }

    public int countCollection(Map<String, Object> param) {
        fixTimestampParameter(param);
        return resourceCollectionMapper.countCollection(param);
    }

    public List<String> getContentids(Map<String, Object> map) {
        List<String> contentids = resourceCollectionMapper.getContentids(map);
        if (contentids != null) {
            contentids.add("");
            contentids.add("");
        }
        return contentids;
    }
}
