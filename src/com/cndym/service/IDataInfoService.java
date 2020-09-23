package com.cndym.service;

import java.util.List;

import com.cndym.entity.data.pick.DataInfo;
import com.cndym.util.PageView;

public interface IDataInfoService {
	/**
	 * 分页查询数据
	 * 
	 * @param dataInfo
	 * @param currentPage
	 * @param permissionType
	 * @return
	 */
	PageView queryPageList(DataInfo dataInfo, int currentPage,
			String permissionType, String administratorType);

	// 用于邮件发送，根据sid,订阅时间 ,来获取从订阅时间到当前时间的所有数据
	List<DataInfo> queryBySidAllTime(String sid, String reportTime,
			String permissionType, String administratorType);


	List<DataInfo> getExportDataList(DataInfo dataInfo, String permissionType,
			String strAdmin);
}
