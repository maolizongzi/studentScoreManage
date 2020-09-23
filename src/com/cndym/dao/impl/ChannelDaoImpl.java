package com.cndym.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.cndym.dao.IChannelDao;
import com.cndym.entity.channel.Channel;
import com.cndym.util.BuilderSql;
import com.cndym.util.export.Utils;

@Repository
@SuppressWarnings("unchecked")
public class ChannelDaoImpl extends BaseDaoImpl<Channel> implements IChannelDao {
	private Logger logger = Logger.getLogger(getClass());
	@Override
	public List<Channel> queryByQuery(Channel channel) {

		List<Channel> results = this.getSession().createCriteria(Channel.class)
				.add(Example.create(channel)).addOrder( Order.asc("sid")).list();
		if (Utils.isNotEmpty(results)) {
			return results;
		}
		return null;
	}


	@Override
	public List<Channel> searchChannelList(Channel channel) {

		BuilderSql bs = new BuilderSql(Channel.class);

		if (channel.getName() != null) {
			bs.addWhereClause("name like ?", "%" + channel.getName() + "%");
		}
		if (channel.getSid() != null) {

			bs.addWhereClause("sid like ?",  channel.getSid() + "%");
		}
		if (channel.getCompanyCode() != null) {

			bs.addWhereClause("companyCode = ?", channel.getCompanyCode());
		}
		bs.addWhereClause("delStatus = ?", 0);
		bs.addOrderClause(true, "sid");
		List<Channel> channelList = this.getList(bs);
		if (channelList != null && channelList.size() > 0) {
			return channelList;
		}
		return null;

	}

	@Override
	public Channel getChannelBySid(String sid) {
		try {
			BuilderSql bs = new BuilderSql(Channel.class);
			if (Utils.isNotEmpty(sid)) {
				bs.addWhereClause("sid=?", sid);
			}
			List<Channel> channelList = getList(bs);
			if (channelList != null && channelList.size() > 0) {
				return channelList.get(0);
			}
		} catch (Exception e) {
			logger.error("根据sid查询渠道名称出错..", e);
		}
		return null;
	}
	
	
	@Override
	public Map<String, String> sidMap() {
		Map<String, String> map=new HashMap<String,String>();
		BuilderSql bs=new BuilderSql(Channel.class);
		bs.addSelectClause("sid","name");
		List itemList = super.getList(bs);
		if(itemList!=null&& itemList.size()>0){
			for (Object obj : itemList) {
				Object[] objArray = (Object[]) obj;
				if(Utils.isNotEmpty(objArray[1])){
					map.put(objArray[0].toString(),objArray[1].toString());
				}
			}
		}
		return map;
	}
}
