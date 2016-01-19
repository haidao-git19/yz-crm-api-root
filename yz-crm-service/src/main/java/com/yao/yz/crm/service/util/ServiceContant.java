package com.yao.yz.crm.service.util;
/**
 *	描述：服务层常量接口
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 6:21:55 PM
 *	@Versin 1.0
 */
public interface ServiceContant {

	/**
	 * 接口调用成功
	 */
	static final Integer RET_CODE_SUCCESS = 1;
	
	/**
	 * 接口调用失败
	 */
	static final Integer RET_CODE_ERROR = 0; 
	
	/**
	 * token验证失败
	 */
	static final Integer RET_CODE_TIMEOUT = -1;
	
	/**
	 * F-女
	 */
	static final String SEX_WOMAN = "F";
	
	/**
	 * M-男
	 */
	static final String SEX_MAN = "M";
	
	/**
	 * 男性称呼
	 */
	static final String CALL_MAN = "先生";
	
	/**
	 * 女性称呼
	 */
	static final String CALL_WOMAN = "小姐";
	
	/**
	 * 是否就诊，Y-是
	 */
	static final String INQUIRE_FLAG_Y = "Y";
	
	/**
	 * 是否就诊，N-否
	 */
	static final String INQUIRE_FLAG_N = "N";
	
	/**
	 * 已去医院就诊
	 */
	static final String INQUIRE_RES_Y = "已去医院就诊";
	
	/**
	 * 未去医院就诊
	 */
	static final String INQUIRE_RES_N = "未去医院就诊";
	
	/**
	 * 图片分隔符
	 */
	static final String IMAGE_SPLIT_FLAG = ",";
	
	/**
	 * 处理方式，F-初诊
	 */
	static final String QUEST_TYPE_F = "F";
	
	/**
	 * 处理方式，S-复诊
	 */
	static final String QUEST_TYPE_S = "S";
	
	/**
	 * 处理方式：R-回访
	 */
	static final String QUEST_TYPE_R = "R";
	
	/**
	 * 初诊
	 */
	static final String QUEST_RES_F = "初诊";
	
	/**
	 * 复诊
	 */
	static final String QUEST_RES_S = "复诊";
	
	/**
	 * 回访
	 */
	static final String QUEST_RES_R = "回访";
	
	/**
	 * 问诊是否完成，是-Y
	 */
	static final String FINISH_FLAG_Y = "Y";
	
	/**
	 * 问诊是否完成，否-N
	 */
	static final String FINISH_FLAG_N = "N";
	
	/**
	 * 编号正则表达式
	 */
	static final String REG_NUMBER = "^-?[0-9]\\d*$";

	/**
	 * 今日待办事项请求
	 */
	static final String REQ_TYPE_N = "N";
	
	/**
	 * 明日待办事项请求
	 */
	static final String REQ_TYPE_F = "F";
	
	/**
	 * 新建问诊档案详情请求
	 */
	static final String ARCHIVE_DETAIL_A = "A";
	
	/**
	 * 编辑问诊档案详情请求
	 */
	static final String ARCHIVE_DETAIL_E = "E";
	
	/**
	 * 回访问诊档案详情求
	 */
	static final String ARCHIVE_DETAIL_R = "R";
	
	/**
	 * 来电处理清单档案详请求
	 */
	static final String ARCHIVE_DETAIL_H = "H";
	
	/**
	 * 全新问诊保存请求
	 */
	static final String ARCHIVE_SAVE_N = "N";
	
	/**
	 * 新建问诊保存请求
	 */
	static final String ARCHIVE_SAVE_A = "A";
	
	/**
	 * 编辑问诊保存请求
	 */
	static final String ARCHIVE_SAVE_E = "E";
	
	/**
	 * 回访问诊保存请求
	 */
	static final String ARCHIVE_SAVE_R = "R";
	
	/**
	 * 来电清单问诊保存请求
	 */
	static final String ARCHIVE_SAVE_H = "H";
	
	/**
	 * 短信发送状态
	 */
	static final String MSG_SEND_Y = "Y";
	
	/**
	 * 短信发送状态
	 */
	static final String MSG_SEND_N = "N";
	/**
	 * 短信类型  用户未接通
	 */
	static final String SMS_NOT_ACCEPT = "SMS_NOT_ACCEPT";
	/**
	 * 短信类型 预约回访
	 */
	static final String SMS_BOOK_BACK = "SMS_BOOK_BACK";
	/**
	 * 短信类型  异常中断
	 */
	static final String SMS_EXCEPTION = "SMS_EXCEPTION";
	/**
	 * 短信类型  医生现在忙
	 */
	static final String SMS_DOCTOR_BUSY = "SMS_DOCTOR_BUSY";
	/**
	 * 短信类型  取消短信
	 */
	static final String SMS_CANCEL_INQUERY = "SMS_CANCEL_INQUERY";
	
	/**
	 * 短信类型 短信超时
	 */
	static final String SMS_TIME_OUT = "SMS_TIME_OUT";
	
	/**
	 * 短信类型   提前30分钟提醒
	 */
	static final String SMS_REMINDER = "SMS_REMINDER";
	
	/**
	 * 短信类型   提交诊单提醒
	 */
	static final String SMS_SUBMIT_INQUERY = "SMS_SUBMIT_INQUERY";
	
	/**
	 * MD5加密权值,配置文件同步配置
	 */
	static final String MD5_KEY = "18c5310f50302475d0b9a261208b181d";
 	
	/**
	 * 文本提示
	 */
	static final String EXCEPTION_NOTICE = "系统出了点小问题，请联系管理人员";
	
	/******************************************************** V2.0版本新增 **********************************************************/
	/**
	 * 医生角色，1-专科医生
	 */
	static final Integer DOCTOR_TYPE_1 = 1;
	
	/**
	 * 医生角色，2-心里医生
	 */
	static final Integer DOCTOR_TYPE_2 = 2;
	
	/**
	 * 医生角色，3-医助
	 */
	static final Integer DOCTOR_TYPE_3 = 3;
	
	/**
	 * 同步APP标识,Y-同步
	 */
	static final String SYNCH_STATUS_Y = "Y";
	
	/**
	 * 同步APP标识，N-不同步
	 */
	static final String SYNCH_STATUS_N = "N";
	
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
	
	/**
	 * 诊单对应订单状态，-1-未知
	 */
	static final Integer PAY_FLAG_DEFAULT = -1;
	
	/**
	 * 诊单对应订单状态，0-无需支付
	 */
	static final Integer PAY_FLAG_0 = 0;
	
	/**
	 * 诊单对应订单状态，1-待支付
	 */
	static final Integer PAY_FLAG_1 = 1;
	
	/**
	 * 诊单对应订单状态，2-已支付
	 */
	static final Integer PAY_FLAG_2 = 2;
	
	/**
	 * 通话状态,Y-已接通
	 */
	static final String PROCESS_FLAG_Y = "Y";
	
	/**
	 * 通话状态，N-未接通
	 */
	static final String PROCESS_FLAG_N = "N";
}
