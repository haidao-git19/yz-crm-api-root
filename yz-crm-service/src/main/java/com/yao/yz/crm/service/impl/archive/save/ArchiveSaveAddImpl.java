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
//import com.yao.yz.crm.service.interf.archive.save.ArchiveSaveAdd;
//import com.yao.yz.crm.service.interf.third.MsgGateway;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.ServiceUtil;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.ArchiveSaveReq;
//import com.yao.yz.util.cache.merberCache.MemcachedUtils;
//import com.yao.yz.util.common.MemcacheKeyPropsReader;
//
///**
// *	描述：保存新建问诊
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 8:30:11 AM
// *	@Versin 1.0
// */
//@Component
//public class ArchiveSaveAddImpl implements ArchiveSaveAdd{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveSaveAddImpl.class);
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
//	@Override
//	public BasicResVo save(ArchiveSaveReq archiveSaveReq) {
//		
//		BasicResVo basicResVo = new BasicResVo();
//		
//		// 短信下发标示
//		boolean sendFlag = false;
//		
//		// 参数校验
//		archiveSaveReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		YzArchive sourceYzArchive = yzArchiveDaoExt.getYzArchiveByKey(Integer.parseInt(archiveSaveReq.getArchive_id()));
//		// source_id
//		if (sourceYzArchive.getSourceId() == null) {
//			sourceYzArchive.setSourceId(Integer.parseInt(archiveSaveReq.getArchive_id()));
//		}
//		// guestDepartment
//		sourceYzArchive.setGuestDepartment(Integer.parseInt(archiveSaveReq.getGuest_department()));
//		// questType
//		sourceYzArchive.setQuestType(archiveSaveReq.getQuest_type());
//		// doctorId
//		sourceYzArchive.setDoctorId(Integer.parseInt(archiveSaveReq.getDoctor_id()));
//		// illnessKey
//		sourceYzArchive.setIllnessKey(archiveSaveReq.getIllness_key());
//		// consult
//		sourceYzArchive.setConsult(archiveSaveReq.getConsult());
//		// 预约信息
//		if ( StringUtils.isNotBlank(archiveSaveReq.getBook_department()) && !"0".equals(archiveSaveReq.getBook_department())) {
//			// book_doctor_id,book_department
//			if ("-1".equals(archiveSaveReq.getBook_department())) {
//				sourceYzArchive.setBookDoctorId(0);
//				sourceYzArchive.setBookDepartment(null);
//			} else {
//				sourceYzArchive.setBookDoctorId(Integer.parseInt(archiveSaveReq.getBook_doctor_id()));
//				sourceYzArchive.setBookDepartment(Integer.parseInt(archiveSaveReq.getBook_department()));
//			}
//			// book_date
//			sourceYzArchive.setBookDate(ServiceUtil.getDateFromSeconds(archiveSaveReq.getBook_date()));
//			// book_start
//			sourceYzArchive.setBookStart(ServiceUtil.timeToInt(archiveSaveReq.getBook_start()));
//			// book_end
//			sourceYzArchive.setBookEnd(ServiceUtil.timeToInt(archiveSaveReq.getBook_end()));
//			// book_desc
//			sourceYzArchive.setBookDesc(archiveSaveReq.getBook_desc());
//			// 下发短信标识
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
//		// 清空更新时间
//		sourceYzArchive.setUpdateTime(null);
//		// 是否完成,默认为N
//		sourceYzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_N);
//		// 创建时间
//		sourceYzArchive.setCreateTime(new Date());
//		// 清空id
//		sourceYzArchive.setId(null);
//		// 保存健康档案
//		if (yzArchiveDaoExt.insert(sourceYzArchive) == 1) {
//			logger.info("【健康档案新建保存】新建问诊[archive_id=" + sourceYzArchive.getId() + "]成功...");
//			
//			// 回访成功之后，删除缓存中的健康档案列表
//			String key = MemcacheKeyPropsReader.getProperties("YZ_USER_ARCHIVE_KEY") + sourceYzArchive.getUid() ;
//			try {
//				if (MemcachedUtils.delete(key)) {
//					logger.info("【MemberCache缓存更新】从缓存中删除用户健康档案列表成功,key=[" + key + "]");
//				} else {
//					logger.warn("【MemberCache缓存更新】从缓存中删除用户健康档案列表失败,key=[" + key + "]");
//				}
//			} catch (Exception e) {
//				logger.error("【MemberCache缓存更新】从缓存中删除用户健康档案列表异常,key=[" + key + "]",e);
//			}
//			
//			if (sendFlag){
//				// 查询用户信息
//				YzUser yzUser = yzUserDaoExt.getYzUserByKey(sourceYzArchive.getUid());
//				if (yzUser != null) {
//					// 即时发送预约短信
//					msgGateway.sendMsg(ServiceContant.SMS_BOOK_BACK, yzUser.getMobile(), sourceYzArchive.getBookDate(),
//							sourceYzArchive.getBookStart(),sourceYzArchive.getBookEnd());
//				} else {
//					logger.warn("【健康档案新建保存】查询用户信息不存在[uid=" + sourceYzArchive.getUid() + "],不会发送预约短信和提醒短信，请注意...");
//				}
//			}
//			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", sourceYzArchive.getId());
//		} else {
//			logger.warn("【健康档案新建保存】数据库操作失败");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "保存失败", null);
//		}
//	}
//}
