package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

import javax.persistence.Transient;

public class ResConTypeInfo extends BaseEntity {
    @Transient
    private String chapterid;

    @Transient
    private String contenttype;

    @Transient
    private Long count;

    public String getChapterid() {
        return chapterid;
    }

    public void setChapterid(String chapterid) {
        this.chapterid = chapterid;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
