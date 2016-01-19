package com.yao.yz.model.v3.base.res;

import java.io.Serializable;


/**
 * 
 * @Description: 业务数据Bean抽象类，所有的业务Bean都必须继承该类
 * @Autor: wuwenjun
 * @Date: 12:55:13 PM Dec 29, 2015
 * @Version: v1.0
 * 
 */

public abstract class AbstractBizBean implements BizBean,Serializable{
	
	private static final long serialVersionUID = 2226649236419838669L;

	/**
	 * 业务Bean数据名称，用于填充返回数据模型，子类自行实现
	 * @return
	 */
	public abstract String beanName();
	
}
