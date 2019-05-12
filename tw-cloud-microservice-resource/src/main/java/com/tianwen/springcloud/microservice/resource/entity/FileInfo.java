package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_file")
public class FileInfo extends BaseEntity {
    private static final long serialVersionUID = 6806799806995005203L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_fileid_t_e_file')")
    @Column(name = "fileid")
    @ApiModelProperty("文件Id")
    private String fileid;

    @Column(name = "size")
    @ApiModelProperty(value = "文件大小", required = true)
    private String size;

    @Column(name = "localpath")
    @ApiModelProperty(value = "文件存储地址", required = true)
    private String localpath;

    @Column(name = "playtime")
    @ApiModelProperty(value = "时长")
    private Integer playtime;

    @Column(name = "channels")
    @ApiModelProperty(value = "声道数")
    private String channels;

    @Column(name = "norms")
    @ApiModelProperty(value = "规格")
    private String norms;

    @Column(name = "width")
    @ApiModelProperty(value = "宽度")
    private Integer width;

    @Column(name = "height")
    @ApiModelProperty(value = "高度")
    private Integer height;

    @Column(name = "format")
    @ApiModelProperty(value = "文件格式", required = true)
    private String format;

    @Column(name = "filename")
    @ApiModelProperty(value = "文件名称", required = true)
    private String filename;

    @Column(name = "file_md5")
    @ApiModelProperty(value = "存储文件的 md5")
    private String file_md5;

    @Column(name = "isstreamfile")
    @ApiModelProperty("")
    private String isstreamfile;

    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间")
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "最后修改时间")
    private Timestamp lastmodifytime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Timestamp getCreatetime() {
        if (createtime == null)
            return null;
        return new Timestamp(createtime.getTime());
    }

    public void setCreatetime(Timestamp createtime) {
        if (createtime != null)
            this.createtime = new Timestamp(createtime.getTime());
    }

    public Timestamp getLastmodifytime() {
        if (lastmodifytime == null)
            return null;
        return new Timestamp(lastmodifytime.getTime());
    }

    public void setLastmodifytime(Timestamp lastmodifytime) {
        if (lastmodifytime != null)
            this.lastmodifytime = new Timestamp(lastmodifytime.getTime());
    }

    @Override
    public String toString() {
        return "FileInfo[" +
                "fileid='" + fileid + '\'' +
                ", size='" + size + '\'' +
                ", localpath='" + localpath + '\'' +
                ", playtime=" + playtime +
                ", channels='" + channels + '\'' +
                ", norms='" + norms + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", format='" + format + '\'' +
                ", filename='" + filename + '\'' +
                ", file_md5='" + file_md5 + '\'' +
                ", isstreamfile='" + isstreamfile + '\'' +
                ", createtime=" + createtime +
                ", lastmodifytime=" + lastmodifytime +
                ']';
    }
}
