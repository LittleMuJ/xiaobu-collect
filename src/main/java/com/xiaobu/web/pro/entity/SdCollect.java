package com.xiaobu.web.pro.entity;
import java.io.Serializable;
import java.util.Date;

import com.xiaobu.common.base.BaseEntity;

/**
* 描述：标注平台用户模型
* @author MuRunSen
* @date 2018-08-31 16:22:04
*/

public class SdCollect extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
    *ID
    */
    /**
    *任务ID
    */
    private Integer taskId;

    /**
    *用户ID
    */
    private Integer userId;

    /**
    *报酬
    */
    private Double price;
    /**
    *采集数据
    */
    /**
    *状态
    */
    private Integer status;

    /**
    *开始时间
    */
         private Date startTime;

    /**
    *提交时间
    */
         private Date finishTime;

    /**
    *提交期限
    */
         private Date deadTime;

    /**
     * 任务名
     */
    private String taskName;

    /**
     * 用户名
     */
    private String userName;

    private Object collectData;

    /**
     * 审核失败之后的提交次数,首次提交之后为0；审核失败之后再提交一次就为1
     */
    private Integer submitLimit;


    public Integer getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getFinishTime() {
        return this.finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
    public Date getDeadTime() {
        return this.deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getCollectData() {
        return collectData;
    }

    public void setCollectData(Object collectData) {
        this.collectData = collectData;
    }

    public Integer getSubmitLimit() {
        return submitLimit;
    }

    public void setSubmitLimit(Integer submitLimit) {
        this.submitLimit = submitLimit;
    }
}