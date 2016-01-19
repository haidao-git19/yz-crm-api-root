package com.yao.yz.model.v3.biz.inquery;

import org.apache.commons.lang.StringUtils;

//import com.yao.yz.biz.diagnosis.api.bean.inquery.DrugAdviceBean;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 
 * @Description: 诊单完成接口
 * @Autor: wuwenjun
 * @Date: 6:34:22 PM Jan 5, 2016
 * @Version: v1.0
 * 
 */

public class InqueryFinishReq extends AbstractReqModel{
	
	private static final long serialVersionUID = 6211249642664017007L;

	@BindValidation(_blackable = false, _nullable = false,regexType = RegexType.NUMBER,description = "inquery_id")
	private String inquery_id;
	
	private String nick_name;
	
	private String sex_comment;
	
	private String age;
	
	private String age_type;
	
	private String doctor_desc;
	
	private String desease_his_record;
	
	private String consult;
	
	private String initial_check;
	
	private String drugs_note;
	
	private String drug_advice_list;
	
	@BindValidation(_blackable = false,_nullable = false,regexExpression = "^[YN]{1}$",description = "synchronous")
	private String synchronous;
	
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
		return true;
	}
	

	public String getDrug_advice_list() {
		return drug_advice_list;
	}


	public void setDrug_advice_list(String drug_advice_list) {
		this.drug_advice_list = drug_advice_list;
	}


	public String getDrugs_note() {
		return drugs_note;
	}



	public void setDrugs_note(String drugs_note) {
		this.drugs_note = drugs_note;
	}



	public String getInquery_id() {
		return inquery_id;
	}


	public void setInquery_id(String inquery_id) {
		this.inquery_id = inquery_id;
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
		return "InqueryFinishReq [inquery_id=" + inquery_id + ", nick_name="
				+ nick_name + ", sex_comment=" + sex_comment + ", age=" + age
				+ ", age_type=" + age_type + ", doctor_desc=" + doctor_desc
				+ ", desease_his_record=" + desease_his_record + ", consult="
				+ consult + ", initial_check=" + initial_check
				+ ", drugs_note=" + drugs_note + ", drug_advice_list="
				+ drug_advice_list + ", synchronous=" + synchronous + "]";
	}
}
