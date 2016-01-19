//package com.yao.yz.crm.service.impl.archive.v2;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmInqueryDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzDoctorDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
//import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
//import com.yao.yz.crm.service.interf.archive.v2.ArchiveSaveService;
//import com.yao.yz.crm.service.util.DBValidateUtil;
//import com.yao.yz.crm.service.util.HttpUtils;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveSaveReq;
//import com.yao.yz.util.cache.merberCache.MemcachedUtils;
//import com.yao.yz.util.common.Md5Utils;
//import com.yao.yz.util.common.MemcacheKeyPropsReader;
//
///**
// * 诊单保存时新增诊单对应的健康档案
// * 
// * @author wuwenjun
// * @version 2.0
// */
//@Service
//public class ArchiveSaveServiceImpl implements ArchiveSaveService {
//
//	private static final Logger logger = Logger
//			.getLogger(ArchiveSaveServiceImpl.class);
//
//	@Autowired
//	private CrmInqueryDaoExt inqueryDaoExt;
//
//	@Autowired
//	private CrmYzDoctorDaoExt yzDoctorDaoExt;
//
//	@Autowired
//	private CrmYzArchiveDaoExt yzArchiveDaoExt;
//
//	@Override
//	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
//	public BasicResVo saveArchive(ArchiveSaveReq archiveNewReq) {
//
//		String loggerTitle = SysPropertyReader.getValue("REQNAME_INQUERY_SAVE");
//
//		// 请求校验
//		BasicResVo basicResVo = new BasicResVo();
//		archiveNewReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			logger.warn(loggerTitle + "请求参数校验失败[" + archiveNewReq.toString()
//					+ "]");
//			return basicResVo;
//		}
//
//		// 检查诊单是否存在
//		YzInquery yzInquery = inqueryDaoExt.getYzInqueryByKey(Integer
//				.parseInt(archiveNewReq.getInquery_id()));
//		if (null == yzInquery) {
//			logger.warn(loggerTitle + "未查询到指定诊单["
//					+ archiveNewReq.getInquery_id() + "");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR,
//					SysPropertyReader.getValue("RET_MSG_INQUERY_00000"), null);
//		}
//
//		// 检查诊单状态是否为待处理、处理中 
//		String inqueryFlag = yzInquery.getFinishFlag();
//		if (ServiceContant.INQUERY_STATUS_C.equals(inqueryFlag) || ServiceContant.INQUERY_STATUS_Y.equals(inqueryFlag)) {
//			logger.warn(loggerTitle + "诊单状态为已取消、已完成时不允许点击处理[" + yzInquery.toString() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR,SysPropertyReader.getValue("RET_MSG_INQUERY_00002"), null);
//		}
//
//		// 检查请求人是否为医生
//		YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorByAccount(archiveNewReq
//				.getUser_name());
//		if (yzDoctor == null
//				|| ServiceContant.DOCTOR_TYPE_3
//						.equals(yzDoctor.getDoctorType())) {
//			logger.warn(loggerTitle + "待处理诊单处理保存的请求人不是医生["
//					+ archiveNewReq.toString() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR,
//					SysPropertyReader.getValue("RET_MSG_INQUERY_00001"), null);
//		}
//
//		// 检查诊单处理人是否请求人一致
//		if (StringUtils.isNotBlank(yzInquery.getOpAccountName())
//				&& !yzInquery.getOpAccountName().equals(
//						archiveNewReq.getUser_name())) {
//			logger.warn(loggerTitle + "待处理诊单已有其他医生处理[" + yzInquery.toString()
//					+ "]");
//			return basicResVo.processData(
//					ServiceContant.RET_CODE_ERROR,
//					SysPropertyReader.getValue("RET_MSG_INQUERY_00004_1")
//							+ yzInquery.getOpDoctorName()
//							+ SysPropertyReader
//									.getValue("RET_MSG_INQUERY_00004_2"), null);
//		}
//
//		// 判断APP同步标识真伪
//		if (ServiceContant.SYNCH_STATUS_Y
//				.equals(archiveNewReq.getSynchronous())) {
//			if (StringUtils.isBlank(archiveNewReq.getDoctor_desc())
//					|| StringUtils.isBlank(archiveNewReq.getConsult())
//					|| StringUtils.isBlank(archiveNewReq.getInitial_check())) {
//				logger.warn(loggerTitle + "APP同步标识无效，健康档案关键字段不完整["
//						+ archiveNewReq.toString() + "]");
//				return basicResVo.processData(ServiceContant.RET_CODE_ERROR,
//						SysPropertyReader.getValue("RET_MSG_ARCHIVE_00012"), null);
//			}
//		}
//
//		// 检查字典参数有效性
//		if (!DBValidateUtil.checkDepartment(Integer.parseInt(archiveNewReq
//				.getGuest_department()))) {
//			logger.warn(loggerTitle + "未查询指定的科室[" + archiveNewReq.toString()
//					+ "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR,
//					SysPropertyReader.getValue("RET_MSG_METADATA_00000"), null);
//		}
//		if (!DBValidateUtil.checkDoctor(Integer.parseInt(archiveNewReq
//				.getDoctor_id()))) {
//			logger.warn(loggerTitle + "未查询到指定的医生[" + archiveNewReq.toString()
//					+ "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR,
//					SysPropertyReader.getValue("RET_MSG_DOCTOR_00000"), null);
//		}
//
//		// 检查是否已存在健康档案，防止重复生成
//		if (yzInquery.getArchiveId() != null) {
//			logger.warn(loggerTitle + "诊单已存在健康档案[" + yzInquery.toString() + "]");
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR,
//					SysPropertyReader.getValue("RET_MSG_INQUERY_00007"), null);
//		}
//
//		// 调用订单消耗接口
//		if (!consumeTrade(yzDoctor, yzInquery)) {
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_INQUERY_00011"), null);
//		}
//		
//		// 新增健康档案、更新诊单状态
//		doBusiness(yzInquery, archiveNewReq, yzDoctor);
//
//		// 处理缓存：删除健康档案列表
//		freshCache(yzInquery);
//
//		// 装填测试数据，仅供测试使用
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("archive_id", yzInquery.getArchiveId());
//
//		return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS,SysPropertyReader.getValue("RET_MSG_SYS_00000"), jsonObject);
//	}
//
//	/**
//	 * 新增健康档案、更新诊单
//	 * 
//	 * @param yzInquery
//	 * @param archiveNewReq
//	 * @param yzDoctor
//	 */
//	@Override
//	@Transactional
//	public void doBusiness(YzInquery yzInquery, ArchiveSaveReq archiveNewReq,YzDoctor yzDoctor) {
//		/*************************************************	新增健康档案	*******************************************/
//		YzArchive yzArchive = new YzArchive();
//
//		yzArchive.setCreateTime(new Date());
//		yzArchive.setSexComment(archiveNewReq.getSex_comment());
//		yzArchive.setUid(yzInquery.getUid());
//		yzArchive.setSelfDesc(yzInquery.getSelfDesc());
//		yzArchive.setPhotos(yzInquery.getPhotos());
//		yzArchive.setDoctorDesc(archiveNewReq.getDoctor_desc());
//		yzArchive.setQuestType(archiveNewReq.getQuest_type());
//		yzArchive.setConsult(archiveNewReq.getConsult());
//		yzArchive.setDoctorId(Integer.parseInt(archiveNewReq.getDoctor_id()));
//		yzArchive.setGuestDepartment(Integer.parseInt(archiveNewReq.getGuest_department()));
//		if (ServiceContant.SYNCH_STATUS_Y
//				.equals(archiveNewReq.getSynchronous())) {
//			yzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_Y);
//		} else {
//			yzArchive.setFinishFlag(ServiceContant.FINISH_FLAG_N);
//		}
//		yzArchive.setInqueryId(yzInquery.getId());
//		yzArchive.setDeseaseHisRecord(archiveNewReq.getDesease_his_record());
//		yzArchive.setInitialCheck(archiveNewReq.getInitial_check());
//		yzArchive.setNickName(archiveNewReq.getNick_name());
//		if (StringUtils.isNotBlank(archiveNewReq.getAge())) {
//			yzArchive.setAge(Integer.parseInt(archiveNewReq.getAge()));
//			yzArchive.setAgeType(Integer.parseInt(archiveNewReq.getAge_type()));
//		}
//
//		yzArchiveDaoExt.insert(yzArchive);
//
//		/**************************************	更新诊单状态	**************************************/
//		String finish_flag = ServiceContant.INQUERY_STATUS_Y;
//		Date update_time = new Date();
//		String op_accout_name = archiveNewReq.getUser_name();
//		String op_doctor_name = (yzDoctor.getSecondName() == null ? "" : yzDoctor.getSecondName()) + (yzDoctor.getFirstName() == null ? "" : yzDoctor.getFirstName());
//		Integer archive_id = yzArchive.getId();
//		Integer op_account_id = yzDoctor.getId();
//		
//		inqueryDaoExt.updateInqueryStatus(yzInquery.getId(), finish_flag, op_accout_name, archive_id, op_doctor_name, update_time,op_account_id);
//		
//		// 添加测试代码
//		yzInquery.setArchiveId(archive_id);
//		
//		logger.info(SysPropertyReader.getValue("REQNAME_INQUERY_SAVE") + "新增健康档案成功[" + yzArchive.toString() + ",更新诊单成功[" + "]");
//	}
//	
//	/**
//	 * 消耗订单
//	 * @param yzDoctor 处理医生信息
//	 * @param yzInquery 诊单信息
//	 * @return
//	 */
//	private boolean consumeTrade(YzDoctor yzDoctor,YzInquery yzInquery) {
//		try {
//			// 检查诊单支付状态
//			if (ServiceContant.PAY_FLAG_DEFAULT.equals(yzInquery.getPayFlag())) {
//				
//				logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "诊单支付状态为-1，准备调用订单消耗接口，请注意~~~~~~~~~~[" + yzInquery.toString() + "]");
//				
//				// 组装参数
//				String userId = String.valueOf(yzInquery.getUid());
//				String inqueryId = String.valueOf(yzInquery.getId());
//				String doctorId = String.valueOf(yzDoctor.getId());
//				String doctorName = yzInquery.getOpDoctorName();
//				// 通话已接通，通话时长为null取0，通话时长非空取通话时长；通话未接通，通话时长按0处理
//				int callDucation = 0;
//				if (ServiceContant.PROCESS_FLAG_Y.equals(yzInquery.getProcessFlag())) {
//					if (null == yzInquery.getCallDuration()) {
//						callDucation = 0;
//						logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "通话已接通,通话时长为null，通话时长当作0进行处理，请注意~~~~~~~~[" + yzInquery.toString() + "]");
//					} else {
//						callDucation = yzInquery.getCallDuration();
//						logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "通话已接通，通话时长为" + callDucation + ",请注意~~~~~~");
//					}
//				} else if (ServiceContant.PROCESS_FLAG_N.equals(yzInquery.getProcessFlag())) {
//					callDucation = 0;
//					logger.warn(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "通话未接通，通话时长当作0进行处理，请注意~~~~~~~~~~");
//				} else {
//					logger.warn(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "未知的通话状态，通话时长当作0进行处理，请注意~~~~~~~~~[" + yzInquery.toString() + "]");
//				}
//				String sign = Md5Utils.md5Encrypt(userId + inqueryId + doctorId + doctorName + callDucation + SysPropertyReader.getValue("SIGN_KEY"));
//				
//				Map<String, Object> paraMap = new HashMap<String, Object>();
//				paraMap.put("userId", userId);
//				paraMap.put("inqueryId", inqueryId);
//				paraMap.put("doctorId", doctorId);
//				paraMap.put("doctorName", doctorName);
//				paraMap.put("sign", sign);
//				paraMap.put("callDuration", String.valueOf(callDucation));
//
//				// 调用订单接口
//				String url = SysPropertyReader.getValue("TRADE_URL");
//				String resString = HttpUtils.httpPostPubMethod(url, paraMap);
//				JSONObject tradeRes = JSONObject.parseObject(resString);
//				
//				if (0 == tradeRes.getIntValue("ret")) {
//					logger.error(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用成功，接口返回消耗订单失败，请注意~~~~~~~~~~~["+ tradeRes.toJSONString() + "," + yzInquery.toString() + "]");
//					return false;
//				} else if (1 == tradeRes.getIntValue("ret")) {
//					logger.info(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用成功，接口返回消耗订单成功，请注意~~~~~~~~~~~["+ tradeRes.toJSONString() + "," + yzInquery.toString() + "]");
//					return true;
//				} else {
//					logger.error(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用成功，接口返回未知状态码，请注意~~~~~~~~~~~~[" + tradeRes.toJSONString() + "," + yzInquery.toString() + "]");
//					return false;
//				}
//			} else {
//				logger.warn(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "诊单已进入支付流程，不会调用订单消耗接口，请注意~~~~~~~~~~[" + yzInquery.toString() + "]");
//				return true;
//			}
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_TRADEINFO_TARDE") + "调用订单消耗接口异常", e);
//			return false;
//		}
//	}
//
//	/**
//	 * 删除档案缓存列表
//	 * 
//	 * @param yzInquery
//	 */
//	private void freshCache(YzInquery yzInquery) {
//		String key = MemcacheKeyPropsReader
//				.getProperties("YZ_USER_ARCHIVE_KEY") + yzInquery.getUid();
//		try {
//			MemcachedUtils.delete(key);
//			logger.info(SysPropertyReader.getValue("REQNAME_MEMCACHE_FRESH")
//					+ "删除用户健康档案列表成功[" + key + "]");
//		} catch (Exception e) {
//			logger.error(SysPropertyReader.getValue("REQNAME_MEMCACHE_FRESH")
//					+ "删除用户健康档案列表异常[" + key + "]", e);
//		}
//	}
//
//}
