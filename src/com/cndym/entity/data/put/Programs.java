package com.cndym.entity.data.put;

import java.io.Serializable;
import java.util.Date;

public class Programs implements Serializable {
	private static final long serialVersionUID = 6783972831789411338L;
	private Long id;
	private String partnersCode;
	private String programsOrderId;
	private String autoOrderId;
	private String outOrderId;
	private String orderId;
	private String userCode;
	private String presentedUserCode;
	private String issue;
	private String lotteryCode;
	private String playCode;
	private String pollCode;
	private String title;
	private Integer buyType;
	private String numberInfo;
	private String userInfo;
	private Integer orderStatus;
	private Integer sendStatus;
	private Integer bonusStatus;
	private Integer privacy;
	private Integer item;
	private Integer multiple;
	private Integer totalWere;
	private Integer buyWere;
	private Integer minWere;
	private Integer lastWere;
	private Integer commission;
	private String description;
	private Double orderAmount;
	private Double fixBonusAmount;
	private Double bonusAmount;
	private Integer ticketCount;
	private Integer bonusTicket;
	private Integer successTicket;
	private Integer failureTicket;
	private Date createTime;
	private Date acceptTime;
	private Integer isTop;
	private String platform;
	private Integer bonusToAccount;
	private Integer bigBonus;
	private Integer exception;
	private String backup1;
	private String backup2;
	private String backup3;
	private Integer isUpload;
	private String filePath;
	private String gameStartId;
	private Date gameStartTime;
	private String gameEndId;
	private Date gameEndTime;
	private Date returnTime;
	private Date sendTime;
	private Date bonusTime;
	private String postCode;
	private Integer renGouCount;
	private Date uploadTime;
	private String softVer;
	private Integer[] orderStatusArr;
	private Integer bonusType;
	private String issueNameStart;
	private String issueNameEnd;
	private Double orderAmountStart;
	private Double orderAmountEnd;

	public Programs() {
	}

	public Programs(Long id, String partnersCode, String programsOrderId,
			String autoOrderId, String outOrderId, String userCode,
			String presentedUserCode, String issue, String lotteryCode,
			String playCode, String pollCode, String title, Integer buyType,
			String numberInfo, String userInfo, Integer orderStatus,
			Integer sendStatus, Integer bonusStatus, Integer privacy,
			Integer item, Integer multiple, Integer totalWere, Integer buyWere,
			Integer minWere, Integer lastWere, Integer commission,
			String description, Double orderAmount, Double fixBonusAmount,
			Double bonusAmount, Integer ticketCount, Integer bonusTicket,
			Integer successTicket, Integer failureTicket, Date createTime,
			Date acceptTime, Integer top, String platform,
			Integer bonusToAccount, Integer bigBonus, Integer exception,
			String backup1, String backup2, String backup3, Integer upload,
			String filePath, String gameStartId, Date gameStartTime,
			String gameEndId, Date gameEndTime, Date returnTime, Date sendTime,
			Date bonusTime, String postCode, Integer renGouCount,
			Date uploadTime, String orderId, String softVer) {
		this.id = id;
		this.partnersCode = partnersCode;
		this.programsOrderId = programsOrderId;
		this.autoOrderId = autoOrderId;
		this.outOrderId = outOrderId;
		this.userCode = userCode;
		this.presentedUserCode = presentedUserCode;
		this.issue = issue;
		this.lotteryCode = lotteryCode;
		this.playCode = playCode;
		this.pollCode = pollCode;
		this.title = title;
		this.buyType = buyType;
		this.numberInfo = numberInfo;
		this.userInfo = userInfo;
		this.orderStatus = orderStatus;
		this.sendStatus = sendStatus;
		this.bonusStatus = bonusStatus;
		this.privacy = privacy;
		this.item = item;
		this.multiple = multiple;
		this.totalWere = totalWere;
		this.buyWere = buyWere;
		this.minWere = minWere;
		this.lastWere = lastWere;
		this.commission = commission;
		this.description = description;
		this.orderAmount = orderAmount;
		this.fixBonusAmount = fixBonusAmount;
		this.bonusAmount = bonusAmount;
		this.ticketCount = ticketCount;
		this.bonusTicket = bonusTicket;
		this.successTicket = successTicket;
		this.failureTicket = failureTicket;
		this.createTime = createTime;
		this.acceptTime = acceptTime;
		this.isTop = top;
		this.platform = platform;
		this.bonusToAccount = bonusToAccount;
		this.bigBonus = bigBonus;
		this.exception = exception;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
		this.isUpload = upload;
		this.filePath = filePath;
		this.gameStartId = gameStartId;
		this.gameStartTime = gameStartTime;
		this.gameEndId = gameEndId;
		this.gameEndTime = gameEndTime;
		this.returnTime = returnTime;
		this.sendTime = sendTime;
		this.bonusTime = bonusTime;
		this.postCode = postCode;
		this.renGouCount = renGouCount;
		this.uploadTime = uploadTime;
		this.orderId = orderId;
		this.softVer = softVer;
	}

	public Double getOrderAmountStart() {
		return this.orderAmountStart;
	}

	public void setOrderAmountStart(Double orderAmountStart) {
		this.orderAmountStart = orderAmountStart;
	}

