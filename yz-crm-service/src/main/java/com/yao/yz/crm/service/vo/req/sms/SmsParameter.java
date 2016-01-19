/**
 * <p>Copyright: Copyright (c) 2015<／p>
 * <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
 * @author wangyulong
 * @date Nov 5, 2015-6:53:08 PM
 * @version 1.0
 */
package com.yao.yz.crm.service.vo.req.sms;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司名: 壹药网 类名称：SmsParameter 类描述：
 * 
 * @author wangyulong
 * @date Nov 5, 2015-6:53:08 PM
 */
public class SmsParameter implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -4775735081660625104L;

	String mode;
	String mobile;
	Date book_date;
	Integer book_start;
	Integer book_end;

	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getBook_date() {
		return book_date;
	}

	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}

	public Integer getBook_start() {
		return book_start;
	}

	public void setBook_start(Integer book_start) {
		this.book_start = book_start;
	}

	public Integer getBook_end() {
		return book_end;
	}

	public void setBook_end(Integer book_end) {
		this.book_end = book_end;
	}

	@Override
	public String toString() {
		return "SmsParameter [mode=" + mode + ", mobile=" + mobile
				+ ", book_date=" + book_date + ", book_start=" + book_start
				+ ", book_end=" + book_end + "]";
	}
}
