package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

public class UserCountInfo extends BaseEntity {
    private String userid;
    private Integer collectiontimes;
    private Integer votetimes;
    private Integer downtimes;
    private Integer viewtimes;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getCollectiontimes() {
        return collectiontimes;
    }

    public void setCollectiontimes(Integer collectiontimes) {
        this.collectiontimes = collectiontimes;
    }

    public Integer getVotetimes() {
        return votetimes;
    }

    public void setVotetimes(Integer votetimes) {
        this.votetimes = votetimes;
    }

    public Integer getDowntimes() {
        return downtimes;
    }

    public void setDowntimes(Integer downtimes) {
        this.downtimes = downtimes;
    }

    public Integer getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(Integer viewtimes) {
        this.viewtimes = viewtimes;
    }
}
