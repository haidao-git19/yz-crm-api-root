package com.yao.yz.crm.service.interf.inquersms;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;

/**
 * 
 * @Description: 诊单短信服务
 * @Autor: wuwenjun
 * @Date: 8:50:25 PM Jan 5, 2016
 * @Version: v1.0
 * 
 */

public interface InquerySmsService {

	/**
	 * 根据诊单查询短信发送记录
	 * @return
	 */
	List<YzInquerySmsLog> getByInqueryId(Integer inqueryId);
	
}
