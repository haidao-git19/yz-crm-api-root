package com.yao.yz.crm.service.vo.req.archive.v2;

import org.apache.commons.lang.StringUtils;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.util.SysPropertyReader;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.util.exception.YzRuntimeException;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;
import com.yz.util.tools.validate.ValidateService;

/**
 * 回拨界面生成健康档案请求数据模型
 * @author wuwenjun
 * @version 2.0版本新增
 *
 */
public class ArchiveSaveReq extends BasicReqVo{

	private static final long serialVersionUID = 2107238800601357782L;
	
	/**
	 * 诊单id
	 */
	@BindValidation(_blackable = false,_nullable = false,regexType = RegexType.NUMBER,description = "诊单id")
	private String inquery_id;
	
	/**
	 * 用户uid
	 */
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER,description = "用户uid")
	private String uid;
	
	/**
	 * 问诊患者称呼
	 */
	private String nick_name;
	
	/**
	 * 问诊患者性别
	 */
	@BindValidation(_blackable = false, _nullable = false,regexExpression = "^[FM]{1}$",description = "性别")
	private String sex_comment;
	
	/**
	 * 年龄
	 */
	private String age;
	
	/**
	 * 年龄类型
	 */
	private String age_type;
	
	/**
	 * 问诊类型
	 */
	@BindValidation(_blackable = false,_nullable = false,regexExpression = "^[F]{1}$",description = "问诊类型")
	private String quest_type;
	
	/**
	 * 处理科室编号
	 */
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER , description = "处理科室编号")
	private String guest_department;
	
	/**
	 * 处理医生编号
	 */
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER, description = "处理医生编号")
	private String doctor_id;
	
	/**
	 * 主诉
	 */
	private String doctor_desc;
	
	/**
	 * 现病史
	 */
	private String desease_his_record;
	
	/**
	 * 建议
	 */
	private String consult;
	
	/**
	 * 诊断
	 */
	private String initial_check;
	
	/**
	 * 同步APP标识，Y-同步，N-不同步
	 */
	@BindValidation(_blackable = false, _nullable = false, regexExpression = "^[YN]{1}$", description = "同步标识")
	private String synchronous;
	
	@Override
	public void checkParameter(BasicResVo basicResVo) {
		super.checkParameter(basicResVo);
		try {
			ValidateService.valid(this);
			
			/********* 扩展校验 **********/
			// 年龄校验
			if (StringUtils.isNotBlank(this.age)) {
				// age
				if (!this.age.matches("[0-9]*")) {
					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00006"), null);
					return;
				}
				// age_type 
				if (StringUtils.isBlank(this.age_type) || !this.age_type.matches("^[12]{1}$")) {
					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00007"), null);
					return;
				}
				// age、age_type组合校验
				if (age_type.equals("1")) {
					if (Integer.parseInt(age) >11) {
						basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00008"), null);
						return;
					}
				} else {
					if (Integer.parseInt(age) > 100) {
						basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00009"), null);
						return;
					}
				}
			}

			// 长度校验
			if (StringUtils.isNotBlank(this.getDoctor_desc()) && this.getDoctor_desc().length() > 200) {
				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00002"), null);
			}
			if (StringUtils.isNotBlank(this.getDesease_his_record()) && this.getDesease_his_record().length() > 200) {
				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00003"), null);
			}
			if (StringUtils.isNotBlank(this.getInitial_check()) && this.getInitial_check().length() > 200) {
				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00005"), null);
			}
			if (StringUtils.isNotBlank(this.getConsult()) && this.getConsult().length() > 500) {
				basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_ARCHIVE_00004"), null);
			}
		} catch (YzRuntimeException e) {
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, e.getErrInlineMsg(), null);
		}
	}

	public String getInquery_id() {
		return inquery_id;
	}

	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getSex_comment() {
		return sex_comment;
	}

	public void setSex_comment(String sex_comment) {
		this.sex_comment = sex_comment;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge_type() {
		return age_type;
	}

	public void setAge_type(String age_type) {
		this.age_type = age_type;
	}

	public String getQuest_type() {
		return quest_type;
	}

	public void setQuest_type(String quest_type) {
		this.quest_type = quest_type;
	}

	public String getGuest_department() {
		return guest_department;
	}

	public void setGuest_department(String guest_department) {
		this.guest_department = guest_department;
	}

	public String getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_desc() {
		return doctor_desc;
	}

	public void setDoctor_desc(String doctor_desc) {
		this.doctor_desc = doctor_desc;
	}

	public String getDesease_his_record() {
		return desease_his_record;
	}

	public void setDesease_his_record(String desease_his_record) {
		this.desease_his_record = desease_his_record;
	}

	public String getConsult() {
		return consult;
	}

	public void setConsult(String consult) {
		this.consult = consult;
	}

	public String getInitial_check() {
		return initial_check;
	}

	public void setInitial_check(String initial_check) {
		this.initial_check = initial_check;
	}

	public String getSynchronous() {
		return synchronous;
	}

	public void setSynchronous(String synchronous) {
		this.synchronous = synchronous;
	}

	@Override
	public String toString() {
		return "ArchiveNewReq [inquery_id=" + inquery_id + ", uid=" + uid
				+ ", nick_name=" + nick_name + ", sex_comment=" + sex_comment
				+ ", age=" + age + ", age_type=" + age_type + ", quest_type="
				+ quest_type + ", guest_department=" + guest_department
				+ ", doctor_id=" + doctor_id + ", doctor_desc=" + doctor_desc
				+ ", desease_his_record=" + desease_his_record + ", consult="
				+ consult + ", initial_check=" + initial_check
				+ ", synchronous=" + synchronous + "]" + super.toString();
	}
}
