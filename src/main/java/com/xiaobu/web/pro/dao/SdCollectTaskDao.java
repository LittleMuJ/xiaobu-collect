package com.xiaobu.web.pro.dao;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import com.xiaobu.common.base.BaseDao;
import com.xiaobu.web.pro.entity.SdCollectTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户DTO
* @author MuRunSen
* @date 2018-08-14 15:47:36
*/
@Mapper
public interface SdCollectTaskDao extends BaseDao<SdCollectTask, Integer>{

    List<SdCollectTask> findByCutomerId(@Param("id") int id);

    List<SdCollectTask> findByOraginationId(@Param("id") int id);

    Page<SdCollectTask> findExaminePage(SdCollectTask sdCollectTask);

    Page<SdCollectTask> findOrgExaminePage(SdCollectTask sdCollectTask);

    Page<SdCollectTask> findCollectPage(SdCollectTask sdCollectTask);

    Page<SdCollectTask> findReadyCollectPage(SdCollectTask sdCollectTask);

    Page<SdCollectTask> findtOrgPage(SdCollectTask sdCollectTask);

    Page<SdCollectTask> findCunExaminePage(SdCollectTask sdCollectTask);

    List<SdCollectTask> findCollectTaskByStatus(Integer status);


    List<SdCollectTask> findtaskByParentId(SdCollectTask sdCollectTask);

    Page<SdCollectTask> findCumCollectPage(SdCollectTask sdCollectTask);

    List<Map<String,Object>> findPieInfo(Map<String,Object> parmaMapPie);

    //查询甲方管理员首页头部信息
    Map<String,Object> findConHeadInfo(Integer conId);

    //查询甲方管理员首页进行中和已验收任务列表
    List<Map<String,Object>> findTaskIngAndACCEPTANCE( Map<String,Object> parmMap);

    List<Map<String,Object>> findtableInfo(Map<String,Object> parmaMap);

    Map<String,Object> findDataByMonth(Map<String,Object> month);
}