package com.yao.yz.admin.yzadmin.persistence.model.crm;

import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;

/**
 * 
 * @Description: 医生信息扩展扩展
 * @Autor: wuwenjun
 * @Date: 7:22:54 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public class CrmYzDoctorExt extends YzDoctor{

	private static final long serialVersionUID = -5890164043344690386L;
	
	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
