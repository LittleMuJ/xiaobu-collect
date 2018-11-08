package com.xiaobu.web.system.service;

import com.xiaobu.web.system.entity.SdManager;
/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-07-31 11:56:39
*/
public interface SdManagerService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    SdManager getById(Integer id)throws Exception;

    //SdManagerDTO createSdManager(SdManagerDTO sdManagerDTO) throws Exception;
    void add(SdManager sdManager);

    void delete(Integer id) throws Exception;

    //SdManagerDTO updateSdManager(SdManagerDTO sdManagerDTO) throws Exception;
    void update(SdManager sdManager);

	SdManager selectByUsername(String username);

	SdManager selectByphone(String phone);

	int insertAndGetId(SdManager sdManager);

}