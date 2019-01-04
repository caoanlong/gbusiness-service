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
	Long total(@Param("mobile") String mobile);
	void insert(Member member);
	void update(Member member);
	void del(Integer memberId);
}
