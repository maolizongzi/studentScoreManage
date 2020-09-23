package com.cndym.dao.put;


import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.Ticket;

public interface ITicketDao extends BaseDao<Ticket> {

	/**
	 * 【代购部分成功，算票】 根据方案ID查询票表，获得总的 投注成功的钱
	 * @param returnTime   回执时间
	 */
	Double queryAmountByProgramsOrderId(Map<String,Object> paramMap);

	/**
	 * 【合买部分成功，算票】根据方案id，查询合买代购用户总成功的金额（也就是除去失败票的价格）
	 * @param paramMap
	 * @return
	 */
	Double querySuccessTicketByProgramsOrderId(Map<String, Object> paramMap);

	
	
	
}