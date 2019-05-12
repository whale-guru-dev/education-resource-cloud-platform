package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_question")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator ="SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_question_questionid')")
    @Column(name = "questionid")
    @ApiModelProperty("试题ID")
    private String questionid;

    @Column(name = "questioncode")
    @ApiModelProperty(value = "试题编码")
    private String questioncode;

    @Column(name = "typelevel")
    @ApiModelProperty(value = "主题型")
    private String typelevel;

    @Column(name = "typelevellabel")
    @ApiModelProperty(value = "副题型")
    private String typelevellabel;

    @Column(name = "title")
    @ApiModelProperty(value = "试题正文")
    private String title;

    @Column(name = "note")
    @ApiModelProperty(value = "解析")
    private String note;

    @Column(name = "defaultscore")
    @ApiModelProperty(value = "题分")
    private Double defaultscore;

    @Column(name = "paragraphid")
    @ApiModelProperty(value = "学段")
    private String paragraphid;

    @Column(name = "subjectid")
    @ApiModelProperty(value = "学科")
    private String subjectid;

    @Column(name = "editiontypeid")
    @ApiModelProperty(value = "教材版本")
    private String editiontypeid;

    @Column(name = "grade")
    @ApiModelProperty(value = "年级")
    private String grade;

    @Column(name = "bookmodel")
    @ApiModelProperty(value = "册别")
    private String bookmodel;

    @Column(name = "degree")
    @ApiModelProperty(value = "难度")
    private Double degree;

    @Column(name = "sourceid")
    @ApiModelProperty(value = "来源ID")
    private String sourceid;

    @Column(name = "reslevelid")
    @ApiModelProperty(value = "属性/标签/级别")
    private String reslevelid;

    @Column(name = "keyword")
    @ApiModelProperty(value = "关键字")
    private String keyword;

    @Column(name = "sharerange")
    @ApiModelProperty(value = "共享范围")
    private String sharerange;

    @Column(name = "version")
    @ApiModelProperty(value = "版本号")
    private Double version;

    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private String status;

    @Column(name = "score")
    @ApiModelProperty(value = "积分值")
    private Integer score;

    @Column(name = "creator")
    @ApiModelProperty(value = "上传人")
    private String creator;

    @Column(name = "createtime")
    @ApiModelProperty(value = "上传时间")
    private Timestamp createtime;

    @Column(name = "lastmodifier")
    @ApiModelProperty(value = "修订人")
    private String lastmodifier;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "修订时间")
    private Timestamp lastmodifytime;

    @Column(name = "viewtimes")
    @ApiModelProperty(value = "查看次数")
    private Integer viewtimes;

    @Column(name = "downtimes")
    @ApiModelProperty(value = "下载次数")
    private Integer downtimes;

    @Column(name = "refertimes")
    @ApiModelProperty(value = "组卷次数")
    private Integer refertimes;

    @Column(name = "collectiontimes")
    @ApiModelProperty(value = "收藏次数")
    private Integer collectiontimes;

    @Column(name = "clicktimes")
    @ApiModelProperty(value = "点赞次数")
    private Integer clicktimes;

    @Column(name = "usetime")
    @ApiModelProperty(value = "最近使用时间")
    private Timestamp usetime;

    @Column(name = "numprow")
    @ApiModelProperty(value = "显示个数")
    private Integer numprow;

    @Column(name = "rowlable")
    @ApiModelProperty(value = "序号样式")
    private String rowlable;

    @Column(name = "isautomark")
    @ApiModelProperty(value = "自动批改标识")
    private String isautomark;

    @Column(name = "lrstatus")
    @ApiModelProperty(value = "跟读题生成状态")
    private String lrstatus;

    @Column(name = "cyclenum")
    @ApiModelProperty(value = "播放次数")
    private Integer cyclenum;

    @Column(name = "sequencerelated")
    @ApiModelProperty(value = "答案顺序")
    private String sequencerelated;

    @Column(name = "orgid")
    @ApiModelProperty(value = "机构ID")
    private String orgid;
    
    @Column(name = "item")
    @ApiModelProperty(value = "")
    private String item;

    @Column(name = "isitemrandom")
    @ApiModelProperty(value = "isitemrandom")
    private String isitemrandom;

    @Column(name = "labelid")
    @ApiModelProperty(value = "labelid")
    private String labelid;

    @Column(name = "itemempty")
    @ApiModelProperty(value = "itemempty")
    private String itemempty;

    @Column(name = "subjectability")
    @ApiModelProperty(value = "subjectability")
    private String subjectability;

    @Column(name = "fitobject")
    @ApiModelProperty(value = "fitobject")
    private String fitobject;

    @Column(name = "answer")
    @ApiModelProperty(value = "answer")
    private String answer;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestioncode() {
        return questioncode;
    }

    public void setQuestioncode(String questioncode) {
        this.questioncode = questioncode;
    }

    public String getTypelevel() {
        return typelevel;
    }

    public void setTypelevel(String typelevel) {
        this.typelevel = typelevel;
    }

    public String getTypelevellabel() {
        return typelevellabel;
    }

    public void setTypelevellabel(String typelevellabel) {
        this.typelevellabel = typelevellabel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraphid() {
        return paragraphid;
    }

    public void setParagraphid(String paragraphid) {
        this.paragraphid = paragraphid;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getEditiontypeid() {
        return editiontypeid;
    }

    public void setEditiontypeid(String editiontypeid) {
        this.editiontypeid = editiontypeid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getBookmodel() {
        return bookmodel;
    }

    public void setBookmodel(String bookmodel) {
        this.bookmodel = bookmodel;
    }

    public Double getDegree() {
        return degree;
    }

    public void setDegree(Double degree) {
        this.degree = degree;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getReslevelid() {
        return reslevelid;
    }

    public void setReslevelid(String reslevelid) {
        this.reslevelid = reslevelid;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSharerange() {
        return sharerange;
    }

    public void setSharerange(String sharerange) {
        this.sharerange = sharerange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getLastmodifier() {
        return lastmodifier;
    }

    public void setLastmodifier(String lastmodifier) {
        this.lastmodifier = lastmodifier;
    }

    public Timestamp getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Timestamp lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public Integer getViewtimes() {
        return viewtimes;
    }

    public void setViewtimes(Integer viewtimes) {
        this.viewtimes = viewtimes;
    }

    public Integer getDowntimes() {
        return downtimes;
    }

    public void setDowntimes(Integer downtimes) {
        this.downtimes = downtimes;
    }

    public Integer getRefertimes() {
        return refertimes;
    }

    public void setRefertimes(Integer refertimes) {
        this.refertimes = refertimes;
    }

    public Integer getCollectiontimes() {
        return collectiontimes;
    }

    public void setCollectiontimes(Integer collectiontimes) {
        this.collectiontimes = collectiontimes;
    }

    public Integer getClicktimes() {
        return clicktimes;
    }

    public void setClicktimes(Integer clicktimes) {
        this.clicktimes = clicktimes;
    }

    public Timestamp getUsetime() {
        return usetime;
    }

    public void setUsetime(Timestamp usetime) {
        this.usetime = usetime;
    }

    public Integer getNumprow() {
        return numprow;
    }

    public void setNumprow(Integer numprow) {
        this.numprow = numprow;
    }

    public String getRowlable() {
        return rowlable;
    }

    public void setRowlable(String rowlable) {
        this.rowlable = rowlable;
    }

    public String getIsautomark() {
        return isautomark;
    }

    public void setIsautomark(String isautomark) {
        this.isautomark = isautomark;
    }

    public String getLrstatus() {
        return lrstatus;
    }

    public void setLrstatus(String lrstatus) {
        this.lrstatus = lrstatus;
    }

    public Integer getCyclenum() {
        return cyclenum;
    }

    public void setCyclenum(Integer cyclenum) {
        this.cyclenum = cyclenum;
    }

    public String getSequencerelated() {
        return sequencerelated;
    }

    public void setSequencerelated(String sequencerelated) {
        this.sequencerelated = sequencerelated;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getDefaultscore() {
        return defaultscore;
    }

    public void setDefaultscore(Double defaultscore) {
        this.defaultscore = defaultscore;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getIsitemrandom() {
        return isitemrandom;
    }

    public void setIsitemrandom(String isitemrandom) {
        this.isitemrandom = isitemrandom;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }

    public String getItemempty() {
        return itemempty;
    }

    public void setItemempty(String itemempty) {
        this.itemempty = itemempty;
    }

    public String getSubjectability() {
        return subjectability;
    }

    public void setSubjectability(String subjectability) {
        this.subjectability = subjectability;
    }

    public String getFitobject() {
        return fitobject;
    }

    public void setFitobject(String fitobject) {
        this.fitobject = fitobject;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
