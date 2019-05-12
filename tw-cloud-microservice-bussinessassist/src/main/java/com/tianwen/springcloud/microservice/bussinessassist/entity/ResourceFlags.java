package com.tianwen.springcloud.microservice.bussinessassist.entity;

/**
 * Create on 2019.01.12.
 */
public class ResourceFlags {
    private int isbasket;
    private int iscollect;
    private int isnew;
    private int isvote;

    public int getIsbasket() {
        return isbasket;
    }

    public void setIsbasket(int isbasket) {
        this.isbasket = isbasket;
    }

    public int getIscollect() {
        return iscollect;
    }

    public void setIscollect(int iscollect) {
        this.iscollect = iscollect;
    }

    public int getIsnew() {
        return isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public int getIsvote() {
        return isvote;
    }

    public void setIsvote(int isvote) {
        this.isvote = isvote;
    }
}
