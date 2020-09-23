package com.cndym.service;

import java.util.List;
import java.util.Map;

import com.cndym.entity.channel.Channel;

public interface IChannelService {

	/**
	 * @Description: 根据公司code获取渠道集合
	 * @Author:程禄元  2016.04.13
	 * @Return:  获取所有符合条件的渠道集合
	 */
	List<Channel> queryChannelListByCompanyCode(String  code);
	
	/**
	 * @Name: getChannelBySid
	 * @Description: 根据sid得到渠道
	 * @Author:LiNa
	 * @Version:v1.00(版本号)
	 * @Create Date: 2016年4月21日13:59:31
	 * @Parameters:无
	 * @Return: 无
	 */
	Channel getChannelBySid(String  sid);

	List<Channel> getChannelList(String name, String  code, String companyCode);
	
	
	
	 Map<String, String> sidMap();

}
