package com.cndym.entity.data.task;

import java.util.Date;

import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;

/**积分详情日报*/
public class ScoreDetailData extends DataDetail{
	
	@ExportDataConfig(formats={@Format("yyyy-MM-dd")})
	private Date currentDate;
	
	private Integer taskCompletePeopleCount;
	
	private Integer taskCompleteTaskCount;
	
	private Integer taskCompleteRewardScore;
	
	private Integer taskCompleteRecivePeopleCount;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double taskCompleteAverageTaskScore;
	
	private Integer costDrawPeopleCount;
	
	private Integer costDrawCostScore;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double costDrawAverageDrawScore;
	
	private Integer buyPropPeopleCount;
	
	private Integer buyPropCostScore;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double buyPropAverageCostScore;
	
	private Integer buyInventedPropPeopleCount;
	
	private Integer buyInventedPropScoreCount;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double buyInventedPropAverageCount;
	
	private Integer guessMakeSuccessPeopleCount;
	
	private Integer guessMakeRewardScore;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double guessMakeAverageRewardScore;
	
	private Integer guessCostJoinPeopleCount;
	
	private Integer guessCostCostScore;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double guessCostAverageGuessScore;
	
	private Integer guessCostRecoverScore;
	
	private Integer JiangBuTingMakeSuccessPeopleCount;
	
	private Integer JiangBuTingMakeScore;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double JiangBuTingMakeAverageScore;
	
	private Integer guessGodMakeSuccessPeopleCount;
	
	private Integer guessGodMakeSocre;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double guessGodMakeAverageScore;
	
	private Integer buyLuckySocrePeopleCount;
	
	private Integer buyLuckyScoreScore;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double buyLuckyScoreAverageScore;
	
	private Integer guessGodCostPeopleCount;
	
	private Integer guessGodCostSocre;
	
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double guessGodCostAverageScore;
	
	private Integer guessGodReciveScore;
	
	private Integer reclaimPeople;
	private Integer reclaimScore;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double reclaimAverageScore;

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getTaskCompletePeopleCount() {
		return taskCompletePeopleCount;
	}

	public void setTaskCompletePeopleCount(Integer taskCompletePeopleCount) {
		this.taskCompletePeopleCount = Math.abs(taskCompletePeopleCount);
	}

	public Integer getTaskCompleteTaskCount() {
		return taskCompleteTaskCount;
	}

	public void setTaskCompleteTaskCount(Integer taskCompleteTaskCount) {
		this.taskCompleteTaskCount = Math.abs(taskCompleteTaskCount);
	}

	public Integer getTaskCompleteRewardScore() {
		return taskCompleteRewardScore;
	}

	public void setTaskCompleteRewardScore(Integer taskCompleteRewardScore) {
		this.taskCompleteRewardScore = Math.abs(taskCompleteRewardScore);
	}

	public Integer getTaskCompleteRecivePeopleCount() {
		return taskCompleteRecivePeopleCount;
	}

	public void setTaskCompleteRecivePeopleCount(Integer taskCompleteRecivePeopleCount) {
		this.taskCompleteRecivePeopleCount = Math.abs(taskCompleteRecivePeopleCount);
	}

	public Double getTaskCompleteAverageTaskScore() {
		return taskCompleteAverageTaskScore;
	}

	public void setTaskCompleteAverageTaskScore(Double taskCompleteAverageTaskScore) {
		this.taskCompleteAverageTaskScore = Math.abs(taskCompleteAverageTaskScore);
	}

	public Integer getCostDrawPeopleCount() {
		return costDrawPeopleCount;
	}

	public void setCostDrawPeopleCount(Integer costDrawPeopleCount) {
		this.costDrawPeopleCount = Math.abs(costDrawPeopleCount);
	}

	public Integer getCostDrawCostScore() {
		return costDrawCostScore;
	}

	public void setCostDrawCostScore(Integer costDrawCostScore) {
		this.costDrawCostScore = Math.abs(costDrawCostScore);
	}

	public Double getCostDrawAverageDrawScore() {
		return costDrawAverageDrawScore;
	}

	public void setCostDrawAverageDrawScore(Double costDrawAverageDrawScore) {
		this.costDrawAverageDrawScore = Math.abs(costDrawAverageDrawScore);
	}

	public Integer getBuyPropPeopleCount() {
		return buyPropPeopleCount;
	}

	public void setBuyPropPeopleCount(Integer buyPropPeopleCount) {
		this.buyPropPeopleCount = Math.abs(buyPropPeopleCount);
	}

	public Integer getBuyPropCostScore() {
		return buyPropCostScore;
	}

	public void setBuyPropCostScore(Integer buyPropCostScore) {
		this.buyPropCostScore = Math.abs(buyPropCostScore);
	}

	public Double getBuyPropAverageCostScore() {
		return buyPropAverageCostScore;
	}

