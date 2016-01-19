package com.yao.yz.model.v3.biz.inquery;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 3:06:21 PM Jan 9, 2016
 * @Version: v1.0
 * 
 */

public class InqueryStatusRes extends AbstractBizBean{
	
	private static final long serialVersionUID = 3125119112168361687L;
	
	private String process_flag;
	
	@Override
	public String beanName() {
		return "inquery_status";
	}

	public String getProcess_flag() {
		return process_flag;
	}

	public void setProcess_flag(String process_flag) {
		this.process_flag = process_flag;
	}

	@Override
	public String toString() {
		return "InqueryStatusRes [process_flag=" + process_flag + "]";
	}
}
