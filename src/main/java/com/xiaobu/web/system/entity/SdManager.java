package com.xiaobu.web.system.entity;
import java.io.Serializable;

import com.xiaobu.common.base.BaseEntity;

/**
* 描述：标注平台用户模型
* @author MuRunSen
* @date 2018-07-31 11:56:39
*/

public class SdManager extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
    *
    */
    /**
    *
    */
    private String phone;

    /**
    *
    */
    private String email;

    /**
    *
    */
    private String username;

    /**
    *
    */
    private String password;
    
    
    private String vCode;

    /**
    *
    */
    /**
    *
    */

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

	public String getvCode() {
		return vCode;
	}

	public void setvCode(String vCode) {
		this.vCode = vCode;
	}


}