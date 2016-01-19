package com.yao.yz.crm.service.vo.res.archive.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *	描述：健康档案列表返回数据
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 1:08:44 PM
 *	@Versin 1.0
 */
public class ArchiveListRes implements Serializable{

	private static final long serialVersionUID = -1101563320641936574L;
	
	private List<ArchiveInfoList> archive_list;
	
	public ArchiveListRes() {
		archive_list = new ArrayList<ArchiveInfoList>();
	}

	public List<ArchiveInfoList> getArchive_list() {
		return archive_list;
	}

	public void setArchive_list(List<ArchiveInfoList> archive_list) {
		this.archive_list = archive_list;
	}
	
}
