//package com.yao.yz.crm.service.vo.req.msg;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicReqVo;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.util.exception.YzRuntimeException;
//import com.yz.util.tools.validate.BindValidation;
//import com.yz.util.tools.validate.RegexType;
//import com.yz.util.tools.validate.ValidateService;
//
///**
// *	描述：发送短信请求
// *	@Author wuwenjun
// *	@Date Nov 6, 2015 2:15:07 PM
// *	@Versin 1.0
// */
//public class SendMsgReq extends BasicReqVo{
//	
//	private static final long serialVersionUID = 4953388909766526182L;
//
//	/**
//	 * 用户编号
//	 */
//	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER, description = "用户编号")
//	private String uid;
//	
//	/**
//	 * 短信类型
//	 */
//	@BindValidation(_blackable = false, _nullable = false , description = "短信类型")
//	private String send_type;
//	
//	/**
//	 * 诊单id
//	 * v2.0新增字段
//	 */
//	private String inquery_id;
//	
//	@Override
//	public void checkParameter(BasicResVo basicResVo) {
//		try {
//			// 通用校验
//			ValidateService.valid(this);
//			
//			// 扩展校验短信类型校验
//			if (!ServiceContant.SMS_CANCEL_INQUERY.equals(this.send_type) && !ServiceContant.SMS_NOT_ACCEPT.equals(this.send_type) & !ServiceContant.SMS_EXCEPTION.equals(this.send_type)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("REQNAME_SMS_SEND_00000"), null);
//				return;
//			}
//			
//			// 扩展校验诊单id
//			if (StringUtils.isNotBlank(this.inquery_id)) {
//				if (!this.inquery_id.matches("[0-9]*")) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("REQNAME_SMS_SEND_00001"), null);
//					return;
//				}
//			}
//		} catch (YzRuntimeException e) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, e.getErrInlineMsg(), null);
//		}
//	}
//	
//	public String getInquery_id() {
//		return inquery_id;
//	}
//
//	public void setInquery_id(String inquery_id) {
//		this.inquery_id = inquery_id;
//	}
//
//	public String getUid() {
//		return uid;
//	}
//
//	public void setUid(String uid) {
//		this.uid = uid;
//	}
//
//	public String getSend_type() {
//		return send_type;
//	}
//
//	public void setSend_type(String send_type) {
//		this.send_type = send_type;
//	}
//
//	@Override
//	public String toString() {
//		return "SendMsgReq [uid=" + uid + ", send_type=" + send_type
//				+ ", inquery_id=" + inquery_id + "]";
//	}
//}
