/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 10, 2015-11:41:22 AM
* @version 1.0
*/
package com.yao.yz.crm.service.vo.req.avaya;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.util.exception.YzRuntimeException;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;
import com.yz.util.tools.validate.ValidateService;

/**
 * 公司名: 壹药网
 * 类名称：AvayaReq
 * 类描述：
 * @author wangyulong
 * @date Dec 10, 2015-11:41:22 AM
 */
public class AvayaReq extends BasicReqVo{

	private static final long	serialVersionUID	= 422731319037887661L;

	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER,description = "手机号")
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		super.checkParameter(basicResVo);
		try{
			ValidateService.valid(this);
		}catch(YzRuntimeException e){
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, e.getErrInlineMsg(), null);
		}
	}
	
}
