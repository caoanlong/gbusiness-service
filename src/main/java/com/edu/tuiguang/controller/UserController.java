package com.edu.tuiguang.controller;

import com.edu.tuiguang.config.Constant;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.entity.User;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.service.UserService;
import com.edu.tuiguang.utils.JwtUtils;
import com.edu.tuiguang.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

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
	public ResultBean login(HttpServletResponse response, @RequestBody Map<String, String> loginInfo) {
		if (StringUtils.isBlank(loginInfo.get("userName")))
			throw new CommonException(ErrorCode.USERNAME_NOTNULL);
		if (StringUtils.isBlank(loginInfo.get("password")))
			throw new CommonException(ErrorCode.PASSWORD_NOTNULL);
		User user = userService.findByNameAndPassword(loginInfo.get("userName"), loginInfo.get("password"));
		if (null == user) throw new CommonException(ErrorCode.USERNAMEORPASSWORD_ERROR);

		return loginReturn(response, user);
	}

	private ResultBean loginReturn(HttpServletResponse response, User user) {
		String jwt = jwtUtils.createJWT(Constant.JWT_ID, "Caoanlong", user.getUserId().toString(), Constant.JWT_TTL);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Authorization", jwt);
		return ResultUtils.success();
	}
}
