package com.cndym.service;

import java.util.List;
import java.util.Map;

import com.cndym.entity.user.UserGroup;
import com.cndym.util.PageView;

public interface IUserGroupService {

	/**
	 * @Name: queryAllGroupList
	 * @Description: 获取所有的用户组集合
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return: 所有的用户组集合
	 */
	List<UserGroup> queryAllGroupList();

	/**
	 * @Name: queryAllGroupList
	 * @Description: 获取所有的用户组集合
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return: 所有的用户组集合
	 */
	PageView queryPageUserGroupList( int currentPage);

	/**
	 * @Name: saveOrUpdateUserGroup
	 * @Description: 保存或者修改用户组信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月25日17:17:12
	 * @Parameters: 要保存的用户信息
	 * @Return: 无
	 */
	void saveOrUpdateUserGroup(UserGroup userGroup);

	/**
	 * @Name: queryByQuery
	 * @Description: 根据paramMap条件查询用户组详细信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月25日18:13:11
	 * @Parameters: paramMap 查询条件
	 * @Return: 完整的用户信息
	 */
	UserGroup queryByQuery(Map<String, Object> paramMap);
	
	/**
	 * @Name: deleteGroupById
	 * @Description: 根据id删除用户组信息，删除之前要判断该id下面是否有用户
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月23日15:50:46
	 * @Parameters: 无
	 * @Return: 是否删除成功
	 */
	String deleteGroupById(Long id);
}
