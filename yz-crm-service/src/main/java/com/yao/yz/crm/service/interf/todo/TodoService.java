package com.yao.yz.crm.service.interf.todo;

import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.todo.ToDoReq;
import com.yao.yz.crm.service.vo.req.todo.TodoFinishReq;

/**
 *	描述：待办事项服务接口
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 9:54:14 AM
 *	@Versin 1.0
 */
public interface TodoService {
	
	/**
	 * 查询待办事项
	 * @param toDoReq
	 * @return
	 */
	BasicResVo getToList(ToDoReq toDoReq);

	
	/**
	 * 完成待办事项
	 * @param todoFinishReq
	 * @return
	 */
	BasicResVo todoFinish(TodoFinishReq todoFinishReq);
	
	
}
