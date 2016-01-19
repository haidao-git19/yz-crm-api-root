//
//
//
// v3.0版本移除
//
//
//package com.yao.yz.crm.service.impl.login;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.yao.yz.admin.yzadmin.persistence.model.YzDoctor;
//import com.yao.yz.admin.yzadmin.persistence.model.YzMetaDepartment;
//import com.yao.yz.crm.persistence.dao.ext.AdminSysParameterDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.YzDoctorDaoExt;
//import com.yao.yz.crm.persistence.dao.ext.YzMetaDepartmentDaoExt;
//import com.yao.yz.crm.service.interf.login.SeatService;
//import com.yao.yz.crm.service.util.ServiceContant;
//import com.yao.yz.crm.service.vo.BasicResVo;
//import com.yao.yz.crm.service.vo.req.login.SeatInfoReq;
//import com.yao.yz.crm.service.vo.res.login.SeatInfoRes;
//
///**
// *	描述：坐席服务
// *	@Author wuwenjun
// *	@Date Nov 12, 2015 11:44:25 AM
// *	@Versin 2.0修改，新增登录帐号角色信息
// */
//@Service
//public class SeatServiceImpl implements SeatService{
//	
//	/**
//	 * 坐席号匹配规则
//	 */
//	private static final String REG_SEAT_NUM = "^[7][0-9]{4}$";
//	
//	@Autowired
//	private YzDoctorDaoExt yzDoctorDaoExt;
//	
//	@Autowired
//	private YzMetaDepartmentDaoExt yzMetaDepartmentDaoExt;
//	
//	@Autowired
//	private AdminSysParameterDaoExt adminSysParameterDaoExt;
//	
//	@Override
//	public BasicResVo getSeatInfo(SeatInfoReq seatInfoReq) {
//		// 参数校验
//		BasicResVo basicResVo = new BasicResVo();
//		seatInfoReq.checkParameter(basicResVo);
//		if (ServiceContant.RET_CODE_ERROR == basicResVo.getRet()) {
//			return basicResVo;
//		}
//		
//		// 判断是否为坐席号
//		SeatInfoRes seatInfoRes = new SeatInfoRes();
//		if (seatInfoReq.getUser_name().matches(REG_SEAT_NUM)) {
//			seatInfoRes.getSeatinfo().setIs_seat(true);
//			seatInfoRes.getSeatinfo().setSeat_num(Integer.parseInt(seatInfoReq.getUser_name()));
//			seatInfoRes.getSeatinfo().setCall_num(Integer.parseInt(seatInfoReq.getUser_name().substring(1)));
//		} else {
//			seatInfoRes.getSeatinfo().setIs_seat(false);
//		}
//		
//		// 查询登录帐号对应的医生类型
//		YzDoctor yzDoctor = yzDoctorDaoExt.getDoctorByAccount(seatInfoReq.getUser_name());
//		if (yzDoctor != null) {
//			seatInfoRes.getSeatinfo().setDoctor_type(yzDoctor.getDoctorType());
//			seatInfoRes.getSeatinfo().setFirst_name(yzDoctor.getFirstName());
//			seatInfoRes.getSeatinfo().setSecond_name(yzDoctor.getSecondName());
//			
//			YzMetaDepartment yzMetaDepartment = yzMetaDepartmentDaoExt.getDepartmentByTagKey(yzDoctor.getDepartment());
//			if (yzMetaDepartment != null) {
//				seatInfoRes.getSeatinfo().setDepartment_name(yzMetaDepartment.getName());
//			}
//			
//			String imageHost = adminSysParameterDaoExt.getImageHost();
//			seatInfoRes.getSeatinfo().setFace(imageHost + yzDoctor.getFace());
//		}
//		
//		return basicResVo.processData(ServiceContant.RET_CODE_SUCCESS, "ok", seatInfoRes);
//	}
//	
//}
