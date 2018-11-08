package com.xiaobu.web.system.service.Impl;

import com.github.pagehelper.PageHelper;
import com.xiaobu.common.config.Collect;
import com.xiaobu.common.config.CollectTask;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.pro.entity.SdCollectTask;
import com.xiaobu.web.system.dao.SdOrganizationDao;
import com.xiaobu.web.system.entity.SdOrganization;
import com.xiaobu.web.system.service.SdOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-07-31 11:40:29
*/
@Service("sdOrganizationService")
public class SdOrganizationServiceImpl implements SdOrganizationService {

    @Autowired
    private SdOrganizationDao sdOrganizationDao;


    @Override
    public SdOrganization getById(Integer id) throws Exception {
        SdOrganization sdOrganization = sdOrganizationDao.getById(id);
        return sdOrganization;
    }

    @Override
    public void add(SdOrganization sdOrganization) {
    	sdOrganization.setCreatedAt(new Date());
    	sdOrganization.setUpdatedAt(new Date());
        sdOrganizationDao.add(sdOrganization);
    }

    @Override
    public void update(SdOrganization sdOrganization){
       sdOrganizationDao.updateNotNull(sdOrganization);
    }

    @Override
	public void delete(Integer id) {

		sdOrganizationDao.delete(id);
	}

	@Override
	public SdOrganization selectByUsername(String username) {
		return sdOrganizationDao.selectByUsername(username);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void insertAndGetId(SdOrganization sdOrganization) {

		try {
			sdOrganization.setCreatedAt(new Date());
			sdOrganization.setUpdatedAt(new Date());
			sdOrganizationDao.insertAndGetId(sdOrganization);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("注册失败");
		}
	}

	@Override
	public PageModel<SdOrganization> selectOrganizationInfo(SdOrganization sdOrganization, PageModel<SdOrganization> page) {
		PageHelper.offsetPage(page.getStart(),page.getLength());
		page.initData(sdOrganizationDao.findByPage(sdOrganization));
		return page;
	}

    @Override
    public Map<String,Object> findOrgInfoById(Integer orgId) {

        List<Map<String,Object>> liMap = new ArrayList();
        Map<String,Object> map = new HashMap<>();
        //查询该团队下的成员数
        Integer userSum = sdOrganizationDao.findUserSumById(orgId);
        map.put("userSum",userSum);
        //查询该团队下有多少采集任务
        Integer collectSum = sdOrganizationDao.findCollectSumById(orgId);
        map.put("collectSum",collectSum);
        return map;
    }

    @Override
    public List<Map<String, Object>> findOrgTaskInfoById(Integer orgId) {
        List<Map<String, Object>> liMap = new ArrayList<>();
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("success",Collect.INSPECTION_PASS.value());
        parmMap.put("error",Collect.INSPECTION_ERROR.value());
        parmMap.put("orgId",orgId);

        parmMap.put("shenhe3",CollectTask.FOR_INSPECTION.value());
        parmMap.put("shenhe4",CollectTask.INSPECTION_ERROR.value());
        parmMap.put("shenhe5",CollectTask.INSPECTION_PASS.value());
        //获取信息
        liMap = sdOrganizationDao.findOrgTaskInfoById(parmMap);
        return liMap;
    }

    @Override
    public Map<String, Object> findOrgPiechartInfo(Integer taskId, Integer orgId) {
        Map<String,Object> resultMap = new HashMap<>();
        //首先查询表格数据
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("taskId",taskId);
        parmMap.put("orgId",orgId);
        parmMap.put("success",Collect.INSPECTION_PASS.value());
        parmMap.put("error",Collect.INSPECTION_ERROR.value());
        parmMap.put("shenhe5",CollectTask.INSPECTION_PASS.value());
        List<Map<String,Object>> liMap = new ArrayList<>();
        liMap = sdOrganizationDao.findTable(parmMap);
        //查询饼图信息
        Map<String,Object> pieMap = new HashMap<>();
        pieMap = sdOrganizationDao.findPie(parmMap);

        resultMap.put("table",liMap);
        resultMap.put("pie",pieMap);
        return resultMap;
    }
}