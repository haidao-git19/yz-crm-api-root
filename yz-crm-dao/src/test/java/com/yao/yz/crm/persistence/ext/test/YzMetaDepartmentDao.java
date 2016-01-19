package com.yao.yz.crm.persistence.ext.test;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzMetaDepartmentDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzMetaDepartment;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Desc:
 * Author: wuwenjun
 * Date: 2015/09/27 13:49
 */
public class YzMetaDepartmentDao extends AbstractTest{

    @Autowired
    private CrmYzMetaDepartmentDaoExt yzMetaDepartmentDaoExt;

    @Test
    public void TestGetPartment(){
        List<YzMetaDepartment> list=yzMetaDepartmentDaoExt.getDepartments();
        if(list!=null){
			System.out.println("size: "+list.size());
		}
    }
    
    @Test
    public void TestGetDepartment() {
    	YzMetaDepartment department = yzMetaDepartmentDaoExt.getDepartmentByTagKey(7);
    	if (department != null) {
    		System.out.println(department.getName());
    	}
    }

}
