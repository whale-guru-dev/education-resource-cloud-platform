package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_con_resbasket")
public class ResourceBasket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_basketid_t_con_resbasket')")
    @Column(name = "basketid")
    @ApiModelProperty("basketid")
    private String basketid;

    @Column(name = "objectid")
    @ApiModelProperty(value = "objectid", required = true)
    private String objectid;

    @Column(name = "creator")
    @ApiModelProperty(value = "creator", required = true)
    private String creator;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    @Column(name = "createtime")
    @ApiModelProperty(value = "createtime", required = true)
    private Timestamp createtime;

    @Column(name = "buytime")
    @ApiModelProperty(value = "buytime")
    private Timestamp buytime;

    private String collectid;

    public String getBasketid() {
        return basketid;
    }

    public void setBasketid(String basketid) {
        this.basketid = basketid;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getCreator() {
        return creator;
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

    public Timestamp getBuytime() {
        if (buytime == null)
            return null;
        return new Timestamp(buytime.getTime());
    }

    public void setBuytime(Timestamp buytime) {
        if (buytime == null)
            this.buytime = null;
        else
            this.buytime = new Timestamp(buytime.getTime());
    }

    public String getCollectid() {
        return collectid;
    }

    public void setCollectid(String collectid) {
        this.collectid = collectid;
    }
}
