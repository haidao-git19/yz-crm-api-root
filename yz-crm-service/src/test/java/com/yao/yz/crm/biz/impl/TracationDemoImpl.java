package com.yao.yz.crm.biz.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzUserDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
import com.yao.yz.crm.biz.interf.TracationDemo;

@Service
public class TracationDemoImpl implements TracationDemo{
	
	@Autowired
	private CrmYzUserDaoExt yzUserDaoExt;
	
	@Autowired
	private CrmYzArchiveDaoExt yzArchiveDaoExt;

	@Override
	public void method1() {
		YzArchive yzArchive = getYzArchive();
		YzUser yzUser = getUser();
		
		method2(yzUser, yzArchive);
	}

	@Override
	@Transactional
	public void method2(YzUser yzUser, YzArchive yzArchive) {
		if (yzUser != null) {
			yzUser.setUpdateTime(new Date());
			yzUserDaoExt.update(yzUser);
		}
		if (yzArchive != null) {
			yzArchive.setUpdateTime(new Date());
			yzArchiveDaoExt.update(yzArchive);
		}
/*		
		if (1 ==1 ) {
			throw new YzRuntimeException(0, "模拟异常");
		}
		*/
	}
	
	public YzUser getUser() {
		return yzUserDaoExt.getYzUserByKey(230);
	}
	
	public YzArchive getYzArchive() {
		return yzArchiveDaoExt.getYzArchiveByKey(99795);
	}
	
}
