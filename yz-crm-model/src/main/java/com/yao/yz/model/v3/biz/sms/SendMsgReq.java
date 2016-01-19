package com.yao.yz.model.v3.biz.sms;

import org.apache.commons.lang.StringUtils;

import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 短信发送请求
 * @Autor: wuwenjun
 * @Date: 9:28:09 AM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class SendMsgReq extends AbstractReqModel{

	private static final long serialVersionUID = -7579846039195754503L;

	private String send_type;
	
	@BindValidation(_blackable = false,_nullable = false , regexType = RegexType.NUMBER ,description = "inquery_id")
	private String inquery_id;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		if (StringUtils.isBlank(send_type)) {
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 40000);
			return false;
		} else if (!ModelConstants.SMS_CANCEL_INQUERY.equals(send_type) && !ModelConstants.SMS_EXCEPTION.equals(send_type) && !ModelConstants.SMS_NOT_ACCEPT.equals(send_type)){
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 40000);
			return false;
		}
		return true;
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}

	public String getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}

	@Override
	public String toString() {
		return "SendMsgReq [send_type=" + send_type + ", inquery_id="
				+ inquery_id + "]";
	}
}
