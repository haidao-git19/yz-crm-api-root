package com.yao.yz.crm.web.controller.todo;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yao.yz.crm.service.interf.todo.TodoService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.todo.ToDoReq;
import com.yao.yz.crm.service.vo.req.todo.TodoFinishReq;
import com.yao.yz.crm.web.controller.BaseController;

/**
 *	描述：待办事项查询
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 10:54:13 AM
 *	@Versin 1.0
 */
@Controller
@RequestMapping("/todolist")
public class ToDoController extends BaseController{

	private static final Logger logger = Logger.getLogger(ToDoController.class);
	
	@Autowired
	private TodoService todoListService;
	
	/**
	 * 查询待办事项
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/todo",method = {RequestMethod.POST,RequestMethod.GET})
	public Object getTodayList(ToDoReq toDoReq,HttpServletRequest request){
		logger.info("【待办事项查询接口】服务端接收接收到待办事项查询请求,准备处理...");
		try {
			// token验证
			BasicResVo basicResVo = beforeExecute(toDoReq, request);
			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
				return basicResVo;
			}
			
			// 查询待办事项
			return todoListService.getToList(toDoReq);
		} catch (Exception e) {
			logger.error("【待办事项查询接口】接口异常", e);
			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
		}
	}
	
	/**
	 * 完成待办事项
	 * @param todoFinishReq
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/finish",method = {RequestMethod.POST,RequestMethod.GET})
	public Object finishTodo(TodoFinishReq todoFinishReq,HttpServletRequest request) {
		logger.info("【待办事项完成接口】服务端接收到今日待办事项完成请求,准备处理...");
		try {
			// token验证
			BasicResVo basicResVo = beforeExecute(todoFinishReq, request);
			if (ServiceContant.RET_CODE_TIMEOUT == basicResVo.getRet()) {
				return basicResVo;
			}
			
			// 完成待办事项
			return todoListService.todoFinish(todoFinishReq);
		} catch (Exception e) {
			logger.error("【待办事项完成接口】接口异常",e);
			return new BasicResVo().processData(ServiceContant.RET_CODE_ERROR, ServiceContant.EXCEPTION_NOTICE, null);
		}
	}
}
