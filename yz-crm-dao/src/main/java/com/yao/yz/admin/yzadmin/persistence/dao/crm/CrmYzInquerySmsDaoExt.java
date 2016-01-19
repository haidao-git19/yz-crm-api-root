package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.dao.YzInquerySmsLogDao;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;

/**
 * 
 * @Description: 诊单发送记录扩展Dao
 * @Autor: wuwenjun
 * @Date: 5:46:50 PM Jan 4, 2016
 * @Version: v1.0
 * 
 */

public interface CrmYzInquerySmsDaoExt extends YzInquerySmsLogDao{

	/**
	 * 根据诊单id查询短信发送列表
	 * @param inqueryId 诊单id
	 * @return
	 */
	List<YzInquerySmsLog> getByInqueryId(Integer inqueryId);
	
}