	public void setBuyPropAverageCostScore(Double buyPropAverageCostScore) {
		this.buyPropAverageCostScore = Math.abs(buyPropAverageCostScore);
	}

	public Integer getGuessMakeSuccessPeopleCount() {
		return guessMakeSuccessPeopleCount;
	}

	public void setGuessMakeSuccessPeopleCount(Integer guessMakeSuccessPeopleCount) {
		this.guessMakeSuccessPeopleCount = Math.abs(guessMakeSuccessPeopleCount);
	}

	public Integer getGuessMakeRewardScore() {
		return guessMakeRewardScore;
	}

	public void setGuessMakeRewardScore(Integer guessMakeRewardScore) {
		this.guessMakeRewardScore = Math.abs(guessMakeRewardScore);
	}

	public Double getGuessMakeAverageRewardScore() {
		return guessMakeAverageRewardScore;
	}

	public void setGuessMakeAverageRewardScore(Double guessMakeAverageRewardScore) {
		this.guessMakeAverageRewardScore = Math.abs(guessMakeAverageRewardScore);
	}

	public Integer getGuessCostJoinPeopleCount() {
		return guessCostJoinPeopleCount;
	}

	public void setGuessCostJoinPeopleCount(Integer guessCostJoinPeopleCount) {
		this.guessCostJoinPeopleCount = Math.abs(guessCostJoinPeopleCount);
	}

	public Integer getGuessCostCostScore() {
		return guessCostCostScore;
	}

	public void setGuessCostCostScore(Integer guessCostCostScore) {
		this.guessCostCostScore = Math.abs(guessCostCostScore);
	}

	public Double getGuessCostAverageGuessScore() {
		return guessCostAverageGuessScore;
	}

	public void setGuessCostAverageGuessScore(Double guessCostAverageGuessScore) {
		this.guessCostAverageGuessScore = Math.abs(guessCostAverageGuessScore);
	}

	public Integer getGuessCostRecoverScore() {
		return guessCostRecoverScore;
	}

	public void setGuessCostRecoverScore(Integer guessCostRecoverScore) {
		this.guessCostRecoverScore = guessCostRecoverScore;
	}

	public Integer getJiangBuTingMakeSuccessPeopleCount() {
		return JiangBuTingMakeSuccessPeopleCount;
	}

	public void setJiangBuTingMakeSuccessPeopleCount(Integer jiangBuTingMakeSuccessPeopleCount) {
		JiangBuTingMakeSuccessPeopleCount = jiangBuTingMakeSuccessPeopleCount;
	}

	public Integer getJiangBuTingMakeScore() {
		return JiangBuTingMakeScore;
	}

	public void setJiangBuTingMakeScore(Integer jiangBuTingMakeScore) {
		JiangBuTingMakeScore = jiangBuTingMakeScore;
	}

	public Double getJiangBuTingMakeAverageScore() {
		return JiangBuTingMakeAverageScore;
	}

	public void setJiangBuTingMakeAverageScore(Double jiangBuTingMakeAverageScore) {
		JiangBuTingMakeAverageScore = jiangBuTingMakeAverageScore;
	}

	public Integer getGuessGodMakeSuccessPeopleCount() {
		return guessGodMakeSuccessPeopleCount;
	}

	public void setGuessGodMakeSuccessPeopleCount(Integer guessGodMakeSuccessPeopleCount) {
		this.guessGodMakeSuccessPeopleCount = Math.abs(guessGodMakeSuccessPeopleCount);
	}

	public Integer getGuessGodMakeSocre() {
		return guessGodMakeSocre;
	}

	public void setGuessGodMakeSocre(Integer guessGodMakeSocre) {
		this.guessGodMakeSocre = Math.abs(guessGodMakeSocre);
	}

	public Double getGuessGodMakeAverageScore() {
		return guessGodMakeAverageScore;
	}

	public void setGuessGodMakeAverageScore(Double guessGodMakeAverageScore) {
		this.guessGodMakeAverageScore = Math.abs(guessGodMakeAverageScore);
	}

	public Integer getGuessGodCostPeopleCount() {
		return guessGodCostPeopleCount;
	}

	public void setGuessGodCostPeopleCount(Integer guessGodCostPeopleCount) {
		this.guessGodCostPeopleCount = Math.abs(guessGodCostPeopleCount);
	}

	public Integer getGuessGodCostSocre() {
		return guessGodCostSocre;
	}

	public void setGuessGodCostSocre(Integer guessGodCostSocre) {
		this.guessGodCostSocre = Math.abs(guessGodCostSocre);
	}

	public Double getGuessGodCostAverageScore() {
		return guessGodCostAverageScore;
	}

	public void setGuessGodCostAverageScore(Double guessGodCostAverageScore) {
		this.guessGodCostAverageScore = Math.abs(guessGodCostAverageScore);
	}

