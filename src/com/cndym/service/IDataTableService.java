package com.cndym.service;

import java.util.Date;

import com.cndym.entity.data.task.DataTable;

public interface IDataTableService {
	
	public DataTable loadDataTableFromSource(String DataTableType,Date start,Date end);
	
	public DataTable loadDataTableFromLocal(String DataTableType,Date start,Date end);

	public boolean exportDataToFile(String templatePath, String exportPath, String type, DataTable dataTable);

	public void saveDataTableToLocal(String DataTableType, DataTable dataTable);



}
