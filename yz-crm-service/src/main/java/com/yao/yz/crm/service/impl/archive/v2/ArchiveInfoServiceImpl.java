//package com.yao.yz.crm.service.impl.archive.v2;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.expression.spel.ast.NullLiteral;
//import org.springframework.stereotype.Service;
//
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzRecommendDrugsDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//import com.yao.yz.admin.yzadmin.persistence.model.YzRecommendDrugs;
//import com.yao.yz.crm.service.interf.archive.v2.ArchiveInfoService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveInfoReq;
//import com.yao.yz.crm.service.vo.res.archive.v2.ArchiveDetailRes;
//import com.yao.yz.model.v3.biz.drug.DrugBean;
///**
// * 健康档案详情查询接口
// * @author wuwenjun
// *
// */
//@Service
//public class ArchiveInfoServiceImpl implements ArchiveInfoService{
//	
//	private static final Logger logger = Logger.getLogger(ArchiveInfoServiceImpl.class);
//	
//	@Autowired
//	private CrmYzArchiveDaoExt yzArchiveDaoExt;
//	
//	@Autowired
//	private CrmAdminSysParameterDaoExt adminSysParameterDaoExt;
//	
//	@Autowired
//	private CrmYzRecommendDrugsDaoExt crmYzRecommendDrugsDaoExt;
//
//	@Override
//	public BasicResVo getArchiveDetail(ArchiveInfoReq archiveInfoReq) {
//		String loggerTitle = SysPropertyReader.getValue("REQNAME_ARCHIVE_DETAIL");
//		
//		// 参数检查
//		BasicResVo basicResVo = new BasicResVo();
//		archiveInfoReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			logger.warn(loggerTitle + "请求参数校验失败[" + archiveInfoReq.toString() + "]");
//			return basicResVo;
//		}
//		
//		// 查询健康档案详情
//		YzArchive yzArchive = yzArchiveDaoExt.getYzArchiveByKey(Integer.parseInt(archiveInfoReq.getArchive_id()));
//		if (yzArchive == null) {
//			logger.warn(loggerTitle + "未查询到指定的健康档案[" + archiveInfoReq.getArchive_id() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00000"), null);
//		}
//		
//		// 拼接返回数据模型
//		ArchiveDetailRes archiveDetailRes = new ArchiveDetailRes();
//		archiveDetailRes.getArchive_info().setArchive_id(yzArchive.getId());
//		archiveDetailRes.getArchive_info().setNick_name(yzArchive.getNickName());
//		archiveDetailRes.getArchive_info().setSex_comment(yzArchive.getSexComment());
//		archiveDetailRes.getArchive_info().setAge(yzArchive.getAge());
//		archiveDetailRes.getArchive_info().setAge_type(yzArchive.getAgeType());
//		archiveDetailRes.getArchive_info().setSelf_desc(yzArchive.getSelfDesc());
//		
//		// 拼接图片
//		String imageHost = adminSysParameterDaoExt.getImageHost();
//		if (StringUtils.isNotBlank(yzArchive.getPhotos())) {
//			String[] photos = yzArchive.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//			for (String imageUrl:photos) {
//				archiveDetailRes.getArchive_info().getPhotos().add(imageHost + imageUrl);
//			}
//		}
//		
//		archiveDetailRes.getArchive_info().setGuest_department(yzArchive.getGuestDepartment());
//		archiveDetailRes.getArchive_info().setDoctor_id(yzArchive.getDoctorId());
//		archiveDetailRes.getArchive_info().setQuest_type(yzArchive.getQuestType());
//		archiveDetailRes.getArchive_info().setDoctor_desc(yzArchive.getDoctorDesc());
//		archiveDetailRes.getArchive_info().setDesease_his_record(yzArchive.getDeseaseHisRecord());
//		archiveDetailRes.getArchive_info().setInitial_check(yzArchive.getInitialCheck());
//		archiveDetailRes.getArchive_info().setConsult(yzArchive.getConsult());
//		archiveDetailRes.getArchive_info().setInquery_id(yzArchive.getInqueryId());
//		
//		// 查询用药建议
//		List<DrugBean> drugBeans = new ArrayList<DrugBean>();
//		if (null != yzArchive.getInqueryId()) {
//			
//		}
//		
//		
//		logger.info(loggerTitle + "查询健康档案成功[" + archiveDetailRes.toString() + "]");
//		return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, SysPropertyReader.getValue("RET_MSG_SYS_00000"), archiveDetailRes);
//	}
//
//	@Override
//	public void getArchiveDetail(
//			com.yao.yz.crm.service.vo.req.archive.ArchiveInfoReq archiveInfoReq) {
//		// TODO Auto-generated method stub
//		
//	}
//}
