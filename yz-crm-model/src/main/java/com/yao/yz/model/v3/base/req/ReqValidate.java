package com.yao.yz.model.v3.base.req;

import com.yao.yz.model.v3.base.Validate;
import com.yao.yz.model.v3.base.res.AbstractResModel;

/**
 * 
 * @Description: 请求数据模型校验接口
 * @Autor: wuwenjun
 * @Date: 12:43:33 PM Dec 28, 2015
 * @Version: v1.0
 * 
 */

public interface ReqValidate extends Validate{

	/**
	 * 请求数据模型参数校验，将请求校验结果写入返回数据模型中
	 * @param resModel 返回数据模型
	 * @return 请求校验结果，true-校验通过，false-校验未通过
	 */
	boolean validate(AbstractResModel resModel);
	
}
