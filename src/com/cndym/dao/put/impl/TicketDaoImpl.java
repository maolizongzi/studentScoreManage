package com.cndym.dao.put.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.put.ITicketDao;
import com.cndym.entity.data.put.Ticket;

@Repository
public class TicketDaoImpl extends BaseDaoImpl<Ticket> implements ITicketDao {

	@Override
	public Double queryAmountByProgramsOrderId(Map<String, Object> paraMap) {
		Double d = null;
		try {
			// 04570884643347548181
			String sql = "SELECT  SUM(amount) as renGouSuccessAmount  FROM TMS_TICKET t where  t.programs_order_id =  ? and  t.ticket_status = 3   ";
			SQLQuery query = getSession().createSQLQuery(sql);
			String programsOrderIdId = paraMap.get("programsOrderId") + "";
			query.setString(0, programsOrderIdId);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String, Object>> list = query.list();
			if (list != null && list.size() > 0) {
				d = Double.valueOf(list.get(0).get("renGouSuccessAmount") + "");
			}
		} catch (Exception e) {
			logger.error("代购部分成功之登陆的渠道", e);
		}
		return d;
	}

	@Override
	public Double querySuccessTicketByProgramsOrderId(
			Map<String, Object> paramMap) {
		Double money = null;
		try {
			String programsOrderId = paramMap.get("programsOrderId") + "";
			Integer ticket = (Integer) paramMap.get("ticketStatus");
			String sql = " SELECT sum(amount) as amount from  Ticket  t where t.ticketStatus = ?  and t.programsOrderId = ?  ";
			Query query = this.getSession().createSQLQuery(sql);
			query.setInteger(0, ticket);
			query.setString(1, programsOrderId);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			List<Map<String, Object>> list = query.list();
			if (list != null && list.size() > 0) {
				money = Double.valueOf(list.get(0).get("amount") + "");
			}
		} catch (Exception e) {
			logger.error("合买部分成功之登陆的渠道,算认购成功的钱出错了", e);
		}
		return money;
	}
}
