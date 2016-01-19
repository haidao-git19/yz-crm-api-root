package com.yao.yz.crm.service.vo.res.user;

import java.io.Serializable;

/**
 *	描述：用户信息
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 8:15:11 PM
 *	@Versin 1.0
 */
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 794749575421398169L;

	/**
	 * 用户uid
	 */
	private Integer uid;
	
	/**
	 * 用户称呼
	 */
	private String user_call;
	
	/**
	 * 用户姓
	 */
	private String second_name;
	
	/**
	 * 用户年龄
	 */
	private Integer age;
	
	/**
	 * 用户性别，F-女，M-男
	 */
	private String sex;
	
	/**
	 * 电话
	 */
	private String mobile;
	

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUser_call() {
		return user_call==null?"":user_call;
	}

	public void setUser_call(String user_call) {
		this.user_call = user_call;
	}

	public String getSecond_name() {
		return second_name==null?"":second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex==null?"":sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile==null?"":mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
