package com.xiaobu.web.system.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.web.system.entity.SdUser;

import java.util.Map;

@Mapper
public interface SdUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SdUser record);

    int insertSelective(SdUser record);

    SdUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SdUser record);

    int updateByPrimaryKey(SdUser record);

	SdUser selectByname(String nickname);

	SdUser selectByphone(String phone);

    Page<SdUser> findByPage(SdUser user);

    Map<String,Object> findPeopleInfo();
}