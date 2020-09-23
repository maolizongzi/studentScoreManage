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
import com.cndym.dao.put.IMemberLoginHistoryDao;
import com.cndym.entity.data.put.MemberLoginHistory;

@Repository
public class MemberLoginHistoryDaoImpl extends BaseDaoImpl<MemberLoginHistory> implements IMemberLoginHistoryDao {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void deleteMemberLoginHistory(String startDate, String endDate) {
		try {
			String sql = "delete from user_member_login_history_pick t where t.create_time  >= to_date('"+startDate+"','yyyy-MM-dd')  AND  t.create_time  < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("同步之前先删除数" + e);
		}
	}
	
	
	public boolean queryMemberLoginHistoryList(String startDate , String endDate) {
		boolean flag = false;
		try {
			String sql = " insert into user_member_login_history_pick(id,user_code,CREATE_time ,status,sid,platform,soft_ver)";
			sql = sql + " SELECT SEQ_USER_ML_HISTORY_PICK.NEXTVAL, t.user_code,t.CREATE_time ,t.status,t.sid,t.platform,t.soft_ver from user_member_login_history t WHERE id";
			sql = sql + " in ( SELECT  min(id)  from user_member_login_history t ";
			sql = sql + " WHERE t.create_time  >= to_date('"+startDate+"','yyyy-MM-dd') AND t.create_time  < to_date('"+endDate+"','yyyy-MM-dd') and t.sid is not NULL ";
			sql = sql + " GROUP BY   t.user_code  , t.sid)";
			jdbcTemplate.update(sql);
			flag = true;
		} catch (Exception e) {
			flag = false;
			logger.error("DAU去重登录用户数的记录，插入到新建的表user_member_login_history_pick中出错", e);
		}
		return flag;
	}

	
	@Override
	public List<Map<String,Object>>  queryDistinctGroupBySId(String startDate, String endDate){
		List<Map<String,Object>> list=null;
		try {
			String sql = "select count(DISTINCT(USER_CODE)) as count,sid from user_member_login_history_pick ";
			sql = sql + " WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') and CREATE_TIME < to_date(?,'yyyy-MM-dd') ";
			sql = sql + " GROUP BY SID";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("DAU去重登录用户数", e);
		}
		return list;
	}
	
	@Override
	//DAU统计数据，不用进行渠道分组,直接统计去重数
	public Integer  queryDistinctCount(String startDate, String endDate){
		Integer result=0;
		try {
			String sql = "select count(DISTINCT(USER_CODE)) as count from user_member_login_history_pick ";
			sql = sql + " WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') and CREATE_TIME < to_date(?,'yyyy-MM-dd') ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("DAU去重登录用户数汇总", e);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryDistinctUserCode(String startTime,String endTime){
		List<Map<String,Object>> list=null;
		try {
			String sql=" select DISTINCT(USER_CODE) as userCode  from user_member_login_history_pick  ";
			sql = sql+" WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') and CREATE_TIME < to_date(?,'yyyy-MM-dd') ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("DAU去重登录用户数", e);
		}
		return list;
	}
	/**
	 * 获取当天的登录用户集合 ,List<Map<String,Object>> userCodeList
	 */
	@Override
	public List<Map<String,Object>> queryMemberLoginByTime(String startTime, String endTime,String start, String end){
		List<Map<String,Object>> list = null;
		try {
			StringBuffer sb=new StringBuffer(" select count(1) as count,SID as sid from USER_MEMBER_LOGIN_HISTORY_PICK ");
			sb.append(" WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd')");
			sb.append(" AND user_code in ( select DISTINCT(USER_CODE) as userCode  from user_member_login_history_pick");
			sb.append(" WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') and CREATE_TIME < to_date(?,'yyyy-MM-dd'))");
			sb.append(" group by SID");
			Query query = this.getSession().createSQLQuery(sb.toString());
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, start);
			query.setString(3, end);
			logger.info("sql："+sb.toString()+" startTime:"+startTime+" endTime:"+endTime);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("获取开始时间:"+startTime+"结束时间:"+endTime+"的登陆用户集合:"+list+" start:"+start+" end:"+end);
		} catch (Exception e) {
			logger.error("获取开始时间:"+startTime+"结束时间:"+endTime+"的登陆用户集合:"+list+" start:"+start+" end:"+end, e);
		}
		return list;
	}
	@Override
	public List<Map<String, Object>> queryMemberLoginBettingByTime(
			String startTime, String endTime, String start, String end) {
		List<Map<String,Object>> list = null;
		try {
			StringBuffer sb=new StringBuffer(" select count(1) as count,sid as sid from user_member_login_history_pick ");
			sb.append(" WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd')");
			sb.append(" AND user_code in (select distinct(a.user_code) from USER_BETTORS a");
			sb.append(" where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd'))");
			sb.append(" group by sid");
			Query query = this.getSession().createSQLQuery(sb.toString());
			query.setString(0, startTime);
			query.setString(1, endTime);
			query.setString(2, start);
			query.setString(3, end);
			logger.info("sql："+sb.toString()+" startTime:"+startTime+" endTime:"+endTime);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("获取开始时间:"+startTime+"结束时间:"+endTime+"的登陆用户集合:"+list+" start:"+start+" end:"+end);
		} catch (Exception e) {
			logger.error("获取开始时间:"+startTime+"结束时间:"+endTime+"的登陆用户集合:"+list+" start:"+start+" end:"+end, e);
		}
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> queryDistinct(String startDate, String endDate){
		List<Map<String,Object>> list = null;
		try {
			String sql = " select count(1) as count,SID as sid from USER_MEMBER_LOGIN_HISTORY_PICK ";
			sql = sql + " WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd') group by UNION_ID ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
			logger.info("去重注册用户数:"+list);
		} catch (Exception e) {
			logger.error("去重注册用户数", e);
		}
		return list;
	}
	
}
