package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_question_item")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_question_item_itemid')")
    @Column(name = "itemid")
    @ApiModelProperty("选项ID")
    private String itemid;

    @Column(name = "questionid")
    @ApiModelProperty(value = "试题ID", required = true)
    private String questionid;

    @Column(name = "childid")
    @ApiModelProperty("子试题ID")
    private String childid;

    @Column(name = "content")
    @ApiModelProperty(value = "选项具体内容", required = true)
    private String content;

    @Column(name = "sequenceno")
    @ApiModelProperty(value = "选项序号", required = true)
    private String sequenceno;

    @Column(name = "colno")
    @ApiModelProperty("试题选项列序号")
    private String colno;

    @Column(name = "width")
    @ApiModelProperty("连线题选项宽")
    private Integer width;

    @Column(name = "height")
    @ApiModelProperty("连线题选项高")
    private Integer height;

    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间", required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "最后修改时间", required = true)
    private Timestamp lastmodifytime;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSequenceno() {
        return sequenceno;
    }

    public void setSequenceno(String sequenceno) {
        this.sequenceno = sequenceno;
    }

    public String getColno() {
        return colno;
    }

    public void setColno(String colno) {
        this.colno = colno;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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
