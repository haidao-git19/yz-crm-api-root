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
public class UndoListReq extends AbstractReqModel{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= 1L;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}

	//@BindValidation(_blackable = false, _nullable = false, description = "doctor_id")
	private String doctor_id;
	
	private String order_by;
	
	private String inquery_status;

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getOrder_by() {
		return order_by;
	}

	public void setOrder_by(String order_by) {
		this.order_by = order_by;
	}

	public String getInquery_status() {
		return inquery_status;
	}

	public void setInquery_status(String inquery_status) {
		this.inquery_status = inquery_status;
	}
	
}
