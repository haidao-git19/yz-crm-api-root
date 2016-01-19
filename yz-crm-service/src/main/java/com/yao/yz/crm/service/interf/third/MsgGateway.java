package com.yao.yz.crm.service.interf.third;

import java.util.Date;

import com.yao.yz.crm.service.vo.res.sms.SmsResponse;

/**
 *	描述：预约短信发送网关
 *	@Author wuwenjun
 *	@Date Nov 6, 2015 10:02:19 AM
 *	@Versin 1.0
 */
public interface MsgGateway {
	
	/**
	 * 短信下发
	 * @param sendType 发送类型
	 * @param mobile 手机号码
	 * @param bookDate 预约日期
	 * @param bookStart 预约开始时间
	 * @param bookEnd 预约结束时间
	 * @return
	 */
	SmsResponse sendMsg(String sendType, String mobile,Date bookDate,Integer bookStart,Integer bookEnd);
	
	/**
	 * 短信下发
	 * @version 2.0 新增
	 * @param sendType
	 * @param mobile
	 * @return
	 */
	SmsResponse sendMsg(String sendType,String mobile);
	
}
