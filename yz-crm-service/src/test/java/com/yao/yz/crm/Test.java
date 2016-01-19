package com.yao.yz.crm;

import java.util.ResourceBundle;

import com.yao.yz.crm.service.util.ServiceUtil;

/**
 *	描述：
 *	@Author wuwenjun
 *	@Date Nov 13, 2015 2:28:42 PM
 *	@Versin 1.0
 */
public class Test {
	
	public static void main(String[] args) {
		String a = "1447400322";
		System.out.println(ServiceUtil.getDateFromSeconds(a));
		
		ResourceBundle bundle = ResourceBundle.getBundle("system");
		System.out.println(bundle.getString("RET_MSG_SYS_00001"));
	}
}
