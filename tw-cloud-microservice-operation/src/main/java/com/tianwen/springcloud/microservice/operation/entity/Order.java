package com.tianwen.springcloud.microservice.operation.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "t_e_order")
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT pg_nextval('seq_orderid_t_e_order')")
    @Column(name = "orderid")
    @ApiModelProperty("会员Id")
    private String orderid;

    @Column(name = "ispaid")
    @ApiModelProperty("")
    private String ispaid;

    @Column(name = "paidtime")
    @ApiModelProperty("")
    private Timestamp paidtime;

    @Column(name = "userid")
    @ApiModelProperty("")
    private String userid;

    @Column(name = "orderamount")
    @ApiModelProperty("")
    private Integer orderamount;

    @Column(name = "integralprice")
    @ApiModelProperty("")
    private Integer integralprice;

    @Column(name = "cashprice")
    @ApiModelProperty("")
    private Integer cashprice;

    @Column(name = "merchantno")
    @ApiModelProperty("")
    private Integer  merchantno;

    @Column(name = "starttime")
    @ApiModelProperty("")
    private Timestamp starttime;

    @Column(name = "endtime")
    @ApiModelProperty("")
    private Timestamp endtime;

    @Column(name = "paymenttype")
    @ApiModelProperty("")
    private String paymenttype;

    @Column(name = "createtime")
    @ApiModelProperty("")
    private Timestamp createtime;

    @Column(name = "paymentcard")
    @ApiModelProperty("")
    private String paymetncard;

    @Column(name = "lastmodifytime")
    @ApiModelProperty("")
    private Timestamp lastmodifytime;

    @Column(name = "itemid")
    @ApiModelProperty("")
    private String itemid;

    @Column(name = "address")
    @ApiModelProperty("")
    private String address;

    @Column(name = "addressee")
    @ApiModelProperty("")
    private String addressee;

    @Column(name = "phone")
    @ApiModelProperty("")
    private String phone;

    private String contentname;
    private String realname;

    private List<String> contentids;

    private Integer count;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getIspaid() {
        return ispaid;
    }

    public void setIspaid(String ispaid) {
        this.ispaid = ispaid;
    }

    public Timestamp getPaidtime() {
        if (paidtime == null)
            return null;
        return new Timestamp(paidtime.getTime());
    }

    public void setPaidtime(Timestamp paidtime) {
        if (paidtime == null)
            this.paidtime = null;
        else
            this.paidtime = new Timestamp(paidtime.getTime());
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(Integer merchantno) {
        this.merchantno = merchantno;
    }

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

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
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

    public String getPaymetncard() {
        return paymetncard;
    }

    public void setPaymetncard(String paymetncard) {
        this.paymetncard = paymetncard;
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

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getcontentName() {
        return contentname;
    }

    public void setcontentName(String contentname) {
        this.contentname = contentname;
    }

    public void setOrderamount(Integer orderamount) {
        this.orderamount = orderamount;
    }

    public Integer getIntegralprice() {
        return integralprice;
    }

    public void setIntegralprice(Integer integralprice) {
        this.integralprice = integralprice;
    }

    public Integer getCashprice() {
        return cashprice;
    }

    public void setCashprice(Integer cashprice) {
        this.cashprice = cashprice;
    }

    public String getContentname() {
        return contentname;
    }

    public void setContentname(String contentname) {
        this.contentname = contentname;
    }

    public Integer getOrderamount() {
        return orderamount;
    }

    public List<String> getContentids() {
        return contentids;
    }

    public void setContentids(List<String> contentids) {
        this.contentids = contentids;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
