package com.yao.yz.model.v3.biz.inquery;

import java.util.ArrayList;
import java.util.List;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: 诊单信息
 * @Autor: wuwenjun
 * @Date: 8:40:26 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public class InqueryInfoRes extends AbstractBizBean{
	
	private static final long serialVersionUID = -1944355163355408935L;

	private Integer inquery_id;
	
	private Integer uid;
	
	private List<String> photos = new ArrayList<String>();
	
	private String self_desc;
	
	private Integer age;
	
	private Integer age_type;
	
	private String sex_comment;
	
	private String finish_flag;
	
	@Override
	public String beanName() {
		return "inquery_info";
	}

	public Integer getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(Integer inquery_id) {
		this.inquery_id = inquery_id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public String getSelf_desc() {
		return self_desc;
	}

	public void setSelf_desc(String self_desc) {
		this.self_desc = self_desc;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge_type() {
		return age_type;
	}

	public void setAge_type(Integer age_type) {
		this.age_type = age_type;
	}

	public String getSex_comment() {
		return sex_comment;
	}

	public void setSex_comment(String sex_comment) {
		this.sex_comment = sex_comment;
	}

	public String getFinish_flag() {
		return finish_flag;
	}

	public void setFinish_flag(String finish_flag) {
		this.finish_flag = finish_flag;
	}

	@Override
	public String toString() {
		return "InqueryInfoRes [inquery_id=" + inquery_id + ", uid=" + uid
				+ ", photos=" + photos + ", self_desc=" + self_desc + ", age="
				+ age + ", age_type=" + age_type + ", sex_comment="
				+ sex_comment + ", finish_flag=" + finish_flag + "]";
	}
}
