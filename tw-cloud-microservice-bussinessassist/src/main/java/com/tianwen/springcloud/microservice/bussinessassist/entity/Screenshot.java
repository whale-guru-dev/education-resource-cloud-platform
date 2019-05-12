package com.tianwen.springcloud.microservice.bussinessassist.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_screenshot")
public class Screenshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_screenid_t_e_screenshot')")
    @Column(name = "screenid")
    @ApiModelProperty("")
    private String screenid;

    @Column(name = "size")
    @ApiModelProperty("")
    private String size;

    @Column(name = "localpath")
    @ApiModelProperty("")
    private String localpath;

    @Column(name = "width")
    @ApiModelProperty("")
    private Integer width;

    @Column(name = "height")
    @ApiModelProperty("")
    private Integer height;

    @Column(name = "format")
    @ApiModelProperty("")
    private String format;

    @Column(name = "filename")
    @ApiModelProperty("")
    private String filename;

    @Column(name = "createtime")
    @ApiModelProperty("")
    private Timestamp createtime;

    public String getScreenid() {
        return screenid;
    }

    public void setScreenid(String screenid) {
        this.screenid = screenid;
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
}
