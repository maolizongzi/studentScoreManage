package com.cndym.dao.put.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SQLQuery;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.put.IOrderDao;
import com.cndym.entity.data.pick.UserBettors;
import com.cndym.entity.data.put.Order;
@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements IOrderDao {
	
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public List<UserBettors> queryHMByProgramsOrderId(Map<String, Object> paramMap) {
		List<UserBettors>  userList=null;
		try {
			String programsOrderId= paramMap.get("programsOrderId")+"";
			String sql =" SELECT   t.user_code as userCode,t.order_amount AS   renGouAmount  FROM  TMS_ORDER  t";
			sql = sql + "  where t.PROGRAMS_ORDER_ID  = ? ";
			Object[] obj=new Object[1];
			obj[0]=programsOrderId;
			userList = this.jdbcTemplate.query(sql,obj , (RowMapper)ParameterizedBeanPropertyRowMapper.newInstance(UserBettors.class));
		} catch (Exception e) {
			logger.error("合买部分成功之登陆的渠道,插入到新建的表user_bettors中出错", e);
		}
		return userList;
	}
	@Override
	public List<UserBettors> queryHMAllSuccessByProgramsOrderId(
			Map<String, Object> paramMap) {
		List<UserBettors>  userList=null;
		String programsOrderId= paramMap.get("programsOrderId")+"";
		try {
			String sql = " SELECT   SUM(T1.ORDER_AMOUNT) as  renGouSuccessAmount    , SUM(T1.ORDER_AMOUNT) as  renGouAmount    , USER_CODE  as userCode   ";
			sql = sql  + " FROM     TMS_ORDER  t1  where         t1.PROGRAMS_ORDER_ID  =  ?  group BY  T1.USER_CODE";
			Object[] obj=new Object[1];
			obj[0]=programsOrderId;
			userList = this.jdbcTemplate.query(sql,obj , (RowMapper)ParameterizedBeanPropertyRowMapper.newInstance(UserBettors.class));
		} catch (Exception e) {
			logger.error("合买全部成功之登陆的渠道,插入到新建的表user_bettors中出错", e);
		}
		return userList;
	}
}
