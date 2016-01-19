package com.yao.yz.crm.web.controller.metadata;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yao.yz.crm.service.interf.metadata.MetaDataService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.web.controller.BaseController;


/**
 * Desc: 数据字典控制器
 * Author: wuwenjun
 * Date: 2015/10/24 16:43
 */
@Controller
@RequestMapping("/metadata")
public class MetaDataController extends BaseController{

	private static final Logger logger = Logger.getLogger(MetaDataController.class);
	
	@Autowired
	private MetaDataService metaDataService;
	
	/**
	 * 请求科室列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/department",method = {RequestMethod.POST})
	public Object getDepartmentList(BasicReqVo basicReqVo,HttpServletRequest request) {
		logger.info("【数据字典接口】服务端接收科室列表查询请求，准备处理...");
		
		// token验证
		BasicResVo basicResVo =this.beforeExecute(basicReqVo, request);
		if(basicResVo.getRet() == ServiceContant.RET_CODE_TIMEOUT){
			return basicResVo;
		}
		
		// 请求科室列表
		return metaDataService.getDepartmentList();
	}

	/**
	 * 请求医生列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doctor", method = {RequestMethod.POST})
	public Object getDoctorList(BasicReqVo basicReqVo,HttpServletRequest request) {
		logger.info("【数据字典接口】服务端接收医生列表查询请求，准备处理...");
		
		// token验证
		BasicResVo basicResVo = beforeExecute(basicReqVo, request);
		if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
			return basicResVo;
		}
		
		// 请求医生列表
		return metaDataService.getDoctorList();
	}
}
