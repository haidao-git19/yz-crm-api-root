package com.yao.yz.crm.web.test;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.util.common.Md5Utils;

/**
 *	描述：
 *	@Author wuwenjun
 *	@Date Nov 13, 2015 7:31:06 PM
 *	@Versin 1.0
 */
public class BaseControllerTest {
	public static void main(String[] args) {
		String token = "abe67273904c223ce063c2058cb17753";
		String login_id = "1";
		String user_name = "admin";
		String user_role = "65535";
		String platform_id = "1";
		String current_time = "1447413988776";
		
		String realToken = Md5Utils.md5Encrypt(login_id + user_name + user_role + platform_id + current_time + ServiceContant.MD5_KEY);
		System.out.println(realToken.equals(token));
	}
}
