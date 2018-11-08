package com.xiaobu.web.system.controller;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.system.service.SdOrganizationRoleService;
import com.xiaobu.web.system.entity.SdOrganizationRole;
import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import com.xiaobu.common.util.ValidateUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * 描述：标注平台用户controller
 * @author MuRunSen
 * @date 2018-08-31 14:33:15
 */
@Controller
@RequestMapping("/api/organizationRole")
public class SdOrganizationRoleController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(SdOrganizationRoleController.class);

    @Autowired
    private SdOrganizationRoleService sdOrganizationRoleService;
}