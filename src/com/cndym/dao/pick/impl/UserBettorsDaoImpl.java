package com.cndym.dao.pick.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.pick.IUserBettorsDao;
import com.cndym.entity.data.pick.UserBettors;

@Repository
public class UserBettorsDaoImpl extends BaseDaoImpl<UserBettors> implements
		IUserBettorsDao {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Override
	public void insertUserBettors(List<UserBettors> insertPurchaseUB) {
		try {
			if(insertPurchaseUB!=null && insertPurchaseUB.size()>0){
				for (UserBettors ub : insertPurchaseUB) {
					super.save(ub);
				}
			}
		} catch (Exception e) {
			logger.error("登陆渠道---->整理的数据明细添加到投注表USER_BETTORS出错了.....",e);
		}
	}
	
	@Override
	//登陆渠道投注用户数
	public List<Map<String,Object>> queryDistinctLoginGroupBySId(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = " select count(DISTINCT(USER_CODE)) as count, partners_Code as sid from USER_BETTORS ";
			sql = sql + " WHERE RETURN_TIME >= to_date(?,'yyyy-MM-dd') AND RETURN_TIME < to_date(?,'yyyy-MM-dd') group by partners_Code ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("去重投注用户数", e);
		}
		return list;
	}
	//登陆渠道投注用户数
	@Override
	public Integer queryDistinctLoginCount(String startDate, String endDate) {
		Integer result=0;
		try {
			String sql = " select count(DISTINCT(USER_CODE)) as count from USER_BETTORS ";
			sql = sql + " WHERE RETURN_TIME >= to_date(?,'yyyy-MM-dd') AND RETURN_TIME < to_date(?,'yyyy-MM-dd') ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("去重投注用户数", e);
		}
		return result;
	}
	
	

	@Override
	public List<Map<String,Object>> queryAllTouZhuMoneyLogin(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql =  "select SUM(RENGOU_SUCCESS_AMOUNT) as count , partners_Code as sid from USER_BETTORS ";
			sql += " WHERE RETURN_TIME >= to_date('"+startDate+"','yyyy-MM-dd') AND RETURN_TIME < to_date('"+endDate+"','yyyy-MM-dd') group by partners_Code ";
			Query query = this.getSession().createSQLQuery(sql);
//			jdbcTemplate.query(sql,(RowMapper)ParameterizedBeanPropertyRowMapper.newInstance(ProgramsPick.class));
			logger.info("sql-------》"+sql);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("计算总投注金额出错", e);
		}
		return list;
	}
	

	@Override
	public List<Map<String,Object>>  queryTouZhuNewCountLoginGroupBySid(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {

			String sql = "select a.partners_code as sid, count(distinct(a.user_code)) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by a.partners_code";
			
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("投注新用户数出错", e);
		}
		return list;
	}
	public Integer  queryTouZhuNewCountLoginCount(String startDate, String endDate) {
		Integer result=0;
		try {

			String sql = "select count(distinct(a.user_code)) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>>  list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("投注新用户数出错", e);
		}
		return result;
	}
	
	@Override
	public List<Map<String,Object>> queryTouZhuNewMoneryCountLogin(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select a.partners_code as sid, sum(a.rengou_success_amount) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by a.partners_code";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("投注新用户数出错", e);
		}
		return list;
	}

	@Override
	//注册渠道投注用户数
	public List<Map<String,Object>>  queryDistinctRegGroupBySid(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, count(distinct(a.user_code)) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by b.union_id";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("去重投注用户数---->注册渠道", e);
		}
		return list;
	}
	@Override
	public Integer  queryDistinctRegCount(String startDate, String endDate) {
		Integer result=0;
		try {
			String sql = "select  count(distinct(a.user_code)) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>>  list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("去重投注用户数---->注册渠道", e);
		}
		return result;
	}

	@Override
	public List<Map<String,Object>> queryAllTouZhuMoneyReg(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, sum(a.rengou_success_amount) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by b.union_id";
			
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("计算总投注金额出错", e);
		}
		return list;
	}

	@Override
	public List<Map<String,Object>>  queryTouZhuNewCountRegGroupBySid(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, count(distinct(a.user_code)) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by b.union_id";

			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("投注新用户数出错", e);
		}
		return list;
	}
	public Integer  queryTouZhuNewCountRegCount(String startDate, String endDate) {
		Integer result=0;
		try {
			String sql = "select count(distinct(a.user_code)) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String,Object>> list=query.list();
			if(list!=null && list.size()>0){
				result=Integer.valueOf(list.get(0).get("COUNT")+"");
			}
		} catch (Exception e) {
			logger.error("投注新用户数出错", e);
		}
		return result;
	}

	@Override
	public List<Map<String,Object>> queryTouZhuNewMoneryCountReg(String startDate, String endDate) {
		List<Map<String,Object>> list=null;
		try {
			String sql = "select b.union_id as sid, sum(a.rengou_success_amount) as count from user_bettors a left join user_member b on a.user_code=b.user_code ";
			sql += "where a.return_time>=to_date(?,'yyyy-MM-dd') and a.return_time<to_date(?,'yyyy-MM-dd') ";
			sql += "and	 b.create_time>=to_date(?,'yyyy-MM-dd') and b.create_time<to_date(?,'yyyy-MM-dd') ";
			sql += "group by b.union_id";
			
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setString(2, startDate);
			query.setString(3, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list=query.list();
		} catch (Exception e) {
			logger.error("投注新用户数出错", e);
		}
		return list;
	}
	@Override
	public void deleteUserBettors(String startDate, String endDate) {
		try {
			String sql = "delete from USER_BETTORS t where t.RETURN_TIME >= to_date('"+startDate+"','yyyy-MM-dd') AND t.RETURN_TIME < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("在添加之前删除userBettors错误", e);
		}
	}
}
