package com.cndym.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cndym.dao.IResourceAllocationDao;
import com.cndym.entity.company.ResourceAllocation;
import com.cndym.util.BuilderSql;

@Repository
public class ResourceAllocationDaoImpl extends BaseDaoImpl<ResourceAllocation>
		implements IResourceAllocationDao {

	@Override
	public ResourceAllocation queryByQuery(Map<String, Object> groupMap) {
		BuilderSql bs = new BuilderSql(ResourceAllocation.class);
		if (groupMap.get("id") != null) {
			Long id = (Long) groupMap.get("id");
			bs.addWhereClause("id=?", id);
		}
		if (groupMap.get("code") != null) {
			String  code = (String) groupMap.get("code");
			bs.addWhereClause("code=?", code);
		}
		if (groupMap.get("resType") != null) {
			Integer resType = (Integer) groupMap.get("resType");
			bs.addWhereClause("resType=?", resType);
		}
		List<ResourceAllocation> groupList = this.getList(bs);
		if (groupList != null&&groupList.size()>0) {
			return groupList.get(0);
		}
		return null;

	}
}
