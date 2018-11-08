package com.xiaobu.web.system.controller;
import com.xiaobu.common.config.Collect;
import com.xiaobu.common.config.CollectTask;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.common.util.PasswordUtil;
import com.xiaobu.web.pro.entity.SdCollect;
import com.xiaobu.web.pro.entity.SdCollectTask;
import com.xiaobu.web.pro.service.SdCollectService;
import com.xiaobu.web.pro.service.SdCollectTaskService;
import com.xiaobu.web.redis.service.RedisInfoService;
import com.xiaobu.web.redis.service.RedisTaskService;
import com.xiaobu.web.system.entity.SdConsumer;
import com.xiaobu.web.system.entity.SdOrganization;
import com.xiaobu.web.system.entity.SdUser;
import com.xiaobu.web.system.service.*;
import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户controller
* @author MuRunSen
* @date 2018-07-31 11:56:39
*/
@Controller
@RequestMapping("/api/manager")
public class SdManagerController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(SdManagerController.class);

    @Autowired
    private SdManagerService sdManagerService;

    @Autowired
    private RedisTaskService redisService;

    @Autowired
    private SdCollectTaskService sdCollectTaskService;

    @Autowired
    private SdConsumerService sdConsumerService;

    @Autowired
    private SdDictionarydataService sdDictionarydataService;

    @Autowired
    private SdOrganizationService sdOrganizationService;

    @Autowired
    private SdUserService sdUserService;

    @Autowired
    private SdCollectService sdCollectService;


   /* *//**
    * 描述：根据Id 查询
    * @param id  标注平台用户id
    *//*
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object findById(@PathVariable("id") Integer id)throws Exception {
        SdManager sdManager = sdManagerService.getById(id);
        
        return actionResult(Code.OK,sdManager);
    }

    *//**
    * 描述：删除标注平台用户
    * @param id 标注平台用户id
    *//*
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object deleteById(@PathVariable("id") Integer id) throws Exception {
    try {
        	sdManagerService.delete(id);
    	} catch (Exception e) {
		
		logger.error(e.getMessage(), e);
		return actionResult(Code.OK);
	}
	return actionResult(Code.INTERNAL_SERVER_ERROR);
  }*/
    /***********************************************************************平台管理员********************************************************************************/
    /**
     * 4.1.发布采集任务接口及拆分接口
     * 当发布公网任务时organizationId不传，当直接发布给团体时以及拆分给团体时需传organizationId
     */
    @RequestMapping(value = {"/issueCollectTask"},method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object issueCollectTask(@Valid SdCollectTask sdCollectTask, BindingResult bindingResult){
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date newdate = df.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            if (bindingResult.hasErrors()){
                return actionResult(Code.BAD_REQUEST,"请完成表单填写");
            }
            sdCollectTask.setDoneNum(0);
            //发布主任务时
            if(sdCollectTask.getParentId()==null){
                if(newdate.getTime() ==sdCollectTask.getStartTime().getTime()||newdate.getTime()>sdCollectTask.getStartTime().getTime()){
                    //发布任务，设置状态为2
                    sdCollectTask.setStatus(CollectTask.IS_START.value());
                }else{
                    //发布任务，设置状态为1
                    sdCollectTask.setStatus(CollectTask.IS_ISSUE.value());
                }
                //发布子任务时
            }else{
                sdCollectTask.setStatus(CollectTask.IS_ISSUE.value());
            }

            sdCollectTaskService.add(sdCollectTask);
            return actionResult(Code.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 4.2.平台管理员采集任务列表分页
     * @param sdCollectTask
     * @param page
     * @return
     */
    @RequestMapping(value = "/collectTaskList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object collectTaskList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page){
        try {
            PageModel<SdCollectTask> pages = sdCollectTaskService.selectCollectTaskInfo(sdCollectTask,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 4.3.平台管理员查询审核页面List分页（查询散户任务以及团体提交审核的任务）
     * 平台管理员查询审核List，查询organization_id为空的公网任务以及状态值为3的团队管理员提交的待审核状态
     * @param sdCollectTask
     * @param page
     * MuRunSen
     * 平台管理员审核List接口
     * @return
     */
    @RequestMapping(value = "/examineCollectList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object examineCollectList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page){
        try {
            PageModel<SdCollectTask> pages = sdCollectTaskService.selectExamineCollect(sdCollectTask,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 4.4.平台管理员审核详情接口
     * @param taskId
     * @param page
     * @param
     * @return
     */
    @RequestMapping(value = "/examineCollectInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER})
    @RequiresAuthentication
    public Object examineCollectInfo(Integer taskId, PageModel<SdCollect> page){

        try {
            PageModel<SdCollect> pages = sdCollectService.selectExamineCollectInfo(taskId,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 4.5.平台管理员审核接口(因平台管理员审核的单表sd_collect数据，只是状态值传值不同平台管理员传6或7，
     * @return
     */
    @RequestMapping(value = "/examineCollect",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public Object examineCollect(Integer taskId,Integer id,Integer status){

        try {
            sdCollectService.updateStatus(taskId,id,status);
            return actionResult(Code.OK,"审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"审核失败");
        }
    }

    /**
     * 4.6.此接口是在填写任务表单时用做查询团队、甲方信息，权限为平台管理员
     * @return
     */
    @RequestMapping(value = "/orgAndConInfo",method = RequestMethod.GET)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object selectOrgAndCon(){
        Map<String,Object> map = new HashMap<>();
        try {
            map = sdUserService.getOrgAndCon();
            return actionResult(Code.OK,"获取成功",map);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"后台发生错误");
        }
    }

    /**
     * 4.7.平台管理员更新采集任务接口，权限为只有平台管理员可访问
     * @param sdCollectTask
     * @return
     */
    @RequestMapping(value = "/updateCollectTask",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object updateCollectTask(SdCollectTask sdCollectTask){
        try {
            //当是子任务时并且状态为11（废弃）时
            if(sdCollectTask.getParentId()!=null && sdCollectTask.getStatus()==CollectTask.DISCARD.value()){
                    SdCollectTask task = sdCollectTaskService.getById(sdCollectTask.getParentId());
                    //将子任务的份额数添回主任务
                    task.setSurplusNum(task.getSurplusNum()+sdCollectTask.getCount());
                    task.setResidueCount(task.getResidueCount()+sdCollectTask.getCount());
                    sdCollectTaskService.update(task);

            }else if(sdCollectTask.getParentId()==null && sdCollectTask.getStatus()==CollectTask.DISCARD.value()){
                List<SdCollectTask> childTask = sdCollectTaskService.findTaskByParentId(sdCollectTask.getId());
                if(childTask.size()>0){
                    for(SdCollectTask c:childTask){
                        c.setStatus(CollectTask.DISCARD.value());
                        sdCollectTaskService.update(c);
                    }
                }
            }
            //主任务重启时
            if(sdCollectTask.getStatus()==CollectTask.IS_START.value()&& sdCollectTask.getUpdateType().equals("restart")){
                //根据id查询当前重启的任务信息
                //查询当前任务的验收失败个数
                Map<String,Object> parmMap = new HashMap<>();
                parmMap.put("status",Collect.ACCEPTANCE_ERROR.value());
                parmMap.put("taskId",sdCollectTask.getId());
                Integer num = sdCollectService.findErrorNumByTaskId(parmMap);
                sdCollectTask.setDoneNum(0);
                sdCollectTask.setResidueCount(num);
            }
            //子任务重启时
            /*if(sdCollectTask.getParentId()!=null && sdCollectTask.getStatus()==CollectTask.INSPECTION_ERROR.value()){

                Map<String,Object> parmMap = new HashMap<>();
                parmMap.put("status",Collect.ACCEPTANCE_ERROR.value()+","+Collect.INSPECTION_ERROR.value());
                parmMap.put("taskId",sdCollectTask.getId());
                Integer num = sdCollectService.findErrorNumByTaskId(parmMap);
                sdCollectTask.setDoneNum(0);
                sdCollectTask.setResidueCount(num);
            }*/
            sdCollectTaskService.update(sdCollectTask);
            return actionResult(Code.OK,"更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"更新失败");
        }
    }

    /**
     * 4.8.根据Id撤销采集任务接口(删除任务)
     * @param id
     * @return
     */
    @RequestMapping(value = "/revocationCollectTask",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object revocationCollectTask(Integer id){
        try {
            if(redisService.getString(id.toString())!=null){
                redisService.remove(id.toString());
            }
            sdCollectTaskService.delete(id);
            return actionResult(Code.OK,"撤销成功");
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"撤销失败");
        }
    }

    /**
     * 4.9 提交采集任务至甲方管理员验收接口
     * @param
     * @param taskId   整体任务id
     * @return
     */
    @RequestMapping(value = "/submitCollectTask",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object submitCollectTask(Integer taskId){
        try {
            String msg = sdCollectTaskService.submitTaskToCunsumer(taskId);
            return actionResult(Code.OK,msg);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"提交失败");
        }
    }

    /**
     * 4.10 甲方信息列表List接口（分页）
     * @Author luxinli
     */
    @RequestMapping(value = {"/queryConsumerList"},method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object selectConsumerInfo(SdConsumer sdConsumers, PageModel<SdConsumer> page){

        try {
            PageModel<SdConsumer> pages = sdConsumerService.selectConsumerInfos(sdConsumers,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 4.11 新增甲方信息接口
     * @Author luxinli
     */
    @RequestMapping(value = "/addConsumerInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object insertConsumerInfo(SdConsumer sdConsumer){
        try {
            //判断该用户是否存在，不存在新增
            if(sdConsumerService.selectByUsername(sdConsumer.getUsername()) != null ){
                logger.info("该用户已存在");
                return actionResult(Code.BAD_REQUEST,SysMessage.SIGNUP_USER_EXIST);
            }
            else{
                //不存在插入该用户信息
                //对用户的密码进行加密
                String encryption_pwd =  PasswordUtil.encryptionPwd(sdConsumer.getPassword());
                sdConsumer.setPassword(encryption_pwd);
                sdConsumerService.insertConsumerInfo(sdConsumer);
                logger.info(sdConsumer.getUsername()+"新增成功");
                return actionResult(Code.OK,"新增成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info(sdConsumer.getUsername()+"新增失败");
            return  actionResult(Code.INTERNAL_SERVER_ERROR,"新增失败");
        }

    }


    /**
     * 4.12 修改甲方信息接口
     * @param sdConsumer
     * @Author luxinli
     * @return
     */
    @RequestMapping(value = "/modifyConsumerInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public  Object updateConsumerInfo(SdConsumer sdConsumer){
        try {
            sdConsumerService.updateConsumerInfo(sdConsumer);
            logger.info(sdConsumer.getUsername()+"修改成功");
            return actionResult(Code.OK,SysMessage.COMMON_UPDATE_SUCCESS);
        }catch (Exception e){
            logger.info(sdConsumer.getUsername()+"修改失败");
            return actionResult(Code.BAD_REQUEST,"修改失败");
        }

    }

    /**
     * 4.13 删除甲方信息接口
     * @param id
     * @Author luxinli
     * @return
     */
    @RequestMapping(value = "/removeConsumerInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object deleteConsumer(int id) {
        try {
            //删除之前查询该用户是否存在任务信息
            List<SdCollectTask> list = sdCollectTaskService.findByCustomerId(id);
            if(list.size() != 0){
                logger.info("该用户存在任务信息，暂时不可进行删除操作！");
                return actionResult(Code.CONFLICT,"该用户存在任务信息，暂时不可进行删除操作！");
            }
            sdConsumerService.deletConsumer(id);
            logger.info("删除成功");
            return actionResult(Code.OK, SysMessage.COMMON_DELETE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.BAD_REQUEST, "删除失败");
        }
    }

    /**
     * 4.14 团体组织信息List接口（分页）
     * @author luxinli
     */
    @RequestMapping(value = "/queryOrganizationList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object selectOrganizationInfo(SdOrganization sdOrganization, PageModel<SdOrganization> page){
        try {
            PageModel<SdOrganization> pages = sdOrganizationService.selectOrganizationInfo(sdOrganization,page);
            logger.info("查询成功");
            return actionResult(Code.OK,"获取成功",pages);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("查询失败");
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");

        }
    }

    /**
     * 4.15 新增团体组织信息接口
     * @author luxinli
     */
    @RequestMapping(value = "/addOrganizationInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public  Object insertOrganizationInfo(SdOrganization sdOrganization){
        try {
            //判断账号是否已在数据库存在
            if(sdOrganizationService.selectByUsername(sdOrganization.getUsername()) != null){
                logger.info(sdOrganization.getUsername()+"该用户已存在");
                return actionResult(Code.BAD_REQUEST,"用户名已存在");
            }
            else{
                //对用户密码进行加密
                String encryption_pwd =  PasswordUtil.encryptionPwd(sdOrganization.getPassword());
                sdOrganization.setPassword(encryption_pwd);
                sdOrganizationService.add(sdOrganization);
                logger.info(sdOrganization.getUsername()+"新增成功");
                return actionResult(Code.OK,SysMessage.COMMON_ADD_SUCCESS);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info(sdOrganization.getUsername()+"新增失败");
            return actionResult(Code.INTERNAL_SERVER_ERROR,"新增失败");

        }
    }


    /**
     * 4.16 修改团体组织信息接口
     * @author luxinli
     */
    @RequestMapping(value = "/modifyOrganizationInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object updateOrganizationInfo(SdOrganization sdOrganization){
        try {
            sdOrganizationService.update(sdOrganization);
            logger.info(sdOrganization.getName()+"修改成功");
            return actionResult(Code.OK,SysMessage.COMMON_UPDATE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(sdOrganization.getName()+"修改失败");
            return actionResult(Code.INTERNAL_SERVER_ERROR,"修改失败");
        }
    }

    /**
     * 4.17 根据id删除团体组织信息接口
     * @author luxinli
     */
    @RequestMapping(value = "/removeOrganizationInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object deleteOrganizationInfo(int id){
        try {
            //判断该用户是否还存在任务信息
            List<SdCollectTask> list = sdCollectTaskService.findByOrganizationId(id);
            if(list.size() != 0){
                logger.info("该用户存在任务信息，暂时不可进行删除操作！");
                return actionResult(Code.CONFLICT,"该用户存在任务信息，暂时不可进行删除操作！");
            }
            sdOrganizationService.delete(id);
            logger.info("删除成功");
            return actionResult(Code.OK,SysMessage.COMMON_DELETE_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除失败");
            return actionResult(Code.INTERNAL_SERVER_ERROR,"删除失败");
        }
    }

    /**
     * 4.18 在册人员信息
     * @author luxinli
     */
    @RequestMapping(value = "/userList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object userList(SdUser user,PageModel<SdUser> page){
        try {
            PageModel<SdUser> s = sdUserService.queryUserInfo(user,page);
            return actionResult(Code.OK,"获取成功",page);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }


    /**
     * 4.19 审核系统成员
     * @author MuRunSen
     */
    @RequestMapping(value = "/auditUser",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)//只有用户类型为manager的用户才可访问
    @RequiresAuthentication
    public Object auditUser(Integer id,Integer status){
        SdUser user=new SdUser();
        user.setId(id);
        user.setStatus(status);
        try {
            sdUserService.updateNotNull(user);
            return actionResult(Code.OK,"审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"审核失败");
        }
    }


    /**
     * 4.20 平台管理员审核整条任务的通过与不通过 状态值是4审核失败(平台管理员审核)->5审核成功(平台管理员审核)
     * @param taskId
     * @param status
     * @return
     */
    @RequestMapping(value = "examineCollectTask",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object examineCollectTask(Integer taskId,Integer status){
        try {
            if(taskId!=null && status!=null){
                SdCollectTask task = sdCollectTaskService.getById(taskId);
                //当为审核成功时判断通过率
                if(status==CollectTask.INSPECTION_PASS.value()){
                 double math=  sdCollectService.findSuccessPorb(taskId);
                 Double Porb = task.getResidueCount()*(1-task.getRuleError());
                 if(math<Porb){
                     return actionResult(Code.BAD_REQUEST,"当前任务的通过率未达标");
                    }
                }else{
                    if(task.getSubmitLimit()==null){
                        //审核失败之后的提交次数,首次提交之后为0
                        task.setSubmitLimit(0);
                    }else{
                        task.setSubmitLimit(task.getSubmitLimit()+1);
                    }

                }

                task.setStatus(status);
                sdCollectTaskService.update(task);
                return actionResult(Code.OK,"审核成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"审核失败");
        }
        return actionResult(Code.BAD_REQUEST,"参数出错");
    }

    /**
     * 4.21 获取平台管理员首页数据
     * @return
     */
    @RequestMapping(value = "/findSysInfo" ,method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object findSysInfo(){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            resultMap = sdCollectTaskService.findSysInfo();
            return actionResult(Code.OK,"获取成功",resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取错误");
        }
    }

    /**
     * 4.22获取平台管理员审核的总情况
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/findExamineInfo" ,method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object findExamineInfo(Integer taskId){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            resultMap = sdCollectService.findExamineInfoByTaskId(taskId);
            return actionResult(Code.OK,"获取成功",resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取错误");
        }
    }

    /**
     * 4.23 获取验收失败详情
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/defeateDetails")
    @ResponseBody
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public Object defeateDetails(Integer taskId){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            resultMap = sdCollectService.findDefeateDetails(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取错误");
        }
        return actionResult(Code.OK,"获取成功",resultMap);
    }
}