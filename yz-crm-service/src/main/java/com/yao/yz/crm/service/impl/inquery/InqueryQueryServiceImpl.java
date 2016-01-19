/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-11:22:46 AM
* @version 1.0
*/
package com.yao.yz.crm.service.impl.inquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.yao.yz.admin.yzadmin.persistence.dao.YzDoctorDao;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmInqueryDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzArchiveDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmInqueryExt;
import com.yao.yz.crm.service.interf.inquery.InqueryQueryService;
import com.yao.yz.crm.service.util.ServiceUtil;
import com.yao.yz.crm.service.vo.res.Inquery;
import com.yao.yz.crm.service.vo.res.Inquery2;
import com.yao.yz.model.v3.biz.inquery.AllListVo;
//import com.yao.yz.model.v3.biz.inquery.UndoListReq;
import com.yao.yz.model.v3.biz.inquery.UndoListRes;

/**
 * 公司名: 壹药网
 * 类名称：InqueryServiceImpl
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-11:22:46 AM
 */
@Service
public class InqueryQueryServiceImpl implements InqueryQueryService {
	
	private static final Logger logger = Logger.getLogger(InqueryQueryServiceImpl.class);
	
	@Autowired
	private CrmInqueryDaoExt dao;
	
	@Autowired
	private CrmYzArchiveDaoExt archiveDao;
	
	@Override
	public List<Inquery> getInqueryList() {
		List<Inquery> listAfter=new ArrayList<Inquery>();
		try{
			List<CrmInqueryExt> listBefore=dao.getInqueryList();
			if(listBefore!=null){
				Inquery inqueryAfter;
				for(CrmInqueryExt inqueryBefore:listBefore){
					inqueryAfter=new Inquery();
					inqueryAfter.setId(inqueryBefore.getId()+"");
					String createTime=ServiceUtil.formatDate(inqueryBefore.getCreateTime(), "yyyy-MM-ddHH:mm:ss");
					inqueryAfter.setCreate_date(createTime.substring(0, 10));
					inqueryAfter.setCreate_time(createTime.substring(10, 18));
					inqueryAfter.setFinish_flag(inqueryBefore.getFinishFlag());
					if(inqueryBefore.getArchiveId()==null){
						inqueryAfter.setProcess_flag("N");
					}else{
						YzArchive archive=archiveDao.getYzArchiveByKey(inqueryBefore.getArchiveId());
						if(archive!=null){
							if(archive.getIllnessKey()!=null&&archive.getIllnessKey()!=""&&archive.getConsult()!=null&&archive.getConsult()!=""){
								inqueryAfter.setProcess_flag("Y");
							}else{
								inqueryAfter.setProcess_flag("N");
							}
						}
					}
					inqueryAfter.setOp_account_id(inqueryBefore.getOpAccountId()==null?"":inqueryBefore.getOpAccountId()+"");
					inqueryAfter.setOp_account_name(inqueryBefore.getOpAccountName());
					inqueryAfter.setSelf_desc(inqueryBefore.getSelfDesc());
					inqueryAfter.setUser_id(inqueryBefore.getUser().getUid()+"");
					if(inqueryBefore.getUser().getSex()!=null&&inqueryBefore.getUser().getSecondName()!=null){
						inqueryAfter.setUser_name(inqueryBefore.getUser().getSex().equals("M")?inqueryBefore.getUser().getSecondName()+"先生":inqueryBefore.getUser().getSecondName()+"女士");
					}else{
						inqueryAfter.setUser_name("");
					}
					inqueryAfter.setDepartment(inqueryBefore.getDepartment().getName());
					
					listAfter.add(inqueryAfter);
				}
			}
			return listAfter;
		}catch(Exception e){
			logger.error("inquery Before and After 对象转换异常!", e);
		}
		return null;
	}

	@Override
	public String checkFinish(int id) {
		
		YzInquery inquery=dao.getYzInqueryByKey(id);
		if(inquery.getArchiveId()==null){
			return "健康档案未创建!";
		}else{
			YzArchive archive=archiveDao.getYzArchiveByKey(inquery.getArchiveId());
			if(archive!=null){
				if(archive.getIllnessKey()==null||archive.getIllnessKey()==""){
					return "档案标题为空!";
				}
				if(archive.getConsult()!=null||archive.getConsult()==""){
					return "私人医生咨询记录为空!";
				}
			}
		}
		return "";
	}

	@Override
	public int updateProcessFlag(int id) {
		return dao.updateProcessFlag(id);
	}

	@Override
	public int finish(int id) {
		return dao.finish(id);
	}

	@Override
	public Map<String, Object> getPagerList(Map<String, Object> input, RowBounds bounds) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Inquery2> listAfter=new ArrayList<Inquery2>();
		
