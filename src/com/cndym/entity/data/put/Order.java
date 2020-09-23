package com.cndym.entity.data.put;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	private static final long serialVersionUID = 1565374566573659302L;
	private Long id;
	private String partnersCode;
	private String userCode;
	private String presentedUserCode;
	private String userInfo;
	private String orderId;
	private String programsOrderId;
	private Integer orderStatus;
	private Integer orderType;
	private Double orderAmount;
	private Integer bonusStatus;
	private Double bonusAmount;
	private Date createTime;
	private Date acceptTime;
	private String platform;
	private Double fixBonusAmount;
	private String lotteryCode;
	private String playCode;
	private Date startTime;
	private Date endTime;
	private String issueNameStart;
	private String issueNameEnd;
	private String userName;
	private String programsUserCode;
	private String rguserName;

	public String getRguserName() {
		return this.rguserName;
	}

	public void setRguserName(String rguserName) {
		this.rguserName = rguserName;
	}

	public Order() {
	}

	public Order(Long id, String partnersCode, String userCode,
			String presentedUserCode, String userInfo, String orderId,
			String programsOrderId, Integer orderStatus, Integer orderType,
			Double orderAmount, Integer bonusStatus, Double bonusAmount,
			Date createTime, Date acceptTime, String platform,
			Double realBonusAmount) {
		this.id = id;
		this.partnersCode = partnersCode;
		this.userCode = userCode;
		this.presentedUserCode = presentedUserCode;
		this.userInfo = userInfo;
		this.orderId = orderId;
		this.programsOrderId = programsOrderId;
		this.orderStatus = orderStatus;
		this.orderType = orderType;
		this.orderAmount = orderAmount;
		this.bonusStatus = bonusStatus;
		this.bonusAmount = bonusAmount;
		this.createTime = createTime;
		this.acceptTime = acceptTime;
		this.platform = platform;
		this.fixBonusAmount = realBonusAmount;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartnersCode() {
		return this.partnersCode;
	}

	public void setPartnersCode(String partnersCode) {
		this.partnersCode = partnersCode;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPresentedUserCode() {
		return this.presentedUserCode;
	}

	public void setPresentedUserCode(String presentedUserCode) {
		this.presentedUserCode = presentedUserCode;
	}

	public String getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProgramsOrderId() {
		return this.programsOrderId;
	}

	public void setProgramsOrderId(String programsOrderId) {
		this.programsOrderId = programsOrderId;
	}

	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Double getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getBonusStatus() {
		return this.bonusStatus;
	}

	public void setBonusStatus(Integer bonusStatus) {
		this.bonusStatus = bonusStatus;
	}

	public Double getBonusAmount() {
		return this.bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getAcceptTime() {
		return this.acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getLotteryCode() {
		return this.lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIssueNameStart() {
		return this.issueNameStart;
	}

	public void setIssueNameStart(String issueNameStart) {
		this.issueNameStart = issueNameStart;
	}

	public String getIssueNameEnd() {
		return this.issueNameEnd;
	}

	public void setIssueNameEnd(String issueNameEnd) {
		this.issueNameEnd = issueNameEnd;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProgramsUserCode() {
		return this.programsUserCode;
	}

	public void setProgramsUserCode(String programsUserCode) {
		this.programsUserCode = programsUserCode;
	}

	public Double getFixBonusAmount() {
		return this.fixBonusAmount;
	}

	public void setFixBonusAmount(Double fixBonusAmount) {
		this.fixBonusAmount = fixBonusAmount;
	}

	public String getPlayCode() {
		return this.playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}
}
