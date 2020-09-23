package com.cndym.dao.task.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cndym.entity.data.task.ScoreDetailData;

public class ScoreDetailDataMapper implements RowMapper<ScoreDetailData> {

	@Override
	public ScoreDetailData mapRow(ResultSet r, int arg1) throws SQLException {
		ScoreDetailData d = new ScoreDetailData();
		d.setCurrentDate(r.getDate("c_date"));
		d.setTaskCompletePeopleCount(r.getInt("tc_people_count"));
		d.setTaskCompleteTaskCount(r.getInt("tc_task_count"));
		d.setTaskCompleteRewardScore(r.getInt("tc_reward_score"));
		d.setTaskCompleteRecivePeopleCount(r.getInt("tc_recive_people_count"));
		d.setTaskCompleteAverageTaskScore(r.getDouble("tc_average_task_score"));
		d.setCostDrawPeopleCount(r.getInt("cd_people_count"));
		d.setCostDrawCostScore(r.getInt("cd_cost_score"));
		d.setCostDrawAverageDrawScore(r.getDouble("cd_average_draw_score"));
		d.setBuyPropPeopleCount(r.getInt("bp_people_count"));
		d.setBuyPropCostScore(r.getInt("bp_cost_score"));
		d.setBuyPropAverageCostScore(r.getDouble("bp_average_cost_score"));
		d.setGuessMakeSuccessPeopleCount(r.getInt("gm_success_people_count"));
		d.setGuessMakeRewardScore(r.getInt("gm_reward_score"));
		d.setGuessMakeAverageRewardScore(r.getDouble("gm_average_reward_score"));
		d.setGuessCostJoinPeopleCount(r.getInt("gc_join_people_count"));
		d.setGuessCostCostScore(r.getInt("gc_cost_score"));
		d.setGuessCostAverageGuessScore(r.getDouble("gc_average_guess_score"));
		d.setGuessCostRecoverScore(r.getInt("gc_recover_score"));
		d.setJiangBuTingMakeSuccessPeopleCount(r.getInt("jbt_make_success_people_count"));
		d.setJiangBuTingMakeScore(r.getInt("jbt_make_score"));
		d.setJiangBuTingMakeAverageScore(r.getDouble("jbt_make_average_score"));
		d.setGuessGodMakeSuccessPeopleCount(r.getInt("ggm_success_people_count"));
		d.setGuessGodMakeSocre(r.getInt("ggm_socre"));
		d.setGuessGodMakeAverageScore(r.getDouble("ggm_average_score"));
		d.setGuessGodCostPeopleCount(r.getInt("ggc_people_count"));
		d.setGuessGodCostSocre(r.getInt("ggc_socre"));
		d.setGuessGodCostAverageScore(r.getDouble("ggc_average_score"));
		d.setGuessGodReciveScore(r.getInt("gg_recive_score"));
		d.setReclaimPeople(r.getInt("r_people"));
		d.setReclaimScore(r.getInt("r_score"));
		d.setReclaimAverageScore(r.getDouble("r_average_score"));
		d.setBuyLuckyScoreScore(r.getInt("bls_score"));
		d.setBuyLuckySocrePeopleCount(r.getInt("bls_people"));
		d.setBuyLuckyScoreAverageScore(r.getDouble("bls_average_score"));
		d.setBuyInventedPropPeopleCount(r.getInt("bip_people"));
		d.setBuyInventedPropScoreCount(r.getInt("bip_score"));
		d.setBuyInventedPropAverageCount(r.getDouble("bip_average_score"));
		return d;
	}

}
