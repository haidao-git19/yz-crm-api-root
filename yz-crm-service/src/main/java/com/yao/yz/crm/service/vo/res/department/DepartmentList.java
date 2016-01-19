package com.yao.yz.crm.service.vo.res.department;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *	描述： 科室列表
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 6:32:02 PM
 *	@Versin 1.0
 */
public class DepartmentList implements Serializable{

	private static final long serialVersionUID = -4745230576506938820L;

	private List<DepartmentInfo> department_list;
	
	public DepartmentList() {
		department_list = new ArrayList<DepartmentInfo>();
	}

	public List<DepartmentInfo> getDepartment_list() {
		return department_list;
	}

	public void setDepartment_list(List<DepartmentInfo> department_list) {
		this.department_list = department_list;
	}
	
}
