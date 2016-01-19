/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-10:58:04 AM
* @version 1.0
*/
package com.yao.yz.crm.persistence.ext.test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmInqueryDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmInqueryExt;

/**
 * 公司名: 壹药网
 * 类名称：InqueryDaoTest
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-10:58:04 AM
 */
public class InqueryDaoTest extends AbstractTest{

	@Autowired
	private CrmInqueryDaoExt dao;
	
	@Test
	public void getInqueryList(){
		List<CrmInqueryExt> list=dao.getInqueryList();
		if(list!=null){
			System.out.println("size: "+list.size());
		}
	}
	
	@Test
	public void finish(){
		System.out.println(dao.finish(2));
	}
	
	@Test
	public void updateProcessFlag(){
		System.out.println(dao.updateProcessFlag(101));
	}
	
	@Test
	public void getInquery() {
		// 查询诊单
		System.out.println(dao.getYzInqueryByKey(1));
	}
	
	@Test
	public void updateInqueryStatus() {
		// 更新诊单状态
		dao.updateInqueryStatus(174	, "N", "75404", 99789, "伍文君", new Date(),16);
	}
	
	@Test
	public void getUndoListV3(){
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("doctor_id", "16");
		input.put("order", "ASC");
		List<CrmInqueryExt> list=dao.getUndoListV3(input);
		if(list!=null){
			System.out.println("size: "+list.size());
			
			for(CrmInqueryExt inquery:list){
				System.out.println(inquery.getInquerySmsLog().getSmsType());
			}
		}
	}
	
	@Test
	public void getUndoCountV3(){
		System.out.println(dao.getUndoCountV3(16));
	}
	
	@Test
	public void getHistoryListV3(){
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("assign_doctor_name", "李四");
		List<CrmInqueryExt> list=dao.getHistoryListV3(input, new RowBounds(0, 10));
		if(list!=null){
			System.out.println("size: "+list.size());
			for(CrmInqueryExt inquery:list){
				System.out.println(inquery.getAssignDoctorName());
			}
		}
	}
	
	@Test
	public void getHistoryCountV3(){
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("inquery_id", "181");
		System.out.println(dao.getHistoryCountV3(input));
	}
	
	
	@Test
	public void getDoctorByLoginName(){
		YzDoctor d=dao.getDoctorByLoginName("75403");
		System.out.println(d.getId());
	}
	
	@Test
	public void getAllListV3(){
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("inquery_id", "324");
		List<CrmInqueryExt> list=dao.getAllListV3(input, new RowBounds(0, 10));
		if(list!=null){
			System.out.println("size: "+list.size());
			for(CrmInqueryExt inquery:list){
				System.out.println(inquery.getAssignDoctorId());
			}
		}
	}
	
	@Test
	public void getAllListCountV3(){
		Map<String, Object> input = new HashMap<String, Object>();
		//input.put("assign_doctor_name", "李四");
		System.out.println(dao.getAllListCountV3(input));
	}
}
