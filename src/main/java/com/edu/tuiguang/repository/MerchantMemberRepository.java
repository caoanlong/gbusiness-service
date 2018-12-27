package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.MerchantMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantMemberRepository {
	List<MerchantMember> findAll(
			@Param("merchantMemberName") String merchantMemberName,
			@Param("isAddMerchant") String isAddMerchant
	);
	List<MerchantMember> findList(
			@Param("pageStart") Integer pageStart,
			@Param("pageSize") Integer pageSize,
			@Param("merchantMemberName") String merchantMemberName,
			@Param("isAddMerchant") String isAddMerchant
	);
	MerchantMember findById(Integer merchantMemberId);
	MerchantMember findByMobile(String mobile);
	Long total(
			@Param("merchantMemberName") String merchantMemberName,
			@Param("isAddMerchant") String isAddMerchant
	);
	void insert(MerchantMember merchantMember);
	void update(MerchantMember merchantMember);
	void del(Integer merchantMemberId);
}
