//
// v3.0版本移除
//
//
//package com.yao.yz.crm.web.controller.login;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.yao.yz.crm.service.interf.login.SeatService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.login.SeatInfoReq;
//import com.yao.yz.crm.web.controller.BaseController;
//
///**
// *	描述：坐席信息
// *	@Author wuwenjun
// *	@Date Nov 12, 2015 12:48:16 PM
// *	@Versin 1.0
// */
//@Controller
//@RequestMapping("/seat")
//public class SeatController extends BaseController{
//
//	private static final Logger logger = Logger.getLogger(SeatController.class);
//	
//	@Autowired
//	private SeatService seatService;
//	
//	@ResponseBody
//	@RequestMapping(value = "/info")
//	public Object getSeatInfo(SeatInfoReq seatInfoReq,HttpServletRequest request){
//		logger.info(SysPropertyReader.getValue("REQNAME_INQUERY_SEAT") + "接收到请求，准备处理...");
//		try {
//			// token验证
//			BasicResVo basicResVo = beforeExecute(seatInfoReq, request);
//			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
//				return basicResVo;
//			}
//			
//			// 查询坐席信息
//			return seatService.getSeatInfo(seatInfoReq);
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_INQUERY_SEAT") + "接口异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_SYS_00001"), null);
//		}
//	}
//}
