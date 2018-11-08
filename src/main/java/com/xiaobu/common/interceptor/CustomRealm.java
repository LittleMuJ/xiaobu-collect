package com.xiaobu.common.interceptor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.xiaobu.common.config.Code;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.web.system.entity.SdConsumer;
import com.xiaobu.web.system.entity.SdManager;
import com.xiaobu.web.system.entity.SdOrganization;
import com.xiaobu.web.system.service.SdConsumerService;
import com.xiaobu.web.system.service.SdManagerService;
import com.xiaobu.web.system.service.SdOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaobu.common.util.JwtManager;

import com.xiaobu.web.system.service.SdUserService;




public class CustomRealm extends AuthorizingRealm{

	private Logger logger = LoggerFactory.getLogger(CustomRealm.class);
	
	@Autowired
	private SdUserService sdUserService;

    @Autowired
    private SdOrganizationService sdOrganizationService;

    @Autowired
    private SdManagerService sdManagerService;

    @Autowired
    private SdConsumerService sdConsumerService;


    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }
	/**
	 *  身份信息授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("————权限认证————");

        Map<String,Object> map = userVerify(principals.toString());
        if(StringUtils.isBlank(map.get("username").toString())){
            throw new AuthenticationException("token invalid");
        }else{

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            //获得该用户角色
            String role = map.get("userType").toString();
            Set<String> set = new HashSet<>();
            //需要将 role 封装到 Set 作为 info.setRoles() 的参数
            set.add(role);
            //设置该用户拥有的角色
            info.setRoles(set);
            return info;
        }
	}
	
	/**
	 * 
	 *身份信息验证
	 *Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
		// TODO Auto-generated method stub
		logger.info("身份验证方法");
        String token = (String) authenticationToken.getCredentials();
		Map<String,Object> map = JwtManager.TokenDecompose(token);
		if(StringUtils.isBlank(map.get("username").toString())){
            throw new AuthenticationException("token 过期！");
        }else{
            if(userVerify(token)==null){
                throw new AuthenticationException("token验证失败");
            }
        }
		return new SimpleAuthenticationInfo(token, token, "custom_realm");
	}


    /**
     * 验证用户
     * @param token
     * @return
     */
	public Map<String,Object> userVerify(String token){
        Map<String,Object> map = JwtManager.TokenDecompose(token);
        String username = map.get("username").toString();
        //查詢系统用戶信息
        if(map.get("userType").equals(SysMessage.MANAGER)) {
            SdManager sdManager=sdManagerService.selectByUsername(username);
            if(sdManager == null) {
                return null;
            }else{
                return map;
            }
            //查询甲方用户信息
        }else if(map.get("userType").equals(SysMessage.CONSUMER)){
            SdConsumer sdConsumer = sdConsumerService.selectByUsername(username);
            if(sdConsumer ==null){
                return null;
            }else{
                return map;
            }
            //查询团队用户信息
        }else {
            SdOrganization sdOrganization = sdOrganizationService.selectByUsername(username);
            if(sdOrganization == null) {
                return null;
            }else{
                return map;
            }
        }
    }
}
