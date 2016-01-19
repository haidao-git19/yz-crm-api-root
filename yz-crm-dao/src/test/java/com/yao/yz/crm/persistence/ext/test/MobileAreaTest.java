/**
* @Title: MobileAreaTest.java
* @Package com.yao.yzcrm.crm
* @Description: TODO
* @author wangyulong
* @date Oct 19, 2015
* @version V1.0
*/
package com.yao.yz.crm.persistence.ext.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmMobileAreaDao;



/**
 * @ClassName: MobileAreaTest
 * @Description: TODO
 * @author wangyulong
 * @date Oct 19, 2015
 *
 */
public class MobileAreaTest extends AbstractTest{

	@Autowired
	private CrmMobileAreaDao dao;
	
	@Test
	public void getMobileArea(){
		String mobile="13918407457";
		System.out.println(dao.getMobileArea(mobile.substring(0, 7)));
	}
	
}
