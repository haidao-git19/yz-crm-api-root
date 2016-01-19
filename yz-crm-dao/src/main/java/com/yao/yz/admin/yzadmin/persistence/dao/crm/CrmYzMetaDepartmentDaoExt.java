package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.admin.yzadmin.persistence.dao.YzMetaDepartmentDao;
import com.yao.yz.admin.yzadmin.persistence.model.YzMetaDepartment;
import com.yao.yz.util.datasource.DataSource;

import java.util.List;

/**
 * Desc:科室扩展Dao
 * Author: wuwenjun
 * Date: 2015/09/27 13:39
 */
public interface CrmYzMetaDepartmentDaoExt extends YzMetaDepartmentDao{

    /**
     * 功能：获取所有的科室
     * @return
     */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
    List<YzMetaDepartment> getDepartments();
    
    /**
     * 功能：根据tagKey查询科室
     * @param tagKey
     * @return
     */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
    YzMetaDepartment getDepartmentByTagKey(Integer tagKey);

}
