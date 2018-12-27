package com.edu.tuiguang.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantMember {
	private Integer merchantMemberId;
	private String merchantMemberName;
	private String mobile;
	/** 所在行业 **/
	private String industry;
	/** 是否已添加商户 **/
	private String isAddMerchant;
	private Date createTime;

	public Integer getMerchantMemberId() {
		return merchantMemberId;
	}

	public void setMerchantMemberId(Integer merchantMemberId) {
		this.merchantMemberId = merchantMemberId;
	}

	public String getMerchantMemberName() {
		return merchantMemberName;
	}

	public void setMerchantMemberName(String merchantMemberName) {
		this.merchantMemberName = merchantMemberName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsAddMerchant() {
		return isAddMerchant;
	}

	public void setIsAddMerchant(String isAddMerchant) {
		this.isAddMerchant = isAddMerchant;
	}
}
