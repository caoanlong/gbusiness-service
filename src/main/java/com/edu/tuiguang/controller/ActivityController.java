package com.edu.tuiguang.controller;

import com.edu.tuiguang.entity.Activity;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.entity.Role;
import com.edu.tuiguang.entity.req.ActivityDto;
import com.edu.tuiguang.service.ActivityService;
import com.edu.tuiguang.service.RoleService;
import com.edu.tuiguang.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	private ActivityService activityService;

	@GetMapping("/findAll")
	public ResultBean findAll(String activityName) {
		List<Activity> all = activityService.findAll(activityName);
		return ResultUtils.success(all);
	}

	@GetMapping("/findList")
	public ResultBean findList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "activityName", required = false) String activityName
	) {
		PageBean<List<Activity>> list = activityService.findList(pageIndex, pageSize, activityName);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("activityId") Integer activityId) {
		Activity activity = activityService.findById(activityId);
		return ResultUtils.success(activity);
	}

	@GetMapping("/findDetail")
	public ResultBean findDetail(@RequestParam("activityId") Integer activityId) {
		return findById(activityId);
	}

	@PostMapping("/add")
	public ResultBean add(HttpServletRequest request, @RequestBody ActivityDto activityDto) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		activityService.insert(activityDto, userId);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(HttpServletRequest request, @RequestBody ActivityDto activityDto) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		activityService.update(activityDto, userId);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestBody Map<String, Integer> map) {
		Integer activityId = map.get("activityId");
		activityService.del(activityId);
		return ResultUtils.success();
	}
}
