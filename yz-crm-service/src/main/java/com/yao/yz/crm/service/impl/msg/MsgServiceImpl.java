package com.yao.yz.crm.service.impl.msg;

//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;

//import org.apache.commons.lang.StringUtils;
//import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.alibaba.fastjson.JSONObject;
//import com.yao.yz.admin.yzadmin.persistence.dao.YzInqueryDao;
//import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
//import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
//import com.yao.yz.crm.persistence.dao.ext.CrmYzDoctorDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.CrmYzUserDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.YzInqueryDao;
//import com.yao.yz.admin.yzadmin.persistence.dao.YzInquerySmsLogDao;
//import com.yao.yz.admin.yzadmin.persistence.dao.YzUserDao;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;
//import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;
import com.yao.yz.biz.diagnosis.api.InqueryHandleService;
import com.yao.yz.biz.diagnosis.api.bean.inquery.InqueryCancelHandlerBean;
import com.yao.yz.crm.service.interf.msg.MsgService;
//import com.yao.yz.crm.service.interf.third.MsgGateway;
import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.HttpUtils;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.msg.SendMsgReq;
//import com.yao.yz.crm.service.vo.res.sms.SmsResponse;
//import com.yao.yz.util.common.Md5Utils;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.sms.ListMsgReq;
import com.yao.yz.model.v3.biz.sms.SendMsgReq;
import com.yao.yz.model.v3.biz.sms.SendMsgRes;
import com.yao.yz.util.exception.YzRuntimeException;

/**
 *	描述：医生下发客户短信
 *	@Author wuwenjun
 *	@Date Nov 6, 2015 2:29:43 PM
 *	@Versin 1.0
 */
@Service
public class MsgServiceImpl implements MsgService{
	
	private static final Logger logger = Logger.getLogger(MsgServiceImpl.class);
	
	@Autowired
	private InqueryHandleService inqueryHandleService;
	
	@Autowired
	private MsgServiceCom msgServiceCom;
	
	@Override
	public void sendMsg(SendMsgReq sendMsgReq, AbstractResModel resModel) {
		
		logger.info(">>> 开始处理短信发送" + sendMsgReq.toString());
		
		// 参数检查
		if (!sendMsgReq.validate(resModel)) {
			return;
		}
		
		// 判断短信类型，如果是取消短信调用取消逻辑，否则调用短信发送逻辑
		if (ModelConstants.SMS_CANCEL_INQUERY.equals(sendMsgReq.getSend_type())) {
			logger.info(">>> 短信类型为取消短信，准备进入诊单取消流程...");
			InqueryCancelHandlerBean inqueryCancelHandlerBean = new InqueryCancelHandlerBean();
			inqueryCancelHandlerBean.setInqueryId(Integer.parseInt(sendMsgReq.getInquery_id()));
			try {
				inqueryHandleService.cancelInquery(inqueryCancelHandlerBean);
			} catch (YzRuntimeException e) {
				logger.error(">>> 调用都取消诊单逻辑失败，请注意..." + e.getErrInlineMsg(), e);
				resModel.setPublicData(ServiceContant.RET_CODE_ERROR, e.getErrorCode());
			}
		} else {
			logger.info(">>> 准备调用dubbo发送短信...");
			msgServiceCom.sendMsg(sendMsgReq,resModel);
		}
		logger.info(">>> 处理短信发送完成");
	}

