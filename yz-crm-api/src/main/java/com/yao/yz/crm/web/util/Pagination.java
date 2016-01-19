package com.yao.yz.crm.web.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 分页
 * @author cg.yang
 * @version $Id: Pagination.java, v 0.1 2014-3-31 下午6:39:35 cg.yang Exp $
 */
public class Pagination<T> {
    private static Logger logger = LoggerFactory.getLogger(Pagination.class);

    private int           pageIndex;
    private int           previousPage;                               //上一页
    private int           nextPage;                                   //下一页
    private int           pageCount;                                  //总页数
    private int           itemCount;                                  //总条数
    private int           pageSize;                                   //每页条数
    private int           startIdx;                                   //开始
    private int           endIdx;                                     //结束

    private List<T>       list   = null;

    private String type;
    
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public void setStartIdx(int startIdx) {
        this.startIdx = startIdx;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public void setEndIdx(int endIdx) {
        this.endIdx = endIdx;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageIndex(String pageIndexStr) {
        int pageIndex = 1;
        try {
            if (StringUtils.isNotBlank(pageIndexStr)) {
                pageIndex = Integer.parseInt(pageIndexStr);
            }
        } catch (NumberFormatException e) {
            logger.error("ftl页面参数pageIndex转换出错",e);
        }
        return pageIndex <= 1 ? 1 : pageIndex;
    }

    public void setPageInfo(int itemCount, int pageSize, int pageIndex) {
        if (pageSize <= 0) {
            pageSize = 5;//每页条数  默认条数
        }
        setItemCount(itemCount);//总条数
        setPageSize(pageSize);//每页条数
        setPageCount(itemCount % pageSize == 0 ? itemCount / pageSize : itemCount / pageSize + 1);//总页数
        setPageIndex(pageIndex);//第几页
        setPreviousPage(pageIndex <= 1 ? -1 : pageIndex - 1);//上一页
        setNextPage(pageIndex < pageCount ? pageIndex + 1 : -1);//下一页
        setStartIdx(1 + pageSize * (pageIndex - 1));//开始条数
        setEndIdx(pageIndex < pageCount ? pageIndex * pageSize : itemCount);//结束条数
    }
    
    public void setPageInfo1(String type) {
        setType(type);
    }
}
