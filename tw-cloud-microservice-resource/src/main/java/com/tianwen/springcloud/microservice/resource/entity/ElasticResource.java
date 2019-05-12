package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.List;

public class ElasticResource extends BaseEntity {
    // content info

    @Transient
    String contentid;

    @Transient
    String contentno;

    @Transient
    String schoolsectionid;

    @Transient
    String schoolsection;

    @Transient
    String subjectid;

    @Transient
    String subject;

    @Transient
    String editiontypeid;

    @Transient
    String editiontype;

    @Transient
    String gradeid;

    @Transient
    String grade;

    @Transient
    String bookmodelid;

    @Transient
    String bookmodel;

    @Transient
    String chapter;

    @Transient
    String section;

    @Transient
    String knowledgepoint;

    @Transient
    String contenttype;

    @Transient
    String onelabel;

    @Transient
    String twolabel;

    @Transient
    String threelabel;

    @Transient
    String searchlabel;

    @Transient
    String oneschool;

    @Transient
    String twoschool;

    @Transient
    String fitobject;

    @Transient
    String sourceid;

    @Transient
    String sharerange;

    @Transient
    String creator;

    @Transient
    Timestamp createtime;

    @Transient
    String lastmodifier;

    @Transient
    Timestamp lastupdatetime;

    @Transient
    Timestamp endtime;

    @Transient
    String status;

    @Transient
    Integer score;

    @Transient
    String version;

    @Transient
    Integer viewtimes;

    @Transient
    Integer downtimes;

    @Transient
    Integer collectiontimes;

    @Transient
    Integer clicktimes;

    @Transient
    Timestamp usetime;

    @Transient
    String reslevelid;

    @Transient
    String contentkey;

    @Transient
    String epubtype;

    @Transient
    String isallowdownload;

    @Transient
    Float ratesum;

    @Transient
    String isgoods;

    @Transient
    String isanswer;

    @Transient
    String remarks;

    @Transient
    String importstatus;

    @Transient
    String filepath;

    @Transient
    String hotvalue;

    @Transient
    String isanonymity;

    @Transient
    String thumbnailpath;

    @Transient
    String sharerangekey;

    @Transient
    String name;

    @Transient
    String keyword;

    @Transient
    String description;

    @Transient
    String encryptstatus;

    // file info

    @Transient
    String fileid;

    @Transient
    String size;

    @Transient
    String localpath;

    @Transient
    Integer playtime;

    @Transient
    String channels;

    @Transient
    String norms;

    @Transient
    Integer width;

    @Transient
    Integer height;

    @Transient
    String format;

    @Transient
    String filename;

    @Transient
    String file_md5;

    @Transient
    String isstreamfile;

    @Transient
    Timestamp createtime_file;

    @Transient
    Timestamp lastmodifytime_file;

    // good info

    @Transient
    String goodid;

    @Transient
    String goodtype;

    @Transient
    String productid;

    @Transient
    String allowpaymenttype;

    @Transient
    Timestamp onshelftime;

    @Transient
    Timestamp downshelftime;

    @Transient
    Integer goodprice;

    @Transient
    String merchantno;

    @Transient
    String gooduserid;

    @Transient
    Timestamp createtime_good;

    @Transient
    Timestamp lastmodifytime_good;

    @Transient
    String goodstatus;

    // activity ids

    @Transient
    String activityids;

    // catalog ids

    @Transient
    String catalogids;

    public String getEncryptstatus() {
        return encryptstatus;
    }

    public void setEncryptstatus(String encryptstatus) {
        this.encryptstatus = encryptstatus;
    }

    public String getActivityids() {
        return activityids;
    }

    public void setActivityids(String activityids) {
        this.activityids = activityids;
    }

    public String getCatalogids() {
        return catalogids;
    }

    public void setCatalogids(String catalogids) {
        this.catalogids = catalogids;
    }

    public String getContentid() {
        return contentid;
    }

    public void setContentid(String contentid) {
        this.contentid = contentid;
    }

    public String getContentno() {
        return contentno;
    }

    public void setContentno(String contentno) {
        this.contentno = contentno;
    }

    public String getSchoolsectionid() {
        return schoolsectionid;
    }

