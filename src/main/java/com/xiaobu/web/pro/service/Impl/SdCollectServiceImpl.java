package com.xiaobu.web.pro.service.Impl;
import com.github.pagehelper.PageHelper;
import com.xiaobu.common.config.Collect;
import com.xiaobu.common.config.CollectTask;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.pro.dao.SdCollectTaskDao;
import com.xiaobu.web.pro.entity.SdCollect;
import com.xiaobu.web.pro.entity.SdCollectTask;
import com.xiaobu.web.pro.service.SdCollectService;
import com.xiaobu.web.pro.dao.SdCollectDao;
import com.xiaobu.web.redis.service.RedisTaskService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-08-31 16:22:04
*/
@Service("sdCollectService")
public class SdCollectServiceImpl implements SdCollectService {

    @Autowired
    private SdCollectDao sdCollectDao;

    @Autowired
    private SdCollectTaskDao sdCollectTaskDao;

    @Autowired
    private RedisTaskService redisService;


    @Override
    public SdCollect getById(Integer id) throws Exception {
        SdCollect sdCollect = sdCollectDao.getById(id);
        return sdCollect;
    }

    @Override
    public void add(SdCollect sdCollect) {
       sdCollectDao.add(sdCollect);
    }

    @Override
    public void update(SdCollect sdCollect){

       sdCollectDao.updateNotNull(sdCollect);
    }

    @Override
	public void delete(Integer id) {

		sdCollectDao.delete(id);
	}

    @Override
    public PageModel<SdCollect> selectExamineCollectInfo(Integer id, PageModel<SdCollect> page) {
        SdCollectTask sdCollectTask = sdCollectTaskDao.getById(id);
        Map<String,Object> parmMap= new HashMap<>();
        if(sdCollectTask.getOrganizationId()==null){
            PageHelper.offsetPage(page.getStart(), page.getLength());
            parmMap.put("id",id);
            parmMap.put("status",Collect.FOR_INSPECTION.value());
            page.initData(sdCollectDao.findExamineCollectInfo(parmMap));
        }else {
            PageHelper.offsetPage(page.getStart(), page.getLength());
            Double examineLen = sdCollectTask.getCount()*sdCollectTask.getRuleCheck();
            BigDecimal bd=new BigDecimal(examineLen).setScale(0, BigDecimal.ROUND_HALF_UP);
            parmMap.put("id",id);
            parmMap.put("examineLen",Integer.parseInt(bd.toString()));
            parmMap.put("status",Collect.CHECKOUT_PASS.value());
            page.initData(sdCollectDao.findExamineOrgCollectInfo(parmMap));
        }

        return page;
    }

    //团队管理员质检详情
    @Override
    public PageModel<SdCollect> selectExaminOrgeCollectInfo(SdCollect sdCollect, PageModel<SdCollect> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        sdCollect.setStatus(Collect.FOR_CHECKOUT.value());
        page.initData(sdCollectDao.findExamineOrgeCollectInfo(sdCollect));
        return page;
    }

    @Override
    public void examineOrgCollect(Integer taskId,Integer id, Integer status) {
        SdCollectTask sdCollectTask= sdCollectTaskDao.getById(taskId);
        SdCollect sdCollect = sdCollectDao.getById(id);

        sdCollect.setStatus(status);
        //团队管理员质检团队成员任务

        if(status==Collect.CHECKOUT_ERROR.value()&&sdCollectTask.getDoneNum()>0){

            Integer sd = sdCollectTask.getDoneNum()-1;
            redisService.setString(taskId.toString(),sd);
            sdCollectTask.setDoneNum(sd);
            sdCollectTaskDao.updateNotNull(sdCollectTask);
            if(sdCollect.getSubmitLimit()==null){
                sdCollect.setSubmitLimit(1);
            }else{
                sdCollect.setSubmitLimit(sdCollect.getSubmitLimit()+1);
            }
        }
        sdCollectDao.updateNotNull(sdCollect);
    }

