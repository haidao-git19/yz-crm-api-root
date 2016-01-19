/**
* @Title: MobileAreaDao.java
* @Package com.yao.yz.crm.persistence.dao.ext
* @Description: TODO
* @author wangyulong
* @date Oct 19, 2015
* @version V1.0
*/
package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.util.datasource.DataSource;

/**
 * @ClassName: MobileAreaDao
 * @Description: TODO
 * @author wangyulong
 * @date Oct 19, 2015
 *
 */
public interface CrmMobileAreaDao {

	 /**
  	 * 功能：根据手机号段获取归属地
  	 * @Author wangyulong
  	 * @param   String
  	 * @return  String
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	String getMobileArea(String mobile);
}
