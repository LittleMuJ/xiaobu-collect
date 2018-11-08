package com.xiaobu.web.system.dao;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.common.base.BaseDao;
import com.xiaobu.web.system.entity.SdDictionarydata;

import java.util.List;

/**
* 描述：标注平台用户DTO
* @author MuRunSen
* @date 2018-08-21 09:56:42
*/
@Mapper
public interface SdDictionarydataDao extends BaseDao<SdDictionarydata, Integer>{

    List<SdDictionarydata> findType(String type);
}