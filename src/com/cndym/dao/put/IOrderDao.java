package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.pick.UserBettors;
import com.cndym.entity.data.put.Order;

public interface IOrderDao extends BaseDao<Order> {

	//根据方案id查询订单 合买部分成功
	List<UserBettors> queryHMByProgramsOrderId(Map<String, Object> paramMap);
	
	
	
	
	
	//合买全部成功 根据方案id查询订单
    List<UserBettors> queryHMAllSuccessByProgramsOrderId(Map<String, Object> paramMap);  

}
