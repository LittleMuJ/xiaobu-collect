package com.xiaobu.web.system.service.Impl;

import com.github.pagehelper.PageHelper;
import com.xiaobu.common.config.CollectTask;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.pro.dao.SdCollectTaskDao;
import com.xiaobu.web.system.dao.SdConsumerDao;
import com.xiaobu.web.system.entity.SdConsumer;
import com.xiaobu.web.system.service.SdConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-08-08 09:39:32
*/
@Service("sdConsumerService")
public class SdConsumerServiceImpl implements SdConsumerService {

    @Autowired
    private SdConsumerDao sdConsumerDao;

    @Autowired
    private SdCollectTaskDao sdCollectTaskDao;



	public SdConsumer selectByUsername(String username){
        return sdConsumerDao.selectByUsername(username);
    }

    @Override
    public SdConsumer getById(Integer id) throws Exception {
        return sdConsumerDao.getById(id);
    }

    @Override
    public PageModel<SdConsumer> selectConsumerInfos(SdConsumer sdConsumers, PageModel<SdConsumer> page) {
	    System.out.println(page.getStart()+"------------"+page.getLength());
        PageHelper.offsetPage(page.getStart(), page.getLength());
        page.initData(sdConsumerDao.findByPage(sdConsumers));
        return page;
    }


    @Override
    public void insertConsumerInfo(SdConsumer sdConsumer) {
        sdConsumer.setCreatedAt(new Date());
        sdConsumer.setUpdatedAt(new Date());
        sdConsumerDao.insertConsumerInfo(sdConsumer);
    }

    @Override
    public void updateConsumerInfo(SdConsumer sdConsumer) {
        sdConsumerDao.updateNotNull(sdConsumer);
    }

    @Override
    public void deletConsumer(int id) {
        sdConsumerDao.delete(id);
    }

    @Override
    public Map<String, Object> findConInfoById(Integer conId) {
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> parmMap1 = new HashMap<>();
        Map<String,Object> parmMap2 = new HashMap<>();

        //查询甲方管理员首页头部信息
        Map<String,Object> headMap = sdCollectTaskDao.findConHeadInfo(conId);

        //查询甲方管理员首页进行中任务列表
        parmMap1.put("conId",conId);
        parmMap1.put("status",CollectTask.IS_START.value());
        parmMap2.put("status2",CollectTask.IS_ISSUE.value());
        List<Map<String,Object>> taskIng = sdCollectTaskDao.findTaskIngAndACCEPTANCE(parmMap1);

        //查询甲方管理员首页已验收任务列表
        parmMap2.put("conId",conId);
        parmMap2.put("status",CollectTask.ACCEPTANCE_SUCCESS.value());
        parmMap2.put("status2",CollectTask.IS_CLOSE_ACCOUNT.value());
        List<Map<String,Object>> taskAcceptance = sdCollectTaskDao.findTaskIngAndACCEPTANCE(parmMap2);


        resultMap.put("head",headMap);
        resultMap.put("ing",taskIng);
        resultMap.put("isAcceptance",taskAcceptance);
        return resultMap;
    }
}