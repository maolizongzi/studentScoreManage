package com.cndym.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.IUserDao;
import com.cndym.dao.IUserGroupDao;
import com.cndym.entity.user.User;
import com.cndym.entity.user.UserGroup;
import com.cndym.service.IUserGroupService;
import com.cndym.util.PageView;
import com.cndym.util.export.Utils;

@Service("userGroupServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class UserGroupServiceImpl implements IUserGroupService {
	@Resource
	private IUserGroupDao userGroupDao;
	@Resource
	private IUserDao userDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public List<UserGroup> queryAllGroupList() {
		List<UserGroup> list = null;
		try {
			list = userGroupDao.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public PageView queryPageUserGroupList(int currentPage) {
		PageView view = userGroupDao.queryPageUserGroupList(currentPage);
		return view;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveOrUpdateUserGroup(UserGroup userGroup) {
		String permission = getPermissionsByList(userGroup.getFunctionParentFlag());
		userGroup.setGroupPermissions(permission);
		if (userGroup.getGroupId() == null) {
			userGroup.setCreateTime(new Date());
		}
		try {
			userGroupDao.saveOrUpdate(userGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// camily 2016 04 26 修改
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public UserGroup queryByQuery(Map<String, Object> paramMap) {
		UserGroup userGroup = userGroupDao.queryByQuery(paramMap);
		String permission = userGroup.getGroupPermissions();
		String[] aa = null;
		List<String> bb = new ArrayList<String>();
		if (Utils.isNotEmpty(permission)) {
			aa = permission.split("@");

			for (String s : aa) {
				bb.add(s);
			}
		}
		userGroup.setFunctionParentFlag(bb);
		return userGroup;
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public String deleteGroupById(Long id) {
		String str = "删除成功";
		try {
			// 1.根据用户组id查询该用户组下面有没有用户
			Map<String, Object> userMap = new HashMap<String, Object>();
			userMap.put("groupId", id);
			List<User> uList = userDao.queryByQuery(userMap);
			if (uList != null && uList.size() > 0) {
				str = "该用户组下面还有用户，不能进行删除操作";
			} else {
				userGroupDao.delete(id);
				str = "删除成功";
			}

		} catch (Exception e) {
			str = "deleteError";
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 拼接权限字符串
	 * 
	 * @param permissions
	 * @return
	 */
	public String getPermissionsByList(List<String> permissions) {
		String falgResult = "";
		if (permissions != null && permissions.size() > 0) {
			for (int i = 0; i < permissions.size(); i++) {
				if (i > 0) {
					falgResult = falgResult + "@" + permissions.get(i);
				} else {
					falgResult = falgResult + permissions.get(i);
				}
			}
		}
		return falgResult;
	}
}
