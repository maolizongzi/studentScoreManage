package com.cndym.dao;

import java.util.List;
import java.util.Map;

import com.cndym.entity.channel.Channel;

public interface IChannelDao  extends BaseDao<Channel>{

	/**
	 * @Author:chengluyuan
	 * @Create Date: 2016年4月13日
	 * @Return: 根据条件查询channel
	 */
	List<Channel> queryByQuery(Channel channel);


	List<Channel> searchChannelList(Channel channel);
	
	/**
	 * @Name: getChannelBySid
	 * @Description: 根据sid得到渠道
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月21日13:59:31
	 * @Parameters:无
	 * @Return: 无
	 */
	Channel getChannelBySid(String sid);


	/**
	 * @Name: sidMap
	 * @Description: 
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月21日13:59:31
	 * @Parameters:无
	 * @Return: 无
	 */
	Map<String, String> sidMap();
}
