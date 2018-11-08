package com.xiaobu.common.base;

import java.io.Serializable;
import java.util.Date;

import com.xiaobu.common.constant.Globals;


/**
 * Entity基础类
 * 
 * @author Mmmmm
 *
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private Integer id;
	/** 创建人 */
	private String createUser;
	/** 创建日期 */
	private Date createDate;
	/** 修改人 */
	private Date createdAt;
	/** 修改日期 */
	private Date updatedAt;
	/** 删除标识(0:正常；1：删除)默认正常 */
	private String delFlag = Globals.USER_TYPE_NORMAL;
	
	/** 创建人名称 */
	private String createUserName;
	/** 修改人名称 */
	private String updateUserName;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	
}
