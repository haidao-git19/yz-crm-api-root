/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Oct 30, 2015-12:57:01 PM
* @version 1.0
*/
package com.yao.yz.crm.web.controller.avaya;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yao.yz.crm.service.interf.avaya.AvayaService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.avaya.AvayaReq;
import com.yao.yz.crm.web.controller.BaseController;

/**
 * 公司名: 壹药网
 * 类名称：AvayaController
 * 类描述：
 * @author wangyulong
 * @date Oct 30, 2015-12:57:01 PM
 */

@Controller
@RequestMapping("/avaya")
public class AvayaController extends BaseController{
	private static final Logger logger = Logger.getLogger(AvayaController.class);
	
	@Autowired
	private AvayaService service;

	
	@ResponseBody
	@RequestMapping(value = "/getMobileArea", method = {RequestMethod.GET,RequestMethod.POST})
	public Object getMobileArea(HttpServletRequest request) {
		JSONObject jsonObject = new JSONObject();
		if(request.getParameter("mobile")==null){
			jsonObject.put("msg", "未接收到mobile!");
			jsonObject.put("mobileArea", "");
	        return jsonObject;
		}else{
			if(!StringUtils.isNumeric(request.getParameter("mobile"))){
				jsonObject.put("msg", "mobile不为数字!");
				jsonObject.put("mobileArea", "");
		        return jsonObject;
			}
			if(request.getParameter("mobile")==""){
				jsonObject.put("msg", "mobile为空!");
				jsonObject.put("mobileArea", "");
		        return jsonObject;
			}
		}
		String mobile=request.getParameter("mobile").substring(0, 7);
		String mobileArea=service.getMobileArea(mobile);
		if(mobileArea==null){
			jsonObject.put("msg", "未记录的手机号段 "+mobile);
			jsonObject.put("mobileArea", "");
			logger.info("未记录的手机号段 "+mobile);
	        return jsonObject;
		}
		
		jsonObject.put("msg", "查询成功!");
		jsonObject.put("mobileArea", mobileArea);
        return jsonObject;
    }
	
	
	@ResponseBody
	@RequestMapping(value = "/getMobileArea1", method = {RequestMethod.GET,RequestMethod.POST})
	public Object getMobileArea1(HttpServletRequest request,AvayaReq avayaReq) {
		BasicResVo basicResVo = beforeExecute(avayaReq, request);
		if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
			logger.warn("token验证失败 [" + avayaReq.toString() + "]");
			return basicResVo;
		}
		basicResVo=service.getMobileArea(avayaReq);
        return basicResVo;
    }
}
