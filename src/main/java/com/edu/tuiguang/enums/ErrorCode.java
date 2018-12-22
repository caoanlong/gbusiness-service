package com.edu.tuiguang.enums;

public enum ErrorCode {
	/**
	 * 用户名不能为空
	 */
	USERNAME_NOTNULL(100, "用户名不能为空"),
	/**
	 * 密码不能为空
	 */
	PASSWORD_NOTNULL(101, "密码不能为空"),
	/**
	 * 密码错误
	 */
	PASSWORD_ERROR(102, "密码错误"),
	/**
	 * 用户不存在
	 */
	USERNAMEORPASSWORD_ERROR(103, "帐号或密码错误"),
	/**
	 * 未登录错误
	 */
	UNLOGIN_ERROR(104, "未登录"),
	/**
	 * token错误
	 */
	TOKEN_ERROR(105, "token错误"),
	/**
	 * token过期
	 */
	TOKEN_EXPIRE(106, "token过期"),
	/**
	 * 角色不能为空
	 */
	ROLE_NOTNULL(1000, "角色不能为空"),

	/**
	 * 用户ID不能为空
	 */
	USERID_NOTNULL(1001, "用户ID不能为空"),

	/**
	 * 角色ID不能为空
	 */
	ROLEID_NOTNULL(1002, "角色ID不能为空"),
	/**
	 * 未知错误
	 */
	UNKONW_ERROR(-1, "未知错误");

	private Integer code;

	private String msg;

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	ErrorCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
