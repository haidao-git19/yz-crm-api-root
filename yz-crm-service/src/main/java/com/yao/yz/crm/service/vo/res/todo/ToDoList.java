package com.yao.yz.crm.service.vo.res.todo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *	描述：待办事项列表
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 10:02:02 AM
 *	@Versin 1.0
 */
public class ToDoList implements Serializable{
	
	private static final long serialVersionUID = 3044956858937909301L;
	
	private List<Todo> todo_list;
	
	public ToDoList() {
		todo_list = new ArrayList<Todo>();
	}

	public List<Todo> getTodo_list() {
		return todo_list;
	}

	public void setTodo_list(List<Todo> todo_list) {
		this.todo_list = todo_list;
	}
}
