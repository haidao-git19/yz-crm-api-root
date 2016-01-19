//package com.yao.yz.crm.service.impl.archive.save;
//
//import java.util.Date;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmInqueryDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzUserDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
//import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
//import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveInquery;
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
// *	描述：保存来电清单处理问诊
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 8:30:49 AM
// *	@Versin 1.0
// */
//@Component
//public class ArchiveSaveInqueryImpl implements ArchiveSaveInquery{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveSaveInqueryImpl.class);
//	
//	@Autowired
//	private CrmInqueryDaoExt inqueryDaoExt;
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
//	@Autowired
//	private CacheFacade cacheFacade;
//
//	@Transactional
//	@Override
//	public BasicResVo save(ArchiveSaveReq archiveSaveReq) {
//		
//		String archiveId = null;
//		
//		// 参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		archiveSaveReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		logger.info("【来电清单处理保存】参数校验通过...");
//		
//		// 短信下发标识
//		boolean sendFlag = false;
//		
//		// 查询待处理来电清单
//		YzInquery yzInquery = inqueryDaoExt.getYzInqueryByKey(Integer.parseInt(archiveSaveReq.getInquery_id()));
//		
//		if (yzInquery.getArchiveId() == null) {// 新建健康档案
//			YzArchive yzArchive = new YzArchive();
//			// uid
//			yzArchive.setUid(yzInquery.getUid());
//			// selfDesc
//			yzArchive.setSelfDesc(yzInquery.getSelfDesc());
//			// photos
//			yzArchive.setPhotos(yzInquery.getPhotos());
//			// questType
//			yzArchive.setQuestType(archiveSaveReq.getQuest_type());
//			// guestDepartment
//			yzArchive.setGuestDepartment(Integer.parseInt(archiveSaveReq.getGuest_department()));
//			// doctorId
//			yzArchive.setDoctorId(Integer.parseInt(archiveSaveReq.getDoctor_id()));
//			// illnessKey
//			yzArchive.setIllnessKey(archiveSaveReq.getIllness_key());
//			// consult
//			yzArchive.setConsult(archiveSaveReq.getConsult());
//			// 存在预约信息的情况下
//			if (StringUtils.isNotBlank(archiveSaveReq.getBook_department()) && !"0".equals(archiveSaveReq.getBook_department())) {
//				// book_doctor_id，book_department
//				if ("-1".equals(archiveSaveReq.getBook_department())) {
//					yzArchive.setBookDoctorId(0);
//					yzArchive.setBookDepartment(null);
//				} else {
//					yzArchive.setBookDoctorId(Integer.parseInt(archiveSaveReq.getBook_doctor_id()));
//					yzArchive.setBookDepartment(Integer.parseInt(archiveSaveReq.getBook_department()));
//				}
//				// book_date
//				yzArchive.setBookDate(ServiceUtil.getDateFromSeconds(archiveSaveReq.getBook_date()));
//				// book_start
//				yzArchive.setBookStart(ServiceUtil.timeToInt(archiveSaveReq.getBook_start()));
//				// book_end
//				yzArchive.setBookEnd(ServiceUtil.timeToInt(archiveSaveReq.getBook_end()));
//				// book_desc
//				yzArchive.setBookDesc(archiveSaveReq.getBook_desc());
//				
//				sendFlag = true;
//			}
//			
//			// create_time
//			yzArchive.setCreateTime(new Date());
//			// finishFlag
//			yzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_N);
//			
//			// 新增健康档案并更新来电清单
//			yzArchiveDaoExt.insert(yzArchive);
//			YzInquery updateInquery = new YzInquery();
//			updateInquery.setId(yzInquery.getId());
//			updateInquery.setArchiveId(yzArchive.getId());
//			updateInquery.setUpdateTime(new Date());
//			updateInquery.setOpAccountName(archiveSaveReq.getUser_name());
//			inqueryDaoExt.update(updateInquery);
//			
//			logger.info("【来电清单处理保存】新增健康档案成功[archive_id=" + yzArchive.getId() + "]...");
//			
//			// 下发短信
//			if (sendFlag){
//				// 查询用户信息
//				YzUser yzUser = yzUserDaoExt.getYzUserByKey(yzInquery.getUid());
//				if (yzUser != null) {
//					// 即时发送预约短信
//					msgGateway.sendMsg(ServiceContant.SMS_BOOK_BACK, yzUser.getMobile(), yzArchive.getBookDate(),
//							yzArchive.getBookStart(),yzArchive.getBookEnd());
//					logger.info("【来电清单处理保存】已下发预约短信...");
//				} else {
//					logger.warn("【健康档案新建保存】查询用户信息不存在[uid=" + yzArchive.getUid() + "],不会发送预约短信和提醒短信，请注意...");
//				}
//			}
//			archiveId = yzArchive.getId() + "";
//		} else {// 更新健康档案
//			YzArchive sourceYzArchive = yzArchiveDaoExt.getYzArchiveByKey(yzInquery.getArchiveId());
//			// guestDepartment
//			sourceYzArchive.setGuestDepartment(Integer.parseInt(archiveSaveReq.getGuest_department()));
//			// questType
//			sourceYzArchive.setQuestType(archiveSaveReq.getQuest_type());
//			// doctorId
//			sourceYzArchive.setDoctorId(Integer.parseInt(archiveSaveReq.getDoctor_id()));
//			// illnessKey
//			sourceYzArchive.setIllnessKey(archiveSaveReq.getIllness_key());
//			// consult
//			sourceYzArchive.setConsult(archiveSaveReq.getConsult());
//			// 预约信息
//			if ( StringUtils.isNotBlank(archiveSaveReq.getBook_department()) && !"0".equals(archiveSaveReq.getBook_department())) {
//				// 编辑之前不存在预约信息
//				if (sourceYzArchive.getBookDoctorId() == null) {
//					sendFlag = true;
//				}
//				// book_doctor_id,book_department
//				if ("-1".equals(archiveSaveReq.getBook_department())) {
//					sourceYzArchive.setBookDoctorId(0);
//					sourceYzArchive.setBookDepartment(null);
//				} else {
//					sourceYzArchive.setBookDoctorId(Integer.parseInt(archiveSaveReq.getBook_doctor_id()));
//					sourceYzArchive.setBookDepartment(Integer.parseInt(archiveSaveReq.getBook_department()));
//				}
//				// book_date
//				sourceYzArchive.setBookDate(new Date(Long.parseLong(archiveSaveReq.getBook_date())));
//				// book_start
//				sourceYzArchive.setBookStart(ServiceUtil.timeToInt(archiveSaveReq.getBook_start()));
//				// book_end
//				sourceYzArchive.setBookEnd(ServiceUtil.timeToInt(archiveSaveReq.getBook_end()));
//				// book_desc
//				sourceYzArchive.setBookDesc(archiveSaveReq.getBook_desc());
//			} else {
//				// 清空预约信息
//				sourceYzArchive.setBookDepartment(null);
//				sourceYzArchive.setBookDoctorId(null);
//				sourceYzArchive.setBookDate(null);
//				sourceYzArchive.setBookStart(null);
//				sourceYzArchive.setBookEnd(null);
//				sourceYzArchive.setBookDesc(null);
//			}
//			
//			// 更新时间
//			sourceYzArchive.setUpdateTime(new Date());
//			
//			yzArchiveDaoExt.updateArchive(sourceYzArchive);
//			logger.info("【来电清单处理保存】更新健康档案成功...");
//			
//			// 更新redis中的档案详情
//			cacheFacade.setKeyFiedlBean(
//					RedisKeyPropsReader.getProperties("YZ_ARCHIVE_KEY"),
//					String.valueOf(sourceYzArchive.getId()), sourceYzArchive, 0);
//			logger.info("【redis缓存更新】更新redis缓存健康档案成功[archie_id=" + sourceYzArchive.getId() + "]...");
//			
//			// 下发短信
//			if (sendFlag){
//				// 查询用户信息
//				YzUser yzUser = yzUserDaoExt.getYzUserByKey(yzInquery.getUid());
//				if (yzUser != null) {
//					// 即时发送预约短信
//					msgGateway.sendMsg(ServiceContant.SMS_BOOK_BACK, yzUser.getMobile(), sourceYzArchive.getBookDate(),
//							sourceYzArchive.getBookStart(),sourceYzArchive.getBookEnd());
//					logger.info("【来电清单处理保存】已下发预约短信...");
//				} else {
//					logger.warn("【健康档案新建保存】查询用户信息不存在[uid=" + sourceYzArchive.getUid() + "],不会发送预约短信和提醒短信，请注意...");
//				}
//			}
//			archiveId = sourceYzArchive.getId() + "";
//		}
//		
//		// 回访成功之后，删除缓存中的健康档案列表
//		String key = MemcacheKeyPropsReader.getProperties("YZ_USER_ARCHIVE_KEY") + yzInquery.getUid() ;
//		try {
//			if (MemcachedUtils.delete(key)) {
//				logger.info("【MemberCache缓存更新】从缓存中删除用户健康档案列表成功,key=[" + key + "]");
//			} else {
//				logger.warn("【MemberCache缓存更新】从缓存中删除用户健康档案列表失败,key=[" + key + "]");
//			}
//		} catch (Exception e) {
//			logger.error("【MemberCache缓存更新】从缓存中删除用户健康档案列表异常,key=[" + key + "]",e);
//		}
//		return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", archiveId);
//	}
//
//}
