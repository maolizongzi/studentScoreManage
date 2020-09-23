package com.cndym.dao.put.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.put.IProgramsDao;
import com.cndym.entity.data.pick.ProgramsPick;
import com.cndym.entity.data.pick.ProgramsPickRegistered;
import com.cndym.entity.data.put.Programs;



@SuppressWarnings("unchecked")
@Repository
public class ProgramsDaoImpl extends BaseDaoImpl<Programs> implements IProgramsDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public void deletePrograms(String startDate, String endDate) {
		try {
			String sql = "delete from PROGRAMS_PICK t where t.return_time  >= to_date('"+startDate+"','yyyy-MM-dd')  AND  t.return_time  < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("同步之前先删除数" + e);
		}
	}
	
	
	
	@Override
	// 充值用户信息表【代购，合买，追号】-------登陆渠道
	public boolean queryAllDataByStatusLogin(String startDate, String endDate) {
		boolean falg=false;
		try {
			String sql =  " insert into PROGRAMS_PICK(id,USER_CODE,ORDER_AMOUNT ,PARTNERS_CODE,RETURN_TIME,ORDER_STATUS, BUY_TYPE , PROGRAMS_ORDER_ID)";
			sql = sql + " select SEQ_PROGRAMS_PICK.NEXTVAL, t.user_code,t.order_amount,t.partners_code,t.return_time  ,t.order_status,t.buy_type ,t.programs_order_id   from tms_programs t";
			sql = sql + " where  t.return_time >= to_date('"+startDate+"','yyyy-MM-dd')  and t.return_time < to_date('"+endDate+"','yyyy-MM-dd') ";
			jdbcTemplate.update(sql);
			falg=true;
		} catch (Exception e) {
			falg=false;
			logger.error("查询当天所有的方案  -->之登陆的渠道,插入到新建的表PROGRAMS_PICK中出错", e);
		}
		return falg;
	}

	// 充值用户信息表【代购，合买，追号】-------注册渠道
	public boolean  queryAllDataByStatusRegistered(String startDate, String endDate){
		boolean falg=false;
		try {
			String sql = "delete from PROGRAMS_PICK_REGISTERED t where t.create_time  >= to_date('"+startDate+"','yyyy-MM-dd')  AND  t.create_time  < to_date('"+endDate+"','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
			
			sql = " insert into PROGRAMS_PICK_REGISTERED(id,USER_CODE,ORDER_AMOUNT ,PARTNERS_CODE,RETURN_TIME,ORDER_STATUS, BUY_TYPE,PROGRAMS_ORDER_ID)";
			sql = sql + "  SELECT  SEQ_PROGRAMS_PICK_REG.NEXTVAL,  t.user_code,t.order_amount, u.UNION_id,t.return_time  ,t.order_status,t.buy_type  ,t.programs_order_id  FROM  tms_programs  t  INNER JOIN      user_member  u on t.user_code = u.user_code ";
			sql = sql +"   where t.return_time >= to_date('"+startDate+"','yyyy-MM-dd')  and t.return_time < to_date('"+endDate+"','yyyy-MM-dd') ";
			jdbcTemplate.update(sql);
			falg=true;
		} catch (Exception e) {
			falg=false;
			logger.error("查询当天所有的方案  -->之注册的渠道,插入到新建的表PROGRAMS_PICK_REGISTERED中出错", e);
		}
		return falg;
	}
	
	
	
	
	//查询登陆渠道方案
	@Override
	public List<ProgramsPick> queryAllDataLogin(String startDate, String endDate) {
		List<ProgramsPick> programs=null;
		try {
			String sql = "  SELECT * from PROGRAMS_PICK t where  t.order_status < 3  and     t.order_status >0    " ;
			sql = sql + "  and  t.return_time >= to_date(?,'yyyy-MM-dd')  and t.return_time < to_date(?,'yyyy-MM-dd') ";
			Object[] obj=new Object[2];
			obj[0]=startDate;
			obj[1]=endDate;
			programs= this.jdbcTemplate.query(sql,obj , (RowMapper)ParameterizedBeanPropertyRowMapper.newInstance(ProgramsPick.class));
		} catch (Exception e) {
			logger.error("查询当天所有成功和部分成功的方案  -->之登陆的渠道", e);
		}
		return programs;
	}
	
	
	//查询注册渠道方案
	@Override
	public List<ProgramsPickRegistered> queryAllDataRegistered(String startDate,
			String endDate) {
		List<ProgramsPickRegistered> programs=null;
		try {
			String sql = "  SELECT * from PROGRAMS_PICK_REGISTERED  t where  t.order_status < 3  and     t.order_status >0    " ;
			sql = sql + "  and  t.return_time >= to_date(?,'yyyy-MM-dd')  and t.return_time < to_date(?,'yyyy-MM-dd') ";
			Object[] obj=new Object[2];
			obj[0]=startDate;
			obj[1]=endDate;
			programs= this.jdbcTemplate.query(sql,obj , (RowMapper)ParameterizedBeanPropertyRowMapper.newInstance(ProgramsPickRegistered.class));
		} catch (Exception e) {
			logger.error("查询当天所有成功和部分成功的方案  -->之登陆的渠道", e);
		}
		return programs;
	}
	
}
