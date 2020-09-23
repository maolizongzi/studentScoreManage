package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.Fill;

/**
 * 
 * @author Administrator
 * 
 */
public interface IFillDao extends BaseDao<Fill> {
	/**
	 * @Name: queryFillList
	 * @Description: 从原始数据中筛选出当天的数据入库
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 无
	 */
	boolean queryFillList(String startDate, String endDate);
	/**
	 * @Name: deleteFill
	 * @Description: 删除当天的数据
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 无
	 */
	void deleteFill(String startDate, String endDate);
	/**
	 * @Name: queryDistinctLoginGroupBySid
	 * @Description: 去重充值用户数，分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的 去重充值用户数
	 */
	List<Map<String, Object>> queryDistinctLoginGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryDistinctLoginCount
	 * @Description: 去重充值用户数，不分渠道----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 去重充值用户数
	 */
	Integer queryDistinctLoginCount(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegGroupBySid
	 * @Description: 去重充值用户数，分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的去重充值数
	 */
	List<Map<String, Object>> queryDistinctRegGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryDistinctRegCount
	 * @Description: 去重充值用户数，不分渠道----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: 去重充值用户数
	 */
	Integer queryDistinctRegCount(String startDate, String endDate);
	/**
	 * @Name: queryAllMoneyLogin
	 * @Description: 总充值金额----->登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的充值金额
	 */
	List<Map<String, Object>> queryAllMoneyLogin(String startDate, String endDate);
	/**
	 * @Name: queryAllMoneyReg
	 * @Description: 总充值金额----->注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  sid 对应的充值金额
	 */
	List<Map<String, Object>> queryAllMoneyReg(String startDate, String endDate);
	/**
	 * @Name: queryNewDistinctLoginGroupBySid
	 * @Description: 充值新用户数： 分渠道--------登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的  充值新用户数
	 */
	List<Map<String, Object>> queryNewDistinctLoginGroupBySid(String startDate, String endDate);
	/**
	 * @Name: queryNewDistinctLoginCount
	 * @Description: 充值新用户数： 不分渠道--------登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  充值新用户数
	 */
	Integer queryNewDistinctLoginCount(String startDate, String endDate) ;
	/**
	 * @Name: queryNewDistinctRegGroupBySid
	 * @Description: 充值新用户数： 分渠道--------注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return: sid 对应的  充值新用户数
	 */
	List<Map<String, Object>> queryNewDistinctRegGroupBySid(String startDate,String endDate);
	/**
	 * @Name: queryNewDistinctRegCount
	 * @Description: 充值新用户数： 不分渠道--------注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  充值新用户数
	 */
	Integer queryNewDistinctRegCount(String startDate, String endDate);
	/**
	 * @Name: queryNewMoneryLogin
	 * @Description: 新用户充值金额--------登陆
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  sid 对应的新用户充值金额
	 */
	List<Map<String, Object>> queryNewMoneryLogin(String startDate, String endDate);
	/**
	 * @Name: queryNewMoneryReg
	 * @Description:  新用户充值金额 ---------注册
	 * @Author: LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年5月11日14:39:33
	 * @Parameters: startDate:开始时间   endDate：结束时间
	 * @Return:  sid 对应的新用户充值金额
	 */
	List<Map<String,Object>> queryNewMoneryReg(String startDate, String endDate);

}
