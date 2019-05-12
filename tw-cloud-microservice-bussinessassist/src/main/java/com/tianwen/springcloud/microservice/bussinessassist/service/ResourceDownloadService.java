package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.ResourceDownloadMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceCollection;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ResourceDownloadService extends MyBaseService<ResourceDownload> {
    @Autowired
    private ResourceDownloadMapper resourceDownloadMapper;

    @SystemServiceLog(description = "")
    public Response<ResourceDownload> search(QueryTree queryTree)
    {
        // PageHelper
        Pagination pagination = queryTree.getPagination();
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixTimestampParameter(map);
        int count = resourceDownloadMapper.countDownload(map);
        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());
        List<ResourceDownload> queryList = resourceDownloadMapper.queryDownloadForList(map);
        Page<ResourceDownload> result = new Page<ResourceDownload>(pagination.getPageNo(), pagination.getNumPerPage());
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<ResourceDownload>(result);
    }

    @SystemServiceLog(description = "")
    public int getCountByUser(Map<String, Object> mapList)
    {
        fixTimestampParameter(mapList);
        int count = resourceDownloadMapper.getCountByUser(mapList);
        return count;
    }

    @SystemServiceLog(description = "")
    public int getCountByUserAndResource(Map<String, Object> mapList)
    {
        fixTimestampParameter(mapList);
        int count = resourceDownloadMapper.getCountByUserAndResource(mapList);
        return count;
    }

    @SystemServiceLog(description = "")
    public void downloadResource(Map<String, Object> mapList)
    {
        resourceDownloadMapper.addResourceDownload(mapList);
    }

    public List<ResourceDownload> getDownloadInfoByUser(String userId) {
        return resourceDownloadMapper.getDownloadInfoByUser(userId);
    }

    public int countDown(Map<String, Object> param) {
        fixTimestampParameter(param);
        return resourceDownloadMapper.countDownload(param);
    }

    public Response<ResourceDownload> getByExample(ResourceDownload example) {
        List<ResourceDownload> resourceDownloads = resourceDownloadMapper.getByExample(example);
        return new Response<>(resourceDownloads);
    }
}
