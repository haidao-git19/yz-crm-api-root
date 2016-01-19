package com.yao.yz.crm.service.vo.res.user;

import java.io.Serializable;

/**
 *	描述：用户信息返回数据
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 8:18:42 PM
 *	@Versin 1.0
 */
public class UserInfoRes implements Serializable{
	
	private static final long serialVersionUID = -2271454758136171785L;
	
	private UserInfo user_info;

	public UserInfoRes() {
		user_info = new UserInfo();
	}

	public UserInfo getUser_info() {
		return user_info;
	}


	public void setUser_info(UserInfo user_info) {
		this.user_info = user_info;
	}
	
}
