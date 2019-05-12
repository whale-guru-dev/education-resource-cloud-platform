package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ResourceStarMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceDownload;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceStar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceStarService extends MyBaseService<ResourceStar> {
    @Autowired
    private ResourceStarMapper resourceStarMapper;

    @SystemServiceLog(description = "")
    public Response<ResourceStar> search(QueryTree queryTree)
    {
        // PageHelper
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixTimestampParameter(map);
        int count = resourceStarMapper.countStar(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<ResourceStar> queryList = resourceStarMapper.queryStarForList(map);
        Page<ResourceStar> result = new Page<ResourceStar>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<ResourceStar>(result);
    }

    @SystemServiceLog(description = "")
    public int getCountByUserAndResource(Map<String, Object> mapList)
    {
        fixTimestampParameter(mapList);
        int count = resourceStarMapper.getCountByUserAndResource(mapList);
        return count;
    }

    @SystemServiceLog(description = "")
    public Map<String, Object> rateResource(Map<String, Object> mapList)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        if (getCountByUserAndResource(mapList) != 0)
            result.put("result", -1);
        else
        {
            resourceStarMapper.addResourceStar(mapList);
            result.put("result", 0);
        }

        return result;
    }

    @SystemServiceLog(description = "")
    public List<ResourceStar> getRateInfo(Map<String, Object> mapList)
    {
        return resourceStarMapper.queryStarForList(mapList);
    }

    public int countStar(Map<String, Object> param) {
        fixTimestampParameter(param);
        return resourceStarMapper.countStar(param);
    }
}
