/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-11:09:32 AM
* @version 1.0
*/
package com.yao.yz.crm.service.vo.res;

import java.io.Serializable;

/**
 * 公司名: 壹药网
 * 类名称：Inquery
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-11:09:32 AM
 */
public class Inquery implements Serializable{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= 6079315430559055814L;

	private String id;
	private String create_date;
	private String create_time;
	private String finish_flag;
	private String process_flag;
	private String op_account_id;
	private String op_account_name;
	private String user_name;
	private String department;
	private String user_id;
	private String self_desc;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getSelf_desc() {
		return self_desc;
	}
	public void setSelf_desc(String self_desc) {
		this.self_desc = self_desc;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getProcess_flag() {
		return process_flag;
	}
	public void setProcess_flag(String process_flag) {
		this.process_flag = process_flag;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getFinish_flag() {
		return finish_flag;
	}
	public void setFinish_flag(String finish_flag) {
		this.finish_flag = finish_flag;
	}
	public String getOp_account_id() {
		return op_account_id;
	}
	public void setOp_account_id(String op_account_id) {
		this.op_account_id = op_account_id;
	}
	public String getOp_account_name() {
		return op_account_name;
	}
	public void setOp_account_name(String op_account_name) {
		this.op_account_name = op_account_name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
}
