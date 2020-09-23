package com.cndym.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cndym.dao.IBookReportDao;
import com.cndym.entity.report.BookReport;
import com.cndym.entity.user.User;
import com.cndym.util.BuilderSql;
import com.cndym.util.PageView;
import com.cndym.util.export.Utils;

/**
 * @author 程禄元
 * @date 2016-4-14
 */

@Repository
public class BookReportDaoImpl extends BaseDaoImpl<BookReport> implements
		IBookReportDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	// 更新数据
	@Override
	public boolean updateBookReport(Long id, int delStatus) {

		// return true;
		if (Utils.isNotEmpty(id) && Utils.isNotEmpty(delStatus)) {
			String sql = "update  BOOK_REPORT  set DEL_STATUS=" + delStatus
					+ " where ID='" + id + "'";
			this.jdbcTemplate.execute(sql);
			return true;
		}
		return false;
	}

	// /分页查询
	@Override
	public PageView queryPageBookReportList(BookReport bookReport,
			int currentPage) {
		BuilderSql bs = new BuilderSql(BookReport.class);

		if (StringUtils.isNotBlank(bookReport.getName())) {
			bs.addWhereClause("name=?", bookReport.getName());
		}
		if (StringUtils.isNotBlank(bookReport.getNumType())) {
			bs.addWhereClause("numType=?", bookReport.getNumType());
		}
		if (StringUtils.isNotBlank(bookReport.getUserCode())) {
			bs.addWhereClause("userCode=?", bookReport.getUserCode());
		}
		if (Utils.isNotEmpty(bookReport.getDelStatus())) {
			bs.addWhereClause("delStatus=?", bookReport.getDelStatus());
		}
		
		bs.addOrderClause(false, "reportTime");
		
		PageView view = this.getPageView(bs, currentPage);
		return view;
	}

	@Override
	public int queryReportByCodeAndName(String name, String code) {
		Object[] obj = new Object[2];
		obj[0] = name;
		obj[1] = code;
		int count = this.jdbcTemplate.queryForInt("  SELECT  COUNT(1) from  BOOK_REPORT where \"NAME\" =?  and USER_CODE =? And DEL_STATUS = 0 ",obj);
		return count;
	}

	@Override
	public List<BookReport> queryReport(Integer isAdmin) {
		List<BookReport> list = null;
		String time = Utils.today();
		BuilderSql bs = new BuilderSql(BookReport.class);
		bs.addWhereClause("isAdmin=?", isAdmin);
		bs.addWhereClause("reportTime<?", time);
		bs.addWhereClause("delStatus=?", 0);
		// 根据builderSql查询，返回集合
		list = this.getList(bs);
		return list;
	}
}
