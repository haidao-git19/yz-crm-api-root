package com.yao.yz.crm.service.vo.res.archive.detail;

import java.io.Serializable;


/**
 *	描述：健康档案详情查询请求返回数据
 *	@Author wuwenjun
 *	@Date Oct 28, 2015 1:11:18 PM
 *	@Versin 1.0
 */
public class ArchiveInfoRes implements Serializable{

	private static final long serialVersionUID = 6508565578449545111L;
	
	private ArchiveInfo archive_info;
	
	public ArchiveInfoRes() {
		archive_info = new ArchiveInfo();
	}

	public ArchiveInfo getArchive_info() {
		return archive_info;
	}

	public void setArchive_info(ArchiveInfo archive_info) {
		this.archive_info = archive_info;
	}
	
}
