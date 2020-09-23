package com.cndym.dao.put;

import java.util.List;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.ProgramsPick;
import com.cndym.entity.data.pick.ProgramsPickRegistered;
import com.cndym.entity.data.put.Programs;

public interface IProgramsDao extends BaseDao<Programs> {
	//方案删除
	void deletePrograms(String startDate, String endDate);
	// 查询所有成功和部分成功，不分代购 ，合买，追号的数据 ----登陆渠道
	public List<ProgramsPick> queryAllDataLogin(String startDate, String endDate);

	// 查询所有成功和部分成功，不分代购 ，合买，追号的数据 ----注册渠道
	public List<ProgramsPickRegistered> queryAllDataRegistered(String startDate, String endDate);

	// 将原始数据局筛选出来，放到投注登陆渠道表
	boolean queryAllDataByStatusLogin(String startDate, String endDate);

	// 将原始数据局筛选出来，放到投注注册渠道表
	boolean queryAllDataByStatusRegistered(String startDate, String endDate);
}
