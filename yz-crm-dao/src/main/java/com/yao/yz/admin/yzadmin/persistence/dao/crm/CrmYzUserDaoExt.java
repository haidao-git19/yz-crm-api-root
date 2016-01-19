package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.admin.yzadmin.persistence.dao.YzUserDao;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
import com.yao.yz.util.datasource.DataSource;

public interface CrmYzUserDaoExt extends YzUserDao{

	 /**
  	 * 功能：搜索用户信息
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<YzUser> searchUser(YzUser user);
	 /**
  	 * 功能：根据手机号获取用户信息
  	 * @Author wangyulong
  	 * @param   String
  	 * @return  YzUser
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	YzUser getUserByMobile(String mobile);
}
