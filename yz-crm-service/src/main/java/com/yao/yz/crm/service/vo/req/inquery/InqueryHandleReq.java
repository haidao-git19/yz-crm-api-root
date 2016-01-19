package com.yao.yz.crm.service.vo.req.inquery;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.util.exception.YzRuntimeException;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;
import com.yz.util.tools.validate.ValidateService;

/**
 * 待处理诊单处理请求数据模型
 * @author wuwenjun
 *
 */
public class InqueryHandleReq extends BasicReqVo{

	private static final long serialVersionUID = -2670716263911655901L;

	@BindValidation(_nullable = false,_blackable = false,regexType = RegexType.NUMBER,description = "诊单id")
	private String inquery_id;

	public String getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
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

	@Override
	public String toString() {
		return "InqueryHandleReq [inquery_id=" + inquery_id + "]";
	}

	
}
