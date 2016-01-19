//
// v3.0版本移除
//
//package com.yao.yz.crm.service.impl.inquery.v2;
//
//import java.util.Date;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
//import com.yao.yz.crm.persistence.dao.ext.AdminSysParameterDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.InqueryDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.YzDoctorDaoExt;
//import com.yao.yz.crm.service.interf.inquery.InqueryStatusService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.inquery.InqueryHandleReq;
//import com.yao.yz.crm.service.vo.req.inquery.InqueryStatusReq;
//import com.yao.yz.crm.service.vo.res.inquery.InqueryHandleRes;
//import com.yao.yz.crm.service.vo.res.inquery.InqueryStatusRes;
//
///**
// * 诊单状态服务
// * @version 2.0
// * @author wuwenjun
// *
// */
//@Service
//public class InqueryStatusServiceImpl implements InqueryStatusService{
//	
//	private static final Logger logger = Logger.getLogger(InqueryStatusServiceImpl.class);
//	
//	@Autowired
//	private InqueryDaoExt inqueryDaoExt;
//	
//	@Autowired
//	private YzDoctorDaoExt yzDoctorDaoExt;
//	
//	@Autowired
//	private AdminSysParameterDaoExt adminSysParameterDaoExt;
//
//	@Override
//	public BasicResVo getInqueryStatus(InqueryStatusReq inqueryStatusReq) {
//		// 参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		inqueryStatusReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_STATUS") + "请求参数校验失败[" + inqueryStatusReq.toString() + "]");
//			return basicResVo;
//		}
//		
//		// 查询指定的诊单
//		YzInquery yzInquery = inqueryDaoExt.getYzInqueryByKey(Integer.valueOf(inqueryStatusReq.getInquery_id()));
//		if (null == yzInquery) {
//			logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_STATUS") + "未查询到诊单[" + inqueryStatusReq.getInquery_id() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00000"), null);
//		}
//		
//		// 拼接返回数据模型
//		InqueryStatusRes inqueryStatusRes = new InqueryStatusRes();
//		inqueryStatusRes.getInquery_status().setInquery_id(yzInquery.getId());
//		inqueryStatusRes.getInquery_status().setOp_account_name(yzInquery.getOpAccountName());
//		inqueryStatusRes.getInquery_status().setOp_doctor_name(yzInquery.getOpDoctorName());
//		inqueryStatusRes.getInquery_status().setProcess_flag(yzInquery.getProcessFlag());
//		inqueryStatusRes.getInquery_status().setFinish_flag(yzInquery.getFinishFlag());
//		basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, SysPropertyReader.getValue("RET_MSG_SYS_00000"), inqueryStatusRes);
//		
//		return basicResVo;
//	}
//
//	@Override
//	public BasicResVo handleInqueryStatus(InqueryHandleReq inqueryHandleReq) {
//		// 请求参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		inqueryHandleReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "请求参数校验失败[" + inqueryHandleReq.toString() + "]");
//			return basicResVo;
//		}
//		
//		// 检查请求发起人的角色是否为医生
//		YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorByAccount(inqueryHandleReq.getUser_name());
//		if (null == yzDoctor || ServiceContant.DOCTOR_TYPE_3 == yzDoctor.getDoctorType()) {
//			/********** 登录帐号不是医生 **********/
//			logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "登录帐号非医生帐号[user_name=" + inqueryHandleReq.getUser_name() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00001"), null);
//		} else {
//			/************ 登录帐号为医生 ************/
//			// 检查诊单是否存在
//			YzInquery yzInquery = inqueryDaoExt.getYzInqueryByKey(Integer.parseInt(inqueryHandleReq.getInquery_id()));
//			if (null == yzInquery) {
//				logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "未查询到诊单[" + inqueryHandleReq.getInquery_id() + "]");
//				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00000"), null);
//			}
//			
//			// 检查诊单状态
//			if (ServiceContant.INQUERY_STATUS_N.equals(yzInquery.getFinishFlag()) || ServiceContant.INQUERY_STATUS_D.equals(yzInquery.getFinishFlag())) {
//				// 诊单状态为待处理或者处理中
//				try {
//					yzInquery.setFinishFlag(ServiceContant.INQUERY_STATUS_D);
//					yzInquery.setUpdateTime(new Date());
//					yzInquery.setOpAccountName(inqueryHandleReq.getUser_name());
//					yzInquery.setOpDoctorName(yzDoctor.getSecondName() + yzDoctor.getFirstName());
//					yzInquery.setOpAccountId(yzDoctor.getId());
//					
//					if (inqueryDaoExt.update(yzInquery) == 1){
//						logger.info(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "更新诊单状态成功[" + yzInquery.toString() + "]");
//						
//						// 添加诊单返回数据
//						InqueryHandleRes inqueryHandleRes = processData(yzInquery,yzDoctor.getId());
//						
//						return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, SysPropertyReader.getValue("RET_MSG_SYS_00000"), inqueryHandleRes);
//					} else {
//						logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "更新诊单状态失败[" + yzInquery.toString() + "]");
//						return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00003"), null);
//					}
//				} catch (Exception e) {
//					logger.error(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "更新诊单状态异常[" + yzInquery.toString() + "]", e);
//					return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00003"), null);
//				}
//			} else {
//				// 诊单状态为已完成或已取消
//				logger.warn(SysPropertyReader.getValue("REQNAME_INQUERY_HANDLE") + "诊单状态为" + yzInquery.getFinishFlag() + ",不允许修改诊单状态");
//				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00002"), null);
//			}
//		}
//	}
//	
//	/**
//	 * 填充返回数据
//	 * @param basicResVo
//	 * @param yzInquery
//	 */
//	private InqueryHandleRes processData(YzInquery yzInquery,Integer doctorId) {
//		/** 拼接返回数据模型 **/
//		InqueryHandleRes inqueryHandleRes = new InqueryHandleRes();
//		inqueryHandleRes.getInquery_info().setInquery_id(yzInquery.getId());
//		inqueryHandleRes.getInquery_info().setUid(yzInquery.getUid());
//		// 拼接图片
//		String imageHost = adminSysParameterDaoExt.getImageHost();
//		if (StringUtils.isNotBlank(yzInquery.getPhotos())) {
//			String[] photos = yzInquery.getPhotos().split(ServiceContant.IMAGE_SPLIT_FLAG);
//			for (String imageUrl:photos) {
//				inqueryHandleRes.getInquery_info().getPhotos().add(imageHost + imageUrl);
//			}
//		}
//		inqueryHandleRes.getInquery_info().setSelf_desc(yzInquery.getSelfDesc());
//		inqueryHandleRes.getInquery_info().setGuest_department(yzInquery.getGuestDepartment());
//		inqueryHandleRes.getInquery_info().setAge(yzInquery.getAge());
//		inqueryHandleRes.getInquery_info().setAge_type(yzInquery.getAgeType());
//		inqueryHandleRes.getInquery_info().setSex_comment(yzInquery.getSex());
//		inqueryHandleRes.getInquery_info().setOp_account_name(yzInquery.getOpAccountName());
//		inqueryHandleRes.getInquery_info().setOp_doctor_name(yzInquery.getOpDoctorName());
//		inqueryHandleRes.getInquery_info().setFinish_flag(yzInquery.getFinishFlag());
//		inqueryHandleRes.getInquery_info().setDoctor_id(doctorId);
//		inqueryHandleRes.getInquery_info().setCreate_time(yzInquery.getCreateTime().getTime());
//		
//		return inqueryHandleRes;
//	}
//}
