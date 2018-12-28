package com.edu.tuiguang.service;

import com.edu.tuiguang.entity.Activity;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.req.ActivityDto;

import java.util.List;

public interface ActivityService {
	List<Activity> findAll(String activityName);
	PageBean<List<Activity>> findList(Integer pageIndex, Integer pageSize, String activityName);
	Activity findById(Integer activityId);
	void insert(ActivityDto activityDto, Integer userId);
	void update(Activity activity);
	void del(Integer activityId);
}
