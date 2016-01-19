package com.yao.yz.crm.service.impl.parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.AdminSysParameter;
import com.yao.yz.crm.service.interf.parameter.ParameterService;

/**
 * 
 * @Description: 系统参数服务接口实现
 * @Autor: wuwenjun
 * @Date: 7:10:24 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */
@Service
public class ParameterServiceImpl implements ParameterService{

	@Autowired
	private CrmAdminSysParameterDaoExt adminSysParameterDaoExt;
	
	@Override
	public AdminSysParameter getParameterByName(String name) {
		return adminSysParameterDaoExt.getParameterByName(name);
	}
}
