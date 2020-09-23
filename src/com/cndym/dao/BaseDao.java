package com.cndym.dao;

import java.io.Serializable;
import java.util.List;

import com.cndym.util.BuilderSql;
import com.cndym.util.PageView;

public interface BaseDao<T> {
	/**
	 * @Name:save
	 * @Description:根据传入的对象，实现增加功能
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:传入的对象
	 * @Return:无
	 */
	void save(T t);
	/**
	 * @Name:delete
	 * @Description:根据id，实现删除功能
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:对象的id
	 * @Return:无
	 */
	void delete(Serializable id);

	/**
	 * @Name:update
	 * @Description:根据对象，实现修改功能
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:对象
	 * @Return:无
	 */
	void update(T t);

	/**
	 * @Name:saveOrUpdate
	 * @Description:保存或更新
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:对象
	 * @Return:无
	 */
	void saveOrUpdate(T obj);

	/**
	 * @Name:getById
	 * @Description: 获取实体
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:对象的id
	 * @Return:根据id查询后返回的完整对象
	 */
	T getById(Serializable id);


	/**
	 * @Name:getByIdList
	 * @Description: 根据id集合，查询对应的对象完整信息
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:id集合
	 * @Return:对象集合
	 */
	List<T> getByIdList(Serializable[] idList);

	/**
	 * @Name:getAll
	 * @Description:查询所有
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:无
	 * @Return:对象集合
	 */
	List<T> getAll();

	/**
	 * @Name:getPageView
	 * @Description:处理一系列的参数，返回对应的pageView对象
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:在Service层，将拼接好的查询语句封装到BuilderSql中，同时传过来currentPage
	 * @Return:pageView对象
	 * 
	 */
	PageView getPageView(BuilderSql builderSql, int currentPage);

	/**
	 * @Name:getList
	 * @Description:根据查询条件，访问数据库，返回完整的集合对象
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:在Service层，将拼接好的查询语句封装到BuilderSql中
	 * @Return:返回查询后的集合
	 * 
	 */
	List<T> getList(BuilderSql builderSql);
	/**
	 * @Name:getListUseCache==========================使用二级缓存
	 * @Description:在二级缓存中返回完整的集合对象，
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:在Service层，将拼接好的查询语句封装到BuilderSql中
	 * @Return:返回查询后的集合
	 * 
	 */
	// 使用二级缓存
	List<T> getListUseCache(BuilderSql builderSql);
	/**
	 * @Name:getListNoGen
	 * @Description:根据hql语句  进行查询    返回的不是某一个对象，而是一个Object类型的集合
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date:2016年3月3日18:16:01
	 * @Parameters:在Service层，将拼接好的查询语句封装到BuilderSql中
	 * @Return:  返回的不是某一个对象，而是一个Object类型的集合
	 * 
	 */
	List getListNoGen(BuilderSql builderSql);
}
