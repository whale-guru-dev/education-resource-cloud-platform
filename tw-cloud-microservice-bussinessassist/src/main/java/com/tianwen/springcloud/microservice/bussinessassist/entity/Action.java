package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_user_action")
public class Action extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_t_e_user_action_actionid')")
    @Column(name = "actionid")
    @ApiModelProperty("actionid")
    private String actionid;

    @Column(name = "userid")
    @ApiModelProperty("userid")
    private String userid;

    @Column(name = "actiontype")
    @ApiModelProperty("actiontype")
    private String actiontype;

    @Column(name = "contentid")
    @ApiModelProperty("contentid")
    private String contentid;

    @Column(name = "starttime")
    @ApiModelProperty("starttime")
    private Timestamp starttime;

    @Column(name = "endtime")
    @ApiModelProperty("endtime")
    private Timestamp endtime;

    @Column(name = "ipaddress")
    @ApiModelProperty("ipaddress")
    private String ipaddress;

    private long actiontime;

    public String getActionid() {
        return actionid;
    }

    public void setActionid(String actionid) {
        this.actionid = actionid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getActiontype() {
        return actiontype;
    }

    public void setActiontype(String actiontype) {
        this.actiontype = actiontype;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public Timestamp getStarttime() {
        if (starttime == null)
            return null;
        return new Timestamp(starttime.getTime());
    }

    public void setStarttime(Timestamp starttime) {
        if (starttime == null)
            this.starttime = null;
        else
            this.starttime = new Timestamp(starttime.getTime());
    }

    public Timestamp getEndtime() {
        if (endtime == null)
            return null;
        return new Timestamp(endtime.getTime());
    }

    public void setEndtime(Timestamp endtime) {
        if (endtime == null)
            this.endtime = null;
        else
            this.endtime = new Timestamp(endtime.getTime());
    }

    public long getActiontime() {
        return actiontime;
    }

    public void setActiontime(long actiontime) {
        this.actiontime = actiontime;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }
}
