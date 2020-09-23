package com.cndym.bean;

import java.io.Serializable;

/**
*
* 角色组
* @author lina
* @time	2020/09/02 10：46
*/
public class UserGroup  implements Serializable {
	private static final long serialVersionUID = 1697539970349488459L;
	private int  groupId	;//主键，自增长
	private String gourName	;//	角色名称
	private String groupPermissions	;//	拥有的权限aa@ab@ac@ad
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGourName() {
		return gourName;
	}
	public void setGourName(String gourName) {
		this.gourName = gourName;
	}
	public String getGroupPermissions() {
		return groupPermissions;
	}
	public void setGroupPermissions(String groupPermissions) {
		this.groupPermissions = groupPermissions;
	}
}
