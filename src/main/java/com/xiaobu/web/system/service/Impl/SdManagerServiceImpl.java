package com.xiaobu.web.system.service.Impl;
import com.xiaobu.web.system.entity.SdManager;
import com.xiaobu.web.system.service.SdManagerService;
import com.xiaobu.web.system.dao.SdManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-07-31 11:56:39
*/
@Service("sdManagerService")
public class SdManagerServiceImpl implements SdManagerService {

	/**
	 * 注入dao
	 */
    @Autowired
    private SdManagerDao sdManagerDao;


    @Override
    public SdManager getById(Integer id) throws Exception {
        SdManager sdManager = sdManagerDao.getById(id);
        return sdManager;
    }

    @Override
    public void add(SdManager sdManager) {

       sdManagerDao.add(sdManager);
    }

    @Override
    public void update(SdManager sdManager){
       sdManagerDao.updateNotNull(sdManager);
    }
    
    @Override
	public void delete(Integer id) {

		sdManagerDao.delete(id);
	}

	@Override
	public SdManager selectByUsername(String username) {
		// TODO Auto-generated method stub
		return sdManagerDao.selectByUsername(username);
	}

	@Override
	public SdManager selectByphone(String phone) {
		// TODO Auto-generated method stub
		return sdManagerDao.selectByphone(phone);
	}


	/**
	 * 报错则回滚
	 * @param sdManager
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int insertAndGetId(SdManager sdManager) {
		// TODO Auto-generated method stub
		try {
			sdManager.setCreatedAt(new Date());
			sdManager.setUpdatedAt(new Date());
			return sdManagerDao.insertAndGetId(sdManager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("注册失败");
		}
	}
}