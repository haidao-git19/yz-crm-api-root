package com.yao.yz.crm.web.test;

import com.yao.yz.util.common.Md5Utils;

public class Ency {
	public static void main(String[] args) {
		String userId = "64477";
		String inqueryId = "521";
		String doctorId = "15";
		String doctorName = "杨小春";
		String sign_key = "app@tocrm@spend@deal";
		String callDucation = "13";
		String sign = Md5Utils.md5Encrypt(userId + inqueryId + doctorId + doctorName + callDucation + sign_key);
		System.err.println(sign);
	}
}
