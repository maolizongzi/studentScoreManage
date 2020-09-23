package com.cndym.dao.put;

import java.util.List;
import java.util.Map;

import com.cndym.dao.BaseDao;
import com.cndym.entity.data.put.ClientActive;

public interface IClientActiveDao extends BaseDao<ClientActive> {

	//激活数据明细
	boolean queryClientActive(String startDate, String endDate);

	//去重激活数
	List<Map<String, Object>> queryDistinct(String startDate, String endDate);
	//删除数
	boolean deleteClientActive(String start, String end);
}
