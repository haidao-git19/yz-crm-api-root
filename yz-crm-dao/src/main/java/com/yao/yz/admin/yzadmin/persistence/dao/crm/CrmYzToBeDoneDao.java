/**
* @Title: YzReminderDao.java
* @Package com.yao.yz.crm.persistence.dao.ext
* @Description: TODO
* @author wangyulong
* @date Sep 26, 2015
* @version V1.0
*/
package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.admin.yzadmin.persistence.dao.YzArchiveDao;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzToBeDone;
import com.yao.yz.util.datasource.DataSource;

import java.util.List;

/**
 * @ClassName: YzReminderDao
 * @Description: 待办事项
 * @author wangyulong
 * @date Sep 26, 2015
 *
 */
public interface CrmYzToBeDoneDao extends YzArchiveDao{

	 /**
  	 * 功能：今日待办显示
  	 * @Author wangyulong
  	 * @param 
  	 * @return  List
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmYzToBeDone> getTodayList();
	  /**
  	 * 功能：明日待办显示
  	 * @Author wangyulong
  	 * @param 
  	 * @return  List
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmYzToBeDone> getTomorrowList();
	 /**
  	 * 功能：修改今日待办的完成状态
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int todayFinish(int id);
	 /**
  	 * 功能：修改明日待办的完成状态
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int tomorrowFinish(int id);
}
