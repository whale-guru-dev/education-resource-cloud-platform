package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_question_level")
public class Level extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_paper_level_levelid')")
    @Column(name = "levelid")
    @ApiModelProperty("分层ID")
    private String levelid;

    @Column(name = "levelname")
    @ApiModelProperty(value = "分层名称", required = true)
    private String levelname;

    @Column(name = "lorder")
    @ApiModelProperty(value = "分层顺序", required = true)
    private Integer lorder;

    @Column(name = "paperid")
    @ApiModelProperty(value = "试卷ID", required = true)
    private String paperid;

    @Column(name = "accuracy")
    @ApiModelProperty(value = "客观题目标正确率")
    private Double accuracy;

    @Column(name = "responserate")
    @ApiModelProperty(value = "主观题目标作答率")
    private Double responserate;

    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间", required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "最后更新时间", required = true)
    private Timestamp lastmodifytime;

    public String getLevelid() {
        return levelid;
    }

    public void setLevelid(String levelid) {
        this.levelid = levelid;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public Integer getLorder() {
        return lorder;
    }

    public void setLorder(Integer lorder) {
        this.lorder = lorder;
    }

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Double getResponserate() {
        return responserate;
    }

    public void setResponserate(Double responserate) {
        this.responserate = responserate;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Timestamp lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }
}
