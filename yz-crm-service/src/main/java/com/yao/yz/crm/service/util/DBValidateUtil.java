//package com.yao.yz.crm.service.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.yao.yz.admin.yzadmin.persistence.dao.YzArchiveDao;
//import com.yao.yz.admin.yzadmin.persistence.dao.YzInqueryDao;
//import com.yao.yz.admin.yzadmin.persistence.dao.YzUserDao;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzDoctorDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzMetaDepartmentDaoExt;
//import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
//// v2.0版本以后废弃，不再使用
////import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
//
///**
// *	描述：数据合法性校验工具，用于校验调用方传递的参数是否有效
// *	@Author wuwenjun
// *	@Date Nov 5, 2015 4:26:07 PM
// *	@Versin 1.0
// */
//@Component
//public class DBValidateUtil {
//	
//	private static YzUserDao yzUserDao;
//	
//	private static CrmYzDoctorDaoExt yzDoctorDao;
//	
//	private static YzArchiveDao yzArchiveDao;
//	
//	private static CrmYzMetaDepartmentDaoExt yzMetaDepartmentDaoExt;
//	
//	private static YzInqueryDao yzInqueryDao;
//	
//	/**
//	 * 检查医生是否存在
//	 * @param doctor_id
//	 * @return
//	 */
//	public static boolean checkDoctor(Integer doctorId){
//		if (yzDoctorDao.getYzDoctorByKey(doctorId) != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	/**
//	 * 检查科室是否存在
//	 * @param tagKey
//	 * @return
//	 */
//	public static boolean checkDepartment(Integer tagKey) {
//		if (yzMetaDepartmentDaoExt.getDepartmentByTagKey(tagKey) != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * 检查健康档案是否存在
//	 * @param archiveId
//	 * @return
//	 */
//	public static boolean checkArchive(Integer archiveId) {
//		if (yzArchiveDao.getYzArchiveByKey(archiveId) != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	/**
//	 * 检查用户是否存在
//	 * @param uid
//	 * @return true=有效
//	 */
//	public static boolean checkUser(Integer uid) {
//		if (yzUserDao.getYzUserByKey(uid) != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	/**
//	 * 检查来电清单是否存在
//	 * @param inqueryId
//	 * @return
//	 */
//	public static boolean checkInquery(Integer inqueryId) {
//		if (yzInqueryDao.getYzInqueryByKey(inqueryId) != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	/**
//	 * 检查健康档案是否为源健康档案
//	 * @param archiveId
//	 * @return
//	 */
//	public static boolean checkSourceArchive(Integer archiveId) {
//		YzArchive yzArchive = yzArchiveDao.getYzArchiveByKey(archiveId);
//		if (yzArchive == null || yzArchive.getSourceId() != null) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//	
////	
////	v2.0版本以后废弃，不再使用
////	/**
////	 * 检查登录账号是否为医生坐席
////	 * @param userName 登录账号
////	 * @return true-医生坐席
////	 */
////	public static boolean checkIsDoctor(String userName) {
////		YzDoctor yzDoctor = yzDoctorDao.getDoctorBySeat(userName);
////		if (yzDoctor == null) {
////			return false;
////		} else {
////			return true;
////		}
////	}
////	
//	
//	
//	public YzUserDao getYzUserDao() {
//		return yzUserDao;
//	}
//	
//	@Autowired
//	public void setYzUserDao(YzUserDao yzUserDao) {
//		DBValidateUtil.yzUserDao = yzUserDao;
//	}
//
//	public CrmYzDoctorDaoExt getYzDoctorDao() {
//		return yzDoctorDao;
//	}
//
//	@Autowired
//	public void setYzDoctorDao(CrmYzDoctorDaoExt yzDoctorDao) {
//		DBValidateUtil.yzDoctorDao = yzDoctorDao;
//	}
//
//	public YzArchiveDao getYzArchiveDao() {
//		return yzArchiveDao;
//	}
//
//	@Autowired
//	public void setYzArchiveDao(YzArchiveDao yzArchiveDao) {
//		DBValidateUtil.yzArchiveDao = yzArchiveDao;
//	}
//
//	public YzInqueryDao getYzInqueryDao() {
//		return yzInqueryDao;
//	}
//
//	@Autowired
//	public void setYzInqueryDao(YzInqueryDao yzInqueryDao) {
//		DBValidateUtil.yzInqueryDao = yzInqueryDao;
//	}
//	
//	public CrmYzMetaDepartmentDaoExt getYzMetaDepartmentDaoExt() {
//		return yzMetaDepartmentDaoExt;
//	}
//
//	@Autowired
//	public void setYzMetaDepartmentDaoExt(
//			CrmYzMetaDepartmentDaoExt yzMetaDepartmentDaoExt) {
//		DBValidateUtil.yzMetaDepartmentDaoExt = yzMetaDepartmentDaoExt;
//	}
//}
