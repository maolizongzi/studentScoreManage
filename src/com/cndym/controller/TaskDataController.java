package com.cndym.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.DataTableType;
import com.cndym.entity.data.task.ExportDataFileType;
import com.cndym.service.IDataTableService;
import com.cndym.util.export.DataDetailUtils;
import com.cndym.util.export.FilesUtils;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("task")
public class TaskDataController {
	
	@Resource
	private IDataTableService dataTableService;
	
	@RequestMapping("export_{fileType}")
	@ResponseBody
	public void exportFile(@PathVariable("fileType")String fileType,String dataType,String start,String end,HttpServletRequest request,HttpServletResponse response){
		System.out.println(fileType);
		String templateDir = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/file/task/template/";
		String dataDir = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/file/task/data/";
		Date startDate = Utils.getDate(start);
		Date endDate = Utils.getDate(end);
		DataTable loadDataTableFromLocal = dataTableService.loadDataTableFromLocal(dataType, startDate, endDate);
		String dataPath = "";
		if("xlsx".equals(fileType)){
			String templatePath = templateDir+"xlsx/"+dataType+".xlsx";
			dataPath = dataDir+dataType+".xlsx";
			dataTableService.exportDataToFile(templatePath, dataPath, ExportDataFileType.XLSX, loadDataTableFromLocal);
		}else if("txt".equals(fileType)){
			String templatePath = templateDir+"txt/"+dataType+".txt";
			dataPath = dataDir+dataType+".txt";
			dataTableService.exportDataToFile(templatePath, dataPath, ExportDataFileType.TXT, loadDataTableFromLocal);
		}else if("csv".equals(fileType)){
			String templatePath = templateDir+"csv/"+dataType+".csv";
			dataPath = dataDir+dataType+".csv";
			dataTableService.exportDataToFile(templatePath, dataPath, ExportDataFileType.CSV, loadDataTableFromLocal);
		}
		FilesUtils.downLoad(dataPath, response);
	}
	
	@RequestMapping("dataList")
	public String showDataList(String dataType,String start,String end,ModelMap model){
		if(Utils.isEmpty(start)||Utils.isEmpty(end)){
			return "/data/task/dataInfoList";
		}
		Date startDate = Utils.getDayStart(Utils.getDate(start));
		Date endDate = Utils.getDayEnd(Utils.getDate(end));
		DataTable loadDataTableFromLocal = dataTableService.loadDataTableFromLocal(dataType, startDate, endDate);
		model.put("dataInfo", loadDataTableFromLocal);
		try {
			List<Map<String, Object>> data = DataDetailUtils.formatDataToMaps(loadDataTableFromLocal, ExportDataFileType.HTML);
			model.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("dataType", dataType);
		return "/data/task/dataInfoList";
	}

}
