package com.cndym.dao.put.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.put.IClientActiveDao;
import com.cndym.entity.data.put.ClientActive;

@Repository
public class ClientActiveDaoImpl extends BaseDaoImpl<ClientActive> implements
		IClientActiveDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public boolean deleteClientActive(String start, String end) {
		boolean falg = false;
		try {
			String sql = "delete from SYS_CLIENT_ACTIVE_PICK t where t.create_time  >= to_date('"
					+ start
					+ "','yyyy-MM-dd')  AND  t.create_time  < to_date('"
					+ end
					+ "','yyyy-MM-dd')";
			jdbcTemplate.update(sql);
			falg = true;
		} catch (Exception e) {
			logger.error("同步之前先删除数" + e);
		}
		return falg;
	}

	@Override
	public boolean queryClientActive(String startDate, String endDate) {
		// 将原始数据局筛选出来，放到登陆渠道表
		boolean flag = false;
		try {
			String sql = "INSERT INTO SYS_CLIENT_ACTIVE_PICK (ID,SID,PLAT_FORM,SOFT_VER,MACH_ID,CREATE_TIME,STATUS) ";
			sql += "SELECT SEQ_CLIENT_ACT_PICK.NEXTVAL, t.sid, t.platform, t.soft_ver ,t.mach_id , t.UPDATE_TIME ,t.status ";
			sql += "from sys_client_active t WHERE t.UPDATE_TIME >= to_date('"
					+ startDate
					+ "','yyyy-MM-dd') AND t.UPDATE_TIME < to_date('" + endDate
					+ "','yyyy-MM-dd') ";
			sql += "and t.status=1  and t.sid is  not NULL ";
			jdbcTemplate.update(sql);
			flag = true;
		} catch (Exception e) {
			flag = false;
			logger.error("激活用户数-->之登陆渠道，插入到新建的表SYS_CLIENT_ACTIVE_PICK中出错", e);
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> queryDistinct(String startDate,
			String endDate) {
		List<Map<String, Object>> list = null;
		try {
			String sql = "SELECT sid,COUNT(1) as count from SYS_CLIENT_ACTIVE_PICK ";
			sql = sql
					+ " WHERE CREATE_TIME >= to_date(?,'yyyy-MM-dd') AND CREATE_TIME < to_date(?,'yyyy-MM-dd') GROUP BY sid";
			Query query = this.getSession().createSQLQuery(sql);
			query.setString(0, startDate);
			query.setString(1, endDate);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		} catch (Exception e) {
			logger.error("激活用户数算错了" + e);
		}
		return list;
	}
}
