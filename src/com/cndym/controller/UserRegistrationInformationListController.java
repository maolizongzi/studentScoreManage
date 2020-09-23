package com.cndym.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.channel.Channel;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.service.IChannelService;
import com.cndym.service.IUserRegistrationInformationListService;
import com.cndym.util.PageView;

@Controller
@RequestMapping("/userRate")
public class UserRegistrationInformationListController extends BaseController {
	@Resource
	private IChannelService channelServiceImpl;
	@Resource
	private IUserRegistrationInformationListService userRegistrationInformationListServiceImpl;

	
	/**
	 * @Name: toUserRateListPage
	 * @Description: 到达新用户保留率页面
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年12月29日10:18:12
	 * @Parameters:无
	 * @Return: /WEB-INF/pages/userRate/userRateList.jsp
	 */
	@RequestMapping("/toUserRateListPage")
	public String toUserRateListPage(HttpServletRequest request, Model model) {
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userRateList";
	}
	
	/**
	 * @Name: toUserRateListPage
	 * @Description: 到达登录用户保留率页面
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年12月29日10:18:12
	 * @Parameters:无
	 * @Return: /WEB-INF/pages/userRate/userRateList.jsp
	 */
	@RequestMapping("/toUserLoginRateListPage")
	public String toUserLoginRateListPage(HttpServletRequest request, Model model) {
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userLoginRateList";
	}
	
	
	/**
	 * @Name: queryDataByUserRegistration
	 * @Description: 新用户查询
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月4日13:49:18
	 * @Parameters:无
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/queryDataByUserRegistration")
	public String queryDataByUserRegistration(HttpServletRequest request, UserRegistrationInformationList userRegistraList,
			Model model) {
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		
		userRegistraList.setIsRegistrStatus(1);
		String startTime=request.getParameter("startTime");
		String endTime= request.getParameter("endTime");
		PageView pageView = userRegistrationInformationListServiceImpl.queryPageList(userRegistraList, currentPage,startTime,endTime);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		model.addAttribute("userRegistraList", userRegistraList);
		model.addAttribute("pageView", pageView);
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userRateList";
	}

	
	/**
	 * @Name: queryDataByUserRegistrationLogin
	 * @Description: 登录用户查询
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月4日13:49:18
	 * @Parameters:无
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	@RequestMapping("/queryDataByUserRegistrationLogin")
	public String queryDataByUserRegistrationLogin(HttpServletRequest request, UserRegistrationInformationList userRegistraList,
			Model model) {
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		userRegistraList.setIsRegistrStatus(2);
		String startTime=request.getParameter("startTime");
		String endTime= request.getParameter("endTime");
		PageView pageView = userRegistrationInformationListServiceImpl.queryPageList(userRegistraList, currentPage,startTime,endTime);
		model.addAttribute("userRegistraList", userRegistraList);
		model.addAttribute("pageView", pageView);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		List<Channel> channelList= channelServiceImpl.getChannelList(null, null, null);
		model.addAttribute("channelList",channelList);
		return "/userRate/userLoginRateList";
	}
}
