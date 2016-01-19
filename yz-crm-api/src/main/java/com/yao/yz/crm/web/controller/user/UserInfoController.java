package com.yao.yz.crm.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yao.yz.crm.service.interf.user.UserInfoService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.user.UserInfoReq;
import com.yao.yz.crm.service.vo.req.user.UserListReq;
// v3.0移除 //
//import com.yao.yz.crm.service.vo.req.user.UserUpdateReq;
import com.yao.yz.crm.web.controller.BaseController;

/**
 *	描述：用户信息服务
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 8:53:08 PM
 *	@Versin 1.0
 */
@Controller
@RequestMapping("/user")
public class UserInfoController extends BaseController{
	
	private static final Logger logger = Logger.getLogger(UserInfoController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 请求用户信息
	 * @param userReqVo 请求对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/userinfo",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getUserInfo(UserInfoReq userReqVo,HttpServletRequest request){
		logger.info("【用户信息查询接口】服务端接收用户信息查询请求,准备处理...");
		try {
			// token验证
			BasicResVo basicResVo = beforeExecute(userReqVo, request);
			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
				return basicResVo;
			}
			
			// 用户信息
			return userInfoService.getUserInfo(userReqVo);
		} catch (Exception e) {
			logger.error("【用户信息查询接口】查询用户信息接口异常", e);
			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
		}
	}
	
	
//	
//	以下代码在v3.0时移除
//	
//	
//	/**
//	 * 更新用户信息
//	 * @param userUpdateReq 请求对象
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/update",method = {RequestMethod.POST,RequestMethod.GET})
//	public Object updateUserInfo(UserUpdateReq userUpdateReq,HttpServletRequest request) {
//		logger.info(loggerName + "更新用户称呼接收到请求，准备处理...");
//		try {
//			// token验证
//			BasicResVo basicResVo = beforeExecute(userUpdateReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				return basicResVo;
//			}
//			
//			// 更新用户信息
//			return userInfoService.doUpdateUserInfo(userUpdateReq);
//		} catch (Exception e) {
//			logger.error("【用户信息更新接口】更新用户信息接口异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
//		}
//	}
	
	/**
	 * 搜索用户
	 * @param userListReq 请求对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/search",method = {RequestMethod.POST,RequestMethod.GET})
	public Object searchUserList(UserListReq userListReq,HttpServletRequest request){
		logger.info("【用户信息搜索接口】服务端接收到用户搜索请求,准备处理...");
		try {
			// token验证
			BasicResVo basicResVo = beforeExecute(userListReq, request);
			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
				return basicResVo;
			}
			
			// 搜索用户
			return userInfoService.searchUserList(userListReq);
		} catch (Exception e) {
			logger.error("【用户信息搜索接口】接口异常", e);
			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
		}
	}
 }
