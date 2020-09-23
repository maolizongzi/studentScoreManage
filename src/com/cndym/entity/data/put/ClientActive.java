package com.cndym.entity.data.put;

import java.io.Serializable;
import java.util.Date;

public class ClientActive implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	// 渠道
	private String sid;
	// 平台
	private String platform;
	// 软件版本
	private String softVer;
	// 机器码
	private String machId;
	// 首次激活时间
	private Date createTime;
	// 更新时间
	private Date updateTime;
	// 0 初始 1 有效
	private Integer status;

	public ClientActive() {
	}

	public ClientActive(Long id, String sid, String platform, String softVer,
			String machId, Date createTime, Date updateTime, Integer status) {
		this.id = id;
		this.sid = sid;
		this.platform = platform;
		this.softVer = softVer;
		this.machId = machId;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public String getMachId() {
		return machId;
	}

	public void setMachId(String machId) {
		this.machId = machId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
