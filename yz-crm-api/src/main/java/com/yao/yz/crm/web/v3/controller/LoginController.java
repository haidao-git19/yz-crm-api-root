package com.yao.yz.crm.web.v3.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.ResModel;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.doctor.ServiceOffReq;
import com.yao.yz.model.v3.biz.doctor.ServiceOnReq;
import com.yao.yz.model.v3.biz.doctor.ServiceStatusReq;
import com.yao.yz.model.v3.biz.doctor.WaitCountReq;
import com.yao.yz.model.v3.biz.login.LoginUserReq;
import com.yzo.yz.crm.open.interior.doctor.DoctorAPI;

/**
 * 
 * @Description: 登录模块
 * @Autor: wuwenjun
 * @Date: 6:45:15 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController{

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private DoctorAPI doctorAPI;
	
	@ResponseBody
	@RequestMapping(value = "/info",method = {RequestMethod.POST})
	public ResModel getAccountInfo(HttpServletRequest request,LoginUserReq loginUserReq,ResModelProxy resModel){
		
		logger.info("【登录帐号查询接口】服务端接收到登录帐号信息查询请求...");
		
		try {
			// token认证
			if (!authric(LoginController.TOKEN_AHTHRIC, loginUserReq, resModel, request)){
				return resModel;
			}
			
			// 查询登录帐号绑定的医生信息
			doctorAPI.getLoginInfo(loginUserReq, resModel);
		} catch (Exception e) {
			logger.error("【登录帐号查询接口】查询登录帐号绑定医生信息异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, 10004);
		}
		
		logger.info("【登录帐号查询接口】服务端处理登陆帐号信息查询请求完毕..." + resModel.toString());
		return resModel;
	}
	
	@ResponseBody
	@RequestMapping(value = "/serviceon",method = {RequestMethod.POST})
	public ResModel serviceOn(HttpServletRequest request,ServiceOnReq serviceOnReq,ResModelProxy resmodel) {
		
		logger.info("【上线接口】服务端接收到医生上班打卡请求..." + serviceOnReq.toString());
		
		try {
			// token验证
			if (!authric(LoginController.TOKEN_AHTHRIC, serviceOnReq, resmodel, request)){
				return resmodel;
			}
			
			// 医生上班打卡
			doctorAPI.serviceOn(serviceOnReq, resmodel);
		} catch (Exception e) {
			logger.error("【上线接口】服务端处理医生上班打卡请求异常",e);
			resmodel.setPublicData(ModelConstants.RET_CODE_ERROR, 10004);
		}
		
		logger.info("【上线接口】服务处理医生上班打卡请求完毕..." + resmodel.toString());
		return resmodel;
	}
	
	@ResponseBody
	@RequestMapping(value = "/serviceoff",method = {RequestMethod.POST})
	public ResModel serviceOff(HttpServletRequest request,ServiceOffReq serviceOffReq,ResModelProxy resmodel) {
		
		logger.info("【下线接口】服务端接收到医生下班打卡请求..." + serviceOffReq.toString());
		
		try {
			// token验证
			if (!authric(LoginController.TOKEN_AHTHRIC, serviceOffReq, resmodel, request)){
				return resmodel;
			}
			
			// 医生上班打卡
			doctorAPI.serviceOff(serviceOffReq, resmodel);
		} catch (Exception e) {
			logger.error("【下线接口】服务端处理医生下班打卡请求异常",e);
			resmodel.setPublicData(ModelConstants.RET_CODE_ERROR, 10004);
		}
		
		logger.info("【下线接口】服务处理医生下班打卡请求完毕..." + resmodel.toString());
		return resmodel;
	}
	
	@ResponseBody
	@RequestMapping(value = "/servicestatus",method = {RequestMethod.POST})
	public ResModel serviceStatus(HttpServletRequest request,ServiceStatusReq serviceStatusReq , ResModelProxy resModel) {
		logger.info("【在线状态查询接口】服务端接收到医生上下班状态查询请求...");
		
		try {
			// token验证
			if (!authric(LoginController.TOKEN_AHTHRIC, serviceStatusReq, resModel, request)){
				return resModel;
			}
			
			// 查询医生状态
			doctorAPI.serviceStatus(serviceStatusReq, resModel);
		} catch (Exception e) {
			logger.error("【在线状态查询接口】服务端处理医生上下班状态查询请求异常", e);
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
		}
		logger.info("【在线状态查询接口】服务端处理医生上班状态查询请求完毕..." + resModel.toString());
		return resModel;
	}
	
	@ResponseBody
	@RequestMapping(value = "/waitcount",method = {RequestMethod.POST})
	public ResModel waitCount(HttpServletRequest request,WaitCountReq waitCountReq,ResModelProxy resModelProxy) {
		
		logger.info("【自营医生转诊列表接口】服务端接收到自营医生分配的待处理诊单列表请求...");
		
		try {
			// token验证
			if (!authric(LoginController.TOKEN_AHTHRIC, waitCountReq, resModelProxy, request)){
				return resModelProxy;
			}
			
			// 查询列表
			doctorAPI.waitCountList(waitCountReq, resModelProxy);
		} catch (Exception e) {
			logger.error("【自营医生转诊列表接口】服务端处理分诊医生列表请求异常", e);
			resModelProxy.setPublicData(ServiceContant.RET_CODE_ERROR, 10004);
		}
		logger.info("【自营医生转诊列表接口】服务端处理自营医生分配的待处理诊单列表请求完毕..." + resModelProxy.toString());
		return resModelProxy;
	}
	
	@ResponseBody
	@RequestMapping(value = "/loginoff",method = {RequestMethod.POST})
	public ResModel loginOff(HttpServletRequest request,ServiceOffReq serviceOffReq,ResModelProxy resmodel) {
		
		logger.info("【登出离线接口】服务端接收到医生离线打卡请求..." + serviceOffReq.toString());
		
		try {
			// token验证
			if (!authric(LoginController.TOKEN_AHTHRIC, serviceOffReq, resmodel, request)){
				return resmodel;
			}
			
			// 医生上班打卡
			doctorAPI.loginOut(serviceOffReq, resmodel);
		} catch (Exception e) {
			logger.error("【登出离线接口】服务端处理医生离线打卡请求异常",e);
			resmodel.setPublicData(ModelConstants.RET_CODE_ERROR, 10004);
		}
		
		logger.info("【登出离线接口】服务处理医生离线打卡请求完毕..." + resmodel.toString());
		return resmodel;
	}
}