    /**
     * 平台管理员、团队管理员公用接口，传任务id以及审核、检验的各个状态值
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Integer taskId,Integer id, Integer status) {

        SdCollect sdCollect = sdCollectDao.getById(id);
        sdCollect.setStatus(status);
        SdCollectTask sdCollectTask= sdCollectTaskDao.getById(taskId);

        //当为公网任务时审核失败时，对公网任务提交数进行减法
        if(status==Collect.INSPECTION_ERROR.value()&& sdCollectTask.getParentId()==null&&sdCollectTask.getOrganizationId()==null&&sdCollectTask.getDoneNum()>0){
            Integer sd = sdCollectTask.getDoneNum()-1;
            redisService.setString(taskId.toString(),sd);
            sdCollectTask.setDoneNum(sd);
            if(sdCollectTask.getStatus()==CollectTask.FULFIL_QUOTA.value()){
                sdCollectTask.setStatus(CollectTask.IS_START.value());
            }
            sdCollectTaskDao.updateNotNull(sdCollectTask);
            if(sdCollect.getSubmitLimit()==null){
                sdCollect.setSubmitLimit(1);
            }else{
                sdCollect.setSubmitLimit(sdCollect.getSubmitLimit()+1);
            }
        }
        sdCollectDao.updateNotNull(sdCollect);
    }

    //甲方管理员验收任务
    @Override
    public PageModel<SdCollect> selectCunExamineCollectInfo(Integer id, PageModel<SdCollect> page) {
        SdCollectTask sdCollectTask = sdCollectTaskDao.getById(id);
        //查询子任务，如果有子任务就将id循环得出（ids,ids）
        List<SdCollectTask> childernTask = sdCollectTaskDao.findtaskByParentId(sdCollectTask);
        String ids="";
        for(SdCollectTask task:childernTask){
            ids+=task.getId()+",";
        }
        ids+=id.toString();
        PageHelper.offsetPage(page.getStart(), page.getLength());
        Map<String,Object> parmMap= new HashMap<>();
        parmMap.put("id",ids);

        //算出抽检长度
        Double examineLen = sdCollectTask.getCount()*sdCollectTask.getRuleCheck();
        BigDecimal bd=new BigDecimal(examineLen).setScale(0, BigDecimal.ROUND_HALF_UP);

        parmMap.put("examineLen",Integer.parseInt(bd.toString()));
        parmMap.put("status",Collect.INSPECTION_PASS.value());
        parmMap.put("status2",Collect.CHECKOUT_PASS.value());
        page.initData(sdCollectDao.findExamineCuneCollectInfo(parmMap));
        return page;
    }

    @Override
    public double findSuccessPorb(Integer taskId) {
        Map<String,Object> parmMap= new HashMap<>();
        parmMap.put("error",Collect.INSPECTION_ERROR.value());
        parmMap.put("success",Collect.INSPECTION_PASS.value());
        parmMap.put("taskId",taskId);
        double math = sdCollectDao.findSuccessPorb(parmMap);
        return math;
    }

    @Override
    public double findConSuccessPorb(Integer taskId) {
        Map<String,Object> parmMap= new HashMap<>();
        parmMap.put("error",Collect.ACCEPTANCE_ERROR.value());
        parmMap.put("success",Collect.ACCEPTANCE_SUCCESS.value());
        parmMap.put("taskId",taskId);
        double math = sdCollectDao.findConSuccessPorb(parmMap);
        return math;
    }

    //获取平台管理员审核情况
    @Override
    public Map<String, Object> findExamineInfoByTaskId(Integer taskId) {
        String ids="";//主，子任务id以逗号分隔开
        Map<String,Object> parmMap= new HashMap<>();
        SdCollectTask task = sdCollectTaskDao.getById(taskId);

        List<SdCollectTask> taskList = sdCollectTaskDao.findtaskByParentId(task);
        if(taskList.size()>0){
            for(SdCollectTask child:taskList){
                ids+=child.getId()+",";
            }
        }
        ids+=taskId;
        parmMap.put("error",Collect.INSPECTION_ERROR.value());
        parmMap.put("success",Collect.INSPECTION_PASS.value());
        parmMap.put("taskId",ids);
        Map<String,Object> resultMap= new HashMap<>();
        resultMap = sdCollectDao.findExamineCountByTaskId(parmMap);
        resultMap.put("percent",1-task.getRuleError());//返回成功率
        return resultMap;
    }

    //获取甲方管理员审核情况
    @Override
    public Map<String, Object> findConExamineInfoByTaskId(Integer taskId) {
        String ids="";//主，子任务id以逗号分隔开
        Map<String,Object> parmMap= new HashMap<>();
        SdCollectTask task = sdCollectTaskDao.getById(taskId);

        List<SdCollectTask> taskList = sdCollectTaskDao.findtaskByParentId(task);
        if(taskList.size()>0){
            for(SdCollectTask child:taskList){
                ids+=child.getId()+",";
            }
        }
        ids+=taskId;
        parmMap.put("error",Collect.ACCEPTANCE_ERROR.value());
        parmMap.put("success",Collect.ACCEPTANCE_SUCCESS.value());
        parmMap.put("taskId",ids);
        Map<String,Object> resultMap= new HashMap<>();
        resultMap = sdCollectDao.findExamineCountByTaskId(parmMap);
        resultMap.put("percent",1-task.getRuleError());//返回成功率
        return resultMap;
    }

    @Override
    public Map<String, Object> findDefeateDetails(Integer taskId) {
        Map<String,Object> resultMap = new HashMap<>();
        String ids="";//主，子任务id以逗号分隔开
        //获取主任务失败详情
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("taskId",taskId);
        parmMap.put("status",Collect.ACCEPTANCE_ERROR.value());
        Map<String, Object> pieMap = sdCollectDao.findDefeateDetails(parmMap);
        List<Map<String,Object>> childList = new ArrayList<>();
        //查询有无子任务
        SdCollectTask task= new SdCollectTask();
        task.setId(taskId);
        List<SdCollectTask> childTask = sdCollectTaskDao.findtaskByParentId(task);
        if(childTask.size()>0){
            for(SdCollectTask child:childTask){
                ids+=child.getId()+",";
                Map<String, Object> childMap = new HashMap<>();
                Map<String,Object> childparmMap = new HashMap<>();
                childparmMap.put("taskId",child.getId());
                childparmMap.put("status",Collect.ACCEPTANCE_ERROR.value());
                childMap = sdCollectDao.findDefeateDetails(childparmMap);
                childList.add(childMap);
            }
            pieMap.put("child",childList);
        }

        //table数据
        ids+=taskId;
        Map<String,Object> parmMapTab = new HashMap<>();
        parmMapTab.put("ids",ids);
        parmMapTab.put("status",Collect.ACCEPTANCE_ERROR.value());
        List<Map<String, Object>> tableMap = sdCollectDao.DefeateDetailsTable(parmMapTab);
        resultMap.put("table",tableMap);
        resultMap.put("pie",pieMap);
        return resultMap;
    }

    @Override
    public Integer findErrorNumByTaskId(Map<String,Object> parmMap) {

        return sdCollectDao.findErrorNumByTaskId(parmMap);
    }
}