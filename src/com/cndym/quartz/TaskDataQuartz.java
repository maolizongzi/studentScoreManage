package com.cndym.quartz;

import java.util.Date;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.DataTableType;
import com.cndym.service.IDataTableService;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.Utils;

public class TaskDataQuartz extends QuartzJobBean {

	private void LoadAndSaveTaskData(){
		IDataTableService dataTableService = (IDataTableService) SpringUtils.getBean("dataTableService");
		Date start = Utils.getDayStart(Utils.addDate(new Date(), "d", -1));
		Date end = Utils.getDayEnd(Utils.addDate(new Date(), "d", -1));
		System.out.println("导入任务积分数据");
		//积分汇总日报
		DataTable loadDataTableFromSource = dataTableService.loadDataTableFromSource(DataTableType.TASK_SCORE_MAIN_DATA,start , end);
		dataTableService.saveDataTableToLocal(DataTableType.TASK_SCORE_MAIN_DATA, loadDataTableFromSource);
		
		DataTable achieve = dataTableService.loadDataTableFromSource(DataTableType.TASK_ACHIEVE_INFO_DATA,start , end);
		dataTableService.saveDataTableToLocal(DataTableType.TASK_ACHIEVE_INFO_DATA, achieve);

		DataTable daytask = dataTableService.loadDataTableFromSource(DataTableType.TASK_DAY_TASK_INFO_DATA,start , end);
		dataTableService.saveDataTableToLocal(DataTableType.TASK_DAY_TASK_INFO_DATA, daytask);
		
		DataTable pafMain = dataTableService.loadDataTableFromSource(DataTableType.TASK_PROP_AND_FRAG_MAIN_DATA,start , end);
		dataTableService.saveDataTableToLocal(DataTableType.TASK_PROP_AND_FRAG_MAIN_DATA, pafMain);
		
		DataTable pafSource = dataTableService.loadDataTableFromSource(DataTableType.TASK_PROP_AND_FRAG_SOURCE_DATA,start , end);
		dataTableService.saveDataTableToLocal(DataTableType.TASK_PROP_AND_FRAG_SOURCE_DATA, pafSource);
		
		DataTable socreSource = dataTableService.loadDataTableFromSource(DataTableType.TASK_SCORE_SOURSE_DATA,start , end);
		dataTableService.saveDataTableToLocal(DataTableType.TASK_SCORE_SOURSE_DATA, socreSource);
		
		DataTable taskMain = dataTableService.loadDataTableFromSource(DataTableType.TASK_TASK_MAIN_DATA,start , end);
		dataTableService.saveDataTableToLocal(DataTableType.TASK_TASK_MAIN_DATA, taskMain);
	}
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		LoadAndSaveTaskData();
	}

}
