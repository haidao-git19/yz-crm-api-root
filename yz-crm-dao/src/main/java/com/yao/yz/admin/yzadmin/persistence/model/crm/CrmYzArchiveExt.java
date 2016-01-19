package com.yao.yz.admin.yzadmin.persistence.model.crm;

import com.yao.yz.admin.yzadmin.persistence.model.YzArchive;
import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;

/**
 *	描述：
 *	@Author wuwenjun
 *	@Date Oct 11, 2015 12:55:34 PM
 *	@Versin 1.0
 */
public class CrmYzArchiveExt extends YzArchive{

	private static final long serialVersionUID = 4598245831680267849L;

	// 预约医生
	private YzDoctor bookDoctor;

	// 处理医生
	private YzDoctor questDoctor;

	// 预约科室名称
	private String bookDepName;

	// 处理科室名称
	private String questDepName;
	
	public String getQuestDepName() {
		return questDepName;
	}

	public void setQuestDepName(String questDepName) {
		this.questDepName = questDepName;
	}

	public String getBookDepName() {

		return bookDepName;
	}

	public void setBookDepName(String bookDepName) {
		this.bookDepName = bookDepName;
	}

	public YzDoctor getQuestDoctor() {

		return questDoctor;
	}

	public void setQuestDoctor(YzDoctor questDoctor) {
		this.questDoctor = questDoctor;
	}

	public YzDoctor getBookDoctor() {

		return bookDoctor;
	}

	public void setBookDoctor(YzDoctor bookDoctor) {
		this.bookDoctor = bookDoctor;
	}
}
