/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 30, 2015-3:28:17 PM
* @version 1.0
*/
package com.yao.yz.model.v3.biz.inquery;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;

/**
 * 公司名: 壹药网
 * 类名称：UndoListReq
 * 类描述：
 * @author wangyulong
 * @date Dec 30, 2015-3:28:17 PM
 */
public class HistoryListReq extends AbstractReqModel{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= 1L;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}
	
	//@BindValidation(_blackable = false, _nullable = false, description = "pageIndex")
	private String page_index;
	//@BindValidation(_blackable = false, _nullable = false, description = "doctor_id")
	private String doctor_id;
	
	private String department_id;
	
	private String uid;
	
	private String inquery_id;
	
	private String op_doctor_name;
	
	private String archive_flag;
	
	private String update_time_begin;
	
	private String update_time_end;
	
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}

	public String getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}


	public String getOp_doctor_name() {
		return op_doctor_name;
	}

	public void setOp_doctor_name(String op_doctor_name) {
		this.op_doctor_name = op_doctor_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getArchive_flag() {
		return archive_flag;
	}

	public void setArchive_flag(String archive_flag) {
		this.archive_flag = archive_flag;
	}

	public String getUpdate_time_begin() {
		return update_time_begin;
	}

	public void setUpdate_time_begin(String update_time_begin) {
		this.update_time_begin = update_time_begin;
	}

	public String getUpdate_time_end() {
		return update_time_end;
	}

	public void setUpdate_time_end(String update_time_end) {
		this.update_time_end = update_time_end;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getPage_index() {
		return page_index;
	}

	public void setPage_index(String page_index) {
		this.page_index = page_index;
	}

	
}
