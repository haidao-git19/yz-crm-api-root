/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Oct 30, 2015-12:55:14 PM
* @version 1.0
*/
package com.yao.yz.crm.service.impl.avaya;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmMobileAreaDao;
import com.yao.yz.crm.service.interf.avaya.AvayaService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.avaya.AvayaReq;

/**
 * 公司名: 壹药网
 * 类名称：AvayaServiceImpl
 * 类描述：
 * @author wangyulong
 * @date Oct 30, 2015-12:55:14 PM
 */
@Service
public class AvayaServiceImpl implements AvayaService {
	private static final Logger logger = Logger.getLogger(AvayaServiceImpl.class);
	
	@Autowired
	private CrmMobileAreaDao dao;
	
	
	/**
  	 * 功能：根据手机号段获取归属地
  	 * @Author wangyulong
  	 * @param   String
  	 * @return  String
  	 */
	@Override
	public String getMobileArea(String mobile) {
		
		return dao.getMobileArea(mobile);
	}


	@Override
	public BasicResVo getMobileArea(AvayaReq avayaReq) {
		BasicResVo basicResVo = new BasicResVo();
		avayaReq.checkParameter(basicResVo);
		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
			logger.warn("请求参数校验失败[" + avayaReq.toString() + "]");
			return basicResVo;
		}
		String mobileArea=dao.getMobileArea(avayaReq.getMobile().substring(0, 7));
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("mobileArea", mobileArea);
		return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok!", jsonObject);
	}

}
