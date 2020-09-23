package com.cndym.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cndym.dao.IUserGroupDao;
import com.cndym.entity.user.UserGroup;
import com.cndym.util.BuilderSql;
import com.cndym.util.PageView;

@Repository
public class UserGroupDaoImpl extends BaseDaoImpl<UserGroup> implements
		IUserGroupDao {
	@Override
	public UserGroup queryByQuery(Map<String, Object> groupMap) {
		BuilderSql bs = new BuilderSql(UserGroup.class);
		if (groupMap.get("groupId") != null) {
			Long groupId = (Long) groupMap.get("groupId");
			bs.addWhereClause("groupId=?", groupId);
		}
		List<UserGroup> groupList = this.getList(bs);
		if (groupList != null) {
			return groupList.get(0);
		}
		return null;
	}
	@Override
	public PageView queryPageUserGroupList( int currentPage) {
		BuilderSql bs = new BuilderSql(UserGroup.class);
		PageView view =this.getPageView(bs, currentPage);
		return view;
	}
}
