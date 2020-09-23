package com.cndym.util;

import java.util.ArrayList;
import java.util.List;

public class PageView {
	// 用户指定/配置
	private int currentPage;// 当前页数
	private int pageSize;// 每页显示的记录数量
	// 从数据库中查询
	private int recordCount;// 总记录数
	private List items = new ArrayList();// 本页的数据列表
	// 计算
	private int pageCount;// 总页数
	private int startPageIndex;// 页码列表的开始的索引
	private int endPageIndex;// 页码列表的结束的索引

	public PageView(int currentPage, int pageSize, int recordCount,
			List recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		this.items = recordList;
		// 计算
		pageCount = (int) ((recordCount + pageSize - 1) / pageSize);// 总页数
		// a, 总页码不大于10页
		if (pageCount <= 10) {
			startPageIndex = 1;// 页码列表的开始的索引
			endPageIndex = pageCount;// 页码列表的结束的索引
		} else {
			// b, 总页码大于10页, 显示当前页附近的共10个页码
			startPageIndex = currentPage - 4;
			endPageIndex = currentPage + 5;

			// c, 前面不足4个时，显示前10个页码
			if (startPageIndex < 1) {
				startPageIndex = 1;
				endPageIndex = 10;
			}
			// d, 后面不足5个时，显示后10个页码
			else if (endPageIndex > pageCount) {
				endPageIndex = pageCount;
				startPageIndex = endPageIndex - 10 + 1;
			}
		}
	}

	public PageView() {
		super();
	}

	public int getCurrentPage() {
		if (currentPage == 0) {
			return 1;
		} else if (currentPage < 1) {
			currentPage = 1;
		}
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartPageIndex() {
		return startPageIndex;
	}

	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	public int getEndPageIndex() {
		return endPageIndex;
	}

	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
}