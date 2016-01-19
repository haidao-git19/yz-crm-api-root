package com.yao.yz.crm.persistence.ext.test;

import java.util.ArrayList;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzRecommendDrugsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzRecommendDrugs;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 8:45:18 PM Jan 11, 2016
 * @Version: v1.0
 * 
 */

public class YzRecordDrugsTest extends AbstractTest{

	@Autowired
	private CrmYzRecommendDrugsDaoExt crmYzRecommendDrugsDaoExt;
	
	@Test
	public void TestAddBatch() {
		
		ArrayList<YzRecommendDrugs> drugs = new ArrayList<YzRecommendDrugs>();
		
		YzRecommendDrugs yzRecommendDrugs = new YzRecommendDrugs();
		yzRecommendDrugs.setId(55);
		yzRecommendDrugs.setInqueryId(289);
		yzRecommendDrugs.setUid(230);
		yzRecommendDrugs.setArchiveId(99891);
		yzRecommendDrugs.setDrugCode("972709");
		yzRecommendDrugs.setDrugName("你有病，该吃药了");
		drugs.add(yzRecommendDrugs);
		
		crmYzRecommendDrugsDaoExt.addDrugList(drugs);
	}
	
}
