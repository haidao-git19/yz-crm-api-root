//
//
//
//
// v3.0版本移除
//
//
//
//
//
//package com.yao.yz.crm.service.vo.res.login;
//
//import java.io.Serializable;
//
///**
// *	描述：坐席信息返回数据
// *	@Author wuwenjun
// *	@Date Nov 12, 2015 11:38:28 AM
// *	@Versin 1.0
// */
//public class SeatInfoRes implements Serializable{
//	
//	private static final long serialVersionUID = -4491830550274015227L;
//
//	public class SeatInfo implements Serializable{
//		
//		private static final long serialVersionUID = -3357761405447469426L;
//
//		/**
//		 * 是否为坐席
//		 */
//		private boolean is_seat;
//		
//		/**
//		 * 坐席号码
//		 */
//		private Integer seat_num;
//		
//		/**
//		 * 分机号
//		 */
//		private Integer call_num;
//		
//		/**
//		 * 登录帐号类型，1-专科医生，2-心里医生，3-医助
//		 */
//		private Integer doctor_type;
//		
//		/**
//		 * 登陆帐号对应的名
//		 */
//		private String first_name;
//		
//		/**
//		 * 登录帐号对应的姓
//		 */
//		private String second_name;
//		
//		/**
//		 * 登录帐号对应的科室名称
//		 */
//		private String department_name;
//		
//		/**
//		 * 登录帐号对应医生的图像
//		 */
//		private String face;
//
//		public boolean isIs_seat() {
//			return is_seat;
//		}
//
//		public void setIs_seat(boolean is_seat) {
//			this.is_seat = is_seat;
//		}
//
//		public Integer getSeat_num() {
//			return seat_num;
//		}
//
//		public void setSeat_num(Integer seat_num) {
//			this.seat_num = seat_num;
//		}
//
//		public Integer getCall_num() {
//			return call_num;
//		}
//
//		public void setCall_num(Integer call_num) {
//			this.call_num = call_num;
//		}
//
//		public Integer getDoctor_type() {
//			return doctor_type;
//		}
//
//		public void setDoctor_type(Integer doctor_type) {
//			this.doctor_type = doctor_type;
//		}
//
//		public String getFirst_name() {
//			return first_name;
//		}
//
//		public void setFirst_name(String first_name) {
//			this.first_name = first_name;
//		}
//
//		public String getSecond_name() {
//			return second_name;
//		}
//
//		public void setSecond_name(String second_name) {
//			this.second_name = second_name;
//		}
//
//		public String getDepartment_name() {
//			return department_name;
//		}
//
//		public void setDepartment_name(String department_name) {
//			this.department_name = department_name;
//		}
//
//		public String getFace() {
//			return face;
//		}
//
//		public void setFace(String face) {
//			this.face = face;
//		}
//	}
//	
//	/**
//	 * 坐席信息
//	 */
//	private SeatInfo seatinfo;
//	
//	public SeatInfoRes () {
//		seatinfo = new SeatInfo();
//	}
//
//	public SeatInfo getSeatinfo() {
//		return seatinfo;
//	}
//
//	public void setSeatinfo(SeatInfo seatinfo) {
//		this.seatinfo = seatinfo;
//	}
//	
//}
