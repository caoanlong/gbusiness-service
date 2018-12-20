package com.edu.tuiguang.controller;

import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.entity.User;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.exception.CommonException;
import com.edu.tuiguang.service.UserService;
import com.edu.tuiguang.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/findAll")
	public ResultBean findAll() {
		List<User> all = userService.findAll();
		return ResultUtils.success(all);
	}

	@GetMapping("/findList")
	public ResultBean findList(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
		PageBean<List<User>> list = userService.findList(pageIndex, pageSize);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("userId") Integer userId) {
		User user = userService.findById(userId);
		return ResultUtils.success(user);
	}

	@PostMapping("/add")
	public ResultBean add(@RequestBody User user) {
		userService.insert(user);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(@RequestBody User user) {
		userService.update(user);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestParam("userId") Integer userId) {
		userService.del(userId);
		return ResultUtils.success();
	}

	@PostMapping("/login")
	public ResultBean login(String userName, String password) {
		if (StringUtils.isBlank(userName))
			throw new CommonException(ErrorCode.USERNAME_NOTNULL);
		if (StringUtils.isBlank(password))
			throw new CommonException(ErrorCode.PASSWORD_NOTNULL);
		User user = userService.findByNameAndPassword(userName, password);
		if (null == user) throw new CommonException(ErrorCode.USERNAMEORPASSWORD_ERROR);
		return ResultUtils.success();
	}
}
