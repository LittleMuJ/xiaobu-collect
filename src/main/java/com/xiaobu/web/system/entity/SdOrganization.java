package com.xiaobu.web.system.entity;
import java.io.Serializable;

import com.xiaobu.common.base.BaseEntity;

/**
* 描述：标注平台用户模型
* @author MuRunSen
* @date 2018-07-31 11:40:29
*/

public class SdOrganization extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
    *主键id
    */
    /**
    *
    */
    private String name;

    /**
    *
    */
    private String intro;

    /**
     *
     */
    private String phone;

    /**
    *
    */
    private String username;

    /**
    *
    */
    private String password;

    /**
    *
    */
    private Integer type;
    
    
    //验证码
    private String vCode;

    /**
    *
    */
    /**
    *
    */

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

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}