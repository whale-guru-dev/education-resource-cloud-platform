package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

public class ActivityCountInfo extends BaseEntity {

    private String activityid;
    private Long viewtimes = 0L;
    private Long downtimes = 0L;
    private Long joinercount = 0L;
    private Long answercount = 0L;
    private String creator = "";

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    public Long getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(Long viewtimes) {
        if (null != viewtimes)
            this.viewtimes = viewtimes;
    }

    public Long getDowntimes() {
        return downtimes;
    }

    public void setDowntimes(Long downtimes) {
        if (null != downtimes)
            this.downtimes = downtimes;
    }

    public Long getJoinercount() {
        return joinercount;
    }

    public void setJoinercount(Long joinercount) {
        if (null != joinercount)
            this.joinercount = joinercount;
    }

    public Long getAnswercount() {
        return answercount;
    }

    public void setAnswercount(Long answercount) {
        if (null != answercount)
            this.answercount = answercount;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        if (null != creator)
            this.creator = creator;
    }
}
