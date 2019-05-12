package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.FeedBackMapper;
import com.tianwen.springcloud.microservice.bussinessassist.dao.UserNewsMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import com.tianwen.springcloud.microservice.bussinessassist.entity.UserNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserNewsService extends MyBaseService<UserNews> {
    @Autowired
    private UserNewsMapper userNewsMapper;

    @SystemServiceLog(description = "")
    public Response<UserNews> search(QueryTree queryTree)
    {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        int count = userNewsMapper.countNews(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<UserNews> queryList = userNewsMapper.queryNewsForList(map);
        Page<UserNews> result = new Page<UserNews>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<UserNews>(result);
    }

    public List<UserNews> getAllData(QueryTree queryTree)
    {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        List<UserNews> data = userNewsMapper.queryNewsForList(map);
        return data;
    }

    public int count(QueryTree queryTree)
    {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        int count = userNewsMapper.countNews(map);
        return count;
    }
}
