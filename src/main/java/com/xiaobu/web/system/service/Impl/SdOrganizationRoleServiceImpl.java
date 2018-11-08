package com.xiaobu.web.system.service.Impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.system.entity.SdOrganizationRole;
import com.xiaobu.web.system.service.SdOrganizationRoleService;
import com.xiaobu.web.system.dao.SdOrganizationRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-08-31 14:33:15
*/
@Service("sdOrganizationRoleService")
public class SdOrganizationRoleServiceImpl implements SdOrganizationRoleService {

    @Autowired
    private SdOrganizationRoleDao sdOrganizationRoleDao;


    @Override
    public PageModel<SdOrganizationRole> findByOriginationId(SdOrganizationRole sdOrganizationRole, PageModel<SdOrganizationRole> page) {
        sdOrganizationRole.setDictdataType("0004");
        sdOrganizationRole.setStatus(2);
        PageHelper.offsetPage(page.getStart(),page.getLength());
        page.initData(sdOrganizationRoleDao.findByPage(sdOrganizationRole));
        return page;
    }

    @Override
    public PageModel<SdOrganizationRole> findByPageAndStatus(SdOrganizationRole sdOrganizationRole, PageModel<SdOrganizationRole> page) {
        sdOrganizationRole.setStatus(1);
        sdOrganizationRole.setDictdataType("0004");
        PageHelper.offsetPage(page.getStart(),page.getLength());
        page.initData(sdOrganizationRoleDao.findByPage(sdOrganizationRole));
        return page;
    }

    @Override
    public void updateByCondition(SdOrganizationRole sdOrganizationRole) {
        sdOrganizationRoleDao.updateByCondition(sdOrganizationRole);
    }
}