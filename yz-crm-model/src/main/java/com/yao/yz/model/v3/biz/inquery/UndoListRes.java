/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 30, 2015-3:45:46 PM
* @version 1.0
*/
package com.yao.yz.model.v3.biz.inquery;

import java.util.List;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 公司名: 壹药网
 * 类名称：UndoListRes
 * 类描述：
 * @author wangyulong
 * @date Dec 30, 2015-3:45:46 PM
 */
public class UndoListRes extends AbstractBizBean{

	private static final long serialVersionUID = 3878787203272606655L;
	
	private List<?> list;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String beanName() {
		// TODO Auto-generated method stub
		return "UndoList";
	}

}
