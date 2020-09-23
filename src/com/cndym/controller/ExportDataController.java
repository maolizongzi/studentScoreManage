package com.cndym.controller;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Array;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.user.User;
import com.cndym.entity.user.UserGroup;
import com.cndym.service.IDataInfoService;
import com.cndym.util.Config;
import com.cndym.util.export.FilesUtils;
import com.cndym.util.export.ListToString;
import com.cndym.util.export.MakeTxt;
import com.cndym.util.export.MakeXlsx;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("/export")
public class ExportDataController extends BaseController {
	@Resource
	private IDataInfoService dataInfoServiceImpl;

	@RequestMapping("/xlsx")
	@ResponseBody
	public void toExportFile(HttpServletRequest request, HttpServletResponse response, DataInfo dataInfo, Model model,
			Integer exportType) {

		String permissionType = request.getParameter("permissionType");
		List<DataInfo> list = getExportList(request, dataInfo, permissionType);

		String str = request.getSession().getServletContext().getRealPath("/");
		String tempPath = null;
		String filePath = null;

		
		if (Utils.isNotEmpty(list) && list.size() <= 3000) {
			if (Config.ALL_DATA.equals(permissionType)) { // 所有数据
				tempPath = str + "WEB-INF" + File.separator + "xls" + File.separator + "ALLDATA.xls";
				filePath = str + "WEB-INF" + File.separator + "xls"+ File.separator + "xlsDown" + File.separator + "ALLDATA"
						+ Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".xls";
				writeLog(request, "导出综合数据，格式为 .xls");

			} else if (Config.CHONGZHI_DATA.equals(permissionType)) {// 充值数据
				tempPath = str + "WEB-INF" + File.separator + "xls" + File.separator + "CHONGZHIDATA.xls";
				filePath = str + "WEB-INF" + File.separator + "xls" + File.separator + "xlsDown"+ File.separator + "CHONGZHIDATA"
						+ Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".xls";
				writeLog(request, "导出充值数据，格式为 .xls");

			} else if (Config.TOUZHU_DATA.equals(permissionType)) {// 投注数据
				tempPath = str + "WEB-INF" + File.separator + "xls" + File.separator + "TOUZHUDATA.xls";
				filePath = str + "WEB-INF" + File.separator + "xls" + File.separator + "xlsDown"+ File.separator + "TOUZHUDATA"
						+ Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".xls";
				writeLog(request, "导出投注数据，格式为 .xls");
			}
			// if (Utils.isNotEmpty(filePath) && Utils.isNotEmpty(list) &&
			// Utils.isNotEmpty(tempPath)) {
			// MakeXlsx.toXls(list, tempPath, filePath, response);
			// writeLog(request, "导出格式为 .xls数据，成功");
			//
			// }
			List<Map<String, Object>> listMap = list2MapList(list);
			// 数据格式化
			 dataFormt(listMap);
			MakeXlsx.toXls(listMap, tempPath, filePath, response);
			writeLog(request, "导出格式为 .xls数据，成功");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("error", "查询数据为空或包含数据项大于限定条数，请更换查询条件");
			List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
			list1.add(map);

			tempPath = str + "WEB-INF" + File.separator + "xls" + File.separator + "error.xls";
			filePath = str + "WEB-INF/xls/error" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".xls";
			MakeXlsx.toXls(list1, tempPath, filePath, response);
			writeLog(request, "导出格式为 .xls数据，成功");
		}

		// MakeXlsx.toXls(list, tempPath, filePath, response);
		// writeLog(request, "导出格式为 .xls数据，成功");

	}

