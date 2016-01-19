package com.yao.yz.crm.service.util;

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

/**
 * 系统配置文件阅读器
 * @version 2.0
 * @author wuwenjun
 *
 */
public class SysPropertyReader {
	
	private static ResourceBundle sysPropertyReader = ResourceBundle.getBundle("system"); 
	
	/**
	 * 获取指定key的系统配置属性
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		if (StringUtils.isBlank(key)) {
			key = "【系统接口】";
		}
		return sysPropertyReader.getString(key);
	}
}
