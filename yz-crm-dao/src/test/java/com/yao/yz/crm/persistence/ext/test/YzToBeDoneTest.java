package com.yao.yz.crm.persistence.ext.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzToBeDoneDao;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzToBeDone;



public class YzToBeDoneTest extends AbstractTest{

	@Autowired
	CrmYzToBeDoneDao dao;
	
	@Test
	public void TestTodayList() {
		List<CrmYzToBeDone> list=dao.getTodayList();
		 if(list!=null){
				System.out.println("size: "+list.size());
			}
	}
	
	@Test
	public void TestTomorrowList() {
		List<CrmYzToBeDone> list=dao.getTomorrowList();
		 if(list!=null){
				System.out.println("size: "+list.size());
			}
	}
	
	@Test
	public void TestTodayFinish() {
		/*dao.todayFinish(5);*/
	}
	
	@Test
	public void TestTomorrowFinish() {
		/*dao.todayFinish(5);*/
	}
}
