package com.cndym.entity.data.task;

import java.util.Date;

import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;

/**道具及碎片汇总日报 */
public class PropAndFragMainData extends DataDetail{

	@ExportDataConfig(formats={@Format("yyyy-MM-dd")})
	private Date currentDate;
	
	private Integer newPropPeopleCount;
	private Integer newPropPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double newPropAverageCount;
	
	private Integer usedPropPeopleCount;
	private Integer usedPropPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double usedPropAverageCount;
	
	private Integer fanliPeopleCount;
	private Integer fanliPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double fanliAverageCount;
	
	private Integer baodiPeopleCount;
	private Integer baodiPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double baodiAverageCount;
	
	private Integer jiajiangPeopleCount;
	private Integer jiajiangPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double jiajiangAverageCount;
	
	private Integer huodongPeopleCount;
	private Integer huodongPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double huodongAverageCount;
	
	private Integer reclaimPropPeopleCount;
	private Integer reclaimPropPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double reclaimPropAverageCount;
	
	private Integer guoqiDayPropCount;
	private Integer guoqiTotalPropCount;
	
	private Integer shengyuDayPropCount;
	private Integer shengyuTotalPropCount;
	
	private Integer newFragPeopleCount;
	private Integer newFragFragCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double newFragAverageCount;
	
	private Integer reclaimFragPeopleCount;
	private Integer reclaimFragCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double reclaimAverageFragCount;
	
	private Integer shengyuFragCount;
	
	@Override
	public void putCurrentDate(Date date) {
		setCurrentDate(date);
	}

	@Override
	public Date loadCurrentDate() {
		return getCurrentDate();
	}
	

	@Override
	public String toString() {
		return "PropAndFragMainData [currentDate=" + currentDate + ", newPropPeopleCount=" + newPropPeopleCount
				+ ", newPropPropCount=" + newPropPropCount + ", newPropAverageCount=" + newPropAverageCount
				+ ", usedPropPeopleCount=" + usedPropPeopleCount + ", usedPropPropCount=" + usedPropPropCount
				+ ", usedPropAverageCount=" + usedPropAverageCount + ", fanliPeopleCount=" + fanliPeopleCount
				+ ", fanliPropCount=" + fanliPropCount + ", fanliAverageCount=" + fanliAverageCount
				+ ", baodiPeopleCount=" + baodiPeopleCount + ", baodiPropCount=" + baodiPropCount
				+ ", baodiAverageCount=" + baodiAverageCount + ", jiajiangPeopleCount=" + jiajiangPeopleCount
				+ ", jiajiangPropCount=" + jiajiangPropCount + ", jiajiangAverageCount=" + jiajiangAverageCount
				+ ", huodongPeopleCount=" + huodongPeopleCount + ", huodongPropCount=" + huodongPropCount
				+ ", huodongAverageCount=" + huodongAverageCount + ", guoqiDayPropCount=" + guoqiDayPropCount
				+ ", guoqiTotalPropCount=" + guoqiTotalPropCount + ", shengyuDayPropCount=" + shengyuDayPropCount
				+ ", shengyuTotalPropCount=" + shengyuTotalPropCount + ", newFragPeopleCount=" + newFragPeopleCount
				+ ", newFragFragCount=" + newFragFragCount + ", newFragAverageCount=" + newFragAverageCount
				+ ", shengyuFragCount=" + shengyuFragCount + "]";
	}
	
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getNewPropPeopleCount() {
		return newPropPeopleCount;
	}

	public void setNewPropPeopleCount(Integer newPropPeopleCount) {
		this.newPropPeopleCount = Math.abs(newPropPeopleCount);
	}

	public Integer getNewPropPropCount() {
		return newPropPropCount;
	}

	public void setNewPropPropCount(Integer newPropPropCount) {
		this.newPropPropCount = Math.abs(newPropPropCount);
	}

	public Double getNewPropAverageCount() {
		return newPropAverageCount;
	}

	public void setNewPropAverageCount(Double newPropAverageCount) {
		this.newPropAverageCount = Math.abs(newPropAverageCount);
	}

	public Integer getUsedPropPeopleCount() {
		return usedPropPeopleCount;
	}

	public void setUsedPropPeopleCount(Integer usedPropPeopleCount) {
		this.usedPropPeopleCount = Math.abs(usedPropPeopleCount);
	}

	public Integer getUsedPropPropCount() {
		return usedPropPropCount;
	}

	public void setUsedPropPropCount(Integer usedPropPropCount) {
		this.usedPropPropCount = Math.abs(usedPropPropCount);
	}

