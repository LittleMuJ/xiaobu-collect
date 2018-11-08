package com.xiaobu.web.system.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import com.xiaobu.common.constant.SessionAttr;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.util.JwtManager;
import com.xiaobu.common.util.MD5Util;

import com.xiaobu.web.system.entity.SdConsumer;
import com.xiaobu.web.system.entity.SdManager;
import com.xiaobu.web.system.entity.SdOrganization;
import com.xiaobu.web.system.entity.SdUser;
import com.xiaobu.web.system.service.SdConsumerService;
import com.xiaobu.web.system.service.SdManagerService;
import com.xiaobu.web.system.service.SdOrganizationService;
import com.xiaobu.web.system.service.SdUserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/api/login")
public class LoginController extends BaseController {
	
    @Autowired
    private SdUserService sdUserService;
    
    @Autowired
	private SdOrganizationService sdOrganizationService;
	 
	@Autowired
	private SdManagerService sdManagerService;

    @Autowired
    private SdConsumerService sdConsumerService;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 2.1系统管理员登录接口
     * @param request
     * @param username
     * @param password
     * @return
     */
	@RequestMapping(value="/checkManagerLogin",method=RequestMethod.POST)
	@ResponseBody
	public Object checkManagerLogin(HttpServletRequest request, HttpServletResponse response, String username, String password) {
		try {
			//查詢系统用戶信息
				SdManager sdManager=sdManagerService.selectByUsername(username);
				if(sdManager == null) {
					return actionResult(Code.BAD_REQUEST,SysMessage.LOGIN_USER_NOT_EXIST);
				}
            String	userPassword = sdManager.getPassword();
            Integer	userId = sdManager.getId();
				//查询甲方用户信息
			//首先對用戶表單中的password進行一次MD5加密
			String MD5password = MD5Util.MD5(password);
			//获取前五位
			String before5 = userPassword.substring(0, 5);
			//获取后五位
			String after5 = userPassword.substring(userPassword.length()-5);
			//获取剔除盐之后的password
			String pwd =  userPassword.substring(5, userPassword.length()-5);
			//生成注册時隨機生成的盐
			String salt = before5+after5;
			String passWordandSalt = MD5Util.MD5(MD5password+salt);
			//验证密码
			if(!pwd.equals(passWordandSalt)) {
				logger.info(username + SysMessage.LOGIN_USER_INFO_ERROR);
			    return actionResult(Code.BAD_REQUEST,SysMessage.LOGIN_USER_INFO_ERROR);
			}
			logger.info(username + SysMessage.LOGIN_SUCCESS);

			String token =JwtManager.createToken(username,userId,SysMessage.MANAGER);

            response.addHeader("Set-Cookie", "Token="+token+"; Path=/; HttpOnly");

			return actionResult(Code.OK,SysMessage.LOGIN_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return actionResult(Code.INTERNAL_SERVER_ERROR,SysMessage.INTERNAL_SERVER_ERROR);
		}
	}

    /**
     * 2.2甲方人员登录接口
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/checkConsumerLogin",method=RequestMethod.POST)
    @ResponseBody
    public Object checkConsumerLogin(HttpServletRequest request,String username,String password, HttpServletResponse response) {

        try {
            //查詢甲方用戶信息
                SdConsumer sdConsumer = sdConsumerService.selectByUsername(username);
                if(sdConsumer ==null){
                    return actionResult(Code.BAD_REQUEST,SysMessage.LOGIN_USER_NOT_EXIST);
                }
            String userPassword = sdConsumer.getPassword();
            Integer  userId = sdConsumer.getId();
                //查询团队用户信息
            //首先對用戶表單中的password進行一次MD5加密
            String MD5password = MD5Util.MD5(password);
            //获取前五位
            String before5 = userPassword.substring(0, 5);
            //获取后五位
            String after5 = userPassword.substring(userPassword.length()-5);
            //获取剔除盐之后的password
            String pwd =  userPassword.substring(5, userPassword.length()-5);
            //生成注册時隨機生成的盐
            String salt = before5+after5;
            String passWordandSalt = MD5Util.MD5(MD5password+salt);
            //验证密码
            if(!pwd.equals(passWordandSalt)) {
                logger.info(username + SysMessage.LOGIN_USER_INFO_ERROR);
                return actionResult(Code.BAD_REQUEST,SysMessage.LOGIN_USER_INFO_ERROR);
            }
            logger.info(username + SysMessage.LOGIN_SUCCESS);
            String token =JwtManager.createToken(username,userId,SysMessage.CONSUMER);
            response.addHeader("Set-Cookie", "Token="+token+"; Path=/; HttpOnly");
            return actionResult(Code.OK,SysMessage.LOGIN_SUCCESS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,SysMessage.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 2.3团队管理员登录接口
     * @param request
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/checkOrganizationLogin",method=RequestMethod.POST)
    @ResponseBody
    public Object checkOrganizationLogin(HttpServletRequest request,String username,String password, HttpServletResponse response) {

        try {
            //查詢团体用戶信息
                SdOrganization sdOrganization = sdOrganizationService.selectByUsername(username);
                if(sdOrganization == null) {
                    return actionResult(Code.BAD_REQUEST,SysMessage.LOGIN_USER_NOT_EXIST);
                }
            String userPassword = sdOrganization.getPassword();
            Integer userId = sdOrganization.getId();

            //首先對用戶表單中的password進行一次MD5加密
            String MD5password = MD5Util.MD5(password);
            //获取前五位
            String before5 = userPassword.substring(0, 5);
            //获取后五位
            String after5 = userPassword.substring(userPassword.length()-5);
            //获取剔除盐之后的password
            String pwd =  userPassword.substring(5, userPassword.length()-5);
            //生成注册時隨機生成的盐
            String salt = before5+after5;
            String passWordandSalt = MD5Util.MD5(MD5password+salt);
            //验证密码
            if(!pwd.equals(passWordandSalt)) {
                logger.info(username + SysMessage.LOGIN_USER_INFO_ERROR);
                return actionResult(Code.BAD_REQUEST,SysMessage.LOGIN_USER_INFO_ERROR);
            }
            logger.info(username + SysMessage.LOGIN_SUCCESS);
            String token =JwtManager.createToken(username,userId,SysMessage.ORGANIZATION);
            response.addHeader("Set-Cookie", "Token="+token+"; Path=/; HttpOnly");
            return actionResult(Code.OK,SysMessage.LOGIN_SUCCESS);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return actionResult(Code.INTERNAL_SERVER_ERROR,SysMessage.INTERNAL_SERVER_ERROR);
        }
    }
	/**
	 * 登出
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		SdUser user = (SdUser) session.getAttribute(SessionAttr.USER_LOGIN.getValue());
		logger.info(user.getUsername()+SysMessage.LOGIN_USER_OUT);
		session.removeAttribute(SessionAttr.USER_LOGIN.getValue());
		session.removeAttribute(SessionAttr.USER_ROLES.getValue());
		session.removeAttribute(SessionAttr.USER_MENUS.getValue());
		
		return "/pages/login";
	}*/

    /*@RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public Object logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return actionResult(Code.OK,"成功注销！");
    }*/
	
	
}
