/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Nov 5, 2015-11:07:57 AM
* @version 1.0
*/
package com.yao.yz.crm.service.vo.res;

import java.io.Serializable;
import java.util.List;

/**
 * 公司名: 壹药网
 * 类名称：InqueryList
 * 类描述：
 * @author wangyulong
 * @date Nov 5, 2015-11:07:57 AM
 */
public class InqueryList implements Serializable{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= 2894889803260983837L;

	private List<Inquery> list;

	public List<Inquery> getList() {
		return list;
	}

	public void setList(List<Inquery> list) {
		this.list = list;
	}
	
}
