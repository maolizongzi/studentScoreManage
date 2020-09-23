package com.cndym.entity.data.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTable {
	
	private Date startDate;
	private Date endDate;
	private List<DataDetail> data = new ArrayList<DataDetail>();
	private Map<String,String> dataMeta = new HashMap<String, String>();
	private String type;
	
	
	public DataTable(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public void addData(DataDetail t){
		data.add(t);
	}
	/*****************getter and setter************************/
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public List<DataDetail> getData() {
		return data;
	}

	public void setData(List<DataDetail> data) {
		this.data = data;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DataTable [startDate=" + startDate + ", endDate=" + endDate + ", data=" + data + "]";
	}
	
	
}