	public Integer getGuessGodReciveScore() {
		return guessGodReciveScore;
	}

	public void setGuessGodReciveScore(Integer guessGodReciveScore) {
		this.guessGodReciveScore = guessGodReciveScore;
	}

	public Integer getReclaimPeople() {
		return reclaimPeople;
	}

	public void setReclaimPeople(Integer reclaimPeople) {
		this.reclaimPeople = reclaimPeople;
	}

	public Integer getReclaimScore() {
		return reclaimScore;
	}

	public void setReclaimScore(Integer reclaimScore) {
		this.reclaimScore = reclaimScore;
	}
	public Double getReclaimAverageScore() {
		return reclaimAverageScore;
	}

	public void setReclaimAverageScore(Double reclaimAverageScore) {
		this.reclaimAverageScore = reclaimAverageScore;
	}

	public Integer getBuyLuckySocrePeopleCount() {
		return buyLuckySocrePeopleCount;
	}

	public void setBuyLuckySocrePeopleCount(Integer buyLuckySocrePeopleCount) {
		this.buyLuckySocrePeopleCount = buyLuckySocrePeopleCount;
	}

	public Integer getBuyLuckyScoreScore() {
		return buyLuckyScoreScore;
	}

	public void setBuyLuckyScoreScore(Integer buyLuckyScoreScore) {
		this.buyLuckyScoreScore = buyLuckyScoreScore;
	}

	public Double getBuyLuckyScoreAverageScore() {
		return buyLuckyScoreAverageScore;
	}

	public void setBuyLuckyScoreAverageScore(Double buyLuckyScoreAverageScore) {
		this.buyLuckyScoreAverageScore = buyLuckyScoreAverageScore;
	}

	public Integer getBuyInventedPropPeopleCount() {
		return buyInventedPropPeopleCount;
	}

	public void setBuyInventedPropPeopleCount(Integer buyInventedPropPeopleCount) {
		this.buyInventedPropPeopleCount = buyInventedPropPeopleCount;
	}

	public Integer getBuyInventedPropScoreCount() {
		return buyInventedPropScoreCount;
	}

	public void setBuyInventedPropScoreCount(Integer buyInventedPropScoreCount) {
		this.buyInventedPropScoreCount = buyInventedPropScoreCount;
	}

	public Double getBuyInventedPropAverageCount() {
		return buyInventedPropAverageCount;
	}

	public void setBuyInventedPropAverageCount(Double buyInventedPropAverageCount) {
		this.buyInventedPropAverageCount = Math.abs(buyInventedPropAverageCount);
	}


	@Override
	public String toString() {
		return "ScoreDetailData [currentDate=" + currentDate + ", taskCompletePeopleCount=" + taskCompletePeopleCount
				+ ", taskCompleteTaskCount=" + taskCompleteTaskCount + ", taskCompleteRewardScore="
				+ taskCompleteRewardScore + ", taskCompleteRecivePeopleCount=" + taskCompleteRecivePeopleCount
				+ ", taskCompleteAverageTaskScore=" + taskCompleteAverageTaskScore + ", costDrawPeopleCount="
				+ costDrawPeopleCount + ", costDrawCostScore=" + costDrawCostScore + ", costDrawAverageDrawScore="
				+ costDrawAverageDrawScore + ", buyPropPeopleCount=" + buyPropPeopleCount + ", buyPropCostScore="
				+ buyPropCostScore + ", buyPropAverageCostScore=" + buyPropAverageCostScore
				+ ", buyInventedPropPeopleCount=" + buyInventedPropPeopleCount + ", buyInventedPropScoreCount="
				+ buyInventedPropScoreCount + ", buyInventedPropAverageCount=" + buyInventedPropAverageCount
				+ ", guessMakeSuccessPeopleCount=" + guessMakeSuccessPeopleCount + ", guessMakeRewardScore="
				+ guessMakeRewardScore + ", guessMakeAverageRewardScore=" + guessMakeAverageRewardScore
				+ ", guessCostJoinPeopleCount=" + guessCostJoinPeopleCount + ", guessCostCostScore="
				+ guessCostCostScore + ", guessCostAverageGuessScore=" + guessCostAverageGuessScore
				+ ", guessCostRecoverScore=" + guessCostRecoverScore + ", JiangBuTingMakeSuccessPeopleCount="
				+ JiangBuTingMakeSuccessPeopleCount + ", JiangBuTingMakeScore=" + JiangBuTingMakeScore
				+ ", JiangBuTingMakeAverageScore=" + JiangBuTingMakeAverageScore + ", guessGodMakeSuccessPeopleCount="
				+ guessGodMakeSuccessPeopleCount + ", guessGodMakeSocre=" + guessGodMakeSocre
				+ ", guessGodMakeAverageScore=" + guessGodMakeAverageScore + ", buyLuckySocrePeopleCount="
				+ buyLuckySocrePeopleCount + ", buyLuckyScoreScore=" + buyLuckyScoreScore
				+ ", buyLuckyScoreAverageScore=" + buyLuckyScoreAverageScore + ", guessGodCostPeopleCount="
				+ guessGodCostPeopleCount + ", guessGodCostSocre=" + guessGodCostSocre + ", guessGodCostAverageScore="
				+ guessGodCostAverageScore + ", guessGodReciveScore=" + guessGodReciveScore + ", reclaimPeople="
				+ reclaimPeople + ", reclaimScore=" + reclaimScore + ", reclaimAverageScore=" + reclaimAverageScore
				+ "]";
	}

