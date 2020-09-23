package com.cndym.entity.data.pick;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 
 * @author Administrator
 */
public class DataInfo implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	// 渠道ID
	
	private String sid;
	// 数据记录的时间[数据的时间]
	private Date dataRecordTime;
	// 激活数：去重激活设备数(需要服务端提供数据）
	private Integer jiHuo=0;
	// DAU：当前日期，去重登录用户数（以登录来源为准）
	private Integer dau=0;
	// 注册用户数：当前日期，完成注册的新增用户数量
	private Integer registeredUserCount=0;
	// 充值用户数：当前日期，充值成功的去重用户数量
	private Integer chongZhiUserCount=0;
	// 投注用户数：当前日期，投注成功的去重用户数量，如果是合买认购的用户，每个用户都记为1
	private Integer touZhuUserCount=0;
	// 总充值金额：当前日期,充值成功的总金额
	private Double chongZhiAllMoney=0.00d;
	// 人均充值金额：总充值金额/充值用户数-------------------算的
	private Double userAvgChongZhiMoney=0.00d;
	// 充值新用户数：当前日期，注册当天截止24:00前充值成功的去重用户数
	private Integer chongZhiNewUserCount=0;
	// 新用户充值金额：当前日期，注册当天截止24:00前充值成功的金额
	private Double chongZhiNewUserMonery=0.00d;
	// 新用户人均充值金额：新用户充值金额/充值新用户数-------------------算的
	private Double chongZhiNewUserAvgMoney=0.00d;
	// 总投注金额：当前日期，投注成功的总金额
	private Double touZhuAllMoney=0.00d;
	// 投注新用户数：当前日期，注册当天截止24:00前投注成功的去重用户数
	private Integer touZhuNewUserCount=0;
	// 新用户投注金额：当前日期，注册当天截止24:00前投注成功的金额
	private Double touZhuNewUserMonery=0.00d;
	// 新用户充值转化率：充值新用户数/注册用户数-------------------算的
	private Double chongZhiNewUserRate=0.00d;
	// 新用户投注转化率：投注新用户数/注册用户数-------------------算的
	private Double touZhuNewUserRate=0.00d;
	// 新用户投注ARPU：新用户投注金额/投注新用户数-------------------算的
	private Double touZhuNewUserArpu=0.00d;
	// 全部用户投注ARPU：总投注金额/投注用户数-------------------算的
	private Double touZhuAllUserArpu=0.00d;
	// 赠金：当前日期，获得赠金的总金额
	private Double grants=0.00d;
	// 赠金消耗：当前日期，赠金消耗的总金额
	private Double grantsConsume=0.00d;
	// 该条记录创建的时间
	private Date createTime;
	// 预留字段，判断是登陆1 sid 还是注册2 sid
	private Integer sidStatus;
	//0:普通数据，1：统计后的数据,按照天统计
	private Integer isCount; 
	
	
	
    //以下字段，不映射到数据库
	// 开始时间 
	private String startTime;
	// 结束时间
	private String endTime;
	//渠道名称，导出数据是使用的字段
	private String channelName;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Date getDataRecordTime() {
		return dataRecordTime;
	}

	public Integer getJiHuo() {
		return jiHuo;
	}

	public void setJiHuo(Integer jiHuo) {
		this.jiHuo = jiHuo;
	}

	public void setDataRecordTime(Date dataRecordTime) {
		this.dataRecordTime = dataRecordTime;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	
	public String getTimeFormat(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(dataRecordTime);
		return time;
	}
	
	
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

	
	public Integer getDau() {
		return dau;
	}

	public void setDau(Integer dau) {
		this.dau = dau;
	}

	public Integer getRegisteredUserCount() {
		return registeredUserCount;
	}

	public void setRegisteredUserCount(Integer registeredUserCount) {
		this.registeredUserCount = registeredUserCount;
	}

	public Integer getChongZhiUserCount() {
		return chongZhiUserCount;
	}

	public void setChongZhiUserCount(Integer chongZhiUserCount) {
		this.chongZhiUserCount = chongZhiUserCount;
	}

	public Integer getTouZhuUserCount() {
		return touZhuUserCount;
	}

	public void setTouZhuUserCount(Integer touZhuUserCount) {
		this.touZhuUserCount = touZhuUserCount;
	}

	public Double getChongZhiAllMoney() {
		
		return chongZhiAllMoney;
	}

	public void setChongZhiAllMoney(Double chongZhiAllMoney) {
		this.chongZhiAllMoney = chongZhiAllMoney;
	}

	public Double getUserAvgChongZhiMoney() {
		return userAvgChongZhiMoney;
	}

	public void setUserAvgChongZhiMoney(Double userAvgChongZhiMoney) {
		this.userAvgChongZhiMoney = userAvgChongZhiMoney;
	}

	public Double getTouZhuAllMoney() {
		return touZhuAllMoney;
	}

	public void setTouZhuAllMoney(Double touZhuAllMoney) {
		this.touZhuAllMoney = touZhuAllMoney;
	}

	public Integer getChongZhiNewUserCount() {
		return chongZhiNewUserCount;
	}

	public void setChongZhiNewUserCount(Integer chongZhiNewUserCount) {
		this.chongZhiNewUserCount = chongZhiNewUserCount;
	}

	public Integer getTouZhuNewUserCount() {
		return touZhuNewUserCount;
	}

	public void setTouZhuNewUserCount(Integer touZhuNewUserCount) {
		this.touZhuNewUserCount = touZhuNewUserCount;
	}

	public Double getChongZhiNewUserMonery() {
		return chongZhiNewUserMonery;
	}

	public void setChongZhiNewUserMonery(Double chongZhiNewUserMonery) {
		this.chongZhiNewUserMonery = chongZhiNewUserMonery;
	}

	public Double getChongZhiNewUserAvgMoney() {
		return chongZhiNewUserAvgMoney;
	}

	public void setChongZhiNewUserAvgMoney(Double chongZhiNewUserAvgMoney) {
		this.chongZhiNewUserAvgMoney = chongZhiNewUserAvgMoney;
	}

	public Double getTouZhuNewUserMonery() {
		return touZhuNewUserMonery;
	}

	public void setTouZhuNewUserMonery(Double touZhuNewUserMonery) {
		this.touZhuNewUserMonery = touZhuNewUserMonery;
	}

	public Double getChongZhiNewUserRate() {
		return chongZhiNewUserRate;
	}

	public void setChongZhiNewUserRate(Double chongZhiNewUserRate) {
		this.chongZhiNewUserRate = chongZhiNewUserRate;
	}

	public Double getTouZhuNewUserRate() {
		return touZhuNewUserRate;
	}

	public void setTouZhuNewUserRate(Double touZhuNewUserRate) {
		this.touZhuNewUserRate = touZhuNewUserRate;
	}

	public Double getTouZhuNewUserArpu() {
		return touZhuNewUserArpu;
	}

	public void setTouZhuNewUserArpu(Double touZhuNewUserArpu) {
		this.touZhuNewUserArpu = touZhuNewUserArpu;
	}

	public Double getTouZhuAllUserArpu() {
		return touZhuAllUserArpu;
	}

	public void setTouZhuAllUserArpu(Double touZhuAllUserArpu) {
		this.touZhuAllUserArpu = touZhuAllUserArpu;
	}

	public Double getGrants() {
		return grants;
	}

	public void setGrants(Double grants) {
		this.grants = grants;
	}

	public Double getGrantsConsume() {
		return grantsConsume;
	}

	public void setGrantsConsume(Double grantsConsume) {
		this.grantsConsume = grantsConsume;
	}

	public Integer getSidStatus() {
		return sidStatus;
	}

	public void setSidStatus(Integer sidStatus) {
		this.sidStatus = sidStatus;
	}

	 
	@Override
	public String toString() {
		String s="jihuo："+jiHuo+"dau："+dau+"registeredUserCount："+registeredUserCount+"chongZhiUserCount："+chongZhiUserCount
				+"touZhuUserCount："+touZhuUserCount+"chongZhiAllMoney："+chongZhiAllMoney+"userAvgChongZhiMoney："+userAvgChongZhiMoney+"chongZhiNewUserCount："+chongZhiNewUserCount
				+"chongZhiNewUserMonery："+chongZhiNewUserMonery+"chongZhiNewUserAvgMoney："+chongZhiNewUserAvgMoney+"touZhuAllMoney："+touZhuAllMoney
				+"touZhuNewUserCount："+touZhuNewUserCount+"touZhuNewUserMonery："+touZhuNewUserMonery+"chongZhiNewUserRate："+chongZhiNewUserRate
				+"touZhuNewUserRate："+touZhuNewUserRate+"touZhuNewUserArpu："+touZhuNewUserArpu
				+"touZhuAllUserArpu："+touZhuAllUserArpu+"grants："+grants+"grantsConsume："+grantsConsume+"sidStatus："+sidStatus
				;
		return s;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getIsCount() {
		return isCount;
	}

	public void setIsCount(Integer isCount) {
		this.isCount = isCount;
	}
}
