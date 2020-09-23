package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.AccountLog;

public interface IAccountLogDao extends BaseDao<AccountLog> {

	//赠金---删除
	void deleteAccountLog(String startDate, String endDate); 
	
	// 将原始数据局筛选出来 登陆渠道
	boolean queryAccountLog(String startDate, String endDate);

	// 赠金金额--->登陆
	List<Map<String, Object>> queryGrantLogin(String startDate, String endDate);

	// 赠金金额--->注册
	List<Map<String, Object>> queryGrantReg(String startDate, String endDate);

	// 赠金消耗--->登陆
	List<Map<String, Object>> queryGrantConsumeLogin(String startDate, String endDate);

	// 赠金消耗--->注册
	List<Map<String, Object>> queryGrantConsumeReg(String startDate,
			String endDate);
}
