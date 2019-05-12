package com.tianwen.springcloud.microservice.operation.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

public class ScoreExportMan extends BaseEntity {
    private String userid;
    private String username;
    private String totalscore;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(String totalscore) {
        this.totalscore = totalscore;
    }
}
