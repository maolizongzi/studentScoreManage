package com.cndym.service;

import com.cndym.entity.data.task.DataTable;
import com.cndym.entity.email.UserEmail;

public interface ISendTaskEmailService {

	/**
	 * 发送邮件
	 * @param dataInfoSid 
	 * @param permissionType 
	 * @param email 
	 * @param Flag 统计flag  
	 */
	void sendEmail(DataTable dataInfoSid, UserEmail email);
	

}
