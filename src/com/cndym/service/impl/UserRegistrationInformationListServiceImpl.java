package com.cndym.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.pick.IUserRegistrationInformationListDao;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.service.IUserRegistrationInformationListService;
import com.cndym.util.PageView;

@Service("userRegistrationInformationListServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class UserRegistrationInformationListServiceImpl implements
		IUserRegistrationInformationListService {
	@Resource
	private IUserRegistrationInformationListDao userRegistrationInformationListDaoImpl;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public PageView queryPageList(UserRegistrationInformationList userRegistr,
			int currentPage, String startTime, String endTime) {
		return userRegistrationInformationListDaoImpl.queryPageView(
				userRegistr, currentPage, startTime, endTime);
	};
}
