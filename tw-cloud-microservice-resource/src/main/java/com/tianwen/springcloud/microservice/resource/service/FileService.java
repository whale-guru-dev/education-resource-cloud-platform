package com.tianwen.springcloud.microservice.resource.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemServiceLog;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.BaseService;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.resource.constant.IResourceMicroConstants;
import com.tianwen.springcloud.microservice.resource.dao.FileMapper;
import com.tianwen.springcloud.microservice.resource.entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService extends BaseService<FileInfo>{

    @Autowired
    private FileMapper fileMapper;

    @SystemServiceLog(description = "资源搜索")
    public Response<FileInfo> search(QueryTree queryTree)
    {
        // 单表查询
        if (queryTree.isSingleTable())
        {
            queryTree.and("status", QueryCondition.Operator.NOT_EQUAL, IResourceMicroConstants.USER_ACCOUNT_STATUS_DELETE);
            Example example = QueryUtils.queryTree2Example(queryTree, FileInfo.class);
            return new Response<FileInfo>(super.selectByExample(example));
        }
        // 分页比较慢，需要更新分页方式，不能直接使用PageHelper
        Pagination pagination = queryTree.getPagination();
        if (pagination != null)
        {
            queryTree.setPagination(null);
        }
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        Long count = fileMapper.countFileInfo(map);

        Integer start, pageSize, pageNo;

        try{
            start = pagination.getStart();
        }
        catch (NullPointerException e) {
            start = 0;
        }
        map.put("start", start);

        try{
            pageSize = pagination.getNumPerPage();
        }
        catch (NullPointerException e) {
            pageSize = 10;
        }
        map.put("numPerPage", pageSize);

        List<FileInfo> queryList = fileMapper.queryFileInfoForList(map);

        try{
            pageNo = pagination.getPageNo();
        }
        catch (NullPointerException e){
            pageNo = 1;
        }

        Page<FileInfo> result = new Page<FileInfo>(pageNo, pageSize);
        result.addAll(queryList);
        result.setTotal(count);
        return new Response<FileInfo>(result);
    }

    @Override
    public boolean cacheable()
    {
        return false;
    }

    @Override
    public boolean listCacheable()
    {
        return false;
    }

    @Override
    public String getKey(FileInfo r)
    {
        return r.getFileid();
    }

    public FileInfo getByContentid(String contentid) {
        return fileMapper.getByContentid(contentid);
    }

    public List<FileInfo> selectByMd5(String md5) {
        Map<String, String> map = new HashMap<>();
        map.put("md5", md5);
        return fileMapper.selectByMd5(map);
    }

    public List<FileInfo> getListByFields(Map<String,String> fields){
        return fileMapper.getListByFields(fields);
    }
}
