package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.Merchant;
import com.edu.tuiguang.entity.MerchantMember;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.repository.MerchantMemberRepository;
import com.edu.tuiguang.repository.MerchantRepository;
import com.edu.tuiguang.service.MerchantService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private MerchantMemberRepository merchantMemberRepository;

	@Override
	public List<Merchant> findAll(String merchantName) {
		return merchantRepository.findAll(merchantName);
	}

	@Override
	public PageBean<List<Merchant>> findList(Integer pageIndex, Integer pageSize, String merchantName) {
		Integer pageStart = (pageIndex - 1) * pageSize;
		List<Merchant> list = merchantRepository.findList(pageStart, pageSize, merchantName);
		Long total = merchantRepository.total();
		PageBean<List<Merchant>> pageBean = new PageBean<>();
		pageBean.setList(list);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public Merchant findById(Integer merchantId) {
		return merchantRepository.findById(merchantId);
	}

	@Override
	@Transactional
	public void insert(Merchant merchant) {
		if (StringUtils.isBlank(merchant.getMerchantName()))
			throw new CommonException(ErrorCode.MERCHANTNAME_NOTNULL);

		Integer merchantMemberId = merchant.getMerchantMemberId();
		if (null == merchantMemberId || StringUtils.isBlank(merchantMemberId.toString()))
			throw new CommonException(ErrorCode.MERCHANTMEMBER_NOTNULL);

		MerchantMember merchantMember = new MerchantMember();
		merchantMember.setMerchantMemberId(merchantMemberId);
		merchantMember.setIsAddMerchant("Y");
		merchantMemberRepository.update(merchantMember);
		merchant.setCreateTime(new Date());
		merchantRepository.insert(merchant);
	}

	@Override
	public void update(Merchant merchant) {
		if (null == merchant.getMerchantId() || StringUtils.isBlank(merchant.getMerchantId().toString()))
			throw new CommonException(ErrorCode.MERCHANTID_NOTNULL);
		merchant.setUpdateTime(new Date());
		merchantRepository.update(merchant);
	}

	@Override
	public void del(Integer merchantId) {
		merchantRepository.del(merchantId);
	}
}
