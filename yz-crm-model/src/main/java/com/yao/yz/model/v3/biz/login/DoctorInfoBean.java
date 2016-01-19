package com.yao.yz.model.v3.biz.login;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: 登录帐号医生信息
 * @Autor: wuwenjun
 * @Date: 4:04:52 PM Dec 29, 2015
 * @Version: v1.0
 * 
 */

public class DoctorInfoBean extends AbstractBizBean{
	
	private static final long serialVersionUID = -325037976508985344L;

	private Integer id;
	
	private Integer doctor_type;
	
	private String first_name;
	
	private String second_name;
	
	private String department_name;
	
	private String face;

	@Override
	public String beanName() {
		// TODO Auto-generated method stub
		return "doctor_info";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDoctor_type() {
		return doctor_type;
	}

	public void setDoctor_type(Integer doctor_type) {
		this.doctor_type = doctor_type;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	@Override
	public String toString() {
		return "DoctorInfoBean [id=" + id + ", doctor_type=" + doctor_type
				+ ", first_name=" + first_name + ", second_name=" + second_name
				+ ", department_name=" + department_name + ", face=" + face
				+ "]";
	}
}
