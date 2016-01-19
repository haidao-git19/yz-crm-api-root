package com.yao.yz.crm.service.interf.msg;

import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.sms.ListMsgReq;
import com.yao.yz.model.v3.biz.sms.SendMsgReq;

/**
 *	描述：短信服务接口
 *	@Author wuwenjun
 *	@Date Nov 6, 2015 2:13:56 PM
 *	@Versin 1.0
 */
public interface MsgService {

	/**
	 * 发送短信
	 * @param sendMsgReq 短信发送请求
	 * @param resModel 返回数据模型
	 */
	void sendMsg(SendMsgReq sendMsgReq,AbstractResModel resModel);
	
	/**
	 * 根据诊单查询短信发送记录
	 * @param listMsgReq 短信发送记录请求
	 * @param resModel 返回数据模型
	 */
	void listMsg(ListMsgReq listMsgReq,AbstractResModel resModel);
}
