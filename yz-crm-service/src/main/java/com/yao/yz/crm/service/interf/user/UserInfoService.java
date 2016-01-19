package com.yao.yz.crm.service.interf.user;

import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.user.UserInfoReq;
import com.yao.yz.crm.service.vo.req.user.UserListReq;
//import com.yao.yz.crm.service.vo.req.user.UserUpdateReq;

/**
 *	描述：用户信息服务接口
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 8:20:28 PM
 *	@Versin 1.0
 */
public interface UserInfoService {
	
	/**
	 * 搜索用户列表
	 * @param userListReq
	 * @return
	 */
	BasicResVo searchUserList(UserListReq userListReq);

	/**
	 * 查询用户信息
	 * @param userReqVo 请求对象
	 * @return
	 */
	BasicResVo getUserInfo(UserInfoReq userReqVo);
	
//
//	v2.0移除	
//	
//	/**
//	 * 更新用户信息
//	 * @param userUpdateReq 请求对象
//	 * @return
//	 */
//	BasicResVo doUpdateUserInfo(UserUpdateReq userUpdateReq);
//	
//	
	/**
	 * 更新用户的姓
	 * @param uid 用户编号
	 * @param secondName 用户的姓
	 * @return true-更新成功，false-更新失败
	 */
	boolean updateSecondName(Integer uid, String secondName);
	
}
