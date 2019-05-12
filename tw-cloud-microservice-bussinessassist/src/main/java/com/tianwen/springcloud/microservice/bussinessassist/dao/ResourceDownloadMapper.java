package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.ResourceDownload;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface ResourceDownloadMapper extends MyMapper<ResourceDownload> {

    /**
     * get a user's downloaded item count
     *
     * @param map : user information
     * @return int : count of collected items
     * @see
     */
    int getCountByUser(Map<String, Object> mapList);

    /**
     * relation of a user and a resource (that user downloaded that resource)
     *
     * @param map : userid, contentid
     * @return int : not 0 : collected, 0 : not collected
     * @see
     */
    int getCountByUserAndResource(Map<String, Object> mapList);

    /**
     * add a resource to download
     *
     * @param map : userid, content information
     * @return void
     * @see
     */
    void addResourceDownload(Map<String, Object> mapList);

    List<ResourceDownload> getDownloadInfoByUser(String userId);

    int countDownload(Map<String, Object> map);

    List<ResourceDownload> queryDownloadForList(Map<String, Object> map);

    /**
     * select by example
     * @param example
     * @return
     */
    List<ResourceDownload> getByExample(ResourceDownload example);
}
