/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 4, 2015-2:54:25 PM
* @version 1.0
*/
package com.yao.yz.crm.web.controller.sendMsg;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.model.YzSms;
import com.yao.yz.admin.yzadmin.persistence.model.YzSmsLog;
import com.yao.yz.crm.service.interf.msg.SmsService;

/**
 * 公司名: 壹药网
 * 类名称：SmsTimer
 * 类描述：
 * @author wangyulong
 * @date Nov 4, 2015-2:54:25 PM
 */
public class SmsTimer {

	private static final Logger logger = Logger.getLogger(SmsTimer.class);
	
	
	@Autowired
	SmsService smsService;
	
    public void job1() {  

    	System.out.println("job1========"+new Date());
    	
    	try{
    		List<YzSms> list=smsService.getSmsList();
    		YzSmsLog log;
    		for(YzSms sms:list){
    			if(dateDiff(sms.getBookTime())<=Long.parseLong(getPropertyValue("sms.remind.time"))){
    				smsService.doSend(sms.getMobile(), sms.getSmsContent());
    				
    				log=new YzSmsLog();
    				log.setMobile(sms.getMobile());
    				log.setSmsContent(sms.getSmsContent());
    				log.setSmsType(sms.getSmsType());
    				log.setBookTime(sms.getBookTime());
    				log.setStatus("Y");
    				log.setSendTime(new Date());
    				
    				smsService.insertSmsLog(log);
    				
    				smsService.deleteSms(sms.getId());
    			}
    		}
			//logger.info("短信发送成功");
		}catch(Exception e){
			logger.error("短信发送失败", e);
		}
    	
    }
    
    
    private long dateDiff(Date time) {   
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数   
        long nh = 1000 * 60 * 60;// 一小时的毫秒数   
        long nm = 1000 * 60;// 一分钟的毫秒数   
        long ns = 1000;// 一秒钟的毫秒数   
        long diff;   
        long day = 0;   
        long hour = 0;   
        long min = 0;   
        long sec = 0;   
        // 获得两个时间的毫秒时间差异   
        try {   
        	Date date=new Date();
    		
            diff = date.getTime() - time.getTime();   
            day = diff / nd;// 计算差多少天   
            hour = diff % nd / nh + day * 24;// 计算差多少小时   
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟   
            sec = diff % nd % nh % nm / ns;// 计算差多少秒   
            // 输出结果   
            System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"  
                    + (min - day * 24 * 60) + "分钟" + sec + "秒。");   
  
            return min;
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return min;
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
