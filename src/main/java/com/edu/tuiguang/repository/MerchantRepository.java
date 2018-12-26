package com.edu.tuiguang.repository;

import com.edu.tuiguang.entity.Merchant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository {
	List<Merchant> findAll(@Param("merchantName") String merchantName);
	List<Merchant> findList(
			@Param("pageStart") Integer pageStart,
			@Param("pageSize") Integer pageSize,
			@Param("merchantName") String merchantName
	);
	Merchant findById(Integer merchantId);
	Long total();
	void insert(Merchant merchant);
	void update(Merchant merchant);
	void del(Integer merchantId);
}
