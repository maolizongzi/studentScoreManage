package com.cndym.service;

import java.util.List;

import com.cndym.entity.report.BookReport;
import com.cndym.util.PageView;



public interface IBookReportService {

	/**
	 * @Description: 根据公司code获取渠道集合
	 * @Author:程禄元  2016.04.13
	 * @Return:  获取所有符合条件的渠道集合
	 */
	boolean updateBookReport(Long id); 
	
	PageView queryPageBookReportList(BookReport bookReport, int currentPage);

	//添加订阅信息
	void saveReport(BookReport bookReport);

	//根据订阅名称+用户code查询是否订阅
	int queryReportByCodeAndName(String name, String code);

	//渠道用户
	List<BookReport> queryReport();
	//管理员用户
	List<BookReport> queryReportAdmin();
}
