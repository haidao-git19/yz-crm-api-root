/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 10, 2015-10:10:49 AM
* @version 1.0
*/
package com.yao.yz.crm.service.interf.callStatistic;

import com.yao.yz.admin.yzadmin.persistence.model.YzCallStatistics;

/**
 * 公司名: 壹药网
 * 类名称：CallStatisticService
 * 类描述：
 * @author wangyulong
 * @date Nov 10, 2015-10:10:49 AM
 */
public interface CallStatisticService {

	int insertCallStatistic(YzCallStatistics cs);
	
	int updateCallStatistic(YzCallStatistics cs);
	
	YzCallStatistics getCallStatistic(int id);
	
}
