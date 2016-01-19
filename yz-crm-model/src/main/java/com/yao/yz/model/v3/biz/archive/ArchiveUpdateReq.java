package com.yao.yz.model.v3.biz.archive;

import org.apache.commons.lang.StringUtils;

import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 11:25:03 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class ArchiveUpdateReq extends AbstractReqModel{
	
	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		// 年龄校验
		if (StringUtils.isNotBlank(this.age)) {
			// age
			if (!this.age.matches("[0-9]*")) {
				abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50000);
				return false;
			}
			// age_type 
			if (StringUtils.isBlank(this.age_type) || !this.age_type.matches("^[12]{1}$")) {
				abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50001);
				return false;
			}
			// age、age_type组合校验
			if (age_type.equals("1")) {
				if (Integer.parseInt(age) >11) {
					abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50002);
					return false;
				}
			} else {
				if (Integer.parseInt(age) > 100) {
					abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50003);
					return false;
				}
			}
		}

		// 长度校验
		if (StringUtils.isNotBlank(this.getDoctor_desc()) && this.getDoctor_desc().length() > 200) {
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50004);
			return false;
		}
		if (StringUtils.isNotBlank(this.getDesease_his_record()) && this.getDesease_his_record().length() > 200) {
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50005);
			return false;
		}
		if (StringUtils.isNotBlank(this.getInitial_check()) && this.getInitial_check().length() > 200) {
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50006);
			return false;
		}
		if (StringUtils.isNotBlank(this.getConsult()) && this.getConsult().length() > 500) {
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50007);
			return false;
		}
		
		// 组合校验，同步标识为Y时，doctor_desc、initial_check、consult不能同时为空
		if (StringUtils.isBlank(this.doctor_desc)) {
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50009);
			return false;
		} else if (StringUtils.isBlank(this.initial_check)){
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50010);
			return false;
		} else if(StringUtils.isBlank(this.consult)) {
			abstractResModel.setPublicData(ModelConstants.RET_CODE_ERROR, 50011);
			return false;
		}
		
		return true;
	}

	private static final long serialVersionUID = 2107238800601357782L;
	
	@BindValidation(_blackable = false,_nullable = false,regexType = RegexType.NUMBER,description = "archive_id")
	private String archive_id;
	
	/**
	 * 用户uid
	 */
//	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER,description = "用户uid")
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
	 * 用药建议
	 */
	private String drugs_note;

	/**
	 * 用药建议
	 */
	private String drug_advice_list;
	
	/**
	 * 同步APP标识，Y-同步，N-不同步
	 */
	@BindValidation(_blackable = false, _nullable = false, regexExpression = "^[YN]{1}$", description = "同步标识")
	private String synchronous;
	
	
	public String getDrugs_note() {
		return drugs_note;
	}

	public void setDrugs_note(String drugs_note) {
		this.drugs_note = drugs_note;
	}

	public String getDrug_advice_list() {
		return drug_advice_list;
	}

	public void setDrug_advice_list(String drug_advice_list) {
		this.drug_advice_list = drug_advice_list;
	}

	public String getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(String archive_id) {
		this.archive_id = archive_id;
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
		return "ArchiveUpdateReq [archive_id=" + archive_id + ", uid=" + uid
				+ ", nick_name=" + nick_name + ", sex_comment=" + sex_comment
				+ ", age=" + age + ", age_type=" + age_type + ", doctor_desc="
				+ doctor_desc + ", desease_his_record=" + desease_his_record
				+ ", consult=" + consult + ", initial_check=" + initial_check
				+ ", drugs_note=" + drugs_note + ", drug_advice_list="
				+ drug_advice_list + ", synchronous=" + synchronous + "]";
	}
}
