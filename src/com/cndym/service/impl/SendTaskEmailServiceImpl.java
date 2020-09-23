package com.cndym.service.impl;

import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.email.MailEngine;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.data.task.AchieveInfoData;
import com.cndym.entity.data.task.DataDetail;
import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.data.task.DataTableType;
import com.cndym.entity.data.task.DayTaskInfoData;
import com.cndym.entity.data.task.ExportDataFileType;
import com.cndym.entity.data.task.PropAndFragMainData;
import com.cndym.entity.data.task.PropAndFragSourceData;
import com.cndym.entity.data.task.ScoreDetailData;
import com.cndym.entity.data.task.ScoreMainData;
import com.cndym.entity.data.task.TaskMainData;
import com.cndym.entity.email.UserEmail;
import com.cndym.service.ISendEmailService;
import com.cndym.service.ISendTaskEmailService;
import com.cndym.util.ConfigUtils;
import com.cndym.util.SpringUtils;
import com.cndym.util.export.DataDetailUtils;
import com.cndym.util.export.Utils;

@Service("sendTaskEmailServiceImpl")
public class SendTaskEmailServiceImpl implements ISendTaskEmailService {
	private Logger logger = Logger.getLogger(getClass());
	// private String cellStyle = " style='border: 1px solid black;' ";
	private String thStyle = " style='background-color: #FFFFFF;border: 1px solid black;' ";
	private String td1Style = " style='background-color: #DDDDDD;border: 1px solid black;text-align: right;' ";
	private String td2Style = " style='background-color: #FFFFFF;border: 1px solid black;text-align: right;' ";
	private String tableStyle = " style='overflow: scroll;min-width:1300px;border-collapse:collapse;' ";
	private String tempStyle = "";

