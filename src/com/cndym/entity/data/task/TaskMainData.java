package com.cndym.entity.data.task;

import java.util.Date;

import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;

public class TaskMainData extends DataDetail {

	@ExportDataConfig(formats={@Format("yyyy-MM-dd")})
	private Date currentDate;
	
	private Integer taskPeopleCount;
	private Integer taskTaskCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double taskAverageTaskCount;
	private Integer taskScore;
	private Integer taskRecoverPeopleCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double taskAverageScore;
	private Integer taskRecoverTaskCount;
	
	private Integer dayTaskPeopleCount;
	private Integer dayTaskTaskCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double dayTaskAverageTaskCount;
	private Integer dayTaskScore;
	private Integer dayTaskRecoverPeopleCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double dayTaskAverageScore;
	private Integer dayTaskRecoverTaskCount;
	
	private Integer achieveTaskPeopleCount;
	private Integer achieveTaskTaskCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double achieveTaskAverageTaskCount;
	private Integer achieveTaskScore;
	private Integer achieveTaskRecoverPeopleCount;
	@ExportDataConfig(formats={@Format("%.2f")})
	private Double achieveTaskAverageScore;
	private Integer achieveTaskRecoverTaskCount;
	
	private Integer catchTitlePeopleCount;
	private Integer catchTitleTitleCount;
	
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
		return "TaskMainData [currentDate=" + currentDate + ", taskPeopleCount=" + taskPeopleCount + ", taskTaskCount="
				+ taskTaskCount + ", taskAverageTaskCount=" + taskAverageTaskCount + ", taskScore=" + taskScore
				+ ", taskRecoverPeopleCount=" + taskRecoverPeopleCount + ", taskAverageScore=" + taskAverageScore
				+ ", dayTaskPeopleCount=" + dayTaskPeopleCount + ", dayTaskTaskCount=" + dayTaskTaskCount
				+ ", dayTaskAverageTaskCount=" + dayTaskAverageTaskCount + ", dayTaskScore=" + dayTaskScore
				+ ", dayTaskRecoverPeopleCount=" + dayTaskRecoverPeopleCount + ", dayTaskAverageScore="
				+ dayTaskAverageScore + ", achieveTaskPeopleCount=" + achieveTaskPeopleCount + ", achieveTaskTaskCount="
				+ achieveTaskTaskCount + ", achieveTaskAverageTaskCount=" + achieveTaskAverageTaskCount
				+ ", achieveTaskScore=" + achieveTaskScore + ", achieveTaskRecoverPeopleCount="
				+ achieveTaskRecoverPeopleCount + ", achieveTaskAverageScore=" + achieveTaskAverageScore + "]";
	}


	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getTaskPeopleCount() {
		return taskPeopleCount;
	}

	public void setTaskPeopleCount(Integer taskPeopleCount) {
		this.taskPeopleCount = taskPeopleCount;
	}

	public Integer getTaskTaskCount() {
		return taskTaskCount;
	}

	public void setTaskTaskCount(Integer taskTaskCount) {
		this.taskTaskCount = taskTaskCount;
	}

	public Double getTaskAverageTaskCount() {
		return taskAverageTaskCount;
	}

	public void setTaskAverageTaskCount(Double taskAverageTaskCount) {
		this.taskAverageTaskCount = taskAverageTaskCount;
	}

	public Integer getTaskScore() {
		return taskScore;
	}

	public void setTaskScore(Integer taskScore) {
		this.taskScore = taskScore;
	}

	public Integer getTaskRecoverPeopleCount() {
		return taskRecoverPeopleCount;
	}

	public void setTaskRecoverPeopleCount(Integer taskRecoverPeopleCount) {
		this.taskRecoverPeopleCount = taskRecoverPeopleCount;
	}

	public Double getTaskAverageScore() {
		return taskAverageScore;
	}

	public void setTaskAverageScore(Double taskAverageScore) {
		this.taskAverageScore = taskAverageScore;
	}

	public Integer getDayTaskPeopleCount() {
		return dayTaskPeopleCount;
	}

	public void setDayTaskPeopleCount(Integer dayTaskPeopleCount) {
		this.dayTaskPeopleCount = dayTaskPeopleCount;
	}

	public Integer getDayTaskTaskCount() {
		return dayTaskTaskCount;
	}

	public void setDayTaskTaskCount(Integer dayTaskTaskCount) {
		this.dayTaskTaskCount = dayTaskTaskCount;
	}

	public Double getDayTaskAverageTaskCount() {
		return dayTaskAverageTaskCount;
	}

	public void setDayTaskAverageTaskCount(Double dayTaskAverageTaskCount) {
		this.dayTaskAverageTaskCount = dayTaskAverageTaskCount;
	}

	public Integer getDayTaskScore() {
		return dayTaskScore;
	}

	public void setDayTaskScore(Integer dayTaskScore) {
		this.dayTaskScore = dayTaskScore;
	}

	public Integer getDayTaskRecoverPeopleCount() {
		return dayTaskRecoverPeopleCount;
	}

	public void setDayTaskRecoverPeopleCount(Integer dayTaskRecoverPeopleCount) {
		this.dayTaskRecoverPeopleCount = dayTaskRecoverPeopleCount;
	}

	public Double getDayTaskAverageScore() {
		return dayTaskAverageScore;
	}

	public void setDayTaskAverageScore(Double dayTaskAverageScore) {
		this.dayTaskAverageScore = dayTaskAverageScore;
	}

	public Integer getAchieveTaskPeopleCount() {
		return achieveTaskPeopleCount;
	}

	public void setAchieveTaskPeopleCount(Integer achieveTaskPeopleCount) {
		this.achieveTaskPeopleCount = achieveTaskPeopleCount;
	}

	public Integer getAchieveTaskTaskCount() {
		return achieveTaskTaskCount;
	}

	public void setAchieveTaskTaskCount(Integer achieveTaskTaskCount) {
		this.achieveTaskTaskCount = achieveTaskTaskCount;
	}

	public Double getAchieveTaskAverageTaskCount() {
		return achieveTaskAverageTaskCount;
	}

	public void setAchieveTaskAverageTaskCount(Double achieveTaskAverageTaskCount) {
		this.achieveTaskAverageTaskCount = achieveTaskAverageTaskCount;
	}

	public Integer getAchieveTaskScore() {
		return achieveTaskScore;
	}

	public void setAchieveTaskScore(Integer achieveTaskScore) {
		this.achieveTaskScore = achieveTaskScore;
	}

	public Integer getAchieveTaskRecoverPeopleCount() {
		return achieveTaskRecoverPeopleCount;
	}

	public void setAchieveTaskRecoverPeopleCount(Integer achieveTaskRecoverPeopleCount) {
		this.achieveTaskRecoverPeopleCount = achieveTaskRecoverPeopleCount;
	}

	public Double getAchieveTaskAverageScore() {
		return achieveTaskAverageScore;
	}

	public void setAchieveTaskAverageScore(Double achieveTaskAverageScore) {
		this.achieveTaskAverageScore = achieveTaskAverageScore;
	}
	

	public Integer getCatchTitlePeopleCount() {
		return catchTitlePeopleCount;
	}

	public void setCatchTitlePeopleCount(Integer catchTitlePeopleCount) {
		this.catchTitlePeopleCount = catchTitlePeopleCount;
	}

	public Integer getCatchTitleTitleCount() {
		return catchTitleTitleCount;
	}

	public void setCatchTitleTitleCount(Integer catchTitleTitleCount) {
		this.catchTitleTitleCount = catchTitleTitleCount;
	}
	public Integer getTaskRecoverTaskCount() {
		return taskRecoverTaskCount;
	}

	public void setTaskRecoverTaskCount(Integer taskRecoverTaskCount) {
		this.taskRecoverTaskCount = taskRecoverTaskCount;
	}
	public Integer getDayTaskRecoverTaskCount() {
		return dayTaskRecoverTaskCount;
	}
	public void setDayTaskRecoverTaskCount(Integer dayTaskRecoverTaskCount) {
		this.dayTaskRecoverTaskCount = dayTaskRecoverTaskCount;
	}
	public Integer getAchieveTaskRecoverTaskCount() {
		return achieveTaskRecoverTaskCount;
	}
	public void setAchieveTaskRecoverTaskCount(Integer achieveTaskRecoverTaskCount) {
		this.achieveTaskRecoverTaskCount = achieveTaskRecoverTaskCount;
	}

	public void invokeTaskAverageTaskCount(){
		if(taskPeopleCount==0){
			taskAverageTaskCount = taskTaskCount/1.0/1;
		}else{
			taskAverageTaskCount = taskTaskCount/1.0/taskPeopleCount;
		}
	}
	public void invokeTaskAverageScore(){
		if(taskRecoverPeopleCount==0){
			taskAverageScore = taskScore/1.0/1;
		}else{
			taskAverageScore = taskScore/1.0/taskRecoverPeopleCount;
		}
	}
	public void invokeDayTaskAverageTaskCount(){
		if(dayTaskPeopleCount==0){
			dayTaskAverageTaskCount = dayTaskTaskCount/1.0/1;
		}else{
			dayTaskAverageTaskCount = dayTaskTaskCount/1.0/dayTaskPeopleCount;
		}
	}
	public void invokeDayTaskAverageScore(){
		if(dayTaskRecoverPeopleCount==0){
			dayTaskAverageScore = dayTaskScore/1.0/1;
		}else{
			dayTaskAverageScore = dayTaskScore/1.0/dayTaskRecoverPeopleCount;
		}
	}
	public void invokeAchieveTaskAverageTaskCount(){
		if(achieveTaskPeopleCount==0){
			achieveTaskAverageTaskCount = achieveTaskTaskCount/1.0/1;
		}else{
			achieveTaskAverageTaskCount = achieveTaskTaskCount/1.0/achieveTaskPeopleCount;
		}
	}
	public void invokeAchieveTaskAverageScore(){
		if(achieveTaskRecoverPeopleCount==0){
			achieveTaskAverageScore = achieveTaskScore/1.0/1;
		}else{
			achieveTaskAverageScore = achieveTaskScore/1.0/achieveTaskRecoverPeopleCount;
		}
	}
	
}
