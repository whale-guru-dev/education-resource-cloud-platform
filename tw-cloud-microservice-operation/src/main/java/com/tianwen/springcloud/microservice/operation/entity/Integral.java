package com.tianwen.springcloud.microservice.operation.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import com.tianwen.springcloud.commonapi.utils.DateUtil;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "t_e_user_integral_detail")
public class Integral extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_did_t_e_user_integral_id')")
    @Column(name = "detailid")
    @ApiModelProperty("主键Id")
    private String detailid;

    @Column(name = "userid")
    @ApiModelProperty(value = "积分用户", required = true)
    private String userid;

    @Column(name = "operation_type")
    @ApiModelProperty(value = "操作方式", required = true)
    private String operationtype;

    @Column(name = "income_type")
    @ApiModelProperty(value = "收支方式", required = true)
    private String incometype;

    @Column(name = "integralvalue")
    @ApiModelProperty(value = "本次消费或是支出积分", required = true)
    private Integer integralvalue;

    @Column(name = "user_integralvalue")
    @ApiModelProperty(value = "用户积分余额", required = true)
    private Integer userintegralvalue;

    @Column(name = "objectid")
    @ApiModelProperty(value = "id", required = true)
    private String objectid;

    @Column(name = "scoretype")
    @ApiModelProperty(value = "积分类型")
    private String scoretype;

    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间", required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "最后修改时间", required = true)
    private Timestamp lastmodifytime;

    private String loginname;
    private String realname;

    public String getDetailid() {
        return detailid;
    }

    public void setDetailid(String detailid) {
        this.detailid = detailid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
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

    public String getOperationtype() {
        return operationtype;
    }

    public void setOperationtype(String operationtype) {
        this.operationtype = operationtype;
    }

    public String getIncometype() {
        return incometype;
    }

    public void setIncometype(String incometype) {
        this.incometype = incometype;
    }

    public Integer getUserintegralvalue() {
        return userintegralvalue;
    }

    public void setUserintegralvalue(Integer userintegralvalue) {
        this.userintegralvalue = userintegralvalue;
    }

    public Integer getIntegralvalue() {
        return integralvalue;
    }

    public void setIntegralvalue(Integer integralvalue) {
        this.integralvalue = integralvalue;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getScoretype() {
        return scoretype;
    }

    public void setScoretype(String scoretype) {
        this.scoretype = scoretype;
    }
}
