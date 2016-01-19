/**
* <p>Title: YzDoctorDaoExt.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:壹药网 <／p>
* @author wangtao9953
* @date Sep 29, 2015
* @version 1.0
*/
package com.yao.yz.admin.yzadmin.persistence.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yao.yz.admin.yzadmin.persistence.dao.YzDoctorDao;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.ext.YzDoctorExt;

/**
 *<p>Title: YzDoctorDaoExt<／p>
 * <p>Description: <／p>
 * <p>Company: 壹药网<／p> 
 * @author wangtao9953
 * @date Sep 29, 2015
 */
public interface YzDoctorDaoExt extends YzDoctorDao{

	//根据科室查询医生列表
	List<YzDoctor> getYzDoctorsByDepartId(int department);
	
	List<YzDoctorExt>  getDocListByUid(int userId);
	//登录帐号查询
	YzDoctorExt getDocByLoginUserName(@Param("loginUserName") String loginUserName);
	
	YzDoctorExt getDocByMobile(@Param("mobile") String mobile);
	
	List<YzDoctor> getDocListAll();
}
