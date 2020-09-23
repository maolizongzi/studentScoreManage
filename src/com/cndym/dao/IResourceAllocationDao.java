package com.cndym.dao;

import java.util.Map;

import com.cndym.entity.company.ResourceAllocation;

public interface IResourceAllocationDao  extends BaseDao<ResourceAllocation>{

	/**
	 * @Name: queryByQuery
	 * @Description: 根据条件获取公司详细信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月23日09:38:19
	 * @Parameters: 无
	 * @Return: 完整的公司集合
	 */
	ResourceAllocation queryByQuery(Map<String, Object> groupMap);
	
}
