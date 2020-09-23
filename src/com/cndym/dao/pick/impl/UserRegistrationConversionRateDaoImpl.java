package com.cndym.dao.pick.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.pick.IUserRegistrationConversionRateDao;
import com.cndym.entity.data.pick.UserMemberPick;
import com.cndym.entity.data.pick.UserRegistrationConversionRate;
import com.cndym.util.BuilderSql;

@Repository
public class UserRegistrationConversionRateDaoImpl extends
		BaseDaoImpl<UserRegistrationConversionRate> implements IUserRegistrationConversionRateDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	private Logger logger = Logger.getLogger(getClass());
	@Override
	public List<UserRegistrationConversionRate> queryUserRegistrationConversionRateList(Integer loginStatus,Integer bettingStatus,Integer isLoginFlag) {
		List<UserRegistrationConversionRate>   urcrList=new ArrayList<UserRegistrationConversionRate>();
		try {
			BuilderSql  bs=new BuilderSql(UserRegistrationConversionRate.class);
			if (bettingStatus!=0) {
				bs.addWhereClause("bettingStatus = ? ",bettingStatus);
			}
			if (loginStatus!=0) {
				bs.addWhereClause("loginStatus = ? ",loginStatus);
			}
			if (isLoginFlag!=0) {
				bs.addWhereClause("isLoginFlag = ? ",isLoginFlag);
			}
			bs.addOrderClause(false, "createTime");
			urcrList=getList(bs);
		} catch (Exception e) {
			logger.error("获取新用户or登录用,登陆or投注转行率的中间表信息", e);
		}
		return urcrList;
	}
	@Override
	public void deleteUserRegRate(String start, String endDate) {
		try {
			String sql = " DELETE  USER_REGISTRATION_RATE t where t.CREATE_TIME >= to_date('" + start + "','yyyy-MM-dd')  AND  t.CREATE_TIME < to_date('" + endDate + "','yyyy-MM-dd') ";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("同步之前先删除数" + e);
		}
	}
}
