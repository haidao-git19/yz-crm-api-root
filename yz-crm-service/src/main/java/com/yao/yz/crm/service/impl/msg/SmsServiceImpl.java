/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-5:07:27 PM
* @version 1.0
*/
package com.yao.yz.crm.service.impl.msg;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.baseinfo.interfaces.SmsManager;
import com.yao.baseinfo.interfaces.output.RenderedContent;
import com.yao.yz.admin.yzadmin.persistence.dao.YzSmsLogDao;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmSmsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzSms;
import com.yao.yz.admin.yzadmin.persistence.model.YzSmsLog;
import com.yao.yz.crm.service.interf.msg.SmsService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.util.ServiceUtil;
import com.yao.yz.crm.service.vo.req.sms.SmsParameter;
import com.yao.yz.crm.service.vo.res.sms.SmsResponse;

/**
 * 公司名: 壹药网
 * 类名称：SmsServiceImpl
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-5:07:27 PM
 */
@Service
public class SmsServiceImpl implements SmsService {

	private static final Logger logger = Logger.getLogger(SmsServiceImpl.class);
	
	@Autowired
	private CrmSmsDaoExt dao;
	
	@Autowired
	private YzSmsLogDao smslogDao;
	
	
	@Autowired
	SmsManager smsManager;
	
	@Override
	public List<YzSms> getSmsList() {
		return dao.getSmsList();
	}

