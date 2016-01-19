package com.yao.yz.crm.persistence.ext.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzUserDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;

public class YzUserDaoExtTest extends AbstractTest{

	@Autowired
	CrmYzUserDaoExt dao;
	
	@Test
	public void TestSearchUser() {
		YzUser user=new YzUser();
		user.setUid(5);
		//user.setMobile("139");
		List<YzUser> list=dao.searchUser(user);
		if(list!=null){
			System.out.println("size: "+list.size());
		}
	}
	
	@Test
	public void TestGetByKey() {
		YzUser yzUser = dao.getYzUserByKey(51);
		if (yzUser != null) {
			System.out.println(yzUser.getUid());
		} else {
			System.out.println("用户不存在");
		}
	}
	
	@Test
	public void TestGetUserByMobile() {
		YzUser user=dao.getUserByMobile("75401");
		if(user!=null){
			System.out.println("uid: "+user.getUid());
		}
	}
}
