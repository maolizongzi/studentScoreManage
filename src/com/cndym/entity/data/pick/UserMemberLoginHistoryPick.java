package com.cndym.entity.data.pick;

import java.io.Serializable;
import java.util.Date;

/**
 * DAU：当前日期，去重登录用户数
 * @author Administrator
 *
 */
public class UserMemberLoginHistoryPick implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String userCode;
	private String sid;
	private String platform;
	private String softVer;
	private String mobileInfo;
	private String ip;
	private Integer status;
	private Date createTime;

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

	public String getSoftVer() {
		return this.softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobileInfo() {
		return this.mobileInfo;
	}

	public void setMobileInfo(String mobileInfo) {
		this.mobileInfo = mobileInfo;
	}
}