	@Override
	public void sendEmail(DataTable data, UserEmail email) {
		MailEngine mailEngine = (MailEngine) SpringUtils.getBean("mailEngine");
		String content = formatDataToEmailContent(data);
//		System.out.println(content);
		try {
			mailEngine.sendMessage(new String[] { email.getReceiveEmail() }, ConfigUtils.getValue("MAIL.DEFAULT.FROM"),
					null, content, email.getTitle(), null);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public String formatDataToEmailContent(DataTable data) {
		String type = data.getType();
		String result = "";
		try {
			List<Map<String, Object>> formatDataToMaps = DataDetailUtils.formatDataToMaps(data,ExportDataFileType.HTML);
			
			if (DataTableType.TASK_ACHIEVE_INFO_DATA.equals(type)) {
				result = getAchieveInfoDataContent(formatDataToMaps);
			} else if (DataTableType.TASK_DAY_TASK_INFO_DATA.equals(type)) {
				result = getDayTaskInfoData(formatDataToMaps);
			} else if (DataTableType.TASK_PROP_AND_FRAG_MAIN_DATA.equals(type)) {
				result = getPAFMainData(formatDataToMaps);
			} else if (DataTableType.TASK_PROP_AND_FRAG_SOURCE_DATA.equals(type)) {
				result = getPAFSourceData(formatDataToMaps);
			} else if (DataTableType.TASK_SCORE_MAIN_DATA.equals(type)) {
				result = getScoreMainData(formatDataToMaps);
			} else if (DataTableType.TASK_SCORE_SOURSE_DATA.equals(type)) {
				result = getSocreSourceData(formatDataToMaps);
			} else if (DataTableType.TASK_TASK_MAIN_DATA.equals(type)) {
				result = getTaskMainData(formatDataToMaps);
			}
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			result = "ERROR:生成邮件正文错误   请检查数据。";
		}
		return result;
	}

	/*
	 * List<DataDetail> d = data.getData(); StringBuilder content = new
	 * StringBuilder(); for(DataDetail detail : d){ } return content.toString();
	 */

	private String getTaskMainData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " + tableStyle + ">");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='21'>任务汇总日报</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>日期 </th>");
		content.append("		<th" + thStyle + " colspan='6'>任务完成情况汇总 </th>");
		content.append("		<th" + thStyle + " colspan='6'>今日任务完成情况汇总 </th>");
		content.append("		<th" + thStyle + " colspan='8'>成就任务完成情况汇总 </th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">完成人数 </th>");
		content.append("		<th" + thStyle + ">完成任务数 </th>");
		content.append("		<th" + thStyle + ">领取奖励任务数 </th>");
		content.append("		<th" + thStyle + ">奖励积分数 </th>");
		content.append("		<th" + thStyle + ">奖励领取人数 </th>");
		content.append("		<th" + thStyle + ">人均奖励积分 </th>");
		content.append("		<th" + thStyle + ">完成人数 </th>");
		content.append("		<th" + thStyle + ">完成任务数 </th>");
		content.append("		<th" + thStyle + ">领取奖励任务数 </th>");
		content.append("		<th" + thStyle + ">奖励积分数 </th>");
		content.append("		<th" + thStyle + ">奖励领取人数 </th>");
		content.append("		<th" + thStyle + ">人均奖励积分 </th>");
		content.append("		<th" + thStyle + ">完成人数 </th>");
		content.append("		<th" + thStyle + ">完成任务数 </th>");
		content.append("		<th" + thStyle + ">领取奖励任务数 </th>");
		content.append("		<th" + thStyle + ">奖励积分数 </th>");
		content.append("		<th" + thStyle + ">奖励领取人数 </th>");
		content.append("		<th" + thStyle + ">人均奖励积分 </th>");
		content.append("		<th" + thStyle + ">获得称号人数 </th>");
		content.append("		<th" + thStyle + ">获得称号数 </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskRecoverTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskRecoverPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskRecoverTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskRecoverPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("dayTaskAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskRecoverTaskCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskRecoverPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("achieveTaskAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("catchTitlePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("catchTitleTitleCount").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getSocreSourceData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " +tableStyle+ ">");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='33'>积分详情日报</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>日期 </th>");
		content.append("		<th" + thStyle + " colspan='3'>任务产生积分 </th>");
		content.append("		<th" + thStyle + " colspan='3'>积分抽奖消耗积分 </th>");
		content.append("		<th" + thStyle + " colspan='3'>兑换道具消耗积分 </th>");
		content.append("		<th" + thStyle + " colspan='6'>猜神驾到产生积分 </th>");
		content.append("		<th" + thStyle + " colspan='3'>猜神驾到消耗积分 </th>");
		content.append("		<th" + thStyle + " colspan='1'>猜神驾到回收 </th>");
		content.append("		<th" + thStyle + " colspan='3'>道具回收产生积分 </th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">奖励积分数 </th>");
		content.append("		<th" + thStyle + ">奖励领取人数 </th>");
		content.append("		<th" + thStyle + ">人均任务积分 </th>");
		content.append("		<th" + thStyle + ">抽奖人数 </th>");
		content.append("		<th" + thStyle + ">消耗积分数 </th>");
		content.append("		<th" + thStyle + ">人均抽奖积分 </th>");
		content.append("		<th" + thStyle + ">兑换人数 </th>");
		content.append("		<th" + thStyle + ">消耗积分数 </th>");
		content.append("		<th" + thStyle + ">人均兑换积分 </th>");
		content.append("		<th" + thStyle + ">兑换人数 </th>");
		content.append("		<th" + thStyle + ">消耗积分 </th>");
		content.append("		<th" + thStyle + ">人均消耗积分 </th>");
		content.append("		<th" + thStyle + ">猜中人数 </th>");
		content.append("		<th" + thStyle + ">奖励积分数 </th>");
		content.append("		<th" + thStyle + ">人均奖励积分 </th>");
		content.append("		<th" + thStyle + ">购买好运值人数 </th>");
		content.append("		<th" + thStyle + ">购买积分数 </th>");
		content.append("		<th" + thStyle + ">人均购买积分数 </th>");
		content.append("		<th" + thStyle + ">竞猜人数 </th>");
		content.append("		<th" + thStyle + ">消耗积分数 </th>");
		content.append("		<th" + thStyle + ">人均竞猜积分 </th>");
		content.append("		<th" + thStyle + ">回收积分数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">产生积分数 </th>");
		content.append("		<th" + thStyle + ">人均积分数 </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskCompleteRewardScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskCompleteRecivePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("taskCompleteAverageTaskScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costDrawPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costDrawCostScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costDrawAverageDrawScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropCostScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropAverageCostScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyInventedPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyInventedPropScoreCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyInventedPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodMakeSuccessPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodMakeSocre").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodMakeAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyLuckySocrePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyLuckyScoreScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyLuckyScoreAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodCostPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodCostSocre").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodCostAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guessGodReciveScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPeople").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimAverageScore").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getScoreMainData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " + tableStyle + "  >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='13'>积分汇总日报</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>日期</th>");
		content.append("		<th" + thStyle + " colspan='3'>产生积分数</th>");
		content.append("		<th" + thStyle + " colspan='3'>消耗积分数</th>");
		content.append("		<th" + thStyle + " rowspan='2'>当天登录用户积分数量</th>");
		content.append("		<th" + thStyle + " rowspan='2'>当天积分存量</th>");
		content.append("		<th" + thStyle + " rowspan='2'>积分存量</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">积分数            </th>");
		content.append("		<th" + thStyle + ">人均产生积分            </th>");
		content.append("		<th" + thStyle + ">消耗人数          </th>");
		content.append("		<th" + thStyle + ">消耗积分数          </th>");
		content.append("		<th" + thStyle + ">人均消耗积分          </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");

		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate") + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("makeScorePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("makeScoreTotalScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("makeScoreAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costScorePeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costScoreTotalScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("costScoreAverageScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("loginScoreTotalScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("changeScore").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("totalScoreInPool").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getPAFSourceData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table" +tableStyle+ " >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='16'>新增道具及碎片来源日报</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>日期</th>");
		content.append("		<th" + thStyle + " colspan='3'>积分兑换道具</th>");
		content.append("		<th" + thStyle + " colspan='6'>积分抽奖道具</th>");
		content.append("		<th" + thStyle + " colspan='3'>碎片合成道具</th>");
		content.append("		<th" + thStyle + " colspan='3'>奖不停活动道具</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均兑换数 </th>");
		content.append("		<th" + thStyle + ">道具人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均道具数 </th>");
		content.append("		<th" + thStyle + ">碎片人数 </th>");
		content.append("		<th" + thStyle + ">碎片数 </th>");
		content.append("		<th" + thStyle + ">人均碎片数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均合成数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均抽中数 </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buyPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawFragPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("drawFragAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hechengPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hechengPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hechengAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiangbutingPeopleCont").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiangbutingPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiangbutingAverageCount").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getPAFMainData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table " +tableStyle+ "  >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='33'>道具及碎片汇总日报</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " rowspan='2'>日期 </th>");
		content.append("		<th" + thStyle + " colspan='3'>新增道具 </th>");
		content.append("		<th" + thStyle + " colspan='3'>使用道具 </th>");
		content.append("		<th" + thStyle + " colspan='3'>返利卡使用情况 </th>");
		content.append("		<th" + thStyle + " colspan='3'>保底卡使用情况 </th>");
		content.append("		<th" + thStyle + " colspan='3'>加奖卡使用情况 </th>");
		content.append("		<th" + thStyle + " colspan='3'>活动道具使用情况 </th>");
		content.append("		<th" + thStyle + " colspan='3'>道具回收情况</th>");
		content.append("		<th" + thStyle + " colspan='2'>过期道具 </th>");
		content.append("		<th" + thStyle + " colspan='2'>剩余道具 </th>");
		content.append("		<th" + thStyle + " colspan='3'>新增碎片 </th>");
		content.append("		<th" + thStyle + " colspan='3'>碎片回收情况</th>");
		content.append("		<th" + thStyle + " >剩余碎片 </th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均新增数 </th>");
		content.append("		<th" + thStyle + ">使用人数 </th>");
		content.append("		<th" + thStyle + ">使用道具数 </th>");
		content.append("		<th" + thStyle + ">人均使用数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均使用数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均使用数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均使用数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">道具数 </th>");
		content.append("		<th" + thStyle + ">人均使用数 </th>");
		content.append("		<th" + thStyle + ">回收人数 </th>");
		content.append("		<th" + thStyle + ">回收道具数 </th>");
		content.append("		<th" + thStyle + ">人均回收道具数 </th>");
		content.append("		<th" + thStyle + ">过期道具数 </th>");
		content.append("		<th" + thStyle + ">累计过期道具数 </th>");
		content.append("		<th" + thStyle + ">当天剩余道具数 </th>");
		content.append("		<th" + thStyle + ">累计剩余道具数 </th>");
		content.append("		<th" + thStyle + ">人数 </th>");
		content.append("		<th" + thStyle + ">碎片数 </th>");
		content.append("		<th" + thStyle + ">人均新增数 </th>");
		content.append("		<th" + thStyle + ">回收人数 </th>");
		content.append("		<th" + thStyle + ">回收碎片数 </th>");
		content.append("		<th" + thStyle + ">人均回收碎片数 </th>");
		;
		content.append("		<th" + thStyle + ">累计剩余碎片数 </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("usedPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("usedPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("usedPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fanliPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fanliPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fanliAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("baodiPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("baodiPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("baodiAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiajiangPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiajiangPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiajiangAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huodongPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huodongPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huodongAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPropPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPropPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimPropAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guoqiDayPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guoqiTotalPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengyuDayPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengyuTotalPropCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newFragPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newFragFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("newFragAverageCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimFragPeopleCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("reclaimAverageFragCount").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengyuFragCount").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getDayTaskInfoData(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append(
				"<table class='table table-bordered table-hover'" +tableStyle+ "  id='chongZhiTableId' >");
		content.append("<thead>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + " colspan='19'>今日任务完成人数分布</th>");
		content.append("	</tr>");
		content.append("	<tr>");
		content.append("		<th" + thStyle + ">日期 </th>");
		content.append("		<th" + thStyle + ">今日登录 </th>");
		content.append("		<th" + thStyle + ">分享1次投注记录 </th>");
		content.append("		<th" + thStyle + ">今日成功充值10元 </th>");
		content.append("		<th" + thStyle + ">今日成功充值20元 </th>");
		content.append("		<th" + thStyle + ">今日成功充值50元 </th>");
		content.append("		<th" + thStyle + ">今日成功充值100元 </th>");
		content.append("		<th" + thStyle + ">今日成功充值200元 </th>");
		content.append("		<th" + thStyle + ">今日成功充值500元 </th>");
		content.append("		<th" + thStyle + ">今日投注满10元 </th>");
		content.append("		<th" + thStyle + ">今日投注满30元 </th>");
		content.append("		<th" + thStyle + ">今日投注满50元 </th>");
		content.append("		<th" + thStyle + ">今日投注满100元 </th>");
		content.append("		<th" + thStyle + ">今日投注满300元 </th>");
		content.append("		<th" + thStyle + ">今日投注满500元 </th>");
		content.append("		<th" + thStyle + ">今日投注满1000元 </th>");
		content.append("		<th" + thStyle + ">今日成功投注1次 </th>");
		content.append("		<th" + thStyle + ">今日成功投注5次 </th>");
		content.append("		<th" + thStyle + ">今日成功投注10次 </th>");
		content.append("	</tr>");
		content.append("</thead>");
		content.append("<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("	<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("login").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shareBetting1").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge10").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge20").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge50").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge100").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge200").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("recharge500").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting10").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting30").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting50").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting100").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting300").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting500").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("betting1000").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("bettingTimes1").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("bettingTimes5").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("bettingTimes10").toString() + "</td>");
			content.append("	</tr>");
		}
		content.append("</tbody>");
		content.append("</table>");
		return content.toString();
	}

	private String getAchieveInfoDataContent(List<Map<String, Object>> data) {
		StringBuilder content = new StringBuilder();
		content.append("<table" +tableStyle+ "");
		content.append("	<thead>");
		content.append("		<tr>");
		content.append("			<th" + thStyle + " colspan='60'>称号获得人数分布</th>");
		content.append("		</tr>");
		content.append("		<tr>");
		content.append("			<th" + thStyle + ">日期 </th>");
		content.append("			<th" + thStyle + ">初出茅庐 </th>");
		content.append("			<th" + thStyle + ">新手上路 </th>");
		content.append("			<th" + thStyle + ">扬帆起航 </th>");
		content.append("			<th" + thStyle + ">财大气粗 </th>");
		content.append("			<th" + thStyle + ">有福同享 </th>");
		content.append("			<th" + thStyle + ">慧眼识珠 </th>");
		content.append("			<th" + thStyle + ">初学乍练 </th>");
		content.append("			<th" + thStyle + ">熟能生巧 </th>");
		content.append("			<th" + thStyle + ">得心应手 </th>");
		content.append("			<th" + thStyle + ">精益求精 </th>");
		content.append("			<th" + thStyle + ">炉火纯青 </th>");
		content.append("			<th" + thStyle + ">出神入化 </th>");
		content.append("			<th" + thStyle + ">小试牛刀 </th>");
		content.append("			<th" + thStyle + ">势不可挡 </th>");
		content.append("			<th" + thStyle + ">乘风破浪 </th>");
		content.append("			<th" + thStyle + ">风雷涌动 </th>");
		content.append("			<th" + thStyle + ">雷霆万钧 </th>   ");
		content.append("			<th" + thStyle + ">龙威虎震 </th>");
		content.append("			<th" + thStyle + ">降龙伏虎 </th>");
		content.append("			<th" + thStyle + ">气吞山河 </th>");
		content.append("			<th" + thStyle + ">叱咤风云 </th>");
		content.append("			<th" + thStyle + ">盖世无双 </th>");
		content.append("			<th" + thStyle + ">惊天动地 </th>");
		content.append("			<th" + thStyle + ">初露锋芒 </th>");
		content.append("			<th" + thStyle + ">时来运转 </th>");
		content.append("			<th" + thStyle + ">运势渐佳 </th>");
		content.append("			<th" + thStyle + ">好运连连 </th>");
		content.append("			<th" + thStyle + ">顺风顺水 </th>");
		content.append("			<th" + thStyle + ">鸿运当头 </th>");
		content.append("			<th" + thStyle + ">洪福齐天 </th>");
		content.append("			<th" + thStyle + ">小有成就 </th>");
		content.append("			<th" + thStyle + ">崭露头角 </th>");
		content.append("			<th" + thStyle + ">略有小成 </th>");
		content.append("			<th" + thStyle + ">出类拔萃 </th>");
		content.append("			<th" + thStyle + ">不同凡响 </th>");
		content.append("			<th" + thStyle + ">一鸣惊人 </th>");
		content.append("			<th" + thStyle + ">大有所为 </th>");
		content.append("			<th" + thStyle + ">一举成名 </th>");
		content.append("			<th" + thStyle + ">硕果累累 </th>");
		content.append("			<th" + thStyle + ">功成名就 </th>");
		content.append("			<th" + thStyle + ">震古烁今 </th>");
		content.append("			<th" + thStyle + ">乐善好施 </th>");
		content.append("			<th" + thStyle + ">福杯满溢 </th>");
		content.append("			<th" + thStyle + ">仗义疏财 </th>");
		content.append("			<th" + thStyle + ">扶危济困 </th>");
		content.append("			<th" + thStyle + ">广结善缘 </th>");
		content.append("			<th" + thStyle + ">积善成德 </th>");
		content.append("			<th" + thStyle + ">默默无闻 </th>");
		content.append("			<th" + thStyle + ">引人注目 </th>");
		content.append("			<th" + thStyle + ">小有名气 </th>");
		content.append("			<th" + thStyle + ">远近闻名 </th>");
		content.append("			<th" + thStyle + ">声名远扬 </th>");
		content.append("			<th" + thStyle + ">大名鼎鼎 </th>");
		content.append("			<th" + thStyle + ">名满天下 </th>");
		content.append("			<th" + thStyle + ">初来乍到 </th>");
		content.append("			<th" + thStyle + ">再接再厉 </th>");
		content.append("			<th" + thStyle + ">坚持不懈 </th>");
		content.append("			<th" + thStyle + ">一如既往 </th>");
		content.append("			<th" + thStyle + ">持之以恒 </th>");
		content.append("		</tr>");
		content.append("	</thead>");
		content.append("	<tbody>");
		int i = 0;
		for (Map<String, Object> ad : data) {
			if (i++ % 2 == 0) {
				tempStyle = td1Style;
			} else {
				tempStyle = td2Style;
			}
			content.append("		<tr>");
			content.append("<td" + tempStyle + ">" + ad.get("currentDate").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuChuMaoLu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xinShouShangLu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yangFanQiHang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("caiDaQiCu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("youFuTongXiang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("huiYanShiZhu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuXueZhaLian").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shuNengShengQiao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("deXinYingShou").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jingYiQiuJing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("luHuoChunQing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuShenRuHua").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiaoShiNiuDao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shiBuKeDang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chengFengPoLang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fengLeiYongDong").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("leiTingWanJun").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("longWeiHuZhen").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiangLongFuHu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("qiTunShanHe").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chiChaFengYun").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("gaiShiWuShuang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jingTianDongDi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuLouFengMang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shiLaiYunZhuan").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yunShiJianJia").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("haoYunLianLian").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("ShunFengShunShui").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hongYunDangTou").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("hongFuQiTian").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiaoYouChengJiu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zhanLuTouJiao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("lueYouXiaoCheng").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuLeiBaCui").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("buTongFanXaing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yiMingJingRen").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("daYouSuoWei").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yiJuChengMing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shuoGuoLeiLei").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("gongChengMingJiu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zhenGuShuoJin").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("leShanHaoShi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fuBeiManYi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zhangYiShuCai").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("fuWeiJiKun").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("guangJieShanYuan").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jiShanChengDe").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("moMoWuWen").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yinRenZhuMu").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("xiaoYouMingQi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yuanJinWenMing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("shengMingYuanYang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("daMingDingDing").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("mingManTianXia").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chuLaiZhaDao").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("zaiJieZaiLi").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("jianChiBuxie").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("yiRuJiWang").toString() + "</td>");
			content.append("<td" + tempStyle + ">" + ad.get("chiZhiYiHeng").toString() + "</td>");
			content.append("		</tr>");
		}
		content.append("	</tbody>");
		content.append("</table>");
		return content.toString();
	}

}
