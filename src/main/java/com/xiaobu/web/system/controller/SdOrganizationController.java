package com.xiaobu.web.system.controller;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.pro.entity.SdCollect;
import com.xiaobu.web.pro.entity.SdCollectTask;
import com.xiaobu.web.pro.service.SdCollectService;
import com.xiaobu.web.pro.service.SdCollectTaskService;
import com.xiaobu.web.redis.service.RedisInfoService;
import com.xiaobu.web.system.entity.SdOrganizationRole;
import com.xiaobu.web.system.service.*;
import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户controller
* @author MuRunSen
* @date 2018-07-31 11:40:29
*/
@Controller
@RequestMapping("/api/organization")
public class SdOrganizationController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(SdOrganizationController.class);

    @Autowired
    private SdOrganizationService sdOrganizationService;

    @Autowired
    private SdCollectTaskService sdCollectTaskService;

    @Autowired
    private SdManagerService sdManagerService;

    @Autowired
    private RedisInfoService redisService;

    @Autowired
    private SdConsumerService sdConsumerService;

    @Autowired
    private SdDictionarydataService sdDictionarydataService;

    @Autowired
    private SdUserService sdUserService;

    @Autowired
    private SdOrganizationRoleService sdOrganizationRoleService;

    @Autowired
    private SdCollectService sdCollectService;



    /**
     * 5.1团队管理员采集任务列表（已接收）分页接口，团队id即可查询
     * @param sdCollectTask
     * @param page organizationId
     * @param
     * @return
     */
    @RequestMapping(value = "/collectTaskList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object collectTaskList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page){
        try {
            PageModel<SdCollectTask> pages = sdCollectTaskService.selectOrgCollectList(sdCollectTask,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 5.2团队管理员采集任务待领取（接收）分页List，是查询已开始任务以及审核失败的任务
     * @param sdCollectTask
     * @param page organizationId
     * @param
     * @return
     */
    @RequestMapping(value = "/readyCollectList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object readyCollectList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page){
        try {
            PageModel<SdCollectTask> pages = sdCollectTaskService.selectReadyOrgCollect(sdCollectTask,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 5.3团队管理员采集任务领取（接收）接口
     * @param sdCollectTask  传任务id
     * @param
     * @return
     */
    @RequestMapping(value = "/receptionCollect",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object OrgReceptionCollect(SdCollectTask sdCollectTask){
        try {
            sdCollectTaskService.OrgReceptionCollect(sdCollectTask);
            return actionResult(Code.OK,"接收成功");
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"接收失败");
        }
    }

    /**
     * 5.4团队管理员查询质检页面List分页（）
     * @param sdCollectTask
     * @param page
     * MuRunSen
     * 团队管理员质检List接口
     * @return
     */
    @RequestMapping(value = "/examineCollectList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object examineCollectList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page){
        try {
            PageModel<SdCollectTask> pages = sdCollectTaskService.selectOrgExamineCollect(sdCollectTask,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 5.5团队管理员质检成员提交的采集任务详情接口根据taskId查询检验详情
     * @param
     * @param page
     * @return
     */
    @RequestMapping(value = "/examineCollectInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object examineCollectInfo(SdCollect sdCollect, PageModel<SdCollect> page){
        try {
            PageModel<SdCollect> pages = sdCollectService.selectExaminOrgeCollectInfo(sdCollect,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 5.6团队管理员检验单条担任接口
     * @param taskId 任务Id
     * @param id  单条任务id
     * @param status  状态值 3检验失败，4检验成功
     * @return
     */
    @RequestMapping(value = "/examineCollect",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object examineCollect(Integer taskId,Integer id,Integer status){
        try {
            sdCollectService.examineOrgCollect(taskId,id,status);
            return actionResult(Code.OK,"检验成功");
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"检验失败");
        }
    }

    /**
     * 5.11 团队管理员提交任务至平台管理员审核
     * @param taskId
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/submitCollectTask",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object submitCollectTask(Integer taskId,Integer orgId){

        try {
            String msg = sdCollectTaskService.submitTaskToManager(taskId,orgId);
            return actionResult(Code.OK,msg);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"提交失败");
        }
    }

    /**
     * 5.12.根据组织id查询审核通过的组织成员信息接口
     * @author: luxinli
     */
    @RequestMapping(value = "/queryOrgRoleInfoById",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER, SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public Object  findByOriginationId(SdOrganizationRole sdOrganizationRole, PageModel<SdOrganizationRole> page){
        try {
            PageModel<SdOrganizationRole> pages =  sdOrganizationRoleService.findByOriginationId(sdOrganizationRole,page);
            logger.info("根据组织id查询组织成员信息接口获取成功");
            return actionResult(Code.OK,"获取成功",pages);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("根据组织id查询组织成员信息接口获取失败");
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 5.13.根据组织id查询待审核的组织成员信息接口
     * @author: luxinli
     */
    @RequestMapping(value = "/queryOrgRoleInfoByStatus",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER, SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public Object  findByOriginationByStatus(SdOrganizationRole sdOrganizationRole, PageModel<SdOrganizationRole> page) {
        try {
            PageModel<SdOrganizationRole> pages = sdOrganizationRoleService.findByPageAndStatus(sdOrganizationRole, page);
            logger.info("根根据组织id查询待审核的组织成员信息接口获取成功");
            return actionResult(Code.OK, "获取成功", pages);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("根据组织id查询待审核的组织成员信息接口获取失败");
            return actionResult(Code.INTERNAL_SERVER_ERROR, "获取失败");
        }
    }

    /**
     * 5.14.审核成员信息接口
     * @author: luxinli
     */
    @RequestMapping(value = "/modifyOrgRoleInfoByStatus",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER, SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public Object updateByCondition(SdOrganizationRole sdOrganizationRole){
        try {
            sdOrganizationRoleService.updateByCondition(sdOrganizationRole);
            logger.info("审核成员信息接口更新成功");
            return actionResult(Code.OK,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("审核成员信息接口更新失败");
            return actionResult(Code.INTERNAL_SERVER_ERROR,"更新失败");
        }
    }

    /**
     * 5.15获取团队用户信息（团队成员，团队采集任务数）
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/findOrgInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object findOrgInfo(Integer orgId){
        Map<String,Object> map = new HashMap<>();
        try {
            map = sdOrganizationService.findOrgInfoById(orgId);
            return actionResult(Code.OK,"获取成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 5.16 获取团队管理员首页的表格中已提交审核的数据
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/findOrgTaskInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object findOrgTaskInfo(Integer orgId){
        List<Map<String,Object>> liMap = new ArrayList<>();
        try {
            liMap = sdOrganizationService.findOrgTaskInfoById(orgId);
            return actionResult(Code.OK,"获取成功",liMap);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 5.17获取某任务的饼状图信息
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/orgPiechartInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.ORGANIZATION)
    @RequiresAuthentication
    public Object orgPiechartInfo(Integer taskId,Integer orgId){
        Map<String, Object> liMap = new HashMap<>();
        try {
            liMap = sdOrganizationService.findOrgPiechartInfo(taskId,orgId);
            return actionResult(Code.OK,"获取成功",liMap);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }
}