package com.edu.tuiguang.utils;

import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.enums.ErrorCode;

public class ResultUtils {
	public static ResultBean success(Object object) {
		ResultBean result = new ResultBean();
		result.setCode(200);
		result.setMsg("成功");
		result.setData(object);
		return result;
	}

	public static ResultBean success() {
		return success(null);
	}

	public static ResultBean error(Integer code, String msg) {
		ResultBean result = new ResultBean();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public static ResultBean error(ErrorCode errorCode) {
		ResultBean result = new ResultBean();
		result.setCode(errorCode.getCode());
		result.setMsg(errorCode.getMsg());
		return result;
	}
}
