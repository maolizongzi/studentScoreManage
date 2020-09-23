package com.cndym.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndym.dao.pick.IDataInfoDao;
import com.cndym.dao.pick.IUserBettorsDao;
import com.cndym.dao.pick.IUserRegistrationConversionRateDao;
import com.cndym.dao.pick.IUserRegistrationInformationListDao;
import com.cndym.dao.put.IAccountLogDao;
import com.cndym.dao.put.IClientActiveDao;
import com.cndym.dao.put.IFillDao;
import com.cndym.dao.put.IMemberDao;
import com.cndym.dao.put.IMemberLoginHistoryDao;
import com.cndym.dao.put.IOrderDao;
import com.cndym.dao.put.IProgramsDao;
import com.cndym.dao.put.ITicketDao;
import com.cndym.entity.data.pick.DataInfo;
import com.cndym.entity.data.pick.ProgramsPick;
import com.cndym.entity.data.pick.UserBettors;
import com.cndym.entity.data.pick.UserRegistrationConversionRate;
import com.cndym.entity.data.pick.UserRegistrationInformationList;
import com.cndym.service.ISynchronizationDataService;
import com.cndym.util.export.Utils;

@Service("synchronizationDataServiceImpl")
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class SynchronizationDataServiceImpl implements
		ISynchronizationDataService {
	private Logger logger = Logger.getLogger(getClass());

	@Resource
	private IMemberLoginHistoryDao memberLoginHistoryDao;
	@Resource
	private IFillDao fillDao;
	@Resource
	private IMemberDao memberDao;
	@Resource
	private ITicketDao ticketDao;
	@Resource
	private IProgramsDao programsDao;
	@Resource
	private IUserBettorsDao userBettorsDao;
	@Resource
	private IOrderDao orderDao;
	@Resource
	private IDataInfoDao dataInfoDao;
	@Resource
	private IClientActiveDao clientActiveDao;
	@Resource
	private IAccountLogDao accountLogDao;
	@Resource
	private IUserRegistrationConversionRateDao userRegistrationConversionRateDao;
	@Resource
	private IUserRegistrationInformationListDao userRegistrationInformationListDao;
	
	/** 注册用户登录转化率 */
	private static String INTERVAL = "2,3,4,5,6,7,10,15,20,25,30";

	// 将原始的数据查询出来入到数据库中
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void getDataByOriginal(String start, String end) {
		// 激活数
		clientActiveDao.queryClientActive(start, end);
		logger.info("client active");
		// DAU
		memberLoginHistoryDao.queryMemberLoginHistoryList(start, end);
		logger.info("client login");
		// 注册用户数
		memberDao.queryMemberList(start, end);
		logger.info("client register");
	}

	/**
	 * 新用户登录/投注保留率
	 * @param start
	 * @param end
	 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void userMemberRetentionRate(String start, String end) {
		/** loginStatus/bettingStatus 中间表特有的信息 */
		Integer loginStatus = 0;	
		Integer bettingStatus = 0;
		/** 保留率标识(1:登录 2：投注) */
		Integer retentionRateFlag = 1;
		/**注册用户/登录用户(1:注册 2：登录)*/
		Integer isLoginFlag=1;
		//注册用户
			// 2.新用户登录
			loginStatus = 1;
			bettingStatus = 0;
			retentionRateFlag = 1;
			userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
			
			// 2.新用户投注
			loginStatus = 0;
			bettingStatus = 1;
			retentionRateFlag = 2;
			userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
			
		//登录用户
			//登录
			 isLoginFlag=2;
			 
			 loginStatus = 1;
			 bettingStatus = 0;
			 retentionRateFlag = 1;
			 userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
		    //投注
			 loginStatus = 0;
		     bettingStatus = 1;
			 retentionRateFlag = 2;
			 userMemberOrLoginRate(start, end, loginStatus, bettingStatus, retentionRateFlag, isLoginFlag);
	}
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateMemberNullRate(String start, String end) {
		//获得注册时间
		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
				.queryUserRegistrationConversionRateList(0,0, 0);
		for (UserRegistrationConversionRate rate : userRegistraList) {
			String loginOrRegTime = Utils.formatDate2Str(
					rate.getRegistrationTime(), "yyyy-MM-dd");
			String endTime = Utils.addDateTime(Utils.getDate(loginOrRegTime),
					"d", +1);
			int dateInterval = Utils.dateInterval(start, loginOrRegTime) + 1;
			if (dateInterval >= 31) {
				continue;
			}
			if (dateInterval == 1) {
				continue;
			}
			Map<Integer,String> paramMap=new HashMap<Integer,String>();
			paramMap.put(2, "TWO_DAY");
			paramMap.put(3, "THREE_DAY");
			paramMap.put(4, "FOUR_DAY");
			paramMap.put(5, "FIVE_DAY");
			paramMap.put(6, "SIX_DAY");
			paramMap.put(7, "SEVEN_DAY");
			paramMap.put(10, "TEN_DAY");
			paramMap.put(15, "FIFTEEN_DAY");
			paramMap.put(20, "TWENTY_DAY");
			paramMap.put(25, "TWENTY_FIVE_DAY");
			paramMap.put(30, "THIRTY_DAY");
			if (INTERVAL.indexOf(dateInterval + "") != -1) {
				userRegistrationInformationListDao.updateUserRegList(loginOrRegTime, endTime, paramMap, dateInterval);
				paramMap.put(2, "TWO_DAY_RATE");
				paramMap.put(3, "THREE_DAY_RATE");
				paramMap.put(4, "FOUR_DAY_RATE");
				paramMap.put(5, "FIVE_DAY_RATE");
				paramMap.put(6, "SIX_DAY_RATE");
				paramMap.put(7, "SEVEN_DAY_RATE");
				paramMap.put(10, "TEN_DAY_RATE");
				paramMap.put(15, "FIFTEEN_DAY_RATE");
				paramMap.put(20, "TWENTY_DAY_RATE");
				paramMap.put(25, "TWENTY_FIVE_DAY_RATE");
				paramMap.put(30, "THIRTY_DAY_RATE");
				userRegistrationInformationListDao.updateUserRegList(loginOrRegTime, endTime, paramMap, dateInterval);
			}
		}
	}
	//注册用户，登录用户     登录/投注保留率
	public void userMemberOrLoginRate(String start, String end, Integer loginStatus,
			Integer bettingStatus, Integer retentionRateFlag,
			Integer isLoginFlag) {
		// 新用户
		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
				.queryUserRegistrationConversionRateList(loginStatus,
						bettingStatus, isLoginFlag);
		logger.info("新用户/登录用户,登录中间表条数：" + userRegistraList.size());
		for (UserRegistrationConversionRate rate : userRegistraList) {
			String loginOrRegTime = Utils.formatDate2Str(
					rate.getRegistrationTime(), "yyyy-MM-dd");
			String endTime = Utils.addDateTime(Utils.getDate(loginOrRegTime),
					"d", +1);
			int dateInterval = Utils.dateInterval(start, loginOrRegTime) + 1;
			if (dateInterval >= 31) {
				// 将登录状态改为2
				rate.setLoginStatus(2);
				rate.setBettingStatus(2);
				userRegistrationConversionRateDao.update(rate);
				logger.info("注册时间：" + rate.getRegistrationTime()
						+ " 30天统计已经完成,loginStatus已经修改为：2");
				continue;
			}
			if (dateInterval == 1) {
				logger.info("注册时间：" + rate.getRegistrationTime() + " 当前时间："
						+ start + " 没到统计时间");
				continue;
			}
			if (INTERVAL.indexOf(dateInterval + "") != -1) {
				// 3.查询这天登录的总的用户数
				Map<String, Integer> memberSidCountMap = new HashMap<String, Integer>();
				Integer memberCount = 0;// 汇总的注册用户数
				List<Map<String, Object>> memberLoginListMap =null;
				if(isLoginFlag.equals(2)){
					memberLoginListMap= memberLoginHistoryDao
						.queryDistinctGroupBySId(loginOrRegTime, endTime);
				}
				if(isLoginFlag.equals(1)){
					memberLoginListMap= memberDao
						.queryDistinct(loginOrRegTime, endTime);
				}		
				if (memberLoginListMap != null && memberLoginListMap.size() > 0) {
					for (Map<String, Object> map : memberLoginListMap) {
						String sid = (String) map.get("SID");
						Integer loginUserCount = Integer.parseInt(map.get(
								"COUNT").toString());
						memberCount = memberCount + loginUserCount;
						memberSidCountMap.put(sid, loginUserCount);
					}
					logger.info("注册时间:" + loginOrRegTime + "总的注册用户数:"
							+ memberCount);
				}
				// 2.去匹配登录表里的信息 start到end之间
				// 登录/投注的用户在registrationTime到endTime这段时间内登录的用户数
				List<Map<String, Object>> memberList = null;
				if(isLoginFlag.equals(1)){
					if (loginStatus.equals(1)) {
						memberList = memberDao.queryMemberLoginByTime(
								loginOrRegTime, endTime, start, end);
					}
					if (bettingStatus.equals(1)) {
						memberList = memberDao.queryMemberBettingByTime(
								loginOrRegTime, endTime, start, end);
					}
				}
				if(isLoginFlag.equals(2)){
					if (loginStatus.equals(1)) {
						memberList=memberLoginHistoryDao.queryMemberLoginByTime(loginOrRegTime, endTime, start, end);
					}
					if (bettingStatus.equals(1)) {
						memberList=memberLoginHistoryDao.queryMemberLoginBettingByTime(loginOrRegTime, endTime, start, end);
					}
				}
				logger.info("开始时间:" + start + " 结束时间:" + end + " 登录的用户,有"
						+ memberList.size() + "个渠道是在注册时间:" + loginOrRegTime
						+ "这天注册 ");
				//普通数据
				Integer countIsNull=0;
				if(memberList.size()==0){
					countIsNull=1;
					if(isLoginFlag.equals(1)){
							memberList = memberDao.queryDistinct(loginOrRegTime, endTime);
					}
					if(isLoginFlag.equals(2)){
							memberList=memberLoginHistoryDao.queryDistinct(loginOrRegTime, endTime);
					}
				}
				if (memberList != null && memberList.size() > 0) {
					// 不分渠道的统计userCode个数
					Integer userCodeCountSum = userRegistrationRateSingleSave(
							loginOrRegTime, dateInterval, memberList,
							memberSidCountMap, retentionRateFlag,isLoginFlag,countIsNull);
					userRegistrationRateSave(loginOrRegTime, dateInterval,
							memberCount, userCodeCountSum, retentionRateFlag,isLoginFlag);

				}
				
				//补漏： 查数据库；条件：注册时间
				//拿到当前时间；
				//两个时间差；6天；
				//这一天；是空；更新这个条数据，6行记录为0；
			}
		}
	}
	
	/**
	 * 登录用户 登录/投注保留率
	 * @param start
	 * @param end
	 * @param loginStatus
	 * @param bettingStatus
	 * @param retentionRateFlag
	 */
