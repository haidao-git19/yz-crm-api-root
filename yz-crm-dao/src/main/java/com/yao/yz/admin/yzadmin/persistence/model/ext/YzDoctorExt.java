package com.yao.yz.admin.yzadmin.persistence.model.ext;

import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;

public class YzDoctorExt extends YzDoctor{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private String doctorName;
	
	private String departmentName;
	
	private String titleName;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
}
