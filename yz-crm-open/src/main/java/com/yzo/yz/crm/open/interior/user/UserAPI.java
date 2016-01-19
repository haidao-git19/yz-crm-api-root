package com.yzo.yz.crm.open.interior.user;

import org.apache.log4j.Logger;

import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.user.UserUpdateReq;

/**
 * 
 * @Description: 用户信息API接口，仅供内部程序调用
 * @Autor: wuwenjun
 * @Date: 11:22:48 AM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public interface UserAPI {
	
	static String apiName = "【用户信息接口】>>>";
	
	static final Logger logger = Logger.getLogger(UserAPI.class);

	/**
	 * 更新用户称呼
	 * @param userUpdateReq 用户称呼更新请求
	 * @param resModel 返回数据模型代理
	 */
 	void updateSecondName(UserUpdateReq userUpdateReq,AbstractResModel resModel);
	
}
