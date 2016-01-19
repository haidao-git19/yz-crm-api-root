//package com.yao.yz.crm.service.impl.archive.v2;
//
//import java.util.Date;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//import com.yao.yz.crm.service.interf.archive.v2.ArchiveUpdateService;
//import com.yao.yz.crm.service.util.DBValidateUtil;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveUpdateReq;
//import com.yao.yz.util.cache.merberCache.MemcachedUtils;
//import com.yao.yz.util.cache.redis.CacheFacade;
//import com.yao.yz.util.common.MemcacheKeyPropsReader;
//import com.yao.yz.util.common.RedisKeyPropsReader;
//
///**
// * 更新健康档案
// * 
// * @version 2.0 新增
// * @author wuwenjun
// *
// */
//@Service
//public class ArchiveUpdateServiceImpl implements ArchiveUpdateService{
//
//	private static final Logger logger = Logger.getLogger(ArchiveUpdateServiceImpl.class);
//	
//	@Autowired
//	private CrmYzArchiveDaoExt yzArchiveDaoExt;
//	
//	@Autowired
//	private CacheFacade cacheFacade;
//	
//	@Override
//	public BasicResVo updateArchive(ArchiveUpdateReq archiveUpdateReq) {
//		
//		String loggerTitle = SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE");
//		
//		// 请求参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		archiveUpdateReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		// 查询健康档案详情
//		YzArchive yzArchive = yzArchiveDaoExt.getYzArchiveByKey(Integer.parseInt(archiveUpdateReq.getArchive_id()));
//		if (null == yzArchive) {
//			logger.warn(loggerTitle + "未查询到指定的健康档案[" + archiveUpdateReq.toString() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00000"), null);
//		}
//		
//		// 字典参数检查
//		if (!DBValidateUtil.checkDoctor(Integer.parseInt(archiveUpdateReq.getDoctor_id()))) {
//			logger.warn(loggerTitle + "未查询到指定的医生[" + archiveUpdateReq.toString() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_DOCTOR_00000"), null);
//		}
//		if (!DBValidateUtil.checkDepartment(Integer.parseInt(archiveUpdateReq.getGuest_department()))) {
//			logger.warn(loggerTitle + "未查询到指定的科室[" + archiveUpdateReq.toString() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_METADATA_00000"), null);
//		}
//		
//		// 更新健康档案详情
//		updateArchive(yzArchive, archiveUpdateReq,basicResVo);
//		
//		// 刷新缓存
//		refreshArchive(yzArchive);
//		
//		return basicResVo;
//	}
//	
//	/**
//	 * 更新健康档案
//	 * @param yzArchive
//	 * @param archiveUpdateReq
//	 * @param basicResVo
//	 */
//	private void updateArchive(YzArchive yzArchive, ArchiveUpdateReq archiveUpdateReq,BasicResVo basicResVo) {
//		// 拼接更新数据
//		yzArchive.setNickName(archiveUpdateReq.getNick_name());
//		yzArchive.setSexComment(archiveUpdateReq.getSex_comment());
//		if (StringUtils.isNotBlank(archiveUpdateReq.getAge())) {
//			yzArchive.setAge(Integer.parseInt(archiveUpdateReq.getAge()));
//			yzArchive.setAgeType(Integer.parseInt(archiveUpdateReq.getAge_type()));
//		}
//		yzArchive.setGuestDepartment(Integer.parseInt(archiveUpdateReq.getGuest_department()));
//		yzArchive.setDoctorId(Integer.parseInt(archiveUpdateReq.getDoctor_id()));
//		yzArchive.setDoctorDesc(archiveUpdateReq.getDoctor_desc());
//		yzArchive.setDeseaseHisRecord(archiveUpdateReq.getDesease_his_record());
//		yzArchive.setInitialCheck(archiveUpdateReq.getInitial_check());
//		yzArchive.setConsult(archiveUpdateReq.getConsult());
//		if (ServiceContant.SYNCH_STATUS_Y.equals(archiveUpdateReq.getSynchronous())) {
//			yzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_Y);
//		} else {
//			yzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_N);
//		}
//		yzArchive.setUpdateTime(new Date());
//		
//		// 更新数据库
//		if (yzArchiveDaoExt.update(yzArchive) == 1) {
//			logger.info(SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE") + "更新健康档案成功[" + yzArchive.toString() + "]");
//			basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, SysPropertyReader.getValue("RET_MSG_SYS_00000"), null);
//		} else {
//			logger.info(SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE") + "更新健康档案失败[" + yzArchive.toString() + "]");
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00010"), null);
//		}
//		
//	}
//	
//	/**
//	 * 刷新健康档案缓存
//	 * @param yzArchive
//	 * @param archiveUpdateReq
//	 * @param basicResVo
//	 */
//	private void refreshArchive(YzArchive yzArchive) {
//		try {
//			// 删除memcache健康档案列表
//			String key = MemcacheKeyPropsReader.getProperties("YZ_USER_ARCHIVE_KEY") + yzArchive.getUid();
//			MemcachedUtils.delete(key);
//			logger.info(SysPropertyReader.getValue("REQNAME_MEMCACHE_FRESH") + "删除用户健康档案列表成功[key=" + key + "]");
//			
//			// 删除redis中的档案详情
//			cacheFacade.setKeyFiedlBean(RedisKeyPropsReader.getProperties("YZ_ARCHIVE_KEY"),String.valueOf(yzArchive.getId()), yzArchive, 0);
//			logger.info(SysPropertyReader.getValue("REQNAME_REDIS_FRESH") + "更新健康档案成功[archive_id=" + yzArchive.getId() + "]");
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_ARCHVIE_UPDATE") + "更新缓存信息失败", e);
//		}
//	}
//}
