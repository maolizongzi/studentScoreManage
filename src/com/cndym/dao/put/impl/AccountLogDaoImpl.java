package com.cndym.dao.put.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.put.IAccountLogDao;
import com.cndym.entity.data.put.AccountLog;

@Repository
public class AccountLogDaoImpl extends BaseDaoImpl<AccountLog> implements
		IAccountLogDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	private Logger logger = Logger.getLogger(getClass());

	
	@Override
	public void deleteAccountLog(String startDate, String endDate) {
		try {
			String sql = "delete from account_log_pick t where t.create_time >= to_date('"+startDate+"','yyyy-MM-dd') AND t.create_time < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("同步之前先删除数" + e);
		}
	}
	
	
	// 登陆渠道
	@Override
	public boolean queryAccountLog(String startDate, String endDate) {
		// 将原始数据局筛选出来
		boolean flag = false;
		try {
			String sql = " INSERT INTO ACCOUNT_LOG_PICK (ID ,sid,USER_CODE,ORDER_ID,PROGRAMS_ORDER_ID,BUY_TYPE,EVENT_CODE,PRESENT_AMOUNT,PRESENT_AMOUNT_NEW,CREATE_TIME) ";
			sql += " SELECT SEQ_ACCOUNT_LOG_PICK.NEXTVAL,m.SID, l.USER_CODE,l.ORDER_ID,l.PROGRAMS_ORDER_ID,l.BUY_TYPE,l.EVENT_CODE,l.PRESENT_AMOUNT,l.PRESENT_AMOUNT_NEW,l.create_time ";
			sql += " FROM  user_account_log l INNER JOIN user_member_login_history_pick m on m.USER_CODE = l.USER_CODE ";
			sql += " WHERE l.create_time >= to_date('"+ startDate + "','yyyy-MM-dd') AND l.create_time < to_date('"+ endDate + "','yyyy-MM-dd') ";
			jdbcTemplate.update(sql);
			flag = true;
		} catch (Exception e) {
			flag = false;
			logger.error("赠金入库-->之登陆渠道", e);
		}
		return flag;
	}

	// 赠金金额--->登陆
	@Override
	public List<Map<String, Object>> queryGrantLogin(String startDate, String endDate) {
		List<Map<String, Object>> list = null;
		try {
			String sql = "SELECT OP.sid as sid, SUM(OP.PRESENT_AMOUNT) as count FROM ACCOUNT_LOG_PICK OP ";
			sql += " WHERE EVENT_CODE = '00700' and CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd')";
			sql +="  GROUP BY SID ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		} catch (Exception e) {
			logger.error("赠金统计---->登陆渠道", e);
		}
		return list;
	}

	// 赠金金额--->注册
	@Override
	public List<Map<String, Object>> queryGrantReg(String startDate,
			String endDate) {
		List<Map<String, Object>> list = null;
		try {
			String sql = "select b.union_id as sid, sum(a.present_amount) as count from account_log_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') and a.event_code = '00700' ";
			sql += "group by b.union_id";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		} catch (Exception e) {
			logger.error("赠金统计---->注册渠道", e);
		}
		return list;
	}

	// 赠金消耗---->登陆登陆
	@Override
	public List<Map<String, Object>> queryGrantConsumeLogin(String startDate,
			String endDate) {
		List<Map<String, Object>> list = null;
		try {
			String sql = " SELECT OP.sid as sid, SUM(OP.present_amount) as count FROM ACCOUNT_LOG_PICK OP ";
			sql += " WHERE EVENT_CODE like '100%' and CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd') GROUP BY SID ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		} catch (Exception e) {
			logger.error("赠金消耗--->登陆渠道", e);
		}
		return list;
	}

	// 赠金消耗---->注册渠道
	@Override
	public List<Map<String, Object>> queryGrantConsumeReg(String startDate,
			String endDate) {
		List<Map<String, Object>> list = null;
		try {
			String sql = "select b.union_id as sid, sum(a.present_amount) as count from account_log_pick a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.create_time>=to_date(?,'yyyy-MM-dd') and a.create_time<to_date(?,'yyyy-MM-dd') and a.event_code like '100%' ";
			sql += "group by b.union_id";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		} catch (Exception e) {
			logger.error("赠金消耗---->注册渠道", e);
		}
		return list;
	}
}
