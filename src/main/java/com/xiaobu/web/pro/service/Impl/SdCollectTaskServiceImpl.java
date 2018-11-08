package com.xiaobu.web.pro.service.Impl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaobu.common.config.Collect;
import com.xiaobu.common.config.CollectTask;
import com.xiaobu.common.constant.SysMessage;
import com.xiaobu.common.model.PageModel;
import com.xiaobu.web.pro.dao.SdCollectDao;
import com.xiaobu.web.pro.entity.SdCollect;
import com.xiaobu.web.pro.entity.SdCollectTask;
import com.xiaobu.web.pro.service.SdCollectTaskService;
import com.xiaobu.web.pro.dao.SdCollectTaskDao;
import com.xiaobu.web.system.dao.SdUserDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* 描述：标注平台用户 服务实现层
* @author MuRunSen
* @date 2018-08-14 15:47:36
*/
@Service("sdCollectTaskService")
public class SdCollectTaskServiceImpl implements SdCollectTaskService {

    @Autowired
    private SdCollectTaskDao sdCollectTaskDao;

    @Autowired
    private SdCollectDao sdCollectDao;

    @Autowired
    private SdUserDao sdUserDao;

    @Override
    public SdCollectTask getById(Integer id) throws Exception {
        //获取当前id的任务
        SdCollectTask sdCollectTask = sdCollectTaskDao.getById(id);
        //获取子任务
        List<SdCollectTask> taskList = sdCollectTaskDao.findtaskByParentId(sdCollectTask);
        boolean submit = false;
        //是否拥有子任务
        if(taskList.size()>0){
            int sumNum=0;
            for(SdCollectTask task:taskList){
                //所有子任务所做任务
                Map<String,Object> parmMap =new HashMap<>();
                //子任务所做任务包括检验成功+审核成功
                parmMap.put("status",Collect.CHECKOUT_PASS.value()+","+Collect.INSPECTION_PASS.value());
                parmMap.put("taskId",task.getId());
                //将子任务的检验成功的任务相加
                sumNum =sumNum+sdCollectDao.findErrorNumByTaskId(parmMap);
                //子任务审核状态必须为审核成功
                if(task.getStatus()==CollectTask.INSPECTION_PASS.value()){
                    submit=true;
                }else{
                    submit=false;
                }
            }
            //查询审核成功个数
            Map<String,Object> parmMap =new HashMap<>();
            parmMap.put("status",Collect.INSPECTION_PASS.value());
            parmMap.put("taskId",id);
            Integer num = sdCollectDao.findErrorNumByTaskId(parmMap);
            //当审核成功的数量大于份额数并且状态值为已开始时
            if(submit && num>=sdCollectTask.getSurplusNum() && sdCollectTask.getStatus()==CollectTask.FULFIL_QUOTA.value()){
                sdCollectTask.setSubmit(true);
            }else{
                sdCollectTask.setSubmit(false);
            }
            //主任务完成数必须大于等于主任务的份额数
            sdCollectTask.setSumCount(num+sumNum);
        }else{
            //查询审核成功or检验成功个数
            Map<String,Object> parmMap =new HashMap<>();
            parmMap.put("status",Collect.INSPECTION_PASS.value()+","+Collect.CHECKOUT_PASS.value());
            parmMap.put("taskId",id);
            Integer num=0;
            num = sdCollectDao.findErrorNumByTaskId(parmMap);
            sdCollectTask.setSumCount(num);
            if(num>=sdCollectTask.getSurplusNum()&& sdCollectTask.getStatus()==CollectTask.FULFIL_QUOTA.value()){
                sdCollectTask.setSubmit(true);
            }else{
                sdCollectTask.setSubmit(false);
            }
        }
        return sdCollectTask;
    }

    @Override
    public void add(SdCollectTask sdCollectTask) {

        sdCollectTask.setResidueCount(sdCollectTask.getCount());//任务此轮任务

        //如果是直接发布的(发布主任务)
        if(sdCollectTask.getParentId()==null){
            sdCollectTask.setSurplusNum(sdCollectTask.getCount());//任务份额数

        }
        //如果是拆分的时候(发布子任务)
        else{
            sdCollectTask.setSurplusNum(sdCollectTask.getCount());
            SdCollectTask task = new SdCollectTask();

            task.setId(sdCollectTask.getParentId());
            //查询子任务
            List<SdCollectTask> taskList = sdCollectTaskDao.findtaskByParentId(task);
            int Num=0;
            if(taskList.size()>0){
                for(SdCollectTask collectTask:taskList){
                    Num=Num+collectTask.getCount();
                }
            }
            task.setSurplusNum(sdCollectTask.getParentCount()-Num-sdCollectTask.getCount());
            task.setResidueCount(sdCollectTask.getParentCount()-Num-sdCollectTask.getCount());

            sdCollectTaskDao.updateNotNull(task);
        }
        sdCollectTaskDao.add(sdCollectTask);
    }

