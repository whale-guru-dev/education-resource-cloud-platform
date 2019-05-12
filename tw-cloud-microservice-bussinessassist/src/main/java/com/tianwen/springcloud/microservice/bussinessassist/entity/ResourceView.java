package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_resource_view")
public class ResourceView extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_viewid_t_e_resource_view')")
    @Column(name = "viewid")
    @ApiModelProperty("viewid")
    private String viewid;

    @Column(name = "resourceid")
    @ApiModelProperty(value = "resourceid", required = true)
    private String resourceid;

    @Column(name = "userid")
    @ApiModelProperty(value = "userid", required = true)
    private String userid;

    @Column(name = "viewtime")
    @ApiModelProperty(value = "viewtime", required = true)
    private Timestamp viewtime;

    @Column(name = "objecttype")
    @ApiModelProperty(value = "objecttype", required = true)
    private String objecttype;

    public String getViewid() {
        return viewid;
    }

    public void setViewid(String viewid) {
        this.viewid = viewid;
    }

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Timestamp getViewtime() {
        if (viewtime == null)
            return null;
        return new Timestamp(viewtime.getTime());
    }

    public void setViewtime(Timestamp viewtime) {
        if (viewtime == null)
            this.viewtime = null;
        else
            this.viewtime = new Timestamp(viewtime.getTime());
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }
}
