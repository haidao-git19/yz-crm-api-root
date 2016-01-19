//package com.yao.yz.crm.service.vo.res.archive.v2;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.mail.internet.NewsAddress;
//
//import com.yao.yz.model.v3.biz.drug.DrugBean;
///**
// * 健康档案详情数据模型
// * 
// * @version 2.0 新增
// * @author wuwenjun
// *
// */
//public class ArchiveDetailRes implements Serializable{
//
//	private static final long serialVersionUID = 1505376751994819159L;
//	
//	private ArchiveDetailModel archive_info;
//	
//	public ArchiveDetailRes () {
//		this.archive_info = new ArchiveDetailModel();
//	}
//	
//	public ArchiveDetailModel getArchive_info() {
//		return archive_info;
//	}
//
//	public void setArchive_info(ArchiveDetailModel archive_info) {
//		this.archive_info = archive_info;
//	}
//	
//	
//
//	@Override
//	public String toString() {
//		return "ArchiveDetailRes [archive_info=" + archive_info.toString() + "]";
//	}
//
//	public class ArchiveDetailModel{
//		
//		private Integer archive_id;
//		
//		private String nick_name;
//		
//		private String sex_comment;
//		
//		private Integer age;
//		
//		private Integer age_type;
//		
//		private String self_desc;
//		
//		private List<String> photos = new ArrayList<String>();
//		
//		private Integer guest_department;
//		
//		private Integer doctor_id;
//		
//		private String quest_type;
//		
//		private String doctor_desc;
//		
//		private String desease_his_record;
//		
//		private String initial_check;
//		
//		private String consult;
//		
//		private Integer inquery_id;
//		
//		private List<DrugBean> drugs = new ArrayList<DrugBean>();
//		
//
//		public List<DrugBean> getDrugs() {
//			return drugs;
//		}
//
//		public void setDrugs(List<DrugBean> drugs) {
//			this.drugs = drugs;
//		}
//
//		public Integer getArchive_id() {
//			return archive_id;
//		}
//
//		public void setArchive_id(Integer archive_id) {
//			this.archive_id = archive_id;
//		}
//
//		public String getNick_name() {
//			return nick_name;
//		}
//
//		public void setNick_name(String nick_name) {
//			this.nick_name = nick_name;
//		}
//
//		public String getSex_comment() {
//			return sex_comment;
//		}
//
//		public void setSex_comment(String sex_comment) {
//			this.sex_comment = sex_comment;
//		}
//
//		public Integer getAge() {
//			return age;
//		}
//
//		public void setAge(Integer age) {
//			this.age = age;
//		}
//
//		public Integer getAge_type() {
//			return age_type;
//		}
//
//		public void setAge_type(Integer age_type) {
//			this.age_type = age_type;
//		}
//
//		public String getSelf_desc() {
//			return self_desc;
//		}
//
//		public void setSelf_desc(String self_desc) {
//			this.self_desc = self_desc;
//		}
//
//		public List<String> getPhotos() {
//			return photos;
//		}
//
//		public void setPhotos(List<String> photos) {
//			this.photos = photos;
//		}
//
//		public Integer getGuest_department() {
//			return guest_department;
//		}
//
//		public void setGuest_department(Integer guest_department) {
//			this.guest_department = guest_department;
//		}
//
//		public Integer getDoctor_id() {
//			return doctor_id;
//		}
//
//		public void setDoctor_id(Integer doctor_id) {
//			this.doctor_id = doctor_id;
//		}
//
//		public String getQuest_type() {
//			return quest_type;
//		}
//
//		public void setQuest_type(String quest_type) {
//			this.quest_type = quest_type;
//		}
//
//		public String getDoctor_desc() {
//			return doctor_desc;
//		}
//
//		public void setDoctor_desc(String doctor_desc) {
//			this.doctor_desc = doctor_desc;
//		}
//
//		public String getDesease_his_record() {
//			return desease_his_record;
//		}
//
//		public void setDesease_his_record(String desease_his_record) {
//			this.desease_his_record = desease_his_record;
//		}
//
//		public String getInitial_check() {
//			return initial_check;
//		}
//
//		public void setInitial_check(String initial_check) {
//			this.initial_check = initial_check;
//		}
//
//		public String getConsult() {
//			return consult;
//		}
//
//		public void setConsult(String consult) {
//			this.consult = consult;
//		}
//		
//		public Integer getInquery_id() {
//			return inquery_id;
//		}
//
//		public void setInquery_id(Integer inquery_id) {
//			this.inquery_id = inquery_id;
//		}
//
//		@Override
//		public String toString() {
//			return "ArchiveDetailModel [archive_id=" + archive_id
//					+ ", nick_name=" + nick_name + ", sex_comment="
//					+ sex_comment + ", age=" + age + ", age_type=" + age_type
//					+ ", self_desc=" + self_desc + ", photos=" + photos
//					+ ", guest_department=" + guest_department + ", doctor_id="
//					+ doctor_id + ", quest_type=" + quest_type
//					+ ", doctor_desc=" + doctor_desc + ", desease_his_record="
//					+ desease_his_record + ", initial_check=" + initial_check
//					+ ", consult=" + consult + ", inquery_id=" + inquery_id
//					+ "]";
//		}
//	}
//}
