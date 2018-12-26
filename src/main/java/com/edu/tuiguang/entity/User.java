package com.edu.tuiguang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User extends Base {
	private Integer userId;
	private String userName;
	private String password;
	private String avatar;
	private String mobile;
	private Integer roleId;
	@JsonIgnore
	private Role role;
	private String roleName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getRoleName() {
		return null == role ? null : role.getRoleName();
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
