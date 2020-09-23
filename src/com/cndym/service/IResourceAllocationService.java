package com.cndym.service;

import java.util.List;

import com.cndym.entity.company.ResourceAllocation;

public interface IResourceAllocationService {

	/**
	 * @Name: queryAllResourceList
	 * @param type   1-合作公司；2-合作方式；3-合作产品；4-结算方式
	 * @Description: 获取所有的资源
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年3月22日11:56:12
	 * @Parameters: 无
	 * @Return:  获取所有的资源集合
	 */
	List<ResourceAllocation> queryAllResourceList(Integer type);

}
