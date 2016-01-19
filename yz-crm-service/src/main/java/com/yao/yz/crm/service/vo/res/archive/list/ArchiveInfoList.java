package com.yao.yz.crm.service.vo.res.archive.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *	描述：健康档案列表，包含源健康档案和子健康档案
 *	@Author wuwenjun
 *	@Date Nov 4, 2015 10:53:05 AM
 *	@Versin 1.0
 */
public class ArchiveInfoList implements Serializable{
	
	private static final long serialVersionUID = 6088230583212608221L;

	/**
	 * 源健康档案编号
	 */
	private Integer archive_id;
	
	/**
	 * 源健康档案标题
	 */
	private String illness_key;
	
	/**
	 * 源健康档案创建时间，格式：yyyy-MM-dd-HH:mm:ss
	 */
	private String create_time;
	
	/**
	 * 子健康档案列表，包含源健康档案信息
	 */
	private List<ArchiveListInfo> archive_infos;
	
	public ArchiveInfoList(){
		archive_infos = new ArrayList<ArchiveListInfo>();
	}

	public Integer getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(Integer archive_id) {
		this.archive_id = archive_id;
	}

	public String getIllness_key() {
		return illness_key==null?"":illness_key;
	}

	public void setIllness_key(String illness_key) {
		this.illness_key = illness_key;
	}

	public String getCreate_time() {
		return create_time==null?"":create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public List<ArchiveListInfo> getArchive_infos() {
		return archive_infos;
	}

	public void setArchive_infos(List<ArchiveListInfo> archive_infos) {
		this.archive_infos = archive_infos;
	}
}
