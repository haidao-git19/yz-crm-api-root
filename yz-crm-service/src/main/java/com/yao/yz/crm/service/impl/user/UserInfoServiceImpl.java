package com.yao.yz.crm.service.impl.user;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yao.yz.admin.yzadmin.persistence.dao.crm.CrmYzUserDaoExt;
import com.yao.yz.admin.yzadmin.persistence.model.YzUser;
import com.yao.yz.crm.service.interf.user.UserInfoService;
import com.yao.yz.crm.service.util.ServiceContant;
import com.yao.yz.crm.service.util.ServiceUtil;
import com.yao.yz.crm.service.vo.BasicResVo;
import com.yao.yz.crm.service.vo.req.user.UserInfoReq;
import com.yao.yz.crm.service.vo.req.user.UserListReq;
//import com.yao.yz.crm.service.vo.req.user.UserUpdateReq;
import com.yao.yz.crm.service.vo.res.user.UserInfo;
import com.yao.yz.crm.service.vo.res.user.UserListInfoRes;
import com.yao.yz.crm.service.vo.res.user.UserInfoRes;
import com.yao.yz.util.cache.redis.CacheFacade;
import com.yao.yz.util.common.RedisKeyPropsReader;
import com.yao.yz.util.exception.YzRuntimeException;
//import com.yao.yz.util.common.RedisKeyPropsReader;
/**
 *	描述：用户信息服务接口实现
 *	@Author wuwenjun
 *	@Date Oct 24, 2015 8:22:59 PM
 *	@Versin 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	private static final Logger logger = Logger.getLogger(UserInfoServiceImpl.class);

	@Autowired
	private CrmYzUserDaoExt yzUserDaoExt;
	
	@Autowired
	private CacheFacade cacheFacade;
	
	@Override
	public BasicResVo getUserInfo(UserInfoReq userReqVo) {
		BasicResVo basicResVo = new BasicResVo();
		
		// 检查参数
		userReqVo.checkParameter(basicResVo);
		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()){
			return basicResVo;
		}
		
		try {
			UserInfoRes userResVo = new UserInfoRes();
			YzUser user = yzUserDaoExt.getYzUserByKey(Integer.parseInt(userReqVo.getUid()));
			
			if (user == null) {
				logger.warn("【用户信息查询接口】未查询到用户[uid=" + userReqVo.getUid() + "]信息，请注意...");
				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "未查询到用户信息", null);
			}
			
			// UID
			userResVo.getUser_info().setUid(user.getUid());
			
			// userCall
			if (user.getSex() != null) {
				if (ServiceContant.SEX_MAN.equals(user.getSex())) {
					userResVo.getUser_info().setUser_call((user.getSecondName()==null?"":user.getSecondName()) + ServiceContant.CALL_MAN);
				} else if (ServiceContant.SEX_WOMAN.equals(user.getSex())) {
					userResVo.getUser_info().setUser_call((user.getSecondName()==null?"":user.getSecondName()) + ServiceContant.CALL_WOMAN);
				} else {
					userResVo.getUser_info().setUser_call(user.getSecondName()==null?"":user.getSecondName());
					logger.warn("【用户信息查询接口】用户[uid=" + user.getUid() + "]未填写性别，请注意...");
				}
			}
			
			// secondName
			userResVo.getUser_info().setSecond_name(user.getSecondName());
			
			// age
			try {
				if (user.getBirthYear() != null) {
					userResVo.getUser_info().setAge(ServiceUtil.yearToAge(user.getBirthYear()));
				}
			} catch (Exception e) {
				logger.error("【用户信息查询接口】计算用户[uid=" + user.getUid() + "]年龄异常", e);
				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户年龄计算错误", null);
			}
			
			// sex
			userResVo.getUser_info().setSex(user.getSex());
			
			// mobile
			userResVo.getUser_info().setMobile(user.getMobile());
			
			logger.info("【用户信息查询接口】查询用户信息[uid=" + user.getUid() + "]成功...");
			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", userResVo);
		} catch (Exception e) {
			logger.error("【用户信息查询接口】查询用户信息异常[uid=" + userReqVo.getUid() + "]", e);
			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "查询失败", null);
		}
	}
//
// v3.0版本移除	
//	
//	@Override
//	public BasicResVo doUpdateUserInfo(UserUpdateReq userUpdateReq) {
//		BasicResVo basicResVo = new BasicResVo();
//		
//		// 参数校验
//		userUpdateReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		try {
//			YzUser yzUser = new YzUser();
//			// uid
//			yzUser.setUid(Integer.parseInt(userUpdateReq.getUid()));
//			// secondName
//			yzUser.setSecondName(userUpdateReq.getSecond_name());
//			// birthYear
//			if (StringUtils.isNotBlank(userUpdateReq.getAge())) {
//				yzUser.setBirthYear(ServiceUtil.ageToYear(Integer.parseInt(userUpdateReq.getAge())));
//			}
//			// sex
//			yzUser.setSex(userUpdateReq.getSex());
//			if (yzUserDaoExt.update(yzUser) == 1) {
//				logger.info("【用户信息更新接口】更新用户[" + yzUser.getUid() + "]成功...");
//				
//				YzUser user = yzUserDaoExt.getYzUserByKey(yzUser.getUid());
//				// 更新成功之后删除缓存中的用户信息
//				try {
//					// 根据用户UID删除缓存信息
//					cacheFacade.deleteByFiledsNKey(RedisKeyPropsReader.getProperties("USER"),RedisKeyPropsReader.getProperties("UID_FILED") + user.getUid());
//					logger.info("【Redis更新】按照用户uid删除用户缓存成功[uid=" + user.getUid() + "]");
//					
//					// 根据用户手机号码删除缓存信息
//					cacheFacade.deleteByFiledsNKey(RedisKeyPropsReader.getProperties("USER"), RedisKeyPropsReader.getProperties("MOBILE_FILED") + user.getMobile());
//					logger.info("【Redis更新】按照用户电话号码删除缓存信息成功[uid=" + user.getUid() + "]");
//					
//					// 根据用户账号删除缓存信息
//					cacheFacade.deleteByFiledsNKey(RedisKeyPropsReader.getProperties("USER"),RedisKeyPropsReader.getProperties("ACCOUNT_FILED") + user.getAccount());
//					logger.info("【Redis更新】按照用户账号删除缓存信息成功[uid=" + user.getUid() + "]");
//				} catch (Exception e) {
//					logger.error("【Redis】删除缓存用户信息异常[uid=" + user.getUid() + "]",e);
//				}
//				return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", null);
//			} else {
//				logger.error("【用户信息更新接口】更新用户[" + yzUser.getUid() + "]失败");
//				return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "更新失败", null);
//			}
//		} catch (Exception e) {
//			logger.error("【用户信息更新接口】更新用户[uid" + userUpdateReq.getUid() + "]异常",e);
//			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "更新数据库异常", null);
//		}
//	}

	@Override
	public BasicResVo searchUserList(UserListReq userListReq) {
		BasicResVo basicResVo = new BasicResVo();
		
		// 参数校验
		userListReq.checkParameter(basicResVo);
		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
			return basicResVo;
		}
		
		UserListInfoRes userInfoList = new UserListInfoRes();
		try {
			YzUser yzUser = new YzUser();
			
			// mobile
			yzUser.setMobile(userListReq.getMobile());
			
			// uid
			if (StringUtils.isNotBlank(userListReq.getUid())) {
				yzUser.setUid(Integer.parseInt(userListReq.getUid()));
			}
			
			// 查询用户
			List<YzUser> yzUsers = yzUserDaoExt.searchUser(yzUser);
			if (yzUser != null) {
				for (YzUser user:yzUsers) {
					UserInfo userInfo = new UserInfo();
					// UID
					userInfo.setUid(user.getUid());
					
					// userCall
					if (ServiceContant.SEX_MAN.equals(user.getSex())) {
						userInfo.setUser_call((user.getSecondName()==null?"":user.getSecondName()) + ServiceContant.CALL_MAN);
					} else if (ServiceContant.SEX_WOMAN.equals(user.getSex())) {
						userInfo.setUser_call((user.getSecondName()==null?"":user.getSecondName()) + ServiceContant.CALL_WOMAN);
					} else {
						logger.warn("【用户信息搜索接口】用户[uid=" + user.getUid() + "]未填写性别,请注意...");
					}
					
					// secondName
					userInfo.setSecond_name(user.getSecondName());
					
					// age
					try {
						if (user.getBirthYear() != null) {
							userInfo.setAge(ServiceUtil.yearToAge(user.getBirthYear()));
						}
					} catch (Exception e) {
						logger.error("【用户信息查询接口】计算用户[uid=" + user.getUid() + "]年龄异常", e);
						return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "用户年龄计算错误", null);
					}
					
					// sex
					userInfo.setSex(user.getSex());
					
					// mobile
					userInfo.setMobile(user.getMobile());
					
					userInfoList.getUser_list().add(userInfo);
				}
			}
			logger.info("【用户信息搜索接口】搜索用户信息列表成功...");
			return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", userInfoList);
		} catch (Exception e) {
			logger.error("【用户信息搜索接口】搜索用户信息列表异常", e);
			return basicResVo.processData(ServiceContant.RET_CODE_ERROR, "查询数据库异常", null);
		}
	}

	@Override
	public boolean updateSecondName(Integer uid, String secondName) throws YzRuntimeException {
		YzUser yzUser = yzUserDaoExt.getYzUserByKey(uid);
		if (null == yzUser) {
			logger.error(">>>未查询到用户[uid=" + uid);
			return false;
		}
		yzUser.setSecondName(secondName);
		yzUser.setUpdateTime(new Date());
		yzUserDaoExt.update(yzUser);
		
		// 刷新缓存
		try {
			// 根据用户UID删除缓存信息
			cacheFacade.deleteByFiledsNKey(RedisKeyPropsReader.getProperties("USER"),RedisKeyPropsReader.getProperties("UID_FILED") + yzUser.getUid());
			logger.info("【Redis更新】按照用户uid删除用户缓存成功[uid=" + yzUser.getUid() + "]");
			
			// 根据用户手机号码删除缓存信息
			cacheFacade.deleteByFiledsNKey(RedisKeyPropsReader.getProperties("USER"), RedisKeyPropsReader.getProperties("MOBILE_FILED") + yzUser.getMobile());
			logger.info("【Redis更新】按照用户电话号码删除缓存信息成功[uid=" + yzUser.getUid() + "]");
			
			// 根据用户账号删除缓存信息
			cacheFacade.deleteByFiledsNKey(RedisKeyPropsReader.getProperties("USER"),RedisKeyPropsReader.getProperties("ACCOUNT_FILED") + yzUser.getAccount());
			logger.info("【Redis更新】按照用户账号删除缓存信息成功[uid=" + yzUser.getUid() + "]");
		} catch (Exception e) {
			logger.error("【Redis更新】更新缓存异常",e);
		}
		
		logger.info(">>>更新用户称呼成功...");
		return true;
	}
}
