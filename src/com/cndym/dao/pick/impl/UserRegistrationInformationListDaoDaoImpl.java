package com.cndym.dao.pick.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;


import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.pick.IUserRegistrationInformationListDao;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.util.BuilderSql;
import com.cndym.util.PageView;

@Repository
@SuppressWarnings({"unchecked","rawtypes"})
public class UserRegistrationInformationListDaoDaoImpl extends
		BaseDaoImpl<UserRegistrationInformationList> implements
		IUserRegistrationInformationListDao {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Override
	public void deleteUserRegList(String startDate, String endDate) {
		try {
			String sql = " DELETE  USER_REGISTRATION_LIST t where t.CREATE_TIME >= to_date('" + startDate + "','yyyy-MM-dd')  AND  t.CREATE_TIME < to_date('" + endDate + "','yyyy-MM-dd') ";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("同步之前先删除数" + e);
		}		
	}
	
	@Override
	public void updateUserRegList(String start, String end,
			Map<Integer, String> paramMap, Integer interval) {
		try {
			String column=paramMap.get(interval);
			StringBuffer sql = new StringBuffer(" update user_registration_list t set t."+column+"=0 where  t.registration_time= to_date('" + start + "','yyyy-MM-dd')  and t."+column+" is null ");
			logger.info("sql:"+sql);
			jdbcTemplate.update(sql.toString());
		} catch (Exception e) {
			logger.error("补漏数据" + e);
		}		
	}
	@Override
	public PageView queryPageView(UserRegistrationInformationList userRegistr,
			int currentPage,String startTime,String endTime) {
		PageView view = null;
		try {
			BuilderSql bs = new BuilderSql(UserRegistrationInformationList.class);
			Integer retentionRateFlag= userRegistr.getRetentionRateFlag();
			bs.addWhereClause("retentionRateFlag=?", retentionRateFlag);
			String sid=userRegistr.getSid();
			Integer isRegistrStatus=userRegistr.getIsRegistrStatus();
			if (StringUtils.isNotBlank(sid)) {
				bs.addWhereClause("sid=?",sid);
			}else{
				bs.addWhereClause("isCount=?", 1);
			}
			if (StringUtils.isNotEmpty(startTime)) {
				bs.addWhereClause("registrationTime >= to_date(?,'yyyy-MM-dd') ",
						startTime);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				bs.addWhereClause("registrationTime <= to_date(?,'yyyy-MM-dd') ",
						endTime);
			}
			bs.addWhereClause("isRegistrStatus=?",isRegistrStatus);
			bs.addOrderClause(false, "registrationTime");
			view = this.getPageView(bs, currentPage);
		} catch (Exception e) {
			logger.error(" 分页查询用户保留率 " + e);
		}
		
		return view;
	}
	
	public List<UserRegistrationInformationList> queryByQuery(Map<String, Object> userMap){
		List<UserRegistrationInformationList> userRegistrationInformationList =new ArrayList<UserRegistrationInformationList>();
		try {
			List paramList = new ArrayList();
			
			StringBuffer sql = new StringBuffer(" select * from USER_REGISTRATION_LIST t where 1=1 ");
			if (StringUtils.isNotEmpty((String)userMap.get("sid"))) {
				sql.append(" and t.sid = ?  ");
				String sidId = (String) userMap.get("sid");
				paramList.add(sidId);
			}else{
				sql.append(" and t.sid is null  ");
			}
			
			if (userMap.get("retentionRateFlag") != null) {
				sql.append(" and t.RETENTION_RATE_FLAG = ?  ");
				Integer retentionRateFlagId = (Integer) userMap.get("retentionRateFlag");
				paramList.add(retentionRateFlagId);
			}
			if (userMap.get("isRegistrStatus") != null) {
				sql.append(" and t.IS_REGISTR_STATUS = ?  ");
				Integer retentionRateFlagId = (Integer) userMap.get("isRegistrStatus");
				paramList.add(retentionRateFlagId);
			}
			if (userMap.get("registrationTime") != null) {
				sql.append(" and t.registration_time = to_date(?,'yyyy-MM-dd')");
				String registrationTime = (String) userMap.get("registrationTime");
				paramList.add(registrationTime);
			}
			
			Integer paramSize = Integer.valueOf(paramList.size());
			Object[] obj = new Object[paramSize.intValue()];
			for (int index = 0; index < paramSize.intValue(); index++) {
			       obj[index] = paramList.get(index);
			}
			
			
			userRegistrationInformationList=this.jdbcTemplate.query(sql.toString(), obj,
					(RowMapper) ParameterizedBeanPropertyRowMapper
					.newInstance(UserRegistrationInformationList.class));
			if(userRegistrationInformationList!=null && userRegistrationInformationList.size()>0){
				return userRegistrationInformationList;
			}
		} catch (Exception e) {
			logger.error("",e);
		}
	
		return null;
	}
	@Override
	public UserRegistrationInformationList queryByQueryURIL(
			Map<String, Object> userMap) {
		List<UserRegistrationInformationList>  list=this.queryByQuery(userMap);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
