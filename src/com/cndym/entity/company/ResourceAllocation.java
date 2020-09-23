package com.cndym.entity.company;

import java.io.Serializable;

public class ResourceAllocation implements Serializable {

	/**
	 * @author 程禄元 2016.3.22 16:27
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String resName; // 名称
	private String code; // 编号
	private String contacts;// 联系人
	private String phone; // 电话
	private String address;// 地址
	private Integer mode; // 方式
	private String note;// 备注
	private Integer resType;// 类型 1-合作公司；2-合作方式；3-合作产品；4-结算方式
							// COOPER_SEQ_RESOURCE_ALLOCATION

	public ResourceAllocation() {

	}

	public ResourceAllocation(Long id, String resName, String code,
			String contacts, String phone, String address, Integer mode,
			String note, Integer resType) {
		super();
		this.id = id;
		this.resName = resName;
		this.code = code;
		this.contacts = contacts;
		this.phone = phone;
		this.address = address;
		this.mode = mode;
		this.note = note;
		this.resType = resType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

}
