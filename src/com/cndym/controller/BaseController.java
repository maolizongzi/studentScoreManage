package com.cndym.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.cndym.entity.Function;
import com.cndym.entity.user.User;
import com.cndym.util.ParsingPermissionUtil;
import com.cndym.util.export.Utils;

public class BaseController {
	private Logger logger = Logger.getLogger(getClass());

	/**
	 * @Name: isSuper
	 * @Description: 是否是管理员,true:是管理员,flase:渠道用户
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月21日16:03:28
	 * @Parameters:无
	 * @Return: /WEB-INF/pages/data/dataInfoList.jsp
	 */
	public boolean isAdministrator(String permission) {
		// 1.判读该用户是渠道用户还是超级管理员用户
		// 权限不是所有的就是渠道用户,拿到所有的权限去比对，查看是普通用户还是超级管理员用户
		boolean falg = true;
		ParsingPermissionUtil parse = new ParsingPermissionUtil();
		Collection<Function> functions = parse.getIndexMenu(null);
		for (Function f : functions) {
			List<Function> childList = f.getChildFunctions();
			for (Function child : childList) {
				if (permission.indexOf("@" + child.getFunctionParentFlag()) == -1) {
					return false;
				}
			}
		}
		return falg;
	}

	// 简单日志记录  2016-04-22 camily
	public void writeLog(HttpServletRequest request, String content) {
		User user = (User) request.getSession().getAttribute("LOGON_USER");
		if (Utils.isNotEmpty(user))
			logger.info("用户:" + null == user.getUserName() ? null : user.getUserName() + "---> " + content);
		else
			logger.info("用户:" + null + "---> " + content);
	}
}
