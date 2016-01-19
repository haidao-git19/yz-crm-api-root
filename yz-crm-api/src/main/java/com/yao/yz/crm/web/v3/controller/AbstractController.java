package com.yao.yz.crm.web.v3.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.crm.web.v3.authc.TokenAuthric;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;

/**
 * 
 * @Description: Controller抽象类
 * @Autor: wuwenjun
 * @Date: 1:37:50 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public abstract class AbstractController {
	
	protected static final Integer TOKEN_AHTHRIC = 0;
	
	@Autowired
	protected TokenAuthric tokenAuthric;
	
	protected boolean authric(Integer authricType,AbstractReqModel reqModel,AbstractResModel resModel,HttpServletRequest request) {
		switch (authricType) {
		case 0:
			return tokenAuthric.authric(request, reqModel, resModel);
		default:
			resModel.setPublicData(ModelConstants.RET_CODE_TIMEOUT, 10003);
			return false;
		}
	}
	
}
