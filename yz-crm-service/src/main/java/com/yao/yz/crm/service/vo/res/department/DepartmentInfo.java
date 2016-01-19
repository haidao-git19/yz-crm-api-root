package com.yao.yz.crm.service.vo.res.department;

import java.io.Serializable;

/**
 *	描述：科室信息
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 6:30:00 PM
 *	@Versin 1.0
 */
public class DepartmentInfo implements Serializable{

	private static final long serialVersionUID = -6335502329257359426L;
	
	private int tag_key;
	
	private String name;

	public int getTag_key() {
		return tag_key;
	}

	public void setTag_key(int tag_key) {
		this.tag_key = tag_key;
	}

	public String getName() {
		return name==null?"":name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
