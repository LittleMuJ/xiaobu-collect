package com.xiaobu.web.system.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.system.dao.SdConsumerDao;
import com.xiaobu.web.system.dao.SdManagerDao;
import com.xiaobu.web.system.dao.SdOrganizationDao;
import com.xiaobu.web.system.entity.SdConsumer;
import com.xiaobu.web.system.entity.SdManager;
import com.xiaobu.web.system.entity.SdOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaobu.web.system.dao.SdUserDao;
import com.xiaobu.web.system.entity.SdUser;
import com.xiaobu.web.system.service.SdUserService;

@Service("sdUserService")
public class SdUserServiceImpl implements SdUserService{

	@Autowired
	private SdUserDao sdUserDao;

    /**
     * 注入dao
     */
    @Autowired
    private SdManagerDao sdManagerDao;

    @Autowired
    private SdConsumerDao sdConsumerDao;

    @Autowired
    private SdOrganizationDao sdOrganizationDao;
	
	@Override
	public void insert(SdUser sdUser) {
		// TODO Auto-generated method stub
		sdUser.setCreatedAt(new Date());
		sdUser.setUpdatedAt(new Date());
		sdUserDao.insert(sdUser);
	}

	@Override
	public SdUser selectByname(String nickname) {
		// TODO Auto-generated method stub
		return sdUserDao.selectByname(nickname);
	}

	@Override
	public SdUser selectByphone(String phone) {
		// TODO Auto-generated method stub
		return sdUserDao.selectByphone(phone);
	}

    @Override
    public Map<String, Object> getUserInfo(Map<String,Object> map,String username) {
        Map<String,Object>  userInfo = new HashMap<>();
        //查詢系统用戶信息
        if(map.get("userType").equals(SysMessage.MANAGER)) {
                SdManager sdManager=sdManagerDao.selectByUsername(username);
                userInfo.put("userName",sdManager.getUsername());
                userInfo.put("userEmail",sdManager.getEmail());
                userInfo.put("userPhone",sdManager.getPhone());
                userInfo.put("userType",SysMessage.MANAGER);
                //查询甲方用户信息
            }else if(map.get("userType").equals(SysMessage.CONSUMER)){
                SdConsumer sdConsumer = sdConsumerDao.selectByUsername(username);
                userInfo.put("userName",sdConsumer.getUsername());
                userInfo.put("userEmail",sdConsumer.getEmail());
                userInfo.put("userPhone",sdConsumer.getPhone());
                userInfo.put("userType",SysMessage.CONSUMER);
                //查询团队用户信息
            }else {
                SdOrganization sdOrganization = sdOrganizationDao.selectByUsername(username);
                userInfo.put("userName",sdOrganization.getUsername());
                userInfo.put("userEmail",sdOrganization.getName());
                userInfo.put("userPhone",sdOrganization.getPhone());
                userInfo.put("userType",SysMessage.ORGANIZATION);
        }
        return userInfo;
    }

    @Override
    public Map<String, Object> getOrgAndCon() {
	    List<SdConsumer> sdConsumerList = sdConsumerDao.findByPage(null);

	    List<SdOrganization> sdOrganizationList = sdOrganizationDao.findByPage(null);

        Map<String,Object> map = new HashMap<>();

        map.put("consumerList",sdConsumerList);
        map.put("organizationList",sdOrganizationList);

        return map;
    }

    @Override
    public PageModel<SdUser> queryUserInfo(SdUser user, PageModel<SdUser> page) {
        PageHelper.offsetPage(page.getStart(),page.getLength());
        page.initData(sdUserDao.findByPage(user));
        return page;
    }

    @Override
    public void updateNotNull(SdUser user) {
        sdUserDao.updateByPrimaryKeySelective(user);
    }

}
