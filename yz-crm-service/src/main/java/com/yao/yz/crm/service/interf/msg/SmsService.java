/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-4:41:25 PM
* @version 1.0
*/
package com.yao.yz.crm.service.interf.msg;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.model.YzSms;
import com.yao.yz.admin.yzadmin.persistence.model.YzSmsLog;
import com.yao.yz.crm.service.vo.req.sms.SmsParameter;
import com.yao.yz.crm.service.vo.res.sms.SmsResponse;

/**
 * 公司名: 壹药网
 * 类名称：SmsService
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-4:41:25 PM
 */
public interface SmsService {

	List<YzSms> getSmsList();
	
	int finish(YzSms sms);
	
	int deleteSms(int id);
	
	int insertSmsLog(YzSmsLog log);
	
	SmsResponse send(SmsParameter sp);
	String doSend(String mobile,String content);
}
