package com.cndym.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.IChannelDao;
import com.cndym.entity.channel.Channel;
import com.cndym.service.IChannelService;

@Service("channelServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class ChannelServiceImpl implements IChannelService {
	@Resource
	private IChannelDao channelDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
	public List<Channel> queryChannelListByCompanyCode(String code) {
		Channel channel = new Channel();
		channel.setCompanyCode(code);
		return channelDao.queryByQuery(channel);
	}

	@Override
	public Channel getChannelBySid(String sid) {

		return channelDao.getChannelBySid(sid);
	}

	@Override
	public List<Channel> getChannelList(String name, String code,
			String companyCode) {
		Channel channel = new Channel();
		channel.setName(name);
		channel.setSid(code);
		// channel.setCompanyCode(companyCode);

		return this.channelDao.searchChannelList(channel);
	}

	@Override
	public Map<String, String> sidMap() {
		Map<String,String> sidMap=channelDao.sidMap();
		return sidMap;
	}
}
