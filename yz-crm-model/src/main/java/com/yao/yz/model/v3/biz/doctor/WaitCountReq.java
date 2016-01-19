package com.yao.yz.model.v3.biz.doctor;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 转诊医生待处理诊单数量查询接口
 * @Autor: wuwenjun
 * @Date: 5:38:26 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class WaitCountReq extends AbstractReqModel{

	private static final long serialVersionUID = -5271624342129793712L;
	
	@BindValidation(_blackable = false,_nullable = false,regexType = RegexType.NUMBER,description = "guest_department")
	private String guest_department;
	
	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}

	public String getGuest_department() {
		return guest_department;
	}

	public void setGuest_department(String guest_department) {
		this.guest_department = guest_department;
	}

	@Override
	public String toString() {
		return "WaitCountReq [guest_department=" + guest_department + "]";
	}
}
