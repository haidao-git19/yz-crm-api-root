package com.yao.yz.model.v3.biz.doctor;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 医生上下班状态查询请求
 * @Autor: wuwenjun
 * @Date: 12:19:18 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class ServiceStatusReq extends AbstractReqModel{

	private static final long serialVersionUID = 4972577823198146886L;
	
	@BindValidation(_blackable = false,_nullable = false,regexType = RegexType.NUMBER,description = "doctor_id")
	private String doctor_id;
	
	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
}
