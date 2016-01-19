/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-11:03:24 AM
* @version 1.0
*/
package com.yao.yz.crm.service.interf.inquery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

//import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
import com.yao.yz.crm.service.vo.res.Inquery;
import com.yao.yz.crm.service.vo.res.Inquery2;
import com.yao.yz.model.v3.biz.inquery.AllListVo;
//import com.yao.yz.model.v3.biz.inquery.UndoListReq;

/**
 * 公司名: 壹药网
 * 类名称：InqueryService
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-11:03:24 AM
 */
public interface InqueryQueryService {

	/**
  	 * 功能：来电清单列表显示
  	 * @Author wangyulong
  	 * @param 
  	 * @return  List
  	 */
	List<Inquery> getInqueryList();
	
	/**
  	 * 功能：来电记录完成
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int 
  	 */
	String checkFinish(int id);
	
	int finish(int id);
	
	/**
  	 * 功能：更新process_flag为Y
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  int 
  	 */
	int updateProcessFlag(int id);
	
	public Map<String, Object> getPagerList(Map<String, Object> input, RowBounds bounds);
	
	int getCallDuration(int id);
	
	int insertCallDuration(Integer call_duration,Integer id);
	
	int getUndoCount(String start_time, String end_time);
	
	/**
  	 * 功能：V1.3     获取某个医生的待处理诊单列表
  	 * @Author wangyulong
  	 * @param   int
  	 * @return  list 
  	 */
	List<Inquery2> getUndoListV3(Map<String, Object> input);
	
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
  	 * @return  list 
  	 */
	List<Inquery2> getHistoryListV3(Map<String, Object> input, RowBounds rowBounds);
	
	/**
  	 * 功能：V1.3     获取某个医生的已处理诊单数量
  	 * @Author wangyulong
  	 * @param   Map
  	 * @return  int 
  	 */
	int getHistoryCountV3(Map<String, Object> input);
	
	/**
  	 * 功能：V1.3     获取某个医生的已处理诊单数量
  	 * @Author wangyulong
  	 * @param   Map
  	 * @return  int 
  	 */
	YzDoctor getDoctorByLoginName(String loginName);
	
	/**
  	 * 功能：V1.3      查询所有诊单
  	 * @Author wangyulong
  	 * @param   Map
  	 * @return  list 
  	 */
	List<AllListVo> getAllListV3(Map<String, Object> input, RowBounds rowBounds);
	
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
	
	/**
	 * 根据诊单id查询诊单信息
	 * @param inqueryI 诊单id
	 * @return
	 */
	YzInquery getYzInqueryById(Integer inqueryId);
	
	
}
