package com.xiaobu.web.system.service;

import com.xiaobu.web.system.entity.SdDictionarydata;

import java.util.Map;

/**
* 描述：标注平台用户 服务实现层接口
* @author MuRunSen
* @date 2018-08-21 09:56:42
*/
public interface SdDictionarydataService {

    /**
    * 描述：根据Id获取DTO
    * @param id
    */
    SdDictionarydata getById(Integer id)throws Exception;

    //SdDictionarydataDTO createSdDictionarydata(SdDictionarydataDTO sdDictionarydataDTO) throws Exception;
    void add(SdDictionarydata sdDictionarydata);

    void delete(Integer id) throws Exception;

    //SdDictionarydataDTO updateSdDictionarydata(SdDictionarydataDTO sdDictionarydataDTO) throws Exception;
    void update(SdDictionarydata sdDictionarydata);

    Map<String,Object> selectDictionaryType(String types);
}