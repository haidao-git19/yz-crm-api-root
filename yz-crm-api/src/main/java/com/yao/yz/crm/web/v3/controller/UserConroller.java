package com.yao.yz.crm.web.v3.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.ResModel;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.user.UserUpdateReq;
import com.yzo.yz.crm.open.interior.user.UserAPI;

/**
 * 
 * @Description: 用户模块Controller
 * @Autor: wuwenjun
 * @Date: 12:43:43 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

@Controller
@RequestMapping("/user")
public class UserConroller extends AbstractController{
	
	private static final Logger logger = Logger.getLogger(UserConroller.class);
	
	@Autowired
	private UserAPI userAPI;
	
	@ResponseBody
	@RequestMapping(value = "/update",method = {RequestMethod.POST})
	public ResModel UpdateSecondName(HttpServletRequest request,UserUpdateReq userUpdateReq){
		logger.info("【用户信息更新接口】服务端接收用户称呼更新请求...");
		ResModelProxy resModel = new ResModelProxy();
		try {
			// token认证
			if (!authric(UserConroller.TOKEN_AHTHRIC, userUpdateReq, resModel, request)) {
				return resModel;
			}

			// 调用api
			userAPI.updateSecondName(userUpdateReq, resModel);
		} catch (Exception e) {
			logger.error("【用户信息更新接口】更新用户称呼异常",e);
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, 10004);
		}
		logger.info("【用户信息更新接口】服务端处理用户称呼更新请求完毕...");
		return resModel;
	}
	
}
