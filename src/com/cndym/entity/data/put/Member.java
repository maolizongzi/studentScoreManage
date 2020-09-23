package com.cndym.entity.data.put;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表USER_MEMBER
 * 
 * @author Administrator
 * 
 */
public class Member implements Serializable {
	private static final long serialVersionUID = -1498894662338670519L;
	private Long id;
	private String userCode;
	private String userName;
	private String mobile;
	private String email;
	private String passWord;
	private Integer passWordStrength;
	private String realName;
	private Integer cardType;
	private String cardCode;
	private Integer status;
	private String registerIp;
	private String registerApp;
	private String loginIp;
	private Date loginTime;
	private Integer loginCount;
	private Date createTime;
	private Long memberId;
	private String unionId;
	private Integer memberCount;
	private String birthday;
	private Integer sex;
	private String address;
	private Integer memberType;
	private Integer trackUnion;
	private String loginApp;
	private String qq;
	private String msn;
	private String backup1;
	private String province;
	private String city;
	private String subBank;
	private String bankName;
	private String bankCode;
	private String machineCode;
	private String orderBank;
	private String softVer;
	private Integer noStatus;

	public Member() {
	}

	public Member(Long id, String userCode, String userName, String mobile,
			String email, String passWord, Integer passWordStrength,
			String realName, Integer cardType, String cardCode, Integer status,
			String registerIp, String registerApp, String loginIp,
			Date loginTime, Integer loginCount, Date createTime, Long memberId,
			String unionId, Integer memberCount, String birthday, Integer sex,
			String address, Integer memberType, Integer trackUnion,
			String loginApp, String qq, String msn, String backup1,
			String province, String city, String subBank, String bankName,
			String bankCode, String machineCode, String softVer,
			String orderBank) {
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.mobile = mobile;
		this.email = email;
		this.passWord = passWord;
		this.passWordStrength = passWordStrength;
		this.realName = realName;
		this.cardType = cardType;
		this.cardCode = cardCode;
		this.status = status;
		this.registerIp = registerIp;
		this.registerApp = registerApp;
		this.loginIp = loginIp;
		this.loginTime = loginTime;
		this.loginCount = loginCount;
		this.createTime = createTime;
		this.memberId = memberId;
		this.unionId = unionId;
		this.memberCount = memberCount;
		this.birthday = birthday;
		this.sex = sex;
		this.address = address;
		this.memberType = memberType;
		this.trackUnion = trackUnion;
		this.loginApp = loginApp;
		this.qq = qq;
		this.msn = msn;
		this.backup1 = backup1;
		this.province = province;
		this.city = city;
		this.subBank = subBank;
		this.bankName = bankName;
		this.bankCode = bankCode;
		this.machineCode = machineCode;
		this.softVer = softVer;
		this.orderBank = orderBank;
	}

	public Member(Long id) {
		this.id = id;
	}

	public Member(String userCode) {
		this.userCode = userCode;
	}

	public Integer getMemberType() {
		return this.memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public Integer getPassWordStrength() {
		return this.passWordStrength;
	}

	public void setPassWordStrength(Integer passWordStrength) {
		this.passWordStrength = passWordStrength;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public Integer getCardType() {
		return this.cardType;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardCode() {
		return this.cardCode;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getRegisterIp() {
		return this.registerIp;
	}

	public void setRegisterApp(String registerApp) {
		this.registerApp = registerApp;
	}

	public String getRegisterApp() {
		return this.registerApp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Integer getLoginCount() {
		return this.loginCount;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getMemberCount() {
		return this.memberCount;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNoStatus() {
		return this.noStatus;
	}

	public void setNoStatus(Integer noStatus) {
		this.noStatus = noStatus;
	}

	public Integer getTrackUnion() {
		return this.trackUnion;
	}

	public void setTrackUnion(Integer trackUnion) {
		this.trackUnion = trackUnion;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getLoginApp() {
		return this.loginApp;
	}

	public void setLoginApp(String loginApp) {
		this.loginApp = loginApp;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSubBank() {
		return this.subBank;
	}

	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}

	public String getBackup1() {
		return this.backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getMachineCode() {
		return this.machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getSoftVer() {
		return this.softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public String getOrderBank() {
		return this.orderBank;
	}

	public void setOrderBank(String orderBank) {
		this.orderBank = orderBank;
	}
}
