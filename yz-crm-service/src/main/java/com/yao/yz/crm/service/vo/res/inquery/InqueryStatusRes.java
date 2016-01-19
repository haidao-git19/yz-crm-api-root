package com.yao.yz.crm.service.vo.res.inquery;

import java.io.Serializable;

/**
 * 诊单状态返回数据模型
 * @version 2.0
 * @author wuwenjun
 *
 */
public class InqueryStatusRes implements Serializable{

	private static final long serialVersionUID = -162503571996597312L;
	
	private InqueryStatusModel inquery_status;
	
	public InqueryStatusRes() {
		inquery_status = new InqueryStatusModel();
	}

	public InqueryStatusModel getInquery_status() {
		return inquery_status;
	}

	public void setInquery_status(InqueryStatusModel inquery_status) {
		this.inquery_status = inquery_status;
	}

	public class InqueryStatusModel {
		/**
		 * 诊单id
		 */
		private Integer inquery_id;
		
		/**
		 * 操作帐号
		 */
		private String op_account_name;
		
		/**
		 * 操作医生姓名
		 */
		private String op_doctor_name;
		
		/**
		 * 通话状态，Y-已接通，N-未接通
		 */
		private String process_flag;
		
		/**
		 * 诊单完成标识，Y-已完成，D-处理中，N-待处理，C-取消
		 */
		private String finish_flag;

		public Integer getInquery_id() {
			return inquery_id;
		}

		public void setInquery_id(Integer inquery_id) {
			this.inquery_id = inquery_id;
		}

		public String getOp_account_name() {
			return op_account_name == null ? "":op_account_name;
		}

		public void setOp_account_name(String op_account_name) {
			this.op_account_name = op_account_name;
		}

		public String getOp_doctor_name() {
			return op_doctor_name == null ? "":op_doctor_name;
		}

		public void setOp_doctor_name(String op_doctor_name) {
			this.op_doctor_name = op_doctor_name;
		}

		public String getProcess_flag() {
			return process_flag;
		}

		public void setProcess_flag(String process_flag) {
			this.process_flag = process_flag;
		}

		public String getFinish_flag() {
			return finish_flag;
		}

		public void setFinish_flag(String finish_flag) {
			this.finish_flag = finish_flag;
		}
	}
}
