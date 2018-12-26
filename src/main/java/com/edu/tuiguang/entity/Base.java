package com.edu.tuiguang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Base implements Serializable {
//	@JsonIgnore
	private User createBy;
	private String createName;
//	@JsonIgnore
	private Integer createUserId;
//	@JsonIgnore
	private User updateBy;
	private String updateName;
//	@JsonIgnore
	private Integer updateUserId;
	private Date createTime;
	private Date updateTime;

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return null == createBy ? null : createBy.getUserName();
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateName() {
		return null == updateBy ? null : updateBy.getUserName();
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
