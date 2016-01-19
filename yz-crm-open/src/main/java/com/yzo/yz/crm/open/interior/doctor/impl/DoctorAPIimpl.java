package com.yzo.yz.crm.open.interior.doctor.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yao.yz.admin.yzadmin.persistence.model.DoctorWorkRecord;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzDoctorExt;
import com.yao.yz.crm.service.interf.doctor.DoctorService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.model.v3.base.ModelConstants;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.doctor.ServiceOffReq;
import com.yao.yz.model.v3.biz.doctor.ServiceOnReq;
import com.yao.yz.model.v3.biz.doctor.ServiceStatusReq;
import com.yao.yz.model.v3.biz.doctor.ServiceStatusRes;
import com.yao.yz.model.v3.biz.doctor.WaitCountReq;
import com.yao.yz.model.v3.biz.doctor.WaitCountRes;
import com.yao.yz.model.v3.biz.login.DoctorInfoBean;
import com.yao.yz.model.v3.biz.login.LoginUserReq;
import com.yao.yz.model.v3.biz.login.SeatInfoBean;
import com.yao.yz.util.exception.YzRuntimeException;
import com.yzo.yz.crm.open.interior.doctor.DoctorAPI;

/**
 * 
 * @Description: 医生信息API接口实现类
 * @Autor: wuwenjun
 * @Date: 5:27:11 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */
@Component
public class DoctorAPIimpl implements DoctorAPI{
	
	/**
	 * 坐席号码校验
	 */
	private static final String REG_SEAT_NUM = "^[7][0-9]{4}$";
	
	@Autowired
	private com.yao.yz.biz.diagnosis.api.DoctorService doctorService2;
	
	@Autowired
	private DoctorService doctorService;
	
