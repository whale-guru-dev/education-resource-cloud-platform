package com.tianwen.springcloud.microservice.operation.dao;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.operation.entity.Charge;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChargeMapper extends MyMapper<Charge> {

    /**
     *
     *
     * @param
     * @return List<Member>
     * @see
     */
    List<Charge> queryChargeForList(Map<String, Object> mapList);

    List<String> getChargeUsers();

    Long countChargeList(Map<String, Object> map);

    Response<Charge> getStatistics(Map<String, Object> map);

    List<Map<String,Object>> getAreaStatistics(Map<String, Object> map);
}
