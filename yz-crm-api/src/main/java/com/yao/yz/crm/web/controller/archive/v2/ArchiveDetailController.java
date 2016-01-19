//package com.yao.yz.crm.web.controller.archive.v2;
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
//import com.yao.yz.crm.service.interf.archive.v2.ArchiveInfoService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveInfoReq;
//import com.yao.yz.crm.web.controller.BaseController;
//
///**
// * 健康档案详情查询API
// * 
// * @version 2.0 新增
// * @author wuwenjun
// *
// */
//@Controller
//@RequestMapping("/archive")
//public class ArchiveDetailController extends BaseController{
//
//	private static final Logger logger = Logger.getLogger(ArchiveDetailController.class);
//	
//	@Autowired
//	private ArchiveInfoService archiveInfoService;
//	
//	@ResponseBody
//	@RequestMapping(value = "/info",method = {RequestMethod.POST})
//	public BasicResVo getArchiveInfo(HttpServletRequest request, ArchiveInfoReq archiveInfoReq) {
//		logger.info(SysPropertyReader.getValue("REQNAME_ARCHIVE_DETAIL") + "接收请求，准备处理...");
//		try {
//			// token验证
//			BasicResVo basicResVo = beforeExecute(archiveInfoReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_ARCHIVE_DETAIL") + "token验证失败[" + archiveInfoReq.toString() + "]");
//				return basicResVo;
//			}
//			
//			// 返回健康档案详情
//			return archiveInfoService.getArchiveDetail(archiveInfoReq);
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_ARCHIVE_DETAIL") + "接口异常",e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_SYS_00001"), null);
//		}
//	}
//	
//}
