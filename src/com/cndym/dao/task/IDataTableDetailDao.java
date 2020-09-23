package com.cndym.dao.task;

import java.util.Date;
import java.util.List;

import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.ScoreMainData;

public interface IDataTableDetailDao {
	
	public abstract DataDetail loadFromSource(Date start,Date end);
	
	public abstract void saveToLocal(DataDetail data);
	
	public abstract List<DataDetail> loadFromLoacl(Date start,Date end);
	
	public abstract void deleteFromLocal(Date start,Date end);

}
