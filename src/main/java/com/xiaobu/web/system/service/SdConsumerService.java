package com.xiaobu.web.system.service;

import com.xiaobu.common.model.PageModel;

import com.xiaobu.web.system.entity.SdConsumer;
import com.xiaobu.web.system.entity.SdManager;

import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-08-08 09:39:32
*/
public interface SdConsumerService {


    SdConsumer selectByUsername(String username);

    SdConsumer getById(Integer id)throws Exception;

    public PageModel<SdConsumer> selectConsumerInfos(SdConsumer sdConsumers, PageModel<SdConsumer> page);

    void  insertConsumerInfo(SdConsumer sdConsumer);

    void updateConsumerInfo(SdConsumer sdConsumer);

    void deletConsumer(int id);

    Map<String,Object> findConInfoById(Integer conId);
}