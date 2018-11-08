package com.xiaobu.common.util;

public enum ResourceEnum {

	UPLOAD_BASE_PATH("upload_base_path"),
	USER_INIT_PASSWORD("user_init_password");
	
	
	private ResourceEnum(String name) {
		this.name = name;
	}

	private String name;

	public String getValue() {
	
		return this.name;
	}
}
