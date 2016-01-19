package com.yao.yz.model.v3.biz.doctor;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 5:41:24 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class WaitCountRes extends AbstractBizBean implements Comparable<WaitCountRes>{
	
	private Integer doctor_id;
	
	private String doctor_name;
	
	private Integer inquery_count;

	private static final long serialVersionUID = 1L;

	@Override
	public String beanName() {
		return "";
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public Integer getInquery_count() {
		return inquery_count;
	}

	public void setInquery_count(Integer inquery_count) {
		this.inquery_count = inquery_count;
	}

	@Override
	public int compareTo(WaitCountRes o) {
//		return o.getInquery_count().compareTo(this.inquery_count);
		return this.inquery_count.compareTo(o.getInquery_count());
	}

	@Override
	public String toString() {
		return "WaitCountRes [doctor_id=" + doctor_id + ", doctor_name="
				+ doctor_name + ", inquery_count=" + inquery_count + "]";
	}
}
