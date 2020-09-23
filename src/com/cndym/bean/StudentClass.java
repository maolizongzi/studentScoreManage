package com.cndym.bean;

import java.io.Serializable;

/**
 * 班级类
 * @author LiNa
 * @time	2020/09/02 10：46
 *
 */
public class StudentClass implements Serializable {
	private static final long serialVersionUID = -701580149443848629L;
	private int	classId	;//	班级Id，主键，自增长
	private String classNo	;//	班级编号2016+01班,唯一不可以修改
	private String className	;//	班级名称,修改高一,高二
	private int classStudentNumber	;//	班级学生人数
	private int classStatus	;//	0(未毕业) 1(已毕业)
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
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
	public int getClassStudentNumber() {
		return classStudentNumber;
	}
	public void setClassStudentNumber(int classStudentNumber) {
		this.classStudentNumber = classStudentNumber;
	}
	public int getClassStatus() {
		return classStatus;
	}
	public void setClassStatus(int classStatus) {
		this.classStatus = classStatus;
	}
}
