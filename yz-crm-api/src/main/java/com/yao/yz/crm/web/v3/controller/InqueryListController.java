/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 30, 2015-5:37:52 PM
* @version 1.0
*/
package com.yao.yz.crm.web.v3.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.crm.service.interf.inquery.InqueryQueryService;
import com.yao.yz.crm.service.vo.res.Inquery2;
import com.yao.yz.crm.web.util.PageUtils;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.drug.PageBean;
import com.yao.yz.model.v3.biz.inquery.AllListReq;
import com.yao.yz.model.v3.biz.inquery.AllListVo;
import com.yao.yz.model.v3.biz.inquery.HistoryListReq;
import com.yao.yz.model.v3.biz.inquery.UndoListReq;
import com.yao.yz.model.v3.biz.inquery.UndoListRes;

/**
 * 公司名: 壹药网
 * 类名称：InqueryListController
 * 类描述：
 * @author wangyulong
 * @date Dec 30, 2015-5:37:52 PM
 */
@Controller
@RequestMapping("/inquery")
public class InqueryListController extends AbstractController{

    private static final Logger logger = Logger.getLogger(InqueryListController.class);
    
    private static final Logger frequencyLogger = Logger.getLogger("frequency");
	
	private static final String loggerName = "【InqueryListController】";
	
	@Autowired
	private InqueryQueryService service;
	
