package com.cndym.controller;

import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.report.BookReport;
import com.cndym.entity.user.User;
import com.cndym.service.IBookReportService;
import com.cndym.util.PageView;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("/bookReport")
public class BookReportController extends BaseController {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private IBookReportService bookReportServiceImpl;

	/**
	 * @author 程禄元 20160414
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/toBookReportList")
	public String toBookReportList(HttpServletRequest request, Model model,
			BookReport bookReport, Integer currentPage) {

		if (!Utils.isNotEmpty(currentPage)) {
			currentPage = 1;
		}

		User user = (User) request.getSession().getAttribute("LOGON_USER");

		if (!Utils.isNotEmpty(user) || !Utils.isNotEmpty(user.getUserCode())) {

			return "/login/login";
		}

		bookReport.setUserCode(user.getUserCode());
		bookReport.setDelStatus(0);
		PageView pageView = bookReportServiceImpl.queryPageBookReportList(
				bookReport, currentPage);
		model.addAttribute("pageView", pageView);
		model.addAttribute("bookReport", bookReport);
		model.addAttribute("email", user.getUserEmail());
		return "/report/bookReportList";
	}

	/**
	 * 删除订阅
	 */
	@RequestMapping("/ajaxDeleteReport")
	public void ajaxDelete(HttpServletRequest request,
			HttpServletResponse response) {
		Long id = Long.valueOf(request.getParameter("reportId"));

		JSONObject resultObject = new JSONObject();
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			boolean flag = bookReportServiceImpl.updateBookReport(id);
			if (flag) {
				resultObject.put("flag", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultObject.put("flag", false);
		}
		printWriter.print(resultObject.toString());
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * 添加订阅<br/>
	 * 根据订阅名称，订阅者Code，查询订阅条件是否存在<br/>
	 * 如果存在就不让添加,提示已经订阅过了！ 否则可以订阅
	 */
	@RequestMapping("/addReport")
	public String addReport(HttpServletRequest request, BookReport bookReport,
			Model model) {
		try {
			User user = (User) request.getSession().getAttribute("LOGON_USER");
			if (user != null) {
				String code = user.getUserCode();
				bookReport.setUserCode(code);
				bookReportServiceImpl.saveReport(bookReport);
			}
		} catch (Exception e) {
			logger.error("添加订阅失败", e);
		}
		return "redirect:/bookReport/toBookReportList.do";
	}
	@RequestMapping("/ajaxRePortByCodeAndName")
	public void ajaxRePortByCodeAndName(HttpServletRequest request,
			BookReport bookReport, HttpServletResponse response) {
		PrintWriter printWriter = null;
		JSONObject object = new JSONObject();
		try {
			printWriter = response.getWriter();
			User user = (User) request.getSession().getAttribute("LOGON_USER");
			if (user != null) {
				String code = user.getUserCode();
				bookReport.setUserCode(code);
				String name = request.getParameter("name");
				name=URLDecoder.decode(name,"utf-8");
				int count = bookReportServiceImpl.queryReportByCodeAndName(
						name, code);
				if (count >0) {
					object.put("flag", "已经是订阅状态啦！");
				} else {
					object.put("flag", "true");
				}
			}
		} catch (Exception e) {
			logger.error("根据userCOde和订阅名称查询是否已经订阅过", e);
		}
		printWriter.print(object.toString());
		printWriter.flush();
		printWriter.close();
	}
}
