package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

import javax.persistence.Transient;

public class ResAuditInfo extends BaseEntity {
    @Transient
    private String creator;

    @Transient
    private String status;

    @Transient
    private Integer count;

    @Transient
    private String subjectid;

    @Transient
    private String schoolsection;

    public String getCreator() {
        return creator;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSchoolsection() { return schoolsection; }

    public void setSchoolsection(String schoolsection) {
        this.schoolsection = schoolsection;
    }
}
