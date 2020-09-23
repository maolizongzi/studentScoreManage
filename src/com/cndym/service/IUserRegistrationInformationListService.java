package com.cndym.service;

import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.util.PageView;

public interface IUserRegistrationInformationListService {
	/**
	 * 
	 * @param userRegistr
	 * @param currentPage
	 * @param retentionRateFlag 保留率标识(1:登录  2：投注)
	 * @return
	 */
	PageView queryPageList(UserRegistrationInformationList userRegistr,int currentPage,String startTime,String endTime);
}
