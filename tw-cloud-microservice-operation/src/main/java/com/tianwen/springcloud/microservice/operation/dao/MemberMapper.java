package com.tianwen.springcloud.microservice.operation.dao;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.operation.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberMapper extends MyMapper<Member> {

    /**
     *
     *
     * @param
     * @return List<Member>
     * @see
     */
    List<Member> queryMemberForList(Map<String, Object> mapList);

    /**
     *
     * @param userid
     * @return
     */
    String getMyScore(String userid);

    /**
     *
     * @return
     */
    Long getTotalScore(Map<String, Object> map);

    Member getByUserId(String userid);

    List<String> getUserIdsByCount(Integer count);
}
