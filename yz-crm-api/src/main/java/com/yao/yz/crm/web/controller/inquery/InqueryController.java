///**
//* <p>Copyright: Copyright (c) 2015<／p>
//* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
//* @author wangyulong
//* @date Nov 5, 2015-11:33:54 AM
//* @version 1.0
//*/
//package com.yao.yz.crm.web.controller.inquery;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.ibatis.session.RowBounds;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.dubbo.common.utils.StringUtils;
//import com.alibaba.fastjson.JSONObject;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
//import com.yao.yz.crm.service.interf.inquery.InqueryQueryService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.inquery.InqueryListReq;
//import com.yao.yz.crm.service.vo.res.Inquery;
//import com.yao.yz.crm.web.controller.BaseController;
//import com.yao.yz.crm.web.util.PageUtils;
//import com.yao.yz.crm.web.util.Pagination;
//
///**
// * 公司名: 壹药网
// * 类名称：InqueryController
// * 类描述：
// * @author wangyulong
// * @date Nov 5, 2015-11:33:54 AM
// */
//@Controller
//@RequestMapping("/inquery")
//public class InqueryController extends BaseController{
//	
//	private static final Logger logger = Logger.getLogger(InqueryController.class);
//	
//	@Autowired
//	private InqueryQueryService service;
//
//	/**
//  	 * 功能：来电清单列表显示
//  	 * @Author wangyulong
//  	 * @param 
//  	 * @return  List
//  	 */
//	@ResponseBody
//	@RequestMapping(value = "/list",method = {RequestMethod.POST,RequestMethod.GET})
//	public Object getInqueryList(){
//		
//		List<Inquery> list=service.getInqueryList();
//		JSONObject jsonObject = new JSONObject();
//		JSONObject jsonObject1 = new JSONObject();
//		jsonObject1.put("list", list);
//		jsonObject.put("ret", 1);
//		jsonObject.put("msg", "ok");
//		jsonObject.put("data", jsonObject1);
//		
//		return jsonObject;
//	}
//	
//	/**
//  	 * 功能：来电记录完成
//  	 * @Author wangyulong
//  	 * @param   int
//  	 * @return  int 
//  	 */
//	@ResponseBody
//	@RequestMapping(value = "/finish",method = {RequestMethod.POST,RequestMethod.GET})
//	public Object finish(HttpServletRequest request){
//		logger.info("getParameter id=="+request.getParameter("id"));
//		
//		JSONObject jsonObject = new JSONObject();
//		if(request.getParameter("id")==null){
//			
//			jsonObject.put("ret", 0);
//			jsonObject.put("msg", "未接收到id!");
//			logger.info("未接收到id!");
//			return jsonObject;
//		}else{
//			if(request.getParameter("id")==""){
//				jsonObject.put("ret", 0);
//				jsonObject.put("msg", "id为空!");
//				logger.info("id为空!");
//				return jsonObject;
//			}else{
//				if(!StringUtils.isNumeric(request.getParameter("id"))){
//					jsonObject.put("ret", 0);
//					jsonObject.put("msg", "id不为数字!");
//					logger.info("id不为数字!");
//					return jsonObject;
//				}
//			}
//		}
//		
//		service.finish(Integer.parseInt(request.getParameter("id")));
//		jsonObject.put("ret", 1);
//		jsonObject.put("msg", "ok");
//		
//		return jsonObject;
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/checkFinish",method = {RequestMethod.POST,RequestMethod.GET})
//	public Object checkFinish(HttpServletRequest request){
//		logger.info("getParameter id=="+request.getParameter("id"));
//		
//		JSONObject jsonObject = new JSONObject();
//		JSONObject jsonObject1 = new JSONObject();
//		if(request.getParameter("id")==null){
//			
//			jsonObject.put("ret", 0);
//			jsonObject.put("msg", "未接收到id!");
//			logger.info("未接收到id!");
//			return jsonObject;
//		}else{
//			if(request.getParameter("id")==""){
//				jsonObject.put("ret", 0);
//				jsonObject.put("msg", "id为空!");
//				logger.info("id为空!");
//				return jsonObject;
//			}else{
//				if(!StringUtils.isNumeric(request.getParameter("id"))){
//					jsonObject.put("ret", 0);
//					jsonObject.put("msg", "id不为数字!");
//					logger.info("id不为数字!");
//					return jsonObject;
//				}
//			}
//		}
//		
//		String result=service.checkFinish(Integer.parseInt(request.getParameter("id")));
//		
//		jsonObject.put("msg", result);
//		if(result==""){
//			jsonObject.put("finish", true);
//		}else{
//			jsonObject.put("finish", false);
//		}
//		jsonObject1.put("data", jsonObject);
//		jsonObject1.put("ret", 1);
//		return jsonObject1;
//	}
////	
////	@SuppressWarnings("unchecked")
////	@ResponseBody
////	@RequestMapping(value = "/getUndoList",method = {RequestMethod.POST,RequestMethod.GET})
////	public Object getUndoList(HttpServletRequest request,InqueryListReq inqueryListReq){
////		
////		try{
////			BasicResVo basicResVo = beforeExecute(inqueryListReq, request);
////			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
////				logger.warn("/inquery/getHistoryList token认证失败[" + inqueryListReq.toString() + "]");
////				return basicResVo;
////			}
////			JSONObject jsonObject = new JSONObject();
////			JSONObject jsonObject1 = new JSONObject();
////			jsonObject.put("ret", 1);
////			jsonObject.put("msg", "ok");
////			jsonObject.put("data", jsonObject1);
////			
////			Pagination<YzInquery> pagination = new Pagination<YzInquery>();
////	        int pageSize = 10;
////	        if(inqueryListReq.getPage_size()!=null&&inqueryListReq.getPage_size()!=""){
////	        	pageSize=Integer.parseInt(inqueryListReq.getPage_size());
////	        }
////	        int pageIndex =1;
////	        if(inqueryListReq.getPage_index()!=null&&inqueryListReq.getPage_index()!=""){
////	        	pageIndex=Integer.parseInt(inqueryListReq.getPage_index());
////	        }
////	        pageIndex = pagination.getPageIndex(pageIndex+"");// 第几页
////	        PageUtils pageUtils = new PageUtils(pageSize, pageIndex+"");
////	        
////	        Map<String, Object> input = new HashMap<String, Object>();
////	        input.put("mode", "M");
////	        input.put("mode1", "N");
////	        input.put("mode2", "D");
////	        input.put("page_index", inqueryListReq.getPage_index());
////	        input.put("department_id", inqueryListReq.getDepartment_id());
////	        input.put("inquery_flag", inqueryListReq.getInquery_flag());
////	        input.put("op_doctor_name", inqueryListReq.getOp_doctor_name());
////	        if(inqueryListReq.getCreate_time_begin()!=null&&inqueryListReq.getCreate_time_begin()!=""){
////	        	if(inqueryListReq.getCreate_time_begin().length()!=13){
////	        		return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "create_time_begin 时间戳数据格式不正确!", null);
////	        	}
////	        	Long long1=Long.parseLong(inqueryListReq.getCreate_time_begin());
////	            String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(long1);
////	            logger.info("create_time_begin: "+date);
////	            input.put("create_time_begin", date);
////	        }
////	        if(inqueryListReq.getCreate_time_end()!=null&&inqueryListReq.getCreate_time_end()!=""){
////	        	if(inqueryListReq.getCreate_time_end().length()!=13){
////	        		return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "create_time_end 时间戳数据格式不正确!", null);
////	        	}
////	        	Long long1=Long.parseLong(inqueryListReq.getCreate_time_end());
////	            String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(long1);
////	            logger.info("create_time_end: "+date);
////	            input.put("create_time_end", date);
////	        }
////	        input.put("uid", inqueryListReq.getUid());
////	        
////	        Map<String, Object> listMap = service.getPagerList(input,
////	            new RowBounds(pageUtils.getStartRow(), pageSize));
////
////	        pagination.setPageInfo((Integer) listMap.get("count"), pageSize, pageIndex);// 设置分页信息
////	        pagination.setList((List<YzInquery>) listMap.get("list"));
////			
////	        jsonObject1.put("pagination", pagination);
////			return new BasicResVo().processData(ServiceContant.RET_CODE_SUCCESS, "ok!", jsonObject1);
////		}catch(Exception e){
////			logger.error("/inquery/getUndoList 接口异常!", e);
////			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "/inquery/getUndoList 接口异常!", null);
////		}
////	}
////	
////	@SuppressWarnings("unchecked")
////	@ResponseBody
////	@RequestMapping(value = "/getHistoryList",method = {RequestMethod.POST,RequestMethod.GET})
////	public BasicResVo getHistoryList(HttpServletRequest request,InqueryListReq inqueryListReq){
////		
////		try{
////			BasicResVo basicResVo = beforeExecute(inqueryListReq, request);
////			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
////				logger.warn("/inquery/getHistoryList token认证失败[" + inqueryListReq.toString() + "]");
////				return basicResVo;
////			}
////			JSONObject jsonObject = new JSONObject();
////			JSONObject jsonObject1 = new JSONObject();
////			jsonObject.put("ret", 1);
////			jsonObject.put("msg", "ok");
////			jsonObject.put("data", jsonObject1);
////			
////			Pagination<YzInquery> pagination = new Pagination<YzInquery>();
////	        int pageSize = 10;
////	        if(inqueryListReq.getPage_size()!=null&&inqueryListReq.getPage_size()!=""){
////	        	pageSize=Integer.parseInt(inqueryListReq.getPage_size());
////	        }
////	        int pageIndex =1;
////	        if(inqueryListReq.getPage_index()!=null&&inqueryListReq.getPage_index()!=""){
////	        	pageIndex=Integer.parseInt(inqueryListReq.getPage_index());
////	        }
////	        pageIndex = pagination.getPageIndex(pageIndex+"");// 第几页
////	        PageUtils pageUtils = new PageUtils(pageSize, pageIndex+"");
////	        
////	        Map<String, Object> input = new HashMap<String, Object>();
////	        input.put("mode1", "Y");
////	        input.put("mode2", "C");
////	        input.put("page_index", inqueryListReq.getPage_index());
////	        input.put("department_id", inqueryListReq.getDepartment_id());
////	        input.put("inquery_flag", inqueryListReq.getInquery_flag());
////	        input.put("op_doctor_name", inqueryListReq.getOp_doctor_name());
////	        if(inqueryListReq.getUpdate_time_begin()!=null&&inqueryListReq.getUpdate_time_begin()!=""){
////	        	if(inqueryListReq.getUpdate_time_begin().length()!=13){
////	        		return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "update_time_begin 时间戳数据格式不正确!", null);
////	        	}
////	        	Long long1=Long.parseLong(inqueryListReq.getUpdate_time_begin());
////	            String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(long1);
////	            logger.info("update_time_begin: "+date);
////	            input.put("update_time_begin", date);
////	        }
////	        if(inqueryListReq.getUpdate_time_end()!=null&&inqueryListReq.getUpdate_time_end()!=""){
////	        	if(inqueryListReq.getUpdate_time_end().length()!=13){
////	        		return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "update_time_end 时间戳数据格式不正确!", null);
////	        	}
////	        	Long long1=Long.parseLong(inqueryListReq.getUpdate_time_end());
////	            String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(long1);
////	            logger.info("update_time_end: "+date);
////	            input.put("update_time_end", date);
////	        }
////	        input.put("uid", inqueryListReq.getUid());
////	        input.put("archive_flag", inqueryListReq.getArchive_flag());
////	        
////	        Map<String, Object> listMap = service.getPagerList(input,
////	            new RowBounds(pageUtils.getStartRow(), pageSize));
////
////	        pagination.setPageInfo((Integer) listMap.get("count"), pageSize, pageIndex);// 设置分页信息
////	        pagination.setList((List<YzInquery>) listMap.get("list"));
////			
////	        jsonObject1.put("pagination", pagination);
////			return new BasicResVo().processData(ServiceContant.RET_CODE_SUCCESS, "ok!", jsonObject1);
////		}catch(Exception e){
////			logger.error("/inquery/getHistoryList接口异常!", e);
////			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "/inquery/getHistoryList接口异常!", null);
////		}
////	}
//	
////	@ResponseBody
////	@RequestMapping(value = "/count",method = {RequestMethod.POST,RequestMethod.GET})
////	public Object getUndoListCount(HttpServletRequest request){
////		
////		
////		JSONObject jsonObject = new JSONObject();
////		JSONObject jsonObject1 = new JSONObject();
////		jsonObject.put("ret", 1);
////		jsonObject.put("msg", "ok");
////		jsonObject.put("data", jsonObject1);
////		
////		String start_time=null;
////		Long timeStamp=0L;
////		if(request.getParameter("start_time")!=null&&request.getParameter("start_time")!=""){
////        	if(request.getParameter("start_time").length()!=13){
////        		return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "start_time 时间戳数据格式不正确!", null);
////        	}
////        	timeStamp=Long.parseLong(request.getParameter("start_time"));
////        	start_time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp);
////            //logger.info("start_time: "+start_time);
////        }else{
////        	return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "start_time 为空!", null);
////        }
////		String end_time=null;
////		if(request.getParameter("end_time")!=null&&request.getParameter("end_time")!=""){
////        	if(request.getParameter("end_time").length()!=13){
////        		return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "end_time 时间戳数据格式不正确!", null);
////        	}
////        	timeStamp=Long.parseLong(request.getParameter("end_time"));
////        	end_time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timeStamp);
////            //logger.info("end_time: "+end_time);
////        }else{
////        	return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "end_time 为空!", null);
////        }
////        
////        int count=service.getUndoCount(start_time, end_time);
////		
////        jsonObject1.put("count", count);
////		return jsonObject;
////	}
//	
//}
