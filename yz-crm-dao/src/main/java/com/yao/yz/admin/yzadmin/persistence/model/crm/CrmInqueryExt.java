/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-10:21:54 AM
* @version 1.0
*/
package com.yao.yz.admin.yzadmin.persistence.model.crm;

import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;
import com.yao.yz.admin.yzadmin.persistence.model.YzMetaDepartment;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;

/**
 * 公司名: 壹药网
 * 类名称：InqueryExt
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-10:21:54 AM
 */
public class CrmInqueryExt extends YzInquery{


	/** serialVersionUID*/
	private static final long	serialVersionUID	= 3920451492563028219L;
	
	YzUser user;
	YzMetaDepartment department;
	YzArchive archive;
	YzInquerySmsLog inquerySmsLog;
	
	
	public YzInquerySmsLog getInquerySmsLog() {
		return inquerySmsLog;
	}
	public void setInquerySmsLog(YzInquerySmsLog inquerySmsLog) {
		this.inquerySmsLog = inquerySmsLog;
	}
	public YzArchive getArchive() {
		return archive;
	}
	public void setArchive(YzArchive archive) {
		this.archive = archive;
	}
	public YzUser getUser() {
		return user;
	}
	public void setUser(YzUser user) {
		this.user = user;
	}
	public YzMetaDepartment getDepartment() {
		return department;
	}
	public void setDepartment(YzMetaDepartment department) {
		this.department = department;
	}
	
	
}
