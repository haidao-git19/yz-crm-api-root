package com.yao.yz.crm.web.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 分页计算类
 * <p>
 * Company: 壹药网<／p>
 * 
 * @author wangtao9953
 * @date Oct 27, 2015
 */
public class PageUtils {

	public PageUtils(int pageSize, String pageIndexStr) {
		if (StringUtils.isBlank(pageIndexStr)) {
			pageIndexStr = "1";
		}
		int pageNo = Integer.parseInt(pageIndexStr);
		this.pageIndex = pageNo;
		this.startRow = pageSize * (pageNo - 1);
		this.endRow = startRow + pageSize;
	}

	public static String getPageIndex(Map<String, Object> paramMap) {
		String pageIndexStr = "";
		if (paramMap.get("pageIndex") != null) {
			pageIndexStr = (String) paramMap.get("pageIndex");
		}
		return pageIndexStr;
	}

	private int startRow;
	private int endRow;
	private int pageIndex;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

}
