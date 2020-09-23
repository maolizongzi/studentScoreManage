package com.cndym.dao.task;

import com.cndym.entity.data.task.DataTableType;
import com.cndym.util.SpringUtils;

public class DataTableDetailDaoFactory {
	
	public static IDataTableDetailDao getDataTableDetailDao(String type){
		if(DataTableType.TASK_SCORE_MAIN_DATA.equals(type)){
			return (IDataTableDetailDao) SpringUtils.getBean("scoreTotalDataDetailDao");
		}
		if(DataTableType.TASK_ACHIEVE_INFO_DATA.equals(type)){
			return (IDataTableDetailDao) SpringUtils.getBean("achieveInfoDataDetailDao");
		}
		if(DataTableType.TASK_DAY_TASK_INFO_DATA.equals(type)){
			return (IDataTableDetailDao) SpringUtils.getBean("dayTaskInfoDataDetailDao");
		}
		
		if(DataTableType.TASK_PROP_AND_FRAG_MAIN_DATA.equals(type)){
			return (IDataTableDetailDao) SpringUtils.getBean("propAndFragMainDataDetailDao");
		}
		if(DataTableType.TASK_PROP_AND_FRAG_SOURCE_DATA.equals(type)){
			return (IDataTableDetailDao) SpringUtils.getBean("propAndFragSourceDataDetailDao");
		}
		if(DataTableType.TASK_SCORE_SOURSE_DATA.equals(type)){
			return (IDataTableDetailDao) SpringUtils.getBean("scoreDetailDataDetailDao");
		}
		if(DataTableType.TASK_TASK_MAIN_DATA.equals(type)){
			return (IDataTableDetailDao) SpringUtils.getBean("taskMainDataDetailDao");
		}
		return null;
	}

}
