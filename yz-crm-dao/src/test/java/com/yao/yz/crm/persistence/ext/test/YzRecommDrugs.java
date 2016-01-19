package com.yao.yz.crm.persistence.ext.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzRecommendDrugsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzRecommendDrugs;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 10:41:43 AM Jan 12, 2016
 * @Version: v1.0
 * 
 */

public class YzRecommDrugs extends AbstractTest{

	@Autowired
	private CrmYzRecommendDrugsDaoExt crmYzRecommendDrugsDaoExt;
	
	@org.junit.Test
	public void Test(){
		List<YzRecommendDrugs> crmYzRecommDrugs = crmYzRecommendDrugsDaoExt.getByArchiveId(99905);
		for (YzRecommendDrugs drugs : crmYzRecommDrugs) {
			System.out.println(drugs.getId());
			System.out.println(drugs.getDrugCode());
		}
	}
}
