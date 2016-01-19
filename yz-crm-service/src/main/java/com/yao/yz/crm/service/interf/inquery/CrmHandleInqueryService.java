package com.yao.yz.crm.service.interf.inquery;

import org.apache.log4j.Logger;

import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.inquery.InqueryCancelReq;
import com.yao.yz.model.v3.biz.inquery.InqueryFinishReq;
import com.yao.yz.model.v3.biz.inquery.InqueryHandleReq;
import com.yao.yz.model.v3.biz.inquery.InqueryStatusReq;
import com.yao.yz.model.v3.biz.inquery.InqueryTransferReq;

/**
 * 
 * @Description: 诊单API
 * @Autor: wuwenjun
 * @Date: 4:43:19 PM Jan 4, 2016
 * @Version: v1.0
 * 
 */

public interface CrmHandleInqueryService {
	
	static final Logger logger = Logger.getLogger(CrmHandleInqueryService.class);

	/**
	 * 处理诊单</br>
	 * 1.查询诊单信息</br>
	 * 2.更新诊单更新时间、处理状态</br>
	 * 3.返回处理之后的结果信息</br>
	 * @param inqueryHandleReq 处理诊单请求
	 * @param resModel 返回数据模型
	 */
	void handleInquery(InqueryHandleReq inqueryHandleReq,AbstractResModel resModel);
	
	/**
	 * 完成诊单</br>
	 * @param inqueryFinishReq
	 * @param resModel
	 */
	void finishInquery(InqueryFinishReq inqueryFinishReq,AbstractResModel resModel);

	/**
	 * 取消诊单
	 * @param inqueryCancelReq
	 * @param resModel
	 */
	void cancelInquery(InqueryCancelReq inqueryCancelReq,AbstractResModel resModel);
	
	/**
	 * 查询诊单状态
	 * @param inqueryStatusReq
	 * @param resModel
	 */
	void queryInqueryStatus(InqueryStatusReq inqueryStatusReq, AbstractResModel resModel);
	
	/**
	 * 转诊
	 * @param inqueryTransferReq 转诊请求
	 * @param resModel 返回数据模型
	 */
	void transferInquery(InqueryTransferReq inqueryTransferReq,AbstractResModel resModel);
}
