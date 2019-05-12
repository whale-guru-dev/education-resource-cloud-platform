package com.tianwen.springcloud.microservice.operation.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_charge_detail")
public class Charge extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_chargeid_t_e_charge_detail')")
    @Column(name = "chargeid")
    @ApiModelProperty("")
    private String chargeid;

    @Column(name = "exchange")
    @ApiModelProperty(value = "", required = true)
    private double exchange;

    @Column(name = "cost")
    @ApiModelProperty(value = "", required = true)
    private double cost;

    @Column(name = "userid")
    @ApiModelProperty(value = "", required = true)
    private String userid;

    @Column(name = "status")
    @ApiModelProperty(value = "", required = true)
    private String status;

    @Column(name = "payinfo")
    @ApiModelProperty("")
    private String payinfo;

    @Column(name = "createtime")
    @ApiModelProperty("")
    private Timestamp createtime;

    private String loginname;

    private String realname;

    private String chargername;

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getChargeid() {
        return chargeid;
    }

    public void setChargeid(String chargeid) {
        this.chargeid = chargeid;
    }

    public double getExchange() {
        return exchange;
    }

    public void setExchange(double exchange) {
        this.exchange = exchange;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayinfo() {
        return payinfo;
    }

    public void setPayinfo(String payinfo) {
        this.payinfo = payinfo;
    }

    public Timestamp getCreatetime() {
        if (createtime == null)
            return null;
        return new Timestamp(createtime.getTime());
    }

    public void setCreatetime(Timestamp createtime) {
        if (createtime != null)
            this.createtime = new Timestamp(createtime.getTime());
    }

    public String getChargername() {
        return chargername;
    }

    public void setChargername(String chargername) {
        this.chargername = chargername;
    }
}
