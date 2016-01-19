/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 8, 2015-11:50:31 AM
* @version 1.0
*/
package com.yao.yz.crm.service.vo.req.inquery;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.util.exception.YzRuntimeException;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;
import com.yz.util.tools.validate.ValidateService;

/**
 * 公司名: 壹药网
 * 类名称：InqueryListReq
 * 类描述：
 * @author wangyulong
 * @date Dec 8, 2015-11:50:31 AM
 */
public class InqueryListReq extends BasicReqVo{

	private static final long	serialVersionUID	= -5351022548411855388L;
	private String page_index;
	private String page_size;
	private String department_id;
	private String inquery_flag;
	private String op_doctor_name;
	private String create_time_begin;
	private String create_time_end;
	private String archive_flag;
	private String update_time_begin;
	private String update_time_end;
	
	
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
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER,description = "用户uid")
	private String uid;
	
	@Override
	public void checkParameter(BasicResVo basicResVo) {
		super.checkParameter(basicResVo);
		try{
			ValidateService.valid(this);
		}catch(YzRuntimeException e){
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, e.getErrInlineMsg(), null);
		}
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPage_index() {
		return page_index;
	}
	public void setPage_index(String page_index) {
		this.page_index = page_index;
	}
	public String getPage_size() {
		return page_size;
	}
	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}
	public String getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(String department_id) {
		this.department_id = department_id;
	}
	public String getInquery_flag() {
		return inquery_flag;
	}
	public void setInquery_flag(String inquery_flag) {
		this.inquery_flag = inquery_flag;
	}
	public String getOp_doctor_name() {
		return op_doctor_name;
	}
	public void setOp_doctor_name(String op_doctor_name) {
		this.op_doctor_name = op_doctor_name;
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
	public String getArchive_flag() {
		return archive_flag;
	}
	public void setArchive_flag(String archive_flag) {
		this.archive_flag = archive_flag;
	}

}
