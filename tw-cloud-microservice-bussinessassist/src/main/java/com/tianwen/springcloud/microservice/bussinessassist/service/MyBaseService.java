package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.fge.jsonschema.core.exceptions.ExceptionProvider;
import com.tianwen.springcloud.datasource.base.BaseService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class MyBaseService<T> extends BaseService<T>{
    void fixTimestampParameter(Map<String, Object> map) {
        String[] fields = {"begin_time", "audit_begintime", "upload_begintime", "end_time", "audit_endtime", "upload_endtime"};

        int i, len = fields.length;
        for (i = 0; i < len; i++) {
            String suffix;
            if (i < len / 2)
                suffix = " 00:00:00";
            else
                suffix = " 23:59:59";

            String key = fields[i];
            Object stringValue = map.get(key);
            if (stringValue != null) {
                map.remove(key);
                if (!stringValue.toString().isEmpty()) {
                    try {
                        Timestamp dateValue = Timestamp.valueOf(stringValue.toString() + suffix);
                        map.put(key, dateValue);
                    } catch (IllegalArgumentException e) {
                    }
                }
            }
        }
    }
}
