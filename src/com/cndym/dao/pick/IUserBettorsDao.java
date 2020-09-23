package com.cndym.dao.pick;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserBettors;

public interface IUserBettorsDao extends BaseDao<UserBettors> {
	/**
	 * @Name: insertUserBettors
	 * @Description: 将userBettors集合添加到投注表中
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 无
	 */
	void insertUserBettors(List<UserBettors> insertPurchaseUB);
	/**
	 * @Name: queryDistinctLoginGroupBySId
	 * @Description: 去重投注用户数，分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的 去重投注用户数
	 */
	List<Map<String, Object>> queryDistinctLoginGroupBySId(String startDate, String endDate);
	/**
	 * @Name: queryDistinctLoginCount
	 * @Description: 去重投注用户数，不分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 去重投注用户数
	 */
	Integer queryDistinctLoginCount(String startDate, String endDate) ;
	/**
	 * @Name: queryAllTouZhuMoneyLogin
	 * @Description: 投注总金额，分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的 投注总金额
	 */
	List<Map<String, Object>> queryAllTouZhuMoneyLogin(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountLoginGroupBySid
	 * @Description: 去重投注新用户数，分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的 去重投注新用户数
	 */
	List<Map<String, Object>> queryTouZhuNewCountLoginGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountLoginCount
	 * @Description: 去重投注新用户数，不分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  去重投注新用户数
	 */
	Integer  queryTouZhuNewCountLoginCount(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewMoneryCountLogin
	 * @Description: 新用户投注金额，分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  sid 对应的 新用户投注金额
	 */
	List<Map<String, Object>> queryTouZhuNewMoneryCountLogin(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegGroupBySid
	 * @Description: 去重投注用户数，分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的 去重投注用户数
	 */
	List<Map<String, Object>> queryDistinctRegGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegCount
	 * @Description: 去重投注用户数，不分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  去重投注用户数
	 */
	Integer  queryDistinctRegCount(String startDate, String endDate) ;
	/**
	 * @Name: queryAllTouZhuMoneyReg
	 * @Description: 投注总金额，分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  sid 对应的 投注总金额
	 */
	List<Map<String, Object>> queryAllTouZhuMoneyReg(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountRegGroupBySid
	 * @Description: 去重投注新用户数，分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的 去重投注新用户数
	 */
	List<Map<String, Object>> queryTouZhuNewCountRegGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewCountRegCount
	 * @Description: 去重投注新用户数，不分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 去重投注新用户数
	 */
	Integer  queryTouZhuNewCountRegCount(String startDate, String endDate);
	/**
	 * @Name: queryTouZhuNewMoneryCountReg
	 * @Description: 新用户投注金额，分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  sid 对应的 新用户投注金额
	 */
	List<Map<String, Object>> queryTouZhuNewMoneryCountReg(String startDate, String endDate);
	/**
	 * @Name: deleteUserBettors
	 * @Description: 删除记录
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  无
	 */
	void deleteUserBettors(String startDate, String endDate);

}
