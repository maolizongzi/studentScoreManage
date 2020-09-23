package com.cndym.entity.data.task;

import java.util.Date;
import java.util.Map;

public abstract class DataDetail {
	
	public abstract void putCurrentDate(Date date);
	
	public abstract Date loadCurrentDate();
	
	@Override
	public abstract String toString();

}
