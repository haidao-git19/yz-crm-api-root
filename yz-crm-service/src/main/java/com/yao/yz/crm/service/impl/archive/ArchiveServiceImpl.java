package com.yao.yz.crm.service.impl.archive;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzInquerySmsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;
import com.yao.yz.admin.yzadmin.persistence.model.YzRecommendDrugs;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzArchiveExt;
import com.yao.yz.crm.service.interf.archive.ArchiveService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.util.SysPropertyReader;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.archive.ArchiveListReq;
import com.yao.yz.model.v3.biz.archive.ArchiveListRes;
import com.yao.yz.model.v3.biz.archive.ArchiveQueryReq;
import com.yao.yz.model.v3.biz.archive.ArchiveQueryRes;
import com.yao.yz.model.v3.biz.archive.ArchiveUpdateReq;
import com.yao.yz.model.v3.biz.drug.DrugRes;
import com.yao.yz.model.v3.biz.sms.SendMsgRes;
import com.yao.yz.util.cache.redis.CacheFacade;
import com.yao.yz.util.common.RedisKeyPropsReader;

/**
 * 
 * @Description: 健康档案接口实现
 * @Autor: wuwenjun
 * @Date: 10:44:41 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */
@Service
public class ArchiveServiceImpl implements ArchiveService{
	
	private static final Logger logger = Logger.getLogger(ArchiveServiceImpl.class);
	
	@Autowired
	private ArchiveServiceCom archiveServiceCom;
	
	@Autowired
	private CrmAdminSysParameterDaoExt crmAdminSysParameterDaoExt;
	
	@Autowired
	private CrmYzInquerySmsDaoExt crmYzInquerySmsDaoExt;
	
	@Autowired
	private CacheFacade cacheFacade;
	
