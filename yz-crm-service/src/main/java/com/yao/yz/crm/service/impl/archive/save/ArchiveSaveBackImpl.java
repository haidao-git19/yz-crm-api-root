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
//import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveBack;
//import com.yao.yz.crm.service.interf.third.MsgGateway;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.ServiceUtil;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.ArchiveSaveReq;
//import com.yao.yz.util.cache.merberCache.MemcachedUtils;
//import com.yao.yz.util.common.MemcacheKeyPropsReader;
//
///**
// *	描述：保存回访问诊
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 8:30:30 AM
// *	@Versin 1.0
// */
//@Component
//public class ArchiveSaveBackImpl implements ArchiveSaveBack{
//
//	private static final Logger logger = Logger.getLogger(ArchiveSaveBackImpl.class);
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
//	public BasicResVo save(ArchiveSaveReq archiveBackSaveReq) {
//		BasicResVo basicResVo = new BasicResVo();
//		
//		// 参数校验
//		archiveBackSaveReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		// 短信下发标示
//		boolean sendFlag = false;
//		
//		YzArchive sourceYzArchive = yzArchiveDaoExt.getYzArchiveByKey(Integer.parseInt(archiveBackSaveReq.getArchive_id()));
//		// source_id
//		if (sourceYzArchive.getSourceId() == null) {
//			sourceYzArchive.setSourceId(Integer.parseInt(archiveBackSaveReq.getArchive_id()));
//		}
//		// guestDepartment
//		sourceYzArchive.setGuestDepartment(Integer.parseInt(archiveBackSaveReq.getGuest_department()));
//		// questType
//		sourceYzArchive.setQuestType(archiveBackSaveReq.getQuest_type());
//		// doctorId
//		sourceYzArchive.setDoctorId(Integer.parseInt(archiveBackSaveReq.getDoctor_id()));
//		// illnessKey
//		sourceYzArchive.setIllnessKey(archiveBackSaveReq.getIllness_key());
//		// consult
//		sourceYzArchive.setConsult(archiveBackSaveReq.getConsult());
//		// 预约信息
//		if ( StringUtils.isNotBlank(archiveBackSaveReq.getBook_department()) && !"0".equals(archiveBackSaveReq.getBook_department())) {
//			// book_doctor_id,book_department
//			if ("-1".equals(archiveBackSaveReq.getBook_department())) {
//				sourceYzArchive.setBookDoctorId(0);
//				sourceYzArchive.setBookDepartment(null);
//			} else {
//				sourceYzArchive.setBookDepartment(Integer.parseInt(archiveBackSaveReq.getBook_department()));
//				sourceYzArchive.setBookDoctorId(Integer.parseInt(archiveBackSaveReq.getBook_doctor_id()));
//			}
//			// book_date
//			sourceYzArchive.setBookDate(ServiceUtil.getDateFromSeconds(archiveBackSaveReq.getBook_date()));
//			// book_start
//			sourceYzArchive.setBookStart(ServiceUtil.timeToInt(archiveBackSaveReq.getBook_start()));
//			// book_end
//			sourceYzArchive.setBookEnd(ServiceUtil.timeToInt(archiveBackSaveReq.getBook_end()));
//			// book_desc
//			sourceYzArchive.setBookDesc(archiveBackSaveReq.getBook_desc());
//			
//			// 修改下发标示
//			sendFlag = true;
//		} else {
//			// 清空预约信息
//			sourceYzArchive.setBookDepartment(null);
//			sourceYzArchive.setBookDoctorId(null);
//			sourceYzArchive.setBookDate(null);
//			sourceYzArchive.setBookStart(null);
//			sourceYzArchive.setBookEnd(null);
//			sourceYzArchive.setBookDesc(null);
//		}
//		
//		// 清空更新时间
//		sourceYzArchive.setUpdateTime(null);
//		
//		// 是否完成,默认为N
//		sourceYzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_N);
//		
//		// 创建时间
//		sourceYzArchive.setCreateTime(new Date());
//
//		// 清空id
//		sourceYzArchive.setId(null);
//		try {
//			if (yzArchiveDaoExt.insert(sourceYzArchive) == 1) {
//				logger.info("【健康档案回访保存】新建问诊成功[archive_id=" + archiveBackSaveReq.getArchive_id() + "]...");
//				
//				// 回访成功之后，删除缓存中的健康档案列表
//				String key = MemcacheKeyPropsReader.getProperties("YZ_USER_ARCHIVE_KEY") + sourceYzArchive.getUid();
//				if (MemcachedUtils.delete(key)) {
//					logger.info("【MemberCache缓存更新】从缓存中删除用户健康档案列表成功,key=[" + key + "]");
//				} else {
//					logger.warn("【MemberCache缓存更新】从缓存中删除用户健康档案列表失败,key=[" + key + "]");
//				}
//				
//				// 下发短信
//				if (sendFlag){
//					// 查询用户信息
//					YzUser yzUser = yzUserDaoExt.getYzUserByKey(sourceYzArchive.getUid());
//					if (yzUser != null) {
//						// 即时发送预约短信
//						msgGateway.sendMsg(ServiceContant.SMS_BOOK_BACK, yzUser.getMobile(), sourceYzArchive.getBookDate(),
//								sourceYzArchive.getBookStart(),sourceYzArchive.getBookEnd());
//					} else {
//						logger.warn("【健康档案新建保存】查询用户信息不存在[uid=" + sourceYzArchive.getUid() + "],不会发送预约短信和提醒短信，请注意...");
//					}
//				}
//				return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", sourceYzArchive.getId());
//			} else {
//				logger.warn("【健康档案回访保存】数据库操作失败");
//				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "保存失败", null);
//			}
//		} catch (Exception e) {
//			logger.error("【健康档案回访保存】数据库操作失败", e);
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "保存失败", null);
//		}
//	}
//
//}
