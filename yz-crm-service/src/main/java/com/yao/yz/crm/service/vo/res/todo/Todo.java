package com.yao.yz.crm.service.vo.res.todo;

import java.io.Serializable;

/**
 *	描述：待办事项
 *	@Author wuwenjun
 *	@Date Oct 26, 2015 9:56:40 AM
 *	@Versin 1.0
 */
public class Todo implements Serializable{

	private static final long serialVersionUID = -1393315029862357067L;

	private Integer archive_id;
	
	private String book_start;
	
	private String book_end;
	
	private String user_call;
	
	private String book_desc;
	
	private String book_doctor;
	
	private Integer uid;

	public Integer getArchive_id() {
		return archive_id;
	}

	public void setArchive_id(Integer archive_id) {
		this.archive_id = archive_id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getBook_start() {
		return book_start==null?"":book_start;
	}

	public void setBook_start(String book_start) {
		this.book_start = book_start;
	}

	public String getBook_end() {
		return book_end==null?"":book_end;
	}

	public void setBook_end(String book_end) {
		this.book_end = book_end;
	}

	public String getUser_call() {
		return user_call==null?"":user_call;
	}

	public void setUser_call(String user_call) {
		this.user_call = user_call;
	}

	public String getBook_desc() {
		return book_desc==null?"":book_desc;
	}

	public void setBook_desc(String book_desc) {
		this.book_desc = book_desc;
	}

	public String getBook_doctor() {
		return book_doctor==null?"":book_doctor;
	}

	public void setBook_doctor(String book_doctor) {
		this.book_doctor = book_doctor;
	}
}
