package com.yao.yz.model.v3.biz.archive;

import com.yao.yz.model.v3.base.req.AbstractReqModel;
import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yz.util.tools.validate.BindValidation;
import com.yz.util.tools.validate.RegexType;

/**
 * 健康档案详情查询请求数据模型
 * 
 * @version 2.0 新增
 * @author wuwenjun
 *
 */
public class ArchiveQueryReq extends AbstractReqModel{
	
	private static final long serialVersionUID = -6108531331092357860L;
	/**
	 * 健康档案编号
	 */
	@BindValidation(_blackable = false, _nullable = false, regexType = RegexType.NUMBER, description = "archive_id")
	private String archive_id;
	
	@Override
	protected boolean customValidate(AbstractResModel abstractResModel) {
		return true;
	}

	public String getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(String archive_id) {
		this.archive_id = archive_id;
	}

	@Override
	public String toString() {
		return "ArchiveQueryReq [archive_id=" + archive_id + "]";
	}
}
