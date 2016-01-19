//
//
// v3.0版本废弃
//
//
//
//package com.yao.yz.crm.service.vo.req.archive.v2;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.util.SysPropertyReader;
//import com.yao.yz.crm.service.vo.BasicReqVo;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.util.exception.YzRuntimeException;
//import com.yz.util.tools.validate.BindValidation;
//import com.yz.util.tools.validate.RegexType;
//import com.yz.util.tools.validate.ValidateService;
//
///**
// * 健康档案更新请求数据模型
// * 
// * @version 2.0 新增
// * @author wuwenjun
// *
// */
//public class ArchiveUpdateReq extends BasicReqVo{
//
//	private static final long serialVersionUID = -3743797018179960876L;
//	
//	/**
//	 * 健康档案
//	 */
//	@BindValidation(_nullable = false, _blackable = false, regexType = RegexType.NUMBER , description = "健康档案id")
//	private String  archive_id;
//	
//	/**
//	 * 问诊患者称呼
//	 */
//	private String nick_name;
//	
//	/**
//	 * 问诊患者性别
//	 */
//	@BindValidation(_nullable = false, _blackable = false, regexExpression = "^[FM]{1}$", description = "问诊患者性别")
//	private String sex_comment;
//	
//	/**
//	 * 问诊患者年龄
//	 */
//	private String age;
//	
//	/**
//	 * 问诊患者年龄类型
//	 */
//	private String age_type;
//	
//	/**
//	 * 处理科室编号
//	 */
//	@BindValidation(_nullable = false, _blackable = false, regexType = RegexType.NUMBER , description = "处理科室")
//	private String guest_department;
//	
//	/**
//	 * 处理医生编号
//	 */
//	@BindValidation(_nullable = false, _blackable = false, regexType = RegexType.NUMBER, description = "处理医生")
//	private String doctor_id;
//	
//	/**
//	 * 主诉
//	 */
//	@BindValidation(_nullable = false, _blackable = false, maxLength = 200, minLength = 1, description = "主诉")
//	private String doctor_desc;
//	
//	/**
//	 * 现病史
//	 */
//	private String desease_his_record;
//	
//	/**
//	 * 诊断
//	 */
//	@BindValidation(_nullable = false, _blackable = false, maxLength = 200, minLength = 1, description = "诊断")
//	private String initial_check;
//	
//	/**
//	 * 建议
//	 */
//	@BindValidation(_nullable = false, _blackable = false, maxLength = 500, minLength = 1, description = "建议")
//	private String consult;
//	
//	/**
//	 * 同步标识，Y-同步，N-不同步
//	 */
//	@BindValidation(_nullable = false, _blackable = false, regexExpression = "^[YN]{1}$", description = "APP同步标识")
//	private String synchronous;
//	
//	@Override
//	public void checkParameter(BasicResVo basicResVo) {
//		try {
//			super.checkParameter(basicResVo);
//			
//			// 系统校验
//			ValidateService.valid(this);
//			
//			// 扩展校验
//			if (StringUtils.isNotBlank(this.age)) {
//				// age
//				if (!age.matches("[0-9]*")) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00006"), null);
//					return;
//				}
//				// age_type
//				if (StringUtils.isBlank(this.age_type) || !age_type.matches("^[12]{1}$")) {
//					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00007"), null);
//					return;
//				}
//				// age、age_type组合校验
//				if (age_type.equals("1")) {
//					if (Integer.parseInt(age) >11) {
//						basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00008"), null);
//						return;
//					}
//				} else {
//					if (Integer.parseInt(age) > 100) {
//						basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00009"), null);
//						return;
//					}
//				}
//			}
//			// 现病史
//			if (StringUtils.isNotBlank(this.desease_his_record) && this.desease_his_record.length() > 200) {
//				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00003"), null);
//			}
//		} catch (YzRuntimeException e) {
//			basicResVo.processData(ServiceContant.RET_CODE_ERROR, e.getErrInlineMsg(), null);
//			return;
//		}
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
//	public String getNick_name() {
//		return nick_name;
//	}
//
//	public void setNick_name(String nick_name) {
//		this.nick_name = nick_name;
//	}
//
//	public String getSex_comment() {
//		return sex_comment;
//	}
//
//	public void setSex_comment(String sex_comment) {
//		this.sex_comment = sex_comment;
//	}
//
//	public String getAge() {
//		return age;
//	}
//
//	public void setAge(String age) {
//		this.age = age;
//	}
//
//	public String getAge_type() {
//		return age_type;
//	}
//
//	public void setAge_type(String age_type) {
//		this.age_type = age_type;
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
//	public String getDoctor_desc() {
//		return doctor_desc;
//	}
//
//	public void setDoctor_desc(String doctor_desc) {
//		this.doctor_desc = doctor_desc;
//	}
//
//	public String getDesease_his_record() {
//		return desease_his_record;
//	}
//
//	public void setDesease_his_record(String desease_his_record) {
//		this.desease_his_record = desease_his_record;
//	}
//
//	public String getInitial_check() {
//		return initial_check;
//	}
//
//	public void setInitial_check(String initial_check) {
//		this.initial_check = initial_check;
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
//	public String getSynchronous() {
//		return synchronous;
//	}
//
//	public void setSynchronous(String synchronous) {
//		this.synchronous = synchronous;
//	}
//
//	@Override
//	public String toString() {
//		return "ArchiveUpdateReq [archive_id=" + archive_id + ", nick_name="
//				+ nick_name + ", sex_comment=" + sex_comment + ", age=" + age
//				+ ", age_type=" + age_type + ", guest_department="
//				+ guest_department + ", doctor_id=" + doctor_id
//				+ ", doctor_desc=" + doctor_desc + ", desease_his_record="
//				+ desease_his_record + ", initial_check=" + initial_check
//				+ ", consult=" + consult + ", synchronous=" + synchronous + "]" + super.toString();
//	}
//	
//}
