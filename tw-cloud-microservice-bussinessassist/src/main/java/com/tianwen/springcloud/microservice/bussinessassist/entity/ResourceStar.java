package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_resource_star")
public class ResourceStar extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_starid_t_e_resource_star')")
    @Column(name = "starid")
    @ApiModelProperty("starid")
    private String starid;

    @Column(name = "objectid")
    @ApiModelProperty(value = "objectid", required = true)
    private String objectid;

    @Column(name = "objecttype")
    @ApiModelProperty(value = "objecttype", required = true)
    private String objecttype;

    @Column(name = "result")
    @ApiModelProperty(value = "result", required = true)
    private int result;

    @Column(name = "staruser")
    @ApiModelProperty(value = "staruser", required = true)
    private String staruser;

    @Column(name = "startime")
    @ApiModelProperty(value = "startime", required = true)
    private Timestamp startime;

    public String getStarid() {
        return starid;
    }

    public void setStarid(String starid) {
        this.starid = starid;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getStaruser() {
        return staruser;
    }

    public void setStaruser(String staruser) {
        this.staruser = staruser;
    }

    public Timestamp getStartime() {
        if (startime == null)
            return null;
        return new Timestamp(startime.getTime());
    }

    public void setStartime(Timestamp startime) {
        if (startime == null)
            this.startime = null;
        else
            this.startime = new Timestamp(startime.getTime());
    }
}
