package com.cndym.dao.pick;

import java.util.List;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.util.PageView;

public interface IDataInfoDao extends BaseDao<DataInfo> {
	// 删除结果表里面的数据
	void deleteDataInfo(String start, String endDate);

	/**
	 * 分页查询充值数据------注册渠道
	 * @param administratorType 
	 * 
	 * @return
	 */
	PageView queryPageViewChongzhi(DataInfo dataInfo, int currentPage, String administratorType);
	/**
	 * 分页查询全部数据------登陆渠道
	 * 
	 * @return
	 */
	PageView queryPageViewAll(DataInfo dataInfo, int currentPage);
	/**
	 * 分页查询全部投注数据------注册渠道
	 * @param administratorType 
	 * 
	 * @return
	 */
	PageView queryPageViewTouzhu(DataInfo dataInfo, int currentPage, String administratorType);
	/**
	 * 查询所有数据不分页充值数据------注册渠道
	 * @param administratorType 
	 * 
	 * @return
	 */
	List<DataInfo> queryListChongzhi(String sid, String startTime,String endTime,
			Integer sidStatus, String administratorType);
	/**
	 * 查询所有数据不分页投注数据------注册渠道
	 * @param administratorType 
	 * 
	 * @return
	 */
	List<DataInfo> queryListTouzhu(String sid, String startTime,String endTime,
			Integer sidStatus, String administratorType);
	/**
	 * 查询所有数据不分页全部数据------登陆渠道
	 * @param administratorType 
	 * 
	 * @return
	 */
	List<DataInfo> queryListAll(String sid, String startTime,String endTime,
			Integer sidStatus, String administratorType);
	
	/**
	 * 登陆渠道，全部数据统计
	 * 
	 * @return
	 */
	List<DataInfo> queryCountListAll(String reportTime,String endTime, Integer sidStatus);
	/**
	 * 注册渠道，充值数据统计
	 * @param endTime 
	 * 
	 * @return
	 */
	List<DataInfo> queryCountListChongzhi(String reportTime, String endTime, Integer sidStatus);
	/**
	 * 注册渠道，投注数据统计
	 * @param endTime 
	 * 
	 * @return
	 */
	List<DataInfo> queryCountListTouzhu(String reportTime, String endTime, Integer sidStatus);
}
