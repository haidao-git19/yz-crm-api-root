package com.yao.yz.crm.persistence.ext.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzArchiveExt;

public class YzArchiveDaoExtTest extends AbstractTest{

	@Autowired
	CrmYzArchiveDaoExt dao;

	@Test
	public void TestGetArchiveList() {
		List<CrmYzArchiveExt> list = dao.archiveList(230);
		if (list != null) {
			System.out.println("size:" + list.size());
			if (list.size() != 0) {
				CrmYzArchiveExt yzArchiveExt = list.get(0);
				YzDoctor yzDoctor = yzArchiveExt.getBookDoctor();
				System.out.println(yzArchiveExt.getSexComment());
				if (yzDoctor != null) {
					System.out.println("预约医生信息");
					System.out.println("预约科室：" + yzArchiveExt.getBookDepName());
					System.out.println("Id:" + yzDoctor.getId());
					System.out.println("FirstName:" + yzDoctor.getFirstName());
					System.out.println("SecondName:" + yzDoctor.getSecondName());
				}
				YzDoctor questDoctor = yzArchiveExt.getQuestDoctor();
				if (questDoctor != null) {
					System.out.println("处理医生信息");
					System.out.println("预约科室：" + yzArchiveExt.getQuestDepName());
					System.out.println("Id:" + questDoctor.getId());
					System.out.println("FirstName:" + questDoctor.getFirstName());
					System.out.println("SecondName:" + questDoctor.getSecondName());
				}
			}
		}
	}
	
//	@Test
//	public void TestArchive() {
//		YzArchive yzArchive = dao.getYzArchiveByKey(132);
//		if (yzArchive != null) {
//			yzArchive.setAdvice(null);
//			dao.updateArchive(yzArchive);
//		} else {
//			System.out.println("null");
//		}
//	}
//	
	@Test
	public void TestSelectArchive() {
		List<CrmYzArchiveExt> yzArchiveExts = dao.selectArchiveExts(72	, 1);
		if (yzArchiveExts != null) {
			System.out.println("size:" + yzArchiveExts.size());
		}
	}
	
	@Test
	public void TestSelectArchiveBySource() {
		List<CrmYzArchiveExt> yzArchiveExts = dao.archiveListBySource(230);
		if (yzArchiveExts != null) {
			System.out.println("size:" + yzArchiveExts.size());
			if (yzArchiveExts.size() > 0) {
				System.out.println(yzArchiveExts.get(0).getId());
				System.out.println(yzArchiveExts.get(0).getBookDoctor());
			}
		}
	}
	
	@Test
	public void TestSelectArchiveBySourceDep() {
		List<CrmYzArchiveExt> yzArchiveExts = dao.archiveListByDep(230, 1);
		if (yzArchiveExts != null) {
			System.out.println("size:" + yzArchiveExts.size());
			if (yzArchiveExts.size() > 0) {
				System.out.println(yzArchiveExts.get(0).getGuestDepartment());
			}
		}
	}
}
