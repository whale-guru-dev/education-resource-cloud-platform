package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ResourceVoteMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceFlags;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceVoteService extends MyBaseService<ResourceVote> {
    @Autowired
    private ResourceVoteMapper resourceVoteMapper;

    @SystemServiceLog(description = "")
    public Response<ResourceVote> search(QueryTree queryTree)
    {
        if (queryTree.isSingleTable())
        {
            Example example = QueryUtils.queryTree2Example(queryTree, ResourceVote.class);
            return new Response<ResourceVote>(super.selectByExample(example));
        }

        // PageHelper
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        int count = resourceVoteMapper.countVote(map), pageNO = 1, pageSize = 200;
        List<ResourceVote> queryList = resourceVoteMapper.queryVoteForList(map);
        if (pagination.getNumPerPage() != null && pagination.getPageNo() != null) {
            pageNO = pagination.getPageNo();
            pageSize = pagination.getNumPerPage();
        }
        Page<ResourceVote> result = new Page<ResourceVote>(pageNO, pageSize);
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<ResourceVote>(result);
    }

    @SystemServiceLog(description = "")
    public int getCountByUserAndResource(Map<String, Object> mapList)
    {
        int count = resourceVoteMapper.getCountByUserAndResource(mapList);
        return count;
    }

    @SystemServiceLog(description = "")
    public Map<String, Object> voteResource(Map<String, Object> mapList)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        if (getCountByUserAndResource(mapList) != 0)
            result.put("result", -1);
        else
        {
            resourceVoteMapper.addResourceVote(mapList);
            result.put("result", 0);
        }

        return result;
    }

    public int countVote(Map<String, Object> param) {
        return resourceVoteMapper.countVote(param);
    }

    public Integer getHotValue(String contentid) {
        Map<String, String> map = new HashMap<>();
        map.put("contentid", contentid);
        Integer hotVal = resourceVoteMapper.getHotValue(map);
        hotVal = (null == hotVal) ?0:hotVal;
        return hotVal;
    }

    public ResourceFlags getResourceFlags(Map<String, String> param) {
        return resourceVoteMapper.getResourceFlags(param);
    }
}
