package com.yao.yz.model.v3.biz.inquery;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 转诊请求
 * @Autor: wuwenjun
 * @Date: 10:15:24 PM Jan 11, 2016
 * @Version: v1.0
 * 
 */

public class InqueryTransferReq extends AbstractReqModel{

	private static final long serialVersionUID = 8071690054807981051L;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}

	@BindValidation(_blackable = false, _nullable = false, description = "inquery_id", regexType = RegexType.NUMBER)
	private String inquery_id;
	
	@BindValidation(_blackable = false,_nullable = false, description = "to_doctor_id", regexType = RegexType.NUMBER)
	private String to_doctor_id;
	
	@BindValidation(_blackable = false, _nullable = false, description = "from_doctor_id", regexType = RegexType.NUMBER)
	private String from_doctor_id;

	public String getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}

	public String getTo_doctor_id() {
		return to_doctor_id;
	}

	public void setTo_doctor_id(String to_doctor_id) {
		this.to_doctor_id = to_doctor_id;
	}

	public String getFrom_doctor_id() {
		return from_doctor_id;
	}

	public void setFrom_doctor_id(String from_doctor_id) {
		this.from_doctor_id = from_doctor_id;
	}

	@Override
	public String toString() {
		return "InqueryTransferReq [inquery_id=" + inquery_id
				+ ", to_doctor_id=" + to_doctor_id + ", from_doctor_id="
				+ from_doctor_id + "]";
	}
}
