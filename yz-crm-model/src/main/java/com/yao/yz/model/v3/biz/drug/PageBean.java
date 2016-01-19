/**
* <p>Copyright: Copyright (c) 2015<／p>
* <p>Company:耀方信息技术(上海)有限公司-版权所有 <／p>
* @author wangyulong
* @date Dec 31, 2015-11:49:17 AM
* @version 1.0
*/
package com.yao.yz.model.v3.biz.drug;

import com.yao.yz.model.v3.base.res.AbstractBizBean;

/**
 * 公司名: 壹药网
 * 类名称：Pagination
 * 类描述：
 * @author wangyulong
 * @date Dec 31, 2015-11:49:17 AM
 */
public class PageBean extends AbstractBizBean{

	/** serialVersionUID*/
	private static final long	serialVersionUID	= -5029580889207173477L;
	
	@Override
	public String beanName() {
		return "PageBean";
	}
	
	private String pageSize;
	private String pageIndex;
	private String totalCount;
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

}
