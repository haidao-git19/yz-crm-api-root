package com.yao.yz.model.v3.biz.doctor;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: 医生接诊状态查询返回结果
 * @Autor: wuwenjun
 * @Date: 12:26:45 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class ServiceStatusRes extends AbstractBizBean{

	private static final long serialVersionUID = -758664553104352788L;

	@Override
	public String beanName() {
		return "status";
	}
	
	private Integer doctor_id;
	
	private Integer status;

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ServiceStatusRes [doctor_id=" + doctor_id + ", status="
				+ status + "]";
	}
}
