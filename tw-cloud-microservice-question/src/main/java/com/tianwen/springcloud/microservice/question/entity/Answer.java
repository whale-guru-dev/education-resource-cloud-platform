package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_question_answer")
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_question_answer_answerid')")
    @Column(name = "answerid")
    @ApiModelProperty("答案ID")
    private String answerid;

    @Column(name = "questionid")
    @ApiModelProperty(value = "试题ID",required = true)
    private String questionid;

    @Column(name = "childid")
    @ApiModelProperty(value = "子试题ID")
    private String childid;

    @Column(name = "sequenceno")
    @ApiModelProperty(value = "答案序号", required = true)
    private String sequenceno;

    @Column(name = "content")
    @ApiModelProperty(value = "答案具体内容", required = true)
    private String content;

    @Column(name = "score")
    @ApiModelProperty(value = "该答案所占积分值")
    private Integer score;

    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间", required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "最后更新时间", required = true)
    private Timestamp lastmodifytime;

    public String getAnswerid() {
        return answerid;
    }

    public void setAnswerid(String answerid) {
        this.answerid = answerid;
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

    public String getSequenceno() {
        return sequenceno;
    }

    public void setSequenceno(String sequenceno) {
        this.sequenceno = sequenceno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
