/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-11:09:32 AM
* @version 1.0
*/
package com.yao.yz.crm.service.vo.res;

import java.io.Serializable;

/**
 * 公司名: 壹药网
 * 类名称：Inquery
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-11:09:32 AM
 */
public class Inquery2 implements Serializable{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= 6079315430559055814L;

	private String inquery_id;
	private String create_time;
	private String user_id;
	private String self_desc;
	private String inquery_status;
	private String op_doctor_name;
	private String user_name;
	private String department_name;
	private String archive_status;
	private String archive_id;
	private String update_time;
	
	/** V1.3 add*/
	private String sms_type;
	private String sms_send_time;
	
	
	
	public String getSms_type() {
		return sms_type;
	}
	public void setSms_type(String sms_type) {
		this.sms_type = sms_type;
	}
	public String getSms_send_time() {
		return sms_send_time;
	}
	public void setSms_send_time(String sms_send_time) {
		this.sms_send_time = sms_send_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getArchive_id() {
		return archive_id;
	}
	public void setArchive_id(String archive_id) {
		this.archive_id = archive_id;
	}
	public String getInquery_id() {
		return inquery_id;
	}
	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getInquery_status() {
		return inquery_status;
	}
	public void setInquery_status(String inquery_status) {
		this.inquery_status = inquery_status;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getArchive_status() {
		return archive_status;
	}
	public void setArchive_status(String archive_status) {
		this.archive_status = archive_status;
	}
	public String getOp_doctor_name() {
		return op_doctor_name;
	}
	public void setOp_doctor_name(String op_doctor_name) {
		this.op_doctor_name = op_doctor_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSelf_desc() {
		return self_desc;
	}
	public void setSelf_desc(String self_desc) {
		this.self_desc = self_desc;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
