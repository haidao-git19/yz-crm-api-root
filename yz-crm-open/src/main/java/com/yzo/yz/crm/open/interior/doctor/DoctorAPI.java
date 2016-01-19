package com.yzo.yz.crm.open.interior.doctor;

import org.apache.log4j.Logger;

import com.yao.yz.model.v3.base.res.AbstractResModel;
import com.yao.yz.model.v3.biz.doctor.ServiceOffReq;
import com.yao.yz.model.v3.biz.doctor.ServiceOnReq;
import com.yao.yz.model.v3.biz.doctor.ServiceStatusReq;
import com.yao.yz.model.v3.biz.doctor.WaitCountReq;
import com.yao.yz.model.v3.biz.login.LoginUserReq;

/**
 * 
 * @Description: 医生服务API接口，供外部调用
 * @Autor: wuwenjun
 * @Date: 5:24:51 PM Dec 30, 2015
 * @Version: v1.0
 * 
 */

public interface DoctorAPI {
	
	static final Logger logger = Logger.getLogger(DoctorAPI.class);
	
	/**
	 * 查询登录帐号的医生信息
	 * @param loginUserReq 请求数据模型
	 * @param resModel 返回数据模型
	 */
	void getLoginInfo(LoginUserReq loginUserReq,AbstractResModel resModel);
	
	/**
	 * 医生接诊打卡
	 * @param serviceOnReq 接诊打卡请求数据模型
	 * @param resModel 返回数据模型
	 */
	void serviceOn(ServiceOnReq serviceOnReq,AbstractResModel resModel);
	
	/**
	 * 医生停诊打卡,更新最后一条接诊打卡记录的接诊状态、停诊时间
	 * @param serviceOffReq 停诊打卡请求数据模型
	 * @param resModel 返回数据模型
	 */
	void serviceOff(ServiceOffReq serviceOffReq,AbstractResModel resModel);
	
	/**
	 * 查询医生登录状态，查询医生当天的最后一条打卡记录，打卡记录为空则为下班状态，打卡记录存在则判断打卡记录状态
	 * @param serviceStatusReq
	 * @param resModel
	 */
	void serviceStatus(ServiceStatusReq serviceStatusReq,AbstractResModel resModel);
	
	/**
	 * 查询指定科室的所有医生待处理诊单数量
	 * @param waitCountReq
	 * @param resModel
	 */
	void waitCountList(WaitCountReq waitCountReq,AbstractResModel resModel);
	
	/**
	 * 登出
	 * @param serviceOffReq
	 * @param resModel
	 */
	void loginOut(ServiceOffReq serviceOffReq,AbstractResModel resModel);
}
