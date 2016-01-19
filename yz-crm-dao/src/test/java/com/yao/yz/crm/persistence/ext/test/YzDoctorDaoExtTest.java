package com.yao.yz.crm.persistence.ext.test;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.dao.YzDoctorDao;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzDoctorDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzDoctorExt;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc:
 * Author: wuwenjun
 * Date: 2015/09/27 11:50
 */
public class YzDoctorDaoExtTest extends AbstractTest{

    @Autowired
    private CrmYzDoctorDaoExt yzDoctorDaoExt;

    @Autowired
    private YzDoctorDao yzDoctorDao;
    
    @Test
    public void TestGetDoctorsByDep() {
        List<YzDoctor> list=yzDoctorDaoExt.getDoctorsByDep(1);
        if(list!=null){
			System.out.println("size: "+list.size());
		}
    }

    @Test
    public void TestGetDoctorList(){
        List<YzDoctor> list = yzDoctorDaoExt.getDoctorList();
        if (list != null) {
            System.out.println("size:" + list.size());
        }
    }
//    
//    
//    v2.0版本以后废弃，不再使用
//    
//    
//    @Test
//    public void TestGetDoctorBySeat(){
//    	YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorBySeat("71111");
//    	if (yzDoctor != null){
//    		System.out.println(yzDoctor.toString());
//    	}
//    }
//    
//    @Test
//    public void TestCheckDoctorBySeat() {
//    	YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorBySeat("admin");
//    	System.out.println(yzDoctor == null);
//    }
//    
    @Test
    public void TestGetDoctorByAccount() {
    	YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorByAccount("75403");
    	if (yzDoctor != null) {
    		System.out.println(yzDoctor.toString());
    	}
    }
    
    @Test
    public void TestGetDoctor() {
    	YzDoctor yzDoctor = yzDoctorDao.getYzDoctorByKey(28);
    	System.out.println(yzDoctor.toString());
    }
    
    @Test
    public void TestGetDoctorExt() {
    	CrmYzDoctorExt yzDoctorExt = yzDoctorDaoExt.getDoctorByAccount("75404");
    	System.out.println(yzDoctorExt.toString());
    }
    
    @Test
    public void TestOnLine(){
    	yzDoctorDaoExt.getSelfOnLine(4, "2015-01-14");
    }
}