	@Override
	public void getAchiveDetail(ArchiveQueryReq archiveInfoReq,
			AbstractResModel resModel) {
		
		logger.info(">>> 开始查询健康档案详情" + archiveInfoReq.toString());
		
		// 参数校验
		if (!archiveInfoReq.validate(resModel)) {
			return;
		}

		// 查询健康档案信息
		YzArchive yzArchive = archiveServiceCom.getArchive(Integer.parseInt(archiveInfoReq.getArchive_id()));
		if (null == yzArchive) {
			logger.error(">>> 查询健康档案信息时，健康档案不存在");
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, 90006);
			return;
		} else {
			ArchiveQueryRes archiveInfoRes = new ArchiveQueryRes();
			
			archiveInfoRes.setArchive_id(yzArchive.getId());
			archiveInfoRes.setNick_name(yzArchive.getNickName());
			archiveInfoRes.setSex_comment(yzArchive.getSexComment());
			archiveInfoRes.setAge(yzArchive.getAge());
			archiveInfoRes.setAge_type(yzArchive.getAgeType());
			archiveInfoRes.setSelf_desc(yzArchive.getSelfDesc());
			// 拼接图片
			String imageHost = crmAdminSysParameterDaoExt.getImageHost();
			if (StringUtils.isNotBlank(yzArchive.getPhotos())) {
				String[] photos = yzArchive.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
				for (String imageUrl:photos) {
					archiveInfoRes.getPhotos().add(imageHost + imageUrl);
				}
			}
			archiveInfoRes.setDoctor_desc(yzArchive.getDoctorDesc());
			archiveInfoRes.setDesease_his_record(yzArchive.getDeseaseHisRecord());
			archiveInfoRes.setInitial_check(yzArchive.getInitialCheck());
			archiveInfoRes.setConsult(yzArchive.getConsult());
			archiveInfoRes.setDrugs_note(yzArchive.getDrugsNote());
			archiveInfoRes.setInquery_id(yzArchive.getInqueryId());
			
			
			// 根据档案id查询用药建议
			List<DrugRes> drugs = new ArrayList<DrugRes>();
			if (null != yzArchive.getInqueryId()) {
				List<YzRecommendDrugs> yzRecommendDrugs = archiveServiceCom.getDrugs(yzArchive.getId());
				if (yzRecommendDrugs != null && yzRecommendDrugs.size() > 0) {
					for(YzRecommendDrugs b : yzRecommendDrugs) {
						
						DrugRes drugRes = new DrugRes();
						drugRes.setDesc(b.getDrugDesc());
						drugRes.setBrand(b.getBrand());
						drugRes.setImg(b.getPicture());
						drugRes.setId(b.getDrugCode());
						drugRes.setName(b.getDrugName());
						drugRes.setPrescription(b.getPrescription());
						
						drugs.add(drugRes);
					}
				}
			}
			
			// 短信发送记录
			List<YzInquerySmsLog> smsLogs = crmYzInquerySmsDaoExt.getByInqueryId(yzArchive.getInqueryId());
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
			
			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
			resModel.setPrivateData(archiveInfoRes.beanName(), archiveInfoRes);
			resModel.setPrivateData("msg_list", msgs);
			resModel.setPrivateData("drug_advice_list", drugs);
		}
	}

	@Override
	public void updateArchive(ArchiveUpdateReq archiveUpdateReq,
			AbstractResModel resModel) {
		
		// 参数校验
		if (!archiveUpdateReq.validate(resModel)) {
			return;
		}
		
		// 查询健康档案
		YzArchive yzArchive = archiveServiceCom.getArchive(Integer.parseInt(archiveUpdateReq.getArchive_id()));
		if (null == yzArchive) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 90006);
			return;
		}
		
		// 执行健康档案更新业务
		yzArchive.setNickName(archiveUpdateReq.getNick_name());
		yzArchive.setSexComment(archiveUpdateReq.getSex_comment());
		yzArchive.setAge(Integer.parseInt(archiveUpdateReq.getAge()));
		yzArchive.setAgeType(Integer.parseInt(archiveUpdateReq.getAge_type()));
		yzArchive.setDoctorDesc(archiveUpdateReq.getDoctor_desc());
		yzArchive.setDeseaseHisRecord(archiveUpdateReq.getDesease_his_record());
		yzArchive.setInitialCheck(archiveUpdateReq.getInitial_check());
		yzArchive.setConsult(archiveUpdateReq.getConsult());
		yzArchive.setDrugsNote(archiveUpdateReq.getDrugs_note());
		if (ServiceContant.SYNCH_STATUS_Y.equals(archiveUpdateReq.getSynchronous())) {
			yzArchive.setFinishFlag(ServiceContant.SYNCH_STATUS_Y);
		} else if (ServiceContant.SYNCH_STATUS_N.equals(archiveUpdateReq.getSynchronous())) {
			yzArchive.setFinishFlag(ServiceContant.SYNCH_STATUS_N);
		}
		if (ServiceContant.SYNCH_STATUS_Y.equals(archiveUpdateReq.getSynchronous())) {
			yzArchive.setFinishFlag("Y");
		} else {
			yzArchive.setFinishFlag("N");
		}
		
		// 拼接用药建议
		ArrayList<DrugRes> drugAdviceBeans = new ArrayList<DrugRes>();
		try {
			if (archiveUpdateReq.getDrug_advice_list() != null && archiveUpdateReq.getDrug_advice_list().length() > 2) {
				drugAdviceBeans = JSON.parseObject(archiveUpdateReq.getDrug_advice_list(),new TypeReference<ArrayList<DrugRes>>(){});
			}
		} catch (Exception e) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, "转换用药建议列表异常");
			logger.error(">>> 转换用药建议异常" + archiveUpdateReq.getDrug_advice_list(), e);
			return;
		}
		
		List<YzRecommendDrugs> drugs = new ArrayList<YzRecommendDrugs>();
		if (archiveUpdateReq.getDrug_advice_list() != null && drugAdviceBeans.size() > 0) {
			for (DrugRes drugRes : drugAdviceBeans) {
				YzRecommendDrugs d = new YzRecommendDrugs();
				d.setArchiveId(yzArchive.getId());
				d.setBrand(drugRes.getBrand());
				d.setDrugCode(drugRes.getId());
				d.setDrugDesc(drugRes.getDesc());
				d.setDrugName(drugRes.getName());
				d.setInqueryId(yzArchive.getInqueryId());
				d.setPicture(drugRes.getImg());
				d.setPrescription(drugRes.getPrescription());
				d.setUid(yzArchive.getUid());
				
				drugs.add(d);
			}
		}
		
		try {
			archiveServiceCom.updateArchive(yzArchive, drugs);
		} catch (Exception e) {
			logger.error(">>> 更新健康档案失败，事务已回滚，请注意...", e);
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, 50008);
			return;
		}
		
		// 删除缓存
		try {
			cacheFacade.setKeyFiedlBean(RedisKeyPropsReader.getProperties("YZ_ARCHIVE_KEY"),String.valueOf(yzArchive.getId()), yzArchive, 0);
			logger.info(SysPropertyReader.getValue("REQNAME_REDIS_FRESH") + "更新健康档案成功[archive_id=" + yzArchive.getId() + "]");
		} catch (Exception e) {
			logger.error(SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE") + "更新缓存信息失败", e);
		}
		
		// 根据同步标识决定是否推送app消息
		if (ServiceContant.SYNCH_STATUS_Y.equals(archiveUpdateReq.getSynchronous())) {
			archiveServiceCom.pushAppMsg(yzArchive.getUid());
		}
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
	}

	@Override
	public void getArchiveList(ArchiveListReq archiveListReq,AbstractResModel resModel) {
		
		logger.info(">>> 开始查询健康档案列表..." + archiveListReq.toString());
		
		// 请求参数校验
		if (!archiveListReq.validate(resModel)) {
			return;
		}
		
		// 查询列表
		List<CrmYzArchiveExt> archiveExts = archiveServiceCom.getByUidAndDep(Integer.parseInt(archiveListReq.getUid()), Integer.parseInt(archiveListReq.getDepartment_id()));
		List<ArchiveListRes> res = new ArrayList<ArchiveListRes>();
		if (archiveExts != null && archiveExts.size() > 0) {
			for (CrmYzArchiveExt yzArchiveExt : archiveExts) {
				ArchiveListRes model = new ArchiveListRes();
				model.setArchive_id(yzArchiveExt.getId());
				model.setCreate_time(yzArchiveExt.getCreateTime().getTime());
				model.setGuest_department_name(yzArchiveExt.getQuestDepName());
				
				// 拼接医生信息
				YzDoctor yzDoctor = yzArchiveExt.getQuestDoctor();
				if (yzDoctor != null) {
					model.setDoctor_name((yzDoctor.getSecondName() == null ? "":yzDoctor.getSecondName()) + (yzDoctor.getFirstName() == null ? "" : yzDoctor.getFirstName()));
				}
				
				model.setNick_name(yzArchiveExt.getNickName());
				model.setSex_comment(yzArchiveExt.getSexComment());
				model.setAge(yzArchiveExt.getAge());
				model.setAge_type(yzArchiveExt.getAgeType());
				model.setSelf_desc(yzArchiveExt.getSelfDesc());
				
				// 拼接图片地址
				String imageHost = crmAdminSysParameterDaoExt.getImageHost();
				if (StringUtils.isNotBlank(yzArchiveExt.getPhotos())) {
					String[] photos = yzArchiveExt.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
					for (String imageUrl:photos) {
						model.getPhotos().add(imageHost + imageUrl);
					}
				}
				if (StringUtils.isNotBlank(yzArchiveExt.getPrescribePhotos())) {
					String[] photos = yzArchiveExt.getPrescribePhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
					for (String imageUrl:photos) {
						model.getPhotos().add(imageHost + imageUrl);
					}
				}
				
				model.setDoctor_desc(yzArchiveExt.getDoctorDesc());
				model.setDesease_his_record(yzArchiveExt.getDeseaseHisRecord());
				model.setInitial_check(yzArchiveExt.getInitialCheck());
				model.setConsult(yzArchiveExt.getConsult());
				model.setInquery_id(yzArchiveExt.getInqueryId());
				
				res.add(model);
			}
		
			// 添加数据模型
			resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
			resModel.setPrivateData("archive_list", res);
			
			logger.info(">>> 查询健康档案列表结束...");
		}
	}
}
