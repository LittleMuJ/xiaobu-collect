package com.xiaobu.web.system.dao;
import com.github.pagehelper.Page;
import com.xiaobu.common.model.PageModel;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.common.base.BaseDao;
import com.xiaobu.web.system.entity.SdOrganizationRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* 描述：标注平台用户DTO
* @author MuRunSen
* @date 2018-08-31 14:33:15
*/
@Mapper
public interface SdOrganizationRoleDao extends BaseDao<SdOrganizationRole, Integer>{

   void  updateByCondition(SdOrganizationRole sdOrganizationRole);

  /* Page<SdOrganizationRole> findByPageAndStatus(SdOrganizationRole sdOrganizationRole);*/

}