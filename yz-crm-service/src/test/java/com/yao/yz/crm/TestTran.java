package com.yao.yz.crm;

import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzUserDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
import com.yao.yz.crm.biz.interf.TracationDemo;

public class TestTran extends AbstractTest{

	@Autowired
	private TracationDemo tracationDemo;
	
	@Autowired
	private CrmYzUserDaoExt yzUserDaoExt;
	
	@Autowired
	private CrmYzArchiveDaoExt yzArchiveDaoExt;
	
	@org.junit.Test
	public void Test() {
		tracationDemo.method1();
	}
	
	@org.junit.Test
	public void Test1() {
		
		YzUser yzUser = yzUserDaoExt.getYzUserByKey(230);
		YzArchive yzArchive = yzArchiveDaoExt.getYzArchiveByKey(99795);
		
		tracationDemo.method2(yzUser, yzArchive);
	}
}
