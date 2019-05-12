package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;

import javax.persistence.Transient;

public class ResConCatalogInfo extends BaseEntity {
    @Transient
    private String bookid;

    @Transient
    private String chapterid;

    @Transient
    private String resourceno;

    @Transient
    private String resourcetype;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getChapterid() {
        return chapterid;
    }

    public void setChapterid(String chapterid) {
        this.chapterid = chapterid;
    }

    public String getResourceno() {
        return resourceno;
    }

    public void setResourceno(String resourceno) {
        this.resourceno = resourceno;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }
}
