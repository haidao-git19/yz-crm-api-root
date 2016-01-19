//
// v3.0版本移除
//
//package com.yao.yz.crm.web.controller.inquery;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.yao.yz.crm.service.interf.archive.v2.ArchiveSaveService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveSaveReq;
//import com.yao.yz.crm.web.controller.BaseController;
//
///**
// * 诊单处理完毕保存API
// * 
// * @author wuwenjun
// * @version 2.0新增
// *
// */
//@Controller
//@RequestMapping("/inquery")
//public class InquerySaveController extends BaseController{
//	
//	private static final Logger logger = Logger.getLogger(InquerySaveController.class);
//	
//	@Autowired
//	private ArchiveSaveService archiveSaveService;
//	
//	@ResponseBody
//	@RequestMapping(value = "/save",method = {RequestMethod.POST})
//	public BasicResVo saveInquery(HttpServletRequest request,ArchiveSaveReq archiveNewReq) {
//		logger.info(SysPropertyReader.getValue("REQNAME_INQUERY_SAVE") + "接收请求，准备处理...");
//		try {
//			// tooken验证
//			BasicResVo basicResVo = beforeExecute(archiveNewReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_SAVE") + "token认证失败[" + archiveNewReq.toString() + "]");
//				return basicResVo;
//			}
//			
//			// 新增健康档案
//			return archiveSaveService.saveArchive(archiveNewReq);
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_INQUERY_SAVE") + "接口异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_SYS_00001"), null);
//		}
//	}
//	
//}
