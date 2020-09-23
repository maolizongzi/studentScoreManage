package com.cndym.entity.data.put;

import java.io.Serializable;
import java.util.Date;

public class AccountLog implements Serializable {
	private static final long serialVersionUID = 3529532806061025845L;
	private Long id;
	private String userCode;
	private String orderId;
	private String programsOrderId;
	private String autoOrderId;
	private Integer buyType;
	private String resources;
	private Integer eventType;
	private String type;
	private String eventCode;
	private Double bonusAmount;
	private Double rechargeAmount;
	private Double presentAmount;
	private Double freezeAmount;
	private Double bonusAmountNew;
	private Double rechargeAmountNew;
	private Double presentAmountNew;
	private Double freezeAmountNew;
	private Date createTime;
	private String memo;
	private String backup1;
	private String backup2;
	private String backup3;
	private String[] eventCodeArr;

	public AccountLog() {
	}

	public AccountLog(Long id, String userCode, String orderId,
			String programsOrderId, String autoOrderId, String resources,
			Integer eventType, String type, String eventCode,
			Double bonusAmount, Double rechargeAmount, Double presentAmount,
			Double freezeAmount, Double bonusAmountNew,
			Double rechargeAmountNew, Double presentAmountNew,
			Double freezeAmountNew, Date createTime, String memo,
			String backup1, String backup2, String backup3) {
		this.id = id;
		this.userCode = userCode;
		this.orderId = orderId;
		this.programsOrderId = programsOrderId;
		this.autoOrderId = autoOrderId;
		this.resources = resources;
		this.eventType = eventType;
		this.type = type;
		this.eventCode = eventCode;
		this.bonusAmount = bonusAmount;
		this.rechargeAmount = rechargeAmount;
		this.presentAmount = presentAmount;
		this.freezeAmount = freezeAmount;
		this.bonusAmountNew = bonusAmountNew;
		this.rechargeAmountNew = rechargeAmountNew;
		this.presentAmountNew = presentAmountNew;
		this.freezeAmountNew = freezeAmountNew;
		this.createTime = createTime;
		this.memo = memo;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getAutoOrderId() {
		return this.autoOrderId;
	}

	public void setAutoOrderId(String autoOrderId) {
		this.autoOrderId = autoOrderId;
	}

	public Integer getBuyType() {
		return this.buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public String getResources() {
		return this.resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public Integer getEventType() {
		return this.eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getEventCode() {
		return this.eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public Double getBonusAmount() {
		return this.bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Double getRechargeAmount() {
		return this.rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Double getPresentAmount() {
		return this.presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getFreezeAmount() {
		return this.freezeAmount;
	}

	public void setFreezeAmount(Double freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public Double getBonusAmountNew() {
		return this.bonusAmountNew;
	}

	public void setBonusAmountNew(Double bonusAmountNew) {
		this.bonusAmountNew = bonusAmountNew;
	}

	public Double getRechargeAmountNew() {
		return this.rechargeAmountNew;
	}

	public void setRechargeAmountNew(Double rechargeAmountNew) {
		this.rechargeAmountNew = rechargeAmountNew;
	}

	public Double getPresentAmountNew() {
		return this.presentAmountNew;
	}

	public void setPresentAmountNew(Double presentAmountNew) {
		this.presentAmountNew = presentAmountNew;
	}

	public Double getFreezeAmountNew() {
		return this.freezeAmountNew;
	}

	public void setFreezeAmountNew(Double freezeAmountNew) {
		this.freezeAmountNew = freezeAmountNew;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String[] getEventCodeArr() {
		return this.eventCodeArr;
	}

	public void setEventCodeArr(String[] eventCodeArr) {
		this.eventCodeArr = eventCodeArr;
	}

	public String getBackup1() {
		return this.backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return this.backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return this.backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}
}
