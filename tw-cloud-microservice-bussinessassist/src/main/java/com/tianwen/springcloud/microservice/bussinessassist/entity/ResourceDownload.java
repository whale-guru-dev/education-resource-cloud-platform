package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_resource_download")
public class ResourceDownload extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_downid_t_e_resource_download')")
    @Column(name = "downid")
    @ApiModelProperty("downid")
    private String downid;

    @Column(name = "userid")
    @ApiModelProperty(value = "userid", required = true)
    private String userid;

    @Column(name = "objectid")
    @ApiModelProperty(value = "objectid", required = true)
    private String objectid;

    @Column(name = "objectcode")
    @ApiModelProperty(value = "objectcode", required = true)
    private String objectcode;

    @Column(name = "objecttype")
    @ApiModelProperty(value = "objecttype", required = true)
    private String objecttype;

    @Column(name = "contenttype")
    @ApiModelProperty(value = "contenttype", required = true)
    private String contenttype;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    @Column(name = "downtime")
    @ApiModelProperty(value = "downtime", required = true)
    private Timestamp downtime;

    public String getDownid() {
        return downid;
    }

    public void setDownid(String downid) {
        this.downid = downid;
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

    public String getObjectcode() {
        return objectcode;
    }

    public void setObjectcode(String objectcode) {
        this.objectcode = objectcode;
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

    public Timestamp getDowntime() {
        if (downtime == null)
            return null;
        return new Timestamp(downtime.getTime());
    }

    public void setDowntime(Timestamp downtime) {
        if (downtime == null)
            this.downtime = null;
        else
            this.downtime = new Timestamp(downtime.getTime());
    }
}
