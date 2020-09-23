package com.cndym.service;

public interface ISynchronizationDataService {
	/** 当天同步之前，先删除数*/
	void deleteDataInfo(String startDate, String endDate);

	/** 同步激活数，DAU，注册用户数到库中*/
	void getDataByOriginal(String start, String end);

	/** 同步充值，赠金，投注到库中*/
	void queryDateLogin(String startDate, String endDate);

	/** 登陆计算结果入库*/
	void countDateLogin(String startDate, String endDate);

	/** 注册计算结果入库 */
	void countDateReg(String startDate, String endDate);
	
	/**统计当天渠道的总数据*/
	void saveDataCount(String startDate,String endDate);
	
	/**新用户And投注用户   登录/投注中间表 */
	public void saveUserMemeber(String start, String end);
	
	/**新用户And投注用户     登录/投注转行率信息保存*/
	public void userMemberRetentionRate(String start, String end) ;
	
	public void updateMemberNullRate(String start, String end);	
}
