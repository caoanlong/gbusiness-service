package com.edu.tuiguang.service;

import com.edu.tuiguang.entity.MerchantMember;
import com.edu.tuiguang.entity.PageBean;

import java.util.List;

public interface MerchantMemberService {
	List<MerchantMember> findAll(String merchantMemberName, String isAddMerchant);
	PageBean<List<MerchantMember>> findList(
			Integer pageIndex,
			Integer pageSize,
			String merchantMemberName,
			String isAddMerchant
	);
	MerchantMember findById(Integer merchantMemberId);
	void insert(MerchantMember merchantMember);
	void update(MerchantMember merchantMember);
	void del(Integer merchantMemberId);
}
