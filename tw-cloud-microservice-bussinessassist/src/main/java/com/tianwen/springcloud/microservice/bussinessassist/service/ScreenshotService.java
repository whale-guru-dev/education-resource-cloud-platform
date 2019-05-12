package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ScreenshotMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.FeedBack;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Screenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScreenshotService extends MyBaseService<Screenshot> {

    @Autowired
    private ScreenshotMapper screenShotMapper;

    public void conFeedback2Screenshot(FeedBack feedback) {
        Map<String,Object>map = new HashMap<>();
        map.put("feedbackid",feedback.getFeedbackid());
        map.put("screenid",feedback.getScreenshot().getScreenid());
        map.put("businesstype", feedback.getType());
        map.put("filetype", feedback.getObjecttype());
        screenShotMapper.conFeedback2Screenshot(map);
    }
}
