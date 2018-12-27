package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository {
	List<Activity> findAll(@Param("activityName") String activityName);
	List<Activity> findList(
			@Param("pageStart") Integer pageStart,
			@Param("pageSize") Integer pageSize,
			@Param("activityName") String activityName
	);
	Activity findById(Integer activityId);
	Long total(@Param("activityName") String activityName);
	void insert(Activity activity);
	void update(Activity activity);
	void del(Integer activityId);
}
