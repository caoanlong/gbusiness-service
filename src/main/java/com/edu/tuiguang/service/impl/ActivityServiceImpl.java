package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.Activity;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.repository.ActivityRepository;
import com.edu.tuiguang.service.ActivityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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
	public void insert(Activity activity) {

	}

	@Override
	public void update(Activity activity) {
		if (null == activity.getActivityId() || StringUtils.isBlank(activity.getActivityId().toString()))
			throw new CommonException(ErrorCode.ACTIVITYID_NOTNULL);
		activity.setUpdateTime(new Date());
		activityRepository.update(activity);
	}

	@Override
	public void del(Integer activityId) {
		activityRepository.del(activityId);
	}
}
