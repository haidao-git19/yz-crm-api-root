//package com.yao.yz.crm.service.impl.archive.query;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//import com.yao.yz.crm.service.interf.archive.query.ArchiveQueryBack;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.ArchiveInfoReq;
//import com.yao.yz.crm.service.vo.res.archive.detail.ArchiveInfoRes;
//
///**
// *	描述：回访界面数据展示
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 8:39:01 AM
// *	@Versin 1.0
// */
//@Component
//public class ArchiveQueryBackImpl implements ArchiveQueryBack{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveQueryBackImpl.class);
//	
//	@Autowired
//	private CrmYzArchiveDaoExt yzArchiveDaoExt;
//	
//	@Autowired
//	private CrmAdminSysParameterDaoExt adminSysParameterDaoExt;
//
//	@Override
//	public BasicResVo getArchiveInfo(ArchiveInfoReq archiveInfoReq) {
//		
//		// 参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		archiveInfoReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		YzArchive yzArchive = null;
//		try {
//			yzArchive = yzArchiveDaoExt.getYzArchiveByKey(Integer.parseInt(archiveInfoReq.getArchive_id()));
//		} catch (Exception e) {
//			logger.error("【健康档案信息接口】查询健康档案信息异常[archiveId=" + archiveInfoReq.getArchive_id() + "]",e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "查询数据库异常", null);
//		}
//		
//		if (yzArchive != null) {
//			ArchiveInfoRes archiveInfoRes = new ArchiveInfoRes();
//			//	archiveId
//			archiveInfoRes.getArchive_info().setArchive_id(yzArchive.getId());
//			//	selfDesc
//			archiveInfoRes.getArchive_info().setSelf_desc(yzArchive.getSelfDesc());
//			//	图片域名地址
//			String imageHost = adminSysParameterDaoExt.getImageHost();
//			// photos
//			if (StringUtils.isNotBlank(yzArchive.getPhotos())) {
//				String[] photos = yzArchive.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//				for (String imageUrl:photos) {
//					archiveInfoRes.getArchive_info().getPhotos().add(imageHost + imageUrl);
//				}
//			}
//			
//			// 这段代码是为了兼容1.5之前的图片展示
//			if (StringUtils.isNotBlank(yzArchive.getPrescribePhotos())) {
//				String[] prescribePhotos = yzArchive.getPrescribePhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//				for (String imageUrl:prescribePhotos) {
//					archiveInfoRes.getArchive_info().getPhotos().add(imageHost + imageUrl);
//				}
//			}
//			
//			//	questType
//			archiveInfoRes.getArchive_info().setQuest_type(ServiceContant.QUEST_TYPE_R);
//			/*
//			 * 处理方式，如果预约的是私人健康顾问，则不自动带出处理科室和私人医生，否则自动带出预约的处理科室和预约医生
//			 */
//			if (0 == yzArchive.getBookDoctorId()) {
//				archiveInfoRes.getArchive_info().setGuest_department(0);
//				archiveInfoRes.getArchive_info().setDoctor_id(0);
//			} else {
//				archiveInfoRes.getArchive_info().setGuest_department(yzArchive.getBookDepartment());
//				archiveInfoRes.getArchive_info().setDoctor_id(yzArchive.getBookDoctorId());
//			}
//			
//			// source_id
//			archiveInfoRes.getArchive_info().setSource_id(yzArchive.getSourceId());
//			
//			/*
//			//	illnessKey
//			archiveInfoRes.getArchive_info().setIllness_key(yzArchive.getIllnessKey());
//			//	consult
//			archiveInfoRes.getArchive_info().setConsult(yzArchive.getConsult());
//			//	bookDepartment
//			archiveInfoRes.getArchive_info().setBook_department(yzArchive.getBookDepartment());
//			//	bookDoctorId
//			archiveInfoRes.getArchive_info().setBook_doctor_id(yzArchive.getBookDoctorId());
//			//	bookDate,格式：yyyyy-MM-dd
//			if (yzArchive.getBookDate() != null) {
//				archiveInfoRes.getArchive_info().setBook_date(new SimpleDateFormat("yyyy-MM-dd").format(yzArchive.getBookDate()));
//			}
//			//	bookStart
//			if (yzArchive.getBookStart() != null) {
//				archiveInfoRes.getArchive_info().setBook_start(ServiceUtil.intToTime(yzArchive.getBookStart()));
//			}
//			//	bookEnd
//			if (yzArchive.getBookEnd() != null) {
//				archiveInfoRes.getArchive_info().setBook_end(ServiceUtil.intToTime(yzArchive.getBookEnd()));
//			}
//			//	bookDesc
//			archiveInfoRes.getArchive_info().setBook_desc(yzArchive.getBookDesc());
//			*/
//			return new BasicResVo().processData(ServiceContant.RET_CODE_SUCCESS, "ok", archiveInfoRes);
//		} else {
//			logger.warn("【健康档案信息接口】未查询到健康档案[archiveId=" + archiveInfoReq.getArchive_id() + "]，请注意...");
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "未查询到健康档案", null);
//		}
//	}
//
//}
