package com.yao.yz.crm.service.vo.req.archive.v2;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.util.exception.YzRuntimeException;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;
import com.yz.util.tools.validate.ValidateService;

/**
 *	描述：健康档案列表请求对象
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 1:21:00 PM
 *	@Versin 1.0
 */
public class ArchiveListReq extends BasicReqVo{

	private static final long serialVersionUID = 3019594954394779510L;

	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER, description = "用户uid")
	private String uid;
	
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER, description = "科室编号")
	private String department_id;

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
	
	@Override
	public String toString() {
		return "ArchiveListReq [uid=" + uid + ", department_id="
				+ department_id + "]" + super.toString();
	}

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		super.checkParameter(basicResVo);
		try {
			ValidateService.valid(this);
		} catch (YzRuntimeException e) {
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, e.getErrInlineMsg(), null);
		}
	}
}
