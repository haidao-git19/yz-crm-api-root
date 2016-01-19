/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Oct 30, 2015-1:31:09 PM
* @version 1.0
*/
package com.yao.yz.crm;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.baseinfo.interfaces.SmsManager;
import com.yao.baseinfo.interfaces.output.RenderedContent;

/**
 * 公司名: 壹药网
 * 类名称：AvayaServiceTest
 * 类描述：
 * @author wangyulong
 * @date Oct 30, 2015-1:31:09 PM
 */
public class SmsServiceTest extends AbstractTest{


	@Autowired
	SmsManager smsManager;
	
	@Test
	public void testSms() {                                   
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("content", "\r\n唉!我还在加班, 苦逼呀!!\r\n");
		RenderedContent content = smsManager.generateContent(
				"yz_sms_msg", params);
		smsManager.sendOneSms("13918407457", content.getContent(),
				"yz_sms_msg");
		
	}
}
