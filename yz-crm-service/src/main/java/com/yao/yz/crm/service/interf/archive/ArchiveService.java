package com.yao.yz.crm.service.interf.archive;

import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.archive.ArchiveListReq;
import com.yao.yz.model.v3.biz.archive.ArchiveQueryReq;
import com.yao.yz.model.v3.biz.archive.ArchiveUpdateReq;

/**
 * 
 * @Description: 健康档案查询
 * @Autor: wuwenjun
 * @Date: 10:38:43 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public interface ArchiveService {

	/**
	 * 查询健康档案详情
	 * @param reqModel 请求数据属模型
	 * @param resModel 返回数据模型
	 * @return
	 */
	void getAchiveDetail(ArchiveQueryReq archiveInfoReq,AbstractResModel resModel);
	
	/**
	 * 跟新健康档案详情
	 * @param archiveUpdateReq 请求数据模型
	 * @param resModel 返回数据模型
	 */
	void updateArchive(ArchiveUpdateReq archiveUpdateReq,AbstractResModel resModel);
	
	/**
	 * 查询健康档案列表
	 * @param archiveListReq 请求数据模型
	 * @param resModel 返回数据模型
	 */
	void getArchiveList(ArchiveListReq archiveListReq,AbstractResModel resModel);
}
