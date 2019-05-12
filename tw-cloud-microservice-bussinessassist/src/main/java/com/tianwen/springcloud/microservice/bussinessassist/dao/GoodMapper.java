package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Good;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodMapper extends MyMapper<Good> {

    /**
     *
     *
     * @param
     * @return List<Good>
     * @see
     */
    List<String> queryProductIdForList(Map<String, Object> mapList);

    List<Good> queryGoodForList(Map<String, Object> mapList);

    Long countGoods(Map<String, Object> mapList);

    Good getByContentid(String contentid);
}
