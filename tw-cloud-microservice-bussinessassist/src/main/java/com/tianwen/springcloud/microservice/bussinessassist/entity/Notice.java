package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_notice")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_noticeid_t_e_notice')")
    @Column(name = "noticeid")
    @ApiModelProperty("noticeid")
    private String noticeid;

    @Column(name = "name")
    @ApiModelProperty(value = "name", required = true)
    private String name;

    @Column(name = "content")
    @ApiModelProperty(value = "content", required = true)
    private String content;

    @Column(name = "filepath")
    @ApiModelProperty(value = "filepath")
    private String filepath;

    @Column(name = "userid")
    @ApiModelProperty(value = "userid", required = true)
    private String userid;

    @Column(name = "sharerange")
    @ApiModelProperty(value = "sharerange", required = true)
    private String sharerange;

    @Column(name = "schoolsection")
    @ApiModelProperty(value = "schoolsection", required = true)
    private String schoolsection;

    @Column(name = "subjectid")
    @ApiModelProperty(value = "subjectid", required = true)
    private String subjectid;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    @Column(name = "createtime")
    @ApiModelProperty(value = "createtime", required = true)
    private Timestamp createtime;

    @Column(name = "realname")
    @ApiModelProperty(value = "realname", required = true)
    private String realname;

    public String getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getSharerange() {
        return sharerange;
    }

    public void setSharerange(String sharerange) {
        this.sharerange = sharerange;
    }

    public String getSchoolsection() {
        return schoolsection;
    }

    public void setSchoolsection(String schoolsection) {
        this.schoolsection = schoolsection;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeid='" + noticeid + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", filepath='" + filepath + '\'' +
                ", userid='" + userid + '\'' +
                ", sharerange='" + sharerange + '\'' +
                ", schoolsection='" + schoolsection + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", status='" + status + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
