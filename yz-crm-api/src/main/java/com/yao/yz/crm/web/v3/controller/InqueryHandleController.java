package com.yao.yz.crm.web.v3.controller;

//import java.util.ArrayList;
//import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.yao.yz.biz.diagnosis.api.bean.inquery.DrugAdviceBean;
import com.yao.yz.crm.service.interf.inquery.CrmHandleInqueryService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.model.v3.base.res.ResModel;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.inquery.InqueryFinishReq;
import com.yao.yz.model.v3.biz.inquery.InqueryHandleReq;
import com.yao.yz.model.v3.biz.inquery.InqueryStatusReq;
import com.yao.yz.model.v3.biz.inquery.InqueryTransferReq;

/**
 * 
 * @Description: 诊单处理Http服务
 * @Autor: wuwenjun
 * @Date: 5:05:34 PM Jan 4, 2016
 * @Version: v1.0
 * 
 */
@Controller
@RequestMapping("/inquery")
public class InqueryHandleController extends AbstractController{

	private static final Logger logger = Logger.getLogger(InqueryHandleController.class);
	
	@Autowired
	private CrmHandleInqueryService crmHandleInqueryService;
	
	@ResponseBody
	@RequestMapping(value = "/handle",method = {RequestMethod.POST})
	public ResModel handleInquery(HttpServletRequest request,InqueryHandleReq inqueryHandleReq){
		
		logger.info("【诊单处理接口】服务端接收到诊单处理请求...");
		
		ResModelProxy resModelProxy = new ResModelProxy();
		try {
			// token验证
			if (!authric(InqueryHandleController.TOKEN_AHTHRIC, inqueryHandleReq, resModelProxy, request)) {
				return resModelProxy;
			}
			
			// 调用api接口
			crmHandleInqueryService.handleInquery(inqueryHandleReq, resModelProxy);
		} catch (Exception e) {
			resModelProxy.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
			logger.error("【诊单处理接口】处理诊单出现异常", e);
		}
		logger.info("【诊单处理接口】服务端处理诊单处理请求完毕..." + resModelProxy.toString());
		return resModelProxy;
	}
	
	@ResponseBody
	@RequestMapping(value = "/save",method = {RequestMethod.POST})
	public ResModel finishInquery(HttpServletRequest request,InqueryFinishReq inqueryFinishReq) {
		
		logger.info("【诊单保存接口】服务端接收到诊单完成请求...");
		
		ResModelProxy resModelProxy = new ResModelProxy();
		try {
			// token验证
			if (!authric(InqueryHandleController.TOKEN_AHTHRIC, inqueryFinishReq, resModelProxy, request)) {
				return resModelProxy;
			}
			
			// 调用api接口
			crmHandleInqueryService.finishInquery(inqueryFinishReq, resModelProxy);
		} catch (Exception e) {
			resModelProxy.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
			logger.error("【诊单处理接口】处理诊单完成异常", e);
		}
		logger.info("【诊单处理接口】服务端处理诊单完成请求完毕..." + resModelProxy.toString());
		return resModelProxy;
	}
	
	@ResponseBody
	@RequestMapping(value = "/status",method = {RequestMethod.POST})
	public ResModel queryStatus(HttpServletRequest request,InqueryStatusReq inqueryStatusReq,ResModelProxy resmodel) {
		
		logger.info("【诊单状态查询接口】服务端接收到诊单状态查询请求...");
		
		try {
			// token验证
			if (!authric(TOKEN_AHTHRIC, inqueryStatusReq, resmodel, request)){
				return resmodel;
			}
			
			// 调用状态查询接口
			crmHandleInqueryService.queryInqueryStatus(inqueryStatusReq, resmodel);
		} catch (Exception e) {
			logger.error("【诊单处理接口】服务端处理诊单状态查询异常", e);
			resmodel.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
		}
		logger.info("【诊单处理接口】服务端处理诊单完成请求完毕..." + resmodel.toString());
		return resmodel;
	}
	
	@ResponseBody
	@RequestMapping(value = "/transfer",method = {RequestMethod.POST})
	public ResModel transferInquery(HttpServletRequest request,InqueryTransferReq inqueryTransferReq,ResModelProxy resModelProxy) {
		
		logger.info("【诊单转诊接口】服务端接收到转诊请求...." + inqueryTransferReq.toString());
		
		try {
			// token认证
			if (!authric(TOKEN_AHTHRIC, inqueryTransferReq, resModelProxy, request)){
				return resModelProxy;
			}
			
			// 调用转诊接口
			crmHandleInqueryService.transferInquery(inqueryTransferReq, resModelProxy);
		} catch (Exception e) {
			logger.error("【诊单转诊接口】服务端处理转诊请求异常", e);
			resModelProxy.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
		}
		logger.info("【诊单转诊接口】服务端处理诊单转诊请求完毕..." + resModelProxy.toString());
		return resModelProxy;
	}
}
