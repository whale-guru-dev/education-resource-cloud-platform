package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_user_news")
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_newsid_t_e_user_news')")
    @Column(name = "newsid")
    @ApiModelProperty("newsid")
    private String newsid;

    @Column(name = "userid")
    @ApiModelProperty(value = "userid", required = true)
    private String userid;

    @Column(name = "realname")
    @ApiModelProperty(value = "realname", required = true)
    private String realname;

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

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "Message{" +
                "newsid='" + newsid + '\'' +
                ", userid='" + userid + '\'' +
                ", realname='" + realname + '\'' +
                ", newsname='" + newsname + '\'' +
                ", newscontent='" + newscontent + '\'' +
                ", receivetime=" + receivetime +
                ", status='" + status + '\'' +
                '}';
    }
}
