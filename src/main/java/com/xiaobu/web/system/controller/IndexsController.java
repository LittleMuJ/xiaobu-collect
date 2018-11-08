package com.xiaobu.web.system.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.util.JwtManager;
import com.xiaobu.web.pro.service.SdCollectTaskService;
import com.xiaobu.web.system.service.SdConsumerService;
import com.xiaobu.web.system.service.SdManagerService;
import com.xiaobu.web.system.service.SdOrganizationService;
import com.xiaobu.web.system.service.SdUserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexsController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(IndexsController.class);
    @Autowired
    private SdOrganizationService sdOrganizationService;

    @Autowired
    private SdManagerService sdManagerService;

    @Autowired
    private SdConsumerService sdConsumerService;

    @Autowired
    private SdUserService sdUserService;

    @Autowired
    private SdCollectTaskService sdCollectTaskService;

    public Map<String,Object> userInfoGet(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = null;
        if(cookies != null && cookies.length>0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("Token")){
                    token  = cookie.getValue();
                }
            }
        }
        //查詢系统用戶信息
        Map<String,Object> map = JwtManager.TokenDecompose(token);
        String username = map.get("username").toString();
        Map<String,Object>  userInfo = sdUserService.getUserInfo(map,username);
        userInfo.put("userId",map.get("userId"));
        return userInfo;
    }

    /**
     * 时间定时起方法，定时每天凌晨6点执行一次方法
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateTaskStatus(){
        logger.info("执行任务状态更改");
        sdCollectTaskService.updateTaskStatus();
    }

    /**
     * 默认访问登录页
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/",method=RequestMethod.GET)
    public String loginHtml(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("----进入登录页----");
        return "index";
    }

    /**
     * 进入主页面
     * @param request
     * @return
     */
    @RequestMapping(value="main",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginMain(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "main/index";
    }

    /**
     * 进入用户详情页面
     * @param request
     * @return
     */
    @RequestMapping(value="authInfo",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginAuthInfo(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "authInfo/index";
    }

    /**
     * 进入甲方验收页面
     * @param request
     * @return
     */
    @RequestMapping(value="collect/acceptance",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectAcceptance(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/acceptance/index";
    }

    @RequestMapping(value="collect/acceptance/audio",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectAcceptanceAudio(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/acceptance/audio/index";
    }

    @RequestMapping(value="collect/acceptance/pictrue",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectAcceptancePictrue(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/acceptance/pictrue/index";
    }

   /* @RequestMapping(value="collect/check",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectCheck(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/check/index";
    }*/

    @RequestMapping(value="collect/check/audio",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectCheckAudio(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/check/audio/index";
    }

    @RequestMapping(value="collect/check/pictrue",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectCheckPictrue(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/check/pictrue/index";
    }

    @RequestMapping(value="collect/consumerlist",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectConsumerlist(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/consumerlist/index";
    }

    @RequestMapping(value="collect/consumerlist/detail",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectConsumerlistDetail(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/consumerlist/detail/index";
    }

    @RequestMapping(value="collect/inspection",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectInspection(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/inspection/index";
    }

    @RequestMapping(value="collect/inspection/audio",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectInspectionAudio(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/inspection/audio/index";
    }

    @RequestMapping(value="collect/inspection/pictrue",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.CONSUMER)
    @RequiresAuthentication
    public String loginCollectInspectionPictrue(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/inspection/pictrue/index";
    }

    /**
     * 进入发布采集任务页面
     * @param request
     * @return
     */
    @RequestMapping(value="collect/release",method=RequestMethod.GET)
    @RequiresRoles(SysMessage.MANAGER)
    @RequiresAuthentication
    public String loginCollect(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/release/index";
    }

    /**
     * 进入采集任务拆分页面
     * @param request
     * @return
     */
    @RequestMapping(value="collect/list/split",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginSplitCollect(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/list/split/index";
    }

    @RequestMapping(value="collect/list/detail",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginSplitCollectDetail(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/list/detail/index";
    }

    /**
     * 进入采集任务修改页面
     * @param request
     * @return
     */
    @RequestMapping(value="collect/list/edit",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginEditCollect(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/list/edit/index";
    }

    /**
     * 采集任务列表
     * @param request
     * @return
     */
    @RequestMapping(value="collect/list",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginSplitTaskList(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/list/index";
    }

    /**
     * 采集任务审核页面
     * @param request
     * @return
     */
    @RequestMapping(value="collect/check",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginCollectExamine(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/check/index";
    }

    /**
     * 采集任务审核音频页面
     * @param request
     * @return
     */
    @RequestMapping(value="collect/audio/check",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginCollectExamineAudio(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/audio/check/index";
    }

    /**
     * 采集任务审核图片页面
     * @param request
     * @return
     */
    @RequestMapping(value="collect/pictrue/check",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginCollectExaminePictrue(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/pictrue/check/index";
    }

    /**
     * 甲方列表页
     * @param request
     * @return
     */
    @RequestMapping(value="consumer/list",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginConsumerList(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "consumer/list/index";
    }

    /**
     * 甲方信息页
     * @param request
     * @return
     */
    @RequestMapping(value="consumer/information",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginConsumerInformation(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "consumer/information/index";
    }

    /**
     * 新增甲方页
     * @param request
     * @return
     */
    @RequestMapping(value="consumer/new",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginConsumerNew(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "consumer/new/index";
    }

    /**
     * 新增标注任务
     * @param request
     * @return
     */
    @RequestMapping(value="label/task",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginLabelNewTask(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "label/task/index";
    }

    /**
     * 标注任务List
     * @param request
     * @return
     */
    @RequestMapping(value="label/list",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginLabelTaskList(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "label/list/index";
    }

    /**
     * 标注审核页
     * @param request
     * @return
     */
    @RequestMapping(value="label/examine",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginLabelExamine(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "label/examine/index";
    }

    /**
     * 团队新增页
     * @param request
     * @return
     */
    @RequestMapping(value="organization/new",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginOrganizationNew(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "organization/new/index";
    }

    /**
     * 团队List
     * @param request
     * @return
     */
    @RequestMapping(value="organization/list",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginOrganizationList(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "organization/list/index";
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value="collect/organizationlist",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginCollectOrganizationList(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/organizationlist/index";
    }

    @RequestMapping(value="collect/organizationlist/detail",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginCollectOrganizationListDetail(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/organizationlist/detail/index";
    }

    @RequestMapping(value="collect/organizationlist/getdata",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginCollectOrganizationListGetdata(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "collect/organizationlist/getdata/index";
    }

    /**
     * 团队成员审核
     * @param request
     * @return
     */
    @RequestMapping(value="organization/examine",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginOrganizationExamine(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "organization/examine/index";
    }

    /**
     * 团队成员列表
     * @param request
     * @return
     */
    @RequestMapping(value="organization/member",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginOrganizationMenber(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "organization/member/index";
    }

    /**
     * 新增标注任务
     * @param request
     * @return
     */
    @RequestMapping(value="register",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String loginRegister(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "register/index";
    }

    @RequestMapping(value="information",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String Information(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "information/index";
    }

    @RequestMapping(value="member",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String Member(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "member/index";
    }

    @RequestMapping(value="member/examine",method=RequestMethod.GET)
    @RequiresRoles(logical = Logical.OR, value = {SysMessage.MANAGER,SysMessage.CONSUMER,SysMessage.ORGANIZATION})
    @RequiresAuthentication
    public String MemberExamine(HttpServletRequest request) {

        request.setAttribute("userInfo", userInfoGet(request));

        return "member/examine/index";
    }
}
