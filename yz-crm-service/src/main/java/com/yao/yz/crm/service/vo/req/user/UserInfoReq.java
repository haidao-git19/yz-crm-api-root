package com.yao.yz.crm.service.vo.req.user;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;

/**
 *	描述：用户信息查询请求数据
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 8:12:32 PM
 *	@Versin 1.0
 */
public class UserInfoReq extends BasicReqVo{
	
	private static final long serialVersionUID = 7348294378352524674L;
	
	private static final Logger logger = Logger.getLogger(UserInfoReq.class);
	
	/**
	 * 用户uid
	 */
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		if (StringUtils.isBlank(this.uid)) {
			logger.warn("【用户信息查询接口】用户编号为空[uid=" + this.uid + "]，请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号为空", null);
			return;
		} else if (!this.uid.matches(ServiceContant.REG_NUMBER)){
			logger.warn("【用户信息查询接口】用户编号[uid=" + this.uid + "]非法");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号格式非法", null);
		}
	}
}
