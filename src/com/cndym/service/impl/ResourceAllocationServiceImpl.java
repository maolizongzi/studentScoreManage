package com.cndym.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.IResourceAllocationDao;
import com.cndym.entity.company.ResourceAllocation;
import com.cndym.service.IResourceAllocationService;
import com.cndym.util.BuilderSql;

@Service("resourceAllocationServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class ResourceAllocationServiceImpl implements
		IResourceAllocationService {
	@Resource
	private IResourceAllocationDao resourceAllocationDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public List<ResourceAllocation> queryAllResourceList(Integer type) {
		List<ResourceAllocation> list = null;
		try {
			BuilderSql bs = new BuilderSql(ResourceAllocation.class);
			bs.addWhereClause("resType=?", type);
			list = resourceAllocationDao.getList(bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
