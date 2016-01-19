package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import com.yao.yz.admin.yzadmin.persistence.dao.YzDoctorDao;
import com.yao.yz.admin.yzadmin.persistence.model.DoctorWorkRecord;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmYzDoctorExt;

import java.util.List;

/**
 * Desc: DoctorDao扩展
 * Author: wuwenjun
 * Date: 2015/09/27 11:30
 */
public interface CrmYzDoctorDaoExt extends YzDoctorDao {

	/**
	 * 功能：根据用户uid和科室查询对应的私人医生列表
	 * @param uid 用户uid
	 * @param department 科室id
	 * @return
	 */
    List<YzDoctor> getPrivateDoctors(Integer uid, Integer department);
    
    /**
     * 功能：查询指定科室的医生
     * @param department 科室id
     * @return
     */
    List<YzDoctor> getDoctorsByDep(Integer department);
    
    /**
     * 功能：查询医生列表
     * @return
     */
    List<YzDoctor> getDoctorList();

//    
//    v2.0版本以后废弃，不再使用
//    
//    /**
//     * 根据坐席号查询医生
//     * @param seat
//     * @version 2.0以后废弃不再使用
//     * @return
//     */
//    @DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
//    YzDoctor getDoctorBySeat(String seat);
//    
    
    /**
     * 根据登录帐号查询医生信息
     * @param account
     * @return
     */
    CrmYzDoctorExt getDoctorByAccount(String account);
    
    /**
     * 查询医生当天的最后一条上班记录
     * @param doctorId
     * @return
     */
    DoctorWorkRecord getLastRecord(Integer doctorId,String date);
    
    /**
     * 根据医生id查询待处理诊单数量
     * @param doctorId
     * @return
     */
    Integer getWaitCount(Integer doctorId);
    
    /**
     * 根据科室查询查询当前在线医生
     * @param department 科室id
     * @param dateString 日期字符串，格式yyyy-MM-dd
     * @return
     */
    List<YzDoctor> getSelfOnLine(Integer department,String dateString);
    
//    /**
//     * 根据科室tag查询医生列表
//     * @param guestDepartment
//     * @return
//     */
//    List<YzDoctor> getByGuestDepartment(Integer guestDepartment);
}
