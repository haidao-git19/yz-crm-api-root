package com.yao.yz.model.v3.biz.login;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 4:09:16 PM Dec 29, 2015
 * @Version: v1.0
 * 
 */

public class SeatInfoBean extends AbstractBizBean{

	private static final long serialVersionUID = 799345080061239535L;

	private boolean is_seat;
	
	private Integer seat_num;
	
	private Integer call_num;
	
	@Override
	public String beanName() {
		// TODO Auto-generated method stub
		return "seat_info";
	}

	public boolean isIs_seat() {
		return is_seat;
	}

	public void setIs_seat(boolean is_seat) {
		this.is_seat = is_seat;
	}

	public Integer getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(Integer seat_num) {
		this.seat_num = seat_num;
	}

	public Integer getCall_num() {
		return call_num;
	}

	public void setCall_num(Integer call_num) {
		this.call_num = call_num;
	}

	@Override
	public String toString() {
		return "SeatInfoBean [is_seat=" + is_seat + ", seat_num=" + seat_num
				+ ", call_num=" + call_num + "]";
	}
}
