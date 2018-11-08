package com.xiaobu.web.pro.service.Impl;
import com.xiaobu.web.pro.entity.SdLabelTask;
import com.xiaobu.web.pro.service.SdLabelTaskService;
import com.xiaobu.web.pro.dao.SdLabelTaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-08-09 15:02:18
*/
@Service("sdLabelTaskService")
public class SdLabelTaskServiceImpl implements SdLabelTaskService {

    @Autowired
    private SdLabelTaskDao sdLabelTaskDao;


    @Override
    public SdLabelTask getById(Integer id) throws Exception {
        SdLabelTask sdLabelTask = sdLabelTaskDao.getById(id);
        return sdLabelTask;
    }

    @Override
    public void add(SdLabelTask sdLabelTask) {
       sdLabelTaskDao.add(sdLabelTask);
    }

    @Override
    public void update(SdLabelTask sdLabelTask){
       sdLabelTaskDao.updateNotNull(sdLabelTask);
    }
    
    @Override
	public void delete(Integer id) {

		sdLabelTaskDao.delete(id);
	}
}