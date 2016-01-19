package com.yao.yz.crm.service.impl.msg;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yao.yz.admin.yzadmin.persistence.dao.YzInqueryDao;
import com.yao.yz.admin.yzadmin.persistence.dao.YzUserDao;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzInquerySmsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
import com.yao.yz.crm.service.interf.third.MsgGateway;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.sms.SendMsgReq;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 2:18:23 PM Jan 9, 2016
 * @Version: v1.0
 * 
 */
@Component
public class MsgServiceCom {

	private static final Logger logger = Logger.getLogger(MsgServiceCom.class);
	
	@Autowired
	private YzInqueryDao yzInqueryDao;
	
	@Autowired
	private YzUserDao yzUserDao;
	
	@Autowired
	private MsgGateway msgGateway;
	
	@Autowired
	private CrmYzInquerySmsDaoExt yzInquerySmsLogDao;
	
	public void sendMsg(SendMsgReq sendMsgReq, AbstractResModel resModel) {
		// 查询诊单
		YzInquery yzInquery = yzInqueryDao.getYzInqueryByKey(Integer.parseInt(sendMsgReq.getInquery_id()));
		if (null == yzInquery) {
			logger.error(">>> 诊单不存在");
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 900001);
		}
		
		// 查询用户信息
		YzUser yzUser = yzUserDao.getYzUserByKey(yzInquery.getUid());
		if (null == yzUser) {
			logger.error(">>> 用户不存在" + yzInquery.toString());
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 90005);
		}
		
		// 发送短信
		msgGateway.sendMsg(sendMsgReq.getSend_type(), yzUser.getMobile());
		
		// 记录短信发送记录,记录失败不影响业务流程
		YzInquerySmsLog yzInquerySmsLog = new YzInquerySmsLog();
		try {
			yzInquerySmsLog.setInqueryId(yzInquery.getId());
			yzInquerySmsLog.setSendOp(yzInquery.getAssignDoctorName());
			yzInquerySmsLog.setSmsContent(SmsServiceImpl.getPropertyValue(sendMsgReq.getSend_type()));
			yzInquerySmsLog.setSmsSendTime(new Date());
			yzInquerySmsLog.setSmsType(sendMsgReq.getSend_type());
			
			yzInquerySmsLogDao.insert(yzInquerySmsLog);
			logger.info(">>> 记录短信发送日志成功..." + yzInquerySmsLog.toString());
		} catch (Exception e) {
			logger.warn(">>> 记录短信发送记录失败，但是不影响主业务，请注意..." + yzInquerySmsLog.toString());
		}
	}
	
	/**
	 * 根据诊单id查询短信发送记录
	 * @param inqueryId 诊单id
	 * @return
	 */
	public List<YzInquerySmsLog> getByInqueryId(Integer inqueryId) {
		return yzInquerySmsLogDao.getByInqueryId(inqueryId);
	}
}
