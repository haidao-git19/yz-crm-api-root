package com.yao.yz.crm.service.vo.res.doctor;

import java.io.Serializable;

/**
 *	描述：医生信息
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 7:36:11 PM
 *	@Versin 1.0
 */
public class DoctorInfo implements Serializable{

	private static final long serialVersionUID = 744404543180972304L;

	/**
	 * 医生编号
	 */
	private Integer doctor_id;
	
	/**
	 * 医生信息
	 */
	private String doctor_info;

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_info() {
		return doctor_info==null?"":doctor_info;
	}

	public void setDoctor_info(String doctor_info) {
		this.doctor_info = doctor_info;
	}
	
}
