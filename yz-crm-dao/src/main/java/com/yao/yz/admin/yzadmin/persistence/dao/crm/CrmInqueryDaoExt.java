/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-10:19:48 AM
* @version 1.0
*/
package com.yao.yz.admin.yzadmin.persistence.dao.crm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yao.yz.admin.yzadmin.persistence.dao.DataSourceConstants;
import com.yao.yz.admin.yzadmin.persistence.dao.YzInqueryDao;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.crm.CrmInqueryExt;
import com.yao.yz.util.datasource.DataSource;

/**
 * 公司名: 壹药网
 * 类名称：InqueryDaoExt
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-10:19:48 AM
 */
public interface CrmInqueryDaoExt extends YzInqueryDao{

	/**
  	 * 功能：来电清单列表显示
  	 * @Author wangyulong
  	 * @param 
  	 * @return  List
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmInqueryExt> getInqueryList();
	
	/**
  	 * 功能：来电记录完成
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int 
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int finish(int id);
	
	/**
  	 * 功能：更新process_flag为Y
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int 
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int updateProcessFlag(int id);
	
	
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmInqueryExt> getPagerList(Map<String, Object> whereStr, RowBounds rowBounds);
	
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	int getListCount(Map<String, Object> whereStr);
	
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int cancelInquery(int id);
	
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	int getCallDuration(int id);
	
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int insertCallDuration(Integer call_duration,Integer id);

	Integer getUndoCount(String start_time, String end_time);
	
	/**
	 * 更新诊单信息
	 * @param id 诊单id
	 * @param finishFlag 完成标识
	 * @param opAccountName 操作医生帐号
	 * @param archiveId 对应健康档案id
	 * @param opDoctorName 操作医生姓名
	 * @param opDoctorId 操作医生id
	 * @return
	 */
	@DataSource(DataSourceConstants.DATASOURCE_W_YZADMIN)
	int updateInqueryStatus(Integer id,String finishFlag, String opAccountName,Integer archiveId,String opDoctorName,Date updateTime,Integer opDoctorId);
	
	
	/**
  	 * 功能：V1.3     获取某个医生的待处理诊单列表
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  list 
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmInqueryExt> getUndoListV3(Map<String, Object> input);
	
	/**
  	 * 功能：V1.3     获取某个医生的待处理诊单数量
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int 
  	 */
	int getUndoCountV3(int doctor_id);
	
	/**
  	 * 功能：V1.3     获取某个医生的已处理诊单列表
  	 * @Author wangyulong
  	 * @param   Map
  	 * @return  List 
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmInqueryExt> getHistoryListV3(Map<String, Object> input, RowBounds rowBounds);
	
	/**
  	 * 功能：V1.3     获取某个医生的已处理诊单数量
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int 
  	 */
	int getHistoryCountV3(Map<String, Object> input);
	
	/**
  	 * 功能：V1.3   根据Loginname获取医生信息
  	 * @Author wangyulong
  	 * @param   userName
  	 * @return  YzDoctor 
  	 */
	YzDoctor getDoctorByLoginName(String loginName);
	
	/**
  	 * 功能：V1.3    查询所有诊单
  	 * @Author wangyulong
  	 * @param   Map
  	 * @return  List 
  	 */
	@DataSource(DataSourceConstants.DATASOURCE_R_YZADMIN)
	List<CrmInqueryExt> getAllListV3(Map<String, Object> input, RowBounds rowBounds);
	
	/**
  	 * 功能：V1.3     获取所有诊单数量
  	 * @Author wangyulong
  	 * @param   Map
  	 * @return  int 
  	 */
	int getAllListCountV3(Map<String, Object> input);
	
	/**
  	 * 功能：V1.3     获取分单失败数量
  	 * @Author wangyulong
  	 * @param   Map
  	 * @return  int 
  	 */
	int getFailedAssignCountV3();
}
