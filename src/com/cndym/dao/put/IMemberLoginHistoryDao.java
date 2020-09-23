package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.MemberLoginHistory;

public interface IMemberLoginHistoryDao extends BaseDao<MemberLoginHistory> {
	/**
	 * @Name: deleteMemberLoginHistory
	 * @Description: 每天同步之前,删除DAU
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 无
	 */
	 void deleteMemberLoginHistory(String startDate, String endDate);

	/**
	 * @Name: queryMemberLoginHistory
	 * @Description: 按照用户code 和渠道进行分组的查询前一天00:00 到23:59:59的数据<br/>
	 *               eg: 2016-04-05 00:00:00 至 2016-04-05 23:59:59<br/>
	 *               查询这段时间内DAU去重登录用户数的记录，然后插入到新建的表中
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月6日15:06:58
	 * @Parameters: 无
	 * @Return: true  插入成功，false 插入失败
	 */
	boolean queryMemberLoginHistoryList(String startDate,String endDate);

	/**
	 * @Name: queryDistinctGroupBySId
	 * @Description: 根据sid分组得到去重后登陆用户数 DAU
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: key： sid   value：去重登陆用户数
	 */
	List<Map<String,Object>>  queryDistinctGroupBySId(String startDate, String endDate);
	
	/**
	 * @Name: queryDistinctCount
	 * @Description: DAU 去重登陆用户数汇总，不分渠道直接统计去重数
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 去重登陆用户数
	 */
	Integer  queryDistinctCount(String startDate, String endDate);
	
	/**
	 * @Name: queryDistinctUserCode
	 * @Description: 得到去重登录userCode集合
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年12月23日18:54:36
	 * @Parameters: startTime:开始时间   endTime：结束时间
	 * @Return: 得到去重登录userCode集合
	 */
	public List<Map<String,Object>> queryDistinctUserCode(String startTime,String endTime);
	
	/**
	 * @param endDate 
	 * @param startDate 
	 * @param userCodeList  
	 * @Name: queryMemberByTime ,List<Map<String,Object>> userCodeList
	 * @Description: 登录用户的集合在某段时间内登录的比例 
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月5日13:40:51
	 * @Parameters: 无
	 * @Return:  返回某段时间内，登录用户登录信息的集合
	 */
	public List<Map<String,Object>> queryMemberLoginByTime(String startTime, String endTime,String start, String end);
	
	/**
	 * 
	 * @param registrationTime  注册时间
	 * @param endTime			注册结束
	 * @param start				开始时间
	 * @param end				结束时间
	 * @return
	 */
	public List<Map<String, Object>> queryMemberLoginBettingByTime(
			String startTime, String endTime, String start, String end);
	
	
	public List<Map<String, Object>> queryDistinct(String startDate, String endDate);
}
