package com.xiaobu.web.pro.service;

import com.xiaobu.web.pro.entity.SdLabelTask;
/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-08-09 15:02:18
*/
public interface SdLabelTaskService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    SdLabelTask getById(Integer id)throws Exception;

    //SdLabelTaskDTO createSdLabelTask(SdLabelTaskDTO sdLabelTaskDTO) throws Exception;
    void add(SdLabelTask sdLabelTask);

    void delete(Integer id) throws Exception;

    //SdLabelTaskDTO updateSdLabelTask(SdLabelTaskDTO sdLabelTaskDTO) throws Exception;
    void update(SdLabelTask sdLabelTask);

}