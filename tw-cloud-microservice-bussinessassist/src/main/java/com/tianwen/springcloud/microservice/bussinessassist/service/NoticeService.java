package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.NoticeMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NoticeService extends MyBaseService<Notice> {
    @Autowired
    private NoticeMapper noticeMapper;

    @SystemServiceLog(description = "")
    public Response<Notice> search(QueryTree queryTree)
    {
        if (queryTree.isSingleTable())
        {
            Example example = QueryUtils.queryTree2Example(queryTree, Notice.class);
            return new Response<Notice>(super.selectByExample(example));
        }

        // PageHelper
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(pagination);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        List<Notice> queryList = noticeMapper.queryNoticeForList(map), pageList;

        int count = queryList.size(), start = -1, pageSize = 200, pageNo=1 ;

        if (map.get("start") != null && pagination.getNumPerPage() != null && pagination.getPageNo() != null) {
            start = Integer.parseInt(map.get("start").toString());
            pageSize = pagination.getNumPerPage().intValue();
            pageNo = pagination.getPageNo();
        }
        if (start > queryList.size())
            pageList = new ArrayList<Notice>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Notice> result = new Page<Notice>(pageNo, pageSize);
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<>(result);
    }

    @SystemServiceLog(description = "")
    public int getCountByUserAndResource(Map<String, Object> mapList)
    {
        int count = noticeMapper.getCountByUserAndResource(mapList);
        return count;
    }

}
