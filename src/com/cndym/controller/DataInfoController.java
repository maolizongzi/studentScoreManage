package com.cndym.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.channel.Channel;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.user.User;
import com.cndym.entity.user.UserGroup;
import com.cndym.service.IChannelService;
import com.cndym.service.IDataInfoService;
import com.cndym.service.IUserService;
import com.cndym.util.PageView;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("/dataInfo")
public class DataInfoController extends BaseController {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private IChannelService channelServiceImpl;
	@Resource
	private IDataInfoService dataInfoServiceImpl;
	@Resource
	private IUserService userServiceImpl;

	/**
	 * @Name: toLogonPage
	 * @Description:根据传入的对象，实现增加功能
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月4日13:49:18
	 * @Parameters:无
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/toDataInfoListPage")
	public String toDataInfoListPage(HttpServletRequest request, Model model) {
		/**
		 * 渠道用户:<br/>
		 * 1.根据该用户的公司信息 去查所有的渠道信息 <br/>
		 * 2.该渠道只能查该用所拥有的数据类型<br/>
		 * 管理员用户:<br/>
		 * 能查询所有的渠道信息
		 */
		User user = (User) request.getSession().getAttribute("LOGON_USER");
		if (user != null) {
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("userCode", user.getUserCode());
			User userInfo = userServiceImpl.queryByQuery(userMap);
			UserGroup userGroup = userInfo.getUserGroup();
			// 权限
			String permission = userGroup.getGroupPermissions();
			// 所有的数据类型权限
			List<String> permissionList = new ArrayList<String>();
			if (permission.indexOf("fa") != -1) {
				permissionList.add("fa");
			}
			if (permission.indexOf("fb") != -1) {
				permissionList.add("fb");
			}
			if (permission.indexOf("fc") != -1) {
				permissionList.add("fc");
			}
			request.getSession().setAttribute("permissionList", permissionList);
			model.addAttribute("userInfo", userInfo);
			if (isAdministrator(permission)) {
				// 超级管理员用户，在查询数据的时候可以查询所有的sid信息
				model.addAttribute("isAdministratorFlag", 1);
				return "/data/dataInfoAdministratorList";
			} else {
				// 渠道用户只能查询该公司下的sid信息
				String companyCode = userInfo.getCompanyCode();
				List<Channel> channelList = channelServiceImpl
						.queryChannelListByCompanyCode(companyCode);
				model.addAttribute("channelList", channelList);
				model.addAttribute("isAdministratorFlag", 0);
				return "/data/dataInfoList";
			}
		}
		model.addAttribute("isAdministratorFlag", 0);
		return "/data/dataInfoList";
	}

	/**
	 * @Name: queryData
	 * @Description: 查询
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月4日13:49:18
	 * @Parameters:无
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/queryData")
	public String queryData(HttpServletRequest request, DataInfo dataInfo,
			Model model) {
		String permissionType = request.getParameter("permissionType");
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		String administratorType = request.getParameter("administratorType");
		PageView pageView = dataInfoServiceImpl.queryPageList(dataInfo,
				currentPage, permissionType,administratorType);
		User user = (User) request.getSession().getAttribute("LOGON_USER");

		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("userCode", user.getUserCode());
		User userInfo = userServiceImpl.queryByQuery(userMap);

		String companyCode = userInfo.getCompanyCode();
		model.addAttribute("permissionType", permissionType);
		model.addAttribute("dataInfo", dataInfo);
		model.addAttribute("pageView", pageView);
		model.addAttribute("userInfo", userInfo);
		if (Utils.isEmpty(administratorType)) {
			List<Channel> channelList = channelServiceImpl
					.queryChannelListByCompanyCode(companyCode);
			model.addAttribute("channelList", channelList);
			model.addAttribute("isAdministratorFlag", 0);
			return "/data/dataInfoList";
		}
		model.addAttribute("isAdministratorFlag", 1);
		return "/data/dataInfoAdministratorList";
	}

	/**
	 * @Name: ajaxSubscribeNameBySid
	 * @Description: 根据sid和数据类型得到订阅报表的名称
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月21日13:59:31
	 * @Parameters:无
	 * @Return: 无
	 */
	@RequestMapping("/ajaxSubscribeNameBySid")
	public void ajaxSubscribeNameBySid(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter printWriter = null;
		JSONObject resultObject = new JSONObject();
		try {
			String sid = request.getParameter("sid");
			Channel channel = channelServiceImpl.getChannelBySid(sid);
			String subscribeName = channel.getName();
			printWriter = response.getWriter();
			resultObject.put("subscribeName", subscribeName);
		} catch (Exception e) {
			resultObject.put("subscribeName", "error");
			logger.error("根据sid和数据类型得到订阅报表的名称出错", e);
		}
		printWriter.print(resultObject.toString());
		printWriter.flush();
		printWriter.close();
	}
}
