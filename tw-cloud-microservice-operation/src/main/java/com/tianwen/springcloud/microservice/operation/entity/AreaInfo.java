package com.tianwen.springcloud.microservice.operation.entity;

import javax.persistence.Entity;
import java.util.List;

/**
 * Create on 2019.01.09.
 */
@Entity
public class AreaInfo {

    private String areaid;

    private String areaname;

    private List<String> userids;

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public List<String> getUserids() {
        return userids;
    }

    public void setUserids(List<String> userids) {
        this.userids = userids;
    }
}
