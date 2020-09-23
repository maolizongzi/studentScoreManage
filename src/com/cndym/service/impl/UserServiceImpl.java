package com.cndym.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.IResourceAllocationDao;
import com.cndym.dao.IUserDao;
import com.cndym.dao.IUserGroupDao;
import com.cndym.entity.company.ResourceAllocation;
import com.cndym.entity.user.User;
import com.cndym.entity.user.UserGroup;
import com.cndym.service.IUserService;
import com.cndym.util.Config;
import com.cndym.util.PageView;
import com.cndym.util.ParsingPermissionUtil;
import com.cndym.util.export.Utils;

@Service("userServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;
	@Resource
	private IUserGroupDao userGroupDao;
	@Resource
	private IResourceAllocationDao resourceAllocationDao;

	public void getFunctionXml() {
		Set<String> permissionSet = new HashSet<String>();
		permissionSet.add("a");
		permissionSet.add("aa");
		permissionSet.add("b");
		permissionSet.add("ba");
		permissionSet.add("baa");
		permissionSet.add("c");
		permissionSet.add("ca");
		permissionSet.add("caa");
		permissionSet.add("cb");
		permissionSet.add("cba");
		permissionSet.add("cc");
		permissionSet.add("cca");
		permissionSet.add("d");
		permissionSet.add("da");
		permissionSet.add("daa");
		permissionSet.add("db");
		permissionSet.add("dba");

		ParsingPermissionUtil parsing = new ParsingPermissionUtil();
		/*
		 * Collection<Function> functionConllection = parsing
		 * .getIndexMenu(permissionSet);
		 */
		parsing.getIndexMenu(permissionSet);
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public User queryByQuery(Map<String, Object> userMap) {
		List<User> userList = userDao.queryByQuery(userMap);
		if (userList != null && userList.size() > 0) {
			User user = userList.get(0);
			Map<String, Object> groupMap = new HashMap<String, Object>();
			groupMap.put("groupId", user.getGroupId());
			UserGroup userGroup = userGroupDao.queryByQuery(groupMap);
			user.setUserGroup(userGroup);

			groupMap.put("code", user.getCompanyCode());
			groupMap.put("resType", Config.COM_TYPE);
			ResourceAllocation allocation = resourceAllocationDao.queryByQuery(groupMap);
			user.setResourceAllocation(allocation);
			return user;
		}
		return null;
	}

	/**
	 * @author camily 删除一些常量 2016.4.20
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public PageView queryPageUserList(User user, int currentPage) {

		PageView pageView = userDao.queryPageUserList(user, currentPage);
		List itemList = pageView.getItems();
		if (itemList != null && itemList.size() > 0) {
			Map<String, Object> groupMap = new HashMap<String, Object>();
			for (Object obj : itemList) {
				User u = (User) obj;
				groupMap.put("groupId", u.getGroupId());
				UserGroup userGroup = userGroupDao.queryByQuery(groupMap);
				u.setUserGroup(userGroup);

				groupMap.put("code", u.getCompanyCode());
				groupMap.put("resType", 1);
				ResourceAllocation allocation = resourceAllocationDao.queryByQuery(groupMap);
				u.setResourceAllocation(allocation);
			}
		}
		return pageView;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public boolean deleteById(Long id) {
		boolean falg = false;
		try {
			userDao.delete(id);
			falg = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdateUser(User u) {
		try {

			Map<String, Object> groupMap = new HashMap<String, Object>();

			groupMap.put("code", u.getCompanyCode());
			groupMap.put("resType", Config.COM_TYPE);
			ResourceAllocation allocation = resourceAllocationDao.queryByQuery(groupMap);
			if (Utils.isNotEmpty(allocation)) {
				u.setUserCompanyName(allocation.getResName());
			}

			userDao.saveOrUpdate(u);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
