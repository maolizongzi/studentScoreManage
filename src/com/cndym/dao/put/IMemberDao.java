package com.cndym.dao.put;


import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.Member;

public  interface IMemberDao extends BaseDao<Member> {
	
	//删除注册用户数
	void deleteMember(String startDate, String endDate) ;
	
	/**
	 * @param endDate 
	 * @param startDate 
	 * @Name: queryMemberList
	 * @Description: 按照用户code 和union_Id渠道进行分组的
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月5日13:40:51
	 * @Parameters: 无
	 * @Return:  返回这段时间内的所有去重登录用户集合
	 */
	boolean queryMemberList(String startDate, String endDate);

	
	List<Map<String,Object>>  queryDistinct(String startDate, String endDate);
	
	/**
	 * @param endDate 
	 * @param startDate 
	 * @param userCodeList  
	 * @Name: queryMemberByTime ,List<Map<String,Object>> userCodeList
	 * @Description: 登录用户的集合在某段时间内注册的比例 
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月5日13:40:51
	 * @Parameters: 无
	 * @Return:  返回某段时间内，登录用户注册信息的集合
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
	public List<Map<String,Object>> queryMemberBettingByTime(String registrationTime, String endTime,String start, String end);
	
}
