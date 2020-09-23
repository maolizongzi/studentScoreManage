package com.cndym.dao.task.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.dao.task.mapper.ScoreDetailDataMapper;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.ScoreDetailData;

@Repository
public class ScoreDetailDataDetailDao implements IDataTableDetailDao {

	@Resource
	private JdbcTemplate taskJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public DataDetail loadFromSource(Date start, Date end) {
		ScoreDetailData data = new ScoreDetailData();
		data.setCurrentDate(end);
		String tmPeopCountSql = "SELECT COALESCE(\"count\"(*),0) FROM (SELECT 1 FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE t.finish_date = ? GROUP BY t.user_code) a";
		String tmTaskCountSql = "SELECT COALESCE(\"count\"(*),0) FROM t_user_task_rec t LEFT JOIN t_task_conf c ON t.task_id = c.\"id\" WHERE  t.finish_date = ?";
//		String tmScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE \"type\"=0 AND datetime >= ? AND datetime <= ?";
//		String tmRecivePeopCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE \"type\"=0 AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
		String taskRecoverPeopleCountSql = "SELECT COALESCE(count(DISTINCT user_code),0) peopleCount,COALESCE(count(1),0) taskCount,COALESCE(sum(l.score),0) scoreCount FROM (SELECT * FROM t_user_score_log WHERE datetime >= ? AND datetime <= ?) l LEFT JOIN t_task_conf t ON t.task_name = l.remark AND now()<= down_time WHERE t.task_type='0' or t.task_type='1'";
		List<Map<String, Object>> queryForList = taskJdbcTemplate.queryForList(taskRecoverPeopleCountSql,start,end);
		data.setTaskCompleteRewardScore(Integer.parseInt(queryForList.get(0).get("scoreCount").toString()));
		data.setTaskCompleteRecivePeopleCount(Integer.parseInt(queryForList.get(0).get("peopleCount").toString()));
		data.setTaskCompletePeopleCount(taskJdbcTemplate.queryForObject(tmPeopCountSql, Integer.class,start));
		data.setTaskCompleteTaskCount(taskJdbcTemplate.queryForObject(tmTaskCountSql, Integer.class,start));
//		data.setTaskCompleteRecivePeopleCount(taskJdbcTemplate.queryForObject(tmRecivePeopCountSql, Integer.class,start,end));
//		data.setTaskCompleteRewardScore(taskJdbcTemplate.queryForObject(tmScoreSql, Integer.class,start,end));
		data.invokeTaskCompleteAverageTaskScore();
		
		String cdPeopCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE type = '1' AND remark LIKE '%抽奖%' AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
		String cdCostScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE type = '1' AND remark LIKE '%抽奖%' AND datetime >= ? AND datetime <= ?";
		data.setCostDrawCostScore(taskJdbcTemplate.queryForObject(cdCostScoreSql, Integer.class,start,end));
		data.setCostDrawPeopleCount(taskJdbcTemplate.queryForObject(cdPeopCountSql, Integer.class,start,end));
		data.invokeCostDrawAverageDrawScore();
		
//		String bpPeopCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE  remark='积分兑换' AND  AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
//		String bpCostScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE remark='积分兑换' AND datetime >= ? AND datetime <= ?";
		String bpSql = "SELECT COALESCE(sum(score),0) score , COALESCE(count(DISTINCT (user_code)),0) people  FROM t_shop_user_prop WHERE get_way = '1' AND get_time >= ? AND get_time < ?";
		List<Map<String, Object>> bpList = taskJdbcTemplate.queryForList(bpSql,start,end);
		data.setBuyPropPeopleCount(Integer.parseInt(bpList.get(0).get("people").toString()));
		data.setBuyPropCostScore(Integer.parseInt(bpList.get(0).get("score").toString()));
		data.invokeBuyPropAverageCostScore();
		
		//兑换实物消耗积分
		String bipSql = "SELECT COALESCE(sum(score),0) score,COALESCE(count(DISTINCT (user_code)),0) people FROM t_shop_user_prop_log WHERE is_invented = 1 AND get_time >= ? AND get_time <= ? ";
		List<Map<String, Object>> bipList = taskJdbcTemplate.queryForList(bipSql,start,end);
		data.setBuyInventedPropPeopleCount(Integer.parseInt(bipList.get(0).get("people").toString()));
		data.setBuyInventedPropScoreCount(Integer.parseInt(bipList.get(0).get("score").toString()));
		data.invokeBuyInventedPropAverageCount();
		
		//猜蓝球活动产生积分		
//		String gbmPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE remark='猜蓝球赢积分' AND score>0 AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
//		String gbmScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE remark='猜蓝球赢积分'  AND score>0 AND datetime >= ? AND datetime <= ?";
//		data.setGuessMakeRewardScore(taskJdbcTemplate.queryForObject(gbmScoreSql, Integer.class,start,end));
//		data.setGuessMakeSuccessPeopleCount(taskJdbcTemplate.queryForObject(gbmPeopleCountSql, Integer.class,start,end));
//		data.invokeGuessMakeAverageRewardScore();
		//猜蓝球活动消耗积分+猜蓝球回收
//		String gbcPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE remark='猜蓝球赢积分' AND score<0 AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
//		String gbcCostScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE remark='猜蓝球赢积分'  AND score<0 AND datetime >= ? AND datetime <= ?";
//		data.setGuessCostJoinPeopleCount(taskJdbcTemplate.queryForObject(gbcPeopleCountSql, Integer.class,start,end));
//		data.setGuessCostCostScore(taskJdbcTemplate.queryForObject(gbcCostScoreSql, Integer.class,start,end));
//		data.invokeGuessCostRecoverScore();
//		data.invokeGuessCostAverageGuessScore();
		//奖不停活动产生积分		
//		String jbtPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE remark='中彩票奖不停' AND score>0 AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
//		String jbtScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE remark='中彩票奖不停'  AND score>0 AND datetime >= ? AND datetime <= ?";
//		data.setJiangBuTingMakeScore(taskJdbcTemplate.queryForObject(jbtScoreSql, Integer.class,start,end));
//		data.setJiangBuTingMakeSuccessPeopleCount(taskJdbcTemplate.queryForObject(jbtPeopleCountSql, Integer.class,start,end));
//		data.invokeJiangBuTingMakeAverageScore();
		
		
		String ggmPeopleCountSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE remark='猜神驾到' AND score>0 AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
		String ggmScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE remark='猜神驾到'  AND score>0 AND datetime >= ? AND datetime <= ?";
		data.setGuessGodMakeSocre(taskJdbcTemplate.queryForObject(ggmScoreSql, Integer.class,start,end));
		data.setGuessGodMakeSuccessPeopleCount(taskJdbcTemplate.queryForObject(ggmPeopleCountSql, Integer.class,start,end));
		data.invokeGuessGodMakeAverageScore();
		
		String ggcPeopleContSql = "SELECT COALESCE(\"count\"(*),0) FROM(SELECT 1 FROM t_user_score_log WHERE remark='猜神驾到' AND score<0 AND datetime >= ? AND datetime <= ? GROUP BY user_code) a";
		String ggcScoreSql = "SELECT COALESCE(\"sum\"(score),0) FROM t_user_score_log WHERE remark='猜神驾到'  AND score<0 AND datetime >= ? AND datetime <= ?";
		data.setGuessGodCostPeopleCount(taskJdbcTemplate.queryForObject(ggcPeopleContSql, Integer.class,start,end));
		data.setGuessGodCostSocre(taskJdbcTemplate.queryForObject(ggcScoreSql, Integer.class,start,end));
		data.invokeGuessGodCostAverageScore();
		data.invokeGuessGodReciveScore();
		
		String buyLuckySql = "SELECT COALESCE (SUM(score), 0) score,COALESCE (count(DISTINCT user_code)) people FROM t_user_score_log WHERE	remark = '购买好运值' AND datetime >= ? AND datetime <= ?";
		List<Map<String, Object>> buyLuckyList = taskJdbcTemplate.queryForList(buyLuckySql,start,end);
		data.setBuyLuckySocrePeopleCount(Integer.parseInt(buyLuckyList.get(0).get("people").toString()));
		data.setBuyLuckyScoreScore(Integer.parseInt(buyLuckyList.get(0).get("score").toString()));
		data.invokeBuyLuckyScoreAverageScore();
		
		
		String reclaimSql = "SELECT COALESCE(count(DISTINCT user_code),0) people,COALESCE(sum(reclaim_score),0) score FROM t_shop_user_prop_reclaim_log WHERE reclaim_time >= ? AND reclaim_time <= ?";
		List<Map<String, Object>> reclaimFragList = taskJdbcTemplate.queryForList(reclaimSql,start,end);
		data.setReclaimPeople(Integer.parseInt(reclaimFragList.get(0).get("people").toString()));
		data.setReclaimScore(Integer.parseInt(reclaimFragList.get(0).get("score").toString()));
		data.invokeReclimAverageScore();
		
		return data;
	}

