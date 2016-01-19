package com.yao.yz.crm.web.v3.controller;

//import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;

//import com.yao.yz.crm.service.interf.msg.SendMsgService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.msg.SendMsgReq;
//import com.yao.yz.crm.web.controller.BaseController;
import com.yao.yz.crm.service.interf.msg.MsgService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.model.v3.base.res.ResModel;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.sms.ListMsgReq;
import com.yao.yz.model.v3.biz.sms.SendMsgReq;
//import com.yzo.yz.crm.open.interior.sms.SmsApi;

/**
 *	描述：医生下发客户短信
 *	@Author wuwenjun
 *	@Date Nov 6, 2015 2:50:44 PM
 *	@Versin 1.0
 */
@Controller
@RequestMapping("/msg")
public class MsgController extends AbstractController{
	
	private static final Logger logger = Logger.getLogger(MsgController.class);
	
	@Autowired
	private MsgService msgService;
	
	@ResponseBody
	@RequestMapping("/send")
	public ResModel sendMsg(HttpServletRequest request,SendMsgReq sendMsgReq,ResModelProxy resModel) {
		
		logger.info("【短信发送接口】服务端接收到短信发送请求...");
		
		try {
			// token认证
			if (!authric(MsgController.TOKEN_AHTHRIC, sendMsgReq, resModel, request)){
				return resModel;
			}
			
			// 执行短信发送
			msgService.sendMsg(sendMsgReq, resModel);
		} catch (Exception e) {
			logger.error("【短信发送接口】短信发送异常", e);
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
		}
		return resModel;
	}
	
	@ResponseBody
	@RequestMapping(value = "/inquerylist",method = {RequestMethod.POST})
	public ResModel queryMsg(HttpServletRequest request,ListMsgReq listMsgReq,ResModelProxy resModelProxy) {
		
		logger.info("【短信发送记录查询接口】服务端接收到短信发送记录查询请求...");
		
		try {
			// token认证
			if (!authric(TOKEN_AHTHRIC, listMsgReq, resModelProxy, request)) {
				return resModelProxy;
			}
			
			// 查询短信发送记录
			msgService.listMsg(listMsgReq, resModelProxy);
		} catch (Exception e) {
			logger.error("【短信发送记录查询接口】服务端处理短信发送记录查询请求异常", e);
			resModelProxy.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
		}
		return resModelProxy;
	}

//	@Autowired
//	private SendMsgService sendMsgService;
//	
//	/**
//	 * 医生下发短信
//	 * @param sendMsgReq
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/send",method = {RequestMethod.POST})
//	public Object sendMsg(SendMsgReq sendMsgReq,HttpServletRequest request) {
//		logger.info("【医生下发短信】服务端接收到短信下发请求，准备处理...");
//		try {
//			// token校验
//			BasicResVo basicResVo = beforeExecute(sendMsgReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				return basicResVo;
//			}
//			
//			// 发送短信
//			return sendMsgService.sendMsg(sendMsgReq);
//		} catch (Exception e) {
//			logger.error("【医生下发短信】接口异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
//		}
//	}
}