	public Double getUsedPropAverageCount() {
		return usedPropAverageCount;
	}

	public void setUsedPropAverageCount(Double usedPropAverageCount) {
		this.usedPropAverageCount = Math.abs(usedPropAverageCount);
	}

	public Integer getFanliPeopleCount() {
		return fanliPeopleCount;
	}

	public void setFanliPeopleCount(Integer fanliPeopleCount) {
		this.fanliPeopleCount = Math.abs(fanliPeopleCount);
	}

	public Integer getFanliPropCount() {
		return fanliPropCount;
	}

	public void setFanliPropCount(Integer fanliPropCount) {
		this.fanliPropCount = Math.abs(fanliPropCount);
	}

	public Double getFanliAverageCount() {
		return fanliAverageCount;
	}

	public void setFanliAverageCount(Double fanliAverageCount) {
		this.fanliAverageCount = Math.abs(fanliAverageCount);
	}

	public Integer getBaodiPeopleCount() {
		return baodiPeopleCount;
	}

	public void setBaodiPeopleCount(Integer baodiPeopleCount) {
		this.baodiPeopleCount = Math.abs(baodiPeopleCount);
	}

	public Integer getBaodiPropCount() {
		return baodiPropCount;
	}

	public void setBaodiPropCount(Integer baodiPropCount) {
		this.baodiPropCount = Math.abs(baodiPropCount);
	}

	public Double getBaodiAverageCount() {
		return baodiAverageCount;
	}

	public void setBaodiAverageCount(Double baodiAverageCount) {
		this.baodiAverageCount = Math.abs(baodiAverageCount);
	}

	public Integer getJiajiangPeopleCount() {
		return jiajiangPeopleCount;
	}

	public void setJiajiangPeopleCount(Integer jiajiangPeopleCount) {
		this.jiajiangPeopleCount = Math.abs(jiajiangPeopleCount);
	}

	public Integer getJiajiangPropCount() {
		return jiajiangPropCount;
	}

	public void setJiajiangPropCount(Integer jiajiangPropCount) {
		this.jiajiangPropCount = Math.abs(jiajiangPropCount);
	}

	public Double getJiajiangAverageCount() {
		return jiajiangAverageCount;
	}

	public void setJiajiangAverageCount(Double jiajiangAverageCount) {
		this.jiajiangAverageCount = Math.abs(jiajiangAverageCount);
	}

	public Integer getHuodongPeopleCount() {
		return huodongPeopleCount;
	}

	public void setHuodongPeopleCount(Integer huodongPeopleCount) {
		this.huodongPeopleCount = Math.abs(huodongPeopleCount);
	}

	public Integer getHuodongPropCount() {
		return huodongPropCount;
	}

	public void setHuodongPropCount(Integer huodongPropCount) {
		this.huodongPropCount = Math.abs(huodongPropCount);
	}

	public Double getHuodongAverageCount() {
		return huodongAverageCount;
	}

	public void setHuodongAverageCount(Double huodongAverageCount) {
		this.huodongAverageCount = Math.abs(huodongAverageCount);
	}

	public Integer getGuoqiDayPropCount() {
		return guoqiDayPropCount;
	}

	public void setGuoqiDayPropCount(Integer guoqiDayPropCount) {
		this.guoqiDayPropCount = Math.abs(guoqiDayPropCount);
	}

	public Integer getGuoqiTotalPropCount() {
		return guoqiTotalPropCount;
	}

	public void setGuoqiTotalPropCount(Integer guoqiTotalPropCount) {
		this.guoqiTotalPropCount = Math.abs(guoqiTotalPropCount);
	}

	public Integer getShengyuDayPropCount() {
		return shengyuDayPropCount;
	}

	public void setShengyuDayPropCount(Integer shengyuDayPropCount) {
		this.shengyuDayPropCount = shengyuDayPropCount;
	}

	public Integer getShengyuTotalPropCount() {
		return shengyuTotalPropCount;
	}

	public void setShengyuTotalPropCount(Integer shengyuTotalPropCount) {
		this.shengyuTotalPropCount = Math.abs(shengyuTotalPropCount);
	}

	public Integer getNewFragPeopleCount() {
		return newFragPeopleCount;
	}

	public void setNewFragPeopleCount(Integer newFragPeopleCount) {
		this.newFragPeopleCount = Math.abs(newFragPeopleCount);
	}

	public Integer getNewFragFragCount() {
		return newFragFragCount;
	}

	public void setNewFragFragCount(Integer newFragFragCount) {
		this.newFragFragCount = Math.abs(newFragFragCount);
	}

