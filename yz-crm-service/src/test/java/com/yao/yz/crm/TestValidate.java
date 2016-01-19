//package com.yao.yz.crm;
//
//import com.yao.yz.crm.service.vo.req.inquery.InqueryStatusReq;
//import com.yao.yz.crm.service.vo.res.inquery.InqueryStatusRes;
//import com.yao.yz.util.exception.YzRuntimeException;
//import com.yz.util.tools.validate.ValidateService;
//
///**
// * 测试校验
// * @author wuwenjun
// *
// */
//public class TestValidate {
//	
//	public static void main(String[] args) {
//		InqueryStatusReq inqueryStatusReq = new InqueryStatusReq();
//		inqueryStatusReq.setInquery_id("aa");
//		try {
//			ValidateService.valid(new InqueryStatusRes());
//		} catch (YzRuntimeException e) {
//			System.out.println(e.getErrInlineMsg());
//		}
//		
//	}
//	
//}