	@ResponseBody
	@RequestMapping(value = "/v2/getUndoList",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getUndoList(HttpServletRequest request,UndoListReq undoListReq){
		logger.info(loggerName + "获取待处理诊单请求开始...");
		logger.info(JSON.toJSONString(undoListReq));
		ResModelProxy resModel = new ResModelProxy();
		try {
			// token认证
			if (!authric(InqueryListController.TOKEN_AHTHRIC, undoListReq, resModel, request)) {
				return resModel;
			}
			
			// 参数检查
			if (!undoListReq.validate(resModel)){
				return resModel;
			}
			List<Inquery2> list=null;
			YzDoctor doctor=service.getDoctorByLoginName(undoListReq.getUser_name());
			if(doctor!=null&&doctor.getId()!=null){
				Map<String, Object> input = new HashMap<String, Object>();
				input.put("doctor_id", doctor.getId());
				input.put("order_by", undoListReq.getOrder_by());
				input.put("inquery_status", undoListReq.getInquery_status());
				list=service.getUndoListV3(input);
				UndoListRes undoListRes=new UndoListRes();
				undoListRes.setList(list);
			}
			
			logger.info(loggerName + "服务端获取待处理诊单完毕...");
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			resModel.setPrivateData("undoList", list);
		} catch (Exception e) {
			logger.info(loggerName + "获取待处理诊单异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, "获取待处理诊单异常");
		}
		return resModel;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/v2/getUndoCount",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getUndoCountV3(HttpServletRequest request,UndoListReq undoListReq){
		frequencyLogger.info(loggerName + "获取待处理诊单数量开始...");
		frequencyLogger.info(JSON.toJSONString(undoListReq));
		ResModelProxy resModel = new ResModelProxy();
		try {
			// token认证
			if (!authric(InqueryListController.TOKEN_AHTHRIC, undoListReq, resModel, request)) {
				return resModel;
			}
			
			// 参数检查
			if (!undoListReq.validate(resModel)){
				return resModel;
			}
			int count=0;
			YzDoctor doctor=service.getDoctorByLoginName(undoListReq.getUser_name());
			if(doctor!=null&&doctor.getId()!=null){
				count=service.getUndoCountV3(doctor.getId());
			}
			
			frequencyLogger.info(loggerName + "获取待处理诊单数量完毕...");
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			resModel.setPrivateData("count", count);
		} catch (Exception e) {
			frequencyLogger.info(loggerName + "获取待处理诊单数量异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, "获取待处理诊单数量异常");
		}
		return resModel;
	}
	
	@ResponseBody
	@RequestMapping(value = "/v2/getHistoryList",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getHistoryListV3(HttpServletRequest request,HistoryListReq historyListReq){
		logger.info(loggerName + "已处理诊单请求开始...");
		logger.info(JSON.toJSONString(historyListReq));
		ResModelProxy resModel = new ResModelProxy();
		try {
			// token认证
			if (!authric(InqueryListController.TOKEN_AHTHRIC, historyListReq, resModel, request)) {
				return resModel;
			}
			
			// 参数检查
			if (!historyListReq.validate(resModel)){
				return resModel;
			}

			Map<String, Object> input = new HashMap<String, Object>();
			int pageSize = 10;
			String pageIndex="1";
			if(historyListReq.getPage_index()!=null&&historyListReq.getPage_index()!=""){
				pageIndex=historyListReq.getPage_index();
			}
			
		    YzDoctor doctor=service.getDoctorByLoginName(historyListReq.getUser_name());
		    String doctor_id=null;
		    if(doctor!=null){
		    	doctor_id=doctor.getId()+"";
		    	if(doctor.getDoctorType()!=null&&doctor.getDoctorType()==3){
			    	doctor_id=null;
			    }
		    }
			input.put("doctor_id", doctor_id);
			input.put("department_id", historyListReq.getDepartment_id());
			input.put("uid", historyListReq.getUid());
			input.put("inquery_id", historyListReq.getInquery_id());
			input.put("op_doctor_name", historyListReq.getOp_doctor_name());
			input.put("archive_flag", historyListReq.getArchive_flag());
			if(historyListReq.getUpdate_time_begin()!=null&&historyListReq.getUpdate_time_begin()!=""){
				input.put("update_time_begin", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.parseLong(historyListReq.getUpdate_time_begin())));
			}
			if(historyListReq.getUpdate_time_end()!=null&&historyListReq.getUpdate_time_end()!=""){
				input.put("update_time_end", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.parseLong(historyListReq.getUpdate_time_end())));
			}
			
			PageUtils pageUtils = new PageUtils(pageSize, pageIndex);
			List<Inquery2> list=service.getHistoryListV3(input,new RowBounds(pageUtils.getStartRow(), pageSize));
			
			int totalCount=service.getHistoryCountV3(input);
			PageBean pb=new PageBean();
			pb.setPageSize(pageSize+"");
			pb.setPageIndex(pageIndex);
			pb.setTotalCount(totalCount+"");
			
			logger.info(loggerName + "服务端获取已处理诊单完毕...");
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			resModel.setPrivateData("pageInfo", pb);
			resModel.setPrivateData("historyList", list);
		} catch (Exception e) {
			logger.info(loggerName + "获取已处理诊单异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, "获取已处理诊单异常");
		}
		return resModel;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/v2/getAllList",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getAllListV3(HttpServletRequest request,AllListReq allListReq){
		logger.info(loggerName + "所有诊单请求开始...");
		logger.info(JSON.toJSONString(allListReq));
		ResModelProxy resModel = new ResModelProxy();
		try {
			// token认证
			if (!authric(InqueryListController.TOKEN_AHTHRIC, allListReq, resModel, request)) {
				return resModel;
			}
			
			// 参数检查
			if (!allListReq.validate(resModel)){
				return resModel;
			}

			Map<String, Object> input = new HashMap<String, Object>();
			int pageSize = 10;
			String pageIndex="1";
			if(allListReq.getPage_index()!=null&&allListReq.getPage_index()!=""){
				pageIndex=allListReq.getPage_index();
			}
		    
			input.put("department_id", allListReq.getDepartment_id());
			input.put("inquery_status", allListReq.getInquery_status());
			input.put("assign_doctor_type", allListReq.getAssign_doctor_type());
			input.put("assign_doctor_name", allListReq.getAssign_doctor_name());
			input.put("assign_flag", allListReq.getAssign_flag());
			input.put("uid", allListReq.getUid());
			input.put("inquery_id", allListReq.getInquery_id());
			if(allListReq.getCreate_time_begin()!=null&&allListReq.getCreate_time_begin()!=""){
				input.put("create_time_begin", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.parseLong(allListReq.getCreate_time_begin())));
			}
			if(allListReq.getCreate_time_end()!=null&&allListReq.getCreate_time_end()!=""){
				input.put("create_time_end", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.parseLong(allListReq.getCreate_time_end())));
			}
			
			PageUtils pageUtils = new PageUtils(pageSize, pageIndex);
			List<AllListVo> list=service.getAllListV3(input,new RowBounds(pageUtils.getStartRow(), pageSize));
			
			int totalCount=service.getAllListCountV3(input);
			PageBean pb=new PageBean();
			pb.setPageSize(pageSize+"");
			pb.setPageIndex(pageIndex);
			pb.setTotalCount(totalCount+"");
			
			logger.info(loggerName + "服务端获取所有诊单完毕...");
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			resModel.setPrivateData("pageInfo", pb);
			resModel.setPrivateData("allList", list);
		} catch (Exception e) {
			logger.info(loggerName + "获取所有诊单异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, "获取所有诊单异常");
		}
		return resModel;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/v2/getFailedAssignCount",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getFailedAssignCountV3(HttpServletRequest request){
		frequencyLogger.info(loggerName + "获取分单失败数量开始...");
		
		ResModelProxy resModel = new ResModelProxy();
		try {

			int count=service.getFailedAssignCountV3();
			
			frequencyLogger.info(loggerName + "获取分单失败数量完毕...");
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			resModel.setPrivateData("count", count);
		} catch (Exception e) {
			frequencyLogger.info(loggerName + "获获取分单失败数量异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, "获取分单失败数量异常");
		}
		return resModel;
	}
}
