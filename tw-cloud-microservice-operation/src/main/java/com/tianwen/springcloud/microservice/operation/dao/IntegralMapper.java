package com.tianwen.springcloud.microservice.operation.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.operation.entity.Integral;
import com.tianwen.springcloud.microservice.operation.entity.ScoreExportMan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IntegralMapper extends MyMapper<Integral> {
    /**
     *get list
     * @param
     * @return List<Integral>
     * @see
     */
    List<Integral> queryIntegralForList(Map<String, Object> mapList);

    /**
     * count for list
     * @param map
     * @return
     */
    Integer countIntegralList(Map<String, Object> map);

    /**
     * get score export man list
     * @return
     */
    List<ScoreExportMan> getExportManList();

    /**
     * get user contribution
     * @param userid
     * @return
     */
    Integer getUserContribution(String userid);

    /**
     *
     * @param userids
     * @return
     */
    List<Integer> getTodayIntegralInfo(Map<String, Object> userids);

    /**
     *
     * @return
     */
    Integer getTodayBonusScore(Map<String, Object> map);

    /**
     * getTodayLoginTimes
     * @param map
     * @return
     */
    Integer getTodayLoginTimes(Map<String, String> map);

    /**
     *
     * @param areaList
     * @return
     */
    List<Map<String,Object>> getStatistics(Map<String, Object> areaList);
}
