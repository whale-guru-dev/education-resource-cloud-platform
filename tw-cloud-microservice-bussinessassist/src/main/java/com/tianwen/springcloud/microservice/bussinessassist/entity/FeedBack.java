package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_content_feedback")
public class FeedBack extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_feedbackid_t_e_content_feedback')")
    @Column(name = "feedbackid")
    @ApiModelProperty("feedbackid")
    private String feedbackid;

    @Column(name = "objectid")
    @ApiModelProperty(value = "objectid", required = true)
    private String objectid;

    @Column(name = "objecttype")
    @ApiModelProperty(value = "objecttype", required = true)
    private String objecttype;

    @Column(name = "status")
    @ApiModelProperty(value = "status", required = true)
    private String status;

    @Column(name = "audituser")
    @ApiModelProperty(value = "audituser")
    private String audituser;

    @Column(name = "audittime")
    @ApiModelProperty(value = "audittime")
    private Timestamp audittime;

    @Column(name = "score")
    @ApiModelProperty("score")
    private int score;

    @Column(name = "type")
    @ApiModelProperty(value = "type", required = true)
    private String type;

    @Column(name = "subtype")
    @ApiModelProperty("subtype")
    private String subtype;

    @Column(name = "remark")
    @ApiModelProperty("remark")
    private String remark;

    @Column(name = "replycontent")
    @ApiModelProperty("replycontent")
    private String replycontent;

    @Column(name = "username")
    @ApiModelProperty(value = "username", required = true)
    private String username;

    @Column(name = "userphone")
    @ApiModelProperty("userphone")
    private String userphone;

    @Column(name = "userqq")
    @ApiModelProperty("userqq")
    private String userqq;

    @Column(name = "useremail")
    @ApiModelProperty("useremail")
    private String useremail;

    @Column(name = "createtime")
    @ApiModelProperty(value = "createtime", required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "lastmodifytime", required = true)
    private Timestamp lastmodifytime;

    @Column(name = "isscored")
    @ApiModelProperty(value = "isscored")
    private String isscored;

    @Transient
    private Screenshot screenshot;

    private Object resource;

    private String school;

    private String district;

    private String city;

    private String castle;

    public String getIsscored() {
        return isscored;
    }

    public void setIsscored(String isscored) {
        this.isscored = isscored;
    }

    public String getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(String feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAudituser() {
        return audituser;
    }

    public void setAudituser(String audituser) {
        this.audituser = audituser;
    }

    public Timestamp getAudittime() {
        if (audittime == null)
            return null;
        return new Timestamp(audittime.getTime());
    }

    public void setAudittime(Timestamp audittime) {
        if (audittime == null)
            this.audittime = null;
        else
            this.audittime = new Timestamp(audittime.getTime());
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Timestamp getLastmodifytime() {
        if (lastmodifytime == null)
            return null;
        return new Timestamp(lastmodifytime.getTime());
    }

    public void setLastmodifytime(Timestamp lastmodifytime) {
        if (lastmodifytime == null)
            this.lastmodifytime = null;
        else
            this.lastmodifytime = new Timestamp(lastmodifytime.getTime());
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getUserqq() {
        return userqq;
    }

    public void setUserqq(String userqq) {
        this.userqq = userqq;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Screenshot getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(Screenshot screenshot) {
        this.screenshot = screenshot;
    }

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCastle() {
        return castle;
    }

    public void setCastle(String castle) {
        this.castle = castle;
    }
}
