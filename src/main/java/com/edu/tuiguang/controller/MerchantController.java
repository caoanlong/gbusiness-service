package com.edu.tuiguang.controller;

import com.edu.tuiguang.entity.Merchant;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.ResultBean;
import com.edu.tuiguang.service.MerchantService;
import com.edu.tuiguang.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/admin/merchant", "/app/merchant"})
public class MerchantController {
	@Autowired
	private MerchantService merchantService;

	@GetMapping("/findAll")
	public ResultBean findAll(@RequestParam(value = "merchantName", required = false) String merchantName) {
		List<Merchant> all = merchantService.findAll(merchantName);
		return ResultUtils.success(all);
	}

	@GetMapping("/findList")
	public ResultBean findList(
			@RequestParam(value = "pageIndex", required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value = "merchantName", required = false) String merchantName
	) {
		PageBean<List<Merchant>> list = merchantService.findList(pageIndex, pageSize, merchantName);
		return ResultUtils.success(list);
	}

	@GetMapping("/findById")
	public ResultBean findById(@RequestParam("merchantId") Integer merchantId) {
		Merchant merchant = merchantService.findById(merchantId);
		return ResultUtils.success(merchant);
	}

	@GetMapping("/findActivities")
	public ResultBean findActivities(@RequestParam("merchantId") Integer merchantId) {
		List<Merchant> activities = merchantService.findActivities(merchantId);
		return ResultUtils.success(activities);
	}

	@PostMapping("/add")
	public ResultBean add(HttpServletRequest request, @RequestBody Merchant merchant) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		merchant.setCreateUserId(userId);
		merchantService.insert(merchant);
		return ResultUtils.success();
	}

	@PostMapping("/update")
	public ResultBean update(HttpServletRequest request, @RequestBody Merchant merchant) {
		Integer userId = Integer.valueOf((String) request.getAttribute("userId"));
		merchant.setUpdateUserId(userId);
		merchantService.update(merchant);
		return ResultUtils.success();
	}

	@PostMapping("/del")
	public ResultBean del(@RequestBody Map<String, Integer> map) {
		Integer merchantId = map.get("merchantId");
		merchantService.del(merchantId);
		return ResultUtils.success();
	}
}
