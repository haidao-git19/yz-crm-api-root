package com.yao.yz.model.v3.biz.archive;

import java.util.ArrayList;
import java.util.List;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 10:26:26 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class ArchiveQueryRes extends AbstractBizBean{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String beanName() {
		return "archive_info";
	}

	private Integer archive_id;
	
	private String nick_name;
	
	private String sex_comment;
	
	private Integer age;
	
	private Integer age_type;
	
	private String self_desc;
	
	private List<String> photos = new ArrayList<String>();
	
	private String doctor_desc;
	
	private String desease_his_record;
	
	private String initial_check;
	
	private String consult;
	
	private String drugs_note;
	
	private Integer inquery_id;
	
	public String getDrugs_note() {
		return drugs_note;
	}

	public void setDrugs_note(String drugs_note) {
		this.drugs_note = drugs_note;
	}

	public Integer getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(Integer archive_id) {
		this.archive_id = archive_id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getSex_comment() {
		return sex_comment;
	}

	public void setSex_comment(String sex_comment) {
		this.sex_comment = sex_comment;
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

	public String getSelf_desc() {
		return self_desc;
	}

	public void setSelf_desc(String self_desc) {
		this.self_desc = self_desc;
	}

	public List<String> getPhotos() {
		return photos;
	}

	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	public String getDoctor_desc() {
		return doctor_desc;
	}

	public void setDoctor_desc(String doctor_desc) {
		this.doctor_desc = doctor_desc;
	}

	public String getDesease_his_record() {
		return desease_his_record;
	}

	public void setDesease_his_record(String desease_his_record) {
		this.desease_his_record = desease_his_record;
	}

	public String getInitial_check() {
		return initial_check;
	}

	public void setInitial_check(String initial_check) {
		this.initial_check = initial_check;
	}

	public String getConsult() {
		return consult;
	}

	public void setConsult(String consult) {
		this.consult = consult;
	}

	public Integer getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(Integer inquery_id) {
		this.inquery_id = inquery_id;
	}

	@Override
	public String toString() {
		return "ArchiveQueryRes [archive_id=" + archive_id + ", nick_name="
				+ nick_name + ", sex_comment=" + sex_comment + ", age=" + age
				+ ", age_type=" + age_type + ", self_desc=" + self_desc
				+ ", photos=" + photos + ", doctor_desc=" + doctor_desc
				+ ", desease_his_record=" + desease_his_record
				+ ", initial_check=" + initial_check + ", consult=" + consult
				+ ", inquery_id=" + inquery_id + "]";
	}
}
