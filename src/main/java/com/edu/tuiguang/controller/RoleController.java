package com.edu.tuiguang.controller;

import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.entity.Role;
import com.edu.tuiguang.service.RoleService;
import com.edu.tuiguang.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/findAll")
	public ResultBean findAll() {
		List<Role> all = roleService.findAll();
		return ResultUtils.success(all);
	}

	@GetMapping("/findList")
	public ResultBean findList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "roleName", required = false) String roleName
	) {
		PageBean<List<Role>> list = roleService.findList(pageIndex, pageSize, roleName);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("roleId") Integer roleId) {
		Role role = roleService.findById(roleId);
		return ResultUtils.success(role);
	}

	@PostMapping("/add")
	public ResultBean add(HttpServletRequest request, @RequestBody Role role) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		role.setCreateUserId(userId);
		roleService.insert(role);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(HttpServletRequest request, @RequestBody Role role) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		role.setUpdateUserId(userId);
		roleService.update(role);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestBody Map<String, Integer> map) {
		Integer roleId = map.get("roleId");
		roleService.del(roleId);
		return ResultUtils.success();
	}
}
