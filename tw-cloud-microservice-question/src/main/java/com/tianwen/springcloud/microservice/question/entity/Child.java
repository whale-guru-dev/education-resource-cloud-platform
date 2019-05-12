package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_question_child")
public class Child extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_question_child_childid')")
    @Column(name = "childid")
    @ApiModelProperty("子试题编号")
    private String childid;

    @Column(name = "questionid")
    @ApiModelProperty(value = "试题ID", required = true)
    private String questionid;

    @Column(name = "typelevel")
    @ApiModelProperty(value = "试题类型", required = true)
    private String typelevel;

    @Column(name = "title")
    @ApiModelProperty(value = "试题标题", required = true)
    private String title;

    @Column(name = "note")
    @ApiModelProperty(value = "试题解析", required = true)
    private String note;

    @Column(name = "defaultscore")
    @ApiModelProperty(value = "默认分数")
    private Integer defaultscore;

    @Column(name = "numprow")
    @ApiModelProperty(value = "显示个数", required = true)
    private String numprow;

    @Column(name = "rowlable")
    @ApiModelProperty(value = "显示序号", required = true)
    private String rowlable;

    @Column(name = "isitemrandom")
    @ApiModelProperty(name = "选项排列方式", required = true)
    private String isitemrandom;

    @Column(name = "isautomark")
    @ApiModelProperty(value = "自动批改标识", required = true)
    private String isautomark;

    @Column(name = "labelid")
    @ApiModelProperty(value = "副题型ID")
    private String labelid;

    @Column(name = "typelevellabel")
    @ApiModelProperty(value = "副题型")
    private String typelevellabel;

    @Column(name = "sequencerelated")
    @ApiModelProperty(value = "答案顺序", required = true)
    private String sequencerelated;

    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间", required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "最后修改时间", required = true)
    private Timestamp lastmodifytime;

    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getTypelevel() {
        return typelevel;
    }

    public void setTypelevel(String typelevel) {
        this.typelevel = typelevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getDefaultscore() {
        return defaultscore;
    }

    public void setDefaultscore(Integer defaultscore) {
        this.defaultscore = defaultscore;
    }

    public String getNumprow() {
        return numprow;
    }

    public void setNumprow(String numprow) {
        this.numprow = numprow;
    }

    public String getRowlable() {
        return rowlable;
    }

    public void setRowlable(String rowlable) {
        this.rowlable = rowlable;
    }

    public String getIsitemrandom() {
        return isitemrandom;
    }

    public void setIsitemrandom(String isitemrandom) {
        this.isitemrandom = isitemrandom;
    }

    public String getIsautomark() {
        return isautomark;
    }

    public void setIsautomark(String isautomark) {
        this.isautomark = isautomark;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    public String getTypelevellabel() {
        return typelevellabel;
    }

    public void setTypelevellabel(String typelevellabel) {
        this.typelevellabel = typelevellabel;
    }

    public String getSequencerelated() {
        return sequencerelated;
    }

    public void setSequence_related(String sequence_related) {
        this.sequencerelated = sequence_related;
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
