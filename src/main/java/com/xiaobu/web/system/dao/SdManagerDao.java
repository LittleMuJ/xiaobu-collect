package com.xiaobu.web.system.dao;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.common.base.BaseDao;
import com.xiaobu.web.system.entity.SdManager;

/**
* 描述：标注平台用户DTO
* @author MuRunSen
* @date 2018-07-31 11:56:39
*/
@Mapper
public interface SdManagerDao extends BaseDao<SdManager, Integer>{

	SdManager selectByUsername(String username);

	SdManager selectByphone(String phone);

	int insertAndGetId(SdManager sdManager);

}