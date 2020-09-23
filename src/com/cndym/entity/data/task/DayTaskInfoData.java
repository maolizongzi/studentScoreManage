package com.cndym.entity.data.task;

import java.util.Date;

import com.cndym.entity.data.task.annotation.ExportDataConfig;
import com.cndym.entity.data.task.annotation.Format;

public class DayTaskInfoData extends DataDetail {

	@ExportDataConfig(formats={@Format("yyyy-MM-dd")})
	private Date currentDate;
	
	private Integer login;
	private Integer shareBetting1;
	private Integer recharge10;
	private Integer recharge20;
	private Integer recharge50;
	private Integer recharge100;
	private Integer recharge200;
	private Integer recharge500;
	private Integer betting10;
	private Integer betting30;
	private Integer betting50;
	private Integer betting100;
	private Integer betting300;
	private Integer betting500;
	private Integer betting1000;
	private Integer bettingTimes1;
	private Integer bettingTimes5;
	private Integer bettingTimes10;
	
	
	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer getLogin() {
		return login;
	}

	public void setLogin(Integer login) {
		this.login = Math.abs(login);
	}

	public Integer getShareBetting1() {
		return shareBetting1;
	}

	public void setShareBetting1(Integer shareBetting1) {
		this.shareBetting1 = Math.abs(shareBetting1);
	}

	public Integer getRecharge10() {
		return recharge10;
	}

	public void setRecharge10(Integer recharge10) {
		this.recharge10 = Math.abs(recharge10);
	}

	public Integer getRecharge20() {
		return recharge20;
	}

	public void setRecharge20(Integer recharge20) {
		this.recharge20 = Math.abs(recharge20);
	}

	public Integer getRecharge50() {
		return recharge50;
	}

	public void setRecharge50(Integer recharge50) {
		this.recharge50 = Math.abs(recharge50);
	}

	public Integer getRecharge100() {
		return recharge100;
	}

	public void setRecharge100(Integer recharge100) {
		this.recharge100 = Math.abs(recharge100);
	}

	public Integer getRecharge200() {
		return recharge200;
	}

	public void setRecharge200(Integer recharge200) {
		this.recharge200 = Math.abs(recharge200);
	}

	public Integer getRecharge500() {
		return recharge500;
	}

	public void setRecharge500(Integer recharge500) {
		this.recharge500 = Math.abs(recharge500);
	}

	public Integer getBetting10() {
		return betting10;
	}

	public void setBetting10(Integer betting10) {
		this.betting10 = Math.abs(betting10);
	}

	public Integer getBetting30() {
		return betting30;
	}

	public void setBetting30(Integer betting30) {
		this.betting30 = Math.abs(betting30);
	}

	public Integer getBetting50() {
		return betting50;
	}

	public void setBetting50(Integer betting50) {
		this.betting50 = Math.abs(betting50);
	}

	public Integer getBetting100() {
		return betting100;
	}

	public void setBetting100(Integer betting100) {
		this.betting100 = Math.abs(betting100);
	}

	public Integer getBetting300() {
		return betting300;
	}

	public void setBetting300(Integer betting300) {
		this.betting300 = Math.abs(betting300);
	}

	public Integer getBetting500() {
		return betting500;
	}

	public void setBetting500(Integer betting500) {
		this.betting500 = Math.abs(betting500);
	}

	public Integer getBetting1000() {
		return betting1000;
	}

	public void setBetting1000(Integer betting1000) {
		this.betting1000 = Math.abs(betting1000);
	}

	public Integer getBettingTimes1() {
		return bettingTimes1;
	}

	public void setBettingTimes1(Integer bettingTimes1) {
		this.bettingTimes1 = Math.abs(bettingTimes1);
	}

	public Integer getBettingTimes5() {
		return bettingTimes5;
	}

	public void setBettingTimes5(Integer bettingTimes5) {
		this.bettingTimes5 = Math.abs(bettingTimes5);
	}

	public Integer getBettingTimes10() {
		return bettingTimes10;
	}

	public void setBettingTimes10(Integer bettingTimes10) {
		this.bettingTimes10 = Math.abs(bettingTimes10);
	}

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
		return "DayTaskInfoData [currentDate=" + currentDate + ", login=" + login + ", shareBetting1=" + shareBetting1
				+ ", recharge10=" + recharge10 + ", recharge20=" + recharge20 + ", recharge50=" + recharge50
				+ ", recharge100=" + recharge100 + ", recharge200=" + recharge200 + ", recharge500=" + recharge500
				+ ", betting10=" + betting10 + ", betting30=" + betting30 + ", betting50=" + betting50 + ", betting100="
				+ betting100 + ", betting300=" + betting300 + ", betting500=" + betting500 + ", betting1000="
				+ betting1000 + ", bettingTimes1=" + bettingTimes1 + ", bettingTimes5=" + bettingTimes5
				+ ", bettingTimes10=" + bettingTimes10 + "]";
	}


	
}