	@Override
	public void getLoginInfo(LoginUserReq loginUserReq,
			AbstractResModel resModel) {
		
		// log
		logger.info(">>> 开始处理登录帐号绑定医生信息查询请求" + loginUserReq.toString());
		
		// 参数检查
		if (!loginUserReq.validate(resModel)) {
			return;
		}
		
		// 医生信息
		CrmYzDoctorExt yzDoctor = doctorService.getByLoginUserName(loginUserReq.getUser_name());
		if (null == yzDoctor) {
			resModel.setPublicData(ModelConstants.RET_CODE_ERROR, 20000);
			return;
		}
		DoctorInfoBean doctorInfoBean = new DoctorInfoBean();
		doctorInfoBean.setId(yzDoctor.getId());
		doctorInfoBean.setDoctor_type(yzDoctor.getDoctorType());
		doctorInfoBean.setFirst_name(yzDoctor.getFirstName());
		doctorInfoBean.setSecond_name(yzDoctor.getSecondName());
		doctorInfoBean.setFace(yzDoctor.getFace());
		doctorInfoBean.setDepartment_name(yzDoctor.getDepartmentName());
		
		// 坐席信息
		SeatInfoBean seatInfoBean = new SeatInfoBean();
		if (StringUtils.isNotBlank(yzDoctor.getSeat())) {
			if (yzDoctor.getSeat().matches(REG_SEAT_NUM)) {
				seatInfoBean.setIs_seat(true);
				seatInfoBean.setSeat_num(Integer.parseInt(yzDoctor.getSeat()));
				seatInfoBean.setCall_num(Integer.parseInt(yzDoctor.getSeat().substring(1)));
			} else {
				seatInfoBean.setIs_seat(false);
			}
		} else {
			seatInfoBean.setIs_seat(false);
		}

		resModel.setPublicData(ModelConstants.RET_CODE_RIGHT, 10000);
		resModel.setPrivateData(doctorInfoBean.beanName(), doctorInfoBean);
		resModel.setPrivateData(seatInfoBean.beanName(), seatInfoBean);
		
		logger.info(">>> 登录帐号查询绑定医生信息请求处理完毕");
	}

	
	@Override
	public void serviceOn(ServiceOnReq serviceOnReq, AbstractResModel resModel) {
		
		logger.info(">>> 开始新增医生上班打卡记录" + serviceOnReq.toString());
		
		// 请求校验
		if (!serviceOnReq.validate(resModel)){
			return;
		}
		
		// 上班打卡
		try {
			doctorService2.serviceOn(Integer.parseInt(serviceOnReq.getDoctor_id()));
		} catch (YzRuntimeException e) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, e.getErrorCode());
			logger.error(e.getErrInlineMsg(),e);
			return;
		}
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
	}

	@Override
	public void serviceOff(ServiceOffReq serviceOffReq,AbstractResModel resModel) {
		logger.info(">>> 开始更新医生下班打卡记录" + serviceOffReq.toString());
		
		// 请求校验
		if (!serviceOffReq.validate(resModel)) {
			return;
		}
		
		// 下班打卡
		try {
			doctorService2.serviceOff(Integer.parseInt(serviceOffReq.getDoctor_id()));
		} catch (YzRuntimeException e) {
			resModel.setPublicData(ServiceContant.RET_CODE_ERROR, e.getErrorCode());
			logger.error(e.getErrInlineMsg(),e);
			return;
		}
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
	}


	@Override
	public void serviceStatus(ServiceStatusReq serviceStatusReq,
			AbstractResModel resModel) {
		// 请求校验
		if (!serviceStatusReq.validate(resModel)) {
			return;
		}
		
		// 查询最后一条医生上班记录
		DoctorWorkRecord record = doctorService.getLastWorkRecord(Integer.parseInt(serviceStatusReq.getDoctor_id()));
		ServiceStatusRes serviceStatusRes = new ServiceStatusRes();
		
		serviceStatusRes.setDoctor_id(Integer.parseInt(serviceStatusReq.getDoctor_id()));
		if (null == record) {
			serviceStatusRes.setStatus(ModelConstants.DOCTOR_SERVICE_OFF);
		} else {
			serviceStatusRes.setStatus(record.getStatus());
		}
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		resModel.setPrivateData(serviceStatusRes.beanName(), serviceStatusRes);
	}

	@Override
	public void waitCountList(WaitCountReq waitCountReq,
			AbstractResModel resModel) {
		// log
		logger.info(">>> 开始查询指定科室的医生待处理诊单数量列表" + waitCountReq.toString());
		
		// 参数校验
		if (!waitCountReq.validate(resModel)) {
			return;
		}
		
		// 查询医生列表
		List<YzDoctor> doctors = doctorService.getSelfOnLine(Integer.parseInt(waitCountReq.getGuest_department()));
		List<WaitCountRes> waitCountRes = new ArrayList<WaitCountRes>();
		if (doctors != null && doctors.size() > 0) {
			for (YzDoctor yzDoctor : doctors) {
				
				// 过滤掉自身
//				if (yzDoctor.getLoginUserName() != null && yzDoctor.getLoginUserName().equals(waitCountReq.getUser_name())) {
//					continue;
//				}
				
				WaitCountRes b = new WaitCountRes();
				b.setDoctor_id(yzDoctor.getId());
				b.setDoctor_name(yzDoctor.getSecondName() + yzDoctor.getFirstName());
				b.setInquery_count(doctorService.getWaitCount(yzDoctor.getId()));
				
				waitCountRes.add(b);
			}
			Collections.sort(waitCountRes);
		}
		
		resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		resModel.setPrivateData("doctor_inquerys", waitCountRes);
		
		logger.info(">>> 查询指定科室的医生待处理诊单数量列表完成");
	}


	@Override
	public void loginOut(ServiceOffReq serviceOffReq, AbstractResModel resModel) {
		
		// log
		logger.info(">>> 开始进行退出系统流程..." + serviceOffReq);
		
		// 参数校验
		if (!serviceOffReq.validate(resModel)) {
			return;
		}
		
		// 查询当天是否存在未下班记录
		DoctorWorkRecord doctorWorkRecord = doctorService.getLastWorkRecord(Integer.parseInt(serviceOffReq.getDoctor_id()));
		if (null == doctorWorkRecord) {
			logger.warn(">>> 不存在未下班记录，无需打卡,请注意...");
			resModel.setPublicData(ServiceContant.RET_CODE_SUCCESS, 10000);
		} else { // 调用打卡
			try {
				doctorService2.serviceOff(Integer.parseInt(serviceOffReq.getDoctor_id()));
			} catch (YzRuntimeException e) {
				resModel.setPublicData(ServiceContant.RET_CODE_ERROR, e.getErrorCode());
				logger.error(e.getErrInlineMsg(),e);
			}
		}
		logger.info(">>> 退出系统流程结束...");
	}
}
