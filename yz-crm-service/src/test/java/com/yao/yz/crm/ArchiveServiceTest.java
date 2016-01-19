package com.yao.yz.crm;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//import com.yao.yz.admin.yzadmin.persistence.dao.YzArchiveDao;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.crm.service.impl.archive.ArchiveServiceCom;
import com.yao.yz.crm.service.interf.archive.ArchiveService;
import com.yao.yz.model.v3.base.res.ResModelProxy;
import com.yao.yz.model.v3.biz.archive.ArchiveQueryReq;
import com.yao.yz.model.v3.biz.archive.ArchiveUpdateReq;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 9:55:28 AM Jan 12, 2016
 * @Version: v1.0
 * 
 */

public class ArchiveServiceTest extends AbstractTest{

	@Autowired
	private ArchiveService archiveService;
	
	@Autowired
	private ArchiveServiceCom archiveServiceCom;
	
//	@Autowired
//	private YzArchiveDao yzArchiveDao;
	
	@Test
	public void TestUpdate() {
		
		ArchiveUpdateReq archiveUpdateReq = new ArchiveUpdateReq();
//		YzArchive yzArchive = yzArchiveDao.getYzArchiveByKey(99902);
		
		archiveUpdateReq.setAge("15");
		archiveUpdateReq.setAge_type("2");
		archiveUpdateReq.setArchive_id("99902");
		archiveUpdateReq.setConsult("更新之后的建议");
		archiveUpdateReq.setCurrent_time(String.valueOf(new Date().getTime()));
		archiveUpdateReq.setDesease_his_record("医生建议");
		archiveUpdateReq.setDoctor_desc("医生主诉");
		archiveUpdateReq.setDrug_advice_list("");
		archiveUpdateReq.setDrugs_note("用药建议");
		archiveUpdateReq.setInitial_check("诊断");
		archiveUpdateReq.setSynchronous("Y");
		archiveUpdateReq.setDrug_advice_list("");
		
		ResModelProxy resModel = new ResModelProxy();
		archiveService.updateArchive(archiveUpdateReq, resModel);
	}
	
	@Test
	public void TestInfo() {
		ArchiveQueryReq archiveQueryReq = new ArchiveQueryReq();
		archiveQueryReq.setArchive_id("99905");
		
		ResModelProxy resModel = new ResModelProxy();
		archiveService.getAchiveDetail(archiveQueryReq, resModel);
		
		System.out.println(resModel.toString());
		
		archiveServiceCom.getDrugs(99905);
	}
}
