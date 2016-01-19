package com.yao.yz.crm.service.vo.req.todo;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicReqVo;
import com.yao.yz.crm.service.vo.BasicResVo;

/**
 *	描述：待办事项请求参数
 *	@Author wuwenjun
 *	@Date Oct 28, 2015 3:49:02 PM
 *	@Versin 1.0
 */
public class ToDoReq extends BasicReqVo{

	private static final long serialVersionUID = 8555576553230871496L;
	
	private static final Logger logger = Logger.getLogger(ToDoReq.class);

	/**
	 * 待办事项类型，N-今日，F-明日
	 */
	private String todo_type;

	@Override
	public void checkParameter(BasicResVo basicResVo) {
		if (StringUtils.isBlank(this.todo_type)) {
			logger.warn("【待办事项查询接口】请求类型为空,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "请求类型为空", null);
			return;
		} else if (!ServiceContant.REQ_TYPE_F.equals(todo_type) && !ServiceContant.REQ_TYPE_N.equals(todo_type)){
			logger.warn("【待办事项查询接口】请求类型[todoType=" + this.todo_type + "]非法,请注意...");
			basicResVo.processData(ServiceContant.RET_CODE_ERROR, "请求类型非法", null);
		}
	}

	public String getTodo_type() {
		return todo_type;
	}

	public void setTodo_type(String todo_type) {
		this.todo_type = todo_type;
	}
	
}
