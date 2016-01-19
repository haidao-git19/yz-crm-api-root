package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.admin.yzadmin.persistence.dao.YzArchiveDao;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzArchiveExt;
import com.yao.yz.util.datasource.DataSource;

public interface CrmYzArchiveDaoExt extends YzArchiveDao{


	/**
	 * 功能：查询指定用户的健康档案列表
	 * @param uid 用户uid
	 * @return
	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmYzArchiveExt> archiveList(Integer uid);
//	
//	/**
//	 * 功能：更新用户健康档案
//	 * @param yzArchive 用户健康档案
//	 * @return
//	 */
//	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
//	int updateArchive(YzArchive yzArchive);
//	


	/**
	 * 功能：根据用户查询uid和处理科室查询健康档案列表
	 * @param uid 用户uid
	 * @param department 处理科室
	 * @return
	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmYzArchiveExt> selectArchiveExts(Integer uid, Integer department);
	
	/**
	 * 功能：根据source_id查询子健康档案列表
	 * @param srouceId
	 * @return
	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmYzArchiveExt> archiveListBySource(Integer sourceId);
	
	/**
	 * 功能：根据用户编号、处理科室编号筛选子健康健康包含指定处理科室编号的源健康档案列表
	 * @param uid
	 * @param guestDepartmentId
	 * @return
	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmYzArchiveExt> archiveListByDep(Integer uid,Integer guestDepartmentId);
	
}
