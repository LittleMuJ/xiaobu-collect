package com.xiaobu.web.system.service;

import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.system.entity.SdUser;

import java.util.Map;

public interface SdUserService {

	void insert(SdUser sdUser);

	SdUser selectByname(String nickname);

	SdUser selectByphone(String phone);


    Map<String,Object> getUserInfo(Map<String,Object> map,String username);

    Map<String,Object> getOrgAndCon();

    PageModel<SdUser> queryUserInfo(SdUser user, PageModel<SdUser> page);

    void updateNotNull(SdUser user);
}
