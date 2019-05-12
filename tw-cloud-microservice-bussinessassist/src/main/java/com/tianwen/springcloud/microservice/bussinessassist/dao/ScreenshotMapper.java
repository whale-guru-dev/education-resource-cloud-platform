package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Screenshot;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * Created by Enoc on 2018.10.02.
 */
@Mapper
public interface ScreenshotMapper extends MyMapper<Screenshot> {
    void conFeedback2Screenshot(Map<String,Object> map);
    Screenshot getScreenshotForFeedback(Map<String, Object>map);
}
