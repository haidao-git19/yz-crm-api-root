package com.yao.yz.crm.service.interf.archive.v2;

import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
import com.yao.yz.admin.yzadmin.persistence.model.YzInquery;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.archive.v2.ArchiveSaveReq;

/**
 * 诊单保存服务，新增诊单对应的健康档案
 * @author wuwenjun
 * @version 2.0新增
 *
 */
public interface ArchiveSaveService {
	
	/**
	 * 新增诊单对应的健康档案
	 * @param archiveNewReq
	 * @return
	 */
	BasicResVo saveArchive(ArchiveSaveReq archiveNewReq);
	
	/**
	 * 执行业务，供saveArchive调用
	 * @param yzInquery
	 * @param archiveNewReq
	 * @param yzDoctor
	 */
	void doBusiness(YzInquery yzInquery, ArchiveSaveReq archiveNewReq,YzDoctor yzDoctor);
	
}
