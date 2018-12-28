package com.edu.tuiguang.entity.req;

import java.util.Date;
import java.util.List;

public class ActivityDto {
	private Integer activityId;
	/** 活动名称 **/
	private String name;
	/** 活动banner **/
	private String banner;
	/** 活动价格 **/
	private Float price;
	/** 购买须知 **/
	private String notes;
	/** 活动介绍 **/
	private String introduction;
	/** 活动商家 **/
	private List<Integer> merchants;
	/** 活动截止时间 **/
	private Date endTime;

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<Integer> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Integer> merchants) {
		this.merchants = merchants;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
