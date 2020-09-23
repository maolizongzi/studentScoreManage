package com.cndym.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import com.cndym.dao.task.DataTableDetailDaoFactory;
import com.cndym.dao.task.IDataTableDetailDao;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.DataTableType;
import com.cndym.entity.data.task.ExportDataFileType;
import com.cndym.service.IDataTableService;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.DataDetailUtils;
import com.cndym.util.export.Utils;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;


@Service
public class DataTableService implements IDataTableService {

	private static Logger logger = Logger.getLogger(DataTableService.class);
	
	public static List<Date> getBetweenDays(Date start,Date end){
		List<Date> res = new ArrayList<Date>();
		Date tempstart = Utils.getDayStart(start);
		Date tempend = Utils.getDayStart(end);
		if(tempstart.compareTo(tempend)==1){
			return res;
		}
		Date tempDate = tempstart;
		while(tempDate.compareTo(tempend)<0){
			res.add(start);
			start = Utils.addDate(start, "d", 1);
			tempDate = Utils.addDate(tempDate, "d", 1);
		}
		res.add(end);
		return res;
	}
	
	@Override
	public DataTable loadDataTableFromLocal(String DataTableType, Date start, Date end) {
		IDataTableDetailDao dataTableDetailDao = DataTableDetailDaoFactory.getDataTableDetailDao(DataTableType);
		DataTable dataTable = new DataTable(start, end);
		List<Date> dates = getBetweenDays(start, end);
		if(dates==null || dates.isEmpty()){
			return null;
		}
		//载入数据
		List<DataDetail> loadFromSource = dataTableDetailDao.loadFromLoacl(start,end);
		dataTable.setData(loadFromSource);
		dataTable.setType(DataTableType);
		return dataTable;
	}

	@Override
	public void saveDataTableToLocal(String DataTableType,DataTable dataTable) {
		IDataTableDetailDao dataTableDetailDao = DataTableDetailDaoFactory.getDataTableDetailDao(DataTableType);
		List<DataDetail> datas = dataTable.getData();
		dataTableDetailDao.deleteFromLocal(dataTable.getStartDate(), dataTable.getEndDate());
		for(DataDetail data : datas){
			dataTableDetailDao.saveToLocal(data);
		}
	}
	
	@Override
	public DataTable loadDataTableFromSource(String DataTableType, Date start, Date end) {
		IDataTableDetailDao dataTableDetailDao = DataTableDetailDaoFactory.getDataTableDetailDao(DataTableType);
		DataTable dataTable = new DataTable(start, end);
		List<Date> dates = getBetweenDays(start, end);
		if(dates==null || dates.isEmpty()){
			return null;
		}
		//载入数据
		for(Date date : dates){
			DataDetail loadFromSource = dataTableDetailDao.loadFromSource(Utils.getDayStart(date), Utils.getDayEnd(date));
			dataTable.addData(loadFromSource);
		}
		dataTable.setType(DataTableType);
		return dataTable;
	}

	
	/******************** 导出文件 start ************************/
	
	@Override
	public boolean exportDataToFile(String templatePath,String exportPath,String type,DataTable dataTable){
		if(ExportDataFileType.XLSX.equals(type)){
			try {
				return exportDataToXLSX(templatePath, exportPath, dataTable);
			} catch (Exception e) {
				logger.debug("导出XLSX出错", e);
				return false;
			}
		}else if(ExportDataFileType.TXT.equals(type)){
			try {
				return exportDataToTxt(templatePath, exportPath, dataTable);
			} catch (Exception e) {
				logger.debug("导出TXT出错", e);
				return false;
			} 
		}else if(ExportDataFileType.CSV.equals(type)){
			try {
				return exportDataToTxt(templatePath, exportPath, dataTable);
			} catch (Exception e) {
				logger.debug("导出CSV出错", e);
				return false;
			}
		}
		return false;
	}
	
	private boolean exportDataToXLSX(String templatePath,String exportPath,DataTable dataTable) throws IllegalArgumentException, IllegalAccessException, ParsePropertyException, InvalidFormatException, IOException{
		List<Map<String, Object>> data;
		data = DataDetailUtils.formatDataToMaps(dataTable,ExportDataFileType.XLSX);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", data);
		XLSTransformer transformer = new XLSTransformer();
		transformer.transformXLS(templatePath, map, exportPath);
		return true;
	}
	
	private boolean exportDataToTxt(String templatePath,String exportPath,DataTable dataTable) throws IOException, IllegalArgumentException, IllegalAccessException{
		File file = new File(templatePath);
		if(!file.exists()){
			return false;
		}
		File outFile = new File(exportPath);
		if(outFile.exists()){
			outFile.delete(); 
		}
		outFile.createNewFile();
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
		String title = reader.readLine();
		String content = reader.readLine();
		List<Map<String,Object>> list = DataDetailUtils.formatDataToMaps(dataTable,ExportDataFileType.TXT);
		String result = "";
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"GBK"));
		writer.write(title);writer.write("\r\n");
		for(Map<String,Object> data : list){
			result = DataDetailUtils.formatContent(content, data);
			result += "\r\n";
			writer.write(result);
		}
		writer.close();
		reader.close();
		return true;
	}
	
	public static void main(String[] args) {
		String templateDir = "E:\\gitRepo\\cooperation_manager\\WebRoot\\WEB-INF\\file\\task\\template";
		String exportDir = "E:\\gitRepo\\cooperation_manager\\WebRoot\\WEB-INF\\file\\task\\data";
		String type = DataTableType.TASK_SCORE_SOURSE_DATA;
		IDataTableService d = (IDataTableService) SpringUtils.getBean("dataTableService");
		Date start = Utils.getDayStart(Utils.addDate(new Date(), "d", -5));
		Date end = Utils.getDayEnd(Utils.addDate(new Date(), "d", -5));
		DataTable loadDataTable = d.loadDataTableFromSource(type, start, end);
//		d.saveDataTableToLocal(type, loadDataTable);
//		DataTable loadDataTableFromLocal = d.loadDataTableFromLocal(type, start, end);
//		System.out.println(loadDataTable);
//		d.exportDataToFile(templateDir+"\\xlsx\\"+type+".xlsx", exportDir+"\\"+type+".xlsx", ExportDataFileType.XLSX, loadDataTableFromLocal);
//		d.exportDataToFile(templateDir+"\\txt\\"+type+".txt", exportDir+"\\"+type+".txt", ExportDataFileType.TXT, loadDataTableFromLocal);
//		d.exportDataToFile(templateDir+"\\csv\\"+type+".csv", exportDir+"\\"+type+".csv", ExportDataFileType.CSV, loadDataTableFromLocal);
		System.out.println(loadDataTable);
	}
}
