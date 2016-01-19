package com.yao.yz.crm.service.vo;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 *	描述： 返回数据
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 6:24:17 PM
 *	@Versin 1.0
 */
public class BasicResVo {
	
	private static final Logger logger = Logger.getLogger(BasicResVo.class);
	
	/**
	 * 返回码
	 */
	private Integer ret;
	
	/**
	 * 返回消息
	 */
	private String msg;
	
	/**
	 * 返回数据
	 */
	private Object data;
	
	public Integer getRet() {
		return ret;
	}

	public void setRet(Integer ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 填充应答数据对象
	 * @param ret 返回码
	 * @param msg 提示信息
	 * @param data 返回数据
	 */
	public BasicResVo processData(Integer ret,String msg,Object data) {
		setRet(ret);
		setMsg(msg==null?"":msg);
		setData(data==null?new JSONObject():data);
		logger.info("【服务端下发数据】" + JSONObject.toJSONString(this));
		return this;
	}

	@Override
	public String toString() {
		return "BasicResVo [ret=" + ret + ", msg=" + msg + ", data=" + data.toString()
				+ "]";
	}
}
