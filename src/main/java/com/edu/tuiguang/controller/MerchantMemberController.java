package com.edu.tuiguang.controller;

import com.edu.tuiguang.entity.MerchantMember;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.service.MerchantMemberService;
import com.edu.tuiguang.utils.ResultUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "MerchantMemberController", description = "商家报名人管理")
@RestController
@RequestMapping("/admin/merchantMember")
public class MerchantMemberController {
	@Autowired
	private MerchantMemberService merchantMemberService;

	@GetMapping("/findAll")
	public ResultBean findAll(
			@RequestParam(value = "merchantMemberName", required = false) String merchantMemberName,
			@RequestParam(value = "isAddMerchant", required = false) String isAddMerchant
	) {
		List<MerchantMember> all = merchantMemberService.findAll(merchantMemberName, isAddMerchant);
		return ResultUtils.success(all);
	}

	@GetMapping("/findList")
	public ResultBean findList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "merchantMemberName", required = false) String merchantMemberName,
			@RequestParam(value = "isAddMerchant", required = false) String isAddMerchant
	) {
		PageBean<List<MerchantMember>> list = merchantMemberService.findList(pageIndex, pageSize, merchantMemberName, isAddMerchant);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("merchantMemberId") Integer merchantMemberId) {
		MerchantMember merchantMember = merchantMemberService.findById(merchantMemberId);
		return ResultUtils.success(merchantMember);
	}

	@PostMapping("/add")
	public ResultBean add(@RequestBody MerchantMember merchantMember) {
		merchantMemberService.insert(merchantMember);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(@RequestBody MerchantMember merchantMember) {
		merchantMemberService.update(merchantMember);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestBody Map<String, Integer> map) {
		Integer merchantMemberId = map.get("merchantMemberId");
		merchantMemberService.del(merchantMemberId);
		return ResultUtils.success();
	}
}
