/**
* <p>Copyright: Copyright (c) 2016<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Jan 5, 2016-9:56:00 AM
* @version 1.0
*/
package com.yao.yz.model.v3.biz.inquery;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;

/**
 * 公司名: 壹药网
 * 类名称：AllListReq
 * 类描述：
 * @author wangyulong
 * @date Jan 5, 2016-9:56:00 AM
 */
public class AllListReq extends AbstractReqModel{

	private static final long serialVersionUID = 624745341222144707L;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}
	private String page_index;
	private String department_id;
	private String inquery_status;
	private String assign_doctor_type;
	private String assign_doctor_name;
	private String assign_flag;
	private String uid;
	private String inquery_id;
	private String create_time_begin;
	private String create_time_end;
	
	public String getPage_index() {
		return page_index;
	}
	public void setPage_index(String page_index) {
		this.page_index = page_index;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getInquery_status() {
		return inquery_status;
	}
	public void setInquery_status(String inquery_status) {
		this.inquery_status = inquery_status;
	}
	public String getAssign_doctor_type() {
		return assign_doctor_type;
	}
	public void setAssign_doctor_type(String assign_doctor_type) {
		this.assign_doctor_type = assign_doctor_type;
	}
	public String getAssign_doctor_name() {
		return assign_doctor_name;
	}
	public void setAssign_doctor_name(String assign_doctor_name) {
		this.assign_doctor_name = assign_doctor_name;
	}
	public String getAssign_flag() {
		return assign_flag;
	}
	public void setAssign_flag(String assign_flag) {
		this.assign_flag = assign_flag;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getInquery_id() {
		return inquery_id;
	}
	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}
	public String getCreate_time_begin() {
		return create_time_begin;
	}
	public void setCreate_time_begin(String create_time_begin) {
		this.create_time_begin = create_time_begin;
	}
	public String getCreate_time_end() {
		return create_time_end;
	}
	public void setCreate_time_end(String create_time_end) {
		this.create_time_end = create_time_end;
	}
	
}