    public void setSchoolsectionid(String schoolsectionid) {
        this.schoolsectionid = schoolsectionid;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEditiontypeid() {
        return editiontypeid;
    }

    public void setEditiontypeid(String editiontypeid) {
        this.editiontypeid = editiontypeid;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getBookmodelid() {
        return bookmodelid;
    }

    public void setBookmodelid(String bookmodelid) {
        this.bookmodelid = bookmodelid;
    }

    public String getBookmodel() {
        return bookmodel;
    }

    public void setBookmodel(String bookmodel) {
        this.bookmodel = bookmodel;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
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

    public String getThreelabel() {
        return threelabel;
    }

    public void setThreelabel(String threelabel) {
        this.threelabel = threelabel;
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

    public Timestamp getCreatetime() {
        return new Timestamp(createtime.getTime());
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = new Timestamp(createtime.getTime());
    }

    public String getLastmodifier() {
        return lastmodifier;
    }

    public void setLastmodifier(String lastmodifier) {
        this.lastmodifier = lastmodifier;
    }

    public Timestamp getLastupdatetime() {
        return new Timestamp(lastupdatetime.getTime());
    }

    public void setLastupdatetime(Timestamp lastupdatetime) {
        this.lastupdatetime = new Timestamp(lastupdatetime.getTime());
    }

    public Timestamp getEndtime() {
        return new Timestamp(endtime.getTime());
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = new Timestamp(endtime.getTime());
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
        return new Timestamp(usetime.getTime());
    }

    public void setUsetime(Timestamp usetime) {
        this.usetime = new Timestamp(usetime.getTime());
    }

    public String getReslevelid() {
        return reslevelid;
    }

    public void setReslevelid(String reslevelid) {
        this.reslevelid = reslevelid;
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

    public String getIsgoods() {
        return isgoods;
    }

    public void setIsgoods(String isgoods) {
        this.isgoods = isgoods;
    }

    public String getIsanswer() {
        return isanswer;
    }

    public void setIsanswer(String isanswer) {
        this.isanswer = isanswer;
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

    public String getHotvalue() {
        return hotvalue;
    }

    public void setHotvalue(String hotvalue) {
        this.hotvalue = hotvalue;
    }

    public String getIsanonymity() {
        return isanonymity;
    }

    public void setIsanonymity(String isanonymity) {
        this.isanonymity = isanonymity;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLocalpath() {
        return localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getNorms() {
        return norms;
    }

    public void setNorms(String norms) {
        this.norms = norms;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFile_md5() {
        return file_md5;
    }

    public void setFile_md5(String file_md5) {
        this.file_md5 = file_md5;
    }

    public String getIsstreamfile() {
        return isstreamfile;
    }

    public void setIsstreamfile(String isstreamfile) {
        this.isstreamfile = isstreamfile;
    }

    public Timestamp getCreatetime_file() {
        return new Timestamp(createtime_file.getTime());
    }

    public void setCreatetime_file(Timestamp createtime_file) {
        this.createtime_file = new Timestamp(createtime_file.getTime());
    }

    public Timestamp getLastmodifytime_file() {
        return new Timestamp(lastmodifytime_file.getTime());
    }

    public void setLastmodifytime_file(Timestamp lastmodifytime_file) {
        this.lastmodifytime_file = new Timestamp(lastmodifytime_file.getTime());
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getAllowpaymenttype() {
        return allowpaymenttype;
    }

    public void setAllowpaymenttype(String allowpaymenttype) {
        this.allowpaymenttype = allowpaymenttype;
    }

    public Timestamp getOnshelftime() {
        return new Timestamp(onshelftime.getTime());
    }

    public void setOnshelftime(Timestamp onshelftime) {
        this.onshelftime = new Timestamp(onshelftime.getTime());
    }

    public Timestamp getDownshelftime() {
        return new Timestamp(downshelftime.getTime());
    }

    public void setDownshelftime(Timestamp downshelftime) {
        this.downshelftime = new Timestamp(downshelftime.getTime());
    }

    public Integer getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(Integer goodprice) {
        this.goodprice = goodprice;
    }

    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno;
    }

    public String getGooduserid() {
        return gooduserid;
    }

    public void setGooduserid(String gooduserid) {
        this.gooduserid = gooduserid;
    }

    public Timestamp getCreatetime_good() {
        return new Timestamp(createtime_good.getTime());
    }

    public void setCreatetime_good(Timestamp createtime_good) {
        this.createtime_good = new Timestamp(createtime_good.getTime());
    }

    public Timestamp getLastmodifytime_good() {
        return new Timestamp(lastmodifytime_good.getTime());
    }

    public void setLastmodifytime_good(Timestamp lastmodifytime_good) {
        this.lastmodifytime_good = new Timestamp(lastmodifytime_good.getTime());
    }

    public String getGoodstatus() {
        return goodstatus;
    }

    public void setGoodstatus(String goodstatus) {
        this.goodstatus = goodstatus;
    }
}
