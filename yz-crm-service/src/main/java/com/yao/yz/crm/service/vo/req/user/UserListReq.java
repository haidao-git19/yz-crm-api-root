package com.yao.yz.crm.service.vo.req.user;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;

/**
 *	描述：用户列表查询请求参数
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 11:14:48 AM
 *	@Versin 1.0
 */
public class UserListReq extends BasicReqVo{

	private static final long serialVersionUID = -6898831089678879114L;
	
	private static final Logger logger = Logger.getLogger(UserListReq.class);

	/**
	 * 用户编号
	 */
	private String uid;
	
	/**
	 * 电话号码
	 */
	private String mobile;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		if (StringUtils.isBlank(uid) && StringUtils.isBlank(this.mobile)) {
			logger.warn("【用户信息搜索接口】搜索条件为空，请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "搜索条件为空", null);
			return;
		} else if (StringUtils.isNotBlank(this.uid)&&!this.uid.matches(ServiceContant.REG_NUMBER)) {
			logger.warn("【用户信息搜索接口】用户编号[uid=" + this.uid + "]格式非法，请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号格式非法", null);
			return;
		} else if (StringUtils.isNotBlank(this.mobile) && !this.mobile.matches(ServiceContant.REG_NUMBER)) {
			logger.warn("【用户信息搜索接口】用户电话号码[mobile=" + this.mobile + "]格式非法,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "电话号码格式非法", null);
			return;
		}else if (StringUtils.isNotBlank(this.uid)&&this.uid.equals("0")) {
			logger.warn("【用户信息搜索接口】用户编号[uid=" + this.uid + "]格式非法，请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号格式非法", null);
			return;
		}
	}
}
