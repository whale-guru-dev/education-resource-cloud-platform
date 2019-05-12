package com.tianwen.springcloud.microservice.resource.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.constant.IStateCode;
import com.tianwen.springcloud.commonapi.exception.ParameterException;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.commonapi.utils.ValidatorUtil;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.resource.api.FileMicroApi;
import com.tianwen.springcloud.microservice.resource.entity.FileInfo;
import com.tianwen.springcloud.microservice.resource.entity.Resource;
import com.tianwen.springcloud.microservice.resource.service.FileService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/file")
public class FileController extends AbstractCRUDController<FileInfo> implements FileMicroApi {

    @Autowired
    private FileService fileService;

    @Override
    public Response<FileInfo> getList(@RequestBody QueryTree queryTree) {
        return fileService.search(queryTree);
    }

    @Override
    public Response<Integer> batchDelete(@RequestBody List<String> ids) {
        int count = 0;
        for (String id : ids) {
            int result = fileService.deleteByPrimaryKey(id);
            if (result == 0) count ++;
        }
        return new Response<>(count);
    }

    @Override
    public Response<FileInfo> getByContentid(@PathVariable(value = "contentid") String contentid) {
        return new Response<>(fileService.getByContentid(contentid));
    }

    @Override
    public Response<FileInfo> getListByFields(@RequestBody Map<String,String> fields) {
        return new Response<FileInfo>(fileService.getListByFields(fields));
    }

    private void validateAdd(FileInfo entity)
    {
        if (null == entity)
            throw new ParameterException(IStateCode.PARAMETER_IS_EMPTY, "请求体为空");

        // 验重复
        String md5 = entity.getFile_md5();
        if (!StringUtils.isEmpty(md5)){
            List<FileInfo> existList = fileService.selectByMd5(md5);
            if (!CollectionUtils.isEmpty(existList)) {
                throw new ParameterException(IStateCode.PARAMETER_IS_INVALID, "登录文件重复");
            }
        }

        ValidatorUtil.parameterValidate(entity);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        entity.setCreatetime(currentTime);
        entity.setLastmodifytime(currentTime);
    }

    private void validateUpdate(FileInfo updateEntity) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        updateEntity.setLastmodifytime(currentTime);
    }

    @Override
    public void validate(MethodType methodType, Object p) {
        switch (methodType)
        {
            case ADD:
                FileInfo entity = (FileInfo) p;
                validateAdd(entity);
                break;
            case UPDATE:
                FileInfo updateEntity = (FileInfo) p;
                validateUpdate(updateEntity);
                break;
            default:
                break;
        }
    }
}
