package com.yao.yz.model.v3.biz.sms;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 短信发送记录请求
 * @Autor: wuwenjun
 * @Date: 4:52:00 PM Jan 9, 2016
 * @Version: v1.0
 * 
 */

public class ListMsgReq extends AbstractReqModel {

	private static final long serialVersionUID = 2345907600881662983L;
	
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER, description = "inquery_id")
	private String inquery_id;

	public String getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}

	@Override
	public String toString() {
		return "ListMsgReq [inquery_id=" + inquery_id + "]";
	}
}
