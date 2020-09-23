package com.cndym.bean;

import java.io.Serializable;

/**
 * 课程表
 * @author LiNa
 * @time	2020/09/02 10：46
 */
public class Course implements Serializable {
	private static final long serialVersionUID = -8596871789059892966L;
	private int courseId	;//	课程Id，主键，自增长
	private String courseNo		;//	课程编号唯一
	private String courseName	;//	课程名称
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
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
