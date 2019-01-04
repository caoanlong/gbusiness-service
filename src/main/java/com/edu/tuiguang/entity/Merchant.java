package com.edu.tuiguang.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 商家
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Merchant extends Base {
	private Integer merchantId;
	/** 商家名称 **/
	private String merchantName;
	/** 商家图片信息 **/
	private String merchantImage;
	/** 推广次数 **/
	private Integer promotionCount;
	/** 销售次数 **/
	private Integer sellCount;
	/** 商家会员ID **/
	private Integer merchantMemberId;

	private String merchantMemberName;
	private String merchantMemberMobile;
	private String merchantMemberIndustry;

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantImage() {
		return merchantImage;
	}

	public void setMerchantImage(String merchantImage) {
		this.merchantImage = merchantImage;
	}

	public Integer getPromotionCount() {
		return promotionCount;
	}

	public void setPromotionCount(Integer promotionCount) {
		this.promotionCount = promotionCount;
	}

	public Integer getSellCount() {
		return sellCount;
	}

	public void setSellCount(Integer sellCount) {
		this.sellCount = sellCount;
	}

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

	public String getMerchantMemberMobile() {
		return merchantMemberMobile;
	}

	public void setMerchantMemberMobile(String merchantMemberMobile) {
		this.merchantMemberMobile = merchantMemberMobile;
	}

	public String getMerchantMemberIndustry() {
		return merchantMemberIndustry;
	}

	public void setMerchantMemberIndustry(String merchantMemberIndustry) {
		this.merchantMemberIndustry = merchantMemberIndustry;
	}
}
