package com.cndym.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;


public class BuilderSql {
	private String selectClause;
	private String fromClause;
	private String whereClause;
	private String orderClause;
	private List<Object> params = new ArrayList<Object>();
	private Logger logger = Logger.getLogger(getClass());
	public BuilderSql(Class<?> c) {
		fromClause = "  from " + c.getSimpleName() + "  this    ";
	}

	public BuilderSql addSelectClause(String... names) {
		StringBuilder builder = new StringBuilder();
		if (selectClause == null || selectClause.length() < 1) {
			selectClause = "select  ";
		} else {
			selectClause += ",";
		}
		for (int i = 0; i < names.length; i++) {
			if (i == names.length - 1) {
				builder.append("this." + names[i]);
			} else {
				builder.append("this." + names[i] + ",");
			}
		}
		selectClause += builder.toString();
		return this;
	}
	public BuilderSql addSelectClauseNoAlias(String... names) {
		StringBuilder builder = new StringBuilder();
		if (selectClause == null || selectClause.length() < 1) {
			selectClause = "select  ";
		} else {
			selectClause += ",";
		}
		for (int i = 0; i < names.length; i++) {
			if (i == names.length - 1) {
				builder.append(" " + names[i]);
			} else {
				builder.append(" " + names[i] + ",");
			}
		}
		selectClause += builder.toString();
		return this;
	}


	public BuilderSql addWhereClause(String where, Object... params) {
		if (whereClause == null || whereClause.length() < 1) {
			whereClause = "   where   ";
		} else {
			whereClause += " and  ";
		}
		whereClause += "this." + where;

		for (Object object : params) {
			this.params.add(object);
		}
		return this;
	}
	
	public BuilderSql addOrWhereClause(String where,String name, Object... params) {
		if (whereClause == null || whereClause.length() < 1) {
			whereClause = "   where   ";
		} else {
				whereClause += name;
		}
		whereClause += " " + where;

		for (Object object : params) {
			this.params.add(object);
		}
		return this;
	}

	public BuilderSql addOrderClause(boolean order, String name) {
		if (orderClause == null || orderClause.length() < 1) {
			orderClause = "   order by   ";
		} else {
			orderClause += ",";
		}
		String orderBy = "";
		if (order == true) {
			orderBy = "asc";
		} else {
			orderBy = "desc";
		}
		orderClause += " this." + name + "  " + orderBy;
		return this;
	}
	
	
	public void addGroupBy(String name){
		
	}
	

	/**
	 * 生成查询总数量的HQL
	 * 
	 * @return
	 */
	public String toQueryCountHql() {
		StringBuilder hql = new StringBuilder();

		hql.append("SELECT COUNT(*) ");
		addNotNullClause(hql, fromClause);
		addNotNullClause(hql, whereClause);
		// 查询数量不需要排序
		return hql.toString();
	}

	/**
	 * 生成查询数据列表的HQL
	 * 
	 * @return
	 */
	public String getHql() {
		StringBuilder hql = new StringBuilder();
		addNotNullClause(hql, selectClause);
		addNotNullClause(hql, fromClause);
		addNotNullClause(hql, whereClause);
		addNotNullClause(hql, orderClause);
		logger.info("--------------hql------------------>"+hql);
		return hql.toString();
	}

	private StringBuilder addNotNullClause(StringBuilder hql, String clause) {
		if (clause != null && clause.length() > 0) {
			hql.append(clause);
		}
		return hql;
	}

	public Query getQuery(Session sesion) {
		Query query = sesion.createQuery(this.getHql());
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}
		return query;
	}

	/**
	 * 查询总记录数
	 * 
	 * @param session
	 * @return
	 */
	public int queryCount(Session session) {
		Query countQuery = session.createQuery(toQueryCountHql());
		for (int i = 0; i < params.size(); i++) {// 设置参数
			countQuery.setParameter(i, params.get(i));
		}
		return ((Number) countQuery.uniqueResult()).intValue(); // 查询数量
	}

	/**
	 * 查询指定页的数据=======1
	 * 
	 * @return
	 */
	public List queryList(Session session, int pageNum, int pageSize) {
		Query listQuery = session.createQuery(getHql());
		for (int i = 0; i < params.size(); i++) {// 设置参数cc
			listQuery.setParameter(i, params.get(i));
		}

		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		return listQuery.list();
	}

	/**
	 * 查询指定页的数据=======2使用二级缓存
	 * 
	 * @return
	 */

	public List queryListUseCache(Session session, int pageNum, int pageSize) {

		Query listQuery = session.createQuery(getHql());
		for (int i = 0; i < params.size(); i++) {// 设置参数
			listQuery.setParameter(i, params.get(i));
		}
		// 打开query缓存
		listQuery.setCacheable(true);
		listQuery.setFirstResult((pageNum - 1) * pageSize);
		listQuery.setMaxResults(pageSize);
		return listQuery.list();
	}
	/*
	 * public List queryList(HibernateTemplate tt,final int pageNum,final int
	 * pageSize) { return tt.execute(new HibernateCallback() {
	 * 
	 * public Object doInHibernate(Session session) throws HibernateException,
	 * SQLException { Query listQuery = session.createQuery(toQueryListHql());
	 * for (int i = 0; i < params.size(); i++) {// 设置参数
	 * listQuery.setParameter(i, params.get(i)); }
	 * 
	 * listQuery.setFirstResult((pageNum - 1) * pageSize);
	 * listQuery.setMaxResults(pageSize); return listQuery.list(); } });
	 * 
	 * 
	 * }
	 */
}
