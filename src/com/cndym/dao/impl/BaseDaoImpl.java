package com.cndym.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cndym.dao.BaseDao;
import com.cndym.util.BuilderSql;
import com.cndym.util.Config;
import com.cndym.util.PageView;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	protected Logger logger = null;
	// ×¢ÈësessionFactory
	private SessionFactory sessionFactory = null;

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	Class<?> clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<?>) type.getActualTypeArguments()[0];
		logger=Logger.getLogger(clazz);
	}

	public void save(T t) {
		this.getSession().save(t);
	}

	public void delete(Serializable id) {
		Object o = this.getSession().get(clazz, id);
		this.getSession().delete(o);
	}

	public void update(T t) {
		this.getSession().update(t);
	}

	public void saveOrUpdate(T obj) {
		this.getSession().saveOrUpdate(obj);
	}

	public T getById(Serializable id) {
		T t = (T) this.getSession().get(clazz, id);
		return t;
	}

	public List<T> getByIdList(Serializable[] idList) {
		if (idList == null || idList.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery( //
					"FROM " + clazz.getName() + " WHERE id in (:idList)") //
					.setParameterList("idList", idList)//
					.list();
		}
	}

	public List<T> getAll() {
		BuilderSql bs = new BuilderSql(clazz);
		Query q = bs.getQuery(getSession());
		return q.list();
	}

	

	public PageView getPageView(BuilderSql queryBuilder, int currentPage) {
		int pageSize = Config.PAGE_SIZE;
		int count = queryBuilder.queryCount(getSession());
		List list = queryBuilder.queryList(getSession(), currentPage, pageSize);
		return new PageView(currentPage, pageSize, count, list);
	}

	public List<T> getList(BuilderSql queryBuilder) {
		int pageSize = Integer.MAX_VALUE;
		List list = queryBuilder.queryList(getSession(), 1, pageSize);
		return list;
	}

	public List<T> getListUseCache(BuilderSql queryBuilder) {
		int pageSize = Integer.MAX_VALUE;
		List list = queryBuilder.queryListUseCache(getSession(), 1, pageSize);
		return list;
	}

	public List getListNoGen(BuilderSql queryBuilder) {
		int pageSize = Integer.MAX_VALUE;
		List list = queryBuilder.queryList(getSession(), 1, pageSize);
		return list;
	}

}
