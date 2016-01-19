/**
* @Title: YzToBeDone.java
* @Package com.yao.yz.crm.persistence.model.ext
* @Description: TODO
* @author wangyulong
* @date Sep 28, 2015
* @version V1.0
*/
package com.yao.yz.admin.yzadmin.persistence.model.crm;

import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;

/**
 * @ClassName: YzToBeDone
 * @Description: TODO
 * @author wangyulong
 * @date Sep 28, 2015
 *
 */
public class CrmYzToBeDone extends YzArchive{

	private static final long serialVersionUID = -1248991844771341849L;
	CrmYzUserExt user;
	YzDoctor doctor;
	
	public CrmYzUserExt getUser() {
		return user;
	}
	public void setUser(CrmYzUserExt user) {
		this.user = user;
	}
	public YzDoctor getDoctor() {
		return doctor;
	}
	public void setDoctor(YzDoctor doctor) {
		this.doctor = doctor;
	}

}
