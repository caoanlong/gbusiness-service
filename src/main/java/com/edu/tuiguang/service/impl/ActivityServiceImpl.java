package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.Activity;
import com.edu.tuiguang.entity.Merchant;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.entity.req.ActivityDto;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.repository.ActivityRepository;
import com.edu.tuiguang.service.ActivityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Activity> findAll(String activityName) {
		return activityRepository.findAll(activityName);
	}

	@Override
	public PageBean<List<Activity>> findList(Integer pageIndex, Integer pageSize, String activityName) {
		Integer pageStart = (pageIndex - 1) * pageSize;
		List<Activity> list = activityRepository.findList(pageStart, pageSize, activityName);
		Long total = activityRepository.total(activityName);
		PageBean<List<Activity>> pageBean = new PageBean<>();
		pageBean.setList(list);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public Activity findById(Integer activityId) {
		return activityRepository.findById(activityId);
	}

	@Override
	@Transactional
	public void insert(ActivityDto activityDto, Integer userId) {
		if (StringUtils.isBlank(activityDto.getName()))
			throw new CommonException(ErrorCode.ACTIVITYNAME_NOTNULL);
		if (StringUtils.isBlank(activityDto.getBanner()))
			throw new CommonException(ErrorCode.ACTIVITYBANNER_NOTNULL);
		if (StringUtils.isBlank(activityDto.getNotes()))
			throw new CommonException(ErrorCode.ACTIVITYNOTES_NOTNULL);
		if (null == activityDto.getPrice() || StringUtils.isBlank(activityDto.getPrice().toString()))
			throw new CommonException(ErrorCode.ACTIVITYPRICE_NOTNULL);
		if (null == activityDto.getEndTime() || StringUtils.isBlank(activityDto.getEndTime().toString()))
			throw new CommonException(ErrorCode.ACTIVITYENDTIME_NOTNULL);

		Activity activity = new Activity();
		activity.setCreateUserId(userId);
		activity.setCreateTime(new Date());
		activity.setName(activityDto.getName());
		activity.setBanner(activityDto.getBanner());
		activity.setPrice(activityDto.getPrice());
		activity.setNotes(activityDto.getNotes());
		activity.setIntroduction(activityDto.getIntroduction());
		activity.setEndTime(activityDto.getEndTime());
		activityRepository.insert(activity);
		Integer activityId = activity.getActivityId();
		List<Integer> merchantIds = activityDto.getMerchantIds();
		insertActivityMerchants(merchantIds, activityId);
	}

	@Override
	@Transactional
	public void update(ActivityDto activityDto, Integer userId) {
		Integer activityId = activityDto.getActivityId();
		if (null == activityId || StringUtils.isBlank(activityId.toString()))
			throw new CommonException(ErrorCode.ACTIVITYID_NOTNULL);
		Activity activity = new Activity();
		activity.setUpdateUserId(userId);
		activity.setUpdateTime(new Date());
		activity.setActivityId(activityId);
		if (StringUtils.isNotBlank(activityDto.getName()))
			activity.setName(activityDto.getName());
		if (StringUtils.isNotBlank(activityDto.getBanner()))
			activity.setBanner(activityDto.getBanner());
		if (StringUtils.isNotBlank(activityDto.getPrice().toString()))
			activity.setPrice(activityDto.getPrice());
		if (StringUtils.isNotBlank(activityDto.getNotes()))
			activity.setNotes(activityDto.getNotes());
		if (StringUtils.isNotBlank(activityDto.getIntroduction()))
			activity.setIntroduction(activityDto.getIntroduction());
		if (null != activityDto.getEndTime())
			activity.setEndTime(activityDto.getEndTime());
		activityRepository.update(activity);
		activityRepository.delActivityMerchantsByActivityId(activityId);
		List<Integer> merchantIds = activityDto.getMerchantIds();
		insertActivityMerchants(merchantIds, activityId);
	}

	private void insertActivityMerchants(List<Integer> merchantIds, Integer activityId) {
		if (merchantIds.size() < 1) return;
		List<Map<String, Integer>> list = new ArrayList<>();
		for (Integer merchantId: merchantIds) {
			Map<String, Integer> map = new HashMap<>();
			map.put("activityId", activityId);
			map.put("merchantId", merchantId);
			list.add(map);
		}
		activityRepository.insertActivityMerchants(list);
	}

	@Override
	public void del(Integer activityId) {
		activityRepository.del(activityId);
	}
}
