package com.cndym.entity.data.put;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户充值记录表USER_FILL
 * 
 * @author Administrator
 * 
 */
public class Fill implements Serializable {
	private static final long serialVersionUID = -2708128804776448543L;
	private Long id;
	private String userCode;
	private String orderId;
	private String outOrderId;
	private String fillResources;
	private Double amount;
	private Double realAmount;
	private Date createTime;
	private Date acceptTime;
	private Integer status;
	private String sid;
	private String platform;
	private String softVer;

	public Fill(String orderId, Integer status) {
		this.orderId = orderId;
		this.status = status;
	}

	public Fill() {
	}

	public Fill(Long id, String userCode, String orderId, String outOrderId,
			String fillResources, Double amount, Double realAmount,
			Date createTime, Date acceptTime, Integer status, String sid,
			String platform, String softVer) {
		this.id = id;
		this.userCode = userCode;
		this.orderId = orderId;
		this.outOrderId = outOrderId;
		this.fillResources = fillResources;
		this.amount = amount;
		this.realAmount = realAmount;
		this.createTime = createTime;
		this.acceptTime = acceptTime;
		this.status = status;
		this.sid = sid;
		this.platform = platform;
		this.softVer = softVer;
	}

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOutOrderId() {
		return this.outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
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

	public String getFillResources() {
		return this.fillResources;
	}

	public void setFillResources(String fillResources) {
		this.fillResources = fillResources;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRealAmount() {
		return this.realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSoftVer() {
		return this.softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
}
