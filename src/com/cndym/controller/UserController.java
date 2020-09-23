package com.cndym.controller;

import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cndym.entity.Function;
import com.cndym.entity.company.ResourceAllocation;
import com.cndym.entity.user.User;
import com.cndym.entity.user.UserGroup;
import com.cndym.service.IResourceAllocationService;
import com.cndym.service.IUserGroupService;
import com.cndym.service.IUserService;
import com.cndym.util.Md5;
import com.cndym.util.PageView;
import com.cndym.util.ParsingPermissionUtil;
import com.cndym.util.SortListMethod;
import com.cndym.util.export.Utils;

/**
 * 
 * 
 * @author LiNa
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Resource
	private IUserService userServiceImpl;
	@Resource
	private IUserGroupService userGroupServiceImpl;
	@Resource
	private IResourceAllocationService resourceAllocationServiceImpl;

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * @Name: toLogonPage
	 * @Description:
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create 
	 * @Parameters:
	 * @Return: /WEB-INF/pages/layout/index.jsp
	 */
	@RequestMapping("/toLogonPage")
	public String toLogonPage() {
		logger.info("");
		userServiceImpl.getFunctionXml();
		return "/layout/index";
	}

	/**
	 * @Name: toUser
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:锟斤拷
	 * @Return: /WEB-INF/pages/user/userList.jsp
	 * 
	 *          Integer currentPage 
	 */

	@RequestMapping("/toUserList")
	public String toUserList(HttpServletRequest request, User user, Model model, Integer currentPage) {
		User userLogin = (User) request.getSession().getAttribute("LOGON_USER");
		String permission = "";
		if (userLogin != null) {
			UserGroup userGroup = userLogin.getUserGroup();
			permission = userGroup.getGroupPermissions();
		}
		if (!Utils.isNotEmpty(currentPage)) {
			currentPage = 1;
		}

		PageView pageView = null;
		if (isAdministrator(permission)) {
			pageView = userServiceImpl.queryPageUserList(user, currentPage);
			model.addAttribute("pageView", pageView);
			model.addAttribute("userDetial", user);
			return "/user/userList";
		}
		pageView = userServiceImpl.queryPageUserList(userLogin, currentPage);
		model.addAttribute("pageView", pageView);
		return "/user/userAdministratorList";
	}

	/**
	 * @Name: addUser
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create 
	 * @Parameters:
	 * @Return: toUserList.do
	 */
	@RequestMapping("/addUser")
	public String addUser(User u) {
		if (!Utils.isNotEmpty(u.getUserId())) {
			String code = String.valueOf(new Date().getTime());
			u.setUserCode(code);
			u.setCreateTime(new Date());
			u.setUserPassword(Md5.Md5(u.getUserPassword()));
		}
		u.setEditTime(new Date());
		userServiceImpl.saveOrUpdateUser(u);
		return "redirect:/user/toUserList.do";
	}

	/**
	 * @Name: toUserDetail
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:
	 * @Return: /WEB-INF/pages/user/userDetails.jsp
	 */
	@RequestMapping("/toUserDetail")
	public String toUserDetail(User user, Model model) {
		Long id = user.getUserId();
		Map<String, Object> userMap = new HashMap<String, Object>();
		if (id != null) {
			userMap.put("userId", id);
			User userDetail = userServiceImpl.queryByQuery(userMap);
			model.addAttribute("userDetail", userDetail);
		}

		List<UserGroup> groupList = getUserGroupList();
		model.addAttribute("groupList", groupList);

		List<ResourceAllocation> resourceList = sortResAllList(getResourceAllocation());

		model.addAttribute("resourceList", resourceList);

		return "/user/userDetails";
	}
	

	private List<ResourceAllocation> sortResAllList(List<ResourceAllocation> resourceList) {
		for (int i = 0; i < resourceList.size(); i++) {
			ResourceAllocation o = resourceList.get(i);
			String str = o.getResName();
			if (str.length() == 0)
				return null;
			String alphabet = str.substring(0, 1);
			if (alphabet.matches("[\\u4e00-\\u9fa5]+")) {
				str = SortListMethod.getAlphabet(str) + "&" + str;
				o.setResName(str);
				
			}
		}
		
		ComparatorChain multiSort = SortListMethod.sortList("resName", "id");
		Collections.sort(resourceList, multiSort);
		
		for (int i = 0; i < resourceList.size(); i++) {
			ResourceAllocation o = resourceList.get(i);
			String str = o.getResName();
			if (str.contains("&") && str.indexOf("&") == 1) {
				o.setResName(str.split("&")[1]);
				
			}
		}
		return resourceList;
	}

	/**
	 * @Name: ajaxDelete
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:
	 * @Return: toUserList.do
	 */
	@RequestMapping("/ajaxDelete")
	public void ajaxDelete(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.valueOf(request.getParameter("userId"));

		JSONObject resultObject = new JSONObject();
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			boolean flag = userServiceImpl.deleteById(id);
			if (flag) {
				resultObject.put("flag", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultObject.put("flag", true);
		}
		printWriter.print(resultObject.toString());
		printWriter.flush();
		printWriter.close();
	}

	/**
	 * @Name: getUserGroupList
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:
	 * @Return: toUserList.do
	 */
	public List<UserGroup> getUserGroupList() {
		List<UserGroup> groupList = userGroupServiceImpl.queryAllGroupList();
		return groupList;
	}

	/**
	 * @Name: getUserGroupList
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:
	 * @Return: toUserList.do
	 */
	public List<ResourceAllocation> getResourceAllocation() {
		Integer type = 1;
		List<ResourceAllocation> resourceList = resourceAllocationServiceImpl.queryAllResourceList(type);
		return resourceList;
	}

	/**
	 * @Name: getUserGroupList
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:
	 * @Return: /WEB-INF/pages/user/purviewGroupList.jsp
	 */
	@RequestMapping("/toPurviewGtoupList")
	public String toPurviewGtoupList(HttpServletRequest request, Model model, Integer currentPage) {

		if (!Utils.isNotEmpty(currentPage)) {
			currentPage = 1;
		}

		PageView pageView = userGroupServiceImpl.queryPageUserGroupList(currentPage);
		model.addAttribute("pageView", pageView);
		return "/user/purviewGroupList";
	}

	/**
	 * @Name: toPurviewGtoupDetail
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:
	 * @Return: /WEB-INF/pages/user/purviewGroupDetail.jsp
	 */
	@RequestMapping("/toPurviewGtoupDetail")
	public String toPurviewGtoupDetail(HttpServletRequest request, UserGroup group, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Long groupId = group.getGroupId();
		if (groupId != null) {
			paramMap.put("groupId", groupId);
			UserGroup userGroup = userGroupServiceImpl.queryByQuery(paramMap);
			model.addAttribute("userGroup", userGroup);
		}
		ParsingPermissionUtil parsing = new ParsingPermissionUtil();
		Collection<Function> functions = parsing.getIndexMenu(null);
		model.addAttribute("functionList", functions);

		return "/user/purviewGroupDetail";
	}

	/**
	 * @Name: addUserGroupAndPurview
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters:
	 * @Return: /WEB-INF/pages/user/purviewGroupDetail.jsp
	 */
	@RequestMapping("/addUserGroupAndPurview")
	public String addUserGroupAndPurview(UserGroup userGroup) {
		userGroup.setCreateTime(new Date());
		userGroupServiceImpl.saveOrUpdateUserGroup(userGroup);
		return "redirect:/user/toPurviewGtoupList.do";
	}

	/**
	 * @Name: ajaxDeleteUserGroup
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00
	 * @Create Date: 
	 * @Parameters: 
	 * @Return:
	 */
	@RequestMapping("/ajaxDeleteUserGroup")
	public void ajaxDeleteUserGroup(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.valueOf(request.getParameter("groupId"));
		JSONObject resultObject = new JSONObject();
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			String flag = userGroupServiceImpl.deleteGroupById(id);
			resultObject.put("flag", flag);
		} catch (Exception e) {
			e.printStackTrace();
			resultObject.put("flag", "删锟斤拷失锟斤拷");
		}
		printWriter.print(resultObject.toString());
		printWriter.flush();
		printWriter.close();

	}

	/**
	 * 
	 * @author camily 2016-04-21
	 * @param user
	 * @param model
	 * @return
	 */

	@RequestMapping("/toEditPassWord")
	public String toEditPassWord(User user, Model model) {
		Long id = user.getUserId();

		model.addAttribute("userId", id);

		return "/user/editPassWord";
	}

	@RequestMapping("/savePassWord")
	public String savePassWord(User user, Model model, String oldPassWord, String newPassWord, String newPassWord2) {
		Long id = user.getUserId();
		Map<String, Object> userMap = new HashMap<String, Object>();
		User userDetail = null;
		if (id == null) {

			model.addAttribute("msg", "UserId为锟秸ｏ拷锟斤拷系锟斤拷锟斤拷");
			return "/user/editPassWord";
		}
		userMap.put("userId", id);
		userDetail = userServiceImpl.queryByQuery(userMap);

		if (Utils.isNotEmpty(oldPassWord) && Utils.isNotEmpty(newPassWord) && Utils.isNotEmpty(newPassWord2)) {

			if (Md5.Md5(oldPassWord).equals(userDetail.getUserPassword())) {
				if (newPassWord.equals(newPassWord2)) {
					userDetail.setUserPassword(Md5.Md5(newPassWord));
					userServiceImpl.saveOrUpdateUser(userDetail);
					model.addAttribute("userId", id);
					model.addAttribute("msg", "锟睫改成癸拷");
					// model.addAttribute("oldPassWord", oldPassWord);
					// model.addAttribute("newPassWord", newPassWord);
					// model.addAttribute("newPassWord2", newPassWord2);
					return "redirect:/user/toUserList.do";

				} else {
					model.addAttribute("msg", "锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷值锟斤拷同锟斤拷");
					return "/user/editPassWord";
				}

			} else {
				model.addAttribute("msg", "原锟斤拷锟诫不锟斤拷确锟斤拷");
				return "/user/editPassWord";
			}

		}

		return "/user/editPassWord";
	}

}
