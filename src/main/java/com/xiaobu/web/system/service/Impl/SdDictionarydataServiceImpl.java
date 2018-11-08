package com.xiaobu.web.system.service.Impl;
import com.xiaobu.web.system.entity.SdDictionarydata;
import com.xiaobu.web.system.service.SdDictionarydataService;
import com.xiaobu.web.system.dao.SdDictionarydataDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-08-21 09:56:42
*/
@Service("sdDictionarydataService")
public class SdDictionarydataServiceImpl implements SdDictionarydataService {

    @Autowired
    private SdDictionarydataDao sdDictionarydataDao;


    @Override
    public SdDictionarydata getById(Integer id) throws Exception {
        SdDictionarydata sdDictionarydata = sdDictionarydataDao.getById(id);
        return sdDictionarydata;
    }

    @Override
    public void add(SdDictionarydata sdDictionarydata) {
       sdDictionarydataDao.add(sdDictionarydata);
    }

    @Override
    public void update(SdDictionarydata sdDictionarydata){
       sdDictionarydataDao.updateNotNull(sdDictionarydata);
    }

    @Override
	public void delete(Integer id) {

		sdDictionarydataDao.delete(id);
	}

    @Override
    public Map<String, Object> selectDictionaryType(String types) {
        Map<String,Object> map = new HashMap<>();
        String[] typelist = StringUtils.isBlank(types)? new String[0] :types.split(",");
        if(typelist.length>0){
            for(String type:typelist){
                List<SdDictionarydata> sdDictionarydataLsit = sdDictionarydataDao.findType(type);
                map.put("type"+type,sdDictionarydataLsit);
            }
        }
        return map;
    }
}