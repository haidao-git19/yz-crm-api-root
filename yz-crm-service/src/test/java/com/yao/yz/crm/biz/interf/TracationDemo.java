package com.yao.yz.crm.biz.interf;

import org.springframework.stereotype.Service;

import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;

@Service
public interface TracationDemo {

	void method1();
	
	void method2(YzUser yzUser, YzArchive yzArchive);
	
}
