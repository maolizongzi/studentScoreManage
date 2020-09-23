package com.cndym.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cndym.service.ISynchronizationDataService;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.Utils;

public class SynchronizationData extends QuartzJobBean {
	private Logger logger = Logger.getLogger(getClass());
	private ISynchronizationDataService synchronizationDataServiceImpl;
	public void query() {
		logger.info("synchronizationData start");
		if (synchronizationDataServiceImpl == null) {
			String start = Utils.yesterday();
			String end = Utils.today();
			synchronizationDataServiceImpl = (ISynchronizationDataService) SpringUtils
					.getBean("synchronizationDataServiceImpl");
			/**同步之前先删除数据*/
			synchronizationDataServiceImpl.deleteDataInfo(start, end);
			/**获得原始数据*/
			synchronizationDataServiceImpl.getDataByOriginal(start, end);
//			/**查询登录渠道数据*/
			synchronizationDataServiceImpl.queryDateLogin(start, end);
//			/**统计登录渠道数据*/
			synchronizationDataServiceImpl.countDateLogin(start, end);
//			/**统计注册渠道数据*/
			synchronizationDataServiceImpl.countDateReg(start, end);
//			/**统计登录渠道/注册渠道 总的数据*/
			synchronizationDataServiceImpl.saveDataCount(start,end);
			/**获得新用户And登录用   登录/投注     中间表 */
			synchronizationDataServiceImpl.saveUserMemeber(start, end);
			/**新用户And投注用户     登录/投注转行率信息保存*/
			synchronizationDataServiceImpl.userMemberRetentionRate(start, end);
			/**处理为空的数据*/
			synchronizationDataServiceImpl.updateMemberNullRate(start,end);
			logger.info("synchronizationData end");
		} else {
			logger.info("synchronizationData error");
		}
	}
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		query();
	}
	
	
}
