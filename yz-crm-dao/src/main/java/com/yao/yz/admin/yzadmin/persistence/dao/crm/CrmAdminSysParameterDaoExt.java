package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import com.yao.yz.admin.yzadmin.persistence.dao.AdminSysParameterDao;
import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.admin.yzadmin.persistence.model.AdminSysParameter;
import com.yao.yz.util.datasource.DataSource;

/**
 *	描述：系统参数dao扩展
 *	@Author wuwenjun
 *	@Date Oct 14, 2015 8:45:53 AM
 *	@Versin 1.0
 */
public interface CrmAdminSysParameterDaoExt extends AdminSysParameterDao{

	/**
	 * 功能：查询图片url域名
	 * @return
	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	public String getImageHost();
	
	/**
	 * 根据参数名称查询系统参数值
	 * @param name 系统参数名称
	 * @return
	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	public AdminSysParameter getParameterByName(String name);
}
