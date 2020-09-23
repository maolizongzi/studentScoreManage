package com.cndym.entity.data.pick;

import java.util.Date;

/**
 * 注册用户表登录/投注 转化率中间表----注册渠道
 * 
 * @author Administrator
 * 
 */
public class UserRegistrationConversionRate {
	private Long id;
	/**登录保留率的状态(1:未结束  2：结束)*/
	private Integer loginStatus;
	/**投注保留率的状态(1:未结束  2：结束)*/
	private Integer bettingStatus;
	/**注册用户/登录用户(1:注册 2：登录)*/
	private Integer isLoginFlag;
	/**注册时间*/
	private Date registrationTime;
	/**30天之后的时间*/
	private Date endTime;
	/**创建时间*/
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Integer getBettingStatus() {
		return bettingStatus;
	}
	public void setBettingStatus(Integer bettingStatus) {
		this.bettingStatus = bettingStatus;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getIsLoginFlag() {
		return isLoginFlag;
	}
	public void setIsLoginFlag(Integer isLoginFlag) {
		this.isLoginFlag = isLoginFlag;
	}
}