	@Override
	public void putCurrentDate(Date date) {
		setCurrentDate(date);
	}

	@Override
	public Date loadCurrentDate() {
		return getCurrentDate();
	}
	
	public void invokeTaskCompleteAverageTaskScore() {
		if(taskCompletePeopleCount==0){
			taskCompleteAverageTaskScore = taskCompleteRewardScore/1.0/1;
		}else{
			taskCompleteAverageTaskScore = taskCompleteRewardScore/1.0/taskCompleteRecivePeopleCount;
		}
	}
	
	public void invokeCostDrawAverageDrawScore() {
		if(costDrawPeopleCount == 0){
			costDrawAverageDrawScore = costDrawCostScore/1.0/1;
		}else{
			costDrawAverageDrawScore = costDrawCostScore/1.0/costDrawPeopleCount;
		}
	}
	
	public void invokeBuyPropAverageCostScore() {
		if(buyPropPeopleCount == 0){
			buyPropAverageCostScore = buyPropCostScore/1.0/1;
		}else{
			buyPropAverageCostScore = buyPropCostScore/1.0/buyPropPeopleCount;
		}
	}
	public void invokeGuessMakeAverageRewardScore() {
		if(guessMakeSuccessPeopleCount == 0){
			guessMakeAverageRewardScore = guessMakeRewardScore/1.0/1;
		}else{
			guessMakeAverageRewardScore = guessMakeRewardScore/1.0/guessMakeSuccessPeopleCount;
		}
	}

	public void invokeGuessCostAverageGuessScore() {
		if(guessCostJoinPeopleCount == 0){
			guessCostAverageGuessScore = guessCostCostScore/1.0/1;
		}else{
			guessCostAverageGuessScore = guessCostCostScore/1.0/guessCostJoinPeopleCount;
		}
	}
	public void invokeGuessCostRecoverScore() {
		guessCostRecoverScore = guessCostCostScore-guessMakeRewardScore;
	}
	public void invokeJiangBuTingMakeAverageScore(){
		if(JiangBuTingMakeSuccessPeopleCount == 0){
			JiangBuTingMakeAverageScore = JiangBuTingMakeScore/1.0/1;
		}else{
			JiangBuTingMakeAverageScore = JiangBuTingMakeScore/1.0/JiangBuTingMakeSuccessPeopleCount;
		}
	}
	public void invokeGuessGodMakeAverageScore() {
		if(guessGodMakeSuccessPeopleCount == 0){
			guessGodMakeAverageScore = guessGodMakeSocre/1.0/1;
		}else{
			guessGodMakeAverageScore = guessGodMakeSocre/1.0/guessGodMakeSuccessPeopleCount;
		}
	}
	public void invokeGuessGodCostAverageScore() {
		if(guessGodCostPeopleCount == 0){
			guessGodCostAverageScore = guessGodCostSocre/1.0/1;
		}else{
			guessGodCostAverageScore = guessGodCostSocre/1.0/guessGodCostPeopleCount;
		}
	}
	public void invokeGuessGodReciveScore() {
		guessGodReciveScore = guessGodCostSocre-guessGodMakeSocre;
	}
	public void invokeReclimAverageScore() {
		if(reclaimPeople == 0){
			reclaimAverageScore = reclaimScore/1.0/1;
		}else{
			reclaimAverageScore = reclaimScore/1.0/reclaimPeople;
		}
	}
	public void invokeBuyLuckyScoreAverageScore() {
		if(buyLuckySocrePeopleCount == 0){
			buyLuckyScoreAverageScore = buyLuckyScoreScore/1.0/1;
		}else{
			buyLuckyScoreAverageScore = buyLuckyScoreScore/1.0/buyLuckySocrePeopleCount;
		}
	}
	public void invokeBuyInventedPropAverageCount(){
		if(buyInventedPropPeopleCount == 0){
			buyInventedPropAverageCount = buyInventedPropScoreCount/1.0/1;
		}else{
			buyInventedPropAverageCount = buyInventedPropScoreCount/1.0/buyInventedPropPeopleCount;
		}
	}
}
