package org.graduate.subject.repository.model;

import org.graduate.base.general.entity.BaseQueryParam;

public class ExamQueryParam extends BaseQueryParam {
    private Long id;
    private String no;
    private String name;

    public ExamQueryParam(Integer currentPage, Integer pageSize) {
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
}
