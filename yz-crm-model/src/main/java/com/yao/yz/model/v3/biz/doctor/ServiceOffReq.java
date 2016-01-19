package com.yao.yz.model.v3.biz.doctor;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 医生停诊请求
 * @Autor: wuwenjun
 * @Date: 12:01:41 PM Dec 31, 2015
 * @Version: v1.0
 * 
 */

public class ServiceOffReq extends AbstractReqModel{
	
	private static final long serialVersionUID = 5830388454590262708L;
	
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER,description = "doctor_id")
	private String doctor_id;
	
	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		// TODO Auto-generated method stub
		return true;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	@Override
	public String toString() {
		return "ServiceOffReq [doctor_id=" + doctor_id + "]";
	}
	
}
