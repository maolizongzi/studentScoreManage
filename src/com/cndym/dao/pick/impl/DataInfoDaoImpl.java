package com.cndym.dao.pick.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.cndym.dao.impl.BaseDaoImpl;
import com.cndym.dao.pick.IDataInfoDao;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.util.BuilderSql;
import com.cndym.util.PageView;

@Repository
@SuppressWarnings({"unchecked","rawtypes"})
public class DataInfoDaoImpl extends BaseDaoImpl<DataInfo> implements
		IDataInfoDao {
	private Logger logger = Logger.getLogger(getClass());
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Override
	public PageView queryPageViewChongzhi(DataInfo dataInfo, int currentPage,String administratorType) {
		PageView view = null;
		try {
			String sid = dataInfo.getSid();
			BuilderSql bs = new BuilderSql(DataInfo.class);
			if(StringUtils.isNotEmpty(sid)) {
				bs=chongZhiBS(dataInfo.getSid(), dataInfo.getStartTime(), dataInfo.getEndTime(), 2,0);
			}else{
				if("admin".equals(administratorType)){
					bs=chongZhiBS(dataInfo.getSid(), dataInfo.getStartTime(), dataInfo.getEndTime(), 2,1);
				}
			}
			view = getPageView(bs, currentPage);
			List itemList = view.getItems();
			List<DataInfo> dataList = new ArrayList<DataInfo>();
			if (itemList != null && itemList.size() > 0) {
				dataList =chongzhiChange(itemList);
				view.getItems().clear();
				view.getItems().addAll(dataList);
			}
		} catch (Exception e) {
			logger.error(" 分页查询充值数据------注册渠道 " + e);
		}
		return view;
	}
	@Override
	public PageView queryPageViewAll(DataInfo dataInfo, int currentPage) {
		PageView view = null;
		try {
			BuilderSql bs = new BuilderSql(DataInfo.class);
			String sid = dataInfo.getSid();
			String startTime = dataInfo.getStartTime();
			String endTime = dataInfo.getEndTime();
			bs.addWhereClause("sidStatus=? ", 1);
			if (StringUtils.isNotEmpty(sid)) {
				bs.addWhereClause("sid=?", sid);
				bs.addWhereClause("isCount=?", 0);
			}else{
				bs.addWhereClause("isCount=?", 1);
			}
			if (StringUtils.isNotEmpty(startTime)) {
				bs.addWhereClause("dataRecordTime >= to_date(?,'yyyy-MM-dd') ",
						startTime);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				bs.addWhereClause("dataRecordTime <= to_date(?,'yyyy-MM-dd') ",
						endTime);
			}
			bs.addOrderClause(false, "id");
			bs.addOrderClause(false, "dataRecordTime");
			view = getPageView(bs, currentPage);
		} catch (Exception e) {
			logger.error(" 分页查询全部数据------登陆渠道 " + e);
		}
		return view;
	}
	@Override
	public PageView queryPageViewTouzhu(DataInfo dataInfo, int currentPage,String administratorType) {
		PageView view = null;
		try {
			BuilderSql bs = new BuilderSql(DataInfo.class);
			String sid = dataInfo.getSid();
			String startTime = dataInfo.getStartTime();
			String endTime = dataInfo.getEndTime();
			if(StringUtils.isNotEmpty(sid)) {
				bs=touzhuBS(sid, startTime, endTime, 2, 0);
			}else{
				if("admin".equals(administratorType)){
					bs=touzhuBS(sid, startTime, endTime, 2, 1);
				}
			}
			view = getPageView(bs, currentPage);
			List itemList = view.getItems();
			List<DataInfo> dataList = new ArrayList<DataInfo>();
			if (itemList != null && itemList.size() > 0) {
				dataList =touzhuChange(itemList);
				view.getItems().clear();
				view.getItems().addAll(dataList);
			}

		} catch (Exception e) {
			logger.error(" 分页查询全部投注数据------注册渠道 " + e);
		}
		return view;
	}
	@Override
	public List<DataInfo> queryListChongzhi(String sid,
			String startTime, String endTime,Integer sidStatus,String administratorType) {
		List<DataInfo> dataList = new ArrayList<DataInfo>();
		try {
			BuilderSql bs = new BuilderSql(DataInfo.class);
			if(StringUtils.isNotEmpty(sid)) {
				bs=chongZhiBS(sid, startTime, endTime, sidStatus,0);
			}else{
				if("admin".equals(administratorType)){
					bs=chongZhiBS(sid, startTime, endTime, sidStatus,1);
				}
			}
			List itemList = super.getList(bs);
			dataList =chongzhiChange(itemList);
		} catch (Exception e) {
			logger.error(" 查询所有数据不分页充值数据------注册渠道 " + e);
		}
		return dataList;
	}
	@Override
	public List<DataInfo> queryListTouzhu(String sid, String startTime,String endTime,
			Integer sidStatus,String administratorType) {
		List<DataInfo> dataList = new ArrayList<DataInfo>();
		try {
			BuilderSql bs = new BuilderSql(DataInfo.class);
			if(StringUtils.isNotEmpty(sid)) {
				bs = touzhuBS(sid, startTime, endTime, sidStatus, 0);
			}else{
				if("admin".equals(administratorType)){
					bs = touzhuBS(sid, startTime, endTime, sidStatus, 1);
				}
			}
			List itemList = super.getList(bs);
			if (itemList != null && itemList.size() > 0) {
				dataList =touzhuChange(itemList);
			}
		} catch (Exception e) {
			logger.error(" 查询所有数据不分页投注数据------注册渠道 " + e);
		}
		return dataList;
	}
	@Override
	public List<DataInfo> queryListAll(String sid, String startTime,String endTime,
			Integer sidStatus,String administratorType) {
		List<DataInfo> dataList = null;
		try {
			BuilderSql bs = new BuilderSql(DataInfo.class);
			bs.addWhereClause("sidStatus=?", sidStatus);
			if (StringUtils.isNotEmpty(sid)) {
				bs.addWhereClause("sid=?", sid);
				bs.addWhereClause("isCount=?", 0);
			}else{
				bs.addWhereClause("isCount=?", 1);
			}
			if (StringUtils.isNotEmpty(startTime)) {
				bs.addWhereClause("dataRecordTime >= to_date(?,'yyyy-MM-dd') ",
						startTime);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				bs.addWhereClause("dataRecordTime <= to_date(?,'yyyy-MM-dd') ",
						endTime);
			}
			bs.addOrderClause(false, "id");
			bs.addOrderClause(false, "dataRecordTime");
			dataList = getList(bs);
		} catch (Exception e) {
			logger.error(" 查询所有数据[不分页]全部数据------登陆渠道 " + e);
		}
		return dataList;
	}
	@Override
	public List<DataInfo> queryCountListAll(String reportTime,String endTime,
			Integer sidStatus) {
		List<DataInfo> dataList = new ArrayList<DataInfo>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			sql.append("DATA_RECORD_TIME   , ");
			sql.append("sum(1) as sid  ,");
			sql.append("sum(DAU)  as DAU , ");
			sql.append("sum(JI_HUO) as JI_HUO , ");
			sql.append("sum(REGISTERED_USER_COUNT)  as REGISTERED_USER_COUNT , ");
			sql.append("sum(CHONG_ZHI_USER_COUNT)  as CHONG_ZHI_USER_COUNT , ");
			sql.append("sum(TOU_ZHU_USER_COUNT)  as TOU_ZHU_USER_COUNT, ");
			sql.append("sum(CHONG_ZHI_ALL_MONEY) as CHONG_ZHI_ALL_MONEY , ");
			sql.append("sum(USER_AVG_CHONG_ZHI_MONEY) as USER_AVG_CHONG_ZHI_MONEY , ");
			sql.append("sum(TOU_ZHU_ALL_MONEY) as TOU_ZHU_ALL_MONEY ,");
			sql.append("sum(CHONG_ZHI_NEW_USER_COUNT) as CHONG_ZHI_NEW_USER_COUNT , ");
			sql.append("sum(TOU_ZHU_NEW_USER_COUNT)  as TOU_ZHU_NEW_USER_COUNT ,");
			sql.append("sum(CHONG_ZHI_NEW_USER_MONERY) as CHONG_ZHI_NEW_USER_MONERY  ,");
			sql.append("sum(CHONG_ZHI_NEW_USER_AVG_MONEY) as CHONG_ZHI_NEW_USER_AVG_MONEY  ,");
			sql.append("sum(TOU_ZHU_NEW_USER_MONERY) as TOU_ZHU_NEW_USER_MONERY ,");
			sql.append("sum(CHONG_ZHI_NEW_USER_RATE) as CHONG_ZHI_NEW_USER_RATE ,");
			sql.append("sum(TOU_ZHU_NEW_USER_RATE) as TOU_ZHU_NEW_USER_RATE ,");
			sql.append("sum(TOU_ZHU_NEW_USER_ARPU) as TOU_ZHU_NEW_USER_ARPU ,");
			sql.append("sum(TOU_ZHU_ALL_USER_ARPU) as TOU_ZHU_ALL_USER_ARPU ,");
			sql.append("sum(GRANTS) as GRANTS ,");
			sql.append("sum(GRANTS_CONSUME)  as GRANTS_CONSUME ");
			sql.append("FROM DATA_INFO u WHERE 1 = 1 ");
			sql.append("and SID_STATUS = ? ");
			sql.append("and IS_COUNT = 0 ");
			sql.append("and DATA_RECORD_TIME >= to_date(?,'yyyy-MM-dd') ");
			sql.append("and DATA_RECORD_TIME < to_date(?,'yyyy-MM-dd') ");
			sql.append(" group by DATA_RECORD_TIME");
			sql.append(" ORDER BY DATA_RECORD_TIME desc ");
			Object[] obj = new Object[3];
			obj[0] = sidStatus;
			obj[1] = reportTime;
			obj[2] = endTime;
			dataList = this.jdbcTemplate.query(sql.toString(), obj,
					(RowMapper) ParameterizedBeanPropertyRowMapper
							.newInstance(DataInfo.class));
		} catch (Exception e) {
			logger.error(" " + e);
		}
		return dataList;
	}
	@Override
	public List<DataInfo> queryCountListChongzhi(String reportTime,String endTime,
			Integer sidStatus) {
		List<DataInfo> dataList = new ArrayList<DataInfo>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			sql.append("DATA_RECORD_TIME as DATA_RECORD_TIME , ");
			sql.append("sum(1) as sid   , ");
			sql.append("sum(JI_HUO) as JI_HUO , ");
			sql.append("sum(REGISTERED_USER_COUNT) as REGISTERED_USER_COUNT ,");
			sql.append("sum(CHONG_ZHI_USER_COUNT) as CHONG_ZHI_USER_COUNT ,");
			sql.append("sum(CHONG_ZHI_ALL_MONEY) as CHONG_ZHI_ALL_MONEY ,");
			sql.append("sum(CHONG_ZHI_NEW_USER_COUNT) as CHONG_ZHI_NEW_USER_COUNT , ");
			sql.append("sum(CHONG_ZHI_NEW_USER_MONERY) as CHONG_ZHI_NEW_USER_MONERY , ");
			sql.append("sum(CHONG_ZHI_NEW_USER_RATE) as CHONG_ZHI_NEW_USER_RATE , ");
			sql.append("sum(USER_AVG_CHONG_ZHI_MONEY) as USER_AVG_CHONG_ZHI_MONEY , ");
			sql.append("sum(CHONG_ZHI_NEW_USER_AVG_MONEY) as CHONG_ZHI_NEW_USER_AVG_MONEY , ");
			sql.append("sum(GRANTS)  as GRANTS ");
			sql.append("FROM DATA_INFO u WHERE 1 = 1 ");
			sql.append("and SID_STATUS = ? ");
			sql.append("and IS_COUNT = 0 ");
			sql.append("and DATA_RECORD_TIME >= to_date(?,'yyyy-MM-dd') ");
			sql.append("and DATA_RECORD_TIME < to_date(?,'yyyy-MM-dd') ");
			sql.append(" group by DATA_RECORD_TIME");
			sql.append(" ORDER BY DATA_RECORD_TIME desc ");
			Object[] paraObj = new Object[3];
			paraObj[0] = sidStatus;
			paraObj[1] = reportTime;
			paraObj[2] = endTime;

			dataList = this.jdbcTemplate.query(sql.toString(), paraObj,
					(RowMapper) ParameterizedBeanPropertyRowMapper
							.newInstance(DataInfo.class));
		} catch (Exception e) {
			logger.error(" 查询所有数据不分页充值数据------注册渠道 " + e);
		}
		return dataList;
	}
	@Override
	public List<DataInfo> queryCountListTouzhu(String reportTime,String endTime, 
			Integer sidStatus) {
		List<DataInfo> dataList = new ArrayList<DataInfo>();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select ");
			sql.append("DATA_RECORD_TIME  as DATA_RECORD_TIME ,");
			sql.append("sum(1)  as sid  ,");
			sql.append("sum(JI_HUO) as JI_HUO ,");
			sql.append("sum(REGISTERED_USER_COUNT) as REGISTERED_USER_COUNT ,");
			sql.append("sum(TOU_ZHU_USER_COUNT) as TOU_ZHU_USER_COUNT ,");
			sql.append("sum(TOU_ZHU_ALL_MONEY) as TOU_ZHU_ALL_MONEY ,");
			sql.append("sum(TOU_ZHU_NEW_USER_COUNT) as TOU_ZHU_NEW_USER_COUNT ,");
			sql.append("sum(TOU_ZHU_NEW_USER_MONERY) as TOU_ZHU_NEW_USER_MONERY ,");
			sql.append("sum(TOU_ZHU_NEW_USER_RATE) as TOU_ZHU_NEW_USER_RATE ,");
			sql.append("sum(TOU_ZHU_ALL_USER_ARPU) as TOU_ZHU_ALL_USER_ARPU ,");
			sql.append("sum(TOU_ZHU_NEW_USER_ARPU) as TOU_ZHU_NEW_USER_ARPU ,");
			sql.append("sum(GRANTS_CONSUME) as GRANTS_CONSUME ");
			sql.append("FROM DATA_INFO u WHERE 1 = 1 ");
			sql.append("and SID_STATUS = ? ");
			sql.append("and IS_COUNT = 0 ");
			sql.append("and DATA_RECORD_TIME >= to_date(?,'yyyy-MM-dd') ");
			sql.append("and DATA_RECORD_TIME < to_date(?,'yyyy-MM-dd') ");
			sql.append(" group by DATA_RECORD_TIME");
			sql.append(" ORDER BY DATA_RECORD_TIME desc ");
			Object[] paraObj = new Object[3];
			paraObj[0] = sidStatus;
			paraObj[1] = reportTime;
			paraObj[2] = endTime;
			dataList = this.jdbcTemplate.query(sql.toString(), paraObj,
					(RowMapper) ParameterizedBeanPropertyRowMapper
							.newInstance(DataInfo.class));
		} catch (Exception e) {
			logger.error("管理员之订阅，渠道统计数据查询----注册渠道，投注数据 " + e);
		}
		return dataList;
	}
	
	//充值BuilderSql
		public BuilderSql chongZhiBS(String sid, String startTime, String endTime,Integer sidStatus,Integer isCount){
			BuilderSql bs = new BuilderSql(DataInfo.class);
			bs.addSelectClause("dataRecordTime", "sid", "jiHuo",
					"registeredUserCount", "chongZhiUserCount",
					"chongZhiAllMoney", "chongZhiNewUserCount",
					"chongZhiNewUserMonery", "chongZhiNewUserRate",
					"userAvgChongZhiMoney", "chongZhiNewUserAvgMoney", "grants");
			bs.addWhereClause("sidStatus=?", sidStatus);
			bs.addWhereClause("isCount=?", isCount);
			if (StringUtils.isNotEmpty(sid)) {
				bs.addWhereClause("sid=?", sid);
			}
			if (StringUtils.isNotEmpty(startTime)) {
				bs.addWhereClause("dataRecordTime >= to_date(?,'yyyy-MM-dd') ",
						startTime);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				bs.addWhereClause("dataRecordTime <= to_date(?,'yyyy-MM-dd') ",
						endTime);
			}
			bs.addOrderClause(false, "id");
			bs.addOrderClause(false, " dataRecordTime ");
			return bs;
		}
		public BuilderSql touzhuBS(String sid,String startTime,String endTime,Integer sidStatus,Integer isCount){
			BuilderSql bs = new BuilderSql(DataInfo.class);
			bs.addSelectClause("dataRecordTime", "sid", "jiHuo",
					"registeredUserCount", "touZhuUserCount", "touZhuAllMoney",
					"touZhuNewUserCount", "touZhuNewUserMonery",
					"touZhuNewUserRate", "touZhuAllUserArpu",
					"touZhuNewUserArpu", "grantsConsume");
			bs.addWhereClause("sidStatus=?", sidStatus);
			bs.addWhereClause("isCount=?", isCount);
			if (StringUtils.isNotEmpty(sid)) {
				bs.addWhereClause("sid=?", sid);
			}
			if (StringUtils.isNotEmpty(startTime)) {
				bs.addWhereClause("dataRecordTime >= to_date(?,'yyyy-MM-dd') ",
						startTime);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				bs.addWhereClause("dataRecordTime <= to_date(?,'yyyy-MM-dd') ",
						endTime);
			}
			bs.addOrderClause(false, "id");
			bs.addOrderClause(false, " dataRecordTime ");
			return bs;
		}
		//充值将List<Object[]>转换成List
		public List<DataInfo> chongzhiChange(List itemList){
			List<DataInfo> dataList = new ArrayList<DataInfo>();
			try {
				if (itemList != null && itemList.size() > 0) {
					for (Object obj : itemList) {
						Object[] objArray = (Object[]) obj;
						DataInfo dataTem = new DataInfo();
						if (objArray[0] != null) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd hh:mm:ss");
							Date da = sdf.parse(objArray[0].toString());
							dataTem.setDataRecordTime(da);
						}
						if (objArray[1] != null) {
							dataTem.setSid(objArray[1].toString());
						}
						if (objArray[2] != null) {
							Integer jiHuo = Integer
									.parseInt(objArray[2].toString());
							dataTem.setJiHuo(jiHuo);
						} else {
							dataTem.setJiHuo(0);
						}
						if (objArray[3] != null) {
							Integer registeredUserCount = Integer
									.parseInt(objArray[3].toString());
							dataTem.setRegisteredUserCount(registeredUserCount);
						} else {
							dataTem.setRegisteredUserCount(0);
						}
						if (objArray[4] != null) {
							Integer chongZhiUserCount = Integer
									.parseInt(objArray[4].toString());
							dataTem.setChongZhiUserCount(chongZhiUserCount);
						} else {
							dataTem.setChongZhiUserCount(0);
						}
						if (objArray[5] != null) {
							Double chongZhiAllMoney = Double
									.parseDouble(objArray[5].toString());
							dataTem.setChongZhiAllMoney(chongZhiAllMoney);
						} else {
							dataTem.setChongZhiAllMoney(0d);
						}
						if (objArray[6] != null) {
							Integer chongZhiNewUserCount = Integer
									.parseInt(objArray[6].toString());
							dataTem.setChongZhiNewUserCount(chongZhiNewUserCount);
						} else {
							dataTem.setChongZhiNewUserCount(0);
						}
						if (objArray[7] != null) {
							Double chongZhiNewUserMonery = Double
									.parseDouble(objArray[7].toString());
							dataTem.setChongZhiNewUserMonery(chongZhiNewUserMonery);
						} else {
							dataTem.setChongZhiNewUserMonery(0d);
						}
						if (objArray[8] != null) {
							Double chongZhiNewUserRate = Double
									.parseDouble(objArray[8].toString());
							dataTem.setChongZhiNewUserRate(chongZhiNewUserRate);
						} else {
							dataTem.setChongZhiNewUserRate(0d);
						}
						if (objArray[9] != null) {
							Double userAvgChongZhiMoney = Double
									.parseDouble(objArray[9].toString());
							dataTem.setUserAvgChongZhiMoney(userAvgChongZhiMoney);
						} else {
							dataTem.setUserAvgChongZhiMoney(0d);
						}
						if (objArray[10] != null) {
							Double chongZhiNewUserAvgMoney = Double
									.parseDouble(objArray[10].toString());
							dataTem.setChongZhiNewUserAvgMoney(chongZhiNewUserAvgMoney);
						} else {
							dataTem.setChongZhiNewUserAvgMoney(0d);
						}
						if (objArray[11] != null) {
							Double grants = Double.parseDouble(objArray[11]
									.toString());
							dataTem.setGrants(grants);
						} else {
							dataTem.setGrants(0d);
						}
						dataList.add(dataTem);
					}
				}
			} catch (Exception e) {
				logger.error("",e);
			}
			return dataList;
		}
		public List<DataInfo> touzhuChange(List itemList ){
			List<DataInfo> dataList = new ArrayList<DataInfo>();
			try {
				if (itemList != null && itemList.size() > 0) {
					for (Object obj : itemList) {
						Object[] objArray = (Object[]) obj;
						DataInfo dataTem = new DataInfo();
						if (objArray[0] != null) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yyyy-MM-dd hh:mm:ss");
							Date da = sdf.parse(objArray[0].toString());
							dataTem.setDataRecordTime(da);
						}
						if (objArray[1] != null) {
							dataTem.setSid(objArray[1].toString());
						}
						if (objArray[2] != null) {
							Integer jiHuo = Integer
									.parseInt(objArray[2].toString());
							dataTem.setJiHuo(jiHuo);
						} else {
							dataTem.setJiHuo(0);
						}
						if (objArray[3] != null) {
							Integer registeredUserCount = Integer
									.parseInt(objArray[3].toString());
							dataTem.setRegisteredUserCount(registeredUserCount);
						} else {
							dataTem.setRegisteredUserCount(0);
						}
						if (objArray[4] != null) {
							Integer touZhuUserCount = Integer.parseInt(objArray[4]
									.toString());
							dataTem.setTouZhuUserCount(touZhuUserCount);
						} else {
							dataTem.setTouZhuUserCount(0);
						}
						if (objArray[5] != null) {
							Double touZhuAllMoney = Double.parseDouble(objArray[5]
									.toString());
							dataTem.setTouZhuAllMoney(touZhuAllMoney);
						} else {
							dataTem.setTouZhuAllMoney(0d);
						}
						if (objArray[6] != null) {
							Integer touZhuNewUserCount = Integer
									.parseInt(objArray[6].toString());
							dataTem.setTouZhuNewUserCount(touZhuNewUserCount);
						} else {
							dataTem.setTouZhuNewUserCount(0);
						}
						if (objArray[7] != null) {
							Double touZhuNewUserMonery = Double
									.parseDouble(objArray[7].toString());
							dataTem.setTouZhuNewUserMonery(touZhuNewUserMonery);
						} else {
							dataTem.setTouZhuNewUserMonery(0d);
						}
						if (objArray[8] != null) {
							Double touZhuNewUserRate = Double
									.parseDouble(objArray[8].toString());
							dataTem.setTouZhuNewUserRate(touZhuNewUserRate);
						} else {
							dataTem.setTouZhuNewUserRate(0d);
						}
						if (objArray[9] != null) {
							Double touZhuAllUserArpu = Double
									.parseDouble(objArray[9].toString());
							dataTem.setTouZhuAllUserArpu(touZhuAllUserArpu);
						} else {
							dataTem.setTouZhuAllUserArpu(0d);
						}
						if (objArray[10] != null) {
							Double touZhuNewUserArpu = Double
									.parseDouble(objArray[10].toString());
							dataTem.setTouZhuNewUserArpu(touZhuNewUserArpu);
						} else {
							dataTem.setTouZhuNewUserArpu(0d);
						}
						if (objArray[11] != null) {
							Double grantsConsume = Double.parseDouble(objArray[11]
									.toString());
							dataTem.setGrantsConsume(grantsConsume);
						} else {
							dataTem.setGrantsConsume(0d);
						}
						dataList.add(dataTem);
					}
				}
			} catch (Exception e) {
				logger.error("",e);
			}
			return dataList;
		}
	//全部数据时，木有数据的时候显示汇总数据
	public List<DataInfo> queryCountAll(DataInfo dataInfo) {
		List<DataInfo> dataList=new ArrayList<DataInfo>();
		try {
			BuilderSql bs = new BuilderSql(DataInfo.class);
			String startTime = dataInfo.getStartTime();
			String endTime = dataInfo.getEndTime();
			bs.addWhereClause("sidStatus=? ", 1);
			bs.addWhereClause("isCount=?", 1);
			if (StringUtils.isNotEmpty(startTime)) {
				bs.addWhereClause("dataRecordTime >= to_date(?,'yyyy-MM-dd') ",
						startTime);
			}
			if (StringUtils.isNotEmpty(endTime)) {
				bs.addWhereClause("dataRecordTime <= to_date(?,'yyyy-MM-dd') ",
						endTime);
			}
			bs.addOrderClause(false, "id");
			bs.addOrderClause(false, "dataRecordTime");
			dataList=getList(bs);
		} catch (Exception e) {
			logger.error(" 分页查询全部数据------登陆渠道 " + e);
		}
		return dataList;
	}
	//充值数据时，木有数据的时候显示汇总数据
	public List queryCountChongZhi(DataInfo dataInfo){
		List list=new ArrayList();
		try {
			BuilderSql bs = chongZhiBS(dataInfo.getSid(), dataInfo.getStartTime(), dataInfo.getEndTime(), dataInfo.getSidStatus(), dataInfo.getIsCount());
			list=getListNoGen(bs);
		} catch (Exception e) {
			logger.error("",e);
		}
		return list;
	}
	public List queryCountTouZhu(DataInfo dataInfo){
		List list=new ArrayList();
		try {
			BuilderSql bs = touzhuBS(dataInfo.getSid(), dataInfo.getStartTime(), dataInfo.getEndTime(), dataInfo.getSidStatus(), dataInfo.getIsCount());
			list=getListNoGen(bs);
		} catch (Exception e) {
			logger.error("",e);
		}
		return list;
	}
	@Override
	public void deleteDataInfo(String start, String endDate) {
		try {
			String sql = " DELETE  DATA_INFO t where t.DATA_RECORD_TIME >= to_date('" + start + "','yyyy-MM-dd')  AND  t.DATA_RECORD_TIME < to_date('" + endDate + "','yyyy-MM-dd') ";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			logger.error("同步之前先删除数" + e);
		}
	}
}
