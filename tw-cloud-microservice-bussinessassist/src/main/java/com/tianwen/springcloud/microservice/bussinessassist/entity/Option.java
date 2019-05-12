package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_resource_option")
public class Option extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_optionid_t_e_resource_option')")
    @Column(name = "optionid")
    @ApiModelProperty(value = "optionid", required = true)
    private String optionid;

    @Column(name = "objectid")
    @ApiModelProperty(value = "", required = true)
    private String objectid;

    @Column(name = "objecttype")
    @ApiModelProperty(value = "", required = true)
    private String objecttype;

    @Column(name = "creator")
    @ApiModelProperty(value = "", required = true)
    private String creator;

    @Column(name = "optionname")
    @ApiModelProperty(value = "", required = true)
    private String optionname;

    @Column(name = "optiontype")
    @ApiModelProperty(value = "", required = true)
    private String optiontype;

    @Column(name = "optiontime")
    @ApiModelProperty(value = "", required = true)
    private Timestamp optiontime;

    @Column(name = "optionedid")
    @ApiModelProperty(value = "", required = true)
    private String optionedid;

    public String getOptionedid() {
        return optionedid;
    }

    public void setOptionedid(String optionedid) {
        this.optionedid = optionedid;
    }

    public String getOptionid() {
        return optionid;
    }

    public void setOptionid(String optionid) {
        this.optionid = optionid;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOptionname() {
        return optionname;
    }

    public void setOptionname(String optionname) {
        this.optionname = optionname;
    }

    public String getOptiontype() {
        return optiontype;
    }

    public void setOptiontype(String optiontype) {
        this.optiontype = optiontype;
    }

    public Timestamp getOptiontime() {
        if (optiontime == null)
            return null;
        return new Timestamp(optiontime.getTime());
    }

    public void setOptiontime(Timestamp optiontime) {
        if (optiontime != null)
            this.optiontime = new Timestamp(optiontime.getTime());
    }
}
