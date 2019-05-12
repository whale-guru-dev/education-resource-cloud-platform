package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_resource_vote")
public class ResourceVote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_voteid_t_e_resource_vote')")
    @Column(name = "voteid")
    @ApiModelProperty("voteid")
    private String voteid;

    @Column(name = "resourceid")
    @ApiModelProperty(value = "resourceid", required = true)
    private String resourceid;

    @Column(name = "userid")
    @ApiModelProperty(value = "userid", required = true)
    private String userid;

    @Column(name = "votetime")
    @ApiModelProperty(value = "votetime", required = true)
    private Timestamp votetime;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    public String getVoteid() {
        return voteid;
    }

    public void setVoteid(String voteid) {
        this.voteid = voteid;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Timestamp getVotetime() {
        if (votetime == null)
            return null;
        return new Timestamp(votetime.getTime());
    }

    public void setVotetime(Timestamp votetime) {
        if (votetime == null)
            this.votetime = null;
        else
            this.votetime = new Timestamp(votetime.getTime());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
