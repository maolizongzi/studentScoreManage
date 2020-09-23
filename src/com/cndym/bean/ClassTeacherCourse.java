package com.cndym.bean;

import java.io.Serializable;

/**
 * 班级老师课程表
 * @author LiNa
 * @time	2020/09/02 10：46
 */
public class ClassTeacherCourse implements Serializable {
	private static final long serialVersionUID = 2188923231590301208L;
	private int  ctcId	;//主键，自增长
	private String classNo	;//	班级编号唯一  外键
	private String 	className	;//班级名称
	private String 	teaNo	;//	老师职工号,唯一 T+入职年份+01.....  外键
	private String 	teaName	;//	老师姓名
	private String 	courseNo	;//	课程编号唯一 外键
	private String 	courseName	;//	课程名称
	public int getCtcId() {
		return ctcId;
	}
	public void setCtcId(int ctcId) {
		this.ctcId = ctcId;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTeaNo() {
		return teaNo;
	}
	public void setTeaNo(String teaNo) {
		this.teaNo = teaNo;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
