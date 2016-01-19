/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 31, 2015-2:53:52 PM
* @version 1.0
*/
package com.yao.yz.model.v3.biz.drug;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 公司名: 壹药网
 * 类名称：DrugBean
 * 类描述：
 * @author wangyulong
 * @date Dec 31, 2015-2:53:52 PM
 */
public class DrugBean extends AbstractBizBean{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= -5507736627413147709L;

	@Override
	public String beanName() {
		return "DrugBean";
	}

	private String id;
	private String name;
	private String desc;
	private String img;
	private String brand;
	private String prescription;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
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
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	
}
