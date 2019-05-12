package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "t_e_content")
public class Resource extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_contentid_t_e_content')")
    @Column(name = "contentid")
    private String contentid;

    @Column(name = "contentno")
    @ApiModelProperty("资源编码")
    private String contentno;

    @Column(name = "name")
    @ApiModelProperty(value = "标题", required = true)
    private String name;

    @Column(name = "schoolsection")
    @ApiModelProperty(value="小学,初中,高中")
    private String schoolsectionid;

    @Transient
    private String schoolsection;

    @Column(name = "subjectid")
    @ApiModelProperty(value="学科")
    private String subjectid;

    @Transient
    private String subject;

    @Column(name = "editiontypeid")
    @ApiModelProperty(value="版本")
    private String editiontypeid;

    @Transient
    private String editiontype;

    @Column(name = "grade")
    @ApiModelProperty(value="年级")
    private String gradeid;

    @Transient
    private String grade;

    @Column(name = "bookmodel")
    @ApiModelProperty("册（模块）")
    private String bookmodelid;

    @Transient
    private String bookmodel;

    @Transient
    private String bookname;

    @Column(name = "chapter")
    @ApiModelProperty("单元")
    private String chapter;

    @Column(name = "section")
    @ApiModelProperty("课")
    private String section;

    @Column(name = "knowledgepoint")
    @ApiModelProperty("知识点")
    private String knowledgepoint;

    @Column(name = "contenttype")
    @ApiModelProperty(value="资源类别", required = true)
    private String contenttype;

    @Column(name = "onelabel")
    @ApiModelProperty("一级标签ID")
    private String onelabelid;

    @Column(name = "twolabel")
    @ApiModelProperty("二级标签ID")
    private String twolabelid;

    @Column(name = "threelabel")
    @ApiModelProperty("三级标签ID")
    private String threelabelid;

    @Column(name = "isanonymity")
    @ApiModelProperty(required = true)
    private String isanonymity;

    @Transient
    private String onelabel;

    @Transient
    private String twolabel;

    @Transient
    private String threelabel;

    @Column(name = "searchlabel")
    @ApiModelProperty("搜索标签ID集")
    private String searchlabel;

    @Column(name = "oneschool")
    @ApiModelProperty("名校一级")
    private String oneschool;

    @Column(name = "twoschool")
    @ApiModelProperty("名校二级")
    private String twoschool;

    @Column(name = "fitobject")
    @ApiModelProperty(value = "适用对象", allowableValues = "1,2,3")
    private String fitobject;

    @Column(name = "keyword")
    @ApiModelProperty("关键词")
    private String keyword;

    @Column(name = "description")
    @ApiModelProperty("资源描述")
    private String description;

    @Column(name = "sourceid")
    @ApiModelProperty(value="来源", allowableValues = "1,2,3,4,5")
    private String sourceid;

    @Column(name = "sharerange")
    @ApiModelProperty(value="共享范围", allowableValues = "1,2,3,4,5")
    private String sharerange;

    @Column(name = "creator")
    @ApiModelProperty("上传者")
    private String creator;

    @Column(name = "createtime")
    @ApiModelProperty("上传时间")
    private Timestamp createtime;

    @Column(name = "lastmodifier")
    @ApiModelProperty("修订人")
    private String lastmodifier;

    @Column(name = "lastupdatetime")
    @ApiModelProperty("修订时间")
    private Timestamp lastupdatetime;

    @Column(name = "endtime")
    @ApiModelProperty("归档时间")
    private Timestamp endtime;

    @Column(name = "status")
    @ApiModelProperty(value="资源状态", allowableValues = "0,1,3,4,7,8,9")
    private String status;

    @Column(name = "score")
    @ApiModelProperty("积分值")
    private Integer score;

    @Column(name = "version")
    @ApiModelProperty("资源版本")
    private String version;

    @Column(name = "viewtimes")
    @ApiModelProperty("查看次数")
    private Integer viewtimes;

    @Column(name = "downtimes")
    @ApiModelProperty("下载次数")
    private Integer downtimes;

    @Column(name = "collectiontimes")
    @ApiModelProperty("收藏次数")
    private Integer collectiontimes;

    @Column(name = "clicktimes")
    @ApiModelProperty("点赞次数")
    private Integer clicktimes;

    @Column(name = "usetime")
    @ApiModelProperty("最后一次使用时间")
    private Timestamp usetime;

    @Column(name = "reslevelid")
    @ApiModelProperty("级别")
    private String reslevelid;

    @Column(name = "encryptstatus")
    @ApiModelProperty(value = "加密状态", allowableValues = "1,2,3,4,5")
    private String encryptstatus;

    @Column(name = "contentkey")
    @ApiModelProperty("密钥")
    private String contentkey;

    @Column(name = "epubtype")
    @ApiModelProperty(value = "Epub类型", allowableValues = "0,1,2,3")
    private String epubtype;

    @Column(name = "isallowdownload")
    @ApiModelProperty(value="是否允许下载", allowableValues = "0,1")
    private String isallowdownload;

    @Column(name = "importstatus")
    @ApiModelProperty(value="导入状态")
    private String importstatus;

    @Column(name = "remarks")
    @ApiModelProperty(value="失败原因")
    private String remarks;

    @Column(name = "isgoods")
    private String isgoods;

    @Column(name = "filepath")
    private String filepath;

    //file
    @Transient
    @ApiModelProperty(value = "fileInfo")
    private FileInfo fileInfo;

    //rating
    @Column(name = "ratesum")
    private Float ratesum;

    @Transient
    private int ratecount;

    @Column(name = "hotvalue")
    @ApiModelProperty("hotvalue")
    private int hotvalue;

    @Column(name = "isanswer")
    @ApiModelProperty("")
    private String isanswer;

    @Column(name = "sharerangekey")
    @ApiModelProperty("")
    private String sharerangekey;

    @Column(name = "thumbnailpath")
    @ApiModelProperty("")
    private String thumbnailpath;

    @Transient
    private String creatorname;

    @Transient
    private int isfavourite;

    @Transient
    private int isnew;

    @Transient
    private int isvote;

    @Transient
    private int rating;

    @Transient
    private int isbasket;

    @Transient
    private int basketable;

    @Transient
    private int downloadable;

    private Object auditinfo;

    private Object goodinfo;

    private Integer collectscore;

    private List<String> catalogids;

    private Timestamp downTime;

    private List<Object> subnaviInfos;

    private String activitystatus;

    private String activityid;

    //author:han
    private String activityname;
    //end han

    private String collectid;

    public String getIsanswer() {
        return isanswer;
    }

    public void setIsanswer(String isanswer) {
        this.isanswer = isanswer;
    }

    public String getActivityid() {
        return activityid;
    }

    public void setActivityid(String activityid) {
        this.activityid = activityid;
    }

    //author:han
    public String getActivityName() {
        return activityname;
    }

    public void setActivityName(String activityname) {
        this.activityname = activityname;
    }
    //end han

    public int getIsbasket() {
        return isbasket;
    }

    public void setIsbasket(int isbasket) {
        this.isbasket = isbasket;
    }

    public int getIsfavourite() {
        return isfavourite;
    }

    public void setIsfavourite(int isfavourite) {
        this.isfavourite = isfavourite;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public String getIsgoods() {
        return isgoods;
    }

    public void setIsgoods(String isgoods) {
        this.isgoods = isgoods;
    }

    public void setCatalogids(List<String> catalogids) {
        this.catalogids = catalogids;
    }

    public List<String> getCatalogids() {
        return catalogids;
    }

    public String getContentid() { return contentid; }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getContentno() {
        return contentno;
    }

    public void setContentno(String contentno) {
        this.contentno = contentno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getKnowledgepoint() {
        return knowledgepoint;
    }

    public void setKnowledgepoint(String knowledgepoint) {
        this.knowledgepoint = knowledgepoint;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getOnelabel() {
        return onelabel;
    }

    public void setOnelabel(String onelabel) {
        this.onelabel = onelabel;
    }

    public String getTwolabel() {
        return twolabel;
    }

    public void setTwolabel(String twolabel) {
        this.twolabel = twolabel;
    }

    public String getSearchlabel() {
        return searchlabel;
    }

    public void setSearchlabel(String searchlabel) {
        this.searchlabel = searchlabel;
    }

    public String getOneschool() {
        return oneschool;
    }

    public void setOneschool(String oneschool) {
        this.oneschool = oneschool;
    }

    public String getTwoschool() {
        return twoschool;
    }

    public void setTwoschool(String twoschool) {
        this.twoschool = twoschool;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFitobject() {
        return fitobject;
    }

    public void setFitobject(String fitobject) {
        this.fitobject = fitobject;
    }

    public String getSourceid() {
        return sourceid;
    }

    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
    }

    public String getSharerange() {
        return sharerange;
    }

    public void setSharerange(String sharerange) {
        this.sharerange = sharerange;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLastmodifier() {
        return lastmodifier;
    }

    public void setLastmodifier(String lastmodifier) {
        this.lastmodifier = lastmodifier;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getViewtimes() {
        return viewtimes==null?0:viewtimes;
    }

    public void setViewtimes(Integer viewtimes) {
        this.viewtimes = viewtimes;
    }
    //Author : GOD 2019-2-14 Bug ID:#647
    public Integer getDowntimes() {
        return downtimes==null?0:downtimes;
    }

    public void setDowntimes(Integer downtimes) {
        this.downtimes = downtimes;
    }

    public Integer getCollectiontimes() {
        return collectiontimes==null?0:collectiontimes;
    }

    public void setCollectiontimes(Integer collectiontimes) {
        this.collectiontimes = collectiontimes;
    }

    public Integer getClicktimes() {
        return clicktimes==null?0:clicktimes;
    }
    //Author : GOD 2019-2-14 Bug ID:#647
    public void setClicktimes(Integer clicktimes) {
        this.clicktimes = clicktimes;
    }

    public String getReslevelid() {
        return reslevelid;
    }

    public void setReslevelid(String reslevelid) {
        this.reslevelid = reslevelid;
    }

    public String getEncryptstatus() {
        return encryptstatus;
    }

    public void setEncryptstatus(String encryptstatus) {
        this.encryptstatus = encryptstatus;
    }

    public String getContentkey() {
        return contentkey;
    }

    public void setContentkey(String contentkey) {
        this.contentkey = contentkey;
    }

    public String getEpubtype() {
        return epubtype;
    }

    public void setEpubtype(String epubtype) {
        this.epubtype = epubtype;
    }

    public String getIsallowdownload() {
        return isallowdownload;
    }

    public void setIsallowdownload(String isallowdownload) {
        this.isallowdownload = isallowdownload;
    }

    public Float getRatesum() {
        return ratesum;
    }

    public void setRatesum(Float ratesum) {
        this.ratesum = ratesum;
    }

    public int getRatecount() {
        return ratecount;
    }

    public void setRatecount(int ratecount) {
        this.ratecount = ratecount;
    }

    public String getCreatorname() {
        return creatorname;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Timestamp getCreatetime() {
        if (createtime == null)
            return null;
        return new Timestamp(createtime.getTime());
    }

    public void setCreatetime(Timestamp createtime) {
        if (createtime != null)
            this.createtime = new Timestamp(createtime.getTime());
    }

    public Timestamp getLastupdatetime() {
        if (lastupdatetime == null)
            return null;
        return new Timestamp(lastupdatetime.getTime());
    }

    public void setLastupdatetime(Timestamp lastupdatetime) {
        if (lastupdatetime == null)
            this.lastupdatetime = new Timestamp(System.currentTimeMillis());
        else
            this.lastupdatetime = new Timestamp(lastupdatetime.getTime());
    }

    public Timestamp getEndtime() {
        if (endtime == null)
            return null;
        return new Timestamp(endtime.getTime());
    }

    public void setEndtime(Timestamp endtime) {
        if (endtime == null)
            this.endtime = null;
        else
            this.endtime = new Timestamp(endtime.getTime());
    }

    public Timestamp getUsetime() {
        if (usetime == null)
            return null;
        return new Timestamp(usetime.getTime());
    }

    public void setUsetime(Timestamp usetime) {
        if (usetime == null)
            this.usetime = null;
        else
            this.usetime = new Timestamp(usetime.getTime());
    }

    public String getSchoolsectionid() {
        return schoolsectionid;
    }

    public void setSchoolsectionid(String schoolsectionid) {
        this.schoolsectionid = schoolsectionid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEditiontype() {
        return editiontype;
    }

    public void setEditiontype(String editiontype) {
        this.editiontype = editiontype;
    }

    public String getGradeid() {
        return gradeid;
    }

    public void setGradeid(String gradeid) {
        this.gradeid = gradeid;
    }

    public String getBookmodelid() {
        return bookmodelid;
    }

    public void setBookmodelid(String bookmodelid) {
        this.bookmodelid = bookmodelid;
    }

    public String getOnelabelid() {
        return onelabelid;
    }

    public void setOnelabelid(String onelabelid) {
        this.onelabelid = onelabelid;
    }

    public String getTwolabelid() {
        return twolabelid;
    }

    public void setTwolabelid(String twolabelid) {
        this.twolabelid = twolabelid;
    }

    public int getHotvalue() {
        return hotvalue;
    }

    public void setHotvalue(int hotvalue) {
        this.hotvalue = hotvalue;
    }

    public String getThreelabelid() {
        return threelabelid;
    }

    public void setThreelabelid(String threelabelid) {
        this.threelabelid = threelabelid;
    }

    public String getThreelabel() {
        return threelabel;
    }

    public void setThreelabel(String threelabel) {
        this.threelabel = threelabel;
    }

    public Integer getCollectscore() {
        return collectscore=20;
    }

    public void setCollectscore(Integer collectscore) {
        this.collectscore = collectscore;
    }

    public Timestamp getDownTime() {
        if (downTime == null)
            return null;
        return new Timestamp(downTime.getTime());
    }

    public void setDownTime(Timestamp downTime) {
        if (downTime == null)
            this.downTime = null;
        else
            this.downTime = new Timestamp(downTime.getTime());
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Object getAuditinfo() {
        return auditinfo;
    }

    public void setAuditinfo(Object auditinfo) {
        this.auditinfo = auditinfo;
    }

    public Object getGoodinfo() {
        return goodinfo;
    }

    public void setGoodinfo(Object goodinfo) {
        this.goodinfo = goodinfo;
    }

    public List<Object> getSubnaviInfos() {
        return subnaviInfos;
    }

    public void setSubnaviInfos(List<Object> subnaviInfos) {
        this.subnaviInfos = subnaviInfos;
    }

    public String getIsanonymity() {
        return isanonymity;
    }

    public void setIsanonymity(String isanonymity) {
        this.isanonymity = isanonymity;
    }

    public String getActivitystatus() {
        return activitystatus;
    }

    public void setActivitystatus(String activitystatus) {
        this.activitystatus = activitystatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getImportstatus() {
        return importstatus;
    }

    public void setImportstatus(String importstatus) {
        this.importstatus = importstatus;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getIsvote() {
        return isvote;
    }

    public void setIsvote(int isvote) {
        this.isvote = isvote;
    }

    public String getThumbnailpath() {
        return thumbnailpath;
    }

    public void setThumbnailpath(String thumbnailpath) {
        this.thumbnailpath = thumbnailpath;
    }

    public String getSharerangekey() {
        return sharerangekey;
    }

    public void setSharerangekey(String sharerangekey) {
        this.sharerangekey = sharerangekey;
    }

    public int getBasketable() {
        return basketable;
    }

    public void setBasketable(int basketable) {
        this.basketable = basketable;
    }

    public int getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(int downloadable) {
        this.downloadable = downloadable;
    }

    public String getCollectid() {
        return collectid;
    }

    public void setCollectid(String collectid) {
        this.collectid = collectid;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "contentid='" + contentid + '\'' +
                ", contentno='" + contentno + '\'' +
                ", name='" + name + '\'' +
                ", schoolsectionid='" + schoolsectionid + '\'' +
                ", schoolsection='" + schoolsection + '\'' +
                ", subjectid='" + subjectid + '\'' +
                ", subject='" + subject + '\'' +
                ", editiontypeid='" + editiontypeid + '\'' +
                ", editiontype='" + editiontype + '\'' +
                ", gradeid='" + gradeid + '\'' +
                ", grade='" + grade + '\'' +
                ", bookmodelid='" + bookmodelid + '\'' +
                ", bookmodel='" + bookmodel + '\'' +
                ", chapter='" + chapter + '\'' +
                ", section='" + section + '\'' +
                ", knowledgepoint='" + knowledgepoint + '\'' +
                ", contenttype='" + contenttype + '\'' +
                ", onelabelid='" + onelabelid + '\'' +
                ", twolabelid='" + twolabelid + '\'' +
                ", threelabelid='" + threelabelid + '\'' +
                ", onelabel='" + onelabel + '\'' +
                ", twolabel='" + twolabel + '\'' +
                ", threelabel='" + threelabel + '\'' +
                ", searchlabel='" + searchlabel + '\'' +
                ", oneschool='" + oneschool + '\'' +
                ", twoschool='" + twoschool + '\'' +
                ", fitobject='" + fitobject + '\'' +
                ", keyword='" + keyword + '\'' +
                ", description='" + description + '\'' +
                ", sourceid='" + sourceid + '\'' +
                ", sharerange='" + sharerange + '\'' +
                ", creator='" + creator + '\'' +
                ", createtime=" + createtime +
                ", lastmodifier='" + lastmodifier + '\'' +
                ", lastupdatetime=" + lastupdatetime +
                ", endtime=" + endtime +
                ", status='" + status + '\'' +
                ", score=" + score +
                ", version='" + version + '\'' +
                ", viewtimes=" + viewtimes +
                ", downtimes=" + downtimes +
                ", collectiontimes=" + collectiontimes +
                ", clicktimes=" + clicktimes +
                ", usetime=" + usetime +
                ", reslevelid='" + reslevelid + '\'' +
                ", encryptstatus='" + encryptstatus + '\'' +
                ", contentkey='" + contentkey + '\'' +
                ", epubtype='" + epubtype + '\'' +
                ", isallowdownload='" + isallowdownload + '\'' +
                ", fileInfo=" + fileInfo +
                ", ratesum=" + ratesum +
                ", ratecount=" + ratecount +
                ", hotvalue=" + hotvalue +
                ", isgoods='" + isgoods + '\'' +
                ", creatorname='" + creatorname + '\'' +
                ", isfavourite=" + isfavourite +
                ", rating=" + rating +
                ", auditinfo=" + auditinfo +
                ", goodinfo=" + goodinfo +
                ", collectscore=" + collectscore +
                ", catalogids=" + catalogids +
                ", downTime=" + downTime +
                ", subnaviInfos=" + subnaviInfos +
                '}';
    }
}