		List<CrmInqueryExt>  listBefore=dao.getPagerList(input, bounds);
		if(listBefore!=null){
			Inquery2 inqueryAfter;
			for(CrmInqueryExt inqueryBefore:listBefore){
				inqueryAfter=new Inquery2();
				inqueryAfter.setInquery_id(inqueryBefore.getId()+"");
				String createTime=ServiceUtil.formatDate(inqueryBefore.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
				inqueryAfter.setCreate_time(createTime);
				inqueryAfter.setUser_id(inqueryBefore.getUser().getUid()+"");
				if(inqueryBefore.getUser().getSex()!=null){
					inqueryAfter.setUser_name(inqueryBefore.getUser().getSex().equals("M")?(inqueryBefore.getUser().getSecondName()==null?"":inqueryBefore.getUser().getSecondName())+"先生":(inqueryBefore.getUser().getSecondName()==null?"":inqueryBefore.getUser().getSecondName())+"女士");
				}else{
					inqueryAfter.setUser_name("");
				}
				inqueryAfter.setDepartment_name(inqueryBefore.getDepartment().getName());
				inqueryAfter.setSelf_desc(inqueryBefore.getSelfDesc());
				inqueryAfter.setOp_doctor_name(inqueryBefore.getOpDoctorName());
				inqueryAfter.setInquery_status(inqueryBefore.getFinishFlag());
				inqueryAfter.setArchive_status(inqueryBefore.getArchive().getFinishFlag());
				inqueryAfter.setArchive_id(inqueryBefore.getArchiveId()==null?null:inqueryBefore.getArchiveId().toString());
				if(inqueryBefore.getUpdateTime()!=null){
					String updateTime=ServiceUtil.formatDate(inqueryBefore.getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
					inqueryAfter.setUpdate_time(updateTime);
				}
				listAfter.add(inqueryAfter);
			}
		}
		
		int count=dao.getListCount(input);
		ret.put("list", listAfter);
		ret.put("count", count);

		return ret;
	}

	@Override
	public int getCallDuration(int id) {
		return dao.getCallDuration(id);
	}

	@Override
	public int insertCallDuration(Integer call_duration, Integer id) {
		return dao.insertCallDuration(call_duration, id);
	}

	@Override
	public int getUndoCount(String start_time, String end_time) {
		return dao.getUndoCount(start_time, end_time);
	}

	@Override
	public List<Inquery2> getUndoListV3(Map<String, Object> input) {
		try{
			List<Inquery2> listAfter=new ArrayList<Inquery2>();
			
			List<CrmInqueryExt>  listBefore=dao.getUndoListV3(input);
			if(listBefore!=null){
				Inquery2 inqueryAfter;
				for(CrmInqueryExt inqueryBefore:listBefore){
					inqueryAfter=new Inquery2();
					inqueryAfter.setInquery_id(inqueryBefore.getId()+"");
					if(inqueryBefore.getCreateTime()!=null){
						inqueryAfter.setCreate_time(ServiceUtil.formatDate(inqueryBefore.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					}
					inqueryAfter.setUser_id(inqueryBefore.getUser().getUid()+"");
					if(inqueryBefore.getUser().getSex()!=null){
						inqueryAfter.setUser_name(inqueryBefore.getUser().getSex().equals("M")?(inqueryBefore.getUser().getSecondName()==null?"":inqueryBefore.getUser().getSecondName())+"先生":(inqueryBefore.getUser().getSecondName()==null?"":inqueryBefore.getUser().getSecondName())+"女士");
					}else{
						inqueryAfter.setUser_name("");
					}
					inqueryAfter.setDepartment_name(inqueryBefore.getDepartment().getName());
					inqueryAfter.setSelf_desc(inqueryBefore.getSelfDesc());
					inqueryAfter.setInquery_status(inqueryBefore.getFinishFlag());
					inqueryAfter.setArchive_status(inqueryBefore.getArchive().getFinishFlag());
					inqueryAfter.setSms_type(inqueryBefore.getInquerySmsLog().getSmsType()+"");
					if(inqueryBefore.getInquerySmsLog().getSmsSendTime()!=null){
						inqueryAfter.setSms_send_time(ServiceUtil.formatDate(inqueryBefore.getInquerySmsLog().getSmsSendTime(), "yyyy-MM-dd HH:mm:ss"));
					}
					
					listAfter.add(inqueryAfter);
				}
			}
			UndoListRes undoListRes=new UndoListRes();
			undoListRes.setList(listAfter);
			return listAfter;
		}catch(Exception e){
			logger.error("/v2/getUndoList service异常", e);
		}
		return null;
	}

	
	@Override
	public int getUndoCountV3(int doctor_id) {
		return dao.getUndoCountV3(doctor_id);
	}

	@Override
	public List<Inquery2> getHistoryListV3(Map<String, Object> input, RowBounds rowBounds) {
		
        List<Inquery2> listAfter=new ArrayList<Inquery2>();
		List<CrmInqueryExt>  listBefore=dao.getHistoryListV3(input, rowBounds);
		if(listBefore!=null){
			Inquery2 inqueryAfter;
			for(CrmInqueryExt inqueryBefore:listBefore){
				inqueryAfter=new Inquery2();
				inqueryAfter.setInquery_id(inqueryBefore.getId()+"");
				if(inqueryBefore.getCreateTime()!=null){
					String createTime=ServiceUtil.formatDate(inqueryBefore.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
					inqueryAfter.setCreate_time(createTime);
				}
				inqueryAfter.setUser_id(inqueryBefore.getUser().getUid()+"");
				if(inqueryBefore.getUser().getSex()!=null){
					inqueryAfter.setUser_name(inqueryBefore.getUser().getSex().equals("M")?(inqueryBefore.getUser().getSecondName()==null?"":inqueryBefore.getUser().getSecondName())+"先生":(inqueryBefore.getUser().getSecondName()==null?"":inqueryBefore.getUser().getSecondName())+"女士");
				}else{
					inqueryAfter.setUser_name("");
				}
				inqueryAfter.setDepartment_name(inqueryBefore.getDepartment().getName());
				inqueryAfter.setSelf_desc(inqueryBefore.getSelfDesc());
				inqueryAfter.setOp_doctor_name(inqueryBefore.getAssignDoctorName());
				inqueryAfter.setInquery_status(inqueryBefore.getFinishFlag());
				inqueryAfter.setArchive_status(inqueryBefore.getArchive().getFinishFlag());
				inqueryAfter.setArchive_id(inqueryBefore.getArchiveId()==null?null:inqueryBefore.getArchiveId().toString());
				if(inqueryBefore.getFinishTime()!=null){
					String updateTime=ServiceUtil.formatDate(inqueryBefore.getFinishTime(), "yyyy-MM-dd HH:mm:ss");
					inqueryAfter.setUpdate_time(updateTime);
				}
				listAfter.add(inqueryAfter);
			}
		}
		
		return listAfter;
	}

	@Override
	public int getHistoryCountV3(Map<String, Object> input) {
		return dao.getHistoryCountV3(input);
	}

	@Override
	public YzDoctor getDoctorByLoginName(String loginName) {
		return dao.getDoctorByLoginName(loginName);
	}

	@Override
	public List<AllListVo> getAllListV3(Map<String, Object> input, RowBounds rowBounds) {
		List<AllListVo> listAfter=new ArrayList<AllListVo>();
		List<CrmInqueryExt>  listBefore=dao.getAllListV3(input, rowBounds);
		if(listBefore!=null){
			AllListVo inqueryAfter;
			for(CrmInqueryExt inqueryBefore:listBefore){
				inqueryAfter=new AllListVo();
				inqueryAfter.setInquery_id(inqueryBefore.getId()+"");
				if(inqueryBefore.getCreateTime()!=null){
					String createTime=ServiceUtil.formatDate(inqueryBefore.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
					inqueryAfter.setCreate_time(createTime);
				}
				inqueryAfter.setUid(inqueryBefore.getUid()+"");
				inqueryAfter.setDepartment_name(inqueryBefore.getDepartment().getName());
				inqueryAfter.setSelf_desc(inqueryBefore.getSelfDesc());
				inqueryAfter.setInquery_status(inqueryBefore.getFinishFlag());
				inqueryAfter.setAssign_doctor_name(inqueryBefore.getAssignDoctorName());
				inqueryAfter.setAssign_doctor_type(inqueryBefore.getAssignDoctorType()+"");
				inqueryAfter.setAssign_flag(inqueryBefore.getAssignFlag()==null?"-1":inqueryBefore.getAssignFlag()+"");
				inqueryAfter.setAssign_doctor_id(inqueryBefore.getAssignDoctorId());
				listAfter.add(inqueryAfter);
			}
		}
		
		return listAfter;
	}

	@Override
	public int getAllListCountV3(Map<String, Object> input) {
		return dao.getAllListCountV3(input);
	}

	@Override
	public int getFailedAssignCountV3() {
		return dao.getFailedAssignCountV3();
	}

	@Override
	public YzInquery getYzInqueryById(Integer inqueryId) {
		return dao.getYzInqueryByKey(inqueryId);
	}
	
}
