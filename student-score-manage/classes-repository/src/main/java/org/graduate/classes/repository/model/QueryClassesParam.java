package org.graduate.classes.repository.model;

import org.graduate.base.general.entity.BaseQueryParam;

public class QueryClassesParam extends BaseQueryParam {
    private Long id;
    private String no;
    private String name;
    private Integer grade;

    public QueryClassesParam(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
