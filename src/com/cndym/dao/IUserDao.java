package com.cndym.dao;

import java.util.List;
import java.util.Map;

import com.cndym.entity.user.User;
import com.cndym.util.PageView;

public interface IUserDao extends BaseDao<User> {

	/**
	 * @Name: queryByQuery
	 * @Description: 根据条件获取用户详细信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return: 完整的用户信息
	 */
	List<User> queryByQuery(Map<String, Object> userMap);

	/**
	 * @Name: queryPageUserList
	 * @Description: 分页显示用户信息集合
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return: 分页显示用户信息集合
	 */
	PageView queryPageUserList(User user, int currentPage);

}