	@Override
	public void saveToLocal(DataDetail data) {
		String sql = "insert into task_score_detail_data\n" +
			"(id,c_date,tc_people_count,tc_task_count,tc_reward_score,tc_recive_people_count,tc_average_task_score,cd_people_count,cd_cost_score,\n" + 
			"cd_average_draw_score,bp_people_count,bp_cost_score,bp_average_cost_score,gm_success_people_count,gm_reward_score,gm_average_reward_score,\n" + 
			"gc_join_people_count,gc_cost_score,gc_average_guess_score,gc_recover_score,jbt_make_success_people_count,jbt_make_score,\n" + 
			"jbt_make_average_score,ggm_success_people_count,ggm_socre,ggm_average_score,ggc_people_count,ggc_socre,ggc_average_score,gg_recive_score,\n"+ 
			"r_people,r_score,r_average_score,bls_people,bls_score,bls_average_score,bip_people,bip_score,bip_average_score)\n" + 
			"values\n" + 
			"(seq_task_score_detail_data.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		ScoreDetailData d = (ScoreDetailData) data;
		jdbcTemplate.update(sql,d.getCurrentDate(),d.getTaskCompletePeopleCount(),d.getTaskCompleteTaskCount(),d.getTaskCompleteRewardScore(),
				d.getTaskCompleteRecivePeopleCount(),d.getTaskCompleteAverageTaskScore(),d.getCostDrawPeopleCount(),d.getCostDrawCostScore(),
				d.getCostDrawAverageDrawScore(),d.getBuyPropPeopleCount(),d.getBuyPropCostScore(),d.getBuyPropAverageCostScore(),
				d.getGuessMakeSuccessPeopleCount(),d.getGuessMakeRewardScore(),d.getGuessMakeAverageRewardScore(),d.getGuessCostJoinPeopleCount(),
				d.getGuessCostCostScore(),d.getGuessCostAverageGuessScore(),d.getGuessCostRecoverScore(),
				d.getJiangBuTingMakeSuccessPeopleCount(),d.getJiangBuTingMakeScore(),d.getJiangBuTingMakeAverageScore(),
				d.getGuessGodMakeSuccessPeopleCount(),d.getGuessGodMakeSocre(),d.getGuessGodMakeAverageScore(),d.getGuessGodCostPeopleCount(),
				d.getGuessGodCostSocre(),d.getGuessGodCostAverageScore(),d.getGuessGodReciveScore(),
				d.getReclaimPeople(),d.getReclaimScore(),d.getReclaimAverageScore(),d.getBuyLuckySocrePeopleCount(),d.getBuyLuckyScoreScore(),d.getBuyLuckyScoreAverageScore(),
				d.getBuyInventedPropPeopleCount(),d.getBuyInventedPropScoreCount(),d.getBuyInventedPropAverageCount());
	}

