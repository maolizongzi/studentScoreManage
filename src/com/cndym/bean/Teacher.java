package com.cndym.bean;

import java.io.Serializable;

import com.cndym.entity.user.UserGroup;

/**
*
* 老师类
* @author lina
* @time	2020/09/02 10：46
*/
public class Teacher implements Serializable {
	private static final long serialVersionUID = 8690739672847914175L;
	private int 	teaId	;//	老师Id，主键，自增长
	private String	teaNo	;//		老师职工号,唯一T+入职年份+01
	private String	teaPassword	;//	老师密码(初始值姓名拼音+@+123)
	private int 	teaSex	;//		老师性别
	private String	teaName	;//		老师姓名
	private String	teaTelephone;//		老师电话
	private String	teaSubject	;//		所教课程
	private String	teaAdmissionTime	;//		入职时间xxxx-xx-xx
	private String	teaLeaveTime	;//		   离职时间xxxx-xx-xx
	private int 	teaState	;//	任职状态: 0(在职) 1(离职)
	// 所属组
	private UserGroup userGroup;
	//组id
	private int groupId;
	public int getTeaId() {
		return teaId;
	}
	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}
	public String getTeaNo() {
		return teaNo;
	}
	public void setTeaNo(String teaNo) {
		this.teaNo = teaNo;
	}
	public String getTeaPassword() {
		return teaPassword;
	}
	public void setTeaPassword(String teaPassword) {
		this.teaPassword = teaPassword;
	}
	public int getTeaSex() {
		return teaSex;
	}
	public void setTeaSex(int teaSex) {
		this.teaSex = teaSex;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getTeaTelephone() {
		return teaTelephone;
	}
	public void setTeaTelephone(String teaTelephone) {
		this.teaTelephone = teaTelephone;
	}
	public String getTeaSubject() {
		return teaSubject;
	}
	public void setTeaSubject(String teaSubject) {
		this.teaSubject = teaSubject;
	}
	public String getTeaAdmissionTime() {
		return teaAdmissionTime;
	}
	public void setTeaAdmissionTime(String teaAdmissionTime) {
		this.teaAdmissionTime = teaAdmissionTime;
	}
	public String getTeaLeaveTime() {
		return teaLeaveTime;
	}
	public void setTeaLeaveTime(String teaLeaveTime) {
		this.teaLeaveTime = teaLeaveTime;
	}
	public int getTeaState() {
		return teaState;
	}
	public void setTeaState(int teaState) {
		this.teaState = teaState;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
