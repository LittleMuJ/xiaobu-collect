package com.xiaobu.web.pro.entity;
import java.io.Serializable;

import com.xiaobu.common.base.BaseEntity;

/**
* 描述：标注平台用户模型
* @author MuRunSen
* @date 2018-08-09 15:02:18
*/

public class SdLabelTask extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
    *ID
    */
    /**
    *名称
    */
    private String name;

    /**
    *简介
    */
    private String intro;

    /**
    *详细介绍
    */
    private String detail;

    /**
    *一级分类
    */
    private Integer categroy;

    /**
    *二级分类
    */
    private Integer type;

    /**
    *示例内容
    */
    /**
    *总次数
    */
    /**
    *单价
    */
    /**
    *收集期限
    */
    private String duration;

    /**
    *已完成次数
    */
    private Integer count;

    /**
    *交付期限
    */
    /**
    *状态
    */
    private Integer status;

    /**
    *创建时间
    */
    /**
    *修改时间
    */
    /**
    *甲方ID
    */
    private Integer consumerId;

    /**
    *父任务ID
    */
    private Integer labelTaskId;


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

    public Integer getCategroy() {
        return this.categroy;
    }

    public void setCategroy(Integer categroy) {
        this.categroy = categroy;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getConsumerId() {
        return this.consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getLabelTaskId() {
        return this.labelTaskId;
    }

    public void setLabelTaskId(Integer labelTaskId) {
        this.labelTaskId = labelTaskId;
    }


}