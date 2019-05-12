package com.tianwen.springcloud.microservice.bussinessassist.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "t_e_good")
public class Good extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_goodid_t_e_good')")
    @Column(name = "goodid")
    @ApiModelProperty("goodid")
    private String goodid;

    @Column(name = "goodtype")
    @ApiModelProperty(required = true, allowableValues = "1,2,3,4,5")
    private String goodtype;

    @Column(name = "productid")
    @ApiModelProperty(required = true)
    private String productid;

    @Column(name = "allowpaymenttype")
    @ApiModelProperty(allowableValues = "1,2,3")
    private Integer allowpaymenttype;

    @Column(name = "onshelftime")
    @ApiModelProperty()
    private Timestamp onshelftime;

    @Column(name = "downshelftime")
    @ApiModelProperty()
    private Timestamp downshelftime;

    @Column(name = "goodprice")
    @ApiModelProperty(required = true)
    private Integer goodprice;

    @Column(name = "merchantno")
    @ApiModelProperty()
    private String merchantno;

    @Column(name = "userid")
    @ApiModelProperty(required = true)
    private String userid;

    @Column(name = "createtime")
    @ApiModelProperty(required = true)
    private Timestamp createtime;

    @Column(name = "lastmodifytime")
    @ApiModelProperty(required = true)
    private Timestamp lastmodifytime;

    @Column(name = "status")
    @ApiModelProperty(required = true)
    private String status;

    private Object resource;

    private String isexpired;

    private String goodname;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoodid() {
        return goodid;
    }

    public void setGoodid(String goodid) {
        this.goodid = goodid;
    }

    public String getGoodtype() {
        return goodtype;
    }

    public void setGoodtype(String goodtype) {
        this.goodtype = goodtype;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public Integer getAllowpaymenttype() {
        return allowpaymenttype;
    }

    public void setAllowpaymenttype(Integer allowpaymenttype) {
        this.allowpaymenttype = allowpaymenttype;
    }

    public Timestamp getOnshelftime() {
        if (onshelftime == null)
            return null;
        return new Timestamp(onshelftime.getTime());
    }

    public void setOnshelftime(Timestamp onshelftime) {
        if (onshelftime == null)
            this.onshelftime = null;
        else
            this.onshelftime = new Timestamp(onshelftime.getTime());
    }

    public Timestamp getDownshelftime() {
        if (downshelftime == null)
            return null;
        return new Timestamp(downshelftime.getTime());
    }

    public void setDownshelftime(Timestamp downshelftime) {
        if (downshelftime == null)
            this.downshelftime = null;
        else
            this.downshelftime = new Timestamp(downshelftime.getTime());
    }

    public Integer getGoodprice() {
        return goodprice;
    }

    public void setGoodprice(Integer goodprice) {
        this.goodprice = goodprice;
    }

    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getIsexpired() {
        return isexpired;
    }

    public void setIsexpired(String isexpired) {
        this.isexpired = isexpired;
    }

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

}
