package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
	List<Member> findAll();
	List<Member> findList(
			@Param("pageStart") Integer pageStart,
			@Param("pageSize") Integer pageSize,
			@Param("mobile") String mobile
	);
	Member findById(Integer memberId);
	Member findByMobile(String mobile);
	Member findByOpenId(String openId);
	Long total(@Param("mobile") String mobile);
	Integer insert(Member member);
	void update(Member member);
	void del(Integer memberId);
}