	private  List<Map<String, Object>> list2MapList(List<DataInfo> list) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < list.size(); i++) {
			mapList.add(Utils.transBean2Map(list.get(i)));
		}
		return mapList;
	}

	private void dataFormt(List<Map<String, Object>> list) {
		Map<String, Object> map = null;
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				if (null != map.get("CHONGZHIALLMONEY")) {
					map.put("CHONGZHIALLMONEY",
							Utils.formatNumberZ(Double.parseDouble(map.get("CHONGZHIALLMONEY").toString())));
				} else {
					map.put("CHONGZHIALLMONEY", 0.00);
				}

				if (null != map.get("USERAVGCHONGZHIMONEY")) {
					map.put("USERAVGCHONGZHIMONEY",
							Utils.formatNumberZ(Double.parseDouble(map.get("USERAVGCHONGZHIMONEY").toString())));
				} else {
					map.put("USERAVGCHONGZHIMONEY", 0.00);
				}

				if (null != map.get("TOUZHUALLMONEY")) {
					map.put("TOUZHUALLMONEY",
							Utils.formatNumberZ(Double.parseDouble(map.get("TOUZHUALLMONEY").toString())));
				} else {
					map.put("TOUZHUALLMONEY", 0.00);
				}

				if (null != map.get("CHONGZHINEWUSERMONERY")) {
					map.put("CHONGZHINEWUSERMONERY",
							Utils.formatNumberZ(Double.parseDouble(map.get("CHONGZHINEWUSERMONERY").toString())));
				} else {
					map.put("CHONGZHINEWUSERMONERY", 0.00);
				}

				if (null != map.get("CHONGZHINEWUSERAVGMONEY")) {
					map.put("CHONGZHINEWUSERAVGMONEY",
							Utils.formatNumberZ(Double.parseDouble(map.get("CHONGZHINEWUSERAVGMONEY").toString())));
				} else {
					map.put("CHONGZHINEWUSERAVGMONEY", 0.00);
				}

				if (null != map.get("TOUZHUNEWUSERMONERY")) {
					map.put("TOUZHUNEWUSERMONERY",
							Utils.formatNumberZ(Double.parseDouble(map.get("TOUZHUNEWUSERMONERY").toString())));
				} else {
					map.put("TOUZHUNEWUSERMONERY", 0.00);
				}

				if (null != map.get("TOUZHUNEWUSERARPU")) {
					map.put("TOUZHUNEWUSERARPU",
							Utils.formatNumberZ(Double.parseDouble(map.get("TOUZHUNEWUSERARPU").toString())));
				} else {
					map.put("TOUZHUNEWUSERARPU", 0.00);
				}

				if (null != map.get("TOUZHUALLUSERARPU")) {
					map.put("TOUZHUALLUSERARPU",
							Utils.formatNumberZ(Double.parseDouble(map.get("TOUZHUALLUSERARPU").toString())));
				} else {
					map.put("TOUZHUALLUSERARPU", 0.00);
				}
				if (null != map.get("GRANTS")) {
					map.put("GRANTS", Utils.formatNumberZ(Double.parseDouble(map.get("GRANTS").toString())));
				} else {
					map.put("GRANTS", 0.00);
				}

				if (null != map.get("GRANTSCONSUME")) {
					map.put("GRANTSCONSUME",
							Utils.formatNumberZ(Double.parseDouble(map.get("GRANTSCONSUME").toString())));
				} else {
					map.put("GRANTSCONSUME", 0.00);
				}

				if (null != map.get("CHONGZHINEWUSERRATE")) {
					map.put("CHONGZHINEWUSERRATE",
							Utils.formatNumberZ(Double.parseDouble(map.get("CHONGZHINEWUSERRATE").toString())) + "%");
				} else {
					map.put("CHONGZHINEWUSERRATE", "0.00%");
				}
				if (null != map.get("TOUZHUNEWUSERRATE")) {
					map.put("TOUZHUNEWUSERRATE",
							Utils.formatNumberZ(Double.parseDouble(map.get("TOUZHUNEWUSERRATE").toString())) + "%");
				} else {
					map.put("TOUZHUNEWUSERRATE", "0.00%");
				}

			}

		}
	}

	@RequestMapping("/txt")
	@ResponseBody
	public void toExportFileTxt(HttpServletRequest request, HttpServletResponse response, DataInfo dataInfo, Model model) {

		String permissionType = request.getParameter("permissionType");
		List<DataInfo> list = getExportList(request, dataInfo, permissionType);

		String str = request.getSession().getServletContext().getRealPath("/");
		String res = null;
		String filePath = null;
		if (Utils.isNotEmpty(list)) {
			if (Config.ALL_DATA.equals(permissionType)) { // 所有数据
				String[] str1 = { "dataRecordTime", "channelName", "jiHuo", "dau", "registeredUserCount", "chongZhiUserCount",
						"chongZhiAllMoney", "userAvgChongZhiMoney", "chongZhiNewUserMonery", "chongZhiNewUserCount",
						"chongZhiNewUserAvgMoney", "chongZhiNewUserRate", "touZhuUserCount", "touZhuAllMoney",
						"touZhuAllUserArpu", "touZhuNewUserCount", "touZhuNewUserMonery", "touZhuNewUserRate",
						"touZhuNewUserArpu", "grants", "grantsConsume" };

				String str2 = "%tF,%s,%d,%d,%d,%d,%.2f,%.2f,%.2f,%d,%.2f,%.2f%%,%d,%.2f,%.2f,%d,%.2f,%.2f%%,%.2f,%.2f,%.2f \n";

				res = ListToString.list2String(list, str2, str1);
				filePath = str + "WEB-INF/txt/ALLDATA" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".txt";
				writeLog(request, "导出综合数据，格式为 .txt");

			} else if (Config.CHONGZHI_DATA.equals(permissionType)) {// 充值数据
				String[] str1 = { "dataRecordTime", "channelName", "jiHuo", "registeredUserCount", "chongZhiUserCount",
						"chongZhiAllMoney", "chongZhiNewUserCount", "chongZhiNewUserMonery", "chongZhiNewUserRate",
						"userAvgChongZhiMoney", "chongZhiNewUserAvgMoney", "grants" };

				String str2 = "%tF,%s,%d,%d,%d,%.2f,%d,%.2f,%.2f%%,%.2f,%.2f,%.2f \n";

				res = ListToString.list2String(list, str2, str1);
				filePath = str + "WEB-INF/txt/CHONGZHIDATA" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss")
						+ ".txt";
				writeLog(request, "导出充值数据，格式为 .txt");

			} else if (Config.TOUZHU_DATA.equals(permissionType)) {// 投注数据
				String[] str1 = { "dataRecordTime", "channelName", "jiHuo", "registeredUserCount", "touZhuUserCount",
						"touZhuAllMoney", "touZhuNewUserCount", "touZhuNewUserMonery", "touZhuNewUserRate",
						"touZhuAllUserArpu", "touZhuNewUserArpu", "grantsConsume" };
				String str2 = "%tF,%s,%d,%d,%d,%.2f,%d,%.2f,%.2f%%,%.2f,%.2f,%.2f \n";

				res = ListToString.list2String(list, str2, str1);
				filePath = str + "WEB-INF/txt/TOUZHUDATA" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".txt";
				writeLog(request, "导出投注数据，格式为 .txt");
			}

		}
		if (!Utils.isNotEmpty(res)) {
			res = "查询数据为空";
		}

		if (!Utils.isNotEmpty(filePath)) {
			filePath = str + "WEB-INF/txt/error" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".txt";
		}

		// if (Utils.isNotEmpty(filePath) ) {
		MakeTxt.getFileExists(str + "WEB-INF" + File.separator + "txt");
		MakeTxt.contentToTxt(filePath, res, true);
		FilesUtils.downLoad(filePath, response);
		writeLog(request, "导出格式为 .txt数据成功");
		// }

	}

	@RequestMapping("/csv")
	@ResponseBody
	public void toExportFileCsv(HttpServletRequest request, HttpServletResponse response, DataInfo dataInfo, Model model) {

		String permissionType = request.getParameter("permissionType");
		List<DataInfo> list = getExportList(request, dataInfo, permissionType);
		String str = request.getSession().getServletContext().getRealPath("/");
		String res = null;
		String filePath = null;
		if (Utils.isNotEmpty(list)) {
			if (Config.ALL_DATA.equals(permissionType)) { // 所有数据
				String[] str1 = { "dataRecordTime", "channelName", "jiHuo", "dau", "registeredUserCount", "chongZhiUserCount",
						"chongZhiAllMoney", "userAvgChongZhiMoney", "chongZhiNewUserMonery", "chongZhiNewUserCount",
						"chongZhiNewUserAvgMoney", "chongZhiNewUserRate", "touZhuUserCount", "touZhuAllMoney",
						"touZhuAllUserArpu", "touZhuNewUserCount", "touZhuNewUserMonery", "touZhuNewUserRate",
						"touZhuNewUserArpu", "grants", "grantsConsume" };
				String str2 = "\"%tF\",\"%s\",\"%d\",\"%d\",\"%d\",\"%d\",\"%.2f\",\"%.2f\",\"%.2f\",\"%d\",\"%.2f\",\"%.2f%%\",\"%d\",\"%.2f\",\"%.2f\",\"%d\",\"%.2f\",\"%.2f%%\",\"%.2f\",\"%.2f\",\"%.2f\" \n";
				res = ListToString.list2String(list, str2, str1);
				filePath = str + "WEB-INF/csv/ALLDATA" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".csv";

				writeLog(request, "导出综合数据，格式为 .csv");

			} else if (Config.CHONGZHI_DATA.equals(permissionType)) {// 充值数据
				String[] str1 = { "dataRecordTime", "channelName", "jiHuo", "registeredUserCount", "chongZhiUserCount",
						"chongZhiAllMoney", "chongZhiNewUserCount", "chongZhiNewUserMonery", "chongZhiNewUserRate",
						"userAvgChongZhiMoney", "chongZhiNewUserAvgMoney", "grants" };

				String str2 = "\"%tF\",\"%s\",\"%d\",\"%d\",\"%d\",\"%.2f\",\"%d\",\"%.2f\",\"%.2f%%\",\"%.2f\",\"%.2f\",\"%.2f\" \n";
				res = ListToString.list2String(list, str2, str1);
				filePath = str + "WEB-INF/csv/CHONGZHIDATA" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss")
						+ ".csv";

				writeLog(request, "导出充值数据，格式为 .csv");

			} else if (Config.TOUZHU_DATA.equals(permissionType)) {// 投注数据
				String[] str1 = { "dataRecordTime", "channelName", "jiHuo", "registeredUserCount", "touZhuUserCount",
						"touZhuAllMoney", "touZhuNewUserCount", "touZhuNewUserMonery", "touZhuNewUserRate",
						"touZhuAllUserArpu", "touZhuNewUserArpu", "grantsConsume" };

				String str2 = "\"%tF\",\"%s\",\"%d\",\"%d\",\"%d\",\"%.2f\",\"%d\",\"%.2f\",\"%.2f%%\",\"%.2f\",\"%.2f\",\"%.2f\" \n";

				res = ListToString.list2String(list, str2, str1);
				filePath = str + "WEB-INF/csv/TOUZHUDATA" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".csv";

				writeLog(request, "导出投注数据，格式为 .csv");

			}

			// if (Utils.isNotEmpty(filePath) && Utils.isNotEmpty(res)) {
			// MakeTxt.getFileExists(str + "WEB-INF" + File.separator + "csv");
			// MakeTxt.contentToTxt(filePath, res, true);
			// FilesUtils.downLoad(filePath, response);
			// writeLog(request, "导出格式为 .csv数据成功");
			// }
		}

		if (!Utils.isNotEmpty(res)) {
			res = "\"查询数据为空\"";
		}

		if (!Utils.isNotEmpty(filePath)) {
			filePath = str + "WEB-INF/csv/error" + Utils.formatDate2Str(new Date(), "yyyyMMddHHmmss") + ".cvs";
		}

		// if (Utils.isNotEmpty(filePath) ) {
		MakeTxt.getFileExists(str + "WEB-INF" + File.separator + "csv");
		MakeTxt.contentToTxt(filePath, res, true);
		FilesUtils.downLoad(filePath, response);
		writeLog(request, "导出格式为 .csv数据成功");
		// }

	}

	// 查询需要导出的数据；返回一个List
	private List<DataInfo> getExportList(HttpServletRequest request, DataInfo dataInfo, String permissionType) {
		User user = (User) request.getSession().getAttribute("LOGON_USER");
		
		if (Utils.isNotEmpty(user)) {

			String strAdmin = "";
			UserGroup userGroup = user.getUserGroup();
			// 权限
			String permission = userGroup.getGroupPermissions();
			if (isAdministrator(permission)) {
				strAdmin = "admin";
			}

			List<DataInfo> list = dataInfoServiceImpl.getExportDataList(dataInfo, permissionType,strAdmin);
			return list;
		}
		return null;
	}

}
