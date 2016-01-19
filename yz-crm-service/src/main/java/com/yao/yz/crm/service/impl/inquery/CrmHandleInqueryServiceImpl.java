package com.yao.yz.crm.service.impl.inquery;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yao.yz.admin.yzadmin.persistence.dao.YzInqueryDao;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzDoctorDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzRecommendDrugsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;
import com.yao.yz.admin.yzadmin.persistence.model.YzRecommendDrugs;
import com.yao.yz.biz.diagnosis.api.InqueryHandleService;
import com.yao.yz.biz.diagnosis.api.bean.inquery.DrugAdviceBean;
import com.yao.yz.biz.diagnosis.api.bean.inquery.InqueryCancelHandlerBean;
import com.yao.yz.biz.diagnosis.api.bean.inquery.InqueryFinishHandlerBean;
import com.yao.yz.biz.diagnosis.api.bean.inquery.InqueryHandleHandlerBean;
import com.yao.yz.biz.diagnosis.api.bean.inquery.InqueryTransferHandlerBean;
import com.yao.yz.crm.service.interf.inquersms.InquerySmsService;
import com.yao.yz.crm.service.interf.inquery.CrmHandleInqueryService;
import com.yao.yz.crm.service.interf.inquery.InqueryQueryService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.drug.DrugRes;
import com.yao.yz.model.v3.biz.inquery.InqueryCancelReq;
import com.yao.yz.model.v3.biz.inquery.InqueryFinishReq;
import com.yao.yz.model.v3.biz.inquery.InqueryHandleReq;
import com.yao.yz.model.v3.biz.inquery.InqueryInfoRes;
import com.yao.yz.model.v3.biz.inquery.InqueryStatusReq;
import com.yao.yz.model.v3.biz.inquery.InqueryStatusRes;
import com.yao.yz.model.v3.biz.inquery.InqueryTransferReq;
import com.yao.yz.model.v3.biz.sms.SendMsgRes;
import com.yao.yz.util.exception.YzRuntimeException;

/**
 * 
 * @Description: 诊单API实现
 * @Autor: wuwenjun
 * @Date: 4:44:00 PM Jan 4, 2016
 * @Version: v1.0
 * 
 */
@Component
public class CrmHandleInqueryServiceImpl implements CrmHandleInqueryService{
	
	@Autowired
	private InqueryHandleService inqueryHandleService;

	@Autowired
	private InquerySmsService inquerySmsService;
	
	@Autowired
	private InqueryQueryService inqueryService;
	
	@Autowired
	private CrmAdminSysParameterDaoExt adminSysParameterDaoExt;
	
	@Autowired
	private CrmYzRecommendDrugsDaoExt crmYzRecommendDrugsDaoExt;
	
	@Autowired
	private CrmYzDoctorDaoExt crmYzDoctorDaoExt;
	
	@Autowired
	private YzInqueryDao yzInqueryDao;
	
