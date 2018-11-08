package com.xiaobu.web.pro.service;

import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.pro.entity.SdCollectTask;

import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-08-14 15:47:36
*/
public interface SdCollectTaskService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    SdCollectTask getById(Integer id)throws Exception;

    //SdCollectTaskDTO createSdCollectTask(SdCollectTaskDTO sdCollectTaskDTO) throws Exception;
    void add(SdCollectTask sdCollectTask);



    //SdCollectTaskDTO updateSdCollectTask(SdCollectTaskDTO sdCollectTaskDTO) throws Exception;

    List<SdCollectTask> findByCustomerId(int id);

    List<SdCollectTask> findByOrganizationId(int id);


    /**
     * 平台管理员
     */
    void update(SdCollectTask sdCollectTask);

    void delete(Integer id) throws Exception;

    PageModel<SdCollectTask> selectCollectTaskInfo(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page);

    PageModel<SdCollectTask> selectExamineCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page);

    String submitTaskToCunsumer(Integer taskId);


    //甲方管理员
    PageModel<SdCollectTask> selectCumCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page);

    PageModel<SdCollectTask> selectCunExamineCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page);

    void updateTaskStatus();

    //团队管理员
    PageModel<SdCollectTask> selectOrgCollectList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page);

    PageModel<SdCollectTask> selectReadyOrgCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page);

    void OrgReceptionCollect(SdCollectTask sdCollectTask);

    PageModel<SdCollectTask> selectOrgExamineCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page);

    String submitTaskToManager(Integer taskId, Integer orgId);

    Map<String,Object> findSysInfo();

    List<SdCollectTask> findTaskByParentId(Integer taskId);
}