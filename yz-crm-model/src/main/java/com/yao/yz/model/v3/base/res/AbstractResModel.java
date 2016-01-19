package com.yao.yz.model.v3.base.res;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 
 * @Description: 返回数据模型抽象类，所有返回数据模型都应该继承该类
 * @Autor: wuwenjun
 * @Date: 11:21:51 AM Dec 28, 2015
 * @Version: v1.0
 * 
 */

public abstract class AbstractResModel implements Serializable,ResModel{
	
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("msgCode");
	
	private static final long serialVersionUID = 1942451480971337096L;

	private Integer ret = 1;
	
	private String msg = "ok";
	
	private Map<String, Object> data = new HashMap<String, Object>();

//	/**
//	 * 向返回数据模型的私有域填充单个返回数据Bean,返回消息从系统配置中获取,ret=1时才会填充返回数据
//	 * @param ret 返回码
//	 * @param errorCode 错误码
//	 * @param abstractBizBean 返回业务数据Bean
//	 * @return 返回数据模型
//	 */
//	public AbstractResModel putSignData(Integer ret,Integer errorCode,AbstractBizBean abstractBizBean){
//		this.ret = ret;
//		if (null != errorCode) {
//			if (errorCode == 10000) {
//				this.msg = resourceBundle.getString(String.valueOf(errorCode));	
//			} else {
//				this.msg = "[" + errorCode + "]" + resourceBundle.getString(String.valueOf(errorCode));
//			}
//		}
//		if (1 == ret && abstractBizBean != null){
//			this.data.put(abstractBizBean.beanName(), abstractBizBean);
//		}
//		logger.info(loggerName + this.toString());
//		return this;
//	}
//	
//	/**
//	 * 直接向返回数据模型中添加ret和msg，无返回业务数据Bean，使用于返回错误信息
//	 * @param ret 返回码
//	 * @param msg 返回消息
//	 * @param abstractBizBean 返回业务数据
//	 * @return
//	 */
//	public AbstractResModel putNoData(Integer ret,String msg){
//		this.ret = ret;
//		this.msg = msg;
//		logger.info(loggerName + this.toString());
//		return this;
//	}
//	
//	/**
//	 * 向返回数据模型中添加多个不同的业务返回数据Bean，返回消息从系统配置中获取，适用于返回正确数据
//	 * @param ret 返回码
//	 * @param errorCode 返回消息代码
//	 * @param absList 业务数据List
//	 * @return
//	 */
//	public AbstractResModel putListData(Integer ret,Integer errorCode,List<AbstractBizBean> absList){
//		this.ret = ret;
//		if (null != errorCode) {
//			if (errorCode == 10000) {
//				this.msg = resourceBundle.getString(String.valueOf(errorCode));	
//			} else {
//				this.msg = "[" + errorCode + "]" + resourceBundle.getString(String.valueOf(errorCode));
//			}
//		}
//		for (AbstractBizBean abstractBizBean : absList) {
//			putSignData(abstractBizBean);
//		}
//		logger.info(loggerName + this.toString());
//		return this;
//	}
//	
//	/**
//	 * 向返回数据模型中添加多个相同的业务返回数据Bean，返回消息从系统配置中获取，适用于返回正确数据
//	 * @param ret 返回码
//	 * @param errorCode 返回消息代码
//	 * @param key 返回业务数据key值
//	 * @param absList 业务数据List，必须相同的返回业务Bean
//	 * @return
//	 */
//	public AbstractResModel putListData(Integer ret,Integer errorCode,String key,List<AbstractBizBean> absList){
//		this.ret = ret;
//		if (null != errorCode) {
//			if (errorCode == 10000) {
//				this.msg = resourceBundle.getString(String.valueOf(errorCode));	
//			} else {
//				this.msg = "[" + errorCode + "]" + resourceBundle.getString(String.valueOf(errorCode));
//			}
//		}
//		this.data.put(key, absList);
//		logger.info(loggerName + this.toString());
//		return this;
//	}
//	
//	/**
//	 * 向返回数据模型中添加单个业务数据Bean
//	 * @param abstractBizBean 返回业务数据
//	 * @return
//	 */
//	public AbstractResModel putSignData(AbstractBizBean abstractBizBean) {
//		this.data.put(abstractBizBean.beanName(), abstractBizBean);
//		return this;
//	}
//	
	
	/**
	 * 填充公共域信息，返回信息通过系统配置获取
	 * @param ret 返回码
	 * @param msgCode 返回信息码
	 * @return
	 */
	public AbstractResModel setPublicData(Integer ret,Integer msgCode){
		this.ret = ret;
		this.msg = resourceBundle.getString(String.valueOf(msgCode));
		return this;
	}
	
	/**
	 * 填充公共域信息，返回信息自定义
	 * @param ret 返回码
	 * @param msg 返回信息
	 * @return
	 */
	public AbstractResModel setPublicData(Integer ret,String msg){
		this.ret = ret;
		this.msg = msg;
		return this;
	}
	
	/**
	 * 填充私有域数据
	 * @param key 键
	 * @param value 数据对象 
	 * @return
	 */
	public AbstractResModel setPrivateData(String key, Object value){
		this.data.put(key, value);
		return this;
	}

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

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "[ret=" + ret + ", msg=" + msg + ", data="
				+ data.toString() + "]";
	}
	
}