	@Override
	public void handleInquery(InqueryHandleReq inqueryHandleReq,
			AbstractResModel resModel) {
		
		logger.info(">>> 开始处理诊单处理" + inqueryHandleReq.toString());
		
		// 请求参数校验
		if (!inqueryHandleReq.validate(resModel)) {
			return;
		}
		
		// 合法性校验
		if (!valMath(inqueryHandleReq.getUser_name(), Integer.parseInt(inqueryHandleReq.getInquery_id()), resModel)) {
			return;
		}
		
		// 处理诊单
		try {
			InqueryHandleHandlerBean inqueryHandleBean = new InqueryHandleHandlerBean();
			inqueryHandleBean.setInqueryId(Integer.parseInt(inqueryHandleReq.getInquery_id()));
			
			inqueryHandleService.handleInquery(inqueryHandleBean);
		} catch (YzRuntimeException e) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, e.getErrorCode());
			logger.error(e.getErrInlineMsg(), e);
			return;
		}
		
		// 拼接诊单信息
		YzInquery yzInquery = inqueryService.getYzInqueryById(Integer.parseInt(inqueryHandleReq.getInquery_id()));
		InqueryInfoRes inqueryInfoRes = new InqueryInfoRes();
		inqueryInfoRes.setAge(yzInquery.getAge());
		inqueryInfoRes.setAge_type(yzInquery.getAgeType());
		inqueryInfoRes.setFinish_flag(yzInquery.getFinishFlag());
		inqueryInfoRes.setInquery_id(yzInquery.getId());
		inqueryInfoRes.setUid(yzInquery.getUid());
		String imageHost = adminSysParameterDaoExt.getImageHost();
		if (StringUtils.isNotBlank(yzInquery.getPhotos())) {
			String[] photos = yzInquery.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
			for (String imageUrl:photos) {
				inqueryInfoRes.getPhotos().add(imageHost + imageUrl);
			}
		}
		inqueryInfoRes.setSelf_desc(yzInquery.getSelfDesc());
		inqueryInfoRes.setSex_comment(yzInquery.getSex());
		inqueryInfoRes.setFinish_flag(yzInquery.getFinishFlag());
		
		// 拼接短信发送记录信息
		List<YzInquerySmsLog> smsLogs = inquerySmsService.getByInqueryId(yzInquery.getId());
		List<SendMsgRes> msgs = new ArrayList<SendMsgRes>();
		if (smsLogs !=null && smsLogs.size() > 0) {
			for (YzInquerySmsLog yzInquerySmsLog : smsLogs) {
				SendMsgRes sendMsgRes = new SendMsgRes();
				sendMsgRes.setSend_doctor_name(yzInquerySmsLog.getSendOp());
				sendMsgRes.setSend_time(yzInquerySmsLog.getSmsSendTime().getTime());
				sendMsgRes.setSend_type(yzInquerySmsLog.getSmsType());
				msgs.add(sendMsgRes);
			}
		}
		
		// 拼接用药建议信息
		List<YzRecommendDrugs> drugs = crmYzRecommendDrugsDaoExt.getByInqueryId(yzInquery.getId());
		List<DrugRes> drugRes = new ArrayList<DrugRes>();
		if (drugs != null && drugs.size() > 0) {
			for (YzRecommendDrugs d : drugs) {
				DrugRes drugResBean = new DrugRes();
				drugResBean.setId(d.getDrugCode());
				drugResBean.setName(d.getDrugName());
				drugResBean.setDesc(d.getDrugDesc());
				drugResBean.setImg(d.getPicture());
				drugResBean.setBrand(d.getBrand());
				drugResBean.setPrescription(d.getPrescription());
				
				drugRes.add(drugResBean);
			}
		}
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		resModel.setPrivateData(inqueryInfoRes.beanName(), inqueryInfoRes);
		resModel.setPrivateData("msg_list", msgs);
		resModel.setPrivateData("drug_advice_list", drugRes);
		
		logger.info(">>> 诊单处理完毕");
	}

	@Override
	public void finishInquery(InqueryFinishReq inqueryFinishReq,
			AbstractResModel resModel) {
		
		logger.info(">>> 开始完成诊单" + inqueryFinishReq.toString());
		
		// 请求参数校验
		if (!inqueryFinishReq.validate(resModel)) {
			return;
		}
		
		// 合法性校验
		if (!valMath(inqueryFinishReq.getUser_name(), Integer.parseInt(inqueryFinishReq.getInquery_id()), resModel)) {
			return;
		}
		
		// 完成诊单
		try {
			InqueryFinishHandlerBean inqueryFinishBean = new InqueryFinishHandlerBean();
			
			inqueryFinishBean.setInqueryId(Integer.parseInt(inqueryFinishReq.getInquery_id()));
			inqueryFinishBean.setNickName(inqueryFinishReq.getNick_name());
			inqueryFinishBean.setSexComment(inqueryFinishReq.getSex_comment());
			if (StringUtils.isNotBlank(inqueryFinishReq.getAge())) {
				inqueryFinishBean.setAge(Integer.parseInt(inqueryFinishReq.getAge()));
				inqueryFinishBean.setAgeType(Integer.parseInt(inqueryFinishReq.getAge_type()));
			}
			inqueryFinishBean.setDoctorDesc(inqueryFinishReq.getDoctor_desc());
			inqueryFinishBean.setDeseaseHisRecord(inqueryFinishReq.getDesease_his_record());
			inqueryFinishBean.setConsult(inqueryFinishReq.getConsult());
			inqueryFinishBean.setInitialCheck(inqueryFinishReq.getInitial_check());
			inqueryFinishBean.setDrugsNote(inqueryFinishReq.getDrugs_note());
			inqueryFinishBean.setSynchronous(inqueryFinishReq.getSynchronous());
			
			// 拼接用药建议
			ArrayList<DrugAdviceBean> drugAdviceBeans = new ArrayList<DrugAdviceBean>();
			try {
				if (inqueryFinishReq.getDrug_advice_list() != null && inqueryFinishReq.getDrug_advice_list().length() > 2) {
					drugAdviceBeans = JSON.parseObject(inqueryFinishReq.getDrug_advice_list(),new TypeReference<ArrayList<DrugAdviceBean>>(){});
				}
			} catch (Exception e) {
				resModel.setPublicData(ServiceContant.RET_CODE_ERROR, "转换用药建议列表异常");
				logger.error(">>> 转换用药建议异常" + inqueryFinishReq.getDrug_advice_list(), e);
				return;
			}
			
			inqueryFinishBean.setDrugAdviceList(drugAdviceBeans);
			
			inqueryHandleService.finishInquery(inqueryFinishBean);
		} catch (YzRuntimeException e) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, e.getErrorCode());
			logger.error(e.getErrInlineMsg(), e);
			return;
		}
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		logger.info(">>> 诊单完成");
	}

	@Override
	public void cancelInquery(InqueryCancelReq inqueryCancelReq,
			AbstractResModel resModel) {
		
		logger.info(">>> 开始取消诊单" + inqueryCancelReq.toString());
		
		// 参数校验
		if (!inqueryCancelReq.validate(resModel)) {
			return;
		}
		
		// 合法性校验
		if (!valMath(inqueryCancelReq.getUser_name(), Integer.parseInt(inqueryCancelReq.getInquery_id()), resModel)) {
			return;
		}
		
		// 取消诊单
		try {
			InqueryCancelHandlerBean inqueryCancelHandlerBean = new InqueryCancelHandlerBean();
			inqueryCancelHandlerBean.setInqueryId(Integer.parseInt(inqueryCancelReq.getInquery_id()));
			
			inqueryHandleService.cancelInquery(inqueryCancelHandlerBean);
		} catch (YzRuntimeException e) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, "取消诊单失败");
			logger.error("取消诊单失败" + e.getErrInlineMsg(), e);
			return;
		}
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		logger.info(">>> 诊单取消完成");
		
	}

	@Override
	public void queryInqueryStatus(InqueryStatusReq inqueryStatusReq,AbstractResModel resModel) {
		
		logger.info(">>> 开始查询诊单状态" + inqueryStatusReq.toString());
		
		// 检查参数
		if (!inqueryStatusReq.validate(resModel)) {
			return;
		}
		
		// 查询诊单信息
		YzInquery yzInquery = inqueryService.getYzInqueryById(Integer.parseInt(inqueryStatusReq.getInquery_id()));
		if (null == yzInquery) {
			logger.error(">>> 查询诊单状态时，诊单不存在");
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 90001);
			return;
		}
		
		// 拼接返回值
		InqueryStatusRes inqueryStatusRes = new InqueryStatusRes();
		inqueryStatusRes.setProcess_flag(yzInquery.getProcessFlag() == null ? "N":yzInquery.getProcessFlag());
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		resModel.setPrivateData(inqueryStatusRes.beanName(), inqueryStatusRes);
		
		logger.info(">>> 查询诊单状态结束");
	}

	@Override
	public void transferInquery(InqueryTransferReq inqueryTransferReq,
			AbstractResModel resModel) {
		
		logger.info(">>> 开始处理转诊..." + inqueryTransferReq.toString());
		
		// 参数校验
		if (!inqueryTransferReq.validate(resModel)) {
			return;
		}
		
		// 调用诊单处理系统的分诊逻辑
		InqueryTransferHandlerBean inqueryTransferHandlerBean = new InqueryTransferHandlerBean();
		inqueryTransferHandlerBean.setInqueryId(Integer.parseInt(inqueryTransferReq.getInquery_id()));
		inqueryTransferHandlerBean.setFromDoctorId(Integer.parseInt(inqueryTransferReq.getFrom_doctor_id()));
		inqueryTransferHandlerBean.setToDoctorId(Integer.parseInt(inqueryTransferReq.getTo_doctor_id()));
		
		try {
			inqueryHandleService.transferInquery(inqueryTransferHandlerBean);
		} catch (YzRuntimeException e) {
			logger.error(">>> 转诊失败" + e.getErrInlineMsg(),e);
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, e.getErrorCode());
			return;
		}
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		logger.info(">>> 处理转诊完毕");
	}
	
	/**
	 * 合法姓校验
	 * @param accountName 登录帐号
	 * @param inqueryId 诊单id
	 * @param resModel 返回数据模型
	 * @return true-合法，false-非法
	 */
	private boolean valMath(String accountName , Integer inqueryId ,AbstractResModel resModel) {
		YzDoctor yzDoctor = crmYzDoctorDaoExt.getDoctorByAccount(accountName);
		YzInquery yzInquery = yzInqueryDao.getYzInqueryByKey(inqueryId);
		
		if (null == yzDoctor) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 90003);
			return false;
		} else if (null == yzInquery) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 90001);
			return false;
		} else if (1 != yzDoctor.getDoctorType() && 2 != yzDoctor.getDoctorType()) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 30002);
			return false;
		} else if (yzDoctor.getId() != yzInquery.getAssignDoctorId()) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 30001);
			return false;
		}
		return true;
	}
	
}
