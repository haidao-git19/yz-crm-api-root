///**
//* <p>Copyright: Copyright (c) 2015<／p>
//* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
//* @author wangyulong
//* @date Nov 4, 2015-9:18:32 AM
//* @version 1.0
//*/
//package com.yao.yz.crm.web.controller.sendMsg;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
///*import java.util.ResourceBundle;*/
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yao.yz.crm.service.interf.msg.SmsService;
//import com.yao.yz.crm.service.vo.req.sms.SmsParameter;
//import com.yao.yz.crm.service.vo.res.sms.SmsResponse;
//
///**
// * 公司名: 壹药网
// * 类名称：SmsController
// * 类描述：
// * @author wangyulong
// * @date Nov 4, 2015-9:18:32 AM
// */
//@Controller
//@RequestMapping("/sms")
//public class SmsController {
//	
//	private static final Logger logger = Logger.getLogger(SmsController.class);
//	
//	/*private static ResourceBundle bundle = ResourceBundle.getBundle("dubbo");*/
//
//	@Autowired
//	SmsService smsService;
//	
//	@ResponseBody
//	@RequestMapping(value = "/send", method = {RequestMethod.GET,RequestMethod.POST})
//	public Object  send(HttpServletRequest request){
//		
//		logger.info("短信接口接收到短信发送请求,准备处理...");
//		logger.info("URI: "+request.getRequestURI());
//		logger.info("mode: "+request.getParameter("mode"));
//		logger.info("mobile: "+request.getParameter("mobile"));
//		logger.info("content: "+request.getParameter("content"));
//		logger.info("token: "+request.getParameter("token"));
//		
//		
//		JSONObject jsonObject = new JSONObject();
//		try{
//			if(request.getParameter("mode")!=null&&request.getParameter("mode").equalsIgnoreCase("SMS_REMINDER")){
//				/*String token=request.getParameter("token");*/
//				
//				if(request.getParameter("mobile")!=null&&request.getParameter("content")!=null){
//					//String realToken=Md5Utils.md5Encrypt(request.getParameter("mode")+request.getParameter("mobile")+request.getParameter("content")+bundle.getString("md5_key"));
//					//if(token.equals(realToken)){
//						logger.info("token验证成功!");
//						smsService.doSend(request.getParameter("mobile"), request.getParameter("content"));
//						jsonObject.put("msg", "短信发送成功!");
//						logger.info("短信发送成功!");
//				        return jsonObject;
////					}else{
////						jsonObject.put("msg", "token验证失败!");
////						logger.info("token验证失败!");
////				        return jsonObject;
////					}
//				}else{
//					jsonObject.put("msg", "mobile 或 content 为空!");
//					logger.info("mobile 或 content 为空!");
//			        return jsonObject;
//				}
//			}
//			
//			SmsParameter sp=new SmsParameter();
//			
//			sp.setMode(request.getParameter("mode"));
//			Date date=null;
//			if(request.getParameter("book_date")!=null){
//				date=(new SimpleDateFormat("yyyyMMdd")).parse(request.getParameter("book_date"));
//			}
//			sp.setBook_date(date);
//			sp.setBook_start(request.getParameter("book_start")==null?0:Integer.parseInt(request.getParameter("book_start")));
//			sp.setBook_end(request.getParameter("book_end")==null?0:Integer.parseInt(request.getParameter("book_end")));
//			sp.setMobile(request.getParameter("mobile"));
//			
//			SmsResponse smsResponse=smsService.send(sp);
//			 
//			jsonObject.put("ret", 1);
//			jsonObject.put("msg", smsResponse);
//			
//			//logger.info("短信发送成功");
//	        return smsResponse;
//		}catch(Exception e){
//			jsonObject.put("ret", 0);
//			jsonObject.put("msg", "短信发送失败!");
//			logger.error("短信发送失败", e);
//	        return jsonObject;
//		}
//	}
//}
