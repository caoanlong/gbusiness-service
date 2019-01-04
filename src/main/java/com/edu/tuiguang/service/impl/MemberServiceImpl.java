package com.edu.tuiguang.service.impl;

import com.edu.tuiguang.entity.Member;
import com.edu.tuiguang.entity.PageBean;
import com.edu.tuiguang.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
	@Override
	public List<Member> findAll() {
		return null;
	}

	@Override
	public PageBean<List<Member>> findList(Integer pageIndex, Integer pageSize, String mobile) {
		return null;
	}

	@Override
	public Member findById(Integer memberId) {
		return null;
	}

	@Override
	public void insert(Member member) {

	}

	@Override
	public void update(Member member) {

	}

	@Override
	public void del(Integer memberId) {

	}
}
