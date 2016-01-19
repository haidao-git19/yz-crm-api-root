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
//import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveEdit;
//import com.yao.yz.crm.service.interf.third.MsgGateway;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.ServiceUtil;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.ArchiveSaveReq;
//import com.yao.yz.util.cache.merberCache.MemcachedUtils;
//import com.yao.yz.util.cache.redis.CacheFacade;
//import com.yao.yz.util.common.MemcacheKeyPropsReader;
//import com.yao.yz.util.common.RedisKeyPropsReader;
//
///**
// *	描述：保存编辑问诊
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 8:29:13 AM
// *	@Versin 1.0
// */
//@Component
//public class ArchiveSaveEditImpl implements ArchiveSaveEdit{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveSaveEditImpl.class);
//	
//	@Autowired
//	private CrmYzUserDaoExt yzUserDaoExt;
//	
//	@Autowired
//	private CrmYzArchiveDaoExt yzArchiveDaoExt;
//	
//	@Autowired
//	private MsgGateway msgGateway;
//	
//	@Autowired
//	private CacheFacade cacheFacade;
//	
//	public BasicResVo save(ArchiveSaveReq archiveEditSaveReq) {
//
//		BasicResVo basicResVo = new BasicResVo();
//		
//		// 参数校验
//		archiveEditSaveReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		// 短息下发标示
//		boolean sendFlag = false;
//		
//		YzArchive sourceYzArchive = yzArchiveDaoExt.getYzArchiveByKey(Integer.parseInt(archiveEditSaveReq.getArchive_id()));
//		// guestDepartment
//		sourceYzArchive.setGuestDepartment(Integer.parseInt(archiveEditSaveReq.getGuest_department()));
//		// questType
//		sourceYzArchive.setQuestType(archiveEditSaveReq.getQuest_type());
//		// doctorId
//		sourceYzArchive.setDoctorId(Integer.parseInt(archiveEditSaveReq.getDoctor_id()));
//		// illnessKey
//		sourceYzArchive.setIllnessKey(archiveEditSaveReq.getIllness_key());
//		// consult
//		sourceYzArchive.setConsult(archiveEditSaveReq.getConsult());
//		
//		// 预约信息
//		if ( StringUtils.isNotBlank(archiveEditSaveReq.getBook_department()) && !"0".equals(archiveEditSaveReq.getBook_department())) {
//			// 判断编辑之前是否存在预约，不存在的情况下发预约短信
//			if (sourceYzArchive.getBookDoctorId() == null) {
//				sendFlag = true;
//			}
//			// book_doctor_id,book_department
//			if ("-1".equals(archiveEditSaveReq.getBook_department())) {
//				sourceYzArchive.setBookDoctorId(0);
//				sourceYzArchive.setBookDepartment(null);
//			} else {
//				sourceYzArchive.setBookDoctorId(Integer.parseInt(archiveEditSaveReq.getBook_doctor_id()));
//				sourceYzArchive.setBookDepartment(Integer.parseInt(archiveEditSaveReq.getBook_department()));
//			}
//			// book_date
//			sourceYzArchive.setBookDate(ServiceUtil.getDateFromSeconds(archiveEditSaveReq.getBook_date()));
//			// book_start
//			sourceYzArchive.setBookStart(ServiceUtil.timeToInt(archiveEditSaveReq.getBook_start()));
//			// book_end
//			sourceYzArchive.setBookEnd(ServiceUtil.timeToInt(archiveEditSaveReq.getBook_end()));
//			// book_desc
//			sourceYzArchive.setBookDesc(archiveEditSaveReq.getBook_desc());
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
//		// 更新时间
//		sourceYzArchive.setUpdateTime(new Date());
//		
//		try {
//			if (yzArchiveDaoExt.updateArchive(sourceYzArchive) == 1) {
//				logger.info("【健康档案编辑保存】更新问诊成功[sourceId=" + archiveEditSaveReq.getArchive_id() + "]...");
//				
//				// 删除memcache缓存中的档案列表
//				String key = MemcacheKeyPropsReader.getProperties("YZ_USER_ARCHIVE_KEY") + sourceYzArchive.getUid() ;
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
//				// 刷新redis中的档案详情
//				cacheFacade.setKeyFiedlBean(
//						RedisKeyPropsReader.getProperties("YZ_ARCHIVE_KEY"),
//						String.valueOf(sourceYzArchive.getId()), sourceYzArchive, 0);
//				logger.info("【Redis缓存刷新】更新redis中的健康档案成功[archive_id=" + sourceYzArchive.getId() + "]...");
//				
//				// 下发预约短信
//				if (sendFlag) {
//					// 查询用户信息
//					YzUser yzUser = yzUserDaoExt.getYzUserByKey(sourceYzArchive.getUid());
//					if (yzUser != null) {
//						// 即时发送预约短信
//						msgGateway.sendMsg(ServiceContant.SMS_BOOK_BACK, yzUser.getMobile(), sourceYzArchive.getBookDate(),
//								sourceYzArchive.getBookStart(),sourceYzArchive.getBookEnd());
//					} else {
//						logger.warn("【健康档案编辑保存】查询用户信息不存在[uid=" + sourceYzArchive.getUid() + "],不会发送预约短信和提醒短信，请注意...");
//					}
//				}
//				
//				return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", sourceYzArchive.getId());
//			} else {
//				logger.warn("【健康档案编辑保存】数据库操作失败");
//				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "数据库操作异常", null);
//			}
//		} catch (Exception e) {
//			logger.error("【健康档案编辑保存】操作失败", e);
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "接口异常", null);
//		}
//	
//	}
//
//}
