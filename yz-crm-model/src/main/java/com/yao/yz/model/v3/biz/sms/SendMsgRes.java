package com.yao.yz.model.v3.biz.sms;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: 短信发送记录
 * @Autor: wuwenjun
 * @Date: 8:44:26 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public class SendMsgRes extends AbstractBizBean{
	
	private static final long serialVersionUID = 8129270987997800814L;

	private Long send_time;
	
	private String send_type;
	
	private String send_doctor_name;

	@Override
	public String beanName() {
		return "";
	}

	public Long getSend_time() {
		return send_time;
	}

	public void setSend_time(Long send_time) {
		this.send_time = send_time;
	}

	public String getSend_type() {
		return send_type;
	}

	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}

	public String getSend_doctor_name() {
		return send_doctor_name;
	}

	public void setSend_doctor_name(String send_doctor_name) {
		this.send_doctor_name = send_doctor_name;
	}

	@Override
	public String toString() {
		return "SendMsgRes [send_time=" + send_time + ", send_type="
				+ send_type + ", send_doctor_name=" + send_doctor_name + "]";
	}
}
