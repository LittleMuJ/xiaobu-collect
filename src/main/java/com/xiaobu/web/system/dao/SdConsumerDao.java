package com.xiaobu.web.system.dao;

import com.xiaobu.web.system.entity.SdManager;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.common.base.BaseDao;
import com.xiaobu.web.system.entity.SdConsumer;

import java.util.List;

/**
* 描述：标注平台用户DTO
* @author MuRunSen
* @date 2018-08-08 09:39:32
*/
@Mapper
public interface SdConsumerDao extends BaseDao<SdConsumer, Integer>{

    SdConsumer selectByUsername(String username);

    void  insertConsumerInfo(SdConsumer sdConsumer);

    void updateNotNull(SdConsumer sdConsumer);

}