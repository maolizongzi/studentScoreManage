package org.graduate.student.repository.model;

import org.graduate.base.general.entity.BaseQueryParam;

public class StudentQueryParam extends BaseQueryParam {

    private String studentNo;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
}
