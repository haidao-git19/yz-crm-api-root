package com.yao.yz.crm.service.impl.doctor;

//import java.text.SimpleDateFormat;
//import java.util.Date;

//import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.yao.yz.admin.yzadmin.persistence.model.DoctorWorkRecord;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmAdminSysParameterDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzDoctorDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.DoctorWorkRecord;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzDoctorExt;
//import com.yao.yz.crm.persistence.dao.ext.CrmInqueryDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.CrmYzWorkRecordDaoExt;
import com.yao.yz.crm.service.interf.doctor.DoctorService;
//import com.yao.yz.model.v3.base.ModelConstants;
//import com.yao.yz.model.v3.base.res.AbstractResModel;

/**
 * 
 * @Description: 医生服务接口实现类
 * @Autor: wuwenjun
 * @Date: 7:06:04 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */
@Service
public class DoctorServiceImpl implements DoctorService{
	
//	private static final Logger logger = Logger.getLogger(DoctorServiceImpl.class);
	
	@Autowired
	private CrmAdminSysParameterDaoExt adminSysParameterDaoExt;
	
	@Autowired
	private CrmYzDoctorDaoExt yzDoctorDaoExt;
	
//	@Autowired
//	private CrmYzWorkRecordDaoExt doctorWorkRecordDao;
	
//	@Autowired
//	private CrmInqueryDaoExt inqueryDaoExt;
	
//	@Override
//	public Integer undoCount(Integer doctorId) {
//		return inqueryDaoExt.getWaitCount(doctorId);
//	}

	@Override
	public CrmYzDoctorExt getByLoginUserName(String loginUserName) {
		CrmYzDoctorExt yzDoctorExt = yzDoctorDaoExt.getDoctorByAccount(loginUserName);
		if (yzDoctorExt != null) {
			String imageHost = adminSysParameterDaoExt.getImageHost();
			yzDoctorExt.setFace(imageHost + yzDoctorExt.getFace());
		}
		return yzDoctorExt;
	}

	@Override
	public DoctorWorkRecord getLastWorkRecord(Integer doctorId) {
		return yzDoctorDaoExt.getLastRecord(doctorId, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}

	@Override
	public Integer getWaitCount(Integer doctorId) {
		return yzDoctorDaoExt.getWaitCount(doctorId);
	}

	@Override
	public List<YzDoctor> getSelfOnLine(Integer guestDepartment) {
		return yzDoctorDaoExt.getSelfOnLine(guestDepartment, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	}
//	
//	@Override
//	public boolean serviceOn(Integer doctorId,AbstractResModel resModel) {
//		// 查询当天的打卡记录,存在未下班的打卡记录时不做任何处理
//		DoctorWorkRecord record = doctorWorkRecordDao.getTodayWorkRecord(doctorId, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//		if (record != null && ModelConstants.DOCTOR_SERVICE_ON == record.getStatus()) {
//			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 20002);
//			logger.warn(">>>医生[doctorID="  + doctorId + "]已处于上班状态，不做任何处理，请注意..." + record.toString());
//			return true;
//		}
//		
//		// 当天不存在打卡记录或者打卡状态为停诊，新增打卡记录
//		record = new DoctorWorkRecord();
//		record.setDoctorId(doctorId);
//		record.setRecordDate(new Date());
//		record.setRecordBgDatetime(new Date());
//		record.setStatus(ModelConstants.DOCTOR_SERVICE_ON);
//		
//		doctorWorkRecordDao.insert(record);
//		resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
//		
//		logger.info(">>>新增上班打卡记录" + record.toString());
//		
//		return true;
//	}
//
//	
//	@Override
//	public boolean serviceOff(Integer doctorId,AbstractResModel resModel){
//		
//		// 查询当天的上班打卡记录
//		DoctorWorkRecord record = doctorWorkRecordDao.getTodayWorkRecord(doctorId, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//		
//		// 未查询到当天的打卡记录，直接无视
//		if (record == null) {
//			resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
//			logger.warn(">>>医生[" + doctorId + "]" + "未查询到需要下班的打卡记录,不需要执行更新操作,请注意...");
//			return true;
//		}
//		
//		// 存在当天的打卡记录，刷新下班时间和状态
//		record.setRecordEndDatetime(new Date());
//		record.setStatus(ModelConstants.DOCTOR_SERVICE_OFF);
//		
//		doctorWorkRecordDao.update(record);
//		resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
//		
//		logger.info(">>>下班打卡刷新" + record.toString());
//		
//		return true;
//	}
//
}
