package com.yao.yz.crm.service.vo;
/**
 *	描述：请求数据校验接口
 *	@Author wuwenjun
 *	@Date Oct 28, 2015 12:32:39 PM
 *	@Versin 1.0
 */
public interface BasicReqValidate {

	/**
	 * 请求参数校验，只执行基本的数据校验
	 * @param basicResVo
	 */
	public void checkParameter(BasicResVo basicResVo);
	
}