	public Double getNewFragAverageCount() {
		return newFragAverageCount;
	}

	public void setNewFragAverageCount(Double newFragAverageCount) {
		this.newFragAverageCount = Math.abs(newFragAverageCount);
	}

	public Integer getShengyuFragCount() {
		return shengyuFragCount;
	}

	public void setShengyuFragCount(Integer shengyuFragCount) {
		this.shengyuFragCount = Math.abs(shengyuFragCount);
	}
	
	public Integer getReclaimPropPeopleCount() {
		return reclaimPropPeopleCount;
	}

	public void setReclaimPropPeopleCount(Integer reclaimPropPeopleCount) {
		this.reclaimPropPeopleCount = reclaimPropPeopleCount;
	}

	public Integer getReclaimPropPropCount() {
		return reclaimPropPropCount;
	}

	public void setReclaimPropPropCount(Integer reclaimPropPropCount) {
		this.reclaimPropPropCount = reclaimPropPropCount;
	}

	public Double getReclaimPropAverageCount() {
		return reclaimPropAverageCount;
	}

	public void setReclaimPropAverageCount(Double reclaimPropAverageCount) {
		this.reclaimPropAverageCount = reclaimPropAverageCount;
	}

	public Integer getReclaimFragPeopleCount() {
		return reclaimFragPeopleCount;
	}

	public void setReclaimFragPeopleCount(Integer reclaimFragPeopleCount) {
		this.reclaimFragPeopleCount = reclaimFragPeopleCount;
	}

	public Integer getReclaimFragCount() {
		return reclaimFragCount;
	}

	public void setReclaimFragCount(Integer reclaimFragCount) {
		this.reclaimFragCount = reclaimFragCount;
	}

	public Double getReclaimAverageFragCount() {
		return reclaimAverageFragCount;
	}

	public void setReclaimAverageFragCount(Double reclaimAverageFragCount) {
		this.reclaimAverageFragCount = reclaimAverageFragCount;
	}

	public void invokeNewFragAverageCount(){
		if(newFragPeopleCount==0){
			newFragAverageCount = newFragFragCount/1.0/1;
		}else{
			newFragAverageCount = newFragFragCount/1.0/newFragPeopleCount;
		}
	}
	public void invokeShengyuDayPropCount(){
		shengyuDayPropCount = newPropPropCount-usedPropPropCount-guoqiDayPropCount - reclaimPropPropCount;
	}
	public void invokeHuodongPropCount(){
		if(huodongPeopleCount==0){
			huodongAverageCount = huodongPropCount/1.0/1;
		}else{
			huodongAverageCount = huodongPropCount/1.0/huodongPeopleCount;
		}
	}
	public void invokeJiajiangPropCount(){
		if(jiajiangPeopleCount==0){
			jiajiangAverageCount = jiajiangPropCount/1.0/1;
		}else{
			jiajiangAverageCount = jiajiangPropCount/1.0/jiajiangPeopleCount;
		}
	}
	public void invokeBaodiPropCount(){
		if(baodiPeopleCount==0){
			baodiAverageCount = baodiPropCount/1.0/1;
		}else{
			baodiAverageCount = baodiPropCount/1.0/baodiPeopleCount;
		}
	}
	public void invokeFanliAverageCount(){
		if(fanliPeopleCount==0){
			fanliAverageCount = fanliPropCount/1.0/1;
		}else{
			fanliAverageCount = fanliPropCount/1.0/fanliPeopleCount;
		}
	}
	public void invokeUsedPropAverageCount(){
		if(usedPropPeopleCount==0){
			usedPropAverageCount = usedPropPropCount/1.0/1;
		}else{
			usedPropAverageCount = usedPropPropCount/1.0/usedPropPeopleCount;
		}
	}
	public void invokeNewPropAverageCount(){
		if(newPropPeopleCount==0){
			newPropAverageCount = newPropPropCount/1.0/1;
		}else{
			newPropAverageCount = newPropPropCount/1.0/newPropPeopleCount;
		}
	}
	public void invokeReclaimPropAverageCount(){
		if(reclaimPropPeopleCount==0){
			reclaimPropAverageCount = reclaimPropPropCount/1.0/1;
		}else{
			reclaimPropAverageCount = reclaimPropPropCount/1.0/reclaimPropPeopleCount;
		}
	}
	public void invokeReclaimAverageFragCount(){
		if(reclaimFragPeopleCount==0){
			reclaimAverageFragCount = reclaimFragCount/1.0/1;
		}else{
			reclaimAverageFragCount = reclaimFragCount/1.0/reclaimFragPeopleCount;
		}
	}
	
	
}
