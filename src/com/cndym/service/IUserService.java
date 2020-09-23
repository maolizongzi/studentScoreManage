package com.cndym.service;

import java.util.Map;

import com.cndym.entity.user.User;
import com.cndym.util.PageView;

public interface IUserService {

	/**
	 * @Name: getFunctionXml
	 * @Description: 获取用户所拥有的权限
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return: 无
	 */
	void getFunctionXml();

	/**
	 * @Name: queryByQuery
	 * @Description: 根据条件获取用户详细信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return: 完整的用户信息
	 */
	User queryByQuery(Map<String,Object> userMap);

	/**
	 * @Name: queryPageUserList
	 * @Description: 根据条件分页查询用户信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return: 完整的用户信息
	 */
	PageView queryPageUserList(User user, int currentPage);

	/**
	 * @Name: deleteById
	 * @Description: 根据id删除用户信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月23日15:50:46
	 * @Parameters: 无
	 * @Return: 是否删除成功
	 */
	boolean deleteById(Long id);

	/**
	 * @Name: saveOrUpdateUser
	 * @Description: 保存或者修改用户信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月23日15:50:46
	 * @Parameters: 无
	 * @Return: 是否保存或者修改成功
	 */
	void saveOrUpdateUser(User u);
}
