package com.tianwen.springcloud.microservice.operation.entity;

import com.tianwen.springcloud.commonapi.base.BaseEntity;
import org.apache.commons.beanutils.converters.BigDecimalConverter;

import java.util.Map;

public class IntegralInfo extends BaseEntity {
    private Integer bonusIntegral = 0;
    private Integer usedIntegral = 0;
    private Long totalIntegral=0L;
    private String areaname;

    public Integer getBonusIntegral() {
        return bonusIntegral;
    }

    public void setBonusIntegral(Integer bonusIntegral) {
        this.bonusIntegral = bonusIntegral;
    }

    public Integer getUsedIntegral() {
        return usedIntegral;
    }

    public void setUsedIntegral(Integer usedIntegral) {
        this.usedIntegral = usedIntegral;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

    public Long getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Long totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public void setInfo(Map<String, Object> statInfo) {
        if (null != statInfo) {
            areaname = (String) statInfo.get("areaname");
            BigDecimalConverter converter = new BigDecimalConverter();
            Object object = statInfo.get("bonus_point");
            if (object != null)
                bonusIntegral = converter.convert(Integer.class, object);
            object = statInfo.get("out_point");
            if (object != null)
                usedIntegral = converter.convert(Integer.class, object);

            totalIntegral = Long.valueOf(bonusIntegral+usedIntegral);
        }
    }
}
