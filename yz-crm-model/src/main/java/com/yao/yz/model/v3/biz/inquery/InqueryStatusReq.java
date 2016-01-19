package com.yao.yz.model.v3.biz.inquery;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 诊单状态查询请求
 * @Autor: wuwenjun
 * @Date: 2:55:53 PM Jan 9, 2016
 * @Version: v1.0
 * 
 */

public class InqueryStatusReq extends AbstractReqModel{

	private static final long serialVersionUID = 3286587494745302587L;
	
	@BindValidation(_blackable = false,_nullable = false, regexType = RegexType.NUMBER,description = "inquery_id")
	private String inquery_id;
	
	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}

	public String getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}

	@Override
	public String toString() {
		return "InqueryStatusReq [inquery_id=" + inquery_id + "]";
	}
}
