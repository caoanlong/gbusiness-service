package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.Member;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.entity.exception.CommonException;
import com.edu.tuiguang.enums.ErrorCode;
import com.edu.tuiguang.repository.MemberRepository;
import com.edu.tuiguang.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Override
	public PageBean<List<Member>> findList(
			Integer pageIndex,
			Integer pageSize,
			String mobile) {
		Integer pageStart = (pageIndex - 1) * pageSize;
		List<Member> list = memberRepository.findList(pageStart, pageSize, mobile);
		Long total = memberRepository.total(mobile);
		PageBean<List<Member>> pageBean = new PageBean<>();
		pageBean.setList(list);
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		pageBean.setTotal(total);
		return pageBean;
	}

	@Override
	public Member findById(Integer memberId) {
		return memberRepository.findById(memberId);
	}

	@Override
	public Member findByMobile(String mobile) {
		return memberRepository.findByMobile(mobile);
	}

	@Override
	public Member findByOpenId(String openId) {
		return memberRepository.findByOpenId(openId);
	}

	@Override
	public Integer insert(Member member) {
		if (StringUtils.isBlank(member.getMobile()))
			throw new CommonException(ErrorCode.MOBILE_NOTNULL);
		member.setCreateTime(new Date());
		memberRepository.insert(member);
		return member.getMemberId();
	}

	@Override
	public void update(Member member) {
		if (null == member.getMemberId() || StringUtils.isBlank(member.getMemberId().toString()))
			throw new CommonException(ErrorCode.USERID_NOTNULL);
		memberRepository.update(member);
	}

	@Override
	public void del(Integer memberId) {
		memberRepository.del(memberId);
	}
}
