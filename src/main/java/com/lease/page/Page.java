
package com.lease.page;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = -1855574134779989167L;
	
	/**
	 * 总共页数
	 */
	private int pages;
	/**
	 * 行数
	 */
	private int pageSize = 20;
	
	/**
	 * 总行数
	 */
	private long total;
	/**
	 * 当前页码
	 */
	private int pageNum = 1;
	
	
	/**
	 * 是否启动插件，如果不启动，则不作分页  
	 */
	private Boolean useFlag;
	
	/**
	 * 是否检测页码的有效性，如果为true，而页码大于最大页数，则抛出异常  
	 */
	private Boolean checkFlag;
	
	/**
	 * 是否清除最后order by 后面的语句 
	 */
	private Boolean cleanOrderBy;
	
	/**
	 * 总页数，插件会回填这个值
	 */
	private Integer totalPage;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
		if (this.pageSize != 0) {
			calculate();
		}
	}
	
	private void calculate() {
		
		this.pages = (int) (this.total / this.pageSize);
		long lastPageSize = this.total % this.pageSize;
		if (lastPageSize > 0) {
			this.pages++;
		}
	}

	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	/**
	 * 起始行
	 * @return int 起始行
	 */
	public int getStartRow() {
		return (this.pageNum - 1) * this.pageSize;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	public Boolean getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Boolean useFlag) {
		this.useFlag = useFlag;
	}

	public Boolean getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(Boolean checkFlag) {
		this.checkFlag = checkFlag;
	}

	public Boolean getCleanOrderBy() {
		return cleanOrderBy;
	}

	public void setCleanOrderBy(Boolean cleanOrderBy) {
		this.cleanOrderBy = cleanOrderBy;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
}
