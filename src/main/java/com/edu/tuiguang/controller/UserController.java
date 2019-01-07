package com.edu.tuiguang.controller;

import com.edu.tuiguang.config.Constant;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.entity.User;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.service.UserService;
import com.edu.tuiguang.utils.JwtUtils;
import com.edu.tuiguang.utils.MD5Utils;
import com.edu.tuiguang.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);

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
	public ResultBean findList(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
	) {
		PageBean<List<User>> list = userService.findList(userName, pageIndex, pageSize);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("userId") Integer userId) {
		User user = userService.findById(userId);
		return ResultUtils.success(user);
	}

	@GetMapping("findByToken")
	public ResultBean findByToken(HttpServletRequest request) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		User user = userService.findById(userId);
		return ResultUtils.success(user);
	}

	@PostMapping("/add")
	public ResultBean add(HttpServletRequest request, @RequestBody User user) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		user.setCreateUserId(userId);
		userService.insert(user);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(HttpServletRequest request, @RequestBody User user) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		user.setUpdateUserId(userId);
		userService.update(user);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestBody Map<String, Integer> map) {
		Integer userId = map.get("userId");
		userService.del(userId);
		return ResultUtils.success();
	}

	@PostMapping("/login")
	public ResultBean login(HttpServletResponse response, @RequestBody Map<String, String> loginInfo) {
		String userName = loginInfo.get("userName");
		if (StringUtils.isBlank(userName))
			throw new CommonException(ErrorCode.USERNAME_NOTNULL);
		String password = loginInfo.get("password");
		if (StringUtils.isBlank(password))
			throw new CommonException(ErrorCode.PASSWORD_NOTNULL);

		String pwd = MD5Utils.md5(password, Constant.MD5_SALT);
		User user = userService.findByNameAndPassword(userName, pwd);

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
