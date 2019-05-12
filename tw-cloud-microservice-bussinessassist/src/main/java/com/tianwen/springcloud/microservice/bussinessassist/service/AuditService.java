package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.OrderMethod;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.AuditMapper;
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
public class AuditService extends MyBaseService<Audit> {
    @Autowired
    private AuditMapper auditMapper;

    public void fixParameter(Map<String, Object> map)
    {
        fixTimestampParameter(map);
        if (map.get("searchkey") != null)
        {
            String search = map.get("searchkey").toString();
            map.remove("searchkey");
            if (!StringUtil.isEmpty(search)) {
                search = '%' + search + '%';
                map.put("searchkey", search);
            }
        }
    }

    @SystemServiceLog(description = "AuditSearch")
    public Response<Audit> search(QueryTree queryTree)
    {
        Pagination pagination = queryTree.getPagination();

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixParameter(map);

        List<OrderMethod> ordermethods = queryTree.getOrderMethods();

        if (ordermethods != null && !CollectionUtils.isEmpty(ordermethods)) {
            OrderMethod orderMethod = ordermethods.get(0);
            String field = orderMethod.getField();
            String value = orderMethod.getMethod().getValue();
            map.put(field+value, "");
        }

        map.remove("orderMethods");

        Long count = auditMapper.countAuditForList(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());

        List<Audit> pageList = auditMapper.queryAuditForList(map);
        Page<Audit> result = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(pageList);
        result.setTotal(count);
        return new Response<>(result);
    }

    @SystemServiceLog(description = "AllowAudit")
    public Response<Audit> allowAudit(Map<String, Object> auditData)
    {
        List<String> ids = (List<String>)auditData.get("contentids");

        for(String id: ids) {
            Map<String ,Object> one = auditData;
            one.put("contentid", id);
            auditMapper.allowAudit(one);
        }
        return new Response<Audit>(new Audit());
    }

    @SystemServiceLog(description = "DenyAudit")
    public Response<Audit> denyAudit(Map<String, Object> auditData)
    {
        List<String> ids = (List<String>)auditData.get("contentids");

        for(String id: ids) {
            Map<String ,Object> one = auditData;
            one.put("contentid", id);
            auditMapper.denyAudit(one);
        }
        return new Response<Audit>(new Audit());
    }

    public Audit getAuditInfoByResourceId(String contentid) {
        Audit audit = auditMapper.getAuditInfoByResourceId(contentid);
        return audit;
    }

    public int getCount(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        List<Audit> data = auditMapper.queryAuditForList(map);
        return data.size();
    }

    public List<String> getAuditorList() {
        return auditMapper.getAuditorList();
    }

    public Audit getByContentid(String contentid) {
        return auditMapper.getAuditInfoByResourceId(contentid);
    }

    public List<Audit> getAllData(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);

        fixParameter(map);

        List<Audit> queryList = auditMapper.queryAuditForList(map);

        return queryList;
    }
}
