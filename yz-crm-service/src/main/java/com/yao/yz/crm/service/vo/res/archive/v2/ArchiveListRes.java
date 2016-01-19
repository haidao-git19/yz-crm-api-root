//package com.yao.yz.crm.service.vo.res.archive.v2;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.yao.yz.model.v3.biz.drug.DrugBean;
///**
// * 用户健康档案列表查询
// * 
// * @version 2.0新增
// * @author wuwenjun
// *
// */
//public class ArchiveListRes implements Serializable{
//
//	private static final long serialVersionUID = -839046926854968471L;
//	
//	public ArchiveListRes() {
//		photos = new ArrayList<String>();
//	}
//
//	/**
//	 * 健康档案id
//	 */
//	private Integer archive_id;
//	
//	/**
//	 * 创建时间戳
//	 */
//	private Long create_time;
//	
//	/**
//	 * 处理科室名称
//	 */
//	private String guest_department_name;
//	
//	/**
//	 * 处理医生姓名
//	 */
//	private String doctor_name;
//	
//	/**
//	 * 问诊患者称呼
//	 */
//	private String nick_name;
//	
//	/**
//	 * 问诊患者性别
//	 */
//	private String sex_comment;
//	
//	/**
//	 * 问诊患者年龄
//	 */
//	private Integer age;
//	
//	/**
//	 * 问诊患者年龄类型
//	 */
//	private Integer age_type;
//	
//	/**
//	 * 患者自诉
//	 */
//	private String self_desc;
//	
//	/**
//	 * 图片列表
//	 */
//	private List<String> photos;
//	
//	/**
//	 * 主诉
//	 */
//	private String doctor_desc;
//	
//	/**
//	 * 现病史
//	 */
//	private String desease_his_record;
//	
//	/**
//	 * 诊断
//	 */
//	private String initial_check;
//	
//	/**
//	 * 建议
//	 */
//	private String consult;
//	
//	/**
//	 * 诊单id
//	 */
//	private Integer inquery_id;
//	
//	public Integer getInquery_id() {
//		return inquery_id;
//	}
//
//	public void setInquery_id(Integer inquery_id) {
//		this.inquery_id = inquery_id;
//	}
//
//	public Integer getArchive_id() {
//		return archive_id;
//	}
//
//	public void setArchive_id(Integer archive_id) {
//		this.archive_id = archive_id;
//	}
//
//	public Long getCreate_time() {
//		return create_time;
//	}
//
//	public void setCreate_time(Long create_time) {
//		this.create_time = create_time;
//	}
//
//	public String getGuest_department_name() {
//		return guest_department_name;
//	}
//
//	public void setGuest_department_name(String guest_department_name) {
//		this.guest_department_name = guest_department_name;
//	}
//
//	public String getDoctor_name() {
//		return doctor_name;
//	}
//
//	public void setDoctor_name(String doctor_name) {
//		this.doctor_name = doctor_name;
//	}
//
//	public String getNick_name() {
//		return nick_name;
//	}
//
//	public void setNick_name(String nick_name) {
//		this.nick_name = nick_name;
//	}
//
//	public String getSex_comment() {
//		return sex_comment;
//	}
//
//	public void setSex_comment(String sex_comment) {
//		this.sex_comment = sex_comment;
//	}
//
//
//	public Integer getAge() {
//		return age;
//	}
//
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//
//	public Integer getAge_type() {
//		return age_type;
//	}
//
//	public void setAge_type(Integer age_type) {
//		this.age_type = age_type;
//	}
//
//	public String getSelf_desc() {
//		return self_desc;
//	}
//
//	public void setSelf_desc(String self_desc) {
//		this.self_desc = self_desc;
//	}
//
//	public List<String> getPhotos() {
//		return photos;
//	}
//
//	public void setPhotos(List<String> photos) {
//		this.photos = photos;
//	}
//
//	public String getDoctor_desc() {
//		return doctor_desc;
//	}
//
//	public void setDoctor_desc(String doctor_desc) {
//		this.doctor_desc = doctor_desc;
//	}
//
//	public String getDesease_his_record() {
//		return desease_his_record;
//	}
//
//	public void setDesease_his_record(String desease_his_record) {
//		this.desease_his_record = desease_his_record;
//	}
//
//	public String getInitial_check() {
//		return initial_check;
//	}
//
//	public void setInitial_check(String initial_check) {
//		this.initial_check = initial_check;
//	}
//
//	public String getConsult() {
//		return consult;
//	}
//
//	public void setConsult(String consult) {
//		this.consult = consult;
//	}
//	
//	@Override
//	public String toString() {
//		return "ArchiveListRes [archive_id=" + archive_id + ", create_time="
//				+ create_time + ", guest_department_name="
//				+ guest_department_name + ", doctor_name=" + doctor_name
//				+ ", nick_name=" + nick_name + ", sex_comment=" + sex_comment
//				+ ", age=" + age + ", age_type=" + age_type + ", self_desc="
//				+ self_desc + ", photos=" + photos + ", doctor_desc="
//				+ doctor_desc + ", desease_his_record=" + desease_his_record
//				+ ", initial_check=" + initial_check + ", consult=" + consult
//				+ "]";
//	}
//	
//}
