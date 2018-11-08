package com.xiaobu.web.system.controller;

import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import com.xiaobu.common.config.Collect;
import com.xiaobu.common.config.CollectTask;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.common.util.PasswordUtil;
import com.xiaobu.web.pro.entity.SdCollect;
import com.xiaobu.web.pro.entity.SdCollectTask;
import com.xiaobu.web.pro.service.SdCollectService;
import com.xiaobu.web.pro.service.SdCollectTaskService;
import com.xiaobu.web.system.entity.SdConsumer;
import com.xiaobu.web.system.service.SdConsumerService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：标注平台用户controller
 * @author MuRunSen
 * @date 2018-08-08 09:39:32
 */
@Controller
@RequestMapping("/api/consumer")
public class SdConsumerController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(SdConsumerController.class);

    @Autowired
    private SdConsumerService sdConsumerService;

    @Autowired
    private SdCollectTaskService sdCollectTaskService;

    @Autowired
    private SdCollectService sdCollectService;


    /**
     * 6.1甲方管理员采集任务列表分页接口，甲方id即可查询
     * @param sdCollectTask
     * @param page
     * @return
     */
    @RequestMapping(value = "/collectTaskList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public Object collectTaskList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page){
        try {
            PageModel<SdCollectTask> pages = sdCollectTaskService.selectCumCollect(sdCollectTask,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 6.2甲方验收页面List分页
     * @param sdCollectTask
     * @param page
     * @return
     */
    @RequestMapping(value = "/examineCollectList",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public Object examineCollectList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page){
        try {
            PageModel<SdCollectTask> pages = sdCollectTaskService.selectCunExamineCollect(sdCollectTask,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }


    /**
     * 6.3甲方管理员验收详情接口
     * @param id
     * @param page examineLen
     * @param
     * @return
     */
    @RequestMapping(value = "/examineCollectInfo",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER})
    @RequiresAuthentication
    public Object examineCollectInfo(Integer id, PageModel<SdCollect> page){

        try {
            PageModel<SdCollect> pages = sdCollectService.selectCunExamineCollectInfo(id,page);
            return actionResult(Code.OK,"获取成功",pages);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
    }

    /**
     * 6.4 甲方管理员验收接口，甲方管理管理员验收的平台管理员提交验收的任务
     * @param id   collect表单条记录id
     * @param status 验收状态值 8（验收成功），9（验收成功）
     * @return
     */
    @RequestMapping(value = "/examineCollect",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER})
    @RequiresAuthentication
    public Object examineCollect(SdCollect collect){

        try {
            //如果状态为验收失败，那么相对应的任务的完成数就要减1
            if(collect.getStatus()==Collect.ACCEPTANCE_ERROR.value()){
                SdCollectTask task = sdCollectTaskService.getById(collect.getTaskId());
                if(task.getDoneNum()>0){
                task.setDoneNum(task.getDoneNum()-1);
                sdCollectTaskService.update(task);
                }
            }
            sdCollectService.update(collect);
            return actionResult(Code.OK,"验收成功");
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR, "验收失败");
        }
    }

    /**
     * 6.5甲方管理员账号登录查看自己的信息，根据id
     * @param id
     * @return
     */
    @RequestMapping(value = "/findConsumerInfoById",method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public Object consumerInfoById(Integer id) {
        try {
            SdConsumer sdConsumer = sdConsumerService.getById(id);
            return actionResult(Code.OK, "获取成功",sdConsumer);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR, "获取失败");
        }
    }

    /**
     * 6.6 甲方管理员验收整条任务的通过与不通过 状态值是7验收失败(甲方管理员验收)->8验收成功(甲方管理员验收)
     * @param taskId
     * @param status
     * @return
     */
    @RequestMapping(value = "/examineCollectTask")
    @ResponseBody
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public Object examineCollectTask(Integer taskId,Integer status){
        try {
            if(taskId!=null && status!=null){
                //当为验收成功时判断通过率
                if(status==CollectTask.ACCEPTANCE_SUCCESS.value()){
                    double math=  sdCollectService.findConSuccessPorb(taskId);
                    if(math<SysMessage.PORB){
                        return actionResult(Code.BAD_REQUEST,"当前任务的通过率未达标");
                    }
                }else{
                    List<SdCollectTask> childTask = sdCollectTaskService.findTaskByParentId(taskId);
                    for(SdCollectTask ta:childTask){
                        ta.setStatus(CollectTask.ACCEPTANCE_ERROR.value());
                        sdCollectTaskService.update(ta);
                    }
                }
                SdCollectTask task = new SdCollectTask();
                task.setId(taskId);
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
     * 6.7 获取甲方管理员首页信息
     * @param ConId
     * @return
     */
    @RequestMapping(value = "/findConInfo",method =RequestMethod.POST )
    @ResponseBody
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public Object findConInfo(Integer ConId){
        Map<String,Object> map = new HashMap<>();
        try {
            if(ConId !=null){
            map = sdConsumerService.findConInfoById(ConId);
            return actionResult(Code.OK,"获取成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取失败");
        }
        return actionResult(Code.BAD_REQUEST,"参数出错");
    }

    /**
     * 6.8获取甲方管理员审核的总情况
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/findExamineInfo" ,method = RequestMethod.POST)
    @ResponseBody
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public Object findExamineInfo(Integer taskId){
        Map<String,Object> resultMap = new HashMap<>();
        try {
            resultMap = sdCollectService.findConExamineInfoByTaskId(taskId);
            return actionResult(Code.OK,"获取成功",resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,"获取错误");
        }
    }
}