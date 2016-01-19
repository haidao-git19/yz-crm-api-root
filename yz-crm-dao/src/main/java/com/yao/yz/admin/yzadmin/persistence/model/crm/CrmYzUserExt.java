/**
* @Title: YzUserExt.java
* @Package com.yao.yz.crm.persistence.model.ext
* @Description: TODO
* @author wangyulong
* @date Oct 13, 2015
* @version V1.0
*/
package com.yao.yz.admin.yzadmin.persistence.model.crm;

import com.yao.yz.admin.yzadmin.persistence.model.YzUser;

/**
 * @ClassName: YzUserExt
 * @Description: TODO
 * @author wangyulong
 * @date Oct 13, 2015
 *
 */
public class CrmYzUserExt extends YzUser{

	private static final long serialVersionUID = -7533237468908472602L;
	private String userFirstName;
	private String userSecondName;
	
	

	public String getUserSecondName() {
		return userSecondName;
	}

	public void setUserSecondName(String userSecondName) {
		this.userSecondName = userSecondName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
}
