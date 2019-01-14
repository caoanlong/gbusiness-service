package com.edu.tuiguang.controller;

import com.edu.tuiguang.config.Constant;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.utils.RandomUtils;
import com.edu.tuiguang.utils.ResultUtils;
import com.edu.tuiguang.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping("/getSmsCode")
	public ResultBean getSmsCode(@RequestParam("mobile") String mobile) {
		if (StringUtils.isBlank(mobile))
			throw new CommonException(ErrorCode.MOBILE_NOTNULL);
		if (ValidatorUtils.isMobile(mobile))
			throw new CommonException(ErrorCode.MOBILE_FORMAT_ERROR);
		String smsCode = RandomUtils.getNumCode(Constant.SMSCODE_NUM);
		stringRedisTemplate.opsForValue().set(mobile, smsCode, Constant.SMSCODE_EXPIRE, TimeUnit.SECONDS);
		return ResultUtils.success(smsCode);
	}
}
