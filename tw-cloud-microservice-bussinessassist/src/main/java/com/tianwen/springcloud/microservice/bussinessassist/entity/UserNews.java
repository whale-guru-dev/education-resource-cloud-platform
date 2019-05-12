package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_user_news")
public class UserNews extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_newsid_t_e_user_news')")
    @Column(name = "newsid")
    @ApiModelProperty("newsid")
    private String newsid;

    @Column(name = "userid")
    @ApiModelProperty(value = "userid", required = true)
    private String userid;

    @Column(name = "realName")
    @ApiModelProperty(value = "realName", required = true)
    private String realName;

    @Column(name = "newsname")
    @ApiModelProperty(value = "newsname", required = true)
    private String newsname;

    @Column(name = "newscontent")
    @ApiModelProperty(value = "newscontent", required = true)
    private String newscontent;

    @Column(name = "receivetime")
    @ApiModelProperty(value = "receivetime", required = true)
    private Timestamp receivetime;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNewsname() {
        return newsname;
    }

    public void setNewsname(String newsname) {
        this.newsname = newsname;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public Timestamp getReceivetime() {
        if (receivetime == null)
            return null;
        return new Timestamp(receivetime.getTime());
    }

    public void setReceivetime(Timestamp receivetime) {
        if (receivetime == null)
            this.receivetime = null;
        else
            this.receivetime = new Timestamp(receivetime.getTime());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }
}