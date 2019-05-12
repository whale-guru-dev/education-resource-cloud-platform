package com.tianwen.springcloud.microservice.bussinessassist.dao;

import com.tianwen.springcloud.datasource.mapper.MyMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Audit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AuditMapper extends MyMapper<Audit> {

    /**
     *  Get count of audit list, especially for pagination
     *
     * @param map : search condition
     * @return long : count
     * @see
     */
    Long countAudit(Map<String, Object> map);

    /**
     * Get list of audit information by using some conditions(filter)
     *
     * @param map : search condition, order condition, pagination, ...
     * @return List<Audit>
     * @see
     */
    List<Audit> queryAuditForList(Map<String, Object> map);

    /**
     * add an audit information about allowing resource
     *
     * @param map : resource to be allowed,
     * @return void
     * @see
     */
    void allowAudit(Map<String, Object> resource);

    /**
     * add an audit information about forbidding resource
     *
     * @param map : resource to be forbidden
     * @return void
     * @see
     */
    void denyAudit(Map<String, Object> resource);

    /**
     * get audit information of resource
     *
     * @param String : resource id(contentid)
     * @return Audit : audit information of that resource
     * @see
     */
    Audit getAuditInfoByResourceId(String contentid);

    List<String> getAuditorList();

    Long countAuditForList(Map<String, Object> map);
}
