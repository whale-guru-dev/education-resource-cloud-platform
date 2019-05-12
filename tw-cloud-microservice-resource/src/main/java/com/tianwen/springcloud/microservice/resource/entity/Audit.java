package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.sql.Timestamp;

@Table(name = "t_e_audit")
public class Audit extends BaseEntity {

    @Column
    private String auditid;

    private String objectid;

    private String objecttype;

    private String result;

    private String audituser;

    private String remark;

    private Timestamp audittime;

    private String audittype;

    private Object resource;

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    public String getAudittype() {
        return audittype;
    }

    public void setAudittype(String audittype) {
        this.audittype = audittype;
    }

    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAudituser() {
        return audituser;
    }

    public void setAudituser(String audituser) {
        this.audituser = audituser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getAudittime() {
        if (audittime == null)
            return null;
        return new Timestamp(audittime.getTime());
    }

    public void setAudittime(Timestamp audittime) {
        if (audittime == null)
            this.audittime = null;
        else
            this.audittime = new Timestamp(audittime.getTime());
    }
}
