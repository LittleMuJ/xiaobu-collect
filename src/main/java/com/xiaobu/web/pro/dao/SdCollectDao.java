package com.xiaobu.web.pro.dao;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.common.base.BaseDao;
import com.xiaobu.web.pro.entity.SdCollect;

import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户DTO
* @author MuRunSen
* @date 2018-08-31 16:22:04
*/
@Mapper
public interface SdCollectDao extends BaseDao<SdCollect, Integer>{

    Page<SdCollect> findExamineCollectInfo(Map<String,Object> parmMap);

    Page<SdCollect> findExamineOrgCollectInfo(Map<String,Object> parmMap);

    Page<SdCollect> findExamineOrgeCollectInfo(SdCollect sdCollect);

    Page<SdCollect> findExamineCuneCollectInfo(Map<String,Object> parmMap);

    List<SdCollect> finfCollectList(SdCollect collect);

    double findSuccessPorb( Map<String,Object> parmMap);

    double findConSuccessPorb(Map<String,Object> parmMap);

    //查询审核失败任务的实质完成数量
    int getDoneNumTask(Integer id);

    Map<String,Object> findExamineCountByTaskId(Map<String,Object> parmMap);

    //获取主任务失败详情
    Map<String,Object> findDefeateDetails(Map<String,Object> parmMap);

    List<Map<String, Object>> DefeateDetailsTable(Map<String,Object> parmMapTab);

    Integer findErrorNumByTaskId(Map<String,Object> parmMap);
}