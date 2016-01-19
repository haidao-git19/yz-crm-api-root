package com.yao.yz.crm.service.vo.res.inquery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 待处理诊单处理接口返回数据模型
 * @author wuwenjun
 * @version 2.0
 *
 */
public class InqueryHandleRes implements Serializable{
	
	private static final long serialVersionUID = 8670394356298121779L;
	
	private InqueryHandleModel inquery_info;
	
	public InqueryHandleRes () {
		this.inquery_info = new InqueryHandleModel();
	}
	
	
	public InqueryHandleModel getInquery_info() {
		return inquery_info;
	}


	public void setInquery_info(InqueryHandleModel inquery_info) {
		this.inquery_info = inquery_info;
	}


	public class InqueryHandleModel {
		
		private InqueryHandleModel() {
			photos = new ArrayList<String>();
		}
		
		/**
		 * 诊单id
		 */
		private Integer inquery_id;
		
		/**
		 * 用户uid
		 */
		private Integer uid;
		
		/**
		 * 用户提交的图片列表
		 */
		private List<String> photos;
		
		/**
		 * 患者自诉
		 */
		private String self_desc;
		
		/**
		 * 用户app端选择的科室编号
		 */
		private Integer guest_department;
		
		/**
		 * 问诊患者年龄
		 */
		private Integer age;
		
		/**
		 * 问诊患者年龄类型
		 */
		private Integer age_type;
		
		/**
		 * 问诊患者性别
		 */
		private String sex_comment;
		
		/**
		 * 更新后的处理人登录帐号
		 */
		private String op_account_name;
		
		/**
		 * 更新后的处理人医生
		 */
		private String op_doctor_name;
		
		/**
		 * 更新的处理状态
		 */
		private String finish_flag;
		
		/**
		 * 处理医生编号
		 */
		private Integer doctor_id;
		
		/**
		 * 诊单创建时间，13位时间戳
		 */
		private Long create_time;

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

		public Integer getGuest_department() {
			return guest_department;
		}

		public void setGuest_department(Integer guest_department) {
			this.guest_department = guest_department;
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

		public String getOp_account_name() {
			return op_account_name;
		}

		public void setOp_account_name(String op_account_name) {
			this.op_account_name = op_account_name;
		}

		public String getOp_doctor_name() {
			return op_doctor_name;
		}

		public void setOp_doctor_name(String op_doctor_name) {
			this.op_doctor_name = op_doctor_name;
		}

		public String getFinish_flag() {
			return finish_flag;
		}

		public void setFinish_flag(String finish_flag) {
			this.finish_flag = finish_flag;
		}

		public Integer getDoctor_id() {
			return doctor_id;
		}

		public void setDoctor_id(Integer doctor_id) {
			this.doctor_id = doctor_id;
		}

		public Long getCreate_time() {
			return create_time;
		}

		public void setCreate_time(Long create_time) {
			this.create_time = create_time;
		}
	}
	
}
