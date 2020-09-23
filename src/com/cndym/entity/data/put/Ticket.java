package com.cndym.entity.data.put;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
	private static final long serialVersionUID = -5277193494280077954L;
	private Long id;
	private String partnersCode;
	private String userCode;
	private String programsOrderId;
	private String subProgramsOrderId;
	private String orderId;
	private String ticketId;
	private String lotteryCode;
	private String playCode;
	private String pollCode;
	private String issue;
	private Integer item;
	private Integer multiple;
	private String numberInfo;
	private String userInfo;
	private String sequence;
	private String saleCode;
	private String errCode;
	private String errMsg;
	private String postCode;
	private Double amount;
	private Integer ticketStatus;
	private Integer bonusStatus;
	private Double bonusAmount;
	private Date createTime;
	private Date acceptTime;
	private String platform;
	private String bonusClass;
	private Double fixBonusAmount;
	private String startTime;
	private String endTime;
	private String acceptStartTime;
	private String acceptEndTime;
	private String issueNameStart;
	private String issueNameEnd;

	public Ticket() {
	}

	public Ticket(String ticketId) {
		this.ticketId = ticketId;
	}

	public Ticket(String ticketId, String programsOrderId,
			String subProgramsOrderId, Double bonusAmount, String bonusClass,
			Double fixBonusAmount) {
		this.ticketId = ticketId;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = subProgramsOrderId;
		this.bonusAmount = bonusAmount;
		this.bonusClass = bonusClass;
		this.fixBonusAmount = fixBonusAmount;
	}

	public Ticket(String ticketId, Double bonusAmount, String bonusClass,
			Double fixBonusAmount) {
		this.ticketId = ticketId;
		this.bonusAmount = bonusAmount;
		this.bonusClass = bonusClass;
		this.fixBonusAmount = fixBonusAmount;
	}

	public Ticket(Long id, String partnersCode, String userCode,
			String programsOrderId, String subProgramsOrderId, String orderId,
			String ticketId, String lotteryCode, String playCode,
			String pollCode, String issue, Integer item, Integer multiple,
			String numberInfo, String userInfo, String sequence,
			String saleCode, String errCode, String errMsg, String postCode,
			Double amount, Integer ticketStatus, Integer bonusStatus,
			Double bonusAmount, Date createTime, Date acceptTime,
			String platform, String bonusClass, Double fixBonusAmount) {
		this.id = id;
		this.partnersCode = partnersCode;
		this.userCode = userCode;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = programsOrderId;
		this.orderId = orderId;
		this.ticketId = ticketId;
		this.lotteryCode = lotteryCode;
		this.playCode = playCode;
		this.pollCode = pollCode;
		this.issue = issue;
		this.item = item;
		this.multiple = multiple;
		this.numberInfo = numberInfo;
		this.userInfo = userInfo;
		this.sequence = sequence;
		this.saleCode = saleCode;
		this.errCode = errCode;
		this.errMsg = errMsg;
		this.postCode = postCode;
		this.amount = amount;
		this.ticketStatus = ticketStatus;
		this.bonusStatus = bonusStatus;
		this.bonusAmount = bonusAmount;
		this.createTime = createTime;
		this.acceptTime = acceptTime;
		this.platform = platform;
		this.bonusClass = bonusClass;
		this.fixBonusAmount = fixBonusAmount;
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

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getProgramsOrderId() {
		return this.programsOrderId;
	}

	public void setProgramsOrderId(String programsOrderId) {
		this.programsOrderId = programsOrderId;
	}

	public String getSubProgramsOrderId() {
		return this.subProgramsOrderId;
	}

	public void setSubProgramsOrderId(String subProgramsOrderId) {
		this.subProgramsOrderId = subProgramsOrderId;
	}

	public String getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getLotteryCode() {
		return this.lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public String getPlayCode() {
		return this.playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}

	public String getPollCode() {
		return this.pollCode;
	}

	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}

	public String getIssue() {
		return this.issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Integer getItem() {
		return this.item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Integer getMultiple() {
		return this.multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	public String getNumberInfo() {
		return this.numberInfo;
	}

	public void setNumberInfo(String numberInfo) {
		this.numberInfo = numberInfo;
	}

	public String getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSaleCode() {
		return this.saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTicketStatus() {
		return this.ticketStatus;
	}

	public void setTicketStatus(Integer ticketStatus) {
		this.ticketStatus = ticketStatus;
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

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
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

	public String getAcceptStartTime() {
		return this.acceptStartTime;
	}

	public void setAcceptStartTime(String acceptStartTime) {
		this.acceptStartTime = acceptStartTime;
	}

	public String getAcceptEndTime() {
		return this.acceptEndTime;
	}

	public void setAcceptEndTime(String acceptEndTime) {
		this.acceptEndTime = acceptEndTime;
	}

	public String getBonusClass() {
		return this.bonusClass;
	}

	public void setBonusClass(String bonusClass) {
		this.bonusClass = bonusClass;
	}

	public Double getFixBonusAmount() {
		return this.fixBonusAmount;
	}

	public void setFixBonusAmount(Double fixBonusAmount) {
		this.fixBonusAmount = fixBonusAmount;
	}
}
