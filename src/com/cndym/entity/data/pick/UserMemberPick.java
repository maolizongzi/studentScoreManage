package com.cndym.entity.data.pick;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表USER_MEMBER_PICK
 * 
 * @author Administrator
 * 
 */
public class UserMemberPick implements Serializable {
	private static final long serialVersionUID = -1498894662338670519L;
	private Long id;
	private String userCode;
	private Integer status;
	private String unionId;
	private Integer trackUnion;
	private Date createTime;
	private String softVer;
	private String registerApp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Integer getTrackUnion() {
		return trackUnion;
	}

	public void setTrackUnion(Integer trackUnion) {
		this.trackUnion = trackUnion;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public String getRegisterApp() {
		return registerApp;
	}

	public void setRegisterApp(String registerApp) {
		this.registerApp = registerApp;
	}
}
