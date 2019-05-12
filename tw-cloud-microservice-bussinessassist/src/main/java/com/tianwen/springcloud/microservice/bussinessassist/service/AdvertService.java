package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.OrderMethod;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.AdvertMapper;
import com.tianwen.springcloud.microservice.bussinessassist.dao.AuditMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Advert;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AdvertService extends MyBaseService<Advert> {
    @Autowired
    private AdvertMapper advertMapper;

    @SystemServiceLog(description = "AdvertSearch")
    public Response<Advert> search(QueryTree queryTree)
    {
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixTimestampParameter(map);

        /*
        if (map.get("status") == null)
            map.put("status", "1");

        */

        int count = advertMapper.countAdvert(map);
        List<Advert> queryList = advertMapper.queryAdvertForList(map);
        Page<Advert> result = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<>(result);
    }

    @SystemServiceLog(description = "CountAdvert")
    public int countAdvert(QueryTree queryTree)
    {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixTimestampParameter(map);
        return advertMapper.countAdvert(map);
    }
}
