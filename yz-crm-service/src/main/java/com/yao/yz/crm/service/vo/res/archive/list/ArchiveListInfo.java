package com.yao.yz.crm.service.vo.res.archive.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *	描述：健康档案列表详情
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 1:08:53 PM
 *	@Versin 1.0
 */
public class ArchiveListInfo implements Serializable{

	private static final long serialVersionUID = 3619996128749360962L;
	
	public ArchiveListInfo() {
		photos = new ArrayList<String>();
	}
	
	/**
	 * 健康档案编号
	 */
	private Integer archive_id;
	
	/**
	 * 创建时间，格式：yyyy-MM-dd-HH:mm:ss
	 */
	private String create_time;
	
	/**
	 * 患者自述
	 */
	private String self_desc;
	
	/**
	 * 患者上传照片
	 */
	private List<String> photos;
	
	/**
	 * 处理类型，F-初诊，S-复诊，R-回访
	 */
	private String quest_type;
	
	/**
	 * 处理科室名称
	 */
	private String guest_department;
	
	/**
	 * 医生名称，医生姓 + 医生名
	 */
	private String doctor_info;
	
	/**
	 * 档案标题
	 */
	private String illness_key;
	
	/**
	 * 私人医生咨询记录
	 */
	private String consult;
	
	/**
	 * 事件提醒，处理方式  + 预约日期 + 开始时间内 + "-" + 结束时间 + 医生名称  + 预约内容
	 */
	private String notice;

	public Integer getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(Integer archive_id) {
		this.archive_id = archive_id;
	}

	public String getCreate_time() {
		return create_time==null?"":create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getSelf_desc() {
		return self_desc==null?"":self_desc;
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

	public String getQuest_type() {
		return quest_type==null?"":quest_type;
	}

	public void setQuest_type(String quest_type) {
		this.quest_type = quest_type;
	}

	public String getGuest_department() {
		return guest_department==null?"":guest_department;
	}

	public void setGuest_department(String guest_department) {
		this.guest_department = guest_department;
	}

	public String getDoctor_info() {
		return doctor_info==null?"":doctor_info;
	}

	public void setDoctor_info(String doctor_info) {
		this.doctor_info = doctor_info;
	}

	public String getIllness_key() {
		return illness_key==null?"":illness_key;
	}

	public void setIllness_key(String illness_key) {
		this.illness_key = illness_key;
	}

	public String getConsult() {
		return consult==null?"":consult;
	}

	public void setConsult(String consult) {
		this.consult = consult;
	}

	public String getNotice() {
		return notice==null?"":notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	
}
