package com.xiaobu.web.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaobu.common.base.BaseController;
import com.xiaobu.common.config.Code;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.util.JwtManager;
import com.xiaobu.common.util.MD5Util;
import com.xiaobu.common.util.RandomUtil;
import com.xiaobu.web.redis.service.RedisInfoService;
import com.xiaobu.web.system.entity.SdManager;
import com.xiaobu.web.system.entity.SdOrganization;
import com.xiaobu.web.system.service.SdManagerService;
import com.xiaobu.web.system.service.SdOrganizationService;
import com.xiaobu.web.system.service.SdUserService;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/signup")
public class SignupController extends BaseController{

	@Autowired
	private SdUserService sdUserService;
	
	@Autowired
	private RedisInfoService redisService;
	
	@Autowired
	private SdOrganizationService sdOrganizationService;
	 
	@Autowired
	private SdManagerService sdManagerService;
	
    @RequestMapping(value="/init",method = RequestMethod.GET)
    @ResponseBody
    public String init(HttpServletRequest request){
        System.out.println("跳转到注册页面");
        return "访问成功";
    }
    
    /**
      * 1.1管理员用户注册
     * SdManager
     * @param
     * @return
     */
    @RequestMapping(value="/signupManager",method = RequestMethod.POST)
    @ResponseBody
    public Object signupManager(SdManager sdManager) {
    	//查询redis验证码是否正确
    	if(!sdManager.getvCode().equals(redisService.getString(sdManager.getPhone()))) {
    		return actionResult(Code.BAD_REQUEST,SysMessage.VERIFICATION_CODE_ERROR);
    	}
    	//注册前检查用户名是否重复
    	if(sdManagerService.selectByUsername(sdManager.getUsername())!=null) {
    		return actionResult(Code.BAD_REQUEST,SysMessage.SIGNUP_USER_EXIST);
    	}
    	//注册前检查电话号码是否已被使用
    	if(sdManagerService.selectByphone(sdManager.getPhone())!=null) {
    		return actionResult(Code.BAD_REQUEST,SysMessage.SIGNUP_PHONE_EXIST);
    	}
    	
    	//获取十位随机串
    	String randStr = RandomUtil.createRandomChar(10);
    	//对用户表单输入密码进行首次md5加密
    	String md5Password = MD5Util.MD5(sdManager.getPassword());
    	//将加密过的password进行与随机数randStr进行一次md5加密
    	String passWordandSalt = MD5Util.MD5(md5Password+randStr);
    	//将随机生成的字符串截取前五位与后五位
    	String before5 = randStr.substring(0, 5);
    	String after5 = randStr.substring(5); 
    	//最终用户存进数据库的密码
    	String user_password = before5+passWordandSalt+after5;
    	sdManager.setPassword(user_password);
    	
    	try {
    		sdManagerService.insertAndGetId(sdManager);
    		int userId= sdManager.getId();
    		String token =JwtManager.createToken(sdManager.getUsername(),userId,SysMessage.MANAGER);
			return actionResult(Code.OK,token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return actionResult(Code.INTERNAL_SERVER_ERROR);
		}
    }
    
    /**
	 * 失效接口，目前团队成员只支持管理员分配添加
      * 团队成员注册接口
     */
    @RequestMapping(value="/signupOrganization",method = RequestMethod.POST)
    @ResponseBody
    public Object signupOrganization(SdOrganization sdOrganization) {
    	if(sdOrganizationService.selectByUsername(sdOrganization.getUsername())!=null) {
    		return actionResult(Code.BAD_REQUEST,SysMessage.SIGNUP_USER_EXIST);
    	}
    	
    	//获取随机串
    	String randStr = RandomUtil.createRandomChar(10);
    	
    	//对用户表单输入密码进行首次md5加密
    	String md5Password = MD5Util.MD5(sdOrganization.getPassword());
    	//将加密过的password进行与随机数randStr进行一次md5加密
    	String passWordandSalt = MD5Util.MD5(md5Password+randStr);
    	//将随机生成的字符串截取前五位与后五位
    	String before5 = randStr.substring(0, 5);
    	String after5 = randStr.substring(5); 
    	//最终用户存进数据库的密码
    	String user_password = before5+passWordandSalt+after5;
    	sdOrganization.setPassword(user_password);
    	try {
			sdOrganizationService.insertAndGetId(sdOrganization);
			int userId= sdOrganization.getId();
			String token =JwtManager.createToken(sdOrganization.getUsername(),userId,SysMessage.ORGANIZATION);
			return actionResult(Code.OK,token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return actionResult(Code.INTERNAL_SERVER_ERROR);
		}
    }
}
