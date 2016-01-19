package com.yao.yz.crm.web.v3.authc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzDoctorDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.util.common.Md5Utils;
import com.yao.yz.util.common.MemcacheKeyPropsReader;

/**
 * 
 * @Description: token认证实现类
 * @Autor: wuwenjun
 * @Date: 12:50:34 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */
@Component
public class TokenAuthricImpl implements TokenAuthric{
	
	private static final Logger logger = Logger.getLogger(TokenAuthricImpl.class);
	
	private static final Logger frequencyLogger = Logger.getLogger("frequency");

	private static final String loggerName = "【安全认证】>>> ";
	
//	@Autowired
//	private CrmYzDoctorDaoExt crmYzDoctorDaoExt;
	
	@Override
	public boolean authric(HttpServletRequest request, AbstractReqModel reqModel,
			AbstractResModel resModel) {
		// logger
		loggerRequestInfo(request, reqModel);
		
		// token认证
		return authric(reqModel, resModel,request);
	}
	
	/**
	 * 记录请求信息
	 * @param request http请求
	 */
	private void loggerRequestInfo(HttpServletRequest request,AbstractReqModel reqModel){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("URL", request.getRequestURL().toString());
		paraMap.put("IP", request.getRemoteAddr());
		paraMap.put("Authric", reqModel.pubString());
		if(request.getRequestURI().contains("getUndoCount")||request.getRequestURI().contains("getFailedAssignCount")){
			frequencyLogger.info(loggerName + paraMap.toString());
		}else{
			logger.info(loggerName + paraMap.toString());
		}
	}
	
	/**
	 * 用户签名验证
	 * @param reqModel 请求数据模型
	 * @param resModel 返回数据模型
	 * @return
	 */
	private boolean authric(AbstractReqModel reqModel,AbstractResModel resModel,HttpServletRequest request) {
		// token验证
		String token = reqModel.getToken();
		String login_id = reqModel.getLogin_id();
		String user_name = reqModel.getUser_name();
		String user_role = reqModel.getUser_role();
		String platform_id = reqModel.getPlatform_id();
		String current_time = reqModel.getCurrent_time();
		
		String realToken = Md5Utils.md5Encrypt(login_id + user_name + user_role + platform_id + current_time + MemcacheKeyPropsReader.getProperties("MD5_KEY"));
		if (realToken.equals(token)) {
//
//			// 判断医生是否上下架
//			YzDoctor yzDoctor = crmYzDoctorDaoExt.getDoctorByAccount(user_name);
//			if (null == yzDoctor || 2 == yzDoctor.getStatus()) {
//				if(request.getRequestURI().contains("getUndoCount")||request.getRequestURI().contains("getFailedAssignCount")){
//					frequencyLogger.warn(loggerName + "医生已下架，认证失败...");
//					return false;
//				}else{
//					logger.warn(loggerName + "医生已下架，认证失败...");
//					return false;
//				}
//			}
			
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			if(request.getRequestURI().contains("getUndoCount")||request.getRequestURI().contains("getFailedAssignCount")){
				frequencyLogger.info(loggerName + "认证通过...");
			}else{
				logger.info(loggerName + "认证通过...");
			}
			return true;
		} else {
			resModel.setPublicData(ModelConstants.RET_CODE_TIMEOUT, 10002);
			if(request.getRequestURI().contains("getUndoCount")||request.getRequestURI().contains("getFailedAssignCount")){
				frequencyLogger.info(loggerName + "认证失败...");
			}else{
				logger.info(loggerName + "认证失败...");
			}
			return false;
		}
	}
}
