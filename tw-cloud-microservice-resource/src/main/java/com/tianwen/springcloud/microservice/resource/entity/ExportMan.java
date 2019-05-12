package com.tianwen.springcloud.microservice.resource.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Timestamp;

public class ExportMan extends BaseEntity {
    private static final long serialVersionUID = 6806799806995005204L;

    private String userid;
    private String username;
    private String count;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ExportMan{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
