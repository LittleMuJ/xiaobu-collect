package com.xiaobu.web.system.entity;
import java.io.Serializable;
import java.util.Date;

import com.xiaobu.common.base.BaseEntity;

/**
* 描述：标注平台用户模型
* @author MuRunSen
* @date 2018-08-31 14:33:15
*/

public class SdOrganizationRole extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
    *
    */
    /**
    *
    */
    private Integer role;

    /**
    *
    */
    private Integer userId;

    /**
    *
    */
    private Integer organizationId;

    /**
    *
    */
    private Integer taskId;

    /**
    *
    */
   private Date ceateTime;

    /**
    *成员审核状态
    */
    private Integer status;

    private String name;

    private String phone;

    private String email;

    private String username;

    private String password;

    private String nickname;

    private String avatarUrl;

    private String gender; // 数据库类型为int  这里查询出来的结果为String

    private String dictdataType;//性别类型


    public Integer getRole() {
        return this.role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getCeateTime() {
        return this.ceateTime;
    }

    public void setCeateTime(Date ceateTime) {
        this.ceateTime = ceateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDictdataType() {
        return dictdataType;
    }

    public void setDictdataType(String dictdataType) {
        this.dictdataType = dictdataType;
    }
}