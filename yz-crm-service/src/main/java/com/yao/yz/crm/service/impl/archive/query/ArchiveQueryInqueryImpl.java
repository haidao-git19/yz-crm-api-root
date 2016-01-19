//
//
//	v2.0版本以后废弃，不再使用
//
//
//package com.yao.yz.crm.service.impl.archive.query;
//
//import java.text.SimpleDateFormat;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
//import com.yao.yz.crm.persistence.dao.ext.AdminSysParameterDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.InqueryDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.YzArchiveDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.YzDoctorDaoExt;
//import com.yao.yz.crm.service.interf.archive.query.ArchiveQueryInquery;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.ServiceUtil;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.ArchiveInfoReq;
//import com.yao.yz.crm.service.vo.res.archive.detail.ArchiveInfoRes;
//
///**
// *	描述：来电清单处理问诊界面数据展示
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 8:39:24 AM
// *	@Versin 1.0
// */
//@Component
//public class ArchiveQueryInqueryImpl implements ArchiveQueryInquery{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveQueryInqueryImpl.class);
//	
//	@Autowired
//	private InqueryDaoExt inqueryDaoExt;
//	
//	@Autowired
//	private AdminSysParameterDaoExt adminSysParameterDaoExt;
//	
//	@Autowired
//	private YzArchiveDaoExt yzArchiveDaoExt;
//	
//	@Autowired
//	private YzDoctorDaoExt yzDoctorDaoExt;
//
//	@Override
//	public BasicResVo getArchiveInfo(ArchiveInfoReq archiveInfoReq) {
//		// 参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		archiveInfoReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		YzInquery inquery = null;
//		try {
//			inquery = inqueryDaoExt.getYzInqueryByKey(Integer.parseInt(archiveInfoReq.getInquery_id()));
//		} catch (Exception e) {
//			logger.error("【健康档案信息查询接口】查询来电清单[inquery_id=" + archiveInfoReq.getInquery_id() + "]异常", e);
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "查询来电清单异常", null);
//		}
//		
//		if (inquery != null){
//			if (inquery.getArchiveId() != null) {
//				ArchiveInfoRes archiveInfoRes = new ArchiveInfoRes();
//				YzArchive yzArchive = null;
//				try {
//					yzArchive = yzArchiveDaoExt.getYzArchiveByKey(inquery.getArchiveId());
//				} catch (Exception e) {
//					logger.error("【健康档案信息查询接口】查询健康档案异常[archive_id=" + inquery.getArchiveId() + "]",e);
//					return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "查询健康档案异常", null);
//				}
//				if (yzArchive != null) {
//					// archive_id
//					archiveInfoRes.getArchive_info().setArchive_id(yzArchive.getId());
//					// self_desc
//					archiveInfoRes.getArchive_info().setSelf_desc(yzArchive.getSelfDesc());
//					// 图片域名
//					String imageHost = adminSysParameterDaoExt.getImageHost();
//					// photos
//					if (StringUtils.isNotBlank(yzArchive.getPhotos())) {
//						String[] prescribePhotos = yzArchive.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//						for (String imageUrl:prescribePhotos) {
//							archiveInfoRes.getArchive_info().getPhotos().add(imageHost + imageUrl);
//						}
//					}
//					// quest_type
//					archiveInfoRes.getArchive_info().setQuest_type(yzArchive.getQuestType());
//					// guest_department
//					archiveInfoRes.getArchive_info().setGuest_department(yzArchive.getGuestDepartment());
//					// doctor_id
//					archiveInfoRes.getArchive_info().setDoctor_id(yzArchive.getDoctorId());
//					//	illnessKey
//					archiveInfoRes.getArchive_info().setIllness_key(yzArchive.getIllnessKey());
//					//	consult
//					archiveInfoRes.getArchive_info().setConsult(yzArchive.getConsult());
//					// 预约私人健康顾问，预约科室为返回为-1，预约医生返回为0；正常预约返回正常值
//					if (yzArchive.getBookDoctorId() !=null && yzArchive.getBookDoctorId() == 0) {
//						archiveInfoRes.getArchive_info().setBook_doctor_id(0);
//						archiveInfoRes.getArchive_info().setBook_department(-1);
//					} else {
//						archiveInfoRes.getArchive_info().setBook_doctor_id(yzArchive.getBookDoctorId());
//						archiveInfoRes.getArchive_info().setBook_department(yzArchive.getBookDepartment());
//					}
//					// bookDate
//					if (yzArchive.getBookDate() != null) {
//						archiveInfoRes.getArchive_info().setBook_date(yzArchive.getBookDate().getTime());
//					}
//					//	bookStart
//					if (yzArchive.getBookStart() != null) {
//						archiveInfoRes.getArchive_info().setBook_start(ServiceUtil.intToTime(yzArchive.getBookStart()));
//					}
//					//	bookEnd
//					if (yzArchive.getBookEnd() != null) {
//						archiveInfoRes.getArchive_info().setBook_end(ServiceUtil.intToTime(yzArchive.getBookEnd()));
//					}
//					//	bookDesc
//					archiveInfoRes.getArchive_info().setBook_desc(yzArchive.getBookDesc());
//					
//					// create_time
//					if (inquery.getCreateTime() != null) {
//						archiveInfoRes.getArchive_info().setCreate_time(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(inquery.getCreateTime()));
//					}
//					// source_id
//					archiveInfoRes.getArchive_info().setSource_id(yzArchive.getSourceId());
//					
//					// inquery_id
//					archiveInfoRes.getArchive_info().setInquery_id(inquery.getId());
//					
//					return new BasicResVo().processData(ServiceContant.RET_CODE_SUCCESS, "ok", archiveInfoRes);
//				} else {
//					logger.warn("【健康档案信息查询接口】未查询到健康档案[archive_id=" + inquery.getArchiveId() +"],请注意...");
//					return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "未查询到健康档案", null);
//				}
//			} else {
//				ArchiveInfoRes archiveInfoRes = new ArchiveInfoRes();
//				// self_desc
//				archiveInfoRes.getArchive_info().setSelf_desc(inquery.getSelfDesc());
//				// 图片域名地址
//				String imageHost = adminSysParameterDaoExt.getImageHost();
//				// photos
//				if (StringUtils.isNotBlank(inquery.getPhotos())) {
//					String[] prescribePhotos = inquery.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//					for (String imageUrl:prescribePhotos) {
//						archiveInfoRes.getArchive_info().getPhotos().add(imageHost + imageUrl);
//					}
//				}
//				// quest_type
//				archiveInfoRes.getArchive_info().setQuest_type(ServiceContant.QUEST_TYPE_F);
//				// guest_department
//				archiveInfoRes.getArchive_info().setGuest_department(inquery.getGuestDepartment());
//				// doctor_id,根据当前登录的用户id查询医生信息，如果配置了坐席号与医生关系，则返回医生编号，否则返回0
//				try {
//					YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorBySeat(archiveInfoReq.getUser_name());
//					if (yzDoctor == null) {
//						archiveInfoRes.getArchive_info().setDoctor_id(0);
//					} else {
//						archiveInfoRes.getArchive_info().setDoctor_id(yzDoctor.getId());
//					}
//				} catch (Exception e) {
//					logger.error("【健康档案信息查询接口】查询坐席[seat=" + archiveInfoReq.getUser_name() + "]医生信息异常", e);
//					return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "查询坐席信息失败", null);
//				}
//				// inquery_id
//				archiveInfoRes.getArchive_info().setInquery_id(inquery.getArchiveId());
//				
//				// create_time
//				if (inquery.getCreateTime() != null) {
//					archiveInfoRes.getArchive_info().setCreate_time(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(inquery.getCreateTime()));
//				}
//				
//				return new BasicResVo().processData(ServiceContant.RET_CODE_SUCCESS, "ok", archiveInfoRes);
//			}
//		} else {
//			logger.error("【健康档案信息查询接口】未查询到来电清单[inquiry_id=" + archiveInfoReq.getInquery_id() + "]");
//			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, "未查询到来电清单", null);
//		}
//	}
//
//}
