package com.cndym.entity.user;

import java.io.Serializable;
import java.util.Date;

import com.cndym.entity.company.ResourceAllocation;

public class User implements Serializable {
	private static final long serialVersionUID = -8228227910878995302L;
	private Long userId;
	// 用户名
	private String userName;
	// 密码
	private String userPassword;
	// 姓名
	private String realName;
	// 公司id
	private String companyCode;
	// 公司名称
	private String userCompanyName;

	// 公司
	private ResourceAllocation resourceAllocation;

	// 部门名称
	private String userDepartmentName;
	// 用户组id
	private Long groupId;
	// 所属组
	private UserGroup userGroup;
	// 电话
	private String userMobile;
	// 邮箱
	private String userEmail;

	// 程禄元 2016.4.14
	private Date createTime;//创建时间
	private Date editTime;//修改时间
	
	
	private String userCode;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	// end

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String userName, String userPassword, String realName, String companyCode,
			String userCompanyName, ResourceAllocation resourceAllocation, String userDepartmentName, Long groupId,
			UserGroup userGroup, String userMobile, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.realName = realName;
		this.companyCode = companyCode;
		this.userCompanyName = userCompanyName;
		this.resourceAllocation = resourceAllocation;
		this.userDepartmentName = userDepartmentName;
		this.groupId = groupId;
		this.userGroup = userGroup;
		this.userMobile = userMobile;
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public ResourceAllocation getResourceAllocation() {
		return resourceAllocation;
	}

	public void setResourceAllocation(ResourceAllocation resourceAllocation) {
		this.resourceAllocation = resourceAllocation;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getUserCompanyName() {
		return userCompanyName;
	}

	public void setUserCompanyName(String userCompanyName) {
		this.userCompanyName = userCompanyName;
	}

	public String getUserDepartmentName() {
		return userDepartmentName;
	}

	public void setUserDepartmentName(String userDepartmentName) {
		this.userDepartmentName = userDepartmentName;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	
}
