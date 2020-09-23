package com.cndym.entity.data.task;

import java.util.Date;

import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;

//积分汇总日报									
public class ScoreMainData extends DataDetail{
	
	
	//统计日期
	@ExportDataConfig(formats={@Format("yyyy-MM-dd")})
	private Date currentDate;
	
	//产生积分总数	
	private Integer makeScorePeopleCount;//总人数
	private Integer makeScoreTotalScore;//积分总数
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double makeScoreAverageScore;//人均产生积分
	//消耗积分总数	
	private Integer costScorePeopleCount;//消耗人数
	private Integer costScoreTotalScore;//消耗总数
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double costScoreAverageScore;//人均消耗积分
	//当天登录用户积分总量
	private Integer loginScoreTotalScore;
	//当天积分存量
	private Integer changeScore;
	//积分总存量
	private Long totalScoreInPool;

	
	@Override
	public void putCurrentDate(Date date) {
		setCurrentDate(date);
	}
	
	@Override
	public Date loadCurrentDate() {
		return getCurrentDate();
	}

	public void invokeMakeScoreAverageScore() {
		if(makeScorePeopleCount==0){
			makeScoreAverageScore = Double.valueOf(makeScoreTotalScore/1.0/1);
		}else{
			makeScoreAverageScore = Double.valueOf(makeScoreTotalScore/1.0/makeScorePeopleCount);
		}
	}
	
	public void invokeCostScoreAverageScore(){
		if(costScorePeopleCount==0){
			costScoreAverageScore = Double.valueOf(costScoreTotalScore/1.0/1);
		}else{
			costScoreAverageScore = Double.valueOf(costScoreTotalScore/1.0/costScorePeopleCount);
		}
	}
	
	public void invokeChangeScore() {
		changeScore = makeScoreTotalScore - Math.abs(costScoreTotalScore);
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getMakeScorePeopleCount() {
		return makeScorePeopleCount;
	}

	public void setMakeScorePeopleCount(Integer makeScorePeopleCount) {
		this.makeScorePeopleCount = Math.abs(makeScorePeopleCount);
	}

	public Integer getMakeScoreTotalScore() {
		return makeScoreTotalScore;
	}

	public void setMakeScoreTotalScore(Integer makeScoreTotalScore) {
		this.makeScoreTotalScore = Math.abs(makeScoreTotalScore);
	}

	public Double getMakeScoreAverageScore() {
		return makeScoreAverageScore;
	}

	public void setMakeScoreAverageScore(Double makeScoreAverageScore) {
		this.makeScoreAverageScore = Math.abs(makeScoreAverageScore);
	}

	public Integer getCostScorePeopleCount() {
		return costScorePeopleCount;
	}

	public void setCostScorePeopleCount(Integer costScorePeopleCount) {
		this.costScorePeopleCount = Math.abs(costScorePeopleCount);
	}

	public Integer getCostScoreTotalScore() {
		return costScoreTotalScore;
	}

	public void setCostScoreTotalScore(Integer costScoreTotalScore) {
		this.costScoreTotalScore = Math.abs(costScoreTotalScore);
	}

	public Double getCostScoreAverageScore() {
		return costScoreAverageScore;
	}

	public void setCostScoreAverageScore(Double costScoreAverageScore) {
		this.costScoreAverageScore = Math.abs(costScoreAverageScore);
	}

	public Integer getLoginScoreTotalScore() {
		return loginScoreTotalScore;
	}

	public void setLoginScoreTotalScore(Integer loginScoreTotalScore) {
		this.loginScoreTotalScore = Math.abs(loginScoreTotalScore);
	}

	public Integer getChangeScore() {
		return changeScore;
	}

	public void setChangeScore(Integer changeScore) {
		this.changeScore = changeScore;
	}

	public Long getTotalScoreInPool() {
		return totalScoreInPool;
	}

	public void setTotalScoreInPool(Long totalScoreInPool) {
		this.totalScoreInPool = Math.abs(totalScoreInPool);
	}

	@Override
	public String toString() {
		return "ScoreMainData [makeScorePeopleCount=" + makeScorePeopleCount + ", makeScoreTotalScore="
				+ makeScoreTotalScore + ", makeScoreAverageScore=" + makeScoreAverageScore + ", costScorePeopleCount="
				+ costScorePeopleCount + ", costScoreTotalScore=" + costScoreTotalScore + ", costScoreAverageScore="
				+ costScoreAverageScore + ", currentDate=" + currentDate + ", loginScoreTotalScore="
				+ loginScoreTotalScore + ", changeScore=" + changeScore + ", totalScoreInPool=" + totalScoreInPool
				+ "]";
	}

}
