package com.edu.tuiguang.service;

import com.edu.tuiguang.entity.Member;
import com.edu.tuiguang.entity.PageBean;

import java.util.List;

public interface MemberService {
	List<Member> findAll();
	PageBean<List<Member>> findList(
			Integer pageIndex,
			Integer pageSize,
			String mobile
	);
	Member findById(Integer memberId);
	Member findByMobile(String mobile);
	Member findByOpenId(String openId);
	Integer insert(Member member);
	void update(Member member);
	void del(Integer memberId);
}
