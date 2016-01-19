package com.yao.yz.crm.persistence.ext.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;



/**
 *	描述：
 *	@Author wuwenjun
 *	@Date Oct 14, 2015 9:05:43 AM
 *	@Versin 1.0
 */
public class AdminSysParameterTest extends AbstractTest{

	@Autowired
	private CrmAdminSysParameterDaoExt adminSysParameterDaoExt;
	
	@Test
	public void Test() {
		System.out.println(adminSysParameterDaoExt.getImageHost());
	}
}
