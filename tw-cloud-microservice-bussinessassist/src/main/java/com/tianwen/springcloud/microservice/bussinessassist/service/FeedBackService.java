package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.FeedBackMapper;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ScreenshotMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Screenshot;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class FeedBackService extends MyBaseService<FeedBack> {
    @Autowired
    private FeedBackMapper feedBackMapper;

    @Autowired
    private ScreenshotMapper screenshotMapper;

    @SystemServiceLog(description = "")
    public Response<FeedBack> search(QueryTree queryTree)
    {
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(pagination);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        {
            Object end = map.get("upload_endtime");
            if (end == null){
                Date enddate = new Date(System.currentTimeMillis());
                map.put("upload_endtime", DateUtils.formatDate(enddate, "yyyy-MM-dd"));
            }
        }
        {
            Object begin = map.get("upload_begintime");
            if (begin == null){
                String begindate = "1900-01-01";
                map.put("upload_begintime", begindate);
            }
        }
        fixTimestampParameter(map);
        List<FeedBack> rawList = feedBackMapper.queryFeedBackForList(map), pageList;
        List<FeedBack> queryList = new ArrayList<>();
        for(FeedBack query: rawList){
            Map<String,Object> querymap = new HashMap<>();
            querymap.put("feedbackid", query.getFeedbackid());
            Screenshot screenshot = screenshotMapper.getScreenshotForFeedback(querymap);
            if (screenshot != null)
                query.setScreenshot(screenshot);
            queryList.add(query);
        }

        int count = queryList.size(), start = -1, pageSize = 200, pageNo=1 ;

        if (map.get("start") != null && pagination.getNumPerPage() != null && pagination.getPageNo() != null) {
            start = Integer.parseInt(map.get("start").toString());
            pageSize = pagination.getNumPerPage().intValue();
            pageNo = pagination.getPageNo();
        }
        if (start > queryList.size())
            pageList = new ArrayList<FeedBack>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<FeedBack> result = new Page<FeedBack>(pageNo, pageSize);
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<FeedBack>(result);
    }

    public List<FeedBack> getAllData(QueryTree queryTree)
    {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        List<FeedBack> data = feedBackMapper.queryFeedBackForList(map);
        return data;
    }

    public int count(QueryTree queryTree)
    {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        int count = feedBackMapper.countFeedBack(map);
        return count;
    }
}
