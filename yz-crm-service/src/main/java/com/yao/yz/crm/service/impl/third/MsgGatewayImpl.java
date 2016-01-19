package com.yao.yz.crm.service.impl.third;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yao.yz.crm.service.interf.msg.SmsService;
import com.yao.yz.crm.service.interf.third.MsgGateway;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.req.sms.SmsParameter;
import com.yao.yz.crm.service.vo.res.sms.SmsResponse;

/**
 *	描述：短信网关
 *	@Author wuwenjun
 *	@Date Nov 6, 2015 12:29:33 PM
 *	@Versin 1.0
 */
@Component
public class MsgGatewayImpl implements MsgGateway{
	
	private static final Logger logger = Logger.getLogger(MsgGatewayImpl.class);
	
	@Autowired
	private SmsService smsService;

	@Override
	public SmsResponse sendMsg(String sendType, String mobile,
			Date bookDate, Integer bookStart, Integer bookEnd) {
		SmsParameter smsParameter = new SmsParameter();
		if (!ServiceContant.SMS_REMINDER.equals(sendType)) {
			smsParameter.setMode(sendType);
			smsParameter.setMobile(mobile);
			smsParameter.setBook_date(bookDate);
			smsParameter.setBook_start(bookStart);
			smsParameter.setBook_end(bookEnd);
		} else {
			smsParameter.setMode(sendType);
			smsParameter.setMobile(mobile);
		}
		SmsResponse smsResponse = smsService.send(smsParameter);
		if (smsResponse.isFlag()) {
			logger.info("【短信下发网关】调用短信发送接口发送短信成功,下发数据：" + smsParameter.toString());
			return smsResponse;
		} else {
			logger.warn("【短信下发网关】调用短信发送接口发送短信失败,下发数据：" + smsParameter.toString());
			return smsResponse;
		}
	}

	@Override
	public SmsResponse sendMsg(String sendType, String mobile) {
		
		// 拼接短信发送请求参数
		SmsParameter smsParameter = new SmsParameter();
		smsParameter.setMode(sendType);
		smsParameter.setMobile(mobile);
		
		logger.info("【短信下发网关】拼装短信内容成功，准备发送...");
		
		// 发送短信
		SmsResponse smsResponse = smsService.send(smsParameter);
		if (smsResponse.isFlag()) {
			logger.info("【短信下发网关】调用短信发送接口发送短信成功,下发数据：" + smsParameter.toString());
		} else {
			logger.warn("【短信下发网关】调用短信发送接口发送短信失败,下发数据：" + smsParameter.toString());
		}
		return smsResponse;
	}
}
