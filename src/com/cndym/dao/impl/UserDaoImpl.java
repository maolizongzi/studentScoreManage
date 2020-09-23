package com.cndym.dao.impl;

import java.util.List;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cndym.dao.IUserDao;
import com.cndym.entity.user.User;
import com.cndym.util.BuilderSql;
import com.cndym.util.PageView;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	@Override
	public List<User> queryByQuery(Map<String, Object> userMap) {
		BuilderSql bs = new BuilderSql(User.class);
		if (userMap.get("userId") != null) {
			Long userId = (Long) userMap.get("userId");
			bs.addWhereClause("userId=?", userId);
		}
		if (userMap.get("userName") != null) {
			String userName = (String) userMap.get("userName");
			bs.addWhereClause("userName=?", userName);
		}
		if (userMap.get("userCode") != null) {
			String userCode = (String) userMap.get("userCode");
			bs.addWhereClause("userCode=?", userCode);
		}
		if (userMap.get("userPassword") != null) {
			String userPassword = (String) userMap.get("userPassword");
			bs.addWhereClause("userPassword=?", userPassword);
		}
		if (userMap.get("groupId") != null) {
			Long groupId = (Long) userMap.get("groupId");
			bs.addWhereClause("groupId=?", groupId);
		}
		// 根据builderSql查询，返回集合
		List<User> userList = this.getList(bs);
		if (userList != null && userList.size() > 0) {
			return userList;
		}
		return null;
	}

	@Override
	public PageView queryPageUserList(User user,  int currentPage) {
		BuilderSql bs = new BuilderSql(User.class);
		String userName = user.getUserName();
		String realName = user.getRealName();
		String userCompanyName = user.getUserCompanyName();
		String userDepartmentName = user.getUserDepartmentName();

		if (StringUtils.isNotBlank(userName)) {
			bs.addWhereClause("userName=?", userName);
		}
		if (StringUtils.isNotBlank(realName)) {
			bs.addWhereClause("realName=?", realName);
		}
		if (StringUtils.isNotBlank(userCompanyName)) {
			bs.addWhereClause("userCompanyName=?", userCompanyName.trim());
		}
		if (StringUtils.isNotBlank(userDepartmentName)) {
			bs.addWhereClause("userDepartmentName=?", userDepartmentName.trim());
		}

		PageView view = this.getPageView(bs, currentPage);
		return view;
	}
}
