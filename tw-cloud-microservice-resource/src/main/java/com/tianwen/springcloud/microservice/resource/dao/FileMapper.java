package com.tianwen.springcloud.microservice.resource.dao;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.resource.entity.FileInfo;

import java.util.List;
import java.util.Map;

public interface FileMapper extends MyMapper<FileInfo> {

    /**
     *
     *
     * @param map
     * @return List<FileInfo>
     * @see
     */
    List<FileInfo> queryFileInfoForList(Map<String, Object> map);

    /**
     *
     *
     * @param map
     * @return Long
     * @see
     */
    Long countFileInfo(Map<String, Object> map);

    /**
     *
     * @param contentid
     * @return
     */
    FileInfo getByContentid(String contentid);

    /**
     *
     * @param map
     * @return
     */
    List<FileInfo> selectByMd5(Map<String, String> map);
    List<FileInfo> getListByFields(Map<String, String> map);

}