//	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
//	public void loginMemberRetentionRate(String start, String end,
//			Integer loginStatus, Integer bettingStatus,
//			Integer retentionRateFlag,Integer isLoginFlag){
//		//1.获取登录用户中间表条数
//		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
//				.queryUserRegistrationConversionRateList(loginStatus,
//						bettingStatus,isLoginFlag);
//		logger.info("登录用户登录中间表条数：" + userRegistraList.size());
//		for (UserRegistrationConversionRate rate : userRegistraList) {
//			String loginTime = Utils.formatDate2Str(
//					rate.getRegistrationTime(), "yyyy-MM-dd");
//			String endTime = Utils.addDateTime(Utils.getDate(loginTime),
//					"d", +1);
//			int dateInterval = Utils.dateInterval(start, loginTime) + 1;
//			if (dateInterval >= 31) {
//				// 将登录状态改为2
//				rate.setLoginStatus(2);
//				rate.setBettingStatus(2);
//				userRegistrationConversionRateDao.update(rate);
//				logger.info("注册时间：" + rate.getRegistrationTime()
//						+ " 30天统计已经完成,loginStatus已经修改为：2");
//				continue;
//			}
//			if (dateInterval == 1) {
//				logger.info("注册时间：" + rate.getRegistrationTime() + " 当前时间："
//						+ start + " 没到统计时间");
//				continue;
//			}
//			if (INTERVAL.indexOf(dateInterval + "") != -1) {
//				// 2.去匹配登录表里的信息 start到end之间
//				// 登录/投注的用户在registrationTime到endTime这段时间内登录的用户数
//				
//				List<Map<String, Object>> memberList = null;
//				memberList=memberLoginHistoryDao.queryMemberLoginByTime(loginTime, endTime, start, endTime);
//				logger.info("开始时间:" + start + " 结束时间:" + end + " 登录的用户,有"
//						+ memberList.size() + "个渠道是在注册时间:" + loginTime
//						+ "这天注册 ");
//				// 3.查询这天登录的总的用户数
//				Map<String, Integer> memberSidCountMap = new HashMap<String, Integer>();
//				Integer memberCount = 0;// 汇总的注册用户数
//				List<Map<String, Object>> memberLoginListMap = memberLoginHistoryDao
//						.queryDistinctGroupBySId(loginTime, endTime);
//				if (memberLoginListMap != null && memberLoginListMap.size() > 0) {
//					for (Map<String, Object> map : memberLoginListMap) {
//						String sid = (String) map.get("SID");
//						Integer loginUserCount = Integer.parseInt(map.get(
//								"COUNT").toString());
//						memberCount = memberCount + loginUserCount;
//						memberSidCountMap.put(sid, loginUserCount);
//					}
//					logger.info("注册时间:" + loginTime + "总的注册用户数:"
//							+ memberCount);
//				}
//				if (memberList != null && memberList.size() > 0) {
//					// 不分渠道的统计userCode个数
//					Integer userCodeCountSum = userRegistrationRateSingleSave(
//							loginTime, dateInterval, memberLoginListMap,
//							memberSidCountMap, retentionRateFlag);
//					userRegistrationRateSave(loginTime, dateInterval,
//							memberCount, userCodeCountSum, retentionRateFlag);
//
//				}
//			}
//		}
//	}
	
	/**
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param loginStatus
	 *            新用户中间表特有信息(登录标志)
	 * @param bettingStatus
	 *            新用户中间表特有信息(投注标志)
	 * @param retentionRateFlag
	 *            0:普通数据，1：统计后的数据,按照天统计
	 */
