package com.tianwen.springcloud.microservice.question.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.question.dao.QuestionMapper;
import com.tianwen.springcloud.microservice.question.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService extends BaseService<Question> {

    @Autowired
    private QuestionMapper questionMapper;

    public Response<Question> getList(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(pagination);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        List<Question> queryList = questionMapper.getList(map), pageList ;

        int count = queryList.size();
        int start = Integer.parseInt(map.get("start").toString()), pageSize = pagination.getNumPerPage().intValue();
        if (start > queryList.size())
            pageList = new ArrayList<Question>();
        else
            pageList = queryList.subList(start ,start + pageSize <= queryList.size() ? start + pageSize : queryList.size());

        Page<Question> result = new Page<Question>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<Question>(result);
    }
    public int getTotalCount(){
        return questionMapper.getTotalCount();
    }

    public int getPaperStatistics(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixTimestampFormat(map);

        List<Question> questionList = questionMapper.getList(map);

        int sum = 0;
        if (!questionList.isEmpty()) {
            sum = questionList.size();
        }
        return sum;
    }

    private Map<String, Object> fixTimestampFormat(Map<String, Object> map) {
        {
            Object begin = map.get("begin_time");
            if (begin != null) {
                map.remove("begin_time");
                if (!begin.toString().isEmpty()) {
                    Timestamp beginDate = Timestamp.valueOf(begin.toString() + " 00:00:00");
                    map.remove("begin_time");
                    map.put("begin_time", beginDate);
                }
            }
        }
        {
            Object end = map.get("end_time");
            if (end != null) {
                map.remove("end_time");
                if (!end.toString().isEmpty()){
                    Timestamp endDate = Timestamp.valueOf(end.toString() + " 23:59:59");
                    map.remove("end_time");
                    map.put("end_time", endDate);
                }
            }
        }
        return map;
    }
}
