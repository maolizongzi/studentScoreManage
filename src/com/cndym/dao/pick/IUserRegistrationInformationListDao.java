package com.cndym.dao.pick;


import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.util.PageView;

public interface IUserRegistrationInformationListDao extends BaseDao<UserRegistrationInformationList> {
	public PageView queryPageView(UserRegistrationInformationList userRegistr, int currentPage,String startTime,String endTime);
	/**
	 * 根据参数查询,返回符合信息的集合
	 * @param userMap
	 * @return
	 */
	public List<UserRegistrationInformationList> queryByQuery(
			Map<String, Object> userMap);
	
	/**
	 * 根据参数查询,返回符合信息
	 * @param userMap
	 * @return
	 */
	public UserRegistrationInformationList queryByQueryURIL(Map<String, Object> userMap);

	/**
	 * 删除结果表里面的数据
	 * @param startDate
	 * @param endDate
	 */
	public void deleteUserRegList(String startDate, String endDate);
	
	
	/**
	 * 补漏数据
	 * @param start
	 * @param end
	 * @param paramMap
	 * @param interval
	 */
	public void updateUserRegList(String start,String end,Map<Integer,String> paramMap,Integer interval);
	
}
