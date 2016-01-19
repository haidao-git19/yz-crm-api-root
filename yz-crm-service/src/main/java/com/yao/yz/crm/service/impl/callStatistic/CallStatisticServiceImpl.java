/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 10, 2015-10:15:56 AM
* @version 1.0
*/
package com.yao.yz.crm.service.impl.callStatistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.yz.admin.yzadmin.persistence.model.YzCallStatistics;
import com.yao.yz.crm.service.interf.callStatistic.CallStatisticService;
import com.yao.yz.admin.yzadmin.persistence.dao.YzCallStatisticsDao;

/**
 * 公司名: 壹药网
 * 类名称：CallStatisticServiceImpl
 * 类描述：
 * @author wangyulong
 * @date Nov 10, 2015-10:15:56 AM
 */
@Service
public class CallStatisticServiceImpl implements CallStatisticService {

	@Autowired
	private YzCallStatisticsDao dao;
	
	@Override
	public int insertCallStatistic(YzCallStatistics cs) {
		return dao.insert(cs);
	}

	@Override
	public int updateCallStatistic(YzCallStatistics cs) {
		return dao.update(cs);
	}

	@Override
	public YzCallStatistics getCallStatistic(int id) {
		return dao.getYzCallStatisticsByKey(id);
	}

}