	@Override
	public List<DataDetail> loadFromLoacl(Date start, Date end) {
		String sql = "select c_date,tc_people_count,tc_task_count,tc_reward_score,tc_recive_people_count,tc_average_task_score,cd_people_count,cd_cost_score,\n" + 
			"cd_average_draw_score,bp_people_count,bp_cost_score,bp_average_cost_score,gm_success_people_count,gm_reward_score,gm_average_reward_score,\n" + 
			"gc_join_people_count,gc_cost_score,gc_average_guess_score,gc_recover_score,jbt_make_success_people_count,jbt_make_score,\n" + 
			"jbt_make_average_score,ggm_success_people_count,ggm_socre,ggm_average_score,ggc_people_count,ggc_socre,ggc_average_score,gg_recive_score,\n"+ 
			"r_people,r_score,r_average_score,bls_people,bls_score,bls_average_score,bip_people,bip_score,bip_average_score\n" + 
			"from TASK_SCORE_DETAIL_DATA\n" + 
			"where c_date>=? and c_date<=? order by c_date desc";
		List query = jdbcTemplate.query(sql, new ScoreDetailDataMapper(),start,end);
		return query;
	}

	@Override
	public void deleteFromLocal(Date start, Date end) {
		String sql = "delete from task_score_detail_data where c_date>=? and c_date<=?";
		jdbcTemplate.update(sql,start,end);
	}

}
