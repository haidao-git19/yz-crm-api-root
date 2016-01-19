package com.yao.yz.model.v3.base.res;

/**
 * 
 * @Description: 返回数据模型代理
 * @Autor: wuwenjun
 * @Date: 1:02:04 PM Dec 29, 2015
 * @Version: v1.0
 * 
 */

public class ResModelProxy extends AbstractResModel{

	private static final long serialVersionUID = -5604753773082002657L;

	@Override
	public String toString() {
		return "ResModelProxy [ret=" + getRet() + ", msg=" + getMsg()
				+ ", data=" + getData().toString() + "]";
	}
}
