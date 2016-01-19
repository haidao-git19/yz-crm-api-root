package com.yao.yz.model.v3.biz.login;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;

/**
 * 
 * @Description: 登录用户信息查询请求
 * @Autor: wuwenjun
 * @Date: 3:59:12 PM Dec 29, 2015
 * @Version: v1.0
 * 
 */

public class LoginUserReq extends AbstractReqModel{

	private static final long serialVersionUID = 6160868460694454201L;

	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
