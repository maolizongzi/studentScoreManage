package com.cndym.dao;

import java.util.Map;

import com.cndym.entity.user.UserGroup;
import com.cndym.util.PageView;

public interface IUserGroupDao extends BaseDao<UserGroup> {

	/**
	 * @Name: queryByQuery
	 * @Description: 根据条件获取用户组详细信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月23日09:38:19
	 * @Parameters: 无
	 * @Return: 完整的用户组信息
	 */
	UserGroup queryByQuery(Map<String, Object> groupMap);

	/**
	 * @Name: queryByQuery
	 * @Description: 根据条件获取用户组详细信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月23日09:38:19
	 * @Parameters: 无
	 * @Return: 完整的用户组信息
	 */
	PageView queryPageUserGroupList( int currentPage);
}
