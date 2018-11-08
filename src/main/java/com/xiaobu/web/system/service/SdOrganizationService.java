package com.xiaobu.web.system.service;

import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.system.entity.SdOrganization;

import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-07-31 11:40:29
*/
public interface SdOrganizationService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    SdOrganization getById(Integer id)throws Exception;

    void add(SdOrganization sdOrganization);

    void delete(Integer id) throws Exception;

    void update(SdOrganization sdOrganization);

	SdOrganization selectByUsername(String username);

	void insertAndGetId(SdOrganization sdOrganization);

	PageModel<SdOrganization> selectOrganizationInfo(SdOrganization sdOrganization,PageModel<SdOrganization> page);

    Map<String,Object> findOrgInfoById(Integer orgId);

    List<Map<String,Object>> findOrgTaskInfoById(Integer orgId);

    Map<String, Object> findOrgPiechartInfo(Integer taskId, Integer orgId);
}