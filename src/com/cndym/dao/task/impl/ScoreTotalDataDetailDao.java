package com.cndym.dao.task.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.ScoreMainData;
import com.cndym.util.export.Utils;

@Repository
public class ScoreTotalDataDetailDao implements IDataTableDetailDao {

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public ScoreMainData loadFromSource(Date start, Date end) {
		ScoreMainData data = new ScoreMainData();
		String makeScorePeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE score>0 AND datetime >= ? AND datetime <= ? GROUP BY user_code) a;";
		String makeScoreTotalScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE score>0 AND datetime >= ? AND datetime <= ?";
		String costScoreTotalScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE score<0 AND datetime >= ? AND datetime <= ?";
		String costScorePeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE score<0  AND datetime >= ? AND datetime <= ? GROUP BY user_code) a;";
		String totalScoreInPoolSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score";
		String loginScoreTotalScore = "SELECT COALESCE(\"sum\"(all_score),0) FROM t_user_score_log WHERE \"type\" = 0 AND (\"remark\" = '用户登录' OR \"remark\" = '今日登录') AND datetime >= ? AND datetime <= ?";
		Object[] args = new Object[]{start,end};
		data.setMakeScoreTotalScore(taskJdbcTemplate.queryForObject(makeScoreTotalScoreSql, Integer.class, args));
		data.setMakeScorePeopleCount(taskJdbcTemplate.queryForObject(makeScorePeopleCountSql, Integer.class, args));
		data.setCostScoreTotalScore(Math.abs(taskJdbcTemplate.queryForObject(costScoreTotalScoreSql, Integer.class, args)));
		data.setCostScorePeopleCount(taskJdbcTemplate.queryForObject(costScorePeopleCountSql, Integer.class, args));
		data.setLoginScoreTotalScore(taskJdbcTemplate.queryForObject(loginScoreTotalScore, Integer.class, args));
		data.setTotalScoreInPool(taskJdbcTemplate.queryForObject(totalScoreInPoolSql, Long.class));
		data.invokeMakeScoreAverageScore();
		data.invokeCostScoreAverageScore();
		data.invokeChangeScore();
		data.putCurrentDate(start);
		return data;
	}

	public void saveToLocal(ScoreMainData data) {
		String date = Utils.formatDate2Str(data.getCurrentDate(), "yyyy-MM-dd");
		String sql = "insert into TASK_SCORE_MAIN_DATA\n" +
						"  (ID,c_date,MAKE_SCORE_PEOPLE_COUNT,MAKE_SCORE_TOTAL_SCORE,MAKE_SCORE_AVERAGE_SCORE\n" + 
						"  ,COST_SCORE_PEOPLE_COUNT,COST_SCORE_TOTAL_SCORE,COST_SCORE_AVERAGE_SCORE,LOGIN_SCORE_TOTAL_SCORE\n" + 
						"  ,CHANGE_SCORE,TOTAL_SCORE_IN_POOL)\n" + 
						"values\n" + 
						"  (SEQ_TASK_SCORE_MAIN_DATA.NEXTVAL,TO_DATE('"+date+"','yyyy-MM-dd'),?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,data.getMakeScorePeopleCount(),data.getMakeScoreTotalScore()
				,data.getMakeScoreAverageScore(),data.getCostScorePeopleCount(),data.getCostScoreTotalScore(),data.getCostScoreAverageScore()
				,data.getLoginScoreTotalScore(),data.getChangeScore(),data.getTotalScoreInPool());
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		ScoreMainData data = new ScoreMainData();
		String param = "";		
		List<Object> list = new ArrayList<Object>();
		param = " t.c_date>=? and t.c_date<=? ";
		list.add(start);
		list.add(end);
		String sql = "select t.c_date currentDate,t.make_score_people_count makeScorePeopleCount,t.make_score_total_score makeScoreTotalScore,t.make_score_average_score makeScoreAverageScore,\n" +
			"       t.cost_score_people_count costScorePeopleCount,t.cost_score_total_score costScoreTotalScore,t.cost_score_average_score costScoreAverageScore,\n" + 
			"       t.login_score_total_score loginScoreTotalScore,t.change_score changeScore,t.total_score_in_pool totalScoreInPool\n" + 
			"from TASK_SCORE_MAIN_DATA t " +
			"where ";
		sql += param +" order by c_date desc";
		List query = jdbcTemplate.query(sql,list.toArray(), ParameterizedBeanPropertyRowMapper.newInstance(ScoreMainData.class));
		return query;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		saveToLocal((ScoreMainData)data);
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from TASK_SCORE_MAIN_DATA t where t.c_date>=? and t.c_date<=? ";
		jdbcTemplate.update(sql,start,end);
	}

}
