package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.MerchantMember;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.repository.MerchantMemberRepository;
import com.edu.tuiguang.service.MerchantMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MerchantMemberServiceImpl implements MerchantMemberService {

	@Autowired
	private MerchantMemberRepository merchantMemberRepository;

	@Override
	public List<MerchantMember> findAll(String merchantMemberName, String isAddMerchant) {
		return merchantMemberRepository.findAll(merchantMemberName, isAddMerchant);
	}

	@Override
	public PageBean<List<MerchantMember>> findList(
			Integer pageIndex,
			Integer pageSize,
			String merchantMemberName,
			String isAddMerchant
	) {
		Integer pageStart = (pageIndex - 1) * pageSize;
		List<MerchantMember> list = merchantMemberRepository.findList(pageStart, pageSize, merchantMemberName, isAddMerchant);
		Long total = merchantMemberRepository.total(merchantMemberName, isAddMerchant);
		PageBean<List<MerchantMember>> pageBean = new PageBean<>();
		pageBean.setList(list);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public MerchantMember findById(Integer merchantMemberId) {
		return merchantMemberRepository.findById(merchantMemberId);
	}

	@Override
	public void insert(MerchantMember merchantMember) {
		if (StringUtils.isBlank(merchantMember.getMerchantMemberName()))
			throw new CommonException(ErrorCode.NAME_NOTNULL);
		if (StringUtils.isBlank(merchantMember.getMobile()))
			throw new CommonException(ErrorCode.MOBILE_NOTNULL);
		if (StringUtils.isBlank(merchantMember.getIndustry()))
			throw new CommonException(ErrorCode.INDUSTRY_NOTNULL);
		MerchantMember byMobile = merchantMemberRepository.findByMobile(merchantMember.getMobile());
		if (null != byMobile)
			throw new CommonException(ErrorCode.MOBILE_EXIST);
		merchantMember.setCreateTime(new Date());
		merchantMemberRepository.insert(merchantMember);
	}

	@Override
	public void update(MerchantMember merchantMember) {
		if (null == merchantMember.getMerchantMemberId() || StringUtils.isBlank(merchantMember.getMerchantMemberId().toString()))
			throw new CommonException(ErrorCode.USERID_NOTNULL);
		merchantMemberRepository.update(merchantMember);
	}

	@Override
	public void del(Integer merchantMemberId) {
		merchantMemberRepository.del(merchantMemberId);
	}
}
