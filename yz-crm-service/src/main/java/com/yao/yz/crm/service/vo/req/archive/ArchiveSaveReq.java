//package com.yao.yz.crm.service.vo.req.archive;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.yao.yz.crm.service.util.DBValidateUtil;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.ServiceUtil;
//import com.yao.yz.crm.service.vo.BasicReqVo;
//import com.yao.yz.crm.service.vo.BasicResVo;
///**
// *	描述：健康档案保存数据请求
// *	@Author wuwenjun
// *	@Date Oct 28, 2015 12:28:52 PM
// *	@Versin 1.0
// */
//public class ArchiveSaveReq extends BasicReqVo {
//
//	private static final long serialVersionUID = 913300319709447656L;
//	
//	/**
//	 * 档案标题长度限制
//	 */
//	private static final Integer illnessKeyLimit = 8;
//	
//	/**
//	 * 预约事项描述长度限制
//	 */
//	private static final Integer bookDescLimit = 10;
//	
//	/**
//	 * 私人医生咨询记录
//	 */
//	private static final Integer consultLimit = 500;
//	
//	/**
//	 * 预约时间校验规则
//	 */
//	private static final String bookTimeReg = "^[0-9]{2}:[0-9]{2}$";
//	
//	/**
//	 * 预约日期校验规则，时间戳必须为13位
//	 */
//	private static final String bookDateReg = "^[0-9]{13}$";
//	
//	/**
//	 * 请求类型
//	 */
//	private String req_type;
//	
//	/**
//	 * 源健康档案id
//	 */
//	private String source_id;
//	
//	/**
//	 * 健康档案编号
//	 */
//	private String archive_id;
//	
//	/**
//	 * 用户编号
//	 */
//	private String uid;
//	
//	/**
//	 * 来电清单编号
//	 */
//	private String inquery_id;
//
//	/**
//	 * 问诊类型，F-初诊，S-复诊，R-回访
//	 */
//	private String quest_type;
//	
//	/**
//	 * 处理科室编号
//	 */
//	private String guest_department;
//	
//	/**
//	 * 处理医生编号
//	 */
//	private String doctor_id;
//	
//	/**
//	 * 档案标题
//	 */
//	private String illness_key;
//	
//	/**
//	 * 私人医生记录
//	 */
//	private String consult;
//	
//	/**
//	 * 预约科室编号
//	 */
//	private String book_department;
//	
//	/**
//	 * 预约医生编号
//	 */
//	private String book_doctor_id;
//	
//	/**
//	 * 预约日期，时间戳
//	 */
//	private String book_date;
//	
//	/**
//	 * 预约开始时间，HH-mm
//	 */
//	private String book_start;
//	
//	/**
//	 * 预约结束时间，HH-mm
//	 */
//	private String book_end;
//	
//	/**
//	 * 事项描述
//	 */
//	private String book_desc;
//	
//	@Override
//	public void checkParameter(BasicResVo basicResVo) {
//		// 校验请求类型
//		if (StringUtils.isBlank(this.req_type)) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "请求类型为空", null);
//			return;
//		}/* else if (ServiceContant.ARCHIVE_SAVE_A.equals(this.req_type) || ServiceContant.ARCHIVE_SAVE_R.equals(this.req_type)) {
//			if (StringUtils.isBlank(this.source_id)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "源健康档案编号为空", null);
//				return;
//			} else if (!this.source_id.matches(ServiceContant.REG_NUMBER)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "源健康档案编号格式非法", null);
//				return;
//			} else if (!DBValidateUtil.checkSourceArchive(Integer.parseInt(this.source_id))){
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "源健康档案编号无效", null);
//				return;
//			}
//		}*/ else if (ServiceContant.ARCHIVE_SAVE_E.equals(this.req_type) 
//				|| ServiceContant.ARCHIVE_SAVE_A.equals(this.req_type)
//				|| ServiceContant.ARCHIVE_SAVE_R.equals(this.req_type)) {
//			if (StringUtils.isBlank(this.archive_id)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案编号为空", null);
//				return;
//			} else if (!this.archive_id.matches(ServiceContant.REG_NUMBER)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案编号格式非法", null);
//				return;
//			} else if (!DBValidateUtil.checkArchive(Integer.parseInt(this.archive_id))) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案编号无效", null);
//				return;
//			}
//		} else if (ServiceContant.ARCHIVE_SAVE_H.equals(this.req_type)) {
//			if (StringUtils.isBlank(this.inquery_id)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "来电清单编号为空", null);
//				return;
//			} else if (!this.inquery_id.matches(ServiceContant.REG_NUMBER)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "来电清单格式非法", null);
//				return;
//			} else if (!DBValidateUtil.checkInquery(Integer.parseInt(this.inquery_id))) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "来电清单编号无效", null);
//				return;
//			}
//		} else if (ServiceContant.ARCHIVE_SAVE_N.equals(this.req_type)) {
//			if (StringUtils.isBlank(this.uid)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号为空", null);
//				return;
//			} else if (!this.uid.matches(ServiceContant.REG_NUMBER)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号格式非法", null);
//				return;
//			} else if (!DBValidateUtil.checkUser(Integer.parseInt(this.uid))) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号无效", null);
//				return;
//			}
//		} else {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "请求类型非法", null);
//			return;
//		}
//		
//		// 校验问题类型
//		if (StringUtils.isBlank(this.quest_type)) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "问题类型为空", null);
//			return;
//		} else if(!(ServiceContant.QUEST_TYPE_F.equals(this.quest_type) 
//						|| ServiceContant.QUEST_TYPE_R.equals(this.quest_type)
//						|| ServiceContant.QUEST_TYPE_S.equals(this.quest_type))) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "问题类型非法", null);
//			return;
//		}
//		
//		// 校验处理科室
//		if (StringUtils.isBlank(this.getGuest_department())) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "处理科室编号为空", null);
//			return;
//		} else if (!this.getGuest_department().matches(ServiceContant.REG_NUMBER)) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "处理科室编号格式非法", null);
//			return;
//		} else if (!DBValidateUtil.checkDepartment(Integer.parseInt(this.getGuest_department()))) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "处理科室编号无效", null);
//			return;
//		}
//		
//		// 校验处理医生
//		if (StringUtils.isBlank(this.doctor_id)) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "处理医生编号为空", null);
//			return;
//		} else if (!this.doctor_id.matches(ServiceContant.REG_NUMBER)) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "处理医生编号格式非法", null);
//			return;
//		} else if (!DBValidateUtil.checkDoctor(Integer.parseInt(this.getDoctor_id()))) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "处理医生编号无效", null);
//			return;
//		}
//		
//		// 校验长度
//		if (StringUtils.isNotBlank(this.illness_key) && this.illness_key.length() > illnessKeyLimit) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案标题字数超过" + illnessKeyLimit, null);
//			return;
//		}
//		if (StringUtils.isNotBlank(this.consult) && this.consult.length() > consultLimit) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "私人医生记录字数超过" + consultLimit, null);
//			return;
//		}
//		
//		// 校验预约信息
//		if (StringUtils.isBlank(this.book_department) || "0".equals(this.book_department)) {// 无预约科室
//			if (StringUtils.isNotBlank(this.book_doctor_id) && !"0".equals(this.book_doctor_id)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "无预约科室时不需要填写预约医生", null);
//				return;
//			}
//			if (StringUtils.isNotBlank(this.getBook_date())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "无预约科室时不需要填写预约日期", null);
//				return;
//			}
//			if (StringUtils.isNotBlank(this.getBook_start())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "无预约科室时不需要填写预约开始时间", null);
//				return;
//			}
//			if (StringUtils.isNotBlank(this.getBook_end())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "无预约科室时不需要填写预约结束时间", null);
//				return;
//			}
//			if (StringUtils.isNotBlank(this.getBook_desc())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "无预约科室时不需要填写事项描述", null);
//				return;
//			}
//		} else {// 有预约科室
//			if ("-1".equals(this.getBook_department())) { // 预约私人健康顾问
//				if (StringUtils.isNotBlank(this.getBook_doctor_id()) && !"0".equals(this.getBook_doctor_id())) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约私人健康顾问不允许预约医生", null);
//					return;
//				}
//			} else {
//				// 预约科室
//				if (StringUtils.isBlank(this.book_department)) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约科室编号为空", null);
//					return;
//				} else if (!this.getBook_department().matches(ServiceContant.REG_NUMBER)) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约科室编号格式非法", null);
//					return;
//				} else if (!DBValidateUtil.checkDepartment(Integer.parseInt(this.getBook_department()))) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约科室编号无效", null);
//					return;
//				}
//				
//				// 预约医生
//				if (StringUtils.isBlank(this.getBook_doctor_id())) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约医生为空", null);
//					return;
//				} else if (!this.getBook_doctor_id().matches(ServiceContant.REG_NUMBER)) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约医生编号格式非法", null);
//					return;
//				} else if (!DBValidateUtil.checkDoctor(Integer.parseInt(this.getBook_doctor_id()))) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约医生编号无效", null);
//					return;
//				}
//			}
//			
//			// 预约日期校验
//			if (StringUtils.isBlank(this.getBook_date())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约日期为空", null);
//				return;
//			} else if (!this.getBook_date().matches(bookDateReg)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约日期格式必须为时间戳(13位)", null);
//				return;
//			}
//			
//			// 预约开始时间
//			if (StringUtils.isBlank(this.getBook_start())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约开始时间为空", null);
//				return;
//			} else if (!this.getBook_start().matches(bookTimeReg)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约开始时间格式必须为HH:mm", null);
//				return;
//			}
//			
//			// 预约结束时间
//			if (StringUtils.isBlank(this.getBook_end())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约结束时间为空", null);
//				return;
//			} else if (!this.getBook_end().matches(bookTimeReg)) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约结束时间格式必须为HH：mm", null);
//				return;
//			}
//			
//			// 预约开始时间必须大于预约结束时间
//			if (ServiceUtil.timeToInt(this.getBook_start()) >= ServiceUtil.timeToInt(this.getBook_end())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "预约开始时间必须小于预约结束时间", null);
//				return;
//			}
//			
//			// 事项描述
//			if (StringUtils.isBlank(this.getBook_desc())) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "事项描述为空", null);
//				return;
//			} else if (this.getBook_desc().length() > bookDescLimit) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, "事项描述长度超过" + bookDescLimit, null);
//				return;
//			}
//		}
//	}
//
//	public String getReq_type() {
//		return req_type;
//	}
//
//	public void setReq_type(String req_type) {
//		this.req_type = req_type;
//	}
//
//	public String getSource_id() {
//		return source_id;
//	}
//
//	public void setSource_id(String source_id) {
//		this.source_id = source_id;
//	}
//
//	public String getArchive_id() {
//		return archive_id;
//	}
//
//	public void setArchive_id(String archive_id) {
//		this.archive_id = archive_id;
//	}
//
//	public String getUid() {
//		return uid;
//	}
//
//	public void setUid(String uid) {
//		this.uid = uid;
//	}
//
//	public String getInquery_id() {
//		return inquery_id;
//	}
//
//	public void setInquery_id(String inquery_id) {
//		this.inquery_id = inquery_id;
//	}
//
//	public String getQuest_type() {
//		return quest_type;
//	}
//
//	public void setQuest_type(String quest_type) {
//		this.quest_type = quest_type;
//	}
//
//	public String getGuest_department() {
//		return guest_department;
//	}
//
//	public void setGuest_department(String guest_department) {
//		this.guest_department = guest_department;
//	}
//
//	public String getDoctor_id() {
//		return doctor_id;
//	}
//
//	public void setDoctor_id(String doctor_id) {
//		this.doctor_id = doctor_id;
//	}
//
//	public String getIllness_key() {
//		return illness_key;
//	}
//
//	public void setIllness_key(String illness_key) {
//		this.illness_key = illness_key;
//	}
//
//	public String getConsult() {
//		return consult;
//	}
//
//	public void setConsult(String consult) {
//		this.consult = consult;
//	}
//
//	public String getBook_department() {
//		return book_department;
//	}
//
//	public void setBook_department(String book_department) {
//		this.book_department = book_department;
//	}
//
//	public String getBook_doctor_id() {
//		return book_doctor_id;
//	}
//
//	public void setBook_doctor_id(String book_doctor_id) {
//		this.book_doctor_id = book_doctor_id;
//	}
//
//	public String getBook_date() {
//		return book_date;
//	}
//
//	public void setBook_date(String book_date) {
//		this.book_date = book_date;
//	}
//
//	public String getBook_start() {
//		return book_start;
//	}
//
//	public void setBook_start(String book_start) {
//		this.book_start = book_start;
//	}
//
//	public String getBook_end() {
//		return book_end;
//	}
//
//	public void setBook_end(String book_end) {
//		this.book_end = book_end;
//	}
//
//	public String getBook_desc() {
//		return book_desc;
//	}
//
//	public void setBook_desc(String book_desc) {
//		this.book_desc = book_desc;
//	}
//
//	public static Integer getIllnesskeylimit() {
//		return illnessKeyLimit;
//	}
//
//	public static Integer getBookdesclimit() {
//		return bookDescLimit;
//	}
//
//	public static Integer getConsultlimit() {
//		return consultLimit;
//	}
//
//	public static String getBooktimereg() {
//		return bookTimeReg;
//	}
//
//	public static String getBookdatereg() {
//		return bookDateReg;
//	}
//}
