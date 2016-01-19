package com.yao.yz.model.v3.base;

/**
 * 
 * @Description: 数据模型常量接口
 * @Autor: wuwenjun
 * @Date: 11:46:46 AM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public interface ModelConstants {

	//***********************  接口调用返回码  ************************** //
	/**
	 * 返回码：0-接口调用失败
	 */
	static Integer RET_CODE_ERROR = 0;
	/**
	 * 返回码：1-接口调用成功
	 */
	static Integer RET_CODE_RIGHT = 1;
	/**
	 * 返回码：-1-token认证失败
	 */
	static Integer RET_CODE_TIMEOUT = -1;
	
	//*********************** 我是分割线 ********************************//
	
	//***********************  医生接诊状态  ************************** //
	/**
	 * 可接诊
	 */
	static Integer DOCTOR_SERVICE_ON = 2;
	/**
	 * 不可接诊
	 */
	static Integer DOCTOR_SERVICE_OFF = 1;
	
	//*********************** 我是分割线 ********************************//
	
	
	//***********************  诊单状态  ******************************* //
	/**
	 * 诊单状态-待处理
	 */
	static final String INQUERY_STATUS_N = "N";
	
	/**
	 * 诊单状态-处理中
	 */
	static final String INQUERY_STATUS_D = "D";
	
	/**
	 * 诊单状态-已完成
	 */
	static final String INQUERY_STATUS_Y = "Y";
	
	/**
	 * 诊单状态-取消
	 */
	static final String INQUERY_STATUS_C = "C";
	
	//*********************** 我是分割线 ********************************//
	
	
	
	//***********************  短信类型  ******************************* //
	
	static final String SMS_NOT_ACCEPT = "SMS_NOT_ACCEPT";
	static final String SMS_EXCEPTION = "SMS_EXCEPTION";
	static final String SMS_CANCEL_INQUERY = "SMS_CANCEL_INQUERY";
	
	//*********************** 我是分割线 ********************************//
}

