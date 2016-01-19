//
//	v3.0版本移除
//
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
//import com.yao.yz.crm.service.interf.inquery.InqueryStatusService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.inquery.InqueryHandleReq;
//import com.yao.yz.crm.service.vo.req.inquery.InqueryStatusReq;
//import com.yao.yz.crm.web.controller.BaseController;
//
///**
// * 诊单状态处理API
// * @version 2.0
// * @author wuwenjun
// *
// */
//@Controller
//@RequestMapping("/inquery")
//public class InquertStatusController extends BaseController{
//	
//	private static final Logger logger = Logger.getLogger(InquertStatusController.class);
//	
//	@Autowired
//	private InqueryStatusService inqueryStatusService;
//
//	/**
//	 * 诊单查询API
//	 * @param request
//	 * @param inqueryStatusReq
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/status",method = {RequestMethod.POST})
//	public BasicResVo getInqueryStatus(HttpServletRequest request,InqueryStatusReq inqueryStatusReq){
//		logger.info(SysPropertyReader.getValue("REQNAME_INQUERY_STATUS") + "接收处理请求，准备处理...");
//		try {
//			// token验证
//			BasicResVo basicResVo = beforeExecute(inqueryStatusReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_STATUS") + "token认证失败[" + inqueryStatusReq.toString() + "]");
//				return basicResVo;
//			}
//			
//			// 查询诊单状态
//			return inqueryStatusService.getInqueryStatus(inqueryStatusReq);
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_INQUERY_STATUS") + "接口异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_SYS_00001"), null);
//		}
//	}
//	
//	/**
//	 * 处理待处理诊单API
//	 * @param request
//	 * @param inqueryHandleReq
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/handle",method = {RequestMethod.POST})
//	public BasicResVo handleInquery(HttpServletRequest request,InqueryHandleReq inqueryHandleReq) {
//		logger.info(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "接收处理请求，准备处理...");
//		try {
//			// token验证
//			BasicResVo basicResVo = beforeExecute(inqueryHandleReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "token认证失败[" + inqueryHandleReq.toString() + "]");
//				return basicResVo;
//			}
//			
//			// 处理诊单
//			return inqueryStatusService.handleInqueryStatus(inqueryHandleReq);
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "接口异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_SYS_00001"), null);
//		}
//	}
//}
