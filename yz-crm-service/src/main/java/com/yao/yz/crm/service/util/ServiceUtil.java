package com.yao.yz.crm.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *	描述：工具类
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 8:43:31 PM
 *	@Versin 1.0
 */
public class ServiceUtil {

	/**
     * 功能：将年龄转换出生年月
     * @param age 年龄
     * @return
     */
    public static Integer ageToYear(Integer age) {
        return Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())) - age;
    }

    /**
     * 功能：将出生年月转换为年龄
     * @param year 出生年
     * @return
     */
    public static Integer yearToAge(Integer year) {
        return Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())) - year;
    }
    
    /**
	 * 功能：将预约时间修改成数据，字符串格式：mm:ss
	 * @param time
	 * @return
	 */
	public static Integer timeToInt(String time) {
		if (time != null) {
			return Integer.valueOf(time.replaceAll(":", ""));
		} else {
			return null;
		}
	}
	
	/**
	 * 功能：将预约时间修改成字符串，字符串格式：mm：dd
	 * @param time
	 * @return
	 */
	public static String intToTime(Integer time) {
		if (time != null) {
			String t = null;
			if (time < 10) {
				t = "00:0" + time;
			} else if (time <100) {
				t = "00:" + time;
			} else if (time < 1000) {
				t = "0" + String.valueOf(time).substring(0, 1) + ":" + String.valueOf(time).substring(1);
			} else if (time >= 1000) {
				t = String.valueOf(time).substring(0, 2) + ":" + String.valueOf(time).substring(2);
			}
			return t;
		} else {
			return null;
		}
	}
	
	
	public static String formatDate(Date date,String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将时间戳转换为日期格式,时间戳的精度到毫秒
	 * @param seconds
	 * @return
	 */
    public static Date getDateFromSeconds(String seconds){
    	Date date=new Date();
    	date.setTime(Long.parseLong(seconds));
    	return date;
    }
}
