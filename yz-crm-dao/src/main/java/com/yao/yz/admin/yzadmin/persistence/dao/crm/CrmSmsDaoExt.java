/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-4:43:40 PM
* @version 1.0
*/
package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.admin.yzadmin.persistence.dao.YzSmsDao;
import com.yao.yz.admin.yzadmin.persistence.model.YzSms;
import com.yao.yz.util.datasource.DataSource;

/**
 * 公司名: 壹药网
 * 类名称：SmsDaoExt
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-4:43:40 PM
 */
public interface CrmSmsDaoExt extends YzSmsDao{

	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<YzSms> getSmsList();
	
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int finish(YzSms sms);
}
