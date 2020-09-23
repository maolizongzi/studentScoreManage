package com.cndym.bean;

import java.io.Serializable;

/**
*
* 用户
* @author lina
* @time	2020/09/02 10：46
*/
public class User  implements Serializable {
	private static final long serialVersionUID = 2237782137665137163L;
	private int userId	;//主键，自增长
	private String userNo	;//	用户编号
	private String userName	;//	用户名
	private String userPassword	;//	密码
	private String userTelephone	;//电话
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
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
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
}