    @Override
    public void update(SdCollectTask sdCollectTask){

       sdCollectTaskDao.updateNotNull(sdCollectTask);
    }

    @Override
    public PageModel<SdCollectTask> selectCollectTaskInfo(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        Page<SdCollectTask> taskPage= sdCollectTaskDao.findByPage(sdCollectTask);
        //获取数据
        List<SdCollectTask> taskList=taskPage.getResult();

        List<SdCollectTask> List = new ArrayList<>();
        for(SdCollectTask task:taskList){
            //如果为主任务
            if(task.getParentId()==null){
                //查询是否有子任务
                List<SdCollectTask> taskChildrenList = sdCollectTaskDao.findtaskByParentId(task);
                //当有子任务时
                if(taskChildrenList.size()>0){
                    boolean submit = false;
                    for(SdCollectTask childrentask:taskChildrenList){
                        //子任务审核状态必须为审核成功
                        if(childrentask.getStatus()==CollectTask.INSPECTION_PASS.value()){
                            submit=true;
                        }else{
                            submit=false;
                        }
                    }
                    //查询主任务是否够数
                    Map<String,Object> parmMap = new HashMap<>();
                    parmMap.put("status",Collect.INSPECTION_PASS.value());
                    parmMap.put("taskId",task.getId());
                    Integer num = sdCollectDao.findErrorNumByTaskId(parmMap);
                    if(submit && num>=task.getSurplusNum()){
                        task.setSubmit(true);
                    }else{
                        task.setSubmit(false);
                    }
                    //List.add(task);
                }else{
                    //查询主任务是否够数
                    Map<String,Object> parmMap = new HashMap<>();
                    parmMap.put("status",Collect.INSPECTION_PASS.value()+","+Collect.CHECKOUT_PASS.value());
                    parmMap.put("taskId",task.getId());
                    Integer num = sdCollectDao.findErrorNumByTaskId(parmMap);
                    if(num>=task.getSurplusNum()){
                        task.setSubmit(true);
                    }else{
                        task.setSubmit(false);
                    }

                }
                //List.add(task);
            }
            List.add(task);
        }
        //page.initData(taskPage);
        page.setData(List);
        page.setRecordsTotal(new Long(taskPage.getTotal()).intValue());
        page.setRecordsFiltered(new Long(taskPage.getTotal()).intValue());
        return page;
    }

