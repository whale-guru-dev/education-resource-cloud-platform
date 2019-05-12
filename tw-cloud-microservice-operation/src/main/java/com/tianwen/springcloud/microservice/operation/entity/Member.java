package com.tianwen.springcloud.microservice.operation.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang.ArrayUtils;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_user_member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_memberid_t_e_user_member')")
    @Column(name = "memberid")
    @ApiModelProperty("会员Id")
    private String memberid;

    @Column(name = "memberno")
    @ApiModelProperty(value = "会员编号", required = true)
    private String memberno;

    @Column(name = "userid")
    @ApiModelProperty(value = "用户ID", required = true)
    private String userid;

    @Column(name = "starttime")
    @ApiModelProperty(value = "会员起启日期", required = true)
    private Timestamp starttime;

    @Column(name = "endtime")
    @ApiModelProperty(value = "会员结束日期", required = true)
    private Timestamp endtime;

    @Column(name = "memberlevel")
    @ApiModelProperty(value = "会员级别", required = true)
    private String memberlevel;

    @Column(name = "membertype")
    @ApiModelProperty(value = "会员级别")
    private String membertype;

    @Column(name = "merchantno")
    @ApiModelProperty("商户编码 字典")
    private String merchantno;

    @Column(name = "status")
    @ApiModelProperty("账户状态")
    private String status;

    @Column(name = "totintegral")
    @ApiModelProperty(value = "账户总积分")
    private Integer totintegral;

    @Column(name = "frozenintegral")
    @ApiModelProperty(value = "账户总积分")
    private Integer frozenintegral;

    @Column(name = "useintegral")
    @ApiModelProperty(value = "账户总积分")
    private Integer useintegral;

    @Column(name = "usedintegral")
    @ApiModelProperty(value = "账户总积分")
    private Integer usedintegral;

    @Column(name = "createtime")
    @ApiModelProperty(value = "创建时间", required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(value = "最后修改时间", required = true)
    private Timestamp lastmodifytime;

    private String[] formats;
    private String format;

    private String loginname;
    private String realname;

    public Timestamp getStarttime() {
        if (starttime == null)
            return null;
        return new Timestamp(starttime.getTime());
    }

    public void setStarttime(Timestamp starttime) {
        if (starttime != null)
            this.starttime = new Timestamp(starttime.getTime());
    }

    public Timestamp getEndtime() {
        if (endtime == null)
            return null;
        return new Timestamp(endtime.getTime());
    }

    public void setEndtime(Timestamp endtime) {
        if (endtime != null)
            this.endtime = new Timestamp(endtime.getTime());
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMemberno() {
        return memberno;
    }

    public void setMemberno(String memberno) {
        this.memberno = memberno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMemberlevel() {
        return memberlevel;
    }

    public void setMemberlevel(String memberlevel) {
        this.memberlevel = memberlevel;
    }

    public String getMembertype() {
        return membertype;
    }

    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotintegral() {
        return totintegral;
    }

    public void setTotintegral(Integer totintegral) {
        this.totintegral = totintegral;
    }

    public Integer getFrozenintegral() {
        return frozenintegral;
    }

    public void setFrozenintegral(Integer frozenintegral) {
        this.frozenintegral = frozenintegral;
    }

    public Integer getUseintegral() {
        return useintegral;
    }

    public void setUseintegral(Integer useintegral) {
        this.useintegral = useintegral;
    }

    public Integer getUsedintegral() {
        return usedintegral;
    }

    public void setUsedintegral(Integer usedintegral) {
        this.usedintegral = usedintegral;
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

    public Timestamp getLastmodifytime() {
        if (lastmodifytime == null)
            return null;
        return new Timestamp(lastmodifytime.getTime());
    }

    public void setLastmodifytime(Timestamp lastmodifytime) {
        if (lastmodifytime == null)
            this.lastmodifytime = null;
        else
            this.lastmodifytime = new Timestamp(lastmodifytime.getTime());
    }

    public String[] getFormats() {
        if (formats == null)
            return null;
        return (String[])ArrayUtils.clone(formats);
    }

    public void setFormats(String[] formats) {
        if (formats == null)
            this.formats = null;
        else
            this.formats = (String[])ArrayUtils.clone(formats);
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
