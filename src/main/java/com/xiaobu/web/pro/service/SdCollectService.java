package com.xiaobu.web.pro.service;

import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.pro.entity.SdCollect;

import java.util.Map;

/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-08-31 16:22:04
*/
public interface SdCollectService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    SdCollect getById(Integer id)throws Exception;

    //SdCollectDTO createSdCollect(SdCollectDTO sdCollectDTO) throws Exception;
    void add(SdCollect sdCollect);

    void delete(Integer id) throws Exception;

    //SdCollectDTO updateSdCollect(SdCollectDTO sdCollectDTO) throws Exception;

    void update(SdCollect sdCollect);
    //平台管理员
    PageModel<SdCollect> selectExamineCollectInfo(Integer id, PageModel<SdCollect> page);
    void updateStatus(Integer taskId,Integer id, Integer status);

    //获取平台管理员审核情况
    Map<String,Object> findExamineInfoByTaskId(Integer taskId);

    //团队管理员
    PageModel<SdCollect> selectExaminOrgeCollectInfo(SdCollect sdCollect, PageModel<SdCollect> page);
    void examineOrgCollect(Integer taskId,Integer id, Integer status);




    //甲方管理员
    PageModel<SdCollect> selectCunExamineCollectInfo(Integer id, PageModel<SdCollect> page);


    //平台管理员查询过关率
    double findSuccessPorb(Integer taskId);

    //甲方管理员管理员查询过关率
    double findConSuccessPorb(Integer taskId);

    Map<String,Object> findDefeateDetails(Integer taskId);

    Integer findErrorNumByTaskId(Map<String,Object> parmMap);

    Map<String,Object> findConExamineInfoByTaskId(Integer taskId);
}