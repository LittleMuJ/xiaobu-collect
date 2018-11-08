package com.xiaobu.web.pro.entity;
import java.io.Serializable;
import java.util.Date;

import com.xiaobu.common.base.BaseEntity;

import javax.validation.constraints.NotNull;

/**
* 描述：标注平台用户模型
* @author MuRunSen
* @date 2018-08-14 15:47:36
*/

public class SdCollectTask extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
    *ID
    */
    /**
    *父任务ID
    */
    private Integer parentId;

    /**
    *甲方ID
    */
    private Integer consumerId;

    /**
    *组织ID
    */
    private Integer organizationId;

    /**
    *任务名称
    */
    @NotNull
    private String name;

    /**
    *简介
    */
    private String intro;

    /**
    *详细说明
    */
    private String detail;

    /**
    *主分类
    */
    //private Integer categroy;

    /**
    *自分类
    */
    private Integer type;

    /***
     * 自分类名称
     */
    private String typeName;

    /**
    *示例
    */
    private String example;

    /**
    *操作期限
    */

    private Integer duration;

    /**
    *交付甲方的时间
    */
    private String promisetime;

    /**
    *总数量
    */
    private Integer count;

    /**
    *完成数量
    */
    private Integer doneNum;

    /**
    *状态
    */
    private Integer status;

    private Integer listStatus;

    /**
     * 甲方名称
     */
    private String consumerName;

    /**
     * 团队名称
     */
    private String organizationName;
    /**
    *任务开始时间
    */

    /**
     * 任务开始时间
     */
    @NotNull
    private Date startTime;
    /**
     * 任务期限
     */
    @NotNull
    private Date deadline;

    /**
     *甲方总价
     */
    private Double total;
    /**
     *单价
     */
    @NotNull
    private Double price;

    /**
     * 状态名
     * @return
     */
    private String statusName;

    /**
     * 代理商总价
     * @return
     */
    private Double organizationTotal;

    /**
     * 父级任务所剩数量，此字段只有发生拆分时才会有值
     */
    private Integer surplusNum;

    /**
     * 父级count
     * @return
     */
    private Integer parentCount;

    /**
     * 约束：采集数量
     */
    private Integer ruleCount;

    /**
     * 约束：当采集任务为音频任务时，录制时长，单位为秒（s）
     */
    private Integer ruleTime;

    /**
     * 约束：当前任务可做次数
     */
    private Integer ruleNumber;

    /**
     * 审核失败之后的提交次数
     */
    private Integer submitLimit;

    /**
     * 第二状态值
     */
    private Integer status2;

    /**
     * 第三状态值
     */
    private Integer status3;

    /**
     * 第四状态值
     */
    private Integer status4;

    /**
     * 所有任务已完成总数
     */
    private Integer sumCount;

    private String multipleStatus;

    /**
     * 是否可提交验收
     * @return
     */
    private boolean isSubmit;

    private String updateType;

    private String statuss;

    /**
     * 任务所剩次数
     */
    private Integer residueCount;

    /**
     * 约束：抽检比例(使用小数点)
     */
    private Double ruleCheck;

    /**
     * 约束：错误比例(使用小数点)
     */
    private Double ruleError;

    /**
     * 约束：当前任务包可错误次数
     * @return
     */
    private Integer ruleErrorPackage;

    /**
     * 约束：当前单条任务可错误次数
     * @return
     */
    private Integer ruleErrorSingle;


    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getConsumerId() {
        return this.consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return this.intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    /*public Integer getCategroy() {
        return this.categroy;
    }

    public void setCategroy(Integer categroy) {
        this.categroy = categroy;
    }*/

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExample() {
        return this.example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPromisetime() {
        return this.promisetime;
    }

    public void setPromisetime(String promisetime) {
        this.promisetime = promisetime;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDoneNum() {
        return this.doneNum;
    }

    public void setDoneNum(Integer doneNum) {
        this.doneNum = doneNum;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName;
    }

    public String getConsumerName() {
        return consumerName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setOrganizationTotal(Double organizationTotal) {
        this.organizationTotal = organizationTotal;
    }

    public Double getOrganizationTotal() {
        return organizationTotal;
    }

    public Integer getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(Integer surplusNum) {
        this.surplusNum = surplusNum;
    }

    public Integer getParentCount() {
        return parentCount;
    }

    public void setParentCount(Integer parentCount) {
        this.parentCount = parentCount;
    }

    public Integer getSumCount() {
        return sumCount;
    }

    public void setSumCount(Integer sumCount) {
        this.sumCount = sumCount;
    }

    public Integer getListStatus() {
        return listStatus;
    }

    public void setListStatus(Integer listStatus) {
        this.listStatus = listStatus;
    }

    public Integer getRuleCount() {
        return ruleCount;
    }

    public void setRuleCount(Integer ruleCount) {
        this.ruleCount = ruleCount;
    }

    public Integer getRuleTime() {
        return ruleTime;
    }

    public void setRuleTime(Integer ruleTime) {
        this.ruleTime = ruleTime;
    }

    public Integer getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(Integer ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    public Integer getSubmitLimit() {
        return submitLimit;
    }

    public void setSubmitLimit(Integer submitLimit) {
        this.submitLimit = submitLimit;
    }

    public String getMultipleStatus() {
        return multipleStatus;
    }

    public void setMultipleStatus(String multipleStatus) {
        this.multipleStatus = multipleStatus;
    }

    public Integer getStatus2() {
        return status2;
    }

    public void setStatus2(Integer status2) {
        this.status2 = status2;
    }

    public Integer getStatus3() {
        return status3;
    }

    public void setStatus3(Integer status3) {
        this.status3 = status3;
    }

    public boolean isSubmit() {
        return isSubmit;
    }

    public void setSubmit(boolean submit) {
        isSubmit = submit;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getStatuss() {
        return statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public Integer getResidueCount() {
        return residueCount;
    }

    public void setResidueCount(Integer residueCount) {
        this.residueCount = residueCount;
    }

    public Double getRuleCheck() {
        return ruleCheck;
    }

    public void setRuleCheck(Double ruleCheck) {
        this.ruleCheck = ruleCheck;
    }

    public Double getRuleError() {
        return ruleError;
    }

    public void setRuleError(Double ruleError) {
        this.ruleError = ruleError;
    }

    public Integer getStatus4() {
        return status4;
    }

    public void setStatus4(Integer status4) {
        this.status4 = status4;
    }

    public Integer getRuleErrorPackage() {
        return ruleErrorPackage;
    }

    public void setRuleErrorPackage(Integer ruleErrorPackage) {
        this.ruleErrorPackage = ruleErrorPackage;
    }

    public Integer getRuleErrorSingle() {
        return ruleErrorSingle;
    }

    public void setRuleErrorSingle(Integer ruleErrorSingle) {
        this.ruleErrorSingle = ruleErrorSingle;
    }
}