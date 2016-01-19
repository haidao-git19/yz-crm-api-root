package com.yao.yz.model.v3.base.req;

import java.io.Serializable;

import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.util.exception.YzRuntimeException;
import com.yz.util.tools.validate.ValidateService;

/**
 * 
 * @Description: 请求数据模型抽象类,所有的业务请求都必须继承该类，用于包装外部请求数据
 * @Autor: wuwenjun
 * @Date: 11:21:32 AM Dec 28, 2015
 * @Version: v1.0
 * 
 */

public abstract class AbstractReqModel implements Serializable,ReqModel,ReqValidate{
	
	private static final long serialVersionUID = -1861851013190049821L;
	
	private String token;
	
	private String login_id;
	
	private String user_role;
	
	private String user_name;
	
	private String platform_id;
	
	private String current_time;
	
	@Override
	public boolean validate(AbstractResModel resModel) {
		// 系统级校验
		try {
			ValidateService.valid(this);
		} catch (YzRuntimeException e) {
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, e.getErrInlineMsg());
			return false;
		}
		
		// 自定义校验
		return customValidate(resModel);
	}
	
	/**
	 * 自定义业务校验，由子类自行实现，无自定义业务校验时子类必须返回true
	 * @param abstractResModel 返回数据模型
	 * @return 请求数据校验结果，true-校验通过，false-校验未通过
	 */
	protected abstract boolean customValidate(AbstractResModel abstractResModel);

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
	public String toString() {
		return "[token=" + token + ", login_id=" + login_id
				+ ", user_role=" + user_role + ", user_name=" + user_name
				+ ", platform_id=" + platform_id + ", current_time="
				+ current_time + "]";
	}
	
	/**
	 * 公共参数
	 * @return
	 */
	public String pubString(){
		return "[token=" + token + ", login_id=" + login_id
				+ ", user_role=" + user_role + ", user_name=" + user_name
				+ ", platform_id=" + platform_id + ", current_time="
				+ current_time + "]";
	}
}
