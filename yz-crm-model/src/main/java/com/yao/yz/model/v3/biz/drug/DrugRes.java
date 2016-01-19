package com.yao.yz.model.v3.biz.drug;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 
 * @Description: TODO
 * @Autor: wuwenjun
 * @Date: 10:31:31 PM Jan 7, 2016
 * @Version: v1.0
 * 
 */

public class DrugRes extends AbstractBizBean{

	private static final long serialVersionUID = 1420708008426627973L;

	/**
	 * 药品编号
	 */
	private String id;
	
	/**
	 * 药品描述
	 */
	private String desc;
	
	/**
	 * 药品名称
	 */
	private String name;
	
	/**
	 * 图片url
	 */
	private String img;
	
	/**
	 * 商品品牌
	 */
	private String brand;
	
	/**
	 * 药品类型
	 */
	private Integer prescription;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getPrescription() {
		return prescription;
	}

	public void setPrescription(Integer prescription) {
		this.prescription = prescription;
	}

	@Override
	public String beanName() {
		return "";
	}

	@Override
	public String toString() {
		return "DrugRes [id=" + id + ", desc=" + desc + ", name=" + name
				+ ", img=" + img + ", brand=" + brand + ", prescription="
				+ prescription + "]";
	}
}
