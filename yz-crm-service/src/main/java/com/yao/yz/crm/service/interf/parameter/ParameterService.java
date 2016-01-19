package com.yao.yz.crm.service.interf.parameter;

import com.yao.yz.admin.yzadmin.persistence.model.AdminSysParameter;

/**
 * 
 * @Description: 系统参数服务接口
 * @Autor: wuwenjun
 * @Date: 7:09:46 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public interface ParameterService {

	/**
	 * 查询系统参数配置
	 * @param name 系统参数名称
	 * @return
	 */
	AdminSysParameter getParameterByName(String name);
}
