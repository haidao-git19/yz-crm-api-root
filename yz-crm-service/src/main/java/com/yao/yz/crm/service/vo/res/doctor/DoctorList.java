package com.yao.yz.crm.service.vo.res.doctor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *	描述：医生列表
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 7:37:53 PM
 *	@Versin 1.0
 */
public class DoctorList implements Serializable{
	
	private static final long serialVersionUID = 1010257320590261118L;
	/**
	 * 医生列表
	 */
	private List<DoctorInfo> doctor_list;
	
	public DoctorList() {
		doctor_list = new ArrayList<DoctorInfo>();
	}

	public List<DoctorInfo> getDoctor_list() {
		return doctor_list;
	}

	public void setDoctor_list(List<DoctorInfo> doctor_list) {
		this.doctor_list = doctor_list;
	}
	
}
