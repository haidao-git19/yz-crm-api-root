/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 10, 2015-10:23:25 AM
* @version 1.0
*/
package com.yao.yz.crm.web.controller.callStatistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yao.yz.admin.yzadmin.persistence.model.YzCallStatistics;
import com.yao.yz.crm.service.interf.callStatistic.CallStatisticService;
import com.yao.yz.crm.service.interf.inquery.InqueryQueryService;

/**
 * 公司名: 壹药网
 * 类名称：CallStatisticController
 * 类描述：
 * @author wangyulong
 * @date Nov 10, 2015-10:23:25 AM
 */
@Controller
@RequestMapping("/callStatistic")
public class CallStatisticController {
	
	private static final Logger logger = Logger.getLogger(CallStatisticController.class);

	@Autowired
	CallStatisticService service;
	
	@Autowired
	InqueryQueryService inqueryService;
	
	@ResponseBody
	@RequestMapping(value = "insert", method = {RequestMethod.GET,RequestMethod.POST})
	public Object insert(HttpServletRequest request){
		logger.info("统计插入动作:");
		
		YzCallStatistics cs=new YzCallStatistics();
		if(request.getParameter("uid")!=null){
			logger.info("uid 接收到 "+ request.getParameter("uid"));
			cs.setUid(Integer.parseInt(request.getParameter("uid")));
		}
		if(request.getParameter("username")!=null){
			logger.info("username 接收到 "+request.getParameter("username"));
			cs.setUsername(request.getParameter("username"));
		}
		if(request.getParameter("mobile")!=null){
			logger.info("mobile 接收到 "+request.getParameter("mobile"));
			cs.setMobile(request.getParameter("mobile"));
		}
		if(request.getParameter("seat")!=null){
			logger.info("seat 接收到 "+request.getParameter("seat"));
			cs.setSeat(request.getParameter("seat"));
		}
		if(request.getParameter("transfer_seat")!=null){
			logger.info("transfer_seat 接收到 "+request.getParameter("transfer_seat"));
			cs.setSeat(request.getParameter("transfer_seat"));
		}
		if(request.getParameter("inquery_id")!=null){
			logger.info("inquery_id 接收到 "+request.getParameter("inquery_id"));
			cs.setInqueryId(Integer.parseInt(request.getParameter("inquery_id")));
		}
		if(request.getParameter("inquery_time")!=null){
			logger.info("inquery_time 接收到 "+request.getParameter("inquery_time"));
			Long time_stamp=Long.parseLong(request.getParameter("inquery_time"));
	    	String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time_stamp);
	    	Date inqueryTime=new Date();
	        try {
	        	inqueryTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	        cs.setInqueryTime(inqueryTime);
		}
		cs.setCallbackTime(new Date());
		if(request.getParameter("doctor_id")!=null){
			logger.info("doctor_id 接收到 "+request.getParameter("doctor_id"));
			cs.setDoctorId(Integer.parseInt(request.getParameter("doctor_id")));
		}
		
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();
		try{
			if(service.insertCallStatistic(cs)==1){
				jsonObject.put("ret", 1);
		        jsonObject.put("msg", "统计插入成功!");
		        jsonObject1.put("call_id", cs.getId());
		        jsonObject.put("data", jsonObject1);
		        logger.info("统计插入成功!");
			}
		}catch(Exception e){
			jsonObject.put("ret", 0);
			jsonObject.put("msg", "统计插入失败!");
			jsonObject1.put("call_id", "");
	        jsonObject.put("data", jsonObject1);
			logger.error("统计插入失败!", e);
		}
		
        return jsonObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public Object update(HttpServletRequest request){
		
		YzCallStatistics cs=new YzCallStatistics();
		JSONObject jsonObject = new JSONObject();
		
		if(request.getParameter("mode")!=null&&request.getParameter("call_id")!=null){
			logger.info("mode 接收到 "+request.getParameter("mode"));
			logger.info("call_id 接收到 "+request.getParameter("call_id"));
			
			cs.setId(Integer.parseInt(request.getParameter("call_id")));
			
			if(request.getParameter("mode").equalsIgnoreCase("insert_start_time")){
				cs.setStartCallbackTime(new Date());
			}
			if(request.getParameter("mode").equalsIgnoreCase("insert_end_time")){
				cs.setEndCallbackTime(new Date());
			}
			
			try{
				if(service.updateCallStatistic(cs)==1){
					jsonObject.put("ret", 1);
			        jsonObject.put("msg", "统计修改成功!");
			        logger.info("统计修改成功!");
			        if(request.getParameter("mode").equalsIgnoreCase("insert_start_time")){
			        	YzCallStatistics callStatistic=service.getCallStatistic(cs.getId());
			        	if(callStatistic.getInqueryId()!=null){
				        	if(inqueryService.updateProcessFlag(callStatistic.getInqueryId())==1){
					        	logger.info("process_flag修改成功!");
					        }else{
					        	logger.info("process_flag修改失败!");
					        }
			        	}
			        }
			        if(request.getParameter("mode").equalsIgnoreCase("insert_end_time")){
			        	YzCallStatistics callStatistic=service.getCallStatistic(cs.getId());
			        	int call_duration=0;
			        	if(callStatistic!=null){
			        		if(callStatistic.getStartCallbackTime()!=null&&callStatistic.getEndCallbackTime()!=null){
			        			call_duration=inqueryService.getCallDuration(cs.getId());
					        	logger.info("call_duration: "+call_duration);
			        		}
			        	}
			        	if(callStatistic.getInqueryId()!=null){
				        	if(inqueryService.insertCallDuration(call_duration,callStatistic.getInqueryId())==1){
					        	logger.info("call_duration插入成功!");
					        }else{
					        	logger.info("call_duration插入失败!");
					        }
			        	}
			        }
				}else{
					jsonObject.put("ret", 0);
					jsonObject.put("msg", "没有数据需要修改!");
			        logger.info("没有数据需要修改!");
				}
			}catch(Exception e){
				jsonObject.put("msg", "统计修改失败!");
				logger.error("统计修改失败!", e);
			}
		}else{
			if(request.getParameter("call_id")==null){
				jsonObject.put("ret", 0);
				jsonObject.put("msg", "call_id为空!");
		        logger.info("call_id为空!");
			}
			if(request.getParameter("mode")==null){
				jsonObject.put("ret", 0);
				jsonObject.put("msg", "mode为空!");
		        logger.info("mode为空!");
			}
		}
		
        return jsonObject;
	}
}
