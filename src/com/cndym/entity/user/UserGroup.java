package com.cndym.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserGroup implements Serializable {
	private static final long serialVersionUID = -5280171719992736768L;
	// 组ID
	private Long groupId;
	private String groupName;
	// 创建时间
	private Date createTime;
	// 拥有的权限
	private String groupPermissions;
	
	
	
	private List<String> functionParentFlag;
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupPermissions() {
		return groupPermissions;
	}

	public void setGroupPermissions(String groupPermissions) {
		this.groupPermissions = groupPermissions;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<String> getFunctionParentFlag() {
		return functionParentFlag;
	}

	public void setFunctionParentFlag(List<String> functionParentFlag) {
		this.functionParentFlag = functionParentFlag;
	}
}
