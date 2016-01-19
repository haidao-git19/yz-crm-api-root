package com.yao.yz.crm.persistence.ext.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzInquerySmsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 5:59:07 PM Jan 4, 2016
 * @Version: v1.0
 * 
 */

public class YzInquerySmsLogDaoTest extends AbstractTest{

	@Autowired
	private CrmYzInquerySmsDaoExt yzInquerySmsDaoExt;
	
	@Test
	public void Test(){
		List<YzInquerySmsLog> logs = yzInquerySmsDaoExt.getByInqueryId(287);
		if (logs != null) {
			System.out.println(logs.size());
			YzInquerySmsLog yzInquerySmsLog = logs.get(0);
			System.err.println(yzInquerySmsLog.getId());
			System.out.println(yzInquerySmsLog.getSendOp());
			System.out.println(yzInquerySmsLog.getSmsSendTime());
		}
	}
	
}
