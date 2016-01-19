package com.yao.yz.crm.service.vo.res.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *	描述：查询用户列表返回数据
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 11:46:13 AM
 *	@Versin 1.0
 */
public class UserListInfoRes implements Serializable{

	private static final long serialVersionUID = 6105137230045404315L;

	private List<UserInfo> user_list;
	
	public UserListInfoRes(){
		user_list = new ArrayList<UserInfo>();
	}

	public List<UserInfo> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<UserInfo> user_list) {
		this.user_list = user_list;
	}
}
