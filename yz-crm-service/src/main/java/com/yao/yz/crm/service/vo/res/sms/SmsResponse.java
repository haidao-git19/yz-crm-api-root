/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 6, 2015-1:13:31 PM
* @version 1.0
*/
package com.yao.yz.crm.service.vo.res.sms;

import java.io.Serializable;

/**
 * 公司名: 壹药网
 * 类名称：SmsResponse
 * 类描述：
 * @author wangyulong
 * @date Nov 6, 2015-1:13:31 PM
 */
public class SmsResponse implements Serializable{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= -4227203657244629247L;

	private boolean flag;
	private String msg;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
