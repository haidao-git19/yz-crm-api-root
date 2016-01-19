package com.yao.yz.crm.service.impl.metadata;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzDoctorDaoExt;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzMetaDepartmentDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.YzMetaDepartment;
import com.yao.yz.crm.service.interf.metadata.MetaDataService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.res.department.DepartmentInfo;
import com.yao.yz.crm.service.vo.res.department.DepartmentList;
import com.yao.yz.crm.service.vo.res.doctor.DoctorInfo;
import com.yao.yz.crm.service.vo.res.doctor.DoctorList;

/**
 *	描述：数据字典服务接口
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 6:33:11 PM
 *	@Versin 1.0
 */
@Service
public class MetaDataServiceImpl implements MetaDataService{
	
	private static final Logger logger = Logger.getLogger(MetaDataServiceImpl.class);

	@Autowired
	private CrmYzMetaDepartmentDaoExt yzMetaDepartmentDaoExt;
	
	@Autowired
	private CrmYzDoctorDaoExt yzDoctorDaoExt;
	
	@Override
	public BasicResVo getDepartmentList() {
		BasicResVo basicResVo = new BasicResVo();
		DepartmentList departmentList = new DepartmentList();

		try {
			List<YzMetaDepartment> departments = yzMetaDepartmentDaoExt.getDepartments();
			if (departments != null) {
				for (YzMetaDepartment department : departments) {
					DepartmentInfo departmentInfo = new DepartmentInfo();
					departmentInfo.setName(department.getName());
					departmentInfo.setTag_key(department.getTagKey());
					departmentList.getDepartment_list().add(departmentInfo);
				}
			} else {
				logger.warn("【数据字典接口】未查询到科室列表信息，请注意...");
				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "未查询到科室列表信息", null);
			}
			logger.info("【数据字典接口】查询科室列表成功...");
			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", departmentList);
		} catch (Exception e) {
			logger.error("【数据字典接口】查询科室列表异常", e);
			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "查询数据库失败", null);
		}
	}

	@Override
	public BasicResVo getDoctorList() {
		BasicResVo basicResVo = new BasicResVo();
		DoctorList doctorList = new DoctorList();
		
		try {
			List<YzDoctor> doctors = yzDoctorDaoExt.getDoctorList();
			if (doctors != null) {
				for (YzDoctor doctor : doctors) {
					DoctorInfo doctorInfo = new DoctorInfo();
					doctorInfo.setDoctor_id(doctor.getId());
					// 拼接医生信息，secondName + firstName + 空格 + 医院
					doctorInfo.setDoctor_info(doctor.getSecondName() + doctor.getFirstName() + " " + doctor.getHostpital());
					doctorList.getDoctor_list().add(doctorInfo);
				}
			} else {
				logger.warn("【数据字典接口】未查询到医生列表,请注意...");
				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "未查询到医生列表", null);
			}
			logger.info("【数据字典接口】查询医生信息列表成功...");
			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", doctorList);
		} catch (Exception e) {
			logger.error("【数据字典接口】查询医生信息异常",e);
			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "查询市数据库失败", doctorList);
		}
	}
	
}