    //平台管理員审核页List
    @Override
    public PageModel<SdCollectTask> selectExamineCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
       /* sdCollectTask.setStatus(CollectTask.FOR_INSPECTION.value());
        sdCollectTask.setListStatus(CollectTask.IS_START.value());*/
        sdCollectTask.setStatus2(CollectTask.FOR_INSPECTION.value());
        sdCollectTask.setStatus3(CollectTask.IS_START.value());
        sdCollectTask.setStatus4(CollectTask.FULFIL_QUOTA.value());
        page.initData(sdCollectTaskDao.findExaminePage(sdCollectTask));
        return page;
    }
    
    @Override
	public void delete(Integer id) {


		sdCollectTaskDao.delete(id);
	}


    @Override
    public List<SdCollectTask> findByCustomerId(int id) {
        return sdCollectTaskDao.findByCutomerId(id);
    }

    @Override
    public List<SdCollectTask> findByOrganizationId(int id) {
        return sdCollectTaskDao.findByOraginationId(id);
    }



    @Override
    public PageModel<SdCollectTask> selectOrgExamineCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        //质检List页面查询指定发布给团队的状态值为2，已接收的任务
        sdCollectTask.setStatus(CollectTask.IS_START.value());
        sdCollectTask.setStatus2(CollectTask.FULFIL_QUOTA.value());
        page.initData(sdCollectTaskDao.findOrgExaminePage(sdCollectTask));
        return page;
    }

    //查询甲方采集页面List
    @Override
    public PageModel<SdCollectTask> selectCumCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        page.initData(sdCollectTaskDao.findCumCollectPage(sdCollectTask));
        return page;
    }

    //查询团队管理员已接收任务
    @Override
    public PageModel<SdCollectTask> selectOrgCollectList(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        //List页面查询指定发布给团队的状态值为2，已接收的任务
        //sdCollectTask.setStatus(CollectTask.IS_START.value());
        //sdCollectTask.setStatus(CollectTask.IS_ISSUE.value());
        String multipleStatus = "("+CollectTask.IS_ISSUE.value()+","+CollectTask.INSPECTION_ERROR.value()+","+CollectTask.DISCARD.value()+")";
        sdCollectTask.setMultipleStatus(multipleStatus);
        sdCollectTask.setStatus2(Collect.CHECKOUT_PASS.value());
        page.initData(sdCollectTaskDao.findCollectPage(sdCollectTask));
        return page;
    }

    //查询团队管理员待接收任务
    @Override
    public PageModel<SdCollectTask> selectReadyOrgCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        //List页面查询指定发布给团队的状态值为1，未未接收的任务
        sdCollectTask.setStatus(CollectTask.IS_ISSUE.value());
        sdCollectTask.setListStatus(CollectTask.INSPECTION_ERROR.value());
        page.initData(sdCollectTaskDao.findReadyCollectPage(sdCollectTask));
        return page;
    }

    @Override
    public void OrgReceptionCollect(SdCollectTask sdCollectTask) {

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("status",Collect.ACCEPTANCE_ERROR.value()+","+Collect.INSPECTION_ERROR.value());
        parmMap.put("taskId",sdCollectTask.getId());
        Integer num = sdCollectDao.findErrorNumByTaskId(parmMap);
        if(num!=0){
            sdCollectTask.setDoneNum(0);
            sdCollectTask.setResidueCount(num);
        }
        //团队管理员接收任务，设置状态值为2
        sdCollectTask.setStatus(CollectTask.IS_START.value());

        sdCollectTaskDao.updateNotNull(sdCollectTask);
    }

    @Override
    public PageModel<SdCollectTask> selectCunExamineCollect(SdCollectTask sdCollectTask, PageModel<SdCollectTask> page) {
        PageHelper.offsetPage(page.getStart(), page.getLength());
        sdCollectTask.setStatus(CollectTask.FOR_ACCEPTANCE.value());
        page.initData(sdCollectTaskDao.findCunExaminePage(sdCollectTask));
        return page;
    }

    /**
     * 团队管理员提交至平台管理员
     * @param taskId
     * @param orgId
     * @return
     */
    @Override
    public String submitTaskToManager(Integer taskId, Integer orgId) {

        //更具taskId获取采集任务信息
        SdCollectTask collectTask = sdCollectTaskDao.getById(taskId);
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("status",Collect.CHECKOUT_PASS.value());
        parmMap.put("taskId",taskId);
        Integer num = sdCollectDao.findErrorNumByTaskId(parmMap);
        if(collectTask.getCount()<=num){
            /*if(collectTask.getSubmitLimit()==null){
                //当任务是新领取任务时，设置审核失败之后的提交次数为0
                collectTask.setSubmitLimit(0);
            }else{
                //当任务时审核失败之后再提交的任务时等于 次数+1
                collectTask.setSubmitLimit(collectTask.getSubmitLimit()+1);
            }*/
            //设置整体任务为待审核状态
            collectTask.setStatus(CollectTask.FOR_INSPECTION.value());
            sdCollectTaskDao.updateNotNull(collectTask);
            return "提交审核成功";
        }
        return "当前提交任务数量不足，待任务数量足够之后才能提交";
    }

    /**
     * 平台管理员提交至甲方管理员验收接口
     * @param taskId
     * @return
     */
    @Override
    public String submitTaskToCunsumer(Integer taskId) {
        int Num=0;
        SdCollectTask collectTask = sdCollectTaskDao.getById(taskId);
        List<SdCollectTask> collectTaskList=sdCollectTaskDao.findtaskByParentId(collectTask);
        //判断是否有子任务
        if(collectTaskList.size()>0){
            for(SdCollectTask task:collectTaskList){
                Num = Num+task.getDoneNum();
            }
            if(collectTask.getCount()<=Num+collectTask.getDoneNum()){
                //设置整体任务为待验收状态
                collectTask.setStatus(CollectTask.FOR_ACCEPTANCE.value());
                sdCollectTaskDao.updateNotNull(collectTask);
                return "提交验收成功";
            }
        }else{
            if(collectTask.getCount()<=collectTask.getDoneNum()){
                //设置整体任务为待验收状态
                collectTask.setStatus(CollectTask.FOR_ACCEPTANCE.value());
                sdCollectTaskDao.updateNotNull(collectTask);
                return "提交验收成功";
            }
        }
        return "当前提交任务数量不足，待任务数量足够之后才能提交";
    }


    //获取平台管理员首页信息
    @Override
    public Map<String, Object> findSysInfo() {
        Map<String,Object> resultMap = new HashMap<>();
        //获取在册人数，在册团队数，在册甲方数
        Map<String,Object> peopleMap = sdUserDao.findPeopleInfo();
        //获取饼图信息
        List<Map<String,Object>> pieMap = new ArrayList<>();
        Map<String,Object> parmaMapPie = new HashMap<>();
        parmaMapPie.put("for_inspection",CollectTask.FOR_INSPECTION.value());//添加待审核
        parmaMapPie.put("inspection_error",CollectTask.INSPECTION_ERROR.value());//审核失败
        parmaMapPie.put("inspection_pass",CollectTask.INSPECTION_PASS.value());//审核成功
        parmaMapPie.put("for_acceptance",CollectTask.FOR_ACCEPTANCE.value());//待验收
        parmaMapPie.put("acceptance_error",CollectTask.ACCEPTANCE_ERROR.value());//验收失败
        parmaMapPie.put("acceptance_success",CollectTask.ACCEPTANCE_SUCCESS.value());//验收成功
        parmaMapPie.put("is_issue",CollectTask.IS_ISSUE.value());//已发布
        parmaMapPie.put("is_start",CollectTask.IS_START.value());//已开始
        parmaMapPie.put("is_close_account",CollectTask.IS_CLOSE_ACCOUNT.value());//已结算
        pieMap = sdCollectTaskDao.findPieInfo(parmaMapPie);

        //获取当前年每个月份的数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);

        c.set(Calendar.DAY_OF_YEAR, 1);
        String firstday =sdf.format(c.getTime()); // 第一天
        c.add(Calendar.YEAR, 1);
        c.set(Calendar.DAY_OF_YEAR, 0);
        String lastday =sdf.format(c.getTime()); // 最后一天
        Map<String,Object> parmaMap = new HashMap<>();
        parmaMap.put("firstday",firstday);
        parmaMap.put("lastday",lastday);

        List<Map<String,Object>> tableMap = new ArrayList<>();
        List<Map<String,Object>> monthMap = sdCollectTaskDao.findtableInfo(parmaMap);
        if(monthMap.size()>0){
            for(Map<String,Object> month:monthMap){
                month.put("for_inspection",CollectTask.FOR_INSPECTION.value());//添加待审核
                month.put("inspection_error",CollectTask.INSPECTION_ERROR.value());//审核失败
                month.put("inspection_pass",CollectTask.INSPECTION_PASS.value());//审核成功
                month.put("for_acceptance",CollectTask.FOR_ACCEPTANCE.value());//待验收
                month.put("acceptance_error",CollectTask.ACCEPTANCE_ERROR.value());//验收失败
                month.put("acceptance_success",CollectTask.ACCEPTANCE_SUCCESS.value());//验收成功
                month.put("is_issue",CollectTask.IS_ISSUE.value());//已发布
                month.put("is_start",CollectTask.IS_START.value());//已开始
                month.put("is_close_account",CollectTask.IS_CLOSE_ACCOUNT.value());//已结算
                //获取每月的的数据
                  Map<String,Object> dataMap = sdCollectTaskDao.findDataByMonth(month);
                  dataMap.put("month",month.get("month"));
                  tableMap.add(dataMap);
            }
        }
        //封装结果
        resultMap.put("info",peopleMap);
        resultMap.put("pieInfo",pieMap);
        resultMap.put("tableInfo",tableMap);
        resultMap.put("year",c.get(Calendar.YEAR));
        return resultMap;
    }

    @Override
    public List<SdCollectTask> findTaskByParentId(Integer taskId) {
        SdCollectTask task = new SdCollectTask();
        task.setId(taskId);
        return sdCollectTaskDao.findtaskByParentId(task);
    }

    //时间定时器，每天凌晨12点执行一次，当任务开始时间小于当前时间，则将任务状态改为已开始“2”
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public void updateTaskStatus()  {
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //查询状态为1已发布的采集任务
        try {
            List<SdCollectTask> sdCollectTaskList = sdCollectTaskDao.findCollectTaskByStatus(1);
            if(sdCollectTaskList.size()>0){
            for(SdCollectTask sdCollectTask:sdCollectTaskList){
                Date newdate = df.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                if(newdate.getTime() ==sdCollectTask.getStartTime().getTime()||newdate.getTime()>sdCollectTask.getStartTime().getTime()){
                    SdCollectTask sd = new SdCollectTask();
                    sd.setStatus(CollectTask.IS_START.value());
                    sd.setId(sdCollectTask.getId());
                    sdCollectTaskDao.updateNotNull(sd);
                }
            }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("更新状态失败");
        }
    }
}