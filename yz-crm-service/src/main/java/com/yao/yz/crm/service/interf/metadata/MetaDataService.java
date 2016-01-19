package com.yao.yz.crm.service.interf.metadata;

import com.yao.yz.crm.service.vo.BasicResVo;

/**
 *	描述：数据字典服务接口，提供数据字典服务
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 6:29:03 PM
 *	@Versin 1.0
 */
public interface MetaDataService {
	
	/**
	 * 查询科室列表
	 * @return
	 */
	BasicResVo getDepartmentList();
	
	
	/**
	 * 查询医生列表
	 * @return
	 */
	BasicResVo getDoctorList();
	
}
