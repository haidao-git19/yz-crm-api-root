package com.yao.yz.crm.service.interf.doctor;

import java.util.List;

import com.yao.yz.admin.yzadmin.persistence.model.DoctorWorkRecord;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzDoctorExt;

/**
 * 
 * @Description: 医生服务接口
 * @Autor: wuwenjun
 * @Date: 4:57:54 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public interface DoctorService {
	
//	/**
//	 * 根据医生id查询分配给该医生的未处理诊单数量
//	 * @param doctorId
//	 * @return
//	 */
//	Integer undoCount(Integer doctorId);
	
	
//	/**
//	 * 新增一条医生打卡记录,先查询当天的上班打卡记录，存在上班打卡记录且状态为上班状态时，不做任何处理，直接返回true；否则新增一条上班打卡记录
//	 * @param doctorId 医生id
//	 * @return
//	 */
//	boolean serviceOn(Integer doctorId,AbstractResModel resModel);
//	
//	/**
//	 * 更新当天上班打卡记录的下班时间、下班状态，先查询当天的上班打卡记录，当天不存在打卡记录时返回false；存在打卡记录时，更新下班时间和下班状态为已下班
//	 * @param doctorId 医生id
//	 * @return
//	 */
//	boolean serviceOff(Integer doctorId,AbstractResModel resModel);

	/**
	 * 根据登录帐号查询对应的医生信息
	 * @param loginUserName 登录帐号
	 * @return
	 */
	CrmYzDoctorExt getByLoginUserName(String loginUserName);
	
	/**
	 * 查询医生当天最新一条上班打卡记录
	 * @param doctorId 医生id
	 * @return
	 */
	DoctorWorkRecord getLastWorkRecord(Integer doctorId);
	
	/**
	 * 查询自营医生待处理诊单数量
	 * @param doctorId
	 * @return
	 */
	Integer getWaitCount(Integer doctorId);
	
	/**
	 * 根据科室查询医生列表
	 * @param guestDepartment
	 * @return
	 */
	List<YzDoctor> getSelfOnLine(Integer guestDepartment);
}
