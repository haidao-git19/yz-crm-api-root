package com.yao.yz.crm.service.impl.inquerysms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzInquerySmsDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquerySmsLog;
import com.yao.yz.crm.service.interf.inquersms.InquerySmsService;

/**
 * 
 * @Description: 诊单短信记录服务
 * @Autor: wuwenjun
 * @Date: 8:51:56 PM Jan 5, 2016
 * @Version: v1.0
 * 
 */
@Service
public class InquerySmsServiceImpl implements InquerySmsService{
	
	@Autowired
	private CrmYzInquerySmsDaoExt yzInquerySmsDaoExt;

	@Override
	public List<YzInquerySmsLog> getByInqueryId(Integer inqueryId) {
		return yzInquerySmsDaoExt.getByInqueryId(inqueryId);
	}
}