	@Override
	public void listMsg(ListMsgReq listMsgReq, AbstractResModel resModel) {
		
		logger.info(">>> 开始处理短信记录查询" + listMsgReq.toString());
		
		// 参数检查
		if (!listMsgReq.validate(resModel)) {
			return;
		}
		
		// 查询短信记录
		List<YzInquerySmsLog> logs = msgServiceCom.getByInqueryId(Integer.parseInt(listMsgReq.getInquery_id()));
		
		// 组装返回数据
		List<SendMsgRes> res = new ArrayList<SendMsgRes>();
		if (logs != null && logs.size() > 0) {
			for (YzInquerySmsLog b : logs) {
				SendMsgRes sendMsgRes = new SendMsgRes();
				sendMsgRes.setSend_doctor_name(b.getSendOp());
				sendMsgRes.setSend_time(b.getSmsSendTime().getTime());
				sendMsgRes.setSend_type(b.getSmsType());
				
				res.add(sendMsgRes);
			}
		}
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		resModel.setPrivateData("msg_list", res);
		
		logger.info(">>> 处理短信记录查询完毕...");
	}
	
	
//	@Override
//	public void sendMsg(Integer inqueryId, String sendType) {
//		// 查询诊单
//		YzInquery yzInquery = yzInqueryDao.getYzInqueryByKey(inqueryId);
//		if (null == yzInquery) {
//			logger.error(">>> 诊单不存在");
//			throw new YzRuntimeException(900001,"诊单不存在");
//		}
//		
//		// 查询用户信息
//		YzUser yzUser = yzUserDao.getYzUserByKey(yzInquery.getUid());
//		if (null == yzUser) {
//			logger.error(">>> 用户不存在" + yzInquery.toString());
//			throw new YzRuntimeException(90005, "用户不存在");
//		}
//		
//		// 发送短信
//		msgGateway.sendMsg(sendType, yzUser.getMobile());
//		
//		// 记录短信发送记录,记录失败不影响业务流程
//		try {
//			YzInquerySmsLog yzInquerySmsLog = new YzInquerySmsLog();
//			yzInquerySmsLog.setInqueryId(yzInquery.getId());
//			yzInquerySmsLog.setSendOp(yzInquery.getAssignDoctorName());
//			yzInquerySmsLog.setSmsContent(SmsServiceImpl.getPropertyValue(sendType));
//			yzInquerySmsLog.setSmsSendTime(new Date());
//			yzInquerySmsLog.setSmsType(sendType);
//			
//			yzInquerySmsLogDao.insert(yzInquerySmsLog);
//		} catch (Exception e) {
//			logger.warn(">>> 记录短信发送记录失败，但是不影响主业务，请注意...");
//		}
//	}
	
//	@Autowired
//	private CrmYzUserDaoExt yzUserDaoExt;
//	
//	@Autowired
//	private YzInqueryDao yzInqueryDao;
//	
//	@Autowired
//	private CrmYzDoctorDaoExt yzDoctorDaoExt;
//
//	@Override
//	public BasicResVo sendMsg(SendMsgReq sendMsgReq) {
//		
//		BasicResVo basicResVo = new BasicResVo();
//		// 参数校验
//		sendMsgReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		// 查询用户信息
//		YzUser yzUser = yzUserDaoExt.getYzUserByKey(Integer.parseInt(sendMsgReq.getUid()));
//		if (null == yzUser) {
//			logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "未查询到指定用户[" + sendMsgReq.toString() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_USER_00000"), null);
//		}
//		
//		// 取消短信时需要刷新订单状态
//		if (ServiceContant.SMS_CANCEL_INQUERY.equals(sendMsgReq.getSend_type()) && !cancelInquery(sendMsgReq, basicResVo)) {
//			return basicResVo;
//		}
//		
//		// 发送短信
//		SmsResponse smsResponse = msgGateway.sendMsg(sendMsgReq.getSend_type(), yzUser.getMobile());
//		if (smsResponse.isFlag()) {
//			logger.info(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "下发短信成功[" + yzUser.toString() + "]");
//			return new BasicResVo().processData(ServiceContant.RET_CODE_SUCCESS, "ok", null);
//		} else {
//			logger.info(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "下发短信失败[" + yzUser.toString() + "]");
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, smsResponse.getMsg(), null);
//		}
//	}
//	
//	
//	/**
//	 * 取消诊单
//	 * @param yzInquery
//	 * @return
//	 */
//	private boolean cancelInquery(SendMsgReq sendMsgReq,BasicResVo basicResVo) {
//		
//		logger.info(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "进入诊单取消流程,请注意~~~~~~~");
//		
//		/**************************** inquery_id为空，认为是历史数据 ****************************/ 
//		if (StringUtils.isBlank(sendMsgReq.getInquery_id())) {
//			logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "请求未传入inquery_id，该数据当作历史数据处理[" + sendMsgReq.toString() + "]");
//			return true;
//		} else {
//			/**************************************	判断是否需要调用取消诊单	**************************************/
//			// 查询诊单信息
//			YzInquery yzInquery = yzInqueryDao.getYzInqueryByKey(Integer.parseInt(sendMsgReq.getInquery_id()));
//			if (yzInquery == null) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00000"), null);
//				logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "未查询到指定的诊单[" + sendMsgReq.toString() + "]");
//				return false;
//			}
//			
//			// 诊单所属uid是否与请求参数uid一致
//			if (yzInquery.getUid() != Integer.parseInt(sendMsgReq.getUid())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00011"), null);
//				logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "诊单所属用户与请求参数不一致[" + yzInquery.toString() + "," + sendMsgReq.toString() + "]");
//				return false;
//			}
//			
//			// 检查诊单状态，已完成、已取消诊单不允许取消,进入支付流程的诊单不允许取消,不执行更新操作
//			if (ServiceContant.INQUERY_STATUS_C.equals(yzInquery.getFinishFlag())) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "已取消诊单不允许取消[" + sendMsgReq.toString() + "," + yzInquery.toString() + "]");
//				return true;
//			} else if (ServiceContant.INQUERY_STATUS_Y.equals(yzInquery.getFinishFlag())) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "已完成诊单不允许取消[" + sendMsgReq.toString() + "," + yzInquery.toString() + "]");
//				return true;
//			} else if (!ServiceContant.PAY_FLAG_DEFAULT.equals(yzInquery.getPayFlag())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00012"), null);
//				logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "已进入支付流程的诊单不允许取消[" + yzInquery.toString() + "]");
//				return false;
//			}
//			
//			// 消耗诊单
//			if (!consumeTrade(yzInquery, basicResVo)) {
//				return false;
//			}
//			
//			// 刷新诊单状态
//			yzInquery.setFinishFlag(ServiceContant.INQUERY_STATUS_C);
//			yzInquery.setUpdateTime(new Date());
//			
//			if (yzInqueryDao.update(yzInquery) != 1) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00010"), null);
//				logger.warn(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "取消诊单失败[" + yzInquery.toString() + "]");
//				return false;
//			}
//			logger.info(SysPropertyReader.getValue("REQNAME_SMS_SEND") + "取消诊单成功[" + yzInquery.toString() + "]");
//			return true;
//
//		}
//	
//	}
//	
//	/**
//	 * 消耗订单
//	 * @param yzDoctor 处理医生信息
//	 * @param yzInquery 诊单信息
//	 * @return
//	 */
//	private boolean consumeTrade(YzInquery yzInquery,BasicResVo basicResVo) {
//		try {
//			// 检查诊单支付状态
//			if (ServiceContant.PAY_FLAG_DEFAULT.equals(yzInquery.getPayFlag())) {
//				
//				logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "诊单支付状态为-1，准备调用订单消耗接口，请注意~~~~~~~~~~[" + yzInquery.toString() + "]");
//				
//				// 查询处理医生信息
//				YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorByAccount(yzInquery.getOpAccountName());
//				if (null == yzDoctor) {
//					logger.warn(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "未查询到诊单对应的医生信息[" + yzInquery.toString() + "]");
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_DOCTOR_00000"), null);
//					return false;
//				}
//				
//				// 组装参数
//				String userId = String.valueOf(yzInquery.getUid());
//				String inqueryId = String.valueOf(yzInquery.getId());
//				String doctorId = String.valueOf(yzDoctor.getId());
//				String doctorName = yzInquery.getOpDoctorName();
//				String callDucation = "0";
//				String sign = Md5Utils.md5Encrypt(userId + inqueryId + doctorId + doctorName + callDucation + SysPropertyReader.getValue("SIGN_KEY"));
//				
//				/*
//				if (ServiceContant.PROCESS_FLAG_Y.equals(yzInquery.getProcessFlag())) {
//					if (null == yzInquery.getCallDuration()) {
//						callDucation = 0;
//						logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "通话已接通,通话时长为null，通话时长当作0进行处理，请注意~~~~~~~~[" + yzInquery.toString() + "]");
//					} else {
//						callDucation = yzInquery.getCallDuration();
//						logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "通话已接通，通话时长为" + callDucation + ",请注意~~~~~~");
//					}
//				} else if (ServiceContant.PROCESS_FLAG_N.equals(yzInquery.getProcessFlag())) {
//					callDucation = 0;
//					logger.warn(SysPropertyReader.getValue("") + "通话未接通，通话时长当作0进行处理，请注意~~~~~~~~~~");
//				} else {
//					logger.warn(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "未知的通话状态，通话时长当作0进行处理，请注意~~~~~~~~~[" + yzInquery.toString() + "]");
//				}
//				*/
//				
//				Map<String, Object> paraMap = new HashMap<String, Object>();
//				paraMap.put("userId", userId);
//				paraMap.put("inqueryId", inqueryId);
//				paraMap.put("doctorId", doctorId);
//				paraMap.put("doctorName", doctorName);
//				paraMap.put("sign", sign);
//				paraMap.put("callDuration", callDucation);
//
//				// 调用订单接口
//				String url = SysPropertyReader.getValue("TRADE_URL");
//				String resString = HttpUtils.httpPostPubMethod(url, paraMap);
//				JSONObject tradeRes = JSONObject.parseObject(resString);
//				
//				if (0 == tradeRes.getIntValue("ret")) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00013"), null);
//					logger.error(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用成功，接口返回消耗订单失败，请注意~~~~~~~~~~~["+ tradeRes.toJSONString() + "," + yzInquery.toString() + "]");
//					return false;
//				} else if (1 == tradeRes.getIntValue("ret")) {
//					logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用成功，接口返回消耗订单成功，请注意~~~~~~~~~~~["+ tradeRes.toJSONString() + "," + yzInquery.toString() + "]");
//					return true;
//				} else {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00013"), null);
//					logger.error(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用成功，接口返回未知状态码，请注意~~~~~~~~~~~~[" + tradeRes.toJSONString() + "," + yzInquery.toString() + "]");
//					return false;
//				}
//			} else {
//				logger.warn(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "诊单已进入支付流程，不会调用订单消耗接口，请注意~~~~~~~~~~[" + yzInquery.toString() + "]");
//				return true;
//			}
//		} catch (Exception e) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00013"), null);
//			logger.error(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用订单消耗接口异常", e);
//			return false;
//		}
//	}
}
