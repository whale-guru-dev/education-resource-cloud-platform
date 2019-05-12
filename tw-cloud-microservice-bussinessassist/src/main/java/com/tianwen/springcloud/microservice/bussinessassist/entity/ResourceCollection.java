package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_resource_collect")
public class ResourceCollection extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_collectid_t_e_resource_collect')")
    @Column(name = "collectid")
    @ApiModelProperty("collectid")
    private String collectid;

    @Column(name = "userid")
    @ApiModelProperty(value = "userid", required = true)
    private String userid;

    @Column(name = "objectid")
    @ApiModelProperty(value = "objectid", required = true)
    private String objectid;

    @Column(name = "objectCode")
    @ApiModelProperty(value = "objectCode", required = true)
    private String objectCode;

    @Column(name = "objecttype")
    @ApiModelProperty(value = "objecttype", required = true)
    private String objecttype;

    @Column(name = "contenttype")
    @ApiModelProperty(value = "contenttype", required = true)
    private String contenttype;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    @Column(name = "collecttime")
    @ApiModelProperty(value = "collecttime", required = true)
    private Timestamp collecttime;

    public String getCollectid() {
        return collectid;
    }

    public void setCollectid(String collectid) {
        this.collectid = collectid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCollecttime() {
        if (collecttime == null)
            return null;
        return new Timestamp(collecttime.getTime());
    }

    public void setCollecttime(Timestamp collecttime) {
        if (collecttime == null)
            this.collecttime = null;
        else
            this.collecttime = new Timestamp(collecttime.getTime());
    }
}
