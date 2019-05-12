package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.TeacherVoteMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.TeacherVote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherVoteService extends MyBaseService<TeacherVote> {
    @Autowired
    private TeacherVoteMapper teacherVoteMapper;

    @SystemServiceLog(description = "")
    public Map<String, Object> voteTeacher(Map<String, Object> mapList)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        if (teacherVoteMapper.getCountByUserAndTeacher(mapList) != 0)
            result.put("result", -1);
        else
        {
            teacherVoteMapper.addTeacherVote(mapList);
            result.put("result", 0);
        }

        return result;
    }

    public int countVote(Map<String, Object> param) {
        return teacherVoteMapper.countVote(param);
    }
}
