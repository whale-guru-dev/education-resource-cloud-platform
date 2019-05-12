package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by gems on 2018.12.21.
 */
@Table(name = "t_con_resource_theme")
public class ThemeRes  extends BaseEntity{
    @Column(name = "themeid")
    @ApiModelProperty("专题id")
    private String themeid;

    @Column(name = "resourceno")
    @ApiModelProperty(value = "", required = true)
    private String resourceno;

    @Column(name = "resourcetype")
    @ApiModelProperty(value = "", required = true)
    private String resourcetype;

    public String getThemeid() {
        return themeid;
    }

    public void setThemeid(String themeid) {
        this.themeid = themeid;
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
