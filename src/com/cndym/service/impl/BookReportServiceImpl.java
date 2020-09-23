package com.cndym.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.IBookReportDao;
import com.cndym.dao.IUserDao;
import com.cndym.entity.report.BookReport;
import com.cndym.entity.user.User;
import com.cndym.service.IBookReportService;
import com.cndym.util.PageView;

/***
 * @author 程禄元
 * @date 2016-4-14
 */
@Service("bookReportServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class BookReportServiceImpl implements IBookReportService {
	private Logger logger = Logger.getLogger(getClass());
	
	
	
	@Resource
	private IBookReportDao bookReportDao;
	@Resource
	private IUserDao userDao;

	// 根据Id更新字段 达到伪删除
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public boolean updateBookReport(Long id) {
		return bookReportDao.updateBookReport(id, 1);
	}

	// 分页获取List
	@Override
	public PageView queryPageBookReportList(BookReport bookReport,
			int currentPage) {
		PageView pageView = bookReportDao.queryPageBookReportList(bookReport,
				currentPage);
		return pageView;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveReport(BookReport bookReport) {
		bookReportDao.save(bookReport);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public int queryReportByCodeAndName(String name, String code) {
		 int count= bookReportDao.queryReportByCodeAndName(name,code);
		return count;
	}
	
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public List<BookReport> queryReport() {
		List<BookReport>  bookReportList=null;
		try {
			 bookReportList=bookReportDao.queryReport(0);
			 if(bookReportList!=null&&bookReportList.size()>0){
				 for (BookReport br : bookReportList) {
					Map<String,Object> userMap=new HashMap<String,Object>();
					userMap.put("userCode", br.getUserCode());
					User user=userDao.queryByQuery(userMap).get(0);
					br.setEmailAddress(user.getUserEmail());
				}
			 }
		} catch (Exception e) {
			logger.info("查询渠道订阅用户出错",e);
		}
		return bookReportList;
	}
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public List<BookReport> queryReportAdmin() {
		List<BookReport>  bookReportList=null;
		try {
			  bookReportList=bookReportDao.queryReport(1);
			  if(bookReportList!=null&&bookReportList.size()>0){
					 for (BookReport br : bookReportList) {
						Map<String,Object> userMap=new HashMap<String,Object>();
						userMap.put("userCode", br.getUserCode());
						User user=userDao.queryByQuery(userMap).get(0);
						br.setEmailAddress(user.getUserEmail());
					}
			  }
		} catch (Exception e) {
			logger.info("查询管理员订阅用户出错",e);
		}
		return bookReportList;
	}
}
