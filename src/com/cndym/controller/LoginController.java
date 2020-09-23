package com.cndym.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.Function;
import com.cndym.entity.user.User;
import com.cndym.service.IUserService;
import com.cndym.util.Md5;
import com.cndym.util.ParsingPermissionUtil;
import com.cndym.util.export.Utils;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	@Resource
	private IUserService userServiceImpl;
	

	/**
	 * @Name: toLogonPage
	 * @Description:根据传入的对象，实现增加功能
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月4日13:49:18
	 * @Parameters:无
	 * @Return: 到达用户登录页面
	 */
	@RequestMapping("/toHome")
	public String toHome(HttpServletRequest request) {
		User userOld = (User) request.getSession().getAttribute("LOGON_USER");
		if (Utils.isNotEmpty(userOld)) {
			splitrules(request, userOld);
			return "/layout/index";
		}
		return "/login/login";
	}

	/**
	 * @Name: loginOut
	 * @Description: 退出
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月4日13:49:18
	 * @Parameters:无
	 * @Return:
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request) {
		request.getSession().getAttribute("LOGON_USER");
		request.getSession().invalidate();
		request.setAttribute("ctx", request.getContextPath());
		return "/login/login";
	}

	/**
	 * @Name: login
	 * @Description: 用户登录，验证用户名，密码，该用户所对应的权限
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月28日17:10:35
	 * @Parameters: 用户登录信息
	 * @Return: 登陆成功后的页面 /WEB-INF/pages/layout/index.jsp
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request,User user,String code,Model model) {
		
	
		User userOld = (User) request.getSession().getAttribute("LOGON_USER");
		if (Utils.isNotEmpty(userOld)) {
			splitrules(request, userOld);
			return "/layout/index";
		}
		
		
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		userPassword=Md5.Md5(userPassword);
		if(request.getSession().getAttribute("rCode")==null || !request.getSession().getAttribute("rCode").equals(code)){
			return "/login/login";
		}
		userMap.put("userName", userName);
		userMap.put("userPassword", userPassword);
		User getUser = userServiceImpl.queryByQuery(userMap);
		
		if(Utils.isNotEmpty(getUser)){
		 splitrules(request, getUser);
		 request.getSession().setAttribute("LOGON_USER", getUser);
			return "/layout/index";
		}
		
		
		
		model.addAttribute("userName", userName);
		model.addAttribute("userPassword", userPassword);
		model.addAttribute("mes","用户名或密码错误！");
		return "/login/login";	
	}

	public String splitrules(HttpServletRequest request, User user) {
		// 拆分权限标识
		String permissions = user.getUserGroup().getGroupPermissions();
		String[] permissionsArray = permissions.split("@");
		Set<String> permissionSet = new HashSet<String>();
		for (String string : permissionsArray) {
			permissionSet.add(string);
		}
		ParsingPermissionUtil parsing = new ParsingPermissionUtil();
		Collection<Function> functions = parsing.getIndexMenu(permissionSet);
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("functions", functions);
		return permissions;
	}
}
