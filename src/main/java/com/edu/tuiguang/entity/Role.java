package com.edu.tuiguang.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 系统角色
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends Base {
	private Integer roleId;
	private String roleName;
	private String permissions;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
}
