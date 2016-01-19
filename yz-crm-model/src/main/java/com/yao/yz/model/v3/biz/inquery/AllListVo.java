/**
* <p>Copyright: Copyright (c) 2016<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Jan 5, 2016-9:32:16 AM
* @version 1.0
*/
package com.yao.yz.model.v3.biz.inquery;

import java.io.Serializable;

/**
 * 公司名: 壹药网
 * 类名称：AllListVo
 * 类描述：
 * @author wangyulong
 * @date Jan 5, 2016-9:32:16 AM
 */
public class AllListVo implements Serializable{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= -6672795446904162400L;
	
	private String inquery_id;
	private String create_time;
	private String uid;
	private String department_name;
	private String self_desc;
	private String inquery_status;
	private String assign_doctor_name;
	private String assign_doctor_type;
	private String assign_flag;
	private Integer assign_doctor_id;
	
	
	
	public Integer getAssign_doctor_id() {
		return assign_doctor_id;
	}
	public void setAssign_doctor_id(Integer assign_doctor_id) {
		this.assign_doctor_id = assign_doctor_id;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getSelf_desc() {
		return self_desc;
	}
	public void setSelf_desc(String self_desc) {
		this.self_desc = self_desc;
	}
	public String getInquery_status() {
		return inquery_status;
	}
	public void setInquery_status(String inquery_status) {
		this.inquery_status = inquery_status;
	}
	public String getAssign_doctor_name() {
		return assign_doctor_name;
	}
	public void setAssign_doctor_name(String assign_doctor_name) {
		this.assign_doctor_name = assign_doctor_name;
	}
	public String getAssign_doctor_type() {
		return assign_doctor_type;
	}
	public void setAssign_doctor_type(String assign_doctor_type) {
		this.assign_doctor_type = assign_doctor_type;
	}
	public String getAssign_flag() {
		return assign_flag;
	}
	public void setAssign_flag(String assign_flag) {
		this.assign_flag = assign_flag;
	}
	
}
