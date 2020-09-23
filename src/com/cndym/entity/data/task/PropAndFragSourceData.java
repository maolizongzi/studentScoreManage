package com.cndym.entity.data.task;

import java.util.Date;

import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;

public class PropAndFragSourceData extends DataDetail {

	@ExportDataConfig(formats={@Format("yyyy-MM-dd")})
	private Date currentDate;
	
	private Integer buyPropPeopleCount;
	private Integer buyPropPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double buyPropAverageCount;
	
	private Integer drawPropPeopleCount;
	private Integer drawPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double drawPropAverageCount;
	private Integer drawFragPeopleCount;
	private Integer drawFragCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double drawFragAverageCount;
	
	private Integer hechengPeopleCount;
	private Integer hechengPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double hechengAverageCount;
	
	private Integer jiangbutingPeopleCont;
	private Integer jiangbutingPropCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double jiangbutingAverageCount;
	
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
		return "PropAndFragSourceData [currentDate=" + currentDate + ", buyPropPeopleCount=" + buyPropPeopleCount
				+ ", buyPropPropCount=" + buyPropPropCount + ", buyPropAverageCount=" + buyPropAverageCount
				+ ", drawPropPeopleCount=" + drawPropPeopleCount + ", drawPropCount=" + drawPropCount
				+ ", drawPropAverageCount=" + drawPropAverageCount + ", drawFragPeopleCount=" + drawFragPeopleCount
				+ ", drawFragCount=" + drawFragCount + ", drawFragAverageCount=" + drawFragAverageCount
				+ ", hechengPeopleCount=" + hechengPeopleCount + ", hechengPropCount=" + hechengPropCount
				+ ", hechengAverageCount=" + hechengAverageCount + ", jiangbutingPeopleCont=" + jiangbutingPeopleCont
				+ ", jiangbutingPropCount=" + jiangbutingPropCount + ", jiangbutingAverageCount="
				+ jiangbutingAverageCount + "]";
	}
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getBuyPropPeopleCount() {
		return buyPropPeopleCount;
	}

	public void setBuyPropPeopleCount(Integer buyPropPeopleCount) {
		this.buyPropPeopleCount = Math.abs(buyPropPeopleCount);
	}

	public Integer getBuyPropPropCount() {
		return buyPropPropCount;
	}

	public void setBuyPropPropCount(Integer buyPropPropCount) {
		this.buyPropPropCount = Math.abs(buyPropPropCount);
	}

	public Double getBuyPropAverageCount() {
		return buyPropAverageCount;
	}

	public void setBuyPropAverageCount(Double buyPropAverageCount) {
		this.buyPropAverageCount = Math.abs(buyPropAverageCount);
	}

	public Integer getDrawPropPeopleCount() {
		return drawPropPeopleCount;
	}

	public void setDrawPropPeopleCount(Integer drawPropPeopleCount) {
		this.drawPropPeopleCount = Math.abs(drawPropPeopleCount);
	}

	public Integer getDrawPropCount() {
		return drawPropCount;
	}

	public void setDrawPropCount(Integer drawPropCount) {
		this.drawPropCount = Math.abs(drawPropCount);
	}

	public Double getDrawPropAverageCount() {
		return drawPropAverageCount;
	}

	public void setDrawPropAverageCount(Double drawPropAverageCount) {
		this.drawPropAverageCount = Math.abs(drawPropAverageCount);
	}

	public Integer getDrawFragPeopleCount() {
		return drawFragPeopleCount;
	}

	public void setDrawFragPeopleCount(Integer drawFragPeopleCount) {
		this.drawFragPeopleCount = Math.abs(drawFragPeopleCount);
	}

	public Integer getDrawFragCount() {
		return drawFragCount;
	}

	public void setDrawFragCount(Integer drawFragCount) {
		this.drawFragCount = Math.abs(drawFragCount);
	}

	public Double getDrawFragAverageCount() {
		return drawFragAverageCount;
	}

	public void setDrawFragAverageCount(Double drawFragAverageCount) {
		this.drawFragAverageCount = Math.abs(drawFragAverageCount);
	}

	public Integer getHechengPeopleCount() {
		return hechengPeopleCount;
	}

	public void setHechengPeopleCount(Integer hechengPeopleCount) {
		this.hechengPeopleCount = Math.abs(hechengPeopleCount);
	}

	public Integer getHechengPropCount() {
		return hechengPropCount;
	}

	public void setHechengPropCount(Integer hechengPropCount) {
		this.hechengPropCount = Math.abs(hechengPropCount);
	}

	public Double getHechengAverageCount() {
		return hechengAverageCount;
	}

	public void setHechengAverageCount(Double hechengAverageCount) {
		this.hechengAverageCount = Math.abs(hechengAverageCount);
	}

	public Integer getJiangbutingPeopleCont() {
		return jiangbutingPeopleCont;
	}

	public void setJiangbutingPeopleCont(Integer jiangbutingPeopleCont) {
		this.jiangbutingPeopleCont = Math.abs(jiangbutingPeopleCont);
	}

	public Integer getJiangbutingPropCount() {
		return jiangbutingPropCount;
	}

	public void setJiangbutingPropCount(Integer jiangbutingPropCount) {
		this.jiangbutingPropCount = Math.abs(jiangbutingPropCount);
	}

	public Double getJiangbutingAverageCount() {
		return jiangbutingAverageCount;
	}

	public void setJiangbutingAverageCount(Double jiangbutingAverageCount) {
		this.jiangbutingAverageCount = Math.abs(jiangbutingAverageCount);
	}

	public void invokeBuyPropAverageCount(){
		if(buyPropPeopleCount==0){
			buyPropAverageCount = buyPropPropCount/1.0/1;
		}else{
			buyPropAverageCount = buyPropPropCount/1.0/buyPropPeopleCount;
		}
	}
	public void invokeDrawPropAverageCount(){
		if(drawPropPeopleCount==0){
			drawPropAverageCount = drawPropCount/1.0/1;
		}else{
			drawPropAverageCount = drawPropCount/1.0/drawPropPeopleCount;
		}
	}
	public void invokeDrawFragAverageCount(){
		if(drawFragPeopleCount==0){
			drawFragAverageCount = drawFragCount/1.0/1;
		}else{
			drawFragAverageCount = drawFragCount/1.0/drawFragPeopleCount;
		}
	}
	public void invokeHechengAverageCount(){
		if(hechengPeopleCount==0){
			hechengAverageCount = hechengPropCount/1.0/1;
		}else{
			hechengAverageCount = hechengPropCount/1.0/hechengPeopleCount;
		}
	}
	public void invokeJiangbutingAverageCount(){
		if(jiangbutingPeopleCont==0){
			jiangbutingAverageCount = jiangbutingPropCount/1.0/1;
		}else{
			jiangbutingAverageCount = jiangbutingPropCount/1.0/jiangbutingPeopleCont;
		}
	}

}
