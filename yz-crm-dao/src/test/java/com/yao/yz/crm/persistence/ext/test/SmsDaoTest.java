/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-4:56:54 PM
* @version 1.0
*/
package com.yao.yz.crm.persistence.ext.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmSmsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzSms;


/**
 * 公司名: 壹药网
 * 类名称：SmsDaoTest
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-4:56:54 PM
 */
public class SmsDaoTest extends AbstractTest{

	@Autowired
	private CrmSmsDaoExt dao;
	
	@Test
	public void getSmsList(){
		List<YzSms> list=dao.getSmsList();
		if(list!=null){
			System.out.println("size: "+list.size());
		}
	}
	
	@Test
	public void finish(){
		YzSms sms=new YzSms();
		sms.setId(1);
		sms.setStatus("Y");
		//sms.setSendTime(new Date());
		System.out.println(dao.finish(sms));
	}
	
	@Test
	public void insert(){
		YzSms sms=new YzSms();
		sms.setStatus("N");
		sms.setBookTime(new Date());
		sms.setMobile("222222");
		System.out.println(dao.insert(sms));
	}
}
