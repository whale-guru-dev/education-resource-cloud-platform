package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_audit")
public class Audit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_auditid_t_e_audit')")
    @Column(name = "auditid")
    @ApiModelProperty("auditid")
    private String auditid;

    @Column(name = "objectid")
    @ApiModelProperty(value = "objectid", required = true)
    private String objectid;

    @Column(name = "objecttype")
    @ApiModelProperty(value = "objecttype", required = true)
    private String objecttype;

    @Column(name = "result")
    @ApiModelProperty(value = "result", required = true)
    private String result;

    @Column(name = "audituser")
    @ApiModelProperty(value = "audituser", required = true)
    private String audituser;

    @Column(name = "remark")
    @ApiModelProperty(value = "remark")
    private String remark;

    @Column(name = "audittime")
    @ApiModelProperty(value = "audittime", required = true)
    private Timestamp audittime;

    @Column(name = "audittype")
    @ApiModelProperty(value = "audittype", required = true)
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

    @Override
    public String toString()
    {
        return "Audit [auditid=" +auditid + ", objectid=" + objectid + ", objecttype= " + objecttype +
                ", result=" + result + ", audituser=" + audituser + ", remark=" + remark +
                ", audittime=" + audittime + ", audittype=" + audittype +  "]";
    }
}
