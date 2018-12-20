package com.edu.tuiguang.controller;

import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.entity.Role;
import com.edu.tuiguang.service.RoleService;
import com.edu.tuiguang.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@GetMapping("/findAll")
	public ResultBean findAll() {
		List<Role> all = roleService.findAll();
		return ResultUtils.success(all);
	}

	@GetMapping("/findList")
	public ResultBean findList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
		PageBean<List<Role>> list = roleService.findList(pageIndex, pageSize);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("roleId") Integer roleId) {
		Role role = roleService.findById(roleId);
		return ResultUtils.success(role);
	}

	@PostMapping("/add")
	public ResultBean add(@RequestBody Role role) {
		roleService.insert(role);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(@RequestBody Role role) {
		roleService.update(role);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestParam("roleId") Integer roleId) {
		roleService.del(roleId);
		return ResultUtils.success();
	}
}
