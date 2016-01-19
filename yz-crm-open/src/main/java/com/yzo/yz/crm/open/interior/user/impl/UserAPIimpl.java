package com.yzo.yz.crm.open.interior.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yao.yz.crm.service.interf.user.UserInfoService;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.user.UserUpdateReq;
import com.yzo.yz.crm.open.interior.user.UserAPI;

/**
 * 
 * @Description: 用户信息API接口实现
 * @Autor: wuwenjun
 * @Date: 11:23:33 AM Dec 30, 2015
 * @Version: v1.0
 * 
 */
@Component
public class UserAPIimpl implements UserAPI{
	
	@Autowired
	private UserInfoService userService;
	
	@Override
	public void updateSecondName(UserUpdateReq userUpdateReq, AbstractResModel resModel) {
		
		logger.info(apiName + "开始处理用户称呼更新请求" + userUpdateReq.toString());
		
		// 参数检查
		if (!userUpdateReq.validate(resModel)){
			return;
		}
		
		// 更新用户称呼
		if (!userService.updateSecondName(Integer.parseInt(userUpdateReq.getUid()), userUpdateReq.getSecond_name())) {
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, 90005);
		} else {
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
		}
		
		logger.info(apiName + "更新用户称呼请求处理完毕");
	}
	
}
