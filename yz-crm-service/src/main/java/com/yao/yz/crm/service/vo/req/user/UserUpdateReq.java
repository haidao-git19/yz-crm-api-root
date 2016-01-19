package com.yao.yz.crm.service.vo.req.user;

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
 *	描述：用户信息更新请求数据
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 9:28:58 AM
 *	@Versin 1.0
 */
public class UserUpdateReq extends BasicReqVo{

	private static final long serialVersionUID = 1672588863688478469L;

	/*private static final String REG_AGE = "[0-9]\\d*$";*/
	
	/**
	 * 用户编号
	 */
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER,description = "用户uid")
	private String uid;
	
	/**
	 * 姓
	 */
	private String second_name;
	
	/**
	 * 年龄
	 */
	private String age;
	
	/**
	 * 性别
	 */
	@BindValidation(_blackable = false, _nullable = false, regexExpression = "^[FM]{1}$",description = "用户性别")
	private String sex;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		try {
			// 系统校验
			ValidateService.valid(this);
			
			// 扩展校验
			if (StringUtils.isNotBlank(this.age)) {
				if (!this.age.matches("[0-9]*")) {
					basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户年龄格式不正确", null);
					return;
				}
				if (Integer.parseInt(this.age) > 100) {
					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_USER_00001"), null);
					return;
				}
				if (Integer.parseInt(this.age) == 0) {
					basicResVo.processData(ServiceContant.RET_CODE_ERROR, SysPropertyReader.getValue("RET_MSG_USER_00002"), null);
					return;
				}
			}
		} catch (YzRuntimeException e) {
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, e.getErrInlineMsg(), null);
		}
		
/*		
 * v2.0版本移除
 * 伍文君
 * 2015.12.11
 * 
 * 
		if (StringUtils.isBlank(this.uid)){
			logger.warn("【用户信息更新接口】用户编号为空,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号为空", null);
			return;
		}else if (!this.uid.matches(ServiceContant.REG_NUMBER)){
			logger.warn("【用户信息更新接口】用户编号[" + this.uid + "]非法，请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号格式非法", null);
			return;
		} else if (StringUtils.isBlank(this.second_name)) {
			logger.warn("【用户信息更新接口】用户称呼为空,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "称呼为空", null);
			return;
		}else if (StringUtils.isBlank(this.age)){
			logger.warn("【用户信息更新接口】用户年龄为空,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "年龄为空", null);
			return;
		} else if (!this.age.matches(REG_AGE)){
			logger.warn("【用户信息更新接口】用户年龄[age=" + this.age + "]非法,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "年龄格式非法", null);
			return;
		} else if (Integer.parseInt(this.age) > 200)  {
			logger.warn("【用户信息更新接口】用户年龄[age=" + this.age + "]大于200,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "年龄大于200", null);
			return;
		} else if (StringUtils.isBlank(this.sex)) {
			logger.warn("【用户信息更新接口】用户性别为空,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "性别为空", null);
			return;
		} else if (!ServiceContant.SEX_MAN.equals(this.sex) && !ServiceContant.SEX_WOMAN.equals(this.sex)) {
			logger.warn("【用户信息更新接口】用户性别[sex=" + this.sex + "]非法,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "性别格式非法", null);
			return;
		} else if (!DBValidateUtil.checkUser(Integer.parseInt(this.uid))) {
			logger.warn("【用户信息更新接口】用户编号[sex=" + this.uid + "]无效,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户编号无效", null);
		}
		*/
	}
}