//	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
//	public void saveUserMemberRate(String start, String end,
//			Integer loginStatus, Integer bettingStatus,
//			Integer retentionRateFlag,Integer isLoginFlag) {
//		List<UserRegistrationConversionRate> userRegistraList = userRegistrationConversionRateDao
//				.queryUserRegistrationConversionRateList(loginStatus,
//							bettingStatus,isLoginFlag);
//			logger.info("新用户登录中间表条数：" + userRegistraList.size());
//		for (UserRegistrationConversionRate rate : userRegistraList) {
//			String registrationTime = Utils.formatDate2Str(
//					rate.getRegistrationTime(), "yyyy-MM-dd");
//			String endTime = Utils.addDateTime(Utils.getDate(registrationTime),
//					"d", +1);
//			int dateInterval = Utils.dateInterval(start, registrationTime) + 1;
//			if (dateInterval >= 31) {
//				// 将登录状态改为2
//				rate.setLoginStatus(2);
//				rate.setBettingStatus(2);
//				userRegistrationConversionRateDao.update(rate);
//				logger.info("注册时间：" + rate.getRegistrationTime()
//						+ " 30天统计已经完成,loginStatus已经修改为：2");
//				continue;
//			}
//			if (dateInterval == 1) {
//				logger.info("注册时间：" + rate.getRegistrationTime() + " 当前时间："
//						+ start + " 没到统计时间");
//				continue;
//			}
//			if (INTERVAL.indexOf(dateInterval + "") != -1) {
//				// 2.去匹配注册表里的信息 start到end之间
//				// 登录/投注的用户在registrationTime到endTime这段时间内注册的用户数
//				List<Map<String, Object>> memberList = null;
//				if (loginStatus.equals(1)) {
//					memberList = memberDao.queryMemberLoginByTime(
//							registrationTime, endTime, start, end);
//				}
//				if (bettingStatus.equals(1)) {
//					memberList = memberDao.queryMemberBettingByTime(
//							registrationTime, endTime, start, end);
//				}
//
//				logger.info("开始时间:" + start + " 结束时间:" + end + " 登录的用户,有"
//						+ memberList.size() + "个渠道是在注册时间:" + registrationTime
//						+ "这天注册 ");
//				// 3.查询这天注册的总的用户数
//				Map<String, Integer> memberSidCountMap = new HashMap<String, Integer>();
//				Integer memberCount = 0;// 汇总的注册用户数
//				List<Map<String, Object>> memberListMap = memberDao
//						.queryDistinct(registrationTime, endTime);
//				if (memberListMap != null && memberListMap.size() > 0) {
//					for (Map<String, Object> map : memberListMap) {
//						String sid = (String) map.get("SID");
//						Integer registeredUserCount = Integer.parseInt(map.get(
//								"COUNT").toString());
//						memberCount = memberCount + registeredUserCount;
//						memberSidCountMap.put(sid, registeredUserCount);
//					}
//					logger.info("注册时间:" + registrationTime + "总的注册用户数:"
//							+ memberCount);
//				}
//				if (memberList != null && memberList.size() > 0) {
//					// 不分渠道的统计userCode个数
//					Integer userCodeCountSum = userRegistrationRateSingleSave(
//							registrationTime, dateInterval, memberListMap,
//							memberSidCountMap, retentionRateFlag);
//					userRegistrationRateSave(registrationTime, dateInterval,
//							memberCount, userCodeCountSum, retentionRateFlag);
//
//				}
//			}
//		}
//	}

	/**
	 * 注册用户，登录，单个渠道保留率
	 * 
	 * @param registrationTime
	 *            中间表的注册时间
	 * @param dateInterval
	 *            时间间隔在（2，3，4，5，6，7，10，15，20，25，30）里面
	 * @param memberList
	 *            去匹配注册表里的信息 start到end之间
	 *            登录的用户在registrationTime到endTime这段时间内注册的用户数
	 *            ，得到的是关于sid分组的统计集合key:sid value：count
	 * @param memberSidCountMap
	 *            registrationTime到endTime这段时间内注册的用户，根据sid分组统计的信息 key:sid
	 *            value：count
	 * @param retentionRateFlag
	 *            0:普通数据，1：统计后的数据,按照天统计
	 * @return 返回在registrationTime到endTime这段时间内 ，汇总各个渠道总的登录的用户个数
	 */
	//countIsNull  : 0:普通数据  1：查询的数据为空
	public Integer userRegistrationRateSingleSave(String registrationTime,
			int dateInterval, List<Map<String, Object>> memberList,
			Map<String, Integer> memberSidCountMap, Integer retentionRateFlag,Integer isLoginFlag,Integer countIsNull) {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("registrationTime", registrationTime);
		paramMap.put("isRegistrStatus", isLoginFlag);
		Integer userCodeCountSum = 0;
		for (Map<String, Object> member : memberList) {
			// 保存或者修改
			String unionId = "";
			Integer sum = 0;
			if (Utils.isNotEmpty(member.get("SID"))) {
				unionId = (String) member.get("SID");
			}
			if (Utils.isNotEmpty(memberSidCountMap.get(unionId))) {
				sum = memberSidCountMap.get(unionId);
			}
			Integer userCodeCount = 0;
			if(countIsNull.equals(0)){
				if (Utils.isNotEmpty(member.get("COUNT"))) {
					userCodeCount = Integer
							.parseInt(member.get("COUNT").toString());
					userCodeCountSum = userCodeCountSum + userCodeCount;
				}
			}
			logger.info("这天" + registrationTime + "注册的用户，第" + dateInterval
					+ "天登录渠道：" + unionId + " 该渠道登录/投注的用户个数：" + userCodeCount
					+ " 单个渠道总数：" + sum);
			paramMap.put("sid", unionId);
			paramMap.put("retentionRateFlag", retentionRateFlag);
			UserRegistrationInformationList userRegistrationSingle = userRegistrationInformationListDao
					.queryByQueryURIL(paramMap);
			if (userRegistrationSingle == null) {
				userRegistrationSingle = new UserRegistrationInformationList();
				userRegistrationSingle.setSid(unionId);
				userRegistrationSingle.setCreateTime(new Date());
				userRegistrationSingle.setRegistrationTime(Utils
						.getDate(registrationTime));
				userRegistrationSingle.setIsRegistrStatus(isLoginFlag);// 新用户
				userRegistrationSingle.setIsCount(0);// 普通数据
				userRegistrationSingle.setRetentionRateFlag(retentionRateFlag);// 登录保留率
				setUril(dateInterval,userRegistrationSingle);
				userRegistrationSingle.setRegisterdCount(sum);// 单个渠道总的注册用户数
			}
			userRegistrationSingle = getColumnByKey(dateInterval, sum,
					userCodeCount, userRegistrationSingle);
			// 先查在update
			// 3.得到信息写入结果表
			userRegistrationInformationListDao
					.saveOrUpdate(userRegistrationSingle);
			logger.info("将信息写入结果表成功");
		}
		return userCodeCountSum;
	}

	private void setUril(Integer dateInterval,UserRegistrationInformationList uril) {
		switch (dateInterval) {
		case 3:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			break;
		case 4:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			break;
		case 5:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			break;
		case 6:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			break;
		case 7:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			break;
		case 10:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			break;
		case 15:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			break;
		case 20:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			uril.setFifteenDay(0);
			uril.setFifteenDayRate(0.00);
			break;
		case 25:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			uril.setFifteenDay(0);
			uril.setFifteenDayRate(0.00);
			uril.setTwentyDay(0);
			uril.setTwentyDayRate(0.00);
			break;
		case 30:
			uril.setTwoDay(0);
			uril.setTwoDayRate(0.00);
			uril.setThreeDay(0);
			uril.setThreeDayRate(0.00);
			uril.setFourDay(0);
			uril.setFourDayRate(0.00);
			uril.setFiveDay(0);
			uril.setFiveDayRate(0.00);
			uril.setSixDay(0);
			uril.setSixDayRate(0.00);
			uril.setSevenDay(0);
			uril.setSevenDayRate(0.00);
			uril.setTenDay(0);
			uril.setTenDayRate(0.00);
			uril.setFifteenDay(0);
			uril.setFifteenDayRate(0.00);
			uril.setTwentyDay(0);
			uril.setTwentyDayRate(0.00);
			uril.setThirtyDay(0);
			uril.setThirtyDayRate(0.00);
			break;
		}
	}

	/**
	 * 注册用户，登陆，汇总数据
	 * 
	 * @param registrationTime
	 *            中间表的注册时间
	 * @param dateInterval
	 *            时间间隔在（2，3，4，5，6，7，10，15，20，25，30）里面
	 * @param memberCount
	 *            汇总的注册用户数
	 * @param userCodeCountSum
	 *            在registrationTime到endTime这段时间内 ，各个渠道总的登录的用户个数
	 */
	public void userRegistrationRateSave(String registrationTime,
			int dateInterval, Integer memberCount, Integer userCodeCountSum,
			Integer retentionRateFlag,Integer isLoginFlag) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("registrationTime", registrationTime);
		paramMap.put("sid", "");
		paramMap.put("retentionRateFlag", retentionRateFlag);
		paramMap.put("isRegistrStatus", isLoginFlag);
		UserRegistrationInformationList userRegistrationSum = userRegistrationInformationListDao
				.queryByQueryURIL(paramMap);
		if (userRegistrationSum == null) {
			userRegistrationSum = new UserRegistrationInformationList();
			userRegistrationSum.setCreateTime(new Date());
			userRegistrationSum.setRegistrationTime(Utils
					.getDate(registrationTime));
			userRegistrationSum.setIsRegistrStatus(isLoginFlag);// 新用户
			userRegistrationSum.setIsCount(1);// 普通数据
			userRegistrationSum.setRetentionRateFlag(retentionRateFlag);// 投注保留率
			userRegistrationSum.setRegisterdCount(memberCount);// 汇总的注册用户数
			setUril(dateInterval,userRegistrationSum);
		}
		logger.info("这天" + registrationTime + "注册的用户，第" + dateInterval
				+ "天汇总投注的用户个数：" + userCodeCountSum);
		userRegistrationSum = getColumnByKey(dateInterval, memberCount,
				userCodeCountSum, userRegistrationSum);
		// 3.得到信息写入结果表
		userRegistrationInformationListDao.saveOrUpdate(userRegistrationSum);
		logger.info("将统计信息写入结果表成功");

	}

	/** 新用户 登录/投注中间表 */
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveUserMemeber(String start, String end) {
		UserRegistrationConversionRate urcrRegistered = new UserRegistrationConversionRate();
		urcrRegistered.setLoginStatus(1);
		urcrRegistered.setBettingStatus(1);
		urcrRegistered.setRegistrationTime(Utils.getDate(start));
//		urcrRegistered.setEndTime(Utils.addDate(Utils.getDate(start), "d", -30));
		urcrRegistered.setCreateTime(new Date());
		urcrRegistered.setIsLoginFlag(1);
		userRegistrationConversionRateDao.save(urcrRegistered);
		logger.info("注册用户  登录/投注中间表 入库成功");
		UserRegistrationConversionRate urcrLogin = new UserRegistrationConversionRate();
		urcrLogin.setLoginStatus(1);
		urcrLogin.setBettingStatus(1);
		urcrLogin.setRegistrationTime(Utils.getDate(start));
//		urcrLogin.setEndTime(Utils.addDate(Utils.getDate(start), "d", -30));
		urcrLogin.setCreateTime(new Date());
		urcrLogin.setIsLoginFlag(2);
		userRegistrationConversionRateDao.save(urcrLogin);
		logger.info("登陆用户  登录/投注中间表 入库成功");
	}

	/**
	 * 
	 * @param key
	 * @param userCodeCountMin
	 * @param userR
	 * @return
	 */
	public UserRegistrationInformationList getColumnByKey(Integer key,
			Integer sumMax, Integer userCodeCountMin,
			UserRegistrationInformationList userR) {
		switch (key) {
		case 2:
			userR.setTwoDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTwoDayRate(0.00);
				break;
			}
			userR.setTwoDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 3:
			userR.setThreeDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setThreeDayRate(0.00);
				break;
			}
			userR.setThreeDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 4:
			userR.setFourDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setFourDayRate(0.00);
				break;
			}
			userR.setFourDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 5:
			userR.setFiveDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setFiveDayRate(0.00);
				break;
			}
			userR.setFiveDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 6:
			userR.setSixDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setSixDayRate(0.00);
				break;
			}
			userR.setSixDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 7:
			userR.setSevenDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setSevenDayRate(0.00);
				break;
			}
			userR.setSevenDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 10:
			userR.setTenDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTenDayRate(0.00);
				break;
			}
			userR.setTenDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 15:
			userR.setFifteenDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setFifteenDayRate(0.00);
				break;
			}
			userR.setFifteenDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 20:
			userR.setTwentyDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTwentyDayRate(0.00);
				break;
			}
			userR.setTwentyDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		case 25:
			userR.setTwentyFiveDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setTwentyFiveDayRate(0.00);
				break;
			}
			userR.setTwentyFiveDayRate(Utils.divideInteger(userCodeCountMin, sumMax,
					4) * 100);
			break;
		case 30:
			userR.setThirtyDay(userCodeCountMin);
			if(userCodeCountMin.equals(0)){
				userR.setThirtyDayRate(0.00);
				break;
			}
			userR.setThirtyDayRate(Utils.divideInteger(userCodeCountMin, sumMax, 4) * 100);
			break;
		}
		return userR;
	}

	// 删除当天的数据
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteDataInfo(String startDate, String endDate) {
		 // 删除激活数
		 clientActiveDao.deleteClientActive(startDate, endDate);
		 logger.info(" deleteClientActive");
		 // 删除DAU
		 memberLoginHistoryDao.deleteMemberLoginHistory(startDate, endDate);
		 logger.info(" deleteMemberLoginHistory");
		 // 删除注册用户
		 memberDao.deleteMember(startDate, endDate);
		 logger.info(" deleteMember");
		 // 充值用户数
		 fillDao.deleteFill(startDate, endDate);
		 logger.info(" deleteFill");
		 // 赠金删除
		 accountLogDao.deleteAccountLog(startDate, endDate);
		 logger.info(" deleteAccountLog");
		 // 方案中间表删除
		 programsDao.deletePrograms(startDate, endDate);
		 logger.info(" deletePrograms");
		 // 投注明细表删除
		 userBettorsDao.deleteUserBettors(startDate, endDate);
		 logger.info(" deleteUserBettors");
		 // 删除结果表数据
		 dataInfoDao.deleteDataInfo(startDate,endDate);
		// 删除获得注册用户登录信息中间表
//		userRegistrationConversionRateDao.deleteUserRegRate(startDate, endDate);
		// 删除新用户登录转行率信息统计表
//		userRegistrationInformationListDao
//				.deleteUserRegList(startDate, endDate);
		logger.info(" deleteDataInfo");
	}

	// 登陆
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	@Override
	public void queryDateLogin(String startDate, String endDate) {
		// 充值用户数
		fillDao.queryFillList(startDate, endDate);
		// 赠金
		accountLogDao.queryAccountLog(startDate, endDate);
		// 将当天的所有的 方案--》登陆渠道 放入中间表
		programsDao.queryAllDataByStatusLogin(startDate, endDate);
		// 将状态 < 3的数据，也就是方案全部成功/部分成功 全部查询出来
		List<ProgramsPick> programsList = programsDao.queryAllDataLogin(
				startDate, endDate);
		logger.info(" 方案全部成功/部分成功的条数 " + programsList.size());
		// 代购成功/部分成功 合买 成功/部分成功
		List<UserBettors> lastList = new ArrayList<UserBettors>();
		if (programsList != null && programsList.size() > 0) {
			for (ProgramsPick ub : programsList) {
				// 购买类型
				Integer buyType = ub.getBuyType();
				if (buyType == 1) {
					// 代购
					getDaiGou(lastList, ub);
				} else if (buyType == 2) {
					// 合买
					getHM(lastList, ub);
				} else {
					// 追号
					addNumber(lastList, ub);
				}
			}
			// 将明细表入库 代购全部成功/部分成功 合买全部成功/部分成功
			logger.info(" 将整理后的结果录入到结果表userBettors中    lastList.size():  "
					+ lastList.size());
			userBettorsDao.insertUserBettors(lastList);
		}
	}

	// 追号登陆渠道
	private void addNumber(List<UserBettors> lastList, ProgramsPick ub) {
		Integer buyType = ub.getBuyType();
		// 用户code
		String userCode = ub.getUserCode();
		String partnersCode = ub.getPartnersCode();
		// 回执时间
		Date returnTime = ub.getReturnTime();
		// 成功与否
		Integer orderStatus = ub.getOrderStatus();
		Double orderAmount = ub.getOrderAmount();
		// 追号 是直接入库的
		UserBettors betTemp = new UserBettors();
		// 用户Code
		betTemp.setUserCode(userCode);
		betTemp.setBuyType(buyType);
		betTemp.setOrderStatus(orderStatus);
		betTemp.setOrderAmount(orderAmount);
		betTemp.setReturnTime(returnTime);
		betTemp.setPartnersCode(partnersCode);
		betTemp.setRenGouSuccessAmount(orderAmount);
		lastList.add(betTemp);
	}

	/**
	 * 代购成功和代购部分成功
	 * 
	 * @param lastList
	 * @param ub
	 */
	private void getDaiGou(List<UserBettors> lastList, ProgramsPick ub) {
		// 用户code
		String userCode = ub.getUserCode();
		String partnersCode = ub.getPartnersCode();
		// 回执时间
		Date returnTime = ub.getReturnTime();
		Integer buyType = ub.getBuyType();
		// 成功与否
		Integer orderStatus = ub.getOrderStatus();
		// 方案ID
		String programsOrderId = ub.getProgramsOrderId();
		Double orderAmount = ub.getOrderAmount();
		UserBettors betTemp = new UserBettors();
		// 用户Code
		betTemp.setUserCode(userCode);
		betTemp.setBuyType(buyType);
		betTemp.setOrderStatus(orderStatus);
		betTemp.setOrderAmount(orderAmount);
		betTemp.setReturnTime(returnTime);
		betTemp.setPartnersCode(partnersCode);
		// 注册渠道还没有写
		if (orderStatus == 1) {
			// 全部成功 直接入库
			betTemp.setRenGouSuccessAmount(orderAmount);
			lastList.add(betTemp);
		} else if (orderStatus == 2) {
			// 部分成功 要查票表
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("programsOrderId", programsOrderId);
			Double renGouSuccessAmount = ticketDao
					.queryAmountByProgramsOrderId(paramMap);
			betTemp.setRenGouSuccessAmount(renGouSuccessAmount);
			lastList.add(betTemp);
		}
	}

	/**
	 * 合买成功和合买部分成功
	 * 
	 * @param lastList
	 * @param ub
	 */
	private void getHM(List<UserBettors> lastList, ProgramsPick ub) {
		Integer buyType = ub.getBuyType();
		String partnersCode = ub.getPartnersCode();
		// 回执时间
		Date returnTime = ub.getReturnTime();
		// 成功与否
		Integer orderStatus = ub.getOrderStatus();
		// 方案ID
		String programsOrderId = ub.getProgramsOrderId();
		// 合买
		Double orderAmount = ub.getOrderAmount();
		if (orderStatus == 1) {
			// 查订单表关联用户表 成功， 查询条件 得带 1.时间 2.方案id
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("programsOrderId", programsOrderId);
			List<UserBettors> userHMSuccesslist = orderDao
					.queryHMAllSuccessByProgramsOrderId(paramMap);
			for (UserBettors ubAfter : userHMSuccesslist) {
				ubAfter.setPartnersCode(partnersCode);
				ubAfter.setBuyType(buyType);
				ubAfter.setOrderStatus(orderStatus);
				ubAfter.setReturnTime(returnTime);
				ubAfter.setOrderAmount(orderAmount);
			}
			lastList.addAll(userHMSuccesslist);
		} else if (orderStatus == 2) {
			// 合买部分成功
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("programsOrderId", programsOrderId);
			List<UserBettors> userHMSuccesslist = orderDao
					.queryHMByProgramsOrderId(paramMap);
			// 查票表
			paramMap.put("ticketStatus", 3);
			Double successMoney = ticketDao
					.querySuccessTicketByProgramsOrderId(paramMap);
			for (UserBettors t : userHMSuccesslist) {
				Double renGou = t.getRenGouAmount();
				BigDecimal allUserAppu1 = new BigDecimal(renGou);
				BigDecimal allUserAppu2 = new BigDecimal(orderAmount);
				BigDecimal allUserAppu3 = allUserAppu1.divide(allUserAppu2, 2,
						BigDecimal.ROUND_DOWN);
				BigDecimal allUserAppu4 = new BigDecimal(successMoney);
				Double suceess = allUserAppu3.multiply(allUserAppu4)
						.doubleValue();
				t.setRenGouSuccessAmount(suceess);
				t.setRenGouAmount(renGou);
				t.setPartnersCode(partnersCode);
				t.setBuyType(buyType);
				t.setOrderStatus(orderStatus);
				t.setReturnTime(returnTime);
				t.setOrderAmount(orderAmount);
			}
			// 查用户表
			lastList.addAll(userHMSuccesslist);
		}
	}

	// 注册渠道的相关计算
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void countDateReg(String startDate, String endDate) {
		try {
			Map<String, DataInfo> dataInfoMap = new HashMap<String, DataInfo>();
			List<Map<String, Object>> resultList = null;
			// dau
			resultList = memberLoginHistoryDao.queryDistinctGroupBySId(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "dau");
			// 激活数
			resultList = clientActiveDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "jiHuo");
			// 注册用户数
			resultList = memberDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "registeredUserCount");
			// 充值用户数
			resultList = fillDao.queryDistinctRegGroupBySid(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiUserCount");
			// 总充值金额
			resultList = fillDao.queryAllMoneyReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiAllMoney");
			// 充值新用户数：
			resultList = fillDao.queryNewDistinctRegGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserCount");
			// 新用户充值金额：当前日期，注册当天截止24:00前充值成功的金额
			resultList = fillDao.queryNewMoneryReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserMonery");
			// 投注用户数
			resultList = userBettorsDao.queryDistinctRegGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuUserCount");
			// 总投注金额
			resultList = userBettorsDao.queryAllTouZhuMoneyReg(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuAllMoney");
			// 投注新用户数
			resultList = userBettorsDao.queryTouZhuNewCountRegGroupBySid(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserCount");
			// 新用户投注金额：当前日期，注册当天截止24:00前投注成功的金额
			resultList = userBettorsDao.queryTouZhuNewMoneryCountReg(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserMonery");
			// 赠金：当前日期，获得赠金的总金额
			resultList = accountLogDao.queryGrantReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "grants");
			// 赠金消耗：当前日期，赠金消耗的总金额
			resultList = accountLogDao.queryGrantConsumeReg(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "grantsConsume");
			// 将DataInfo插入到数据库
			for (Entry<String, DataInfo> entry : dataInfoMap.entrySet()) {
				String sid = entry.getKey();
				DataInfo dataInfo = entry.getValue();
				// 总充值金额 :chongZhiAllMoney 充值用户数:chongZhiUserCount
				Double chongZhiAllMoney = dataInfo.getChongZhiAllMoney();
				Integer chongZhiUserCount = dataInfo.getChongZhiUserCount();
				// userAvgChongZhiMoney 人均充值金额：总充值金额/充值用户数
				Double userAvgChongZhiMoney = Utils.divideDouble(
						chongZhiAllMoney, chongZhiUserCount, 2);
				// 新用户充值金额:chongZhiNewUserMonery 充值新用户数:chongZhiNewUserCount
				Double chongZhiNewUserMonery = dataInfo
						.getChongZhiNewUserMonery();
				Integer chongZhiNewUserCount = dataInfo
						.getChongZhiNewUserCount();
				// chongZhiNewUserAvgMoney 新用户人均充值金额：新用户充值金额/充值新用户数
				Double chongZhiNewUserAvgMoney = Utils.divideDouble(
						chongZhiNewUserMonery, chongZhiNewUserCount, 2);
				// 充值新用户数:chongZhiNewUserCount 注册用户数:registeredUserCount
				Integer registeredUserCount = dataInfo.getRegisteredUserCount();
				// chongZhiNewUserRate
				// 新用户充值转化率：充值新用户数/注册用户数-------------------------
				Double chongZhiNewUserRate = Utils.divideInteger(
						chongZhiNewUserCount, registeredUserCount, 4);
				// 投注新用户数:touZhuNewUserCount 注册用户数:registeredUserCount
				// touZhuNewUserRate
				// 新用户投注转化率：投注新用户数/注册用户数-------------------------
				Integer touZhuNewUserCount = dataInfo.getTouZhuNewUserCount();
				Double touZhuNewUserRate = Utils.divideInteger(
						touZhuNewUserCount, registeredUserCount, 4);
				// 新用户投注金额:touZhuNewUserMonery 投注新用户数:touZhuNewUserCount
				// touZhuNewUserArpu 新用户投注ARPU：新用户投注金额/投注新用户数
				Double touZhuNewUserMonery = dataInfo.getTouZhuNewUserMonery();
				Double touZhuNewUserArpu = Utils.divideDouble(
						touZhuNewUserMonery, touZhuNewUserCount, 2);
				// 总投注金额:touZhuAllMoney 投注用户数：touZhuUserCount
				// touZhuAllUserArpu 全部用户投注ARPU：总投注金额/投注用户数
				Double touZhuAllMoney = dataInfo.getTouZhuAllMoney();
				Integer touZhuUserCount = dataInfo.getTouZhuUserCount();
				Double touZhuAllUserArpu = Utils.divideDouble(touZhuAllMoney,
						touZhuUserCount, 2);
				dataInfo.setTouZhuAllUserArpu(touZhuAllUserArpu);
				dataInfo.setTouZhuNewUserArpu(touZhuNewUserArpu);
				dataInfo.setTouZhuNewUserRate(touZhuNewUserRate * 100);
				dataInfo.setChongZhiNewUserRate(chongZhiNewUserRate * 100);
				dataInfo.setChongZhiNewUserAvgMoney(chongZhiNewUserAvgMoney);
				dataInfo.setUserAvgChongZhiMoney(userAvgChongZhiMoney);
				dataInfo.setSid(sid);
				dataInfo.setCreateTime(Utils.getDate(endDate));
				dataInfo.setDataRecordTime(Utils.getDate(startDate));
				dataInfo.setSidStatus(2);
				dataInfo.setIsCount(0);
				dataInfoDao.save(dataInfo);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveDataCount(String startDate, String endDate) {
		try {
			Integer sidStatus = 1;
			Integer isCount = 1;
			List<DataInfo> allLoginList = dataInfoDao.queryCountListAll(
					startDate, endDate, sidStatus);
			// 登陆渠道汇总计算
			saveDataCount(startDate, endDate, sidStatus, isCount, allLoginList);
			sidStatus = 2;
			List<DataInfo> allRegList = dataInfoDao.queryCountListAll(
					startDate, endDate, sidStatus);
			saveDataCount(startDate, endDate, sidStatus, isCount, allRegList);
		} catch (Exception e) {
			logger.info("当天所有的渠道统计失败", e);
		}
	}

	private void saveDataCount(String startDate, String endDate,
			Integer sidStatus, Integer isCount, List<DataInfo> allLoginList) {
		if (allLoginList != null && allLoginList.size() > 0) {
			for (DataInfo allLogin : allLoginList) {
				// DAU
				Integer dau = memberLoginHistoryDao.queryDistinctCount(
						startDate, endDate);
				// 充值用户数
				Integer chongZhiUserCount = 0;
				// 新用户充值人数
				Integer chongZhiNewUserCount = 0;
				// 投注用户数
				Integer touZhuUserCount = 0;
				// 新用户投注人数
				Integer touZhuNewUserCount = 0;
				if (sidStatus == 1) {
					// 登陆
					chongZhiUserCount = fillDao.queryDistinctLoginCount(
							startDate, endDate);
					chongZhiNewUserCount = fillDao.queryNewDistinctLoginCount(
							startDate, endDate);
					touZhuUserCount = userBettorsDao.queryDistinctLoginCount(
							startDate, endDate);
					touZhuNewUserCount = userBettorsDao
							.queryTouZhuNewCountLoginCount(startDate, endDate);
				} else if (sidStatus == 2) {
					// 注册
					chongZhiUserCount = fillDao.queryDistinctRegCount(
							startDate, endDate);
					chongZhiNewUserCount = fillDao.queryNewDistinctRegCount(
							startDate, endDate);
					touZhuUserCount = userBettorsDao.queryDistinctRegCount(
							startDate, endDate);
					touZhuNewUserCount = userBettorsDao
							.queryTouZhuNewCountRegCount(startDate, endDate);
				}
				allLogin.setIsCount(isCount);
				allLogin.setSidStatus(sidStatus);
				allLogin.setCreateTime(Utils.getDate(endDate));
				// 人均充值金额：总充值金额/充值用户数-------------------算的 userAvgChongZhiMoney
				// chongZhiAllMoney / chongZhiUserCount
				Double chongZhiAllMoney = allLogin.getChongZhiAllMoney();
				Double userAvgChongZhiMoney = Utils.divideDouble(
						chongZhiAllMoney, chongZhiUserCount, 2);
				// 新用户人均充值金额：新用户充值金额/充值新用户数-------------------算的
				// chongZhiNewUserAvgMoney
				// chongZhiNewUserMonery / chongZhiNewUserCount
				Double chongZhiNewUserMonery = allLogin
						.getChongZhiNewUserMonery();
				Double chongZhiNewUserAvgMoney = Utils.divideDouble(
						chongZhiNewUserMonery, chongZhiNewUserCount, 2);
				// 新用户充值转化率：充值新用户数/注册用户数-------------------算的
				// chongZhiNewUserRate
				// chongZhiNewUserCount/registeredUserCount
				Integer registeredUserCount = allLogin.getRegisteredUserCount();
				Double chongZhiNewUserRate = Utils.divideInteger(
						chongZhiNewUserCount, registeredUserCount, 4);
				// 新用户投注转化率：投注新用户数/注册用户数-------------------算的 touZhuNewUserRate
				// touZhuNewUserCount/registeredUserCount
				Double touZhuNewUserRate = Utils.divideInteger(
						touZhuNewUserCount, registeredUserCount, 4);
				// 新用户投注ARPU：新用户投注金额/投注新用户数-------------------算的
				// touZhuNewUserArpu
				// touZhuNewUserMonery/touZhuNewUserCount
				Double touZhuNewUserMonery = allLogin.getTouZhuNewUserMonery();
				Double touZhuNewUserArpu = Utils.divideDouble(
						touZhuNewUserMonery, touZhuNewUserCount, 2);
				// 全部用户投注ARPU：总投注金额/投注用户数-------------------算的 touZhuAllUserArpu
				// touZhuAllMoney/touZhuUserCount
				Double touZhuAllMoney = allLogin.getTouZhuAllMoney();
				Double touZhuAllUserArpu = Utils.divideDouble(touZhuAllMoney,
						touZhuUserCount, 2);
				allLogin.setDau(dau);
				allLogin.setChongZhiUserCount(chongZhiUserCount);
				allLogin.setChongZhiNewUserCount(chongZhiNewUserCount);
				allLogin.setTouZhuNewUserCount(touZhuNewUserCount);
				allLogin.setTouZhuNewUserCount(touZhuNewUserCount);
				allLogin.setUserAvgChongZhiMoney(userAvgChongZhiMoney);
				allLogin.setChongZhiNewUserAvgMoney(chongZhiNewUserAvgMoney);
				allLogin.setChongZhiNewUserRate(chongZhiNewUserRate * 100);
				allLogin.setTouZhuNewUserRate(touZhuNewUserRate * 100);
				allLogin.setTouZhuNewUserArpu(touZhuNewUserArpu);
				allLogin.setTouZhuAllUserArpu(touZhuAllUserArpu);
				dataInfoDao.save(allLogin);
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void countDateLogin(String startDate, String endDate) {
		try {
			Map<String, DataInfo> dataInfoMap = new HashMap<String, DataInfo>();
			List<Map<String, Object>> resultList = null;
			// dau
			resultList = memberLoginHistoryDao.queryDistinctGroupBySId(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "dau");
			// 激活数
			resultList = clientActiveDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "jiHuo");
			// 注册用户数
			resultList = memberDao.queryDistinct(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "registeredUserCount");
			// 充值用户数
			resultList = fillDao.queryDistinctLoginGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiUserCount");
			// 总充值金额
			resultList = fillDao.queryAllMoneyLogin(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiAllMoney");
			// 充值新用户数：
			resultList = fillDao.queryNewDistinctLoginGroupBySid(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserCount");
			// 新用户充值金额：当前日期，注册当天截止24:00前充值成功的金额
			resultList = fillDao.queryNewMoneryLogin(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "chongZhiNewUserMonery");
			// 投注用户数
			resultList = userBettorsDao.queryDistinctLoginGroupBySId(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuUserCount");
			// 总投注金额
			resultList = userBettorsDao.queryAllTouZhuMoneyLogin(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuAllMoney");
			// 投注新用户数
			resultList = userBettorsDao.queryTouZhuNewCountLoginGroupBySid(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserCount");
			// 新用户投注金额：当前日期，注册当天截止24:00前投注成功的金额
			resultList = userBettorsDao.queryTouZhuNewMoneryCountLogin(
					startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "touZhuNewUserMonery");
			// 赠金：当前日期，获得赠金的总金额
			resultList = accountLogDao.queryGrantLogin(startDate, endDate);
			setDataInfo(dataInfoMap, resultList, "grants");
			// 赠金消耗：当前日期，赠金消耗的总金额
			resultList = accountLogDao.queryGrantConsumeLogin(startDate,
					endDate);
			setDataInfo(dataInfoMap, resultList, "grantsConsume");
			logger.info("录入到dataInfo的条数为：dataInfoMap.entrySet().size()："
					+ dataInfoMap.entrySet().size());
			// 将DataInfo插入到数据库
			for (Entry<String, DataInfo> entry : dataInfoMap.entrySet()) {
				String sid = entry.getKey();
				DataInfo dataInfo = entry.getValue();
				// 总充值金额 :chongZhiAllMoney 充值用户数:chongZhiUserCount
				Double chongZhiAllMoney = dataInfo.getChongZhiAllMoney();
				Integer chongZhiUserCount = dataInfo.getChongZhiUserCount();
				// userAvgChongZhiMoney 人均充值金额：总充值金额/充值用户数
				Double userAvgChongZhiMoney = Utils.divideDouble(
						chongZhiAllMoney, chongZhiUserCount, 2);
				// 新用户充值金额:chongZhiNewUserMonery 充值新用户数:chongZhiNewUserCount
				Double chongZhiNewUserMonery = dataInfo
						.getChongZhiNewUserMonery();
				Integer chongZhiNewUserCount = dataInfo
						.getChongZhiNewUserCount();
				// chongZhiNewUserAvgMoney 新用户人均充值金额：新用户充值金额/充值新用户数
				Double chongZhiNewUserAvgMoney = Utils.divideDouble(
						chongZhiNewUserMonery, chongZhiNewUserCount, 2);
				// 充值新用户数:chongZhiNewUserCount 注册用户数:registeredUserCount
				Integer registeredUserCount = dataInfo.getRegisteredUserCount();
				// chongZhiNewUserRate 新用户充值转化率：充值新用户数/注册用户数
				Double chongZhiNewUserRate = Utils.divideInteger(
						chongZhiNewUserCount, registeredUserCount, 4);
				// 投注新用户数:touZhuNewUserCount 注册用户数:registeredUserCount
				// touZhuNewUserRate 新用户投注转化率：投注新用户数/注册用户数
				Integer touZhuNewUserCount = dataInfo.getTouZhuNewUserCount();
				Double touZhuNewUserRate = Utils.divideInteger(
						touZhuNewUserCount, registeredUserCount, 4);
				// 新用户投注金额:touZhuNewUserMonery 投注新用户数:touZhuNewUserCount
				// touZhuNewUserArpu 新用户投注ARPU：新用户投注金额/投注新用户数
				Double touZhuNewUserMonery = dataInfo.getTouZhuNewUserMonery();
				Double touZhuNewUserArpu = Utils.divideDouble(
						touZhuNewUserMonery, touZhuNewUserCount, 2);
				// 总投注金额:touZhuAllMoney 投注用户数：touZhuUserCount
				// touZhuAllUserArpu 全部用户投注ARPU：总投注金额/投注用户数
				Double touZhuAllMoney = dataInfo.getTouZhuAllMoney();
				Integer touZhuUserCount = dataInfo.getTouZhuUserCount();
				Double touZhuAllUserArpu = Utils.divideDouble(touZhuAllMoney,
						touZhuUserCount, 2);
				dataInfo.setTouZhuAllUserArpu(touZhuAllUserArpu);
				dataInfo.setTouZhuNewUserArpu(touZhuNewUserArpu);
				dataInfo.setTouZhuNewUserRate(touZhuNewUserRate * 100);
				dataInfo.setChongZhiNewUserRate(chongZhiNewUserRate * 100);
				dataInfo.setChongZhiNewUserAvgMoney(chongZhiNewUserAvgMoney);
				dataInfo.setUserAvgChongZhiMoney(userAvgChongZhiMoney);
				dataInfo.setSid(sid);
				dataInfo.setCreateTime(Utils.getDate(endDate));
				dataInfo.setDataRecordTime(Utils.getDate(startDate));
				dataInfo.setSidStatus(1);
				dataInfo.setIsCount(0);
				dataInfoDao.save(dataInfo);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void dealList(List<Map<String, Object>> list,
			List<Map<String, Object>> insertData, String mapKey, String dataType) {
		if (list != null && list.size() > 0) {
			for (Map<String, Object> registered : list) {
				String sid = (String) registered.get("SID");
				boolean flag = false;
				for (Map<String, Object> insert : insertData) {
					if (insert.containsValue(sid)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					// 渠道相等
					for (Map<String, Object> insert : insertData) {
						if (insert.containsValue(sid)) {
							Object ob = registered.get("COUNT");
							if (dataType.equals("double")) {
								Double countDouble = Double.parseDouble(ob
										.toString());
								insert.put(mapKey, countDouble);
							} else if (dataType.equals("integer")) {
								Integer countInteger = Integer.parseInt(ob
										.toString());
								insert.put(mapKey, countInteger);
							}
							break;
						}
					}
				} else {
					// 渠道不相等
					Map<String, Object> tem = new HashMap<String, Object>();
					Object ob = registered.get("COUNT");
					if (dataType.equals("double")) {
						Double countDouble = Double.parseDouble(ob.toString());
						tem.put(mapKey, countDouble);
					} else {
						Integer countInteger = Integer.parseInt(ob.toString());
						tem.put(mapKey, countInteger);
					}
					tem.put("SID", sid);
					insertData.add(tem);
				}
			}
		}
	}

	public void setDataInfo(Map<String, DataInfo> dataInfoMap,
			List<Map<String, Object>> resultList, String columnName) {
		if (resultList == null || resultList.size() == 0) {
			logger.info("-----》" + columnName);
			return;
		}
		DataInfo dataInfo = null;
		for (Map<String, Object> result : resultList) {
			String sid = (String) result.get("SID");
			dataInfo = new DataInfo();
			if (Utils.isNotEmpty(dataInfoMap.get(sid))) {
				dataInfo = dataInfoMap.get(sid);
			}
			if ("dau".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setDau(Integer.parseInt(result.get("COUNT")
							.toString()));
				} else {
					dataInfo.setDau(0);
				}
			}
			if ("jiHuo".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setJiHuo(Integer.parseInt(result.get("COUNT")
							.toString()));
				} else {
					dataInfo.setJiHuo(0);
				}
			}
			if ("registeredUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setRegisteredUserCount(Integer.parseInt(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setRegisteredUserCount(0);
				}
			}
			if ("chongZhiUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiUserCount(Integer.parseInt(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setChongZhiUserCount(0);
				}
			}
			if ("chongZhiAllMoney".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiAllMoney(Double.parseDouble(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setChongZhiAllMoney(0d);
				}
			}
			if ("chongZhiNewUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiNewUserCount(Integer.parseInt(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setChongZhiNewUserCount(0);
				}
			}
			if ("chongZhiNewUserMonery".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setChongZhiNewUserMonery(Double.parseDouble(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setChongZhiNewUserMonery(0d);
				}
			}
			if ("touZhuUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuUserCount(Integer.parseInt(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setTouZhuUserCount(0);
				}
			}
			if ("touZhuAllMoney".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuAllMoney(Double.parseDouble(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setTouZhuAllMoney(0d);
				}
			}
			if ("touZhuNewUserCount".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuNewUserCount(Integer.parseInt(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setTouZhuNewUserCount(0);
				}
			}
			if ("touZhuNewUserMonery".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setTouZhuNewUserMonery(Double.parseDouble(result
							.get("COUNT").toString()));
				} else {
					dataInfo.setTouZhuNewUserMonery(0d);
				}
			}
			if ("grants".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setGrants(Double.parseDouble(result.get("COUNT")
							.toString()));
				} else {
					dataInfo.setGrants(0d);
				}
			}
			if ("grantsConsume".equals(columnName)) {
				if (Utils.isNotEmpty(result.get("COUNT"))) {
					dataInfo.setGrantsConsume(Double.parseDouble(result.get(
							"COUNT").toString()));
				} else {
					dataInfo.setGrantsConsume(0d);
				}
			}
			dataInfoMap.put(sid, dataInfo);
		}
	}
}
