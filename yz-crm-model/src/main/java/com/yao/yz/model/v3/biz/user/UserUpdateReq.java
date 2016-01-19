package com.yao.yz.model.v3.biz.user;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 用户称呼更新请求
 * @Autor: wuwenjun
 * @Date: 3:15:48 PM Dec 29, 2015
 * @Version: v1.0
 * 
 */

public class UserUpdateReq extends AbstractReqModel{

	private static final long serialVersionUID = -7127300270108780959L;
	
	@BindValidation(_blackable = false, _nullable = false,regexType = RegexType.NUMBER, description = "uid")
	private String uid;
	
	private String second_name;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		// TODO Auto-generated method stub
		return true;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	@Override
	public String toString() {
		return "[uid=" + uid + ", second_name=" + second_name
				+ "]";
	}
}
