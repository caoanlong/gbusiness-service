package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Activity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
	Integer insert(Activity activity);
	void update(Activity activity);
	void del(Integer activityId);
	void insertActivityMerchants(List<Map<String, Integer>> list);
}
