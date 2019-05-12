package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_advert")
public class Advert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_advertid_t_e_advert')")
    @Column(name = "advertid")
    @ApiModelProperty("advertid")
    private String advertid;

    @Column(name = "name")
    @ApiModelProperty(value = "name", required = true)
    private String name;

    @Column(name = "imagepath")
    @ApiModelProperty(value = "imagepath", required = true)
    private String imagepath;

    @Column(name = "sequence")
    @ApiModelProperty(value = "sequence", required = true)
    private String sequence;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    @Column(name = "creator")
    @ApiModelProperty(value = "creator", required = true)
    private String creator;

    @Column(name = "createtime")
    @ApiModelProperty(value = "createtime", required = true)
    private Timestamp createtime;

    @Column(name = "adverturl")
    @ApiModelProperty(value = "adverturl", required = true)
    private String adverturl;

    public String getAdverturl() {
        return adverturl;
    }

    public void setAdverturl(String adverturl) {
        this.adverturl = adverturl;
    }

    public String getAdvertid() {
        return advertid;
    }

    public void setAdvertid(String advertid) {
        this.advertid = advertid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreatetime() {
        if (createtime == null)
            return null;
        return new Timestamp(createtime.getTime());
    }

    public void setCreatetime(Timestamp createtime) {
        if (createtime == null)
            this.createtime = null;
        else
            this.createtime = new Timestamp(createtime.getTime());
    }
}
