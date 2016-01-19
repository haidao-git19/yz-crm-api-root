//package com.yao.yz.crm.service.impl.archive.query;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
//import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzArchiveExt;
//import com.yao.yz.crm.service.interf.archive.query.ArchiveListService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveListReq;
//import com.yao.yz.crm.service.vo.res.archive.v2.ArchiveListRes;
//
///**
// *	描述：健康档案列表数据展示
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 9:23:57 AM
// *	@Versin 1.0
// *	modified by wuwenjun 2015.12.08  取消根档案、子档案查询 
// */
//@Component
//public class ArchiveListServiceImpl implements ArchiveListService{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveListServiceImpl.class);
//	
//	@Autowired
//	private CrmAdminSysParameterDaoExt adminSysParameterDaoExt;
//	
//	@Autowired
//	private CrmYzArchiveDaoExt yzArchiveDaoExt;
//	
//	@Override
//	public BasicResVo archiveList(ArchiveListReq archiveListReq) {
//		
//		// 参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		archiveListReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//
//		/************************************** 查询指定科室、指定用户的健康档案列表  **************************************/
//		// 根据处理科室编号、用户uid查询健康档案列表，处理科室编号为0时标识查询所有科室 
//		List<CrmYzArchiveExt> archiveExts = null;
//		if ("0".equals(archiveListReq.getDepartment_id())) {
//			archiveExts = yzArchiveDaoExt.archiveList(Integer.parseInt(archiveListReq.getUid()));
//		} else {
//			archiveExts = yzArchiveDaoExt.selectArchiveExts(Integer.parseInt(archiveListReq.getUid()), Integer.parseInt(archiveListReq.getDepartment_id()));
//		}
//		
//		// 拼接返回数据模型
//		List<ArchiveListRes> archiveListRes = new ArrayList<ArchiveListRes>();
//		if (archiveExts != null && archiveExts.size() > 0) {
//			for (CrmYzArchiveExt yzArchiveExt : archiveExts) {
//				ArchiveListRes model = new ArchiveListRes();
//				model.setArchive_id(yzArchiveExt.getId());
//				model.setCreate_time(yzArchiveExt.getCreateTime().getTime());
//				model.setGuest_department_name(yzArchiveExt.getQuestDepName());
//				
//				// 拼接医生信息
//				YzDoctor yzDoctor = yzArchiveExt.getQuestDoctor();
//				if (yzDoctor != null) {
//					model.setDoctor_name((yzDoctor.getSecondName() == null ? "":yzDoctor.getSecondName()) + (yzDoctor.getFirstName() == null ? "" : yzDoctor.getFirstName()));
//				}
//				
//				model.setNick_name(yzArchiveExt.getNickName());
//				model.setSex_comment(yzArchiveExt.getSexComment());
//				model.setAge(yzArchiveExt.getAge());
//				model.setAge_type(yzArchiveExt.getAgeType());
//				model.setSelf_desc(yzArchiveExt.getSelfDesc());
//				
//				// 拼接图片地址
//				String imageHost = adminSysParameterDaoExt.getImageHost();
//				if (StringUtils.isNotBlank(yzArchiveExt.getPhotos())) {
//					String[] photos = yzArchiveExt.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//					for (String imageUrl:photos) {
//						model.getPhotos().add(imageHost + imageUrl);
//					}
//				}
//				if (StringUtils.isNotBlank(yzArchiveExt.getPrescribePhotos())) {
//					String[] photos = yzArchiveExt.getPrescribePhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//					for (String imageUrl:photos) {
//						model.getPhotos().add(imageHost + imageUrl);
//					}
//				}
//				
//				model.setDoctor_desc(yzArchiveExt.getDoctorDesc());
//				model.setDesease_his_record(yzArchiveExt.getDeseaseHisRecord());
//				model.setInitial_check(yzArchiveExt.getInitialCheck());
//				model.setConsult(yzArchiveExt.getConsult());
//				model.setInquery_id(yzArchiveExt.getInqueryId());
//				
//				archiveListRes.add(model);
//			}
//		}
//		
//		logger.info(SysPropertyReader.getValue("REQNAME_ARCHIVE_LIST") + "查询健康档案成功[" + archiveListReq.toString() + "]");
//		
//		// 返回json格式字符串
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("archive_list", archiveListRes);
//		
//		return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, SysPropertyReader.getValue("RET_MSG_SYS_00000"), jsonObject);
//		
///*		
// * 
// * 		以下代码为V1.5版本
// * 
// * 
//		ArchiveListRes archiveList = new ArchiveListRes();
//		try {
//			List<YzArchiveExt> sourceArchiveExts = null;
//			if ("0".equals(archiveListReq.getDepartment_id())) {// 全科室查询
//				sourceArchiveExts = yzArchiveDaoExt.archiveList(Integer.parseInt(archiveListReq.getUid()));
//			} else {// 指定科室查询
//				sourceArchiveExts = yzArchiveDaoExt.archiveListByDep(Integer.parseInt(archiveListReq.getUid()), 
//						Integer.parseInt(archiveListReq.getDepartment_id()));
//			}
//			if (sourceArchiveExts != null) {
//				for (YzArchiveExt sourceArchiveExt : sourceArchiveExts) {
//					ArchiveInfoList archiveInfoList = new ArchiveInfoList();
//					// 源健康档案编号
//					archiveInfoList.setArchive_id(sourceArchiveExt.getId());
//					// 源健康档案标题
//					archiveInfoList.setIllness_key(sourceArchiveExt.getIllnessKey());
//					// 源健康档案创建时间
//					archiveInfoList.setCreate_time(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(sourceArchiveExt.getCreateTime()));
//					
//					// 源健康档案下关联的健康档案列表
//					List<YzArchiveExt> yzArchiveExts = yzArchiveDaoExt.archiveListBySource(sourceArchiveExt.getId());
//					
//					// 源健康档案和子档案同时加入列表
//					archiveInfoList.getArchive_infos().add(handleArchiveListInfo(sourceArchiveExt));
//					if (yzArchiveExts != null) {
//						for (YzArchiveExt yzArchiveExt:yzArchiveExts) {
//							archiveInfoList.getArchive_infos().add(handleArchiveListInfo(yzArchiveExt));
//						}
//					}
//					
//					archiveList.getArchive_list().add(archiveInfoList);
//				}
//			}
//			logger.info("【健康档案列表查询请求】查询用户[uid=" + archiveListReq.getUid() + "]健康档案列表信息成功...");
//			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", archiveList);
//		} catch (Exception e) {
//			logger.error("【健康档案列表查询请求】查询健康档案列表信息异常[uid=" + archiveListReq.getUid() + "]",e);
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "查询数据库异常", null);
//		}
//*/		
//	}
//	
//	
///*	
// * 
// * 以下代码为1.5版本
// * 
// * 
//	*//**
//	 * 处理档案详情数据
//	 * @param yzArchiveExt
//	 * @param archiveListInfo
//	 *//*
//	private ArchiveListInfo handleArchiveListInfo(YzArchiveExt yzArchiveExt) {
//		ArchiveListInfo archiveInfo = new ArchiveListInfo();
//		// archiveId
//		archiveInfo.setArchive_id(yzArchiveExt.getId());
//		
//		// createTime,格式：yyyy-MM-dd-HH:mm:ss
//		archiveInfo.setCreate_time(new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(yzArchiveExt.getCreateTime()));
//		
//		// selfDesc
//		archiveInfo.setSelf_desc(yzArchiveExt.getSelfDesc());
//		
//		// 图片域名地址
//		String imageHost = adminSysParameterDaoExt.getImageHost();
//		
//		// photos
//		if (StringUtils.isNotBlank(yzArchiveExt.getPhotos())) {
//			String[] photos = yzArchiveExt.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//			for (String imageUrl:photos) {
//				archiveInfo.getPhotos().add(imageHost + imageUrl);
//			}
//		}
//		
//		// prescribePhotos
//		if (StringUtils.isNotBlank(yzArchiveExt.getPrescribePhotos())) {
//			String[] prescribePhotos = yzArchiveExt.getPrescribePhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//			for (String imageUrl:prescribePhotos) {
//				archiveInfo.getPhotos().add(imageHost + imageUrl);
//			}
//		}
//		
//		// questType
//		if (ServiceContant.QUEST_TYPE_F.equals(yzArchiveExt.getQuestType())) {
//			archiveInfo.setQuest_type(ServiceContant.QUEST_RES_F);
//		} else if (ServiceContant.QUEST_TYPE_R.equals(yzArchiveExt.getQuestType())) {
//			archiveInfo.setQuest_type(ServiceContant.QUEST_RES_R);
//		} else if (ServiceContant.QUEST_TYPE_S.equals(yzArchiveExt.getQuestType())) {
//			archiveInfo.setQuest_type(ServiceContant.QUEST_RES_S);
//		}
//		
//		// guestDepartment
//		archiveInfo.setGuest_department(yzArchiveExt.getQuestDepName());
//		
//		// doctorInfo
//		if (yzArchiveExt.getQuestDoctor() != null) {
//			archiveInfo.setDoctor_info(yzArchiveExt.getQuestDoctor().getSecondName() + yzArchiveExt.getQuestDoctor().getFirstName());
//		}
//		
//		// illnessKey
//		archiveInfo.setIllness_key(yzArchiveExt.getIllnessKey());
//		
//		// consult
//		archiveInfo.setConsult(yzArchiveExt.getConsult());
//		
//		// notice
//		if (yzArchiveExt.getBookDoctorId() != null) {
//			String notice = new SimpleDateFormat("yyyy-MM-dd").format(yzArchiveExt.getBookDate()) 
//					+ " " + ServiceUtil.intToTime(yzArchiveExt.getBookStart())
//					+ "-" + ServiceUtil.intToTime(yzArchiveExt.getBookEnd())
//					+ " "
//					+ (yzArchiveExt.getBookDoctorId() != 0 ? (
//							yzArchiveExt.getBookDoctor().getSecondName() + yzArchiveExt.getBookDoctor().getFirstName()) : "私人健康顾问")
//					+ " 进行 " + yzArchiveExt.getBookDesc();
//			archiveInfo.setNotice(notice);
//		}
//		return archiveInfo;
//	}
//*/
//}
