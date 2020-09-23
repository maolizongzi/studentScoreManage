package com.cndym.dao.pick;


import java.util.List;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserRegistrationConversionRate;

public interface IUserRegistrationConversionRateDao extends BaseDao<UserRegistrationConversionRate> {
	/**
	 * 当loginStatus=1时，获取所有新用户登录转化率临时信息
	 * 当bettingStatus=1时，获取所有新用户投注转化率临时信息
	 * @param loginStatus  1:未结束  2：结束
	 * @param bettingStatus 1:未结束  2：结束
	 * @return
	 */
	public List<UserRegistrationConversionRate> queryUserRegistrationConversionRateList(Integer loginStatus,Integer bettingStatus,Integer isLoginFlag);
	
	/**
	 * 删除中间表里面的数据
	 * @param start
	 * @param endDate
	 */
	public void deleteUserRegRate(String start, String endDate);
}
