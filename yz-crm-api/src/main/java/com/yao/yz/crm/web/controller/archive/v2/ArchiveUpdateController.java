//package com.yao.yz.crm.web.controller.archive.v2;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.yao.yz.crm.service.interf.archive.v2.ArchiveUpdateService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveUpdateReq;
//import com.yao.yz.crm.web.controller.BaseController;
//
///**
// * 健康档案更新接口
// * 
// * @version 2.0 新增
// * @author wuwenjun
// *
// */
//@Controller
//@RequestMapping("/archive")
//public class ArchiveUpdateController extends BaseController{
//
//	private static final Logger logger = Logger.getLogger(ArchiveUpdateController.class);
//	
//	@Autowired
//	private ArchiveUpdateService archiveUpdateService;
//	
//	@ResponseBody
//	@RequestMapping("/update")
//	public BasicResVo updateArchive(HttpServletRequest request, ArchiveUpdateReq archiveUpdateReq) {
//		logger.info(SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE") + "接收请求，准备处理...");
//		try {
//			// token验证
//			BasicResVo basicResVo = beforeExecute(archiveUpdateReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE") + "token验证失败[" + archiveUpdateReq.toString() + "]");
//				return basicResVo;
//			}
//			
//			// 更新健康档案
//			return archiveUpdateService.updateArchive(archiveUpdateReq);
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE") + "接口异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_SYS_00001"), null);
//		}
//	}
//	
//}
