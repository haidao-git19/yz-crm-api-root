package com.yao.yz.crm.web.v3.authc;

import javax.servlet.http.HttpServletRequest;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;

/**
 * 
 * @Description: token认证接口
 * @Autor: wuwenjun
 * @Date: 12:47:00 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public interface TokenAuthric extends Authric{

	boolean authric(HttpServletRequest request,AbstractReqModel reqModel,AbstractResModel resModel);
	
}
