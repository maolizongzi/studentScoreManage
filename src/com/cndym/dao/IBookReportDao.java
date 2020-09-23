package com.cndym.dao;

import java.util.List;

import com.cndym.entity.report.BookReport;
import com.cndym.util.PageView;


public interface IBookReportDao  extends BaseDao<BookReport>{

	/**
	 * @Author:chengluyuan
	 * @Create Date: 2016年4月13日
	 * @Return: 根据条件查询
	 */
	boolean updateBookReport(Long id, int delStatus);
	
	//分页查询
	PageView queryPageBookReportList(BookReport bookReport, int currentPage);

	////根据订阅名称+用户code查询是否订阅
	int queryReportByCodeAndName(String name, String code);
	
	//查询订阅的渠道
	List<BookReport> queryReport(Integer isAdmin);
}
