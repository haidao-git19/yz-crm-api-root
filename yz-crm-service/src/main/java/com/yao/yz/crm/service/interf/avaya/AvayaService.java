/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Oct 30, 2015-12:54:29 PM
* @version 1.0
*/
package com.yao.yz.crm.service.interf.avaya;

import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.avaya.AvayaReq;

/**
 * 公司名: 壹药网
 * 类名称：AvayaService
 * 类描述：
 * @author wangyulong
 * @date Oct 30, 2015-12:54:29 PM
 */
public interface AvayaService {

	
	/**
  	 * 功能：根据手机号段获取归属地
  	 * @Author wangyulong
  	 * @param   String
  	 * @return  String
  	 */
	String getMobileArea(String mobile);
	BasicResVo getMobileArea(AvayaReq avayaReq);
}
