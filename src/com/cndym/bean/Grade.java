package com.cndym.bean;

import java.io.Serializable;

/**
 * 
 * @author lina
 * @time	2020/09/02 10：46
 */
public class Grade implements Serializable  {
	private static final long serialVersionUID = 4173651857530338379L;
	private int gradeId	;//主键，自增长
	private String stuNo	;//学号,唯一,班级+学号(S201901001)s
	private String stuName	;//	学生姓名
	private String courseNo		;//	课程编号唯一
	private String courseName	;//课程名称
	private String examNo	;//考试编号
	private String examName	;//	考试名称
	private String classNo	;//	班级编号唯一
	private String className	;//	班级名称
	private String examScore	   ;//	考试成绩
	private String examTime	;//考试时间
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
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
	public String getExamNo() {
		return examNo;
	}
	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
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
	public String getExamScore() {
		return examScore;
	}
	public void setExamScore(String examScore) {
		this.examScore = examScore;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
}
