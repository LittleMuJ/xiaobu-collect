package com.xiaobu.web.system.service;

import com.github.pagehelper.Page;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.system.entity.SdOrganizationRole;

import java.util.List;

/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-08-31 14:33:15
*/
public interface SdOrganizationRoleService {

    /**
    * 描述：根据Id获取DTO
    * @param sdOrganizationRole
    */
    PageModel<SdOrganizationRole> findByOriginationId(SdOrganizationRole sdOrganizationRole,PageModel<SdOrganizationRole> page);

    PageModel<SdOrganizationRole> findByPageAndStatus(SdOrganizationRole sdOrganizationRole,PageModel<SdOrganizationRole> page);

   void  updateByCondition(SdOrganizationRole sdOrganizationRole);

}