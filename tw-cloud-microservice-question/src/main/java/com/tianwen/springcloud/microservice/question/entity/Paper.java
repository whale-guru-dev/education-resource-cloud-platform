package com.tianwen.springcloud.microservice.question.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.microservice.question.constant.IQuestionMicroConstants;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_paper")
public class Paper extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('" + IQuestionMicroConstants.prefix + "', 'seq_t_e_paper_paperid')")
    @Column(name = "paperid")
    @ApiModelProperty("试卷ID")
    private String paperid;

    @Column(name = "papercode")
    @ApiModelProperty(value = "试卷编码")
    private String papercode;

    @Column(name = "title")
    @ApiModelProperty(value = "试卷标题")
    private String title;

    @Column(name = "type")
    @ApiModelProperty(value = "测试类型")
    private String type;

    @Column(name = "term")
    @ApiModelProperty(value = "term")
    private String term;

    @Column(name = "totalscore")
    @ApiModelProperty(value = "试卷分值")
    private Double totalscore;

    @Column(name = "paragraphid")
    @ApiModelProperty(value = "学段")
    private String paragraphid;

    @Column(name = "subjectid")
    @ApiModelProperty(value = "学科")
    private String subjectid;

    @Column(name = "editiontypeid")
    @ApiModelProperty(value = "教材版本")
    private String editiontypeid;

    @Column(name = "avgdegree")
    @ApiModelProperty(value = "试卷难度")
    private Double avgdegree;

    @Column(name = "keyword")
    @ApiModelProperty(value = "关键字")
    private String keyword;

    @Column(name = "isbn")
    @ApiModelProperty(value = "isbn")
    private String isbn;

    @Column(name = "sharerange")
    @ApiModelProperty(value = "共享范围")
    private String sharerange;

    @Column(name = "status")
    @ApiModelProperty(value = "状态")
    private String status;

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

    @Column(name = "downtimes")
    @ApiModelProperty(value = "下载次数")
    private Integer downtimes;

    @Column(name = "refertimes")
    @ApiModelProperty(value = "引用次数")
    private Integer refertimes;

    @Column(name = "clicktimes")
    @ApiModelProperty(value = "点赞次数")
    private Integer clicktimes;

    @Column(name = "sourceid")
    @ApiModelProperty(value = "来源ID")
    private String sourceid;

    @Column(name = "orgid")
    @ApiModelProperty(value = "机构ID")
    private String orgid;

    @Column(name = "jsonpath")
    @ApiModelProperty(value = "jeson路径")
    private String jsonpath;

    @Column(name = "wordpath")
    @ApiModelProperty(value = "word路径")
    private String wordpath;

    @Column(name = "lastexporttime")
    @ApiModelProperty(value = "最后导出时间")
    private Timestamp lastexporttime;

    @Column(name = "json_md5")
    @ApiModelProperty(value = "md5值")
    private String json_md5;

    @Column(name = "passscore")
    @ApiModelProperty(value = "及格分")
    private Double passscore;

    @Column(name = "excellentscore")
    @ApiModelProperty(value = "优秀分")
    private Double excellentscore;

    @Column(name = "detail")
    @ApiModelProperty(value = "detail")
    private String detail;

    @Column(name = "publishversion")
    @ApiModelProperty(value = "publishversion")
    private String publishversion;

    @Column(name = "starttime")
    @ApiModelProperty(value = "starttime")
    private Timestamp starttime;

    @Column(name = "endtime")
    @ApiModelProperty(value = "endtime")
    private Timestamp endtime;

    @Column(name = "deluser")
    @ApiModelProperty(value = "deluser")
    private String deluser;

    @Column(name = "deltime")
    @ApiModelProperty(value = "deltime")
    private Timestamp deltime;

    @Column(name = "pmodel")
    @ApiModelProperty(value = "pmodel")
    private String pmodel;

    @Column(name = "source")
    @ApiModelProperty(value = "source")
    private String source;

    @Column(name = "isallowedit")
    @ApiModelProperty(value = "isallowedit")
    private String isallowedit;

    @Column(name = "copyrighttype")
    @ApiModelProperty(value = "copyrighttype")
    private String copyrighttype;

    @Column(name = "lasttime")
    @ApiModelProperty(value = "lasttime")
    private Timestamp lasttime;

    @Column(name = "versionno")
    @ApiModelProperty(value = "版本号")
    private String versionno;

    @Column(name = "grade")
    @ApiModelProperty(value = "年级")
    private Integer grade;

    @Column(name = "usetime")
    @ApiModelProperty(value = "最近使用时间")
    private Integer usetime;

    public String getPaperid() {
        return paperid;
    }

    public void setPaperid(String paperid) {
        this.paperid = paperid;
    }

    public String getPapercode() {
        return papercode;
    }

    public void setPapercode(String papercode) {
        this.papercode = papercode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Double getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Double totalscore) {
        this.totalscore = totalscore;
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

    public Double getAvgdegree() {
        return avgdegree;
    }

    public void setAvgdegree(Double avgdegree) {
        this.avgdegree = avgdegree;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public Integer getClicktimes() {
        return clicktimes;
    }

    public void setClicktimes(Integer clicktimes) {
        this.clicktimes = clicktimes;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getJsonpath() {
        return jsonpath;
    }

    public void setJsonpath(String jsonpath) {
        this.jsonpath = jsonpath;
    }

    public String getWordpath() {
        return wordpath;
    }

    public void setWordpath(String wordpath) {
        this.wordpath = wordpath;
    }

    public Timestamp getLastexporttime() {
        return lastexporttime;
    }

    public void setLastexporttime(Timestamp lastexporttime) {
        this.lastexporttime = lastexporttime;
    }

    public String getJson_md5() {
        return json_md5;
    }

    public void setJson_md5(String json_md5) {
        this.json_md5 = json_md5;
    }

    public Double getPassscore() {
        return passscore;
    }

    public void setPassscore(Double passscore) {
        this.passscore = passscore;
    }

    public Double getExcellentscore() {
        return excellentscore;
    }

    public void setExcellentscore(Double excellentscore) {
        this.excellentscore = excellentscore;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPublishversion() {
        return publishversion;
    }

    public void setPublishversion(String publishversion) {
        this.publishversion = publishversion;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    public String getDeluser() {
        return deluser;
    }

    public void setDeluser(String deluser) {
        this.deluser = deluser;
    }

    public Timestamp getDeltime() {
        return deltime;
    }

    public void setDeltime(Timestamp deltime) {
        this.deltime = deltime;
    }

    public String getPmodel() {
        return pmodel;
    }

    public void setPmodel(String pmodel) {
        this.pmodel = pmodel;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIsallowedit() {
        return isallowedit;
    }

    public void setIsallowedit(String isallowedit) {
        this.isallowedit = isallowedit;
    }

    public String getCopyrighttype() {
        return copyrighttype;
    }

    public void setCopyrighttype(String copyrighttype) {
        this.copyrighttype = copyrighttype;
    }

    public Timestamp getLasttime() {
        return lasttime;
    }

    public void setLasttime(Timestamp lasttime) {
        this.lasttime = lasttime;
    }

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getUsetime() {
        return usetime;
    }

    public void setUsetime(Integer usetime) {
        this.usetime = usetime;
    }
}