	public Double getOrderAmountEnd() {
		return this.orderAmountEnd;
	}

	public void setOrderAmountEnd(Double orderAmountEnd) {
		this.orderAmountEnd = orderAmountEnd;
	}

	public Integer getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Programs(String programsOrderId, String lotteryCode) {
		this.programsOrderId = programsOrderId;
		this.lotteryCode = lotteryCode;
	}

	public Programs(String lotteryCode, String issue, String programsOrderId) {
		this.lotteryCode = lotteryCode;
		this.issue = issue;
		this.programsOrderId = programsOrderId;
	}

	public Programs(String lotteryCode, String issue, String programsOrderId,
			String autoOrderId) {
		this.lotteryCode = lotteryCode;
		this.issue = issue;
		this.programsOrderId = programsOrderId;
		this.autoOrderId = autoOrderId;
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

	public String getOutOrderId() {
		return this.outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
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

	public String getIssue() {
		return this.issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getBuyType() {
		return this.buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
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

	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Integer getBonusStatus() {
		return this.bonusStatus;
	}

	public void setBonusStatus(Integer bonusStatus) {
		this.bonusStatus = bonusStatus;
	}

	public Integer getPrivacy() {
		return this.privacy;
	}

	public void setPrivacy(Integer privacy) {
		this.privacy = privacy;
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

	public Integer getTotalWere() {
		return this.totalWere;
	}

	public void setTotalWere(Integer totalWere) {
		this.totalWere = totalWere;
	}

	public Integer getBuyWere() {
		return this.buyWere;
	}

	public void setBuyWere(Integer buyWere) {
		this.buyWere = buyWere;
	}

	public Integer getMinWere() {
		return this.minWere;
	}

	public void setMinWere(Integer minWere) {
		this.minWere = minWere;
	}

	public Integer getLastWere() {
		return this.lastWere;
	}

	public void setLastWere(Integer lastWere) {
		this.lastWere = lastWere;
	}

	public Integer getCommission() {
		return this.commission;
	}

	public void setCommission(Integer commission) {
		this.commission = commission;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getOrderAmount() {
		return this.orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Double getBonusAmount() {
		return this.bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Integer getTicketCount() {
		return this.ticketCount;
	}

	public void setTicketCount(Integer ticketCount) {
		this.ticketCount = ticketCount;
	}

	public Integer getBonusTicket() {
		return this.bonusTicket;
	}

	public void setBonusTicket(Integer bonusTicket) {
		this.bonusTicket = bonusTicket;
	}

	public Integer getSuccessTicket() {
		return this.successTicket;
	}

	public void setSuccessTicket(Integer successTicket) {
		this.successTicket = successTicket;
	}

	public Integer getFailureTicket() {
		return this.failureTicket;
	}

	public void setFailureTicket(Integer failureTicket) {
		this.failureTicket = failureTicket;
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

	public Integer[] getOrderStatusArr() {
		return this.orderStatusArr;
	}

	public void setOrderStatusArr(Integer[] orderStatusArr) {
		this.orderStatusArr = orderStatusArr;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getBonusType() {
		return this.bonusType;
	}

	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}

	public Integer getBonusToAccount() {
		return this.bonusToAccount;
	}

	public void setBonusToAccount(Integer bonusToAccount) {
		this.bonusToAccount = bonusToAccount;
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

	public Double getFixBonusAmount() {
		return this.fixBonusAmount;
	}

	public void setFixBonusAmount(Double fixBonusAmount) {
		this.fixBonusAmount = fixBonusAmount;
	}

	public Integer getBigBonus() {
		return this.bigBonus;
	}

	public void setBigBonus(Integer bigBonus) {
		this.bigBonus = bigBonus;
	}

	public Integer getException() {
		return this.exception;
	}

	public void setException(Integer exception) {
		this.exception = exception;
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

	public Date getGameEndTime() {
		return this.gameEndTime;
	}

	public void setGameEndTime(Date gameEndTime) {
		this.gameEndTime = gameEndTime;
	}

	public String getGameEndId() {
		return this.gameEndId;
	}

	public void setGameEndId(String gameEndId) {
		this.gameEndId = gameEndId;
	}

	public Date getGameStartTime() {
		return this.gameStartTime;
	}

	public void setGameStartTime(Date gameStartTime) {
		this.gameStartTime = gameStartTime;
	}

	public String getGameStartId() {
		return this.gameStartId;
	}

	public void setGameStartId(String gameStartId) {
		this.gameStartId = gameStartId;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getIsUpload() {
		return this.isUpload;
	}

	public void setIsUpload(Integer isUpload) {
		this.isUpload = isUpload;
	}

	public Integer getTop() {
		return this.isTop;
	}

	public void setTop(Integer top) {
		this.isTop = top;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getUpload() {
		return this.isUpload;
	}

	public void setUpload(Integer upload) {
		this.isUpload = upload;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public Date getBonusTime() {
		return this.bonusTime;
	}

	public void setBonusTime(Date bonusTime) {
		this.bonusTime = bonusTime;
	}

	public Integer getRenGouCount() {
		return this.renGouCount;
	}

	public void setRenGouCount(Integer renGouCount) {
		this.renGouCount = renGouCount;
	}

	public Date getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSoftVer() {
		return this.softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
}
