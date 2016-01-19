//package com.yao.yz.crm.service.impl.archive.save;
//
//import java.util.Date;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzUserDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
//import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveNew;
//import com.yao.yz.crm.service.interf.third.MsgGateway;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.ServiceUtil;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.ArchiveSaveReq;
//import com.yao.yz.util.cache.merberCache.MemcachedUtils;
//import com.yao.yz.util.common.MemcacheKeyPropsReader;
//
///**
// *	描述：保存全新问诊
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 8:29:47 AM
// *	@Versin 1.0
// */
//@Component
//public class ArchiveSaveNewImpl implements ArchiveSaveNew{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveSaveNewImpl.class);
//	
//	@Autowired
//	private CrmYzArchiveDaoExt yzArchiveDaoExt; 
//	
//	@Autowired
//	private CrmYzUserDaoExt yzUserDaoExt;
//	
//	@Autowired
//	private MsgGateway msgGateway;
//
//	public BasicResVo save(ArchiveSaveReq archiveNewSaveReq) {
//		BasicResVo basicResVo = new BasicResVo();
//		YzArchive yzArchive = new YzArchive();
//		
//		// 参数校验
//		archiveNewSaveReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		// 短信下发标示
//		boolean sendFlag = false;
//		
//		// uid
//		yzArchive.setUid(Integer.parseInt(archiveNewSaveReq.getUid()));
//		// questType
//		yzArchive.setQuestType(archiveNewSaveReq.getQuest_type());
//		// guestDepartment
//		yzArchive.setGuestDepartment(Integer.parseInt(archiveNewSaveReq.getGuest_department()));
//		// doctorId
//		yzArchive.setDoctorId(Integer.parseInt(archiveNewSaveReq.getDoctor_id()));
//		// illnessKey
//		yzArchive.setIllnessKey(archiveNewSaveReq.getIllness_key());
//		// consult
//		yzArchive.setConsult(archiveNewSaveReq.getConsult());
//		// 存在预约信息的情况下
//		if (StringUtils.isNotBlank(archiveNewSaveReq.getBook_department()) && !"0".equals(archiveNewSaveReq.getBook_department())) {
//			// book_doctor_id,book_department
//			if ("-1".equals(archiveNewSaveReq.getBook_department())) {
//				yzArchive.setBookDoctorId(0);
//				yzArchive.setBookDepartment(null);
//			} else {
//				yzArchive.setBookDoctorId(Integer.parseInt(archiveNewSaveReq.getBook_doctor_id()));
//				yzArchive.setBookDepartment(Integer.parseInt(archiveNewSaveReq.getBook_department()));
//			}
//			// book_date
//			yzArchive.setBookDate(ServiceUtil.getDateFromSeconds(archiveNewSaveReq.getBook_date()));
//			// book_start
//			yzArchive.setBookStart(ServiceUtil.timeToInt(archiveNewSaveReq.getBook_start()));
//			// book_end
//			yzArchive.setBookEnd(ServiceUtil.timeToInt(archiveNewSaveReq.getBook_end()));
//			// book_desc
//			yzArchive.setBookDesc(archiveNewSaveReq.getBook_desc());
//			
//			sendFlag = true;
//		}
//		
//		// create_time
//		yzArchive.setCreateTime(new Date());
//		
//		// finishFlag
//		yzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_N);
//		
//		try {
//			if (yzArchiveDaoExt.insert(yzArchive) == 1) {
//				logger.info("【健康档案全新问诊保存】保存全新问诊成功...");
//				
//				// 回访成功之后，删除缓存中的健康档案列表
//				String key = MemcacheKeyPropsReader.getProperties("YZ_USER_ARCHIVE_KEY") + yzArchive.getUid() ;
//				try {
//					if (MemcachedUtils.delete(key)) {
//						logger.info("【MemberCache缓存更新】从缓存中删除用户健康档案列表成功,key=[" + key + "]");
//					} else {
//						logger.warn("【MemberCache缓存更新】从缓存中删除用户健康档案列表失败,key=[" + key + "]");
//					}
//				} catch (Exception e) {
//					logger.error("【MemberCache缓存更新】从缓存中删除用户健康档案列表异常,key=[" + key + "]",e);
//				}
//				
//				// 下发短信
//				if (sendFlag){
//					// 查询用户信息
//					YzUser yzUser = yzUserDaoExt.getYzUserByKey(yzArchive.getUid());
//					if (yzUser != null) {
//						// 即时发送预约短信
//						msgGateway.sendMsg(ServiceContant.SMS_BOOK_BACK, yzUser.getMobile(), yzArchive.getBookDate(),
//								yzArchive.getBookStart(),yzArchive.getBookEnd());
//					} else {
//						logger.warn("【健康档案新建保存】查询用户信息不存在[uid=" + yzArchive.getUid() + "],不会发送预约短信和提醒短信，请注意...");
//					}
//				}
//				return new BasicResVo().processData(ServiceContant.RET_CODE_SUCCESS, "ok", yzArchive.getId());
//			} else {
//				logger.error("【健康档案全新问诊保存接口】数据库操作失败");
//				return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "保存失败", null);
//			}
//		} catch (Exception e) {
//			logger.info("【健康档案全新问诊保存接口】保存全新问诊异常",e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "保存异常", null);
//		}
//	}
//
//}
