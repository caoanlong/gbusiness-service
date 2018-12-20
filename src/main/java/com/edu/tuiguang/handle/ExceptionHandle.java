package com.edu.tuiguang.handle;

import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.exception.CommonException;
import com.edu.tuiguang.utils.ResultUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultBean handle(Exception e) {
		System.out.println(e);
		if (e instanceof CommonException) {
			CommonException commonException = (CommonException) e;
			return ResultUtils.error(commonException.getCode(), commonException.getMessage());
		} else if (e instanceof MissingServletRequestParameterException) {
			// 参数缺失错误
			return ResultUtils.error(400, e.getMessage());
		} else if (e instanceof HttpRequestMethodNotSupportedException) {
			// 请求方式错误
			return ResultUtils.error(405, e.getMessage());
		} else {
			// 未知错误
			return ResultUtils.error(ErrorCode.UNKONW_ERROR);
		}
	}
}