	@Override
	public SmsResponse send(SmsParameter sp) {
		SmsResponse response=new SmsResponse();
		YzSmsLog smsLog;
		
		if(sp.getMode()==null){
			logger.info("短信类型为空!");
			response.setFlag(false);
			response.setMsg("短信类型为空!");
			return response;
		}
		if(sp.getMobile()==null){
			logger.info("手机号为空!");
			response.setFlag(false);
			response.setMsg("手机号为空!");
			return response;
		}
		
		if(sp.getMode().equalsIgnoreCase(ServiceContant.SMS_NOT_ACCEPT)){
			String content=getPropertyValue("SMS_NOT_ACCEPT");
			doSend(sp.getMobile(),content);
			
			smsLog=new YzSmsLog();
			smsLog.setSmsType(ServiceContant.SMS_NOT_ACCEPT);
			smsLog.setSmsContent(content);
			smsLog.setMobile(sp.getMobile());
			smsLog.setBookTime(new Date());
			smsLog.setSendTime(new Date());
			smsLog.setStatus("Y");
			smslogDao.insert(smsLog);
			
			response.setFlag(true);
			response.setMsg("发送成功!");
			return response;
		}else if(sp.getMode().equalsIgnoreCase(ServiceContant.SMS_TIME_OUT)){
			String content=getPropertyValue("SMS_TIME_OUT");
			doSend(sp.getMobile(),content);
			
			smsLog=new YzSmsLog();
			smsLog.setSmsType(ServiceContant.SMS_TIME_OUT);
			smsLog.setSmsContent(content);
			smsLog.setMobile(sp.getMobile());
			smsLog.setBookTime(new Date());
			smsLog.setSendTime(new Date());
			smsLog.setStatus("Y");
			smslogDao.insert(smsLog);
			
			response.setFlag(true);
			response.setMsg("发送成功!");
			return response;
		}else if(sp.getMode().equalsIgnoreCase(ServiceContant.SMS_BOOK_BACK)){
			if(sp.getBook_date()==null){
				logger.info("预约日期为空!");
				response.setFlag(false);
				response.setMsg("预约日期为空!");
				return response;
			}
			
			String book_start=ServiceUtil.intToTime(sp.getBook_start());
			
			String date=(new SimpleDateFormat("yyyyMMdd")).format(sp.getBook_date());
			String month=date.substring(4, 6);
			String day=date.substring(6, 8);
			
			int book_start_hour=Integer.parseInt(book_start.substring(0, 2));
			String half=book_start_hour<12?"上午":"下午";
			String content="亲~我们的医生将于"+month+"月"+day+"日"+half+"对您进行回访，请保持手机畅通哟~";
			
			doSend(sp.getMobile(),content);
			
			YzSms sms=new YzSms();
			sms.setSmsType(ServiceContant.SMS_BOOK_BACK);
			sms.setStatus("N");
			
			try {
				sms.setBookTime((new SimpleDateFormat("yyyyMMddHH:mm:ss")).parse(date+book_start+":00"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sms.setMobile(sp.getMobile());
			String book_end=ServiceUtil.intToTime(sp.getBook_end());
			content="亲，我们的医生将于今天"+half+" "+book_start+"~"+book_end+"给您来电，请保持手机畅通，别错过与医生的约会哟~";
			sms.setSmsContent(content);
			dao.insert(sms);
			
			response.setFlag(true);
			response.setMsg("发送成功!");
			return response;
		}else if(sp.getMode().equalsIgnoreCase(ServiceContant.SMS_EXCEPTION)){
			String content=getPropertyValue("SMS_EXCEPTION");
			doSend(sp.getMobile(),content);
			
			smsLog=new YzSmsLog();
			smsLog.setSmsType(ServiceContant.SMS_EXCEPTION);
			smsLog.setSmsContent(content);
			smsLog.setMobile(sp.getMobile());
			smsLog.setBookTime(new Date());
			smsLog.setSendTime(new Date());
			smsLog.setStatus("Y");
			smslogDao.insert(smsLog);
			
			response.setFlag(true);
			response.setMsg("发送成功!");
			return response;
		}else if(sp.getMode().equalsIgnoreCase(ServiceContant.SMS_DOCTOR_BUSY)){
			String content=getPropertyValue("SMS_DOCTOR_BUSY");
			doSend(sp.getMobile(),content);
			
			smsLog=new YzSmsLog();
			smsLog.setSmsType(ServiceContant.SMS_DOCTOR_BUSY);
			smsLog.setSmsContent(content);
			smsLog.setMobile(sp.getMobile());
			smsLog.setBookTime(new Date());
			smsLog.setSendTime(new Date());
			smsLog.setStatus("Y");
			smslogDao.insert(smsLog);
			
			response.setFlag(true);
			response.setMsg("发送成功!");
			return response;
		}else if(sp.getMode().equalsIgnoreCase(ServiceContant.SMS_CANCEL_INQUERY)){
			String content=getPropertyValue("SMS_CANCEL_INQUERY");
			doSend(sp.getMobile(),content);
			
			smsLog=new YzSmsLog();
			smsLog.setSmsType(ServiceContant.SMS_CANCEL_INQUERY);
			smsLog.setSmsContent(content);
			smsLog.setMobile(sp.getMobile());
			smsLog.setBookTime(new Date());
			smsLog.setSendTime(new Date());
			smsLog.setStatus("Y");
			smslogDao.insert(smsLog);
			
			response.setFlag(true);
			response.setMsg("发送成功!");
			return response;
		}else{
			response.setFlag(false);
			response.setMsg("发送失败!");
			return response;
		}
		
	}

	@Override
	public String doSend(String mobile,String content){
		try{
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("content", "\r\n"+content+"\r\n");
			RenderedContent renderedContent = smsManager.generateContent(
					"yz_sms_msg2", params);
			smsManager.sendOneSms(mobile, renderedContent.getContent(),
					"yz_sms_msg2");
			
			return "ok";
		}catch(Exception e){
			e.printStackTrace();
			return "fail"; 
		}
    }

	@Override
	public int finish(YzSms sms) {
		
		return dao.finish(sms);
	}

	@Override
	public int deleteSms(int id) {
		return dao.delete(id);
	}

	@Override
	public int insertSmsLog(YzSmsLog log) {
		return smslogDao.insert(log);
	}
	
	 private static final ResourceBundle bundle = ResourceBundle.getBundle("dubbo");
	    public static String getPropertyValue(String key) {
			try {
				return bundle.getString(key);
			} catch (Exception e) {
				logger.error("未配置错误代码" + key, e);
				return "未配置错误代码";
			}
		}
}
