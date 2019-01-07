package com.edu.tuiguang.controller;


import com.edu.tuiguang.config.Constant;
import com.edu.tuiguang.entity.*;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.service.MemberService;
import com.edu.tuiguang.utils.JwtUtils;
import com.edu.tuiguang.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/admin/member", "/app/member"})
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/findAll")
	public ResultBean findAll() {
		List<Member> list = memberService.findAll();
		return ResultUtils.success(list);
	}

	@GetMapping("/findList")
	public ResultBean findList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "mobile", required = false) String mobile
	) {
		PageBean<List<Member>> list = memberService.findList(pageIndex, pageSize, mobile);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("memberId") Integer memberId) {
		Member member = memberService.findById(memberId);
		return ResultUtils.success(member);
	}

	@GetMapping("findByToken")
	public ResultBean findByToken(HttpServletRequest request) {
		Integer memberId = Integer.valueOf((String) request.getAttribute("memberId"));
		Member member = memberService.findById(memberId);
		return ResultUtils.success(member);
	}

	@PostMapping("/add")
	public ResultBean add(@RequestBody Member member) {
		memberService.insert(member);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(@RequestBody Member member) {
		memberService.update(member);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestBody Map<String, Integer> map) {
		Integer memberId = map.get("memberId");
		memberService.del(memberId);
		return ResultUtils.success();
	}

	@GetMapping("/getSmsCode")
	public ResultBean getSmsCode(@RequestParam("mobile") String mobile) {
		if (StringUtils.isBlank(mobile))
			throw new CommonException(ErrorCode.MOBILE_NOTNULL);
		return ResultUtils.success();
	}

	@PostMapping("/login")
	public ResultBean login(HttpServletResponse response, @RequestBody Map<String, String> loginInfo) {
		String mobile = loginInfo.get("mobile");
		String openId = loginInfo.get("openid");
		String unionid = loginInfo.get("unionid");
		String nickName = loginInfo.get("nickName");
		String headImgUrl = loginInfo.get("headImgUrl");
		String sex = loginInfo.get("sex");
		if (StringUtils.isBlank(mobile))
			throw new CommonException(ErrorCode.MOBILE_NOTNULL);
		if (StringUtils.isBlank(openId))
			throw new CommonException(ErrorCode.OPENID_NOTNULL);

		Member memberForMobile = memberService.findByMobile(mobile);
		Member memberForOpenId = memberService.findByOpenId(openId);

		Integer memberId = null;
		if (null == memberForMobile) {
			// 如果用户不存在
			Member member = new Member();
			member.setMobile(mobile);
			member.setNickName(nickName);
			member.setSex(sex);
			member.setHeadImgUrl(headImgUrl);
			member.setOpenId(openId);
			member.setUnionId(unionid);
			memberId = memberService.insert(member);
		} else {
			memberId = memberForMobile.getMemberId();
			if (null == memberForOpenId) {
				memberForMobile.setOpenId(openId);
				memberForMobile.setUnionId(unionid);
				memberService.update(memberForMobile);
			} else {
				if (mobile != memberForOpenId.getMobile()) {
					memberForMobile.setOpenId(openId);
					memberForMobile.setUnionId(unionid);
					memberService.update(memberForMobile);
				}
			}
		}

		return loginReturn(response, memberId.toString());
	}

	private ResultBean loginReturn(HttpServletResponse response, String memberIdStr) {
		String jwt = jwtUtils.createJWT(Constant.JWT_ID, "Caoanlong", memberIdStr, Constant.JWT_TTL);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Authorization", jwt);
		return ResultUtils.success();
	}
}
