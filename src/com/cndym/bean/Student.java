package com.cndym.bean;

import java.io.Serializable;

import com.cndym.entity.user.UserGroup;

/**
 *
 * 学生类
 * @author lina
 * @time	2020/09/02 10：46
 */
public class Student  implements Serializable {
	private static final long serialVersionUID = -7646170472179329865L;
	private int stuId; //ID
	private String stuNo;//学号,唯一,班级+学号(S201901001,S201902001
	private int stuSex;//学生性别  0男 1女
	private String stuPassword;//学生密码(初始值姓名拼音+@+123)
	private String stuCardNo;//身份证号
	private String stuAddress;//学生地址
	private String stuBirthday;//出生日期xxxx-xx-xx
	private String stuAdmissionTime;//入学时间xxxx-xx-xx
	private String stuGraduationTime;//毕业时间xxxx-xx-xx
	private int stuState;//学籍状态: 0(未毕业) 1(已毕业)2(退学)
	// 所属组
	private UserGroup userGroup;
	//组id
	private int groupId;
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public int getStuSex() {
		return stuSex;
	}
	public void setStuSex(int stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuPassword() {
		return stuPassword;
	}
	public void setStuPassword(String stuPassword) {
		this.stuPassword = stuPassword;
	}
	public String getStuCardNo() {
		return stuCardNo;
	}
	public void setStuCardNo(String stuCardNo) {
		this.stuCardNo = stuCardNo;
	}
	public String getStuAddress() {
		return stuAddress;
	}
	public void setStuAddress(String stuAddress) {
		this.stuAddress = stuAddress;
	}
	public String getStuBirthday() {
		return stuBirthday;
	}
	public void setStuBirthday(String stuBirthday) {
		this.stuBirthday = stuBirthday;
	}
	public String getStuAdmissionTime() {
		return stuAdmissionTime;
	}
	public void setStuAdmissionTime(String stuAdmissionTime) {
		this.stuAdmissionTime = stuAdmissionTime;
	}
	public String getStuGraduationTime() {
		return stuGraduationTime;
	}
	public void setStuGraduationTime(String stuGraduationTime) {
		this.stuGraduationTime = stuGraduationTime;
	}
	public int getStuState() {
		return stuState;
	}
	public void setStuState(int stuState) {
		this.stuState = stuState;
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
