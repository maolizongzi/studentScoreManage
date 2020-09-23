package com.cndym.quartz;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.DataTableType;
import com.cndym.entity.email.UserEmail;
import com.cndym.entity.report.BookReport;
import com.cndym.service.IBookReportService;
import com.cndym.service.IDataInfoService;
import com.cndym.service.IDataTableService;
import com.cndym.service.ISendEmailService;
import com.cndym.service.ISendTaskEmailService;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.Utils;

public class SendEmail extends QuartzJobBean {
	private Logger logger = Logger.getLogger(getClass());
	private ISendEmailService sendEmailServiceImpl;
	private IBookReportService bookReportServiceImpl;
	private IDataInfoService dataInfoServiceImpl;
	private ISendTaskEmailService sendTaskEmailServiceImpl;
	private IDataTableService dataTableService;
	private boolean isDebug = false;


	public void query() {
		logger.info("sendEmail start");
		if (sendEmailServiceImpl == null) {
			sendEmailServiceImpl = (ISendEmailService) SpringUtils
					.getBean("sendEmailServiceImpl");
			bookReportServiceImpl = (IBookReportService) SpringUtils
					.getBean("bookReportServiceImpl");
			dataInfoServiceImpl = (IDataInfoService) SpringUtils
					.getBean("dataInfoServiceImpl");

			// 1.渠道用户
			// 查询订阅的信息 delStatus=0 ， 当前时间》= reportTime+1day
			// 查询邮件订阅表，条件是订阅时间+1<=当前时间
			List<BookReport> reportSidList = bookReportServiceImpl
					.queryReport();
			logger.info("渠道用户订阅报表的条数：" + reportSidList.size());


			Date nowTime=new Date();
			String reportTime = Utils.addDateTime(nowTime, "d", -30);

			// 数据信息
			for (BookReport bookSid : reportSidList) {
				String sid = bookSid.getSid();
				String permissionType = bookSid.getPermissionType();
				List<DataInfo> dataInfoSid = dataInfoServiceImpl
						.queryBySidAllTime(sid, reportTime, permissionType,"");
				UserEmail email = new UserEmail();
				email.setTitle(bookSid.getName());
				email.setReceiveEmail(bookSid.getEmailAddress());
				sendEmailServiceImpl.sendEmail(dataInfoSid, email,
						permissionType, "");
			}

			List<BookReport> reportAdminList = bookReportServiceImpl
					.queryReportAdmin();
			logger.info("管理员用户订阅报表的条数：" + reportAdminList.size());

			for (BookReport bookAdmin : reportAdminList) {
				String permissionType = bookAdmin.getPermissionType();
				String sid = bookAdmin.getSid();
				UserEmail email = new UserEmail();
				email.setTitle(bookAdmin.getName());
				email.setReceiveEmail(bookAdmin.getEmailAddress());
				//发送任务积分邮件
				logger.debug(bookAdmin.getEmailAddress()+"   |    " +bookAdmin.getName());
				if(!"fa".equals(permissionType)&&!"fb".equals(permissionType)&&!"fc".equals(permissionType)){
					//TEST 定向发送邮件 start
					if(isDebug&&!bookAdmin.getUserCode().equals("1461837622684")){
						continue;
					}
					//TEST  end
					try{
//						logger.debug(bookAdmin.getName()+" | "+"称号获得人数分布 | "+Boolean.toString(!bookAdmin.getName().equals("称号获得人数分布")));
						sendTaskMail(bookAdmin.getPermissionType(),email);
					}catch(Exception e){
						e.printStackTrace();
						logger.error("发送任务积分数据邮件失败  type:"+bookAdmin.getPermissionType()+"  to:"+bookAdmin.getName());
					}
					continue;
				}

				if(isDebug){//如果是debug   不执行发送其他邮件
					continue;
				}

				//原数据发送邮件
				List<DataInfo> dataInfoList =  dataInfoServiceImpl
						.queryBySidAllTime(sid, reportTime, permissionType,"admin");
				if (Utils.isEmpty(sid)) {
					// 统计数据
					sendEmailServiceImpl.sendEmail(dataInfoList, email,
							permissionType, "count");
				} else {
					// 根据sid查询渠道数据
					sendEmailServiceImpl.sendEmail(dataInfoList, email,
							permissionType, "");
				}
			}
			logger.info("sendEmail end");
		} else {
			logger.info("sendEmail error");
		}
	}

	private void sendTaskMail(String type,UserEmail email){
		if(!type.equals(DataTableType.TASK_ACHIEVE_INFO_DATA)&&!type.equals(DataTableType.TASK_DAY_TASK_INFO_DATA)&&
				!type.equals(DataTableType.TASK_PROP_AND_FRAG_MAIN_DATA)&&!type.equals(DataTableType.TASK_PROP_AND_FRAG_SOURCE_DATA)&&
				!type.equals(DataTableType.TASK_SCORE_MAIN_DATA)&&!type.equals(DataTableType.TASK_SCORE_SOURSE_DATA)&&
				!type.equals(DataTableType.TASK_TASK_MAIN_DATA)){
			return ;
		}
		dataTableService = (IDataTableService) SpringUtils.getBean("dataTableService");
		sendTaskEmailServiceImpl = (ISendTaskEmailService) SpringUtils.getBean("sendTaskEmailServiceImpl");
		Date start = Utils.getDayStart(new Date(System.currentTimeMillis()-1000*60*60*24*7));
		Date end = Utils.getDayEnd(new Date(System.currentTimeMillis()-1000*60*60*24*1));
		DataTable loadDataTableFromLocal = dataTableService.loadDataTableFromLocal(type, start, end);
		email.setTitle("[任务积分] "+email.getTitle()+" ("+Utils.formatDate2Str(Utils.addDate(new Date(), "d", -1), "yyyyMMdd")+")");
		sendTaskEmailServiceImpl.sendEmail(loadDataTableFromLocal, email);
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		query();
	}

	public static void main(String[] args) {
//		System.out.println(String.format("%.2f",7.0));
		new SendEmail().query();
	}
}
