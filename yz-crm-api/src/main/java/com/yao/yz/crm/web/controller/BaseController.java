/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Oct 29, 2015-2:27:21 PM
* @version 1.0
*/
package com.yao.yz.crm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.util.SysPropertyReader;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.util.common.Md5Utils;
import com.yao.yz.util.common.MemcacheKeyPropsReader;

/**
 * 公司名: 壹药网
 * 类名称：BaseController
 * 类描述：
 * @author wangyulong
 * @date Oct 29, 2015-2:27:21 PM
 */
public class BaseController {

	private static final Logger logger = Logger.getLogger(BaseController.class);
	
	public BasicResVo beforeExecute(BasicReqVo baseReqVo,HttpServletRequest request) {

		logger.info("【登录认证】请求资源: "+request.getRequestURI());
		logger.info("【登录认证】请求参数: "+JSONObject.toJSONString(baseReqVo));
		
		// token验证
		BasicResVo basicResVo = new BasicResVo();
		String token = baseReqVo.getToken();
		String login_id = baseReqVo.getLogin_id();
		String user_name = baseReqVo.getUser_name();
		String user_role = baseReqVo.getUser_role();
		String platform_id = baseReqVo.getPlatform_id();
		String current_time = baseReqVo.getCurrent_time();
		
		if (StringUtils.isBlank(token)) {
			return basicResVo.processData(ServiceContant.RET_CODE_TIMEOUT, "token为空", null);
		} else {
			String realToken = Md5Utils.md5Encrypt(login_id + user_name + user_role + platform_id + current_time + MemcacheKeyPropsReader.getProperties("MD5_KEY"));
			if (token.equals(realToken)) {
				return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "token验证成功", null);
			} else {
				return basicResVo.processData(ServiceContant.RET_CODE_TIMEOUT, SysPropertyReader.getValue("RET_MSG_SYS_00002"), null);
			}
		}
		
		/*return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", null);*/
	}
}
