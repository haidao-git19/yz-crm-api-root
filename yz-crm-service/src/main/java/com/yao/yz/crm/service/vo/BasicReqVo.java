package com.yao.yz.crm.service.vo;

import java.io.Serializable;

/**
 *	描述： 请求数据，所有的请求都必须继承该类
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 6:27:39 PM
 *	@Versin 1.0
 */
public class BasicReqVo implements Serializable,BasicReqValidate{

	private static final long serialVersionUID = -6045611929518051867L;
	/**
	 * 用户认证token
	 */
	private String token;
	
	/**
	 * 登录用户编号
	 */
	private String login_id;
	
	/**
	 * 登录用户角色
	 */
	private String user_role;
	
	/**
	 * 登录用户名
	 */
	private String user_name;
	
	/**
	 * 平台id
	 */
	private String platform_id;
	
	/**
	 * 时间戳
	 */
	private String current_time;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPlatform_id() {
		return platform_id;
	}

	public void setPlatform_id(String platform_id) {
		this.platform_id = platform_id;
	}

	public String getCurrent_time() {
		return current_time;
	}

	public void setCurrent_time(String current_time) {
		this.current_time = current_time;
	}

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		
	}

	@Override
	public String toString() {
		return "BasicReqVo [token=" + token + ", login_id=" + login_id
				+ ", user_role=" + user_role + ", user_name=" + user_name
				+ ", platform_id=" + platform_id + ", current_time="
				+ current_time + "]";
	}
}
