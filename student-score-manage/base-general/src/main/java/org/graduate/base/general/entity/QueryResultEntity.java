package org.graduate.base.general.entity;

public class QueryResultEntity<T> extends BaseResultEntity<T> {

    private Integer pageSize;
    private Integer totalPage;
    private Integer queryResultCount;

    public QueryResultEntity(Integer pageSize, Integer queryResultCount) {
        this.pageSize = pageSize;
        this.queryResultCount = queryResultCount;
        this.totalPage = (queryResultCount + pageSize - 1) / pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getQueryResultCount() {
        return queryResultCount;
    }

    public void setQueryResultCount(Integer queryResultCount) {
        this.queryResultCount = queryResultCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
