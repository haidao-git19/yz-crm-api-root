package com.yao.yz.crm.service.interf.archive.query;

import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveListReq;

/**
 *	描述：查询健康档案列表接口
 *	@Author wuwenjun
 *	@Date Nov 5, 2015 9:21:32 AM
 *	@Versin 1.0
 */
public interface ArchiveListService {
	
	BasicResVo archiveList(ArchiveListReq archiveListReq);
	
}
