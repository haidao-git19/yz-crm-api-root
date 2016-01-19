package com.yao.yz.crm.service.vo.req.todo;

import org.apache.commons.lang.StringUtils;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;

/**
 *	描述：待办事项完成请求
 *	@Author wuwenjun
 *	@Date Oct 27, 2015 3:10:59 PM
 *	@Versin 1.0
 */
public class TodoFinishReq extends BasicReqVo{

	private static final long serialVersionUID = 6632035571246289882L;
	
	/**
	 * 待办事项的健康档案编号
	 */
	private String archive_id;

	public String getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(String archive_id) {
		this.archive_id = archive_id;
	}

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		if (StringUtils.isBlank(archive_id)) {
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案编号为空", null);
			return;
		} else if (!this.archive_id.matches(ServiceContant.REG_NUMBER)) {
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "健康档案编号格式非法", null);
		}
	}
}
