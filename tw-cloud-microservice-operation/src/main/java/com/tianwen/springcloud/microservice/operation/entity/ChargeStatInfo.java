package com.tianwen.springcloud.microservice.operation.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

import java.util.Map;

public class ChargeStatInfo extends BaseEntity {
    private Integer total = 0;
    private Integer today = 0;
    private Integer lastweek=0;
    private Integer lastmonth=0;
    private Integer average = 0;
    private String name;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }

    public Integer getLastweek() {
        return lastweek;
    }

    public void setLastweek(Integer lastweek) {
        this.lastweek = lastweek;
    }

    public Integer getLastmonth() {
        return lastmonth;
    }

    public void setLastmonth(Integer lastmonth) {
        this.lastmonth = lastmonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public void setInfo(Map<String, Object> statInfo) {
        if (null != statInfo) {
            name = (String) statInfo.get("areaname");
            BigDecimalConverter converter = new BigDecimalConverter();
            Object object = statInfo.get("total");
            total = converter.convert(Integer.class, null==object?0:object );

            object = statInfo.get("today");
            today = converter.convert(Integer.class,null==object?0:object );

            object = statInfo.get("lastweek");
            lastweek = converter.convert(Integer.class, null==object?0:object);

            object = statInfo.get("lastmonth");
            lastmonth = converter.convert(Integer.class, null==object?0:object);
        }
    }

}
